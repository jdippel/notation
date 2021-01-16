/*
 *  Ply_ValidateLocation.java
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

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.ICoordinateFactory;

/**
 * <p>
 * The class Ply_ValidateLocation implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public static method boolean validateLocation( ) for class Ply is tested")
public class Ply_ValidateLocation {

    @ParameterizedTest( name = "given a location \"{0}\" then the predicate for a location can be determined to the value \"{1}\"." )
    @MethodSource("locationAndPredicateProvider")
    public void testWithArgMethodSource_ReturnPredicate( String location, boolean predicate ) {
        
        ICoordinateFactory.STANDARD.get();
        
        assertThat( predicate )
                .as( "a location should be validated" )
                .isEqualTo( Ply.validateLocation( location ) );
    }
    
    
    public static Stream<Arguments> locationAndPredicateProvider() {
        return Stream.of(
            
            Arguments.of( "f3", true ),
            Arguments.of( "aa", false ),
            Arguments.of( "u2", false )
            
        ); }

}
