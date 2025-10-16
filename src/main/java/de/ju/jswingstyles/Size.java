package de.ju.jswingstyles;

public enum Size {
    SM(12, 8, 16),
    MD(14, 10, 20),
    LG(16, 12, 24);

    private final int fontSize;
    private final int paddingVertical;
    private final int paddingHorizontal;

    Size(int fontSize, int paddingVertical, int paddingHorizontal) {
        this.fontSize = fontSize;
        this.paddingVertical = paddingVertical;
        this.paddingHorizontal = paddingHorizontal;
    }

    public int getFontSize() { return fontSize; }
    public int getPaddingVertical() { return paddingVertical; }
    public int getPaddingHorizontal() { return paddingHorizontal; }
}