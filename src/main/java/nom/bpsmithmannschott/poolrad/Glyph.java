///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Glyph {
    public static final int glyphsPerLanguage = 36;

    public final Language language;
    public final int ordinal;
    private ImageIcon icon = null;
    private String rsrcPath;


    private Glyph() {
        this.language = null; 
        this.ordinal = -1;
        throw new UnsupportedOperationException();
    }

    private Glyph(Language language, int ordinal) {
        this.language = language;
        this.ordinal = ordinal;
        final String path = 
            language.name + "/" +
            ((ordinal < 10) ? "0" : "") + Integer.toString(ordinal) + ".gif";
        this.rsrcPath = path;
    }

    public static final Glyph first(Language language) {
        if (language == Language.ESPUAR) {
            return Espuar.first;
        } else if (language == Language.DETHEK) {
            return Dethek.first;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public abstract Glyph nextGlyph();

    /**
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		if (icon == null)
		{
			Image img = new ImageIcon(getClass().getResource(rsrcPath)).getImage();
			img = img.getScaledInstance(24, -1, Image.SCALE_SMOOTH);
			this.icon = new ImageIcon(img);
		}
		return icon;
	}

	public static final class Espuar extends Glyph {
        public static final Espuar first;
        private Espuar next;

        public final Espuar next() {
            return this.next;
        }

        public final Glyph nextGlyph() {
            return next();
        }

        private Espuar(int ordinal, Espuar prev) {
            super(Language.ESPUAR, ordinal);
            if (prev != null) {
                prev.next = this;
            }
            this.next = null;
        }

        static {
            Espuar prev = first = new Espuar(0, null);
            for (int i = 1; i < glyphsPerLanguage; i++) {
                prev = new Espuar(i, prev);
            }
        }//static
    }//class Espuar


    public static final class Dethek extends Glyph {
        public static final Dethek first;
        private Dethek next;

        public final Dethek next() {
            return this.next;
        }

        public final Glyph nextGlyph() {
            return next();
        }

        private Dethek(int ordinal, Dethek prev) {
            super(Language.DETHEK, ordinal);
            if (prev != null) {
                prev.next = this;
            }
            this.next = null;
        }

        static {
            Dethek prev = first = new Dethek(0, null);
            for (int i = 1; i < glyphsPerLanguage; i++) {
                prev = new Dethek(i, prev);
            }
        }//static
    }//class Dethek

}
