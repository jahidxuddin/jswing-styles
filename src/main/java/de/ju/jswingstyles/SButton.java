package de.ju.jswingstyles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SButton {
    private final JButton INSTANCE;
    private final int cornerRadius = 12;
    private Color bgColor = new Color(0, 120, 255);
    private Color hoverColor = new Color(0, 102, 215);
    private Color textColor = Color.WHITE;

    private int paddingHorizontal;
    private int paddingVertical;
    private int maxFontSize;
    private final int minFontSize = 10;

    public SButton(JFrame frame, String text, Size size) {
        INSTANCE = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

                FontMetrics fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();
                g2.setColor(getForeground());
                g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
            }
        };

        paddingHorizontal = size.getPaddingHorizontal();
        paddingVertical = size.getPaddingVertical();
        maxFontSize = size.getFontSize();

        INSTANCE.setFont(new Font("Segoe UI", Font.PLAIN, maxFontSize));
        INSTANCE.setForeground(textColor);
        INSTANCE.setBackground(bgColor);
        INSTANCE.setFocusPainted(false);
        INSTANCE.setContentAreaFilled(false);
        INSTANCE.setOpaque(false);
        INSTANCE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        INSTANCE.setMargin(new Insets(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal));

        INSTANCE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                INSTANCE.setBackground(hoverColor);
                INSTANCE.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                INSTANCE.setBackground(bgColor);
                INSTANCE.repaint();
            }
        });

        // Responsiv: Button und Text skalieren bei Fenster-Resize
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeToFit(frame.getContentPane().getWidth());
            }
        });

        updateSize();
    }

    private void resizeToFit(int maxWidth) {
        Insets insets = INSTANCE.getInsets();
        int availableWidth = maxWidth - insets.left - insets.right - 20;

        Font font = INSTANCE.getFont();
        int fontSize = INSTANCE.getFont().getSize();
        FontMetrics fm = INSTANCE.getFontMetrics(font);
        int textWidth = fm.stringWidth(INSTANCE.getText()) + paddingHorizontal * 2;

        while (textWidth > availableWidth && fontSize > minFontSize) {
            fontSize--;
            INSTANCE.setFont(new Font(font.getName(), font.getStyle(), fontSize));
            fm = INSTANCE.getFontMetrics(INSTANCE.getFont());
            textWidth = fm.stringWidth(INSTANCE.getText()) + paddingHorizontal * 2;
        }

        while (textWidth < availableWidth && fontSize < maxFontSize) {
            fontSize++;
            INSTANCE.setFont(new Font(font.getName(), font.getStyle(), fontSize));
            fm = INSTANCE.getFontMetrics(INSTANCE.getFont());
            textWidth = fm.stringWidth(INSTANCE.getText()) + paddingHorizontal * 2;
            if (textWidth > availableWidth) {
                fontSize--;
                INSTANCE.setFont(new Font(font.getName(), font.getStyle(), fontSize));
                break;
            }
        }

        updateSize();
    }

    private void updateSize() {
        FontMetrics fm = INSTANCE.getFontMetrics(INSTANCE.getFont());
        int width = fm.stringWidth(INSTANCE.getText()) + paddingHorizontal * 2;
        int height = fm.getHeight() + paddingVertical * 2;
        INSTANCE.setSize(width, height);
        INSTANCE.setPreferredSize(new Dimension(width, height));
    }

    public JButton getInstance() {
        return INSTANCE;
    }

    public void setText(String text) {
        INSTANCE.setText(text);
        updateSize();
        INSTANCE.repaint();
    }

    public void setSizeEnum(Size size) {
        paddingHorizontal = size.getPaddingHorizontal();
        paddingVertical = size.getPaddingVertical();
        maxFontSize = size.getFontSize();
        INSTANCE.setFont(new Font(INSTANCE.getFont().getName(), INSTANCE.getFont().getStyle(), maxFontSize));
        INSTANCE.setMargin(new Insets(paddingVertical, paddingHorizontal, paddingVertical, paddingHorizontal));
        updateSize();
        INSTANCE.repaint();
    }

    public void setBackgroundColor(Color color) {
        bgColor = color;
        INSTANCE.setBackground(bgColor);
        INSTANCE.repaint();
    }

    public void setHoverColor(Color color) {
        hoverColor = color;
    }

    public void setTextColor(Color color) {
        textColor = color;
        INSTANCE.setForeground(textColor);
        INSTANCE.repaint();
    }

    public void onClick(Runnable action) {
        INSTANCE.addActionListener(e -> action.run());
    }
}
