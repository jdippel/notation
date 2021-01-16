/*
 *  Next.java
 *
 *  chess383 is a collection of chess related utilities.
 *  Copyright (C) 2010-2020 Jörg Dippel
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

import chess383.position.Position;
import chess383.position.Promotion;
import chess383.transition.Transition;

/**
 * Provides the transition between two positions by a notation.
 *
 * @author    Jörg Dippel
 * @version   August 2020
 *
 */
public class Next {
    
    /** ---------  Attributes  -------------------------------- */
    
    private Position position;
    
    /** ---------  Constructors  ------------------------------ */

    private Next( Position position ) {
        setPosition( position );
    }

    /** ---------  Getter and Setter  ------------------------- */
    
    private void setPosition( Position value )    { this.position = value; }
    private Position getPosition( )               { return this.position; }
    
    /** ---------  Factory  ----------------------------------- */
    
    public static Next create( Position position ) {

        return new Next( position );
    }
    
    /** ------------------------------------------------------- */

    public Position next( String originLocation, String targetLocation ) {
        
        return Transition.create( getPosition() ).change( originLocation, targetLocation );
    }
    
    public Position next( Ply transition ) {
        
        Position position = next( transition.getOriginLocation(), transition.getTargetLocation() );
        if( transition.getPromotion().length() > 0 ) {
            
            Promotion promotion = Promotion.create( position );
            return promotion.promote( transition.getTargetLocation(), transition.getPromotion().charAt( 0 ));
        }
        return position;
    }
    
    public Position next( String transition ) {
        
        return next( AlgebraicNotation.create( getPosition() ).expand( transition ));
    }
}
