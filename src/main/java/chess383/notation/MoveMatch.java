/*
 *  MoveMatch.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2010-2021 Jörg Dippel
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package chess383.notation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import chess383.ColorEnum;
import chess383.exception.Chess383Exception;
import chess383.exception.Chess383NotationException;
import chess383.piece.abstraction.Piece;
import chess383.piece.abstraction.PieceFactory;
import chess383.player.Player;
import chess383.position.Position;
import chess383.statemachine.exception.KingWouldBeCheckedAfterwardsException;
import chess383.transition.Transition;

/**
 * <p>
 * Provides the long algebraic notation for a given standard algebraic notation and position.
 * </p> <p>
 * The move from valid moves with best match is provided.
 * </p>
 *
 * @author    Jörg Dippel
 * @version   January 2021
 *
 */
public class MoveMatch {

    final static int MINIMAL_MOVE_STRING_LENGTH = 2;
    
    /** ---------  Attributes  -------------------------------- */
    
    private Position position;
    private Locale inputLocale;
    
    /** ---------  Constructors  ------------------------------ */

    private MoveMatch( Position position, Locale inputLocale ) {

        setPosition( position );
        setInputLocale( inputLocale );
    }

    /** ---------  Getter and Setter  ------------------------- */

    protected Position getPosition( )              { return this.position; }
    private void setPosition( Position value )     { this.position = value; }
    
    private Locale getInputLocale()                { return this.inputLocale; }
    private void setInputLocale( Locale value )    { this.inputLocale = value; }
    
    /** ---------  Factory  ----------------------------------- */
    
    public static MoveMatch create( Position value, Locale inputLocale ) {
        
        if( value == null ) {
            Chess383NotationException.throwNotationException( "Invalid position" );
        }
        return new MoveMatch( value, inputLocale );
    }

    public static MoveMatch create( Position value ) {

        return create( value, Locale.ENGLISH );
    }
    
    public static MoveMatch create( String position, Locale inputLocale ) {
        
        return create( Position.create( position ), inputLocale );
    }
    
    public static MoveMatch create( String position ) {
        
        return create( Position.create( position ) );
    }
    
    /* -------------------------------------------------------- */

    private String getPieceType( String move ) {
        
        if( move.length() > 0 ) {
            Character pieceType = Ply.readLetter( move );
            if( Character.isLetter( pieceType ) && Character.isUpperCase( pieceType ) ) return "" + pieceType;
        }
        return "";
    }

    public Ply findByTarget( String moveDescription ) {
        
        if( moveDescription == null || moveDescription.length() < MINIMAL_MOVE_STRING_LENGTH ) return Ply.INVALID_MOVE;
        
        String pieceType = getPieceType( moveDescription );
        return findByTarget( ( pieceType.length() == 0 ) ? ' ' : pieceType.charAt( 0 ), moveDescription.substring( pieceType.length() ) );
    }
    
    private Piece createPiece( char pieceType, String location ) {
        
        if( pieceType == ' ' ) {
            return PieceFactory.createPiece( location, getPosition().getPawnLetterForForsythEdwardsNotation() );
        }
        
        if( getInputLocale() == Locale.ENGLISH ) return PieceFactory.createPiece( location, pieceType );
        return PieceFactory.createPiece( location, pieceType, getInputLocale() );
    }
    
    public Ply findByTarget( char pieceType, String moveDescription ) {
        
        String targetLocation;
        if( Ply.validateLocation( moveDescription ) ) {
            targetLocation = moveDescription;
            Piece piece = createPiece( pieceType, targetLocation );
            if( piece != null ) {
                return filterResultList( findMatchingOriginLocations( piece ), piece );
            }
        }
        else {
            targetLocation = getLastLocation( moveDescription );
            int cursor = moveDescription.indexOf( targetLocation );
            String prefix = moveDescription.substring( 0, cursor );
            String postfix = moveDescription.substring( cursor + targetLocation.length() );
            String promotion = getPromotion( postfix );
            if( promotion.length() > 0 ) postfix = postfix.substring( promotion.length() + 1 );    // adding the '='
            
            Piece piece = createPiece( pieceType, targetLocation );
            if( piece != null ) {
                List<String> matchingLocations = filterByPattern( prefix, findMatchingOriginLocations( piece ) );
                return filterResultList( matchingLocations, piece ).setPromotion( promotion ).setComment( postfix );
            }
        }
        return Ply.INVALID_MOVE;
    }
    
    private List<String> filterByPattern( String pattern, List<String> locations ) {
        
        List<String> matchingLocations = new ArrayList<String>();
        int currentMatchingValue = 0;
        for( String location : locations ) {
            int matchingValue = calculateDistance( pattern, location );
            if( matchingValue >= currentMatchingValue ) {
                if( matchingValue > currentMatchingValue ) {
                    matchingLocations = new ArrayList<String>();
                    currentMatchingValue = matchingValue;
                }
                matchingLocations.add( location );
            }
        }
        return matchingLocations;
    }
    
    private Ply filterResultList( List<String> locations, Piece piece ) {
        
        if( locations.size() == 1 ) {
            return Ply.create( locations.get( 0 ), piece.getLocation() );
        }
        else if( locations.size() == 0 ) {
            String message = String.format( "There is no %s to move from anywhere to location %s for position %s", piece.getName( Locale.ENGLISH ), piece.getLocation(), getPosition() );
            Chess383NotationException.throwNotationException( message );
        }
        else {
            List<String> ambiguousList = new ArrayList<String>();
            for( String location : locations ) {
                try {
                    @SuppressWarnings("unused")
                    Position position = Transition.create( getPosition() ).change( location, piece.getLocation() );
                    ambiguousList.add( location );
                }
                catch ( KingWouldBeCheckedAfterwardsException chessException ) {
                    // do nothing
                }
                catch ( Chess383Exception chessException ) {
                    // do nothing
                }
            }
            if( ambiguousList.size() == 1 ) return Ply.create( ambiguousList.get( 0 ), piece.getLocation() );
            String message = String.format( "There are more pieces of type %s to move from anywhere to location %s for position %s, ie %s", piece.getName( Locale.ENGLISH ), locations, getPosition(), locations );
            Chess383NotationException.throwNotationException( message );
        }
        return Ply.INVALID_MOVE;
    }
    
    private static int calculateDistance( String pattern, String location ) {
    
        int distance = 0;
        for( int patternCursor = 0; patternCursor < pattern.length(); patternCursor++ ) {
            for( int locationCursor = 0; locationCursor < location.length(); locationCursor++ ) {
                if( pattern.charAt( patternCursor ) == location.charAt( locationCursor ) ) {
                    distance++;
                }
            }
        }
        return distance;    
    }
    
    private List<String> findMovingPawns( Player player, String targetLocation ) {
        
        List<String> resultList = new ArrayList<String>();
        
        List<String> pawnLocations = player.getPieceTypeLocations( player.getPawnLetterForForsythEdwardsNotation() );
        for( String location : pawnLocations ) {
            if( canPieceMoveToTargetLocation( player.getPiece( location ), targetLocation ) ) {
                resultList.add( location );
            }
        }
        return resultList;
    }
    
    private List<String> findCapturingPawns( Player player, String targetLocation ) {
        
        List<String> resultList = new ArrayList<String>();
        
        List<String> pawnLocations = player.getPieceTypeLocations( player.getPawnLetterForForsythEdwardsNotation() );
        for( String location : pawnLocations ) {
            if( canPieceCaptureToTargetLocation( player.getPiece( location ), targetLocation ) ) {
                resultList.add( location );
            }
        }
        return resultList;
    }
    
    private boolean canPieceMoveToTargetLocation( Piece piece, String targetLocation ) {
        
        if( piece != null ) {
            for( List<String> line : piece.getMovingLines() ) {
                if( line.contains( targetLocation ) ) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean canPieceCaptureToTargetLocation( Piece piece, String targetLocation ) {
        
        if( piece != null ) {
            for( List<String> line : piece.getCapturingLines() ) {
                if( line.contains( targetLocation ) ) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isCaptureEnPassant( String targetLocation ) {
        
        return getPosition().getEnPassantLocation().getLocation() != null && getPosition().getEnPassantLocation().getLocation().compareTo( targetLocation ) == 0;
    }
    
    private List<String> findMatchingPawnLocations( String targetLocation, Player ownPlayer, Player opponentPlayer ) {
        
        if( ownPlayer.getPiece( targetLocation ) != null ) {
            // target location is occupied by an own  piece
            return new ArrayList<String>();
        }
        else if( opponentPlayer.getPiece( targetLocation ) != null ) {
            // target location is occupied by an opponent's piece
             return findCapturingPawns( ownPlayer, targetLocation );
        }
        else if( isCaptureEnPassant( targetLocation ) ) {
            // target location is empty but own player can capture en passant
            return findCapturingPawns( ownPlayer, targetLocation );
        }
        else {
            return findMovingPawns( ownPlayer, targetLocation );
        }
    }
    
    private List<String> findMatchingOriginLocations( Piece piece ) {
        
        if( piece == null ) return new ArrayList<String>();
        
        if( piece.isPawn() ) {
            if( getPosition().getActivePlayer().getActive() == ColorEnum.WHITE ) {
                
                String targetLocation = piece.getLocation();
                Player ownPlayer = getPosition().getFirst().getPlayer();
                Player opponentPlayer = getPosition().getSecond().getPlayer();
                
                return findMatchingPawnLocations( targetLocation, ownPlayer, opponentPlayer );
            }
            else {
                String targetLocation = piece.getLocation();
                Player ownPlayer = getPosition().getSecond().getPlayer();
                Player opponentPlayer = getPosition().getFirst().getPlayer();
                
                return findMatchingPawnLocations( targetLocation, ownPlayer, opponentPlayer );
            }
        }
        else {
            
            String targetLocation = piece.getLocation();
            List<String> matchingLocations = new ArrayList<String>();
            
            ColorEnum colorToMove = getPosition().getActivePlayer().getActive();
            Player ownPlayer = ( colorToMove == ColorEnum.WHITE ) ? getPosition().getFirst().getPlayer() : getPosition().getSecond().getPlayer();
            Player oppositePlayer = ( colorToMove == ColorEnum.WHITE ) ? getPosition().getSecond().getPlayer() : getPosition().getFirst().getPlayer();
            Set<List<String>> lines = ( oppositePlayer.getPiece( targetLocation ) != null ) ? piece.getCapturingLines() : piece.getMovingLines();
            
            for( List<String> line : lines ) {
                for( String location : line ) {
                    if( location.compareTo( targetLocation ) != 0 ) {
                        if( ownPlayer.getPiece( location ) != null ) {
                            if( ownPlayer.getPiece( location ).getType() == piece.getType() ) {
                                matchingLocations.add( location );
                            }
                        }
                    }
                }
            }
            
            return matchingLocations;
        }
    }
    
    public static String getLastLocation( String moveDescription ) {
        
        if( moveDescription == null || moveDescription.length() < MINIMAL_MOVE_STRING_LENGTH ) return "";
        
        int cursor = moveDescription.length() - MINIMAL_MOVE_STRING_LENGTH;
        while( cursor >= 0 ) {
            String tailOfMoveDescription = moveDescription.substring( cursor );
            Character headOfAssumedLocation = tailOfMoveDescription.charAt( 0 );
            if( isSeparator( headOfAssumedLocation ) ) return "";
            
            String assumedLocation = Ply.readLocationInformation( tailOfMoveDescription );
            if( Ply.validateLocation( assumedLocation ) ) return assumedLocation;
            cursor--;
        }
        
        return "";
    }
    
    private static boolean isSeparator( Character separator ) {
        
        return separator == Ply.getMoveSeparator().charAt( 0 ) || separator == Ply.getCaptureSeparator().charAt( 0 );
    }
    
    private String getPromotion( String description ) {
        
        if( description.length() > 1 && description.charAt( 0 ) == '=' ) {
            return getPieceType( description.substring( 1 ) );
        }
        return "";
    }
}
