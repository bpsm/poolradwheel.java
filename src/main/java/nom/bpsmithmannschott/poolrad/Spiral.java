///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public final class Spiral {

    public static final Spiral DOTTED = new Spiral(0, "dotted", null);
    public static final Spiral MIXED = new Spiral(1, "mixed", DOTTED);
    public static final Spiral DASHED = new Spiral(2, "dashed", MIXED);

    public static final Spiral first = DOTTED;

    public final int ordinal;
    public final Icon icon;
    private Spiral next;

    public final Spiral next() { return this.next; }

    private Spiral(int ordinal, String baseName, Spiral prev) {
	this.ordinal = ordinal;
 
	String path = "spiral/" + baseName + ".gif";
	URL urlToPng = this.getClass().getResource(path);
	this.icon = new ImageIcon(urlToPng);

        if (prev != null) {
            prev.next = this;
            if (prev.ordinal >= ordinal) {
                throw new IllegalArgumentException();
            }
        }
    }

}
