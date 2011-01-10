///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;

/**
 * type safe enumeration representing one of the three
 * supported games: Pool of Radiance, Curse of the Azure Bonds
 * and Hillsfar.
 */
public final class Game {

    public static final Game POOL_OF_RADIANCE =
	new Game(0, "Pool of Radiance", null);
    public static final Game CURSE_OF_THE_AZURE_BONDS =
	new Game(1, "Curse of the Azure Bonds", POOL_OF_RADIANCE);
    public static final Game HILLSFAR = 
	new Game(2, "Hillsfar", CURSE_OF_THE_AZURE_BONDS);
    
    public static final Game first = POOL_OF_RADIANCE;

    public final int ordinal;
    public final String name;
    private Game next;

    public final Game next() {
        return this.next;
    }

    private Game(int ordinal, String name, Game prev) {
	this.ordinal = ordinal;
	this.name = name;
        if (prev != null) {
            prev.next = this;
        }
        this.next = null;
    }

}//Game
