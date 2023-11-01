package br.com.salescontroller.models;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Utils {

    public static String[] states = {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
    
    public static void cleanFields(AnchorPane pane) {
        ObservableList<Node> components = pane.getChildren();
        for (Node node : components) {
            if (node instanceof TextField)
                ((TextField)node).setText("");
        }
    }
}
