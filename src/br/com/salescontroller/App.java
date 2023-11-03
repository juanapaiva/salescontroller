package br.com.salescontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/LoginPage.fxml"));
        Scene screen = new Scene(root);

        primaryStage.setTitle("Autenticação de Usuário");
        primaryStage.setScene(screen);
        primaryStage.show();
    }
}
