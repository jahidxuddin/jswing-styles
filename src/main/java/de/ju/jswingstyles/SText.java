package de.ju.jswingstyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SText {
    private final JLabel INSTANCE;
    private final Size size;

    public SText(JFrame frame, String text, Size size) {
        this.size = size;
        INSTANCE = new JLabel(text);
        INSTANCE.setHorizontalAlignment(SwingConstants.CENTER);

        // Schrift beim Start etwas größer skalieren
        int baseFontSize = (int) (size.getFontSize() * 1.5f);
        INSTANCE.setFont(new Font(INSTANCE.getFont().getFontName(), Font.PLAIN, baseFontSize));

        // Dynamische Skalierung bei Fensteränderung
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFontSize(frame.getWidth(), frame.getHeight());
            }
        });
    }

    private void updateFontSize(int width, int height) {
        // etwas stärker skalieren als vorher
        float scale = Math.min(width, height) / 360f; // 360 statt 480 → größerer Faktor
        float newSize = size.getFontSize() * scale * 1.5f; // 1.5x generelle Verstärkung
        INSTANCE.setFont(INSTANCE.getFont().deriveFont(newSize));
    }

    public JLabel getINSTANCE() {
        return INSTANCE;
    }

    public void setText(String text) {
        INSTANCE.setText(text);
    }
}
