///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;

public class WheelLogic {

    private static final int numberOfUniquePositions = 36;
    private static final int spiralSpacing = numberOfUniquePositions / 3;

    public static String getWord(Game game, 
                                 Glyph.Espuar espuar,
                                 Glyph.Dethek dethek,
                                 Spiral spiral) {

        if (game != null && espuar != null && dethek != null && spiral != null) {
            final int g = game.ordinal;
            final int e = espuar.ordinal; // outer ring
            final int d = dethek.ordinal; // inner ring
            final int s = spiral.ordinal;
            final int p = spiralSpacing;
            final int n = numberOfUniquePositions;
            final int w = (e + d + p*s) % n;
            return words[g][w];
        } else {
            return "      ";
        }
    }

    private static final String[][] words = {	
        {   //Pool of Radiance
            "0SOMAS", "AXEIAX", "BEWARE", "COPPER", "DRAGON", "EFREET",
            "FRIEND", "GOOGLE", "HARASH", "IXYVSI", "JUNGLE", "KNIGHT",
            "LQLGMT", "MLSSXS", "NOTNOW", "OPTAWA", "POOLRD", "QUOHOG",
            "RHUDIA", "SAVIOR", "TEMPLE", "UICDRH", "VULCAN", "WYVERN",
            "XRSEHK", "YUFSTA", "ZOMBIE", "1GKKRY", "2IOLCD", "3MASAI",
            "4NINER", "5GUNGA", "6BROWN", "7GNATS", "8OASIS", "9TROUT"
        },
        {   //Curse of the Azure Bonds
            "OMIMIC", "ATOMIE", "BEHOLD", "CLERIC", "DRUIDS", "ELVISH",
            "FUNGUS", "GIORGI", "HYDRAS", "INSIDE", "JAGUAR", "KEEPER", 
            "LOOTER", "MOGION", "NIXIES", "OTYUGH", "POWERS", "QUILLS",
            "RESIST", "SLIMES", "TROLLS", "URCHIN", "VERMIN", "WRAITH",
            "XERXES", "YULASH", "ZIRCON", "1MAGIC", "2ARROW", "3DRILL",
            "4PHLAN", "5POLAR", "6FIRST", "7AZURE", "8BONDS", "9ALIAS"
        },
        {   //Hillsfar
            "0SAMAS", "ATTACK", "BOUNTY", "CUDGEL", "DRAGON", "EFREET",
            "FOREST", "GAMBLE", "HAGGLE", "IXYVSI", "JEWELS", "KNIGHT",
            "LQLGMT", "MYSTIC", "NECROS", "OPTAWA", "PRINCE", "QUESTS",
            "RHUDIA", "STEEDS", "TEMPLE", "UNLOCK", "VORPAL", "WYVERN", 
            "XRSEHK", "YUFSTA", "ZOMBIE", "1BLADE", "2AGILE", "3MASAI",
            "4MAGES", "5GUNGA", "6BROWN", "7GNATS", "8OASIS", "9TROUT"
        }
    };

}//class WheelLogic
