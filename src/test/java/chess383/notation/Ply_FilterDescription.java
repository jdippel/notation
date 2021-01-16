/*
 *  Ply_FilterDescription.java
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <p>
 * The class Ply_FilterDescription implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method String filterDescription( ) for class Ply is tested")
public class Ply_FilterDescription {
    
    @Test
    @DisplayName("filtering white kingside castling")
    public void filterDescription_ForWhiteKingsideCastling() {
        
        Ply ply = Ply.create( "e1", "g1" ).setPieceType( "K" ).setDescription( "O-O" );
        
        assertThat( "O-O" )
                   .as( "for castling the notation with location should not be applied" )
                   .isEqualTo( ply.filterDescription() );
    }
    
    @Test
    @DisplayName("filtering black queenside castling")
    public void filterDescription_ForBlackQueensideCastling() {
        
        Ply ply = Ply.create( "e8", "c1" ).setPieceType( "K" ).setDescription( "0-0-0" );
        
        assertThat( "O-O-O" )
                   .as( "for castling the notation with location should not be applied" )
                   .isEqualTo( ply.filterDescription() );
    } 
}
