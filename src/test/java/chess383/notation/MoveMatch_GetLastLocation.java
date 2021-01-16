/*
 *  MoveMatch_GetLastLocation.java
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

/**
 * <p>
 * The class MoveMatch_GetLastLocation implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public static method String getLastLocation( ) for class MoveMatch is tested")
public class MoveMatch_GetLastLocation {

    @ParameterizedTest( name = "given a notation \"{0}\" the target location of the move (\"{1}\") should be returned." )
    @MethodSource("LANAndLocationProvider")
    public void testWithArgMethodSource_ReturnLocation( String moveDescription, String location ) {

        assertThat( location )
                .as( String.format( "Given an algeraic notation %s the target location %s should be returned", moveDescription, location ) )
                .isEqualTo( MoveMatch.getLastLocation( moveDescription ) );
    }
    
    
    public static Stream<Arguments> LANAndLocationProvider() {
        return Stream.of(
            
            Arguments.of( "Ng1-f3", "f3" )
          , Arguments.of( "Rc4-h4", "h4" )
          , Arguments.of( "Qf6xg7", "g7" )
          , Arguments.of( "Nf3-g5", "g5" )
          , Arguments.of( " g5-g6", "g6" )
          , Arguments.of( "Qg3-h3", "h3" )
          , Arguments.of( "Rd1-d7", "d7" )
          , Arguments.of( " f4-f5", "f5" )
          
          , Arguments.of( "Nd4xc6", "c6" )
          , Arguments.of( "Nd4xc6+", "c6" )
          , Arguments.of( "Nd4xc6!?", "c6" )
          , Arguments.of( " f2-f1Q", "f1" )
          
          , Arguments.of( " f2-1Q", "" )
            
        ); }

}
