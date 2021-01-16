/*
 *  Ply_HashCode.java
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
 * The class Ply_HashCode implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method int hashCode( ) for class Ply is tested")
public class Ply_HashCode {
    
    @Test
    @DisplayName("comparing two plies with equals")
    public void equals_WithSameLocations() {
  
        assertThat( Ply.create( "e2", "e4" ).hashCode() )
                   .as( "ply with same locations are equal" )
                   .isEqualTo( Ply.create( "e2", "e4" ).hashCode() );
    }
    
    @Test
    @DisplayName("comparing two different plies with equals")
    public void equals_WithDifferentLocations() {
  
        assertThat( Ply.create( "e2", "e3" ).hashCode() )
                   .as( "ply with different locations are not equal" )
                   .isNotEqualTo( Ply.create( "e2", "e4" ).hashCode() );
    }
}
