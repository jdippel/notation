/*
 *  AlgebraicNotation.java
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

import java.util.Locale;

import chess383.ColorEnum;
import chess383.exception.Chess383NotationException;
import chess383.piece.abstraction.PieceFactory;
import chess383.position.Position;

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
public class AlgebraicNotation {

    /** ---------  Attributes  -------------------------------- */
    
    private Position position;
    private Locale inputLocale;
    private Locale outputLocale;
    
    /** ---------  Constructors  ------------------------------ */

    private AlgebraicNotation( Position position, Locale input, Locale output ) {

        setPosition( position );
        setInputLocale( input );
        setOutputLocale( output );
    }

    /** ---------  Getter and Setter  ------------------------- */

    protected Position getPosition( )              { return this.position; }
    private void setPosition( Position value )     { this.position = value; }
    
    private Locale getInputLocale()                { return this.inputLocale; }
    private void setInputLocale( Locale value )    { this.inputLocale = value; }
    
    private Locale getOutputLocale()               { return this.outputLocale; }
    private void setOutputLocale( Locale value )   { this.outputLocale = value; }
    
    /** ---------  Factory  ----------------------------------- */
    
    public static AlgebraicNotation create( Position value, Locale input, Locale output ) {
        
        if( value == null ) {
            Chess383NotationException.throwNotationException( "Invalid position" );
        }
        return new AlgebraicNotation( value, input, output );
    }

    public static AlgebraicNotation create( Position value ) {

        return create( value, Locale.ENGLISH, Locale.GERMAN );
    }
    
    public static AlgebraicNotation create( Position value, Locale output ) {

        return create( value, Locale.ENGLISH, output );
    }
    
    public static AlgebraicNotation create( String position, Locale output ) {
        
        return create( Position.create( position ), output );
    }
    
    public static AlgebraicNotation create( String position, Locale input, Locale output ) {
        
        return create( Position.create( position ), input, output );
    }
    
    public static AlgebraicNotation create( String position ) {
        
        return create( Position.create( position ) );
    }
    
    /* -------------------------------------------------------- */

    private boolean isSeparator( Character separator ) {
        
        if( separator == Ply.getMoveSeparator().charAt( 0 ) ) return true;
        if( separator == Ply.getCaptureSeparator().charAt( 0 ) ) return true;
        return false;
    }
    
    private boolean isCastling( String move ) {
    
        return( move.charAt( 0 ) == '0' || move.charAt( 0 ) == 'O' );
    }
    
    private Ply getCastling( String description ) {
        
        final String ARBITRARY_LOCATION = "e5";
        final String kingPieceType = PieceFactory.createPiece( ARBITRARY_LOCATION, 'K' ).getType();
        
        if( description.length() > 3 && ( "O-O-".compareTo( description.substring( 0, 4 ) ) == 0 || "0-0-".compareTo( description.substring( 0, 4 ) ) == 0 )) {
            if( getPosition().getActivePlayer().getActive() == ColorEnum.WHITE ) {
                return Ply.create( "e1", "c1" ).setPieceType( kingPieceType ).setSeparator( "-" ).setComment( "white queenside castling").setDescription( "O-O-O" );
            }
            return Ply.create( "e8", "c8" ).setPieceType( kingPieceType ).setSeparator( "-" ).setComment( "black queenside castling").setDescription( "O-O-O" );
        }
        else {
            if( getPosition().getActivePlayer().getActive() == ColorEnum.WHITE ) {
                return Ply.create( "e1", "g1" ).setPieceType( kingPieceType ).setSeparator( "-" ).setComment( "white kingside castling").setDescription( "O-O" );
            }
            return Ply.create( "e8", "g8" ).setPieceType( kingPieceType ).setSeparator( "-" ).setComment( "black kingside castling").setDescription( "O-O" );
        }
    }
   
    private boolean isOccupied( String location ) {
    
    	return getPosition().getFirst().getPlayer().getPiece(location) != null || getPosition().getSecond().getPlayer().getPiece(location) != null;
    }
    
    public Ply expand( String move ) {
        
    	if( isCastling( move ) ) return getCastling( move );
    	
    	MoveMatch matcher = MoveMatch.create( getPosition(), getInputLocale() );
    	Ply resultPly = matcher.findByTarget( move ).setLocaleTag( getOutputLocale() );
    	
    	resultPly.setPieceType( getPosition().getPiece( resultPly.getOriginLocation() ).getType( getOutputLocale() ) );
    	if( isOccupied( resultPly.getTargetLocation() ) ) resultPly.setSeparator( Ply.getCaptureSeparator() );
    	
    	return resultPly;
    }
    
    public Ply expand2LAN( String move ) {
        
        return expand( move );
    }
    
    public String getOriginLocation( String move ) {
      
        int cursor = 0;
        while( cursor < move.length() && Character.isWhitespace( move.charAt( cursor ) ) ) cursor++;
        while( cursor < move.length() && Character.isUpperCase( move.charAt( cursor ) ) ) cursor++;
        
        return getLocationFromAlgebraicNotation( move.substring( cursor ) );
    }
    
    public String getTargetLocation( String move ) {
      
        char letter = ' ';
        int cursor = 0;
        while( cursor < move.length() ) {
            cursor++;
            letter = move.charAt( cursor );
            if( isSeparator( letter ) ) {
                cursor++;
                break;
            }
        }
        
        return getLocationFromAlgebraicNotation( move.substring( cursor ) );
    }
    
    private String getLocationFromAlgebraicNotation( String partOfAlgebraicNotation ) {
        
        int cursor = 0;
        int start = cursor;
        while( cursor < partOfAlgebraicNotation.length() ) {
            char letter = partOfAlgebraicNotation.charAt( cursor );
            if( ! Character.isLetterOrDigit( letter )) break;
            if( letter == Ply.getCaptureSeparator().charAt( 0 ) ) break;
            cursor++;
        }
        
        String location = ( cursor == partOfAlgebraicNotation.length() ) ? partOfAlgebraicNotation.substring( start ) : partOfAlgebraicNotation.substring( start, cursor );
        if( Ply.validateLocation( location ) ) return location;
        
        Chess383NotationException.throwNotationException( String.format( "location %s is not valid", location ) );
        return "";
    }
}
