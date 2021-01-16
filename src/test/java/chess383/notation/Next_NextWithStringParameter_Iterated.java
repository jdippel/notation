/*
 *  Next_NextWithStringParameter_Iterated.java
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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.ICoordinateFactory;
import chess383.position.Position;

/**
 * <p>
 * The class Next_NextWithStringParameter_Iterated implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   August 2020
 *
 */
@DisplayName("the public method Position next( ) for class Next is tested")
public class Next_NextWithStringParameter_Iterated {
    
    @ParameterizedTest( name = "starting with an initial position a list of moves in algebraic notation is consumed for opening {0}." )
    @MethodSource("descriptionAndpositionAndListOfMovesProvider")
    public void testWithArgMethodSource_CompareTerminalPosition( String opening, String fen, List<String> moves ) {
        
        ICoordinateFactory.STANDARD.get();
        Position position = Position.create();
        
        String move;
        Iterator<String> iterator = moves.iterator();
        while( iterator.hasNext() ) {
            move = iterator.next();
            position =  Next.create( position ).next( move );
        }
        
        assertThat( fen ).as( String.format( "After a given list of moves the position should match" ) ).isEqualTo( position.toString() );
    }
    
    
    public static Stream<Arguments> descriptionAndpositionAndListOfMovesProvider() {
        return Stream.of(
            
            Arguments.of( "Jaeggi - Schmid, 2007", "1r6/1P1k4/1K6/8/6p1/1R4P1/8/8 b - - 8 132", Arrays.asList( 
                    "e4", "e6", "d4", "d5", "Nd2", "Nf6", "e5", "Nfd7", "Bd3", "c5", "c3", "Nc6", "Ne2", "Qb6", "Nf3", "cxd4", "cxd4", "f6", "exf6", "Nxf6", 
                    "O-O", "Bd6", "Nc3", "Bd7", "Re1", "O-O", "a3", "Rae8", "Be3", "Qd8", "Bg5", "Qb8", "Nb5", "Bf4", "Bxf4", "Qxf4", "g3", "Qh6", "Ne5", "Nxe5", 
                    "Rxe5", "Bxb5", "Bxb5", "Re7", "Qe2", "Ne4", "f4", "Rf5", "Rc1", "g5", "Bd3", "gxf4", "Bxe4", "Rxe5", "dxe5", "dxe4", "Qxe4", "fxg3", "Rc8+", "Kg7", 
                    "hxg3", "Qg6", "Qe3", "Rf7", "Kg2", "Qf5", "Qe2", "Qg5", "Rc4", "h5", "Rd4", "b6", "b4", "Rf5", "Rd7+", "Rf7", "Rd6", "Qf5", "Rd4", "Qg5", 
                    "b5", "Rf5", "Rd7+", "Kh6", "Qe4", "Qg4", "Qxg4", "hxg4", "Rxa7", "Rxe5", "a4", "Re2+", "Kf1", "Ra2", "Ra6", "Kg5", "Rxb6", "Ra1+", "Ke2", "Ra2+", 
                    "Ke1", "Ra1+", "Kd2", "Ra2+", "Kc3", "Rxa4", "Kb3", "Re4", "Rc6", "Re1", "Rc3", "e5", "Kc4", "e4", "Rb3", "e3", "b6", "e2", "Kd3", "Rh1", 
                    "Kxe2", "Rh8", "b7", "Rb8", "Ke3", "Kf5", "Kd4", "Ke6", "Kc5", "Kd7", "Kb6" ) )

          , Arguments.of( "Scandinavian Defense", "r1bq1rk1/ppp2pbp/1nn3p1/3Pp3/2P5/2N1BN1P/PP1Q1PP1/R3KB1R b KQ - 0 20", Arrays.asList( 
                    "e4", "d5", "ed5", "Nf6", "d4", "Nxd5", "c4", "Nb6", "Nf3", "g6", "h3", "Bg7", "Nc3", "O-O", "Be3", "Nc6", "Qd2", "e5", "d5" ) )
            
          , Arguments.of( "Spassky - Fischer, WM 1972 (21)", "8/3B4/5p2/5P1p/P4k2/1P6/r4PK1/8 b - - 1 82", Arrays.asList( 
                    "e4", "c5", "Nf3", "e6", "d4", "cd4", "Nd4", "a6", "Nc3", "Nc6", "Be3", "Nf6", "Bd3", "d5", "ed5", "ed5", "O-O", "Bd6", "Nc6", "bc6",
                    "Bd4", "O-O", "Qf3", "Be6", "Rfe1", "c5", "Bf6", "Qf6", "Qf6", "gf6", "Rad1", "Rfd8", "Be2", "Rab8", "b3", "c4", "Nd5", "Bd5", "Rd5", "Bh2+",
                    "Kh2", "Rd5", "Bc4", "Rd2", "Ba6", "Rc2", "Re2", "Re2", "Be2", "Rd8", "a4", "Rd2", "Bc4", "Ra2", "Kg3", "Kf8", "Kf3", "Ke7", "g4", "f5",
                    "gf5", "f6", "Bg8", "h6", "Kg3", "Kd6", "Kf3", "Ra1", "Kg2", "Ke5", "Be6", "Kf4", "Bd7", "Rb1", "Be6", "Rb2", "Bc4", "Ra2", "Be6", "h5",
                    "Bd7" ) )
          
          , Arguments.of( "Spassky - Fischer, WM 1972 (20)", "8/8/3k2b1/1p2p2p/p2n2p1/P1K1N1P1/1PP4P/4N3 w - - 30 109", Arrays.asList( 
                  "e4", "c5", "Nf3", "Nc6", "d4", "cd4", "Nd4", "Nf6", "Nc3", "d6", "Bg5", "e6", "Qd2", "a6", "O-O-O", "Bd7", "f4", "Be7", "Be2", "O-O",
                  "Bf3", "h6", "Bh4", "Ne4", "Be7", "Nd2", "Bd8", "Nf3", "Nf3", "Rfd8", "Rd6", "Kf8", "Rhd1", "Ke7", "Na4", "Be8", "Rd8", "Rd8", "Nc5", "Rb8",
                  "Rd3", "a5", "Rb3", "b5", "a3", "a4", "Rc3", "Rd8", "Nd3", "f6", "Rc5", "Rb8", "Rc3", "g5", "g3", "Kd6", "Nc5", "g4", "Ne4+", "Ke7",
                  "Ne1", "Rd8", "Nd3", "Rd4", "Nef2", "h5", "Rc5", "Rd5", "Rc3", "Nd4", "Rc7+", "Rd7", "Rd7+", "Bd7", "Ne1", "e5", "fe5", "fe5", "Kd2", "Bf5",
                  "Nd1", "Kd6", "Ne3", "Be6", "Kd3", "Bf7", "Kc3", "Kc6", "Kd3", "Kc5", "Ke4", "Kd6", "Kd3", "Bg6+", "Kc3", "Kc5", "Nd3+", "Kd6", "Ne1", "Kc6",
                  "Kd2", "Kc5", "Nd3+", "Kd6", "Ne1", "Ne6", "Kc3", "Nd4" ) )
          
          , Arguments.of( "Spassky - Fischer, WM 1972 (19)", "8/6p1/p4k1p/R7/8/7P/P1r2KP1/8 w - - 6 81", Arrays.asList( 
                  "e4", "Nf6", "e5", "Nd5", "d4", "d6", "Nf3", "Bg4", "Be2", "e6", "O-O", "Be7", "h3", "Bh5", "c4", "Nb6", "Nc3", "O-O", "Be3", "d5",
                  "c5", "Bf3", "Bf3", "Nc4", "b3", "Ne3", "fe3", "b6", "e4", "c6", "b4", "bc5", "bc5", "Qa5", "Nd5", "Bg5", "Bh5", "cd5", "Bf7+", "Rf7",
                  "Rf7", "Qd2", "Qd2", "Bd2", "Raf1", "Nc6", "ed5", "ed5", "Rd7", "Be3+", "Kh1", "Bd4", "e6", "Be5", "Rd5", "Re8", "Re1", "Re6", "Rd6", "Kf7",
                  "Rc6", "Rc6", "Re5", "Kf6", "Rd5", "Ke6", "Rh5", "h6", "Kh2", "Ra6", "c6", "Rc6", "Ra5", "a6", "Kg3", "Kf6", "Kf3", "Rc3+", "Kf2", "Rc2"  ) )
            
            // 8. Se2 is only determined because one of the knights is pinned - the valid move is Sg1-e2
          , Arguments.of( "Sbierski - Hurst, 2007", "8/2Pk3p/1p1PN1p1/pP3K2/8/8/1P2r2P/8 w - - 0 89", Arrays.asList(
                   "e4", "d5", "exd5", "Qxd5", "Nc3", "Qa5", "d4", "e5", "Bc4", "Nc6", "d5", "Nd4", "Be3", "Bc5", "Ne2", "Qb4", "Bb3", "Nxb3", "axb3", "Bxe3",
                   "fxe3", "Nf6", "Ra4", "Qe7", "Qa1", "Qc5", "e4", "Ng4", "Rc4", "Qf2+", "Kd2", "Qe3+", "Ke1", "O-O", "Rxc7", "Qb6", "Re7", "Ne3", "Kd2", "Nxg2",
                   "Qg1", "Qd6", "Rxe5", "Qxe5", "Qxg2", "f5", "Rf1", "fxe4", "Rxf8+", "Kxf8", "Qxe4", "Qxe4", "Nxe4", "Bf5", "Nd6", "Bc8", "c4", "b6", "Nxc8", "Rxc8",
                   "Kd3", "Ke7", "b4", "Rf8", "Nd4", "Kd7", "c5", "a6", "Ke4", "Rf1", "c6+", "Kc7", "b5", "Re1+", "Kf5", "a5", "Ne6+", "Kd6", "c7", "Rc1", 
                   "Ke4", "Kd7", "Ke5", "Re1+", "Kf5", "Re2", "d6", "g6+" ) )

            // 36. g5xf6 en passant
          , Arguments.of( "Buehrer - Denscheilmann, 2008", "8/ppr1p3/4bk2/8/1P2R3/P2B4/2P5/1K6 w - - 8 81", Arrays.asList(
                   "e4", "c5", "Nf3", "d6", "d4", "cxd4", "Nxd4", "Nf6", "Nc3", "g6", "Be3", "Bg7", "f3", "O-O", "Qd2", "Nc6", "O-O-O", "Nxd4", "Bxd4", "Be6", 
                   "h4", "Qa5", "Kb1", "Rfc8", "a3", "Rab8", "Bxf6", "Bxf6", "Nd5", "Qxd2", "Nxf6+", "Kg7", "Nh5+", "Kh6", "Rxd2", "Kxh5", "g4+", "Kh6", "g5+", "Kg7",
                   "h5", "Rc5", "f4", "Bg4", "hxg6", "hxg6", "Rdh2", "Rg8", "Bd3", "d5", "b4", "Rc7", "exd5", "Bf3", "Rf1", "Bxd5", "f5", "gxf5", "Rxf5", "Bc4", 
                   "Be4", "Rh8", "Rxh8", "Kxh8", "Rf4", "Be6", "Bd3", "Kg7", "Rh4", "f5", "gxf6+", "Kxf6", "Rf4+", "Ke5", "Re4+", "Kf6", "Rf4+", "Ke5", "Re4+", "Kf6" ) )
          
            // 41. b7-b8 promotion to Queen
          , Arguments.of( "Kresovic - Freuler, 2007", "8/8/2Q4k/7p/8/5N2/2P2P1P/5K2 b - - 0 86", Arrays.asList(
                   "e4", "c5", "Nf3", "Nc6", "Bc4", "e6", "Nc3", "g6", "b3", "Bg7", "Bb2", "Nf6", "Rb1", "a6", "a4", "O-O", "O-O", "Re8", "Re1", "b5", 
                   "axb5", "axb5", "Nxb5", "d6", "e5", "dxe5", "Nxe5", "Nd4", "Nxd4", "cxd4", "Qf3", "Qa5", "Ra1", "Qxd2", "Qxa8", "Qf4", "Qf3", "Qd2", "Qd3", "Qf4", 
                   "Qxd4", "Qf5", "Bd3", "Qg5", "Nf3", "Qd5", "Qxd5", "exd5", "Rxe8+", "Nxe8", "Bxg7", "Kxg7", "Ra8", "Nd6", "Rxc8", "Nxc8", "Bb5", "Nd6", "Bc6", "Nb5", 
                   "Bxd5", "Nc3", "Bc6", "f5", "b4", "g5", "b5", "g4", "Ne5", "h5", "b6", "f4", "b7", "f3", "gxf3", "gxf3", "Nxf3", "Ne2+", "Kf1", "Nd4",
                   "b8=Q", "Nxc6", "Qc7+", "Kh6", "Qxc6+" ) )
          
            // 1.e4 c5 2.Nf3 d6 3.d4 cxd4 4.Nxd4 Nf6 5.Nc3 g6 6.f4 Bg7 7.e5 dxe5 8.fxe5 Ng4 9.Bb5+ Kf8 10.Ne6+ fxe6 11.Qxd8+ Kf7 12.O-O+
          , Arguments.of( "Kresovic - Freuler, 2007", "rnbQ3r/pp2pkbp/4p1p1/1B2P3/6n1/2N5/PPP3PP/R1B2RK1 b - - 2 24", Arrays.asList(
                  "e4", "c5", "Nf3", "d6", "d4", "cxd4", "Nxd4", "Nf6", "Nc3", "g6", "f4", "Bg7", "e5", "dxe5", "fxe5", "Ng4", "Bb5+", "Kf8", "Ne6+", "fxe6",
                  "Qxd8+", "Kf7", "O-O+" ) )
                  
        ); }
}
