/*
 *  Ply_Create.java
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

import chess383.ICoordinateFactory;
import chess383.exception.Chess383NotationException;

/**
 * <p>
 * The class Ply_Create implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public static method boolean validateLocation( ) for class Ply is tested")
public class Ply_Create {

    @ParameterizedTest( name = "given two vaild locations \"{0}\" and \"{1}\" then a valid Ply instance should be created." )
    @MethodSource("locationAndLocationProvider")
    public void testWithArgMethodSource_ReturnPlyInstance( String origin, String target ) {
        
        ICoordinateFactory.STANDARD.get();
        
        assertThat( origin )
                .as( "the origin location ist attribute of the just created Ply instance" )
                .isEqualTo( Ply.create( origin, target ).getOriginLocation( ) );
        assertThat( target )
                .as( "the target location ist attribute of the just created Ply instance" )
                .isEqualTo( Ply.create( origin, target ).getTargetLocation( ) );
    }
    
    
    public static Stream<Arguments> locationAndLocationProvider() {
        return Stream.of(
            
            Arguments.of( "f3", "b4" )
            
        ); }

    
    @Test
    @DisplayName("creating a Ply instance with an invalid origin location should throw an exception")
    public void createPlyWithInvalidOriginLocation() {
        
        String INVALID_LOCATION = "u9";
        String VALID_LOCATION = "f4";
        
        assertThatThrownBy( () ->  Ply.create( INVALID_LOCATION, VALID_LOCATION ) )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContainingAll( "validation of origin location" )
                  .hasMessageContainingAll( "fails" );
    }
    
    @Test
    @DisplayName("creating a Ply instance with an invalid target location should throw an exception")
    public void createPlyWithInvalidTargetLocation() {
        
        String INVALID_LOCATION = "";
        String VALID_LOCATION = "f4";
        
        assertThatThrownBy( () ->  Ply.create( VALID_LOCATION, INVALID_LOCATION ) )
                  .isInstanceOf( Chess383NotationException.class )
                  .hasMessageContainingAll( "validation of target location" )
                  .hasMessageContainingAll( "fails" );
    }
}
