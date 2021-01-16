/*
 *  AlgebraicNotation_Create_Exceptions.java
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

import java.util.Locale;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess383.notation.AlgebraicNotation;
import chess383.position.Position;

/**
 * <p>
 * The class AlgebraicNotation_Create_Exceptions implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   September 2020
 *
 */
@DisplayName("the public method Position change( ) for class Transition is tested if an exception occurs")
public class AlgebraicNotation_Create_Exceptions {  
  
    @Test
    @DisplayName("creating an AlgebraicNotation with a Null-Pointer to a position (one argument)")
    public void AlgebraicNotation_Create_OneArgument() {
        
        assertThatThrownBy( () ->  AlgebraicNotation.create( ( Position) null ) )
                  .as( "if position is not defined an exception is thrown" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContainingAll( String.format( "Invalid position" ) );
    }
    
    @Test
    @DisplayName("creating an AlgebraicNotation with a Null-Pointer to a position (two arguments)")
    public void AlgebraicNotation_Create_TwoArguments() {
        
        assertThatThrownBy( () ->  AlgebraicNotation.create( ( Position) null, Locale.ENGLISH ) )
                  .as( "if position is not defined an exception is thrown" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContainingAll( String.format( "Invalid position" ) );
    }
}
 

