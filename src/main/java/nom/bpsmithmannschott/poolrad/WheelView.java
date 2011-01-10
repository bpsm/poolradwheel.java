///   Copyright (c) 2011, B. Smith-Mannschott
///   All rights reserved.
///   License: BSD-Style (see LICENSE)

package nom.bpsmithmannschott.poolrad;

import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public final class WheelView extends JFrame {

    private static final long serialVersionUID = -6300822941815973246L;
    private transient WheelModel model = null;

    private WheelModel getModel() {
        return model = (model != null) ? model : new WheelModel();
    }

    public WheelView() {
        super();
        init();
    }

    public WheelView(GraphicsConfiguration gc) {
        super(gc);
        init();
    }

    public WheelView(String title) {
        super(title);
        init();
    }

    public WheelView(String title, GraphicsConfiguration gc) {
        super(title, gc);
        init();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WheelView("Wheel");
            }
        });
    }
        
    protected void init() {
        final int padding = 6;
        
        Box mainBox = Box.createVerticalBox();

        Box gameAndSpiralBox = Box.createHorizontalBox();
        Box gameBox = Box.createVerticalBox();
        gameBox.add(label("Game"));
        gameBox.add(makeGamePanel());
        gameAndSpiralBox.add(gameBox);
        Box spiralBox = Box.createVerticalBox();
        spiralBox.add(label("Spiral"));
        spiralBox.add(makeSpiralPanel());
        gameAndSpiralBox.add(spiralBox);
        mainBox.add(gameAndSpiralBox);

        mainBox.add(Box.createVerticalStrut(padding));

        
        Box glyphBox = Box.createHorizontalBox();
        mainBox.add(glyphBox);
        Box espuarBox = Box.createVerticalBox();
        glyphBox.add(espuarBox);
        espuarBox.add(label("Espuar"));
        espuarBox.add(makeLetterPanel(Language.ESPUAR));
        glyphBox.add(Box.createHorizontalStrut(padding*2));
        Box dethekBox = Box.createVerticalBox();
        glyphBox.add(dethekBox);
        dethekBox.add(label("Dethek"));
        dethekBox.add(makeLetterPanel(Language.DETHEK));

        mainBox.add(Box.createVerticalStrut(padding));

        Box outputBox = Box.createVerticalBox();
        mainBox.add(outputBox);
        outputBox.add(label("Output Word"));
        outputBox.add(makeOutputPanel());
        
        mainBox.add(Box.createVerticalStrut(padding));

        this.setContentPane(mainBox);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//init()
        

    private static JComponent label(String text) {
        JLabel L = new JLabel(text);
        JPanel P = new JPanel();
        P.add(L);
        return P;
    }

    private JPanel makeOutputPanel() {
        final JPanel p = new JPanel(new GridLayout(1,6));
        final JLabel[] L = new JLabel[6];
        for (int i = 0; i < 6; i++) {
            L[i] = new JLabel("X");
            L[i].setHorizontalAlignment(SwingConstants.CENTER);
            p.add(L[i]);
        }
        getModel().addListener(new WheelModel.ChangeListenerIf() {
                public void modelChanged(WheelModel.ChangeMessageIf msg) {
                    String word = getModel().getWord();
                    for (int i = 0; i < 6; i++) {
                        L[i].setText(word.substring(i, i+1));
                    }
                }
            });
        return p;
    }
        
    private JPanel makeLetterPanel(final Language language) {
        JPanel panel = new JPanel(new GridLayout(7,5));
        ButtonGroup group = new ButtonGroup();
        
        for (Glyph g = Glyph.first(language).nextGlyph(); g != null; g = g.nextGlyph()) {
            final Glyph glyph = g;
            final JToggleButton b = new JToggleButton(g.getIcon());
            b.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        if (b.isSelected()) {
                            getModel().select(glyph);
                        }
                    }
                });
            panel.add(b);
            group.add(b);
        }
        return panel;
    }

    private JPanel makeSpiralPanel() {
        JPanel panel = new JPanel(new java.awt.GridLayout(1, 3));
        ButtonGroup group = new ButtonGroup();

        for (Spiral s = Spiral.first; s != null; s = s.next()) {
            final Spiral spiral = s;
            final JToggleButton b = new JToggleButton(spiral.icon);
            b.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        if (b.isSelected()) {
                            getModel().select(spiral);
                        }
                    }
                });
            panel.add(b);
            group.add(b);
        }
        return panel;
    }

    private JPanel makeGamePanel() {
        JPanel panel = new JPanel(new GridLayout(3,1));
        ButtonGroup group = new ButtonGroup();

        for (Game g = Game.first; g != null; g = g.next()) {
            final Game game = g;
            final JRadioButton b = new JRadioButton(game.name);
            b.addChangeListener(new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        if (b.isSelected()) {
                            getModel().select(game);
                        }
                    }
                });
            panel.add(b);
            group.add(b);
        }
        return panel;
    }
        
}
