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
 * The class MoveMatch_FindByTarget implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method Ply findByTarget( ) for class MoveMatch is tested")
public class MoveMatch_FindByTarget {
    
    static { ICoordinateFactory.STANDARD.get(); }
    
    @ParameterizedTest( name = "given a notation \"{1}\" (long algebraic notation) the move information (\"{2}\", \"{3}\") should be returned for position {0}." )
    @MethodSource("MappingProvider")
    public void testWithArgMethodSource_ReturnLocation( String position, String moveDescription, String origin, String target ) {
        
        ICoordinateFactory.STANDARD.get();
        MoveMatch notation = MoveMatch.create( Position.create( position ) );

        Ply examinedCall = notation.findByTarget( moveDescription );
        assertThat( origin )
                .as( String.format( "Given an algeraic notation %s the origin location %s  should be returned", moveDescription, origin ) )
                .isEqualTo( examinedCall.getOriginLocation() );
        assertThat( target )
                .as( String.format( "Given an algeraic notation %s the target location %s should be returned", moveDescription, target ) )
                .isEqualTo( examinedCall.getTargetLocation() );
    }
    
    
    public static Stream<Arguments> MappingProvider() {
        return Stream.of(
            
            Arguments.of( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "Nf3", "g1", "f3" )
            
            // 1.e4 d5 2.exd5 Qxd5 3.Nc3 Qd6 4.d4 Nf6 5.Bc4 Nc6 6.Nge2
          , Arguments.of( "r1b1kb1r/ppp1pppp/2nq1n2/8/2BP4/2N5/PPP2PPP/R1BQK1NR w KQkq - 3 11", "Nge2", "g1", "e2" )
          
            // Spassky - Fischer, WM 1972 (21)
            // 1.e4 c5 2.Nf3 e6 3.d4 cd4 4.Nd4 a6 5.Nc3 Nc6 6.Be3 Nf6 7.Bd3 d5 8.ed5 ed5 9.O-O Bd6 10.Nc6 bc6
            // 11.Bd4 O-O 12.Qf3 Be6 13.Rfe1 c5 14.Bf6 Qf6 15.Qf6 gf6 16.Rad1 Rfd8 17.Be2 Rab8 18.b3 c4 19.Nd5 Bd5 20.Rd5 Bh2+
            // 21.Kh2 Rd5 22.Bc4 Rd2 23.Ba6 Rc2 24.Re2 Re2 25.Be2 Rd8 26.a4 Rd2 27.Bc4 Ra2 28.Kg3 Kf8 29.Kf3 Ke7 30.g4 f5
          , Arguments.of( "8/4kp1p/5p2/8/P1B3P1/1P3K2/r4P2/8 b - g3 0 60", "f5", "f6", "f5" )
          
            // Spassky - Fischer, WM 1972 (20)
            // 1.e4 c5 2.Nf3 Nc6 3.d4 cd4 4.Nd4 Nf6 5.Nc3 d6 6.Bg5 e6 7.Qd2 a6 8.O-O-O Bd7 9.f4 Be7 10.Be2 O-O
            // 11.Bf3 h6 12.Bh4 Ne4 13.Be7 Nd2 14.Bd8 Nf3 15.Nf3 Rfd8 16.Rd6 Kf8 17.Rhd1 Ke7 18.Na4 Be8 19.Rd8
          , Arguments.of( "r2rb3/1p2kpp1/p1nRp2p/8/N4P1P/5N2/PPP3P1/2KR4 w - - 5 37", "Rd8", "d6", "d8" )
            
        ); }

}
