package de.ju.jswingstyles;

import javax.swing.*;

public class TestFrame extends JFrame {
    private int counter;

    /*public TestFrame() {
        this.counter = 0; // Zähler initialisieren

        this.setTitle("Test");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        // Text-Label und Button erstellen
        SText text = new SText(this, String.valueOf(counter), Size.LG);
        SButton button = new SButton(this, "Click Me", Size.SM);

        // Button klickbar machen: erhöht den Zähler und aktualisiert das Label
        button.onClick(() -> {
            counter++;
            text.setText(String.valueOf(counter));
        });

        // Komponenten dem Layout hinzufügen und zentrieren
        this.add(Box.createVerticalGlue());
        text.getInstance().setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(text.getInstance());
        this.add(Box.createVerticalStrut(20));
        button.getInstance().setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(button.getInstance());
        this.add(Box.createVerticalGlue());

        this.setVisible(true);
    }*/

    public TestFrame() {
        this.counter = 0; // Zähler initialisieren

        this.setTitle("Test");
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new SFlexLayout(getContentPane(), FlexDirection.ROW).getInstance());

        // Text-Label und Button erstellen
        SText text = new SText(this, String.valueOf(counter), Size.LG);
        SButton button = new SButton(this, "Click Me", Size.SM);

        // Button klickbar machen: erhöht den Zähler und aktualisiert das Label
        button.onClick(() -> {
            counter++;
            text.setText(String.valueOf(counter));
        });

        this.add(text.getInstance());
        this.add(button.getInstance());

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame();
    }
}
