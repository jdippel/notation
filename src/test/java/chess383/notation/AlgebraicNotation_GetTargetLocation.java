/*
 *  AlgebraicNotation_GetTargetLocation.java
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
import chess383.position.Position;

/**
 * <p>
 * The class AlgebraicNotation_GetTargetLocation implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   September 2020
 *
 */
@DisplayName("the public method String getTargetLocation( ) for class AlgebraicNotation is tested")
public class AlgebraicNotation_GetTargetLocation {
    
    static { ICoordinateFactory.STANDARD.get(); }
    
    @ParameterizedTest( name = "given a notation in LAN \"{0}\" (long algebraic notation) the target location of the move should be returned." )
    @MethodSource("LANAndLocationProvider")
    public void testWithArgMethodSource_ReturnLocation( String lan, String location ) {
        
        ICoordinateFactory.STANDARD.get();
        AlgebraicNotation notation = AlgebraicNotation.create( Position.create() );

        assertThat( location )
                .as( String.format( "Given an algeraic notation %s the target location %s should be returned", lan, location ) )
                .isEqualTo( notation.getTargetLocation( lan ) );
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
            
        ); }

}
