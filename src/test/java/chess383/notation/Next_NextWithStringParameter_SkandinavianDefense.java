/*
 *  Next_NextWithStringParameter_SkandinavianDefense.java
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

import chess383.position.Position;

/**
 * <p>
 * The class Next_NextWithStringParameter_SkandinavianDefense implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method Position next( ) with single String parameter for class Next is tested for the Skandinavian Defense opening (B01)")
public class Next_NextWithStringParameter_SkandinavianDefense {  
    
    @Test
    @DisplayName("The Center Game is checked due to next().")
    public void change_CenterGame() {
        
        Position position;
        
        position = Position.create();
        position =  Next.create( position ).next( "e4" );
        position =  Next.create( position ).next( "d5" );
        position =  Next.create( position ).next( "ed5" );
        position =  Next.create( position ).next( "Nf6" );
        position =  Next.create( position ).next( "d4" );
        position =  Next.create( position ).next( "Nxd5" );
        position =  Next.create( position ).next( "c4" );
        position =  Next.create( position ).next( "Nb6" );
        position =  Next.create( position ).next( "Nf3" );
        position =  Next.create( position ).next( "g6" );
        position =  Next.create( position ).next( "h3" );
        position =  Next.create( position ).next( "Bg7" );
        position =  Next.create( position ).next( "Nc3" );
        position =  Next.create( position ).next( "O-O" );
        position =  Next.create( position ).next( "Be3" );
        position =  Next.create( position ).next( "Nc6" );
        position =  Next.create( position ).next( "Qd2" );
        position =  Next.create( position ).next( "e5" );
        position =  Next.create( position ).next( "d5" );
        
        assertThat( "r1bq1rk1/ppp2pbp/1nn3p1/3Pp3/2P5/2N1BN1P/PP1Q1PP1/R3KB1R b KQ - 0 20" )
                  .as( "the string representation of the new position should match" )
                  .isEqualTo( position.toString() );
    }
}


