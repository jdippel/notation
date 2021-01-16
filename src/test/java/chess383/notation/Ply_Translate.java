/*
 *  Ply_Translate.java
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * <p>
 * The class Ply_Translate implements an upper tester
 * </p>
 *
 * @author    Jörg Dippel
 * @version   December 2020
 *
 */
@DisplayName("the public method Ply translate( ) for class Ply is tested")
public class Ply_Translate {
    
    @ParameterizedTest( name = "given a notation \"{0}\" then the notation should be translated into \"{1}\"." )
    @MethodSource("notationAndTranslatedNotationAndLOcaleProvider")
    public void testWithArgMethodSource_ReturnLongAlgebraicNotation( String fenMove, String translatedMove, Locale language ) {
        
        Ply ply = Ply.create( Ply.readLocationInformation( fenMove.substring( 1 ) ), Ply.readLocationInformation( fenMove.substring( 4 ) ) )
                .setPieceType( "" + fenMove.charAt( 0 ) )
                .setSeparator( ( fenMove.contains( "x" ) ? "x" : "-" ) )
                .setLocaleTag( Locale.ENGLISH )
                .setDescription();
        
        assertThat( translatedMove )
                .as( String.format( "A ply notation %s should be translated to %s", fenMove, translatedMove ) )
                .isEqualTo( ply.translate( language ).filterDescription() );
    }
    
    
    public static Stream<Arguments> notationAndTranslatedNotationAndLOcaleProvider() {
        return Stream.of(
                
            Arguments.of( "Ng1-f3", "Sg1-f3", Locale.GERMAN ),
            Arguments.of( " d2-d4", " d2-d4", Locale.GERMAN )
            
        ); }
    
    
    @Test
    @DisplayName("black kingside castling")
    public void translate_BlackKingsideCastling() {
        
        final String CASTLING = "O-O";
        
        Ply ply = Ply.create( "e8", "g8" )
                     .setPieceType( "K" )
                     .setDescription( CASTLING )
                     .setLocaleTag( Locale.ENGLISH );
        
        assertThat( CASTLING )
                .as( String.format( "A ply notation %s should be translated to %s", CASTLING, CASTLING ) )
                .isEqualTo( ply.translate( Locale.GERMAN ).filterDescription() );
    }

}
