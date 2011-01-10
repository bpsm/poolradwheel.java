///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WheelModel {

    public WheelModel() {
    }

    private Glyph.Dethek selectedDethek = null;
    private Glyph.Espuar selectedEspuar = null;
    private Spiral selectedSpiral = null;
    private Game selectedGame = null;

    public Glyph getGlyph(Language language) {
        if (selectedDethek != null && selectedDethek.language == language) {
            return selectedDethek;
        } else if (selectedEspuar != null && selectedEspuar.language == language) {
            return selectedEspuar;
        } else {
            return null;
        }
    }

    public Glyph.Dethek getDethek() {
        return selectedDethek;
    }

    public Glyph.Espuar getEspuar() {
        return selectedEspuar;
    }

    public void select(Glyph g) {
        if (g instanceof Glyph.Espuar) {
            select((Glyph.Espuar)g);
        } else if (g instanceof Glyph.Dethek) {
            select((Glyph.Dethek)g);
        } else if (g == null) {
            throw new IllegalArgumentException
                ("I can't tell if 'null' is meant to be Espuar or Dethek.");
        } else {
            throw new IllegalArgumentException
                ("Unknown Glyph class: " + g.getClass().getName());
        }
    }

    public void select(Glyph.Espuar e) {
        this.selectedEspuar = e;
        fireChange();
    }

    public void select(Glyph.Dethek d) {
        this.selectedDethek = d;
        fireChange();
    }

    public void select(Spiral s) {
        this.selectedSpiral = s;
        fireChange();
    }

    public Spiral getSpiral() {
        return selectedSpiral;
    }

    public Game getGame() {
        return this.selectedGame;
    }

    public void select(Game g) {
        this.selectedGame = g;
        fireChange();
    }

    public String getWord() {
        return WheelLogic.getWord(
                selectedGame,
                selectedEspuar,
                selectedDethek,
                selectedSpiral);
    }


    private void fireChange() {
        ChangeMessageIf msg = new ChangeMessage();
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            ((ChangeListenerIf)it.next()).modelChanged(msg);
        }
    }


    private List listeners = new LinkedList();

    public void addListener(ChangeListenerIf listener) {
        listeners.add(listener);
    }

    public void removeListener(ChangeListenerIf listener) {
        listeners.remove(listeners);
    }

    
    public interface ChangeMessageIf {
        WheelModel getModel();
    }

    private class ChangeMessage implements ChangeMessageIf {
        public WheelModel getModel() {
            return WheelModel.this;
        }
        
        ChangeMessage() { }
    }

    public interface ChangeListenerIf {
        public void modelChanged(ChangeMessageIf msg);
    }

}//WheelModel
