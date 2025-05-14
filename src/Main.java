import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Controller.SceneBuilder;

/** The {@code Main} class serves as an initializer of the JavaFX application. It uses {@link SceneBuilder}   
 * 
 * 
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = SceneBuilder.buildScene(stage);
        Scene scene = new Scene(root, 1920, 1000, true);
        stage.setScene(scene);
        stage.setTitle("Titan Expedition");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}