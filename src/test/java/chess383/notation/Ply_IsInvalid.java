/*
 *  Ply_IsInvalid.java
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
 * The class Ply_IsInvalid implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method boolean isInvalid( ) for class Ply is tested")
public class Ply_IsInvalid {
    
    @Test
    @DisplayName("generating a valid ply and validating it ")
    public void isValid_ForAValidPly() {
        
        Ply ply = Ply.create( "e2", "e4" );
        
        assertThat( false )
                   .as( "generated ply is validated correctly" )
                   .isEqualTo( ply.isInvalid() );
    }
    
    @Test
    @DisplayName("generating an invalid ply and validating it ")
    public void isValid_ForAnInvalidPly() {
        
        Ply ply = Ply.getInvalidPly();
        
        assertThat( true )
                   .as( "generated ply is validated correctly" )
                   .isEqualTo( ply.isInvalid() );
    }
}
