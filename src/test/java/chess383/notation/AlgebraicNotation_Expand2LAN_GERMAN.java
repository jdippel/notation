/*
 *  AlgebraicNotation_Expand2LAN_GERMAN.java
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

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.ICoordinateFactory;

/**
 * <p>
 * The class AlgebraicNotation_Expand2LAN_GERMAN implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   July 2020
 *
 */
@DisplayName("the public method String expand2LAN( ) for class AlgebraicNotation is tested for given positions")
public class AlgebraicNotation_Expand2LAN_GERMAN {
    
    @ParameterizedTest( name = "given a position \"{0}\" and an algebraic notation \"{1}\" then the long notation \"{2}\" should be returned." )
    @MethodSource("positionAndSANandLANProviderForGermanLocale")
    public void testWithArgMethodSource_ReturnLongAlgebraicNotation( String position, String san, String lan ) {
        
        ICoordinateFactory.STANDARD.get();
        AlgebraicNotation notation = AlgebraicNotation.create( position, Locale.GERMAN );
        
        Ply expectedPly = Ply.create( lan.substring( 1, 3 ), lan.substring( 4, 6 ) );
        Ply returnedPly = notation.expand2LAN( san );
        assertThat( expectedPly )
                .as( String.format( "Given a position, for an algebraic notation %s both locations should be determined correctly should be returned", lan ) )
                .isEqualTo( returnedPly );
        assertThat( lan )
                .as( String.format( "Given a position, for an algeraic notation %s the long algebraic notation %s should be returned", san, lan ) )
                .isEqualTo( returnedPly.setDescription().getDescription() );
    }
    
    
    public static Stream<Arguments> positionAndSANandLANProviderForGermanLocale() {
        return Stream.of(
            
            Arguments.of( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "Nf3", "Sg1-f3" )
            
        ); }

}
