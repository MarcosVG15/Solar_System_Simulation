package View;

import Controller.CameraController;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;

public class SettingsMenu {
    private static double height = 1000;
    private static double width = 1920;
    public VBox createSettingsPanel(PerspectiveCamera camera, SubScene subScene){
        
   //Planets
   VBox planets = new VBox(5, 
                new CheckBox("Mercury"),
                new CheckBox("Venus"),
                new CheckBox("Earth"), 
                new CheckBox("Mars"),
                new CheckBox("Jupiter"),
                new CheckBox("Saturn"),
                new CheckBox("Uranus"),
                new CheckBox("Neptune")
                );
    planets.getChildren().forEach(e -> {
        if (e instanceof CheckBox) {((CheckBox) e).setSelected(true);};
    });
    TitledPane planetsMenu = new TitledPane("Show/hide planets", planets);
    // Additional parts of the simulation
    VBox sideParts = new VBox(5,
                new CheckBox("Rocket Trajectory"),
                new CheckBox("Orbits"),
                new CheckBox("Rocket Model"),
                new CheckBox("Rocket Launch Scene")
    );
    sideParts.getChildren().forEach(e -> {
        if (e instanceof CheckBox) {((CheckBox) e).setSelected(true);};
    });
    TitledPane sidePartsMenu = new TitledPane("Trajectory and Orbits", sideParts);
    //Stats of the mission
    VBox statistics = new VBox(5,
                new Label("Speed of the rocket: "),
                new Label("Distance to the Titan: "),
                new Label("Fuel in the tank: ")
                    );
    TitledPane statisticsMenu = new TitledPane("Statistics", statistics);

    VBox cameraPosition = new VBox(5, 
                new Button("From Above"),
                new Button("Main Camera"),
                new Button("Side View")
                );
    TitledPane cameraPositionPane = new TitledPane("Observer vision", cameraPosition);

    VBox setRocketSettings = new VBox(5, 
                new Button("Set conditions for Rocket"));
    TitledPane setRocketSettingsPane = new TitledPane("Set up simulation",setRocketSettings);

    setUpCameraPosition(subScene,cameraPosition, camera);
    String choiceBoxStyle = "-fx-background-color: rgba(0, 0, 0, 1); -fx-text-fill: rgb(255, 255, 255); -fx-mark-color: rgb(52, 123, 33);";
    String titledPaneStyle = "-fx-color: rgba(0,0,0,1); -fx-text-fill: rgb(255, 255, 255);";

    planets.setStyle(choiceBoxStyle);
    sideParts.setStyle(choiceBoxStyle);
    statistics.setStyle(choiceBoxStyle);
    setRocketSettings.setStyle(choiceBoxStyle);
    cameraPosition.setStyle(choiceBoxStyle);

    planetsMenu.setStyle(titledPaneStyle);
    sidePartsMenu.setStyle(titledPaneStyle);
    statisticsMenu.setStyle(titledPaneStyle);
    cameraPositionPane.setStyle(titledPaneStyle);
    setRocketSettingsPane.setStyle(titledPaneStyle);
    

    VBox settingsPanel = new VBox(10, planetsMenu, sidePartsMenu, statisticsMenu, cameraPositionPane, setRocketSettingsPane);
    
    settingsPanel.setStyle("-fx-background-color: rgba(0, 0, 0, 1); -fx-padding: 10; -fx-background-radius: 10;");
    settingsPanel.setPrefWidth(250);
    settingsPanel.getStylesheets().add(getClass().getResource("/View/graphicsCSS.css").toExternalForm());

    return settingsPanel;
    }
    
    private static void setUpCameraPosition(SubScene subScene,VBox cameraPosition, PerspectiveCamera camera){
        Button fromAbove = (Button) cameraPosition.getChildren().get(0);
        Button mainCamera = (Button) cameraPosition.getChildren().get(1);
        Button sideView = (Button) cameraPosition.getChildren().get(2);

        fromAbove.setOnAction(e ->{
            camera.getTransforms().clear();
            camera.setTranslateX(0);
            camera.setTranslateY(300);
            camera.setTranslateZ(0);
            camera.setRotationAxis(Rotate.X_AXIS);
            Rotate yRotate = new Rotate(-90, Rotate.Y_AXIS); 
            Rotate xRotate = new Rotate(0, Rotate.X_AXIS); 
            camera.getTransforms().addAll(yRotate, xRotate);

        });

        mainCamera.setOnAction(e -> {
            camera.getTransforms().clear();
            
            camera.setTranslateX(200); 
            camera.setTranslateY(-250); 
            camera.setTranslateZ(-600); 
            camera.setNearClip(0.1);
            camera.setFarClip(100000);
            Rotate xRotate = new Rotate(-32, Rotate.X_AXIS);
            Rotate yRotate = new Rotate(-32, Rotate.Y_AXIS);
            
            camera.getTransforms().addAll(yRotate, xRotate);
            CameraController.resetCameraSettings(subScene, camera, height, width);
        });

        sideView.setOnAction(e ->{
            camera.getTransforms().clear();
            camera.setTranslateX(-1000);
            camera.setTranslateY(0);
            camera.setTranslateZ(-500);
            camera.setRotationAxis(Rotate.X_AXIS);
            camera.setRotate(0);

            Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
            Rotate rotateY = new Rotate(90, Rotate.Y_AXIS);
            camera.getTransforms().addAll(rotateY, rotateX);
            
            CameraController.resetCameraSettings(subScene, camera, height, width);
        });

    }
}
