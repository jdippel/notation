/*
 *  AlgebraicNotation_Expand2LAN.java
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

import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess383.ICoordinateFactory;

/**
 * <p>
 * The class AlgebraicNotation_Expand2LAN implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   January 2021
 *
 */
@DisplayName("the public method String expand2LAN( ) for class AlgebraicNotation is tested for given positions")
public class AlgebraicNotation_Expand2LAN {
    
    @ParameterizedTest( name = "given a position \"{0}\" and an algebraic notation \"{1}\" then the long notation \"{2}\" should be returned." )
    @MethodSource("positionAndSANandLANProviderForEnglishLocale")
    public void testWithArgMethodSource_ReturnLongAlgebraicNotation( String position, String san, String lan ) {
        
        ICoordinateFactory.STANDARD.get();
        AlgebraicNotation notation = AlgebraicNotation.create( position, Locale.ENGLISH, Locale.ENGLISH );

        Ply expectedPly = Ply.create( lan.substring( 1, 3 ), lan.substring( 4, 6 ) );
        Ply returnedPly = notation.expand2LAN( san );
        assertThat( expectedPly )
                .as( String.format( "Given a position, for an algebraic notation %s both locations should be determined correctly should be returned", lan ) )
                .isEqualTo( returnedPly );
        assertThat( lan )
                .as( String.format( "Given a position, for an algeraic notation %s the long algebraic notation %s should be returned", san, lan ) )
                .isEqualTo( returnedPly.setDescription().getDescription() );
    }
    
    
    public static Stream<Arguments> positionAndSANandLANProviderForEnglishLocale() {
        return Stream.of(
            
            Arguments.of( "rnbqkbnr/ppp2ppp/4p3/3p4/3PP3/8/PPP2PPP/RNBQKBNR w KQkq - 0 5", "Nd2", "Nb1-d2" )
          , Arguments.of( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "Nf3", "Ng1-f3" )
          , Arguments.of( "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", "e2-e4", " e2-e4" )
          , Arguments.of( "8/pN5k/2p5/2P1b1pp/P1r5/5pPK/3R1P2/8 b - - 0 97", "Rh4", "Rc4-h4" )
          , Arguments.of( "6rk/6rp/pp2pQ2/3p4/P5R1/7P/2q3P1/5R1K w - - 0 60", "Qg7", "Qf6xg7" )
          , Arguments.of( "4r1nk/3R3p/5qp1/P7/8/2Q2N1P/6P1/7K w - - 0 102", "Ng5", "Nf3-g5" )
          , Arguments.of( "3r1r1k/p6p/1p1b1P2/2pqpNP1/3p4/1P3R2/1PP3QP/5RK1 w - - 0 56", "g6", " g5-g6" )
          , Arguments.of( "r3r2k/5p1p/4pb1q/p2pB3/P2P2R1/2P2PQ1/7P/6RK w - - 0 68", "Qh3", "Qg3-h3" )
          , Arguments.of( "r1b2k2/1p4pQ/1q2pb1p/p3Np2/P7/8/1PB2PPP/3R2K1 w - - 0 58", "Rd7", "Rd1-d7" )
          , Arguments.of( "rn3rk1/p4p2/1p2pqpp/2p1N3/2BPbPQ1/P1P1P3/6PP/R4RK1 w - - 0 30",  "f5", " f4-f5" )
          , Arguments.of( "k2r4/1p2q3/p1p1P3/3n2p1/3N3p/P6P/1P3rP1/K1QRR3 w - - 0 68", "Nc6", "Nd4xc6" )
            
        ); }
    
    
    @Test
    @DisplayName("white pawn opens the game")
    public void plyForWhitePawnOpening() {
        
        ICoordinateFactory.STANDARD.get();
        final String POSITION_STRING = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        final String NOTATION = " e2-e4";
        
        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );
        Ply ply = notation.expand2LAN( NOTATION );

        assertThat( ply.filterDescription() )
                   .as( "white pawn opens" )
                   .isEqualTo( " e2-e4" );
    }

    @Test
    @DisplayName("white pawn opens the game")
    public void plyForWhitePawnOpeningWithoutPreceedingWhiteSpace() {

        ICoordinateFactory.STANDARD.get();
        final String POSITION_STRING = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        final String NOTATION = "e2-e4";

        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );
        Ply ply = notation.expand2LAN( NOTATION );

        assertThat( ply.filterDescription() )
                .as( "white pawn opens" )
                .isEqualTo( " e2-e4" );
    }

    @Test
    @DisplayName("white pawn captures in Center Game")
    public void plyForWhitePawnCapturingInCenterGane() {

        ICoordinateFactory.STANDARD.get();
        final String POSITION_STRING = "rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3";
        final String NOTATION = "ed5";

        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );
        Ply ply = notation.expand2LAN( NOTATION );

        assertThat( ply.filterDescription() )
                .as( "white pawn opens" )
                .isEqualTo( " e4xd5" );
    }

    @Test
    @DisplayName("white kingside castling")
    public void plyForWhiteKingsideCastling() {

        ICoordinateFactory.STANDARD.get();
        final String KINGSIDE_CASTLING = "O-O";
        final String POSITION_STRING = "r1bqk2r/ppp2ppp/2np1n2/2b1p3/2B1P3/2PP1N2/PP3PPP/RNBQK2R w KQkq - 0 13";

        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );

        Ply ply = notation.expand( KINGSIDE_CASTLING );

        assertThat( KINGSIDE_CASTLING )
                .as( "castling is not described via origin and target location" )
                .isEqualTo( ply.filterDescription() );
        assertThat( "K" ).as( "piece type" ).isEqualTo( ply.getPieceType() );
        assertThat( "e1" ).as( "origin location" ).isEqualTo( ply.getOriginLocation() );
        assertThat( "g1" ).as( "target location" ).isEqualTo( ply.getTargetLocation() );
    }
    
    @Test
    @DisplayName("black kingside castling")
    public void plyForBlackKingsideCastling() {
        
        ICoordinateFactory.STANDARD.get();
        final String KINGSIDE_CASTLING = "O-O";
        final String POSITION_STRING = "r1bqk2r/ppp2ppp/2np1n2/2b1p3/2B1P3/2PP1N2/PP3PPP/RNBQ1RK1 b kq - 1 14";
        
        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );
        
        Ply ply = notation.expand( KINGSIDE_CASTLING );
        
        assertThat( KINGSIDE_CASTLING )
                   .as( "castling is not described via origin and target location" )
                   .isEqualTo( ply.filterDescription() );
        assertThat( "K" ).as( "piece type" ).isEqualTo( ply.getPieceType() );
        assertThat( "e8" ).as( "origin location" ).isEqualTo( ply.getOriginLocation() );
        assertThat( "g8" ).as( "target location" ).isEqualTo( ply.getTargetLocation() );
    }
    
    @Test
    @DisplayName("black queenside castling")
    public void plyForBlackQueensideCastling() {
        
        ICoordinateFactory.STANDARD.get();
        final String QUEENSIDE_CASTLING = "O-O-O";
        final String POSITION_STRING = "r3kb1r/pp4pp/2p1bn2/4N3/2BPpB2/6Pq/PPP1Q2P/R3K2R b WQkq - 4 28";
        
        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );
        
        Ply ply = notation.expand( QUEENSIDE_CASTLING );
        
        assertThat( QUEENSIDE_CASTLING )
                   .as( "castling is not described via origin and target location" )
                   .isEqualTo( ply.filterDescription() );
        assertThat( "K" ).as( "piece type" ).isEqualTo( ply.getPieceType() );
        assertThat( "e8" ).as( "origin location" ).isEqualTo( ply.getOriginLocation() );
        assertThat( "c8" ).as( "target location" ).isEqualTo( ply.getTargetLocation() );
    }
    
    @Test
    @DisplayName("white queenside castling")
    public void plyForWhiteQueensideCastling() {
        
        ICoordinateFactory.STANDARD.get();
        final String QUEENSIDE_CASTLING = "O-O-O";
        final String POSITION_STRING = "2kr1b1r/pp4pp/2p1bn2/4N3/2BPpB2/6Pq/PPP1Q2P/R3K2R w WQ - 5 29";
        
        AlgebraicNotation notation = AlgebraicNotation.create( POSITION_STRING );
        
        Ply ply = notation.expand( QUEENSIDE_CASTLING );
        
        assertThat( QUEENSIDE_CASTLING )
                   .as( "castling is not described via origin and target location" )
                   .isEqualTo( ply.filterDescription() );
        assertThat( "K" ).as( "piece type" ).isEqualTo( ply.getPieceType() );
        assertThat( "e1" ).as( "origin location" ).isEqualTo( ply.getOriginLocation() );
        assertThat( "c1" ).as( "target location" ).isEqualTo( ply.getTargetLocation() );
    }
}
