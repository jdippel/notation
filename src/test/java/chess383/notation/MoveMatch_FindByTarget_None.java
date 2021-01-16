/*
 *  MoveMatch_FindByTarget.java
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.ICoordinateFactory;
import chess383.exception.Chess383NotationException;
import chess383.position.Position;

/**
 * <p>
 * The class MoveMatch_FindByTarget implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method Ply findByTarget( ) for class MoveMatch is tested")
public class MoveMatch_FindByTarget_None {
    
    static { ICoordinateFactory.STANDARD.get(); }
    
    @ParameterizedTest( name = "given a notation \"{1}\" (long algebraic notation) there is no possibility to move to location {2} for position {0} - thus an exception is thrown." )
    @MethodSource("MappingProvider")
    public void testWithArgMethodSource_ReturnLocation( String position, String moveDescription, String target ) {
        
        ICoordinateFactory.STANDARD.get();
        MoveMatch notation = MoveMatch.create( Position.create( position ) );
        
        assertThatThrownBy( () ->  notation.findByTarget( moveDescription ) )
                .as( String.format( "an exception is thrown if there is no origin location for the move to location %s", target ) )
                .isInstanceOf( Chess383NotationException.class )
                .hasMessageContaining( String.format( "There is no" ) )
                .hasMessageContaining( String.format( "to move from anywhere to location" ) );
    }
    
    
    public static Stream<Arguments> MappingProvider() {
        return Stream.of(
            
            Arguments.of( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "Ne5", "e5" )
            
        ); }

}
