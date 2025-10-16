package de.ju.jswingstyles;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SFlexLayout {
    private final Container container;
    private final List<Component> components = new ArrayList<>();
    private final FlexDirection flexDirection;

    public SFlexLayout(Container container, FlexDirection flexDirection) {
        this.container = container;
        this.flexDirection = flexDirection;

        if (flexDirection == FlexDirection.ROW) {
            container.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // horizontale Reihen, Höhe unverändert
        } else {
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS)); // vertikal
        }
    }

    public void addComponent(Component component) {
        components.add(component);
        container.add(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public LayoutManager getInstance() {
        return container.getLayout();
    }
}
