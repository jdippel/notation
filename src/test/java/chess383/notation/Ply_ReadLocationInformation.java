/*
 *  Ply_ReadLocationInformation.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2020, 2021 Jörg Dippel
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

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <p>
 * The class Ply_ReadLocationInformation implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   January 2021
 *
 */
@DisplayName("the public static method String Ply_ReadLocationInformation( ) for class Ply is tested")
public class Ply_ReadLocationInformation {

    @ParameterizedTest( name = "given a string \"{0}\" then the prefix of that string is interpreted as a location \"{1}\"." )
    @MethodSource("StringAndLocationProvider")
    public void testWithArgMethodSource_ReturnLocation( String input, String readResult ) {
        
        assertThat( readResult )
                .as( "a prefix from a string should be interpreted as an location" )
                .isEqualTo( Ply.readLocationInformation( input ) );
    }
    
    
    public static Stream<Arguments> StringAndLocationProvider() {
        return Stream.of(
            
            Arguments.of( "f3", "f3" ),
            Arguments.of( "Nf3", "" ),
            Arguments.of( "f3-h4", "f3" ),
            
            Arguments.of( "u3", "u3" ),   // not a valid location but the pattern to match is correct
            Arguments.of( "aA9", "" ),
            Arguments.of( "", "" ),
            Arguments.of( null, "" )
            
        ); }

}
