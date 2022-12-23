/*
 *  Next_NextWithStringParameter_Bugs.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2022 Jörg Dippel
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

import chess383.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>
 * The class Next_NextWithStringParameter_Bug implements an upper tester for bugs found
 * </p>
 *
 * @author    Jörg Dippel
 * @version   November 2022
 *
 */
@DisplayName("the public method Position next( ) with single String parameter for class Next is tested for bugs found")
public class Next_NextWithStringParameter_Bugs {
    
    @Test
    @DisplayName("A white pawn promotion to a queen due to next() is checked.")
    public void next_PromotionToWhiteQueen() {
        
        Position position;
        
        position = Position.create( "3r4/2P3p1/1K6/1P6/7p/8/2k3P1/8 w - - 0 123" );
        position =  Next.create( position ).next( "cxd8Q" );

        
        assertThat( position.toString()  )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( "3Q4/6p1/1K6/1P6/7p/8/2k3P1/8 b - - 0 124" );
    }
}


