/*
 *  Ply_ReadLetter.java
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.exception.Chess383NotationException;

/**
 * <p>
 * The class Ply_ReadLetter implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public static method Character readLetter( ) for class Ply is tested")
public class Ply_ReadLetter {

    @ParameterizedTest( name = "given an input string \"{1}\" then the first letter \"{0}\" is returned." )
    @MethodSource("characterAndStringProvider")
    public void testWithArgMethodSource_ReturnFirstLetter( Character letter, String input ) {
        
        assertThat( letter )
                .as( "the first letter of the string should be returned" )
                .isEqualTo( Ply.readLetter( input ) );
    }
    
    
    public static Stream<Arguments> characterAndStringProvider() {
        return Stream.of(
            
            Arguments.of( 'f', "f3-b4" ),
            Arguments.of( 'S', "Sf3-b4" ),
            Arguments.of( ' ', " d2-d4" )
            
        ); }

    
    @Test
    @DisplayName("reading from null should throw an exception")
    public void readLetterFromNull() {
        
        String INVALID_STRING = null;
        
        assertThatThrownBy( () ->  Ply.readLetter( INVALID_STRING ) )
                  .as( "if the passed string is null an exception is thrown" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContainingAll( "Ply.readLetter" )
                  .hasMessageContainingAll( "Cannot read character" );
    }
    
    @Test
    @DisplayName("reading from empty string should throw an exception")
    public void readLetterFromEmptyString() {
        
        String INVALID_STRING = "";
        
        assertThatThrownBy( () ->  Ply.readLetter( INVALID_STRING ) )
                  .as( "if the passed string is null an exception is thrown" )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContainingAll( "Ply.readLetter" )
                  .hasMessageContainingAll( "Cannot read character" );
    }
}
