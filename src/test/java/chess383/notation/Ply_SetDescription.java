/*
 *  Ply_SetDescription.java
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

import chess383.ColorEnum;
import chess383.piece.concretion.pawn.Pawn;

/**
 * <p>
 * The class Ply_SetDescription implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method Ply setDescription( ) for class Ply is tested")
public class Ply_SetDescription {

    @Test
    @DisplayName("generationg a ply description for an initial black pawn move")
    public void readLetterFromNull() {
        
        Ply ply = Ply.create( "e7", "e5" );
        ply.setPieceType( Pawn.create( ColorEnum.BLACK, "e5" ).getType() );
        ply.setSeparator( "-" );
        ply.setDescription();
        
        assertThat( ply.getDescription() )
                   .as( "generated ply description as a string should match" )
                   .isEqualTo( " e7-e5" );
    }
}
