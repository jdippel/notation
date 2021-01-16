/*
 *  Ply_SetPieceType.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2020 Jörg Dippel
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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.piece.concretion.knight.Knight;

/**
 * <p>
 * The class Ply_SetPieceType implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method Ply setPieceType( ) for class Ply is tested")
public class Ply_SetPieceType {

    final static private String PIECE_TYPE_VALUE_AFTER_CONSTRUCTOR_CALL = " ";
    
    private Ply createKnight() {
        Ply ply = Ply.create( "g1", "f3" );
        ply.setSeparator( "-" );
        return ply;
    }
    
    @Test
    @DisplayName("generating a valid piece type for an existing ply and for an initial knight move")
    public void setPieceType_ValidInputUsingDefaultLocale() {
        
        String PIECE_TYPE = Knight.create( "f3" ).getType();
        
        Ply ply = createKnight();
        ply.setPieceType( PIECE_TYPE );
        
        assertThat( ply.setPieceType( PIECE_TYPE ).getPieceType() )
                   .as( "generated ply description as a string should match" )
                   .isEqualTo( "S" );
    }
    
    @Test
    @DisplayName("generating a valid piece type for an existing ply and for an initial knight move (English notation)")
    public void setPieceType_ValidInputButEnglishNotation() {

        String PIECE_TYPE = Knight.create( "f3" ).getType( Locale.ENGLISH );
        
        Ply ply = createKnight();
        ply.setPieceType( PIECE_TYPE );
        
        assertThat( ply.setPieceType( PIECE_TYPE ).getPieceType() )
                   .as( "generated ply description as a string should match" )
                   .isEqualTo( "N" );
    }
    
    @Test
    @DisplayName("generating an invalid piece type (null) for an existing ply and for an initial knight move")
    public void setPieceType_NullInput() {

        String PIECE_TYPE = null;
        
        Ply ply = createKnight();
        ply.setPieceType( PIECE_TYPE );
        
        assertThat( ply.setPieceType( PIECE_TYPE ).getPieceType() )
                   .as( "generated ply description as a string should match" )
                   .isEqualTo( PIECE_TYPE_VALUE_AFTER_CONSTRUCTOR_CALL );     // default after constructor call for class PLy 
    }
    
    @Test
    @DisplayName("generating an invalid piece type (empty) for an existing ply and for an initial knight move")
    public void setPieceType_EmptyInput() {

        String PIECE_TYPE = "";
        
        Ply ply = createKnight();
        ply.setPieceType( PIECE_TYPE );
        
        assertThat( ply.setPieceType( PIECE_TYPE ).getPieceType() )
                   .as( "generated ply description as a string should match" )
                   .isEqualTo( PIECE_TYPE_VALUE_AFTER_CONSTRUCTOR_CALL );     // default after constructor call for class PLy 
    }
    
    @Test
    @DisplayName("generating a piece type (word) for an existing ply and for an initial knight move")
    public void setPieceType_WordInput() {

        String PIECE_TYPE = "pawn";
        
        Ply ply = createKnight();
        ply.setPieceType( PIECE_TYPE );
        
        assertThat( ply.setPieceType( PIECE_TYPE ).getPieceType() )
                   .as( "generated ply description as a string should match" )
                   .isEqualTo( "Pawn" );      // first letter will be upper case   
    }
}
