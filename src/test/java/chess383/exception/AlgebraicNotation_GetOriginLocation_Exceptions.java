/*
 *  AlgebraicNotation_GetOriginLocation_Exceptions.java
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

package chess383.exception;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.notation.AlgebraicNotation;
import chess383.position.Position;

/**
 * <p>
 * The class AlgebraicNotation_GetOriginLocation_Exceptions implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   September 2020
 *
 */
@DisplayName("the public method String getOriginLocation( ) for class Transition is tested if an exception occurs")
public class AlgebraicNotation_GetOriginLocation_Exceptions {  
  
    @Test
    @DisplayName("calling method getOriginLocation() for invalid input xa4-a4")
    public void AlgebraicNotation_GetOriginLocation_PrefixCapturingSymbol() {
        
        assertThatThrownBy( () ->  AlgebraicNotation.create( Position.create() ).getOriginLocation( "xa4-a4" ) )
                  .as( "an exception is thrown if the location is not part of all locations" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContaining( String.format( "location" ) )
                  .hasMessageContaining( String.format( "is not valid" ) );
    }
    
    @Test
    @DisplayName("calling method getOriginLocation() for invalid input ax4-a4")
    public void AlgebraicNotation_GetOriginLocation_InfixCapturingSymbol() {
        
        assertThatThrownBy( () ->  AlgebraicNotation.create( Position.create() ).getOriginLocation( "ax4-a4" ) )
                  .as( "an exception is thrown if the location is not part of all locations" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContaining( String.format( "location" ) )
                  .hasMessageContaining( String.format( "is not valid" ) );
    }
    
    @Test
    @DisplayName("calling method getOriginLocation() for invalid input NA4-b2")
    public void AlgebraicNotation_GetOriginLocation_OriginLocationInUpperCase() {
        
        assertThatThrownBy( () ->  AlgebraicNotation.create( Position.create() ).getOriginLocation( "NA4-b2" ) )
                  .as( "an exception is thrown if the location is not part of all locations" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContaining( String.format( "location" ) )
                  .hasMessageContaining( String.format( "is not valid" ) );
    }
    
}
 

