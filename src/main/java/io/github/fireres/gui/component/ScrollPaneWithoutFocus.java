package io.github.fireres.gui.component;

import javafx.scene.control.ScrollPane;

public class ScrollPaneWithoutFocus extends ScrollPane {
    @Override
    public void requestFocus() {
        //do nothing
    }
}
