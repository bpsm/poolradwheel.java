///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;



public final class Language {

    public static final Language ESPUAR = new Language(0, "espuar", null);
    public static final Language DETHEK = new Language(1, "dethek", ESPUAR); 	

    public static final Language first = ESPUAR;

    public final int ordinal;
    public final String name;
    private Language next;

    public final Language next() { 
        return this.next; 
    }
		
    private Language(int ordinal, String name, Language prev) {
	this.ordinal = ordinal;
	this.name = name;
        if (prev != null) {
            prev.next = this;
        }
        this.next = null;
    }//Language

    private Language() { 
	throw new UnsupportedOperationException();
    }

}
