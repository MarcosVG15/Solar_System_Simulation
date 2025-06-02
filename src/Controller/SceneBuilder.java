package Controller;

import Model.Planets;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.AstralObject;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.SolarSystem;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.SpaceObject;
import Util.Lighting;
import View.SettingsMenu;
import View.SkySphere;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code SceneBuilder} class is responsible for building the whole Solar System 
 * including visual objects, animations, lighting, settings menu and camera.
 * This class serves as the main controller for constructing and initializing the 3D scene
 * of the solar system simulation.
 */
public class SceneBuilder {
    
    /**
     * List of all planets in the solar system simulation.
     * Each planet is represented by a {@link Planets} object containing both visual and physical properties.
     */
    private static ArrayList<Planets> planets = new ArrayList<>();

    /**
     * List of all astral objects in the solar system.
     * Contains the physical representation of celestial bodies used for calculations.
     */
    static SolarSystem solarSystemS = new SolarSystem();

    /**
     * Builds and returns the main scene of the solar system simulation.
     * This method initializes all components including:
     *   The sky sphere background
     *   All planetary objects
     *   Lighting system
     *   Camera and its controls
     *   Settings menu
     *   Animation system for planetary motion
     *
     *
     * @param stage The primary stage of the JavaFX application
     * @return A {@link BorderPane} containing the complete solar system scene
     */
    public static BorderPane buildScene(Stage stage){
        // Scene dimensions
        double HEIGHT = 1000;
        double WIDTH = 1920;

        BorderPane root = new BorderPane();
        Group sceneElementsGroup = new Group();

        // Sky sphere background
        SkySphere background = new SkySphere(50000);//"file:src/Resources/8k_stars_milky_way.jpg"
        sceneElementsGroup.getChildren().add(background);

        //Objects in solar system
        planets = SolarSystemInitializer.createVisualizationOfPlanets(solarSystemS, sceneElementsGroup);
        for(Planets planet : planets){
            planet.updatePosition();
        }
        System.out.println("Amount of elements in the Solar System: " + planets.size());

        // Lighting
        Group lighting = new Group(
                Lighting.createAmbientLight(),
                Lighting.createPointLight(WIDTH / 2, HEIGHT / 2, 0)
        );
        sceneElementsGroup.getChildren().add(lighting);

        // Camera setup
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateX(200); 
        camera.setTranslateY(-250); 
        camera.setTranslateZ(-600); 
        camera.setNearClip(0.1);
        camera.setFarClip(100000);
        Rotate rotateX = new Rotate(-32, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(-32, Rotate.Y_AXIS);
        camera.getTransforms().addAll(rotateY, rotateX);

        // SubScene
        SubScene subScene = new SubScene(sceneElementsGroup, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.BLACK);
        subScene.setCamera(camera);

        // Camera control
        CameraController.addCameraControls(subScene, camera, WIDTH, HEIGHT);

        // GUI Left Menu
        SettingsMenu menu = new SettingsMenu();
        root.setLeft(menu.createSettingsPanel(camera, subScene, planets));
        root.setCenter(subScene);
        
        // Animation

        Animation animation = new Animation(planets, solarSystemS);
        animation.animationStart(sceneElementsGroup);

        return root;
    }
}
