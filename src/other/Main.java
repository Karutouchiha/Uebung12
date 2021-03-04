package other;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.ControllerMain;

public class Main extends Application {
    private ControllerMain CM;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/Mainscreen.fxml"));
        Parent root = loader.load();

        CM=loader.getController();
        CM.setStage(primaryStage);

        primaryStage.setTitle("Phonebook");
        primaryStage.getIcons().add(new Image("https://s2.qwant.com/thumbr/0x380/c/e/17fc1f9bbfe37c9fb092530587faeb7b246672b67d6cf2b8be8a02d49f8e36/Address-Book-icon.png?u=http%3A%2F%2Ficons.iconarchive.com%2Ficons%2Fartua%2Fmac%2F512%2FAddress-Book-icon.png&q=0&b=1&p=0&a=1"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    @Override
    public void stop(){
        CM.save();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
