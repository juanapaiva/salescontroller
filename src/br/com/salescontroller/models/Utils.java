package br.com.salescontroller.models;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Utils {
    
    public static void cleanFields(AnchorPane pane) {
        ObservableList<Node> components = pane.getChildren();
        for (Node node : components) {
            if (node instanceof TextField)
                ((TextField)node).setText("");
        }
    }
}
