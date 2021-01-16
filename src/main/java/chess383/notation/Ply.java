/*
 *  Ply.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2010-2020 Jörg Dippel
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

import chess383.ICoordinate;
import chess383.ICoordinateFactory;
import chess383.exception.Chess383NotationException;
import chess383.piece.abstraction.Piece;
import chess383.piece.abstraction.PieceFactory;

/**
 * Provides the description of a ply.
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
public class Ply {

    final public static Ply INVALID_MOVE = new Ply( "", "" ).setComment( "Invalid move" );
    
    static Ply getInvalidPly() { return INVALID_MOVE; }
    public static String getMoveSeparator() { return "-"; }
    public static String getCaptureSeparator() { return "x"; }
    
    /** ---------  Attributes  -------------------------------- */
    
    private String pieceType = " ";
    private String originLocation;
    private String targetLocation;
    private String locationSeparator = getMoveSeparator();
    private String promotion = "";
    private String enPassantLocation = "";
    private Locale localeTag = Locale.ENGLISH;
    private String description = "";
    private String comment = "";
    
    /** ---------  Constructors  ------------------------------ */

    private Ply( String origin, String target ) {

        setOriginLoction( origin );
        setTargetLoction( target );
    }

    /** ---------  Getter and Setter  ------------------------- */

    public String getPieceType( )                    { return this.pieceType; }
//  public Ply setPieceType( String value )          { this.pieceType = value; return this; }
    
    public String getOriginLocation( )               { return this.originLocation; }
    private void setOriginLoction( String value )    { this.originLocation = value; }
    
    public String getTargetLocation( )               { return this.targetLocation; }
    private void setTargetLoction( String value )    { this.targetLocation = value; }
    
    public String getSeparator( )                    { return this.locationSeparator; }
    public Ply setSeparator( String value )          { this.locationSeparator = value; return this;}
    
    public String getPromotion( )                    { return this.promotion; }
    public Ply setPromotion( String value )          { this.promotion = value; return this;}
    
    public String getEnPassantLocation( )            { return this.enPassantLocation; }
    public Ply setEnPassantLoction( String value )   { this.enPassantLocation = value; return this;}
    
    public Locale getLocaleTag()                     { return this.localeTag; }
    public Ply setLocaleTag( Locale value )          { this.localeTag = value; return this; }
    
    public String getDescription( )                  { return this.description; }
    public Ply setDescription( String value )        { this.description = value; return this; }
    
    public String getComment( )                      { return this.comment; }
    public Ply setComment( String value )            { this.comment = value; return this; }
   
    /** ---------  Factory  ----------------------------------- */
    
    private static ICoordinate board = ICoordinateFactory.STANDARD.get();
    
    public static boolean validateLocation( String location ) {
        
        return board.getAllLocations().contains( location );
    }
    
    public static Ply create( String origin, String target ) {
        
        if( validateLocation( origin ) ) {
            if( validateLocation( target ) ) {
                return new Ply( origin, target );
            }
            else {
                Chess383NotationException.throwNotationException( String.format( "Ply.create(): validation of target location %s fails", target ) );
            }
        }
        else {
            Chess383NotationException.throwNotationException( String.format( "Ply.create(): validation of origin location %s fails", origin ) );
        }
        return INVALID_MOVE;
    }
    
    /** ------------------------------------------------------- */

    public static Character readLetter( String input ) {
        
        if( input != null && input.length() > 0 ) return input.charAt( 0 );
        Chess383NotationException.throwNotationException( String.format( "Ply.readLetter(): Cannot read character" ) );
        return null;
    }
    
    public static String readLocationInformation( String description ) {
        
        if( description == null || description.length() == 0 ) return "";
        
        StringBuffer location = new StringBuffer();
        char letter;
        
        int cursor = 0;
        while( cursor < description.length() ) {
            letter = description.charAt( cursor );
            if( Character.isLetter( letter ) && Character.isLowerCase( letter )) { 
                location.append( letter );
            }
            else if( Character.isDigit( letter )) {
                location.append( letter );
            }
            else break;
            cursor++;
        }
      
        if( cursor > 1  ) return location.toString();
        return "";
    }
    
    public Ply setDescription() {
        
        return setDescription( getPieceType() + getOriginLocation() + getSeparator() + getTargetLocation() + getPromotion() );
    }
    
    public Ply setPieceType( String value ) {
        
        if( value != null && value.length() > 0 ) {
            if( value.length() == 1 ) {
                this.pieceType = "" + Character.toUpperCase( value.charAt( 0 ) );
            }
            else {
                this.pieceType = Character.toUpperCase( value.charAt( 0 ) ) + value.substring( 1 );
            }
        }
        return this;
    }
    
    public boolean isInvalid() {
        
        return this.equals( INVALID_MOVE );
    }
    
    public boolean isValid() {
        
        return ! isInvalid();
    }
    
    public Ply translate( Character fenAbbreviation, Locale language ) {
        
        final String ARBITRARY_LOCATION = "e5";
        
        if( getLocaleTag() == language || fenAbbreviation == null ) {
            return this;
        }
        else if( fenAbbreviation == 'O' ) {
            return this;
        }
        else if( Character.isLetter( fenAbbreviation ) ) {
            Piece piece = PieceFactory.createPiece( ARBITRARY_LOCATION, fenAbbreviation );
            String pieceType = piece.getType( language );
            if( pieceType.length() == 1 ) {
                return create( getOriginLocation(), getTargetLocation() )
                       .setSeparator( getSeparator() )
                       .setPieceType( pieceType )
                       .setEnPassantLoction( getEnPassantLocation() )
                       .setPromotion( getPromotion() )
                       .setDescription( getDescription() )
                       .setComment( getComment() )
                       .setLocaleTag( language );
            }
            else {
                return INVALID_MOVE;
            }
        }
        else {
            return this;     // pawn move assumed
        }
    }
    
    public Ply translate( Locale language ) {
        
        if( getPieceType().length() > 0 ) {
            Character fenAbbreviation = getPieceType().charAt( 0 );
            return translate( fenAbbreviation, language );
        }
        else {
            return this;       // no translation
        }
    }
    
    public String filterDescription() {
    
        if( getPieceType().length() == 1 && getPieceType().charAt( 0 ) == 'K' ) {
            if( getDescription().startsWith( "O-O-O" ) ) return "O-O-O";
            if( getDescription().startsWith( "0-0-0" ) ) return "O-O-O";
            if( getDescription().startsWith( "O-O" ) ) return "O-O";
            if( getDescription().startsWith( "0-0" ) ) return "O-O";
        }
        
        return setDescription().getDescription();
    }
    
    /** ------------------------------------------------------- */
    
    @Override
    public String toString() {
        return getOriginLocation() + getSeparator() + getTargetLocation();
    }
    
    @Override
    public boolean equals( Object object ) {
        if( object instanceof Ply ) {
            Ply ply = ( Ply )object;
            return( ply.getOriginLocation().compareTo( getOriginLocation()) == 0 && 
                    ply.getTargetLocation().compareTo( getTargetLocation()) == 0 );
        }
        else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        String locations = getOriginLocation() + getTargetLocation();
        return locations.hashCode();
    }
}
