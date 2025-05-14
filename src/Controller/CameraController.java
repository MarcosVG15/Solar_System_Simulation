package Controller;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.transform.Rotate;

/**
 * The {@code CameraController} class manages camera controls and interactions in the 3D scene.
 * It provides functionality for camera rotation, zooming, and reset capabilities.
 * The camera can be controlled using mouse movements and scroll wheel input.
 */
public class CameraController {
    /** Rotation transform for the X-axis (up-down camera movement) */
    private static Rotate rotateX;
    
    /** Rotation transform for the Y-axis (left-right camera movement) */
    private static Rotate rotateY;
    private static double defaultPOV = 75;
    private static double zoomSpeed = 0.05;

    /**
     * Adds camera control functionality to a 3D scene.
     * Sets up mouse event handlers for:
     * <ul>
     *   <li>Camera rotation with mouse drag</li>
     *   <li>Zoom with mouse scroll wheel</li>
     * </ul>
     *
     * @param scene The SubScene to add camera controls to
     * @param camera The camera to be controlled
     * @param width The width of the scene
     * @param height The height of the scene
     */
    public static void addCameraControls(SubScene scene, Camera camera, double width, double height){
        PerspectiveCamera perspectiveCam = (PerspectiveCamera) camera;
        perspectiveCam.setFieldOfView(defaultPOV);
        rotateX = new Rotate(0, Rotate.X_AXIS);
        rotateY = new Rotate(0, Rotate.Y_AXIS);
        camera.getTransforms().addAll(rotateX, rotateY);

        final double[] coordinatesX = new double[1];
        final double[] coordinatesY = new double[1];
        final double[] angleX = new double[1];
        final double[] angleY = new double[1];
        scene.setOnMousePressed(e -> {
            coordinatesX[0] = e.getSceneX();
            coordinatesY[0] = e.getSceneY();
        });
        
        scene.setOnMouseDragged(e -> {
            if(e.isPrimaryButtonDown()){
                double dx = e.getSceneX() - coordinatesX[0];
                double dy = e.getSceneY() - coordinatesY[0];
                // Rotate the camera around the Y-axis (left-right rotation)
                angleY[0] -= dx * 0.1;
                // Rotate the camera around the X-axis (up-down rotation)
                angleX[0] += dy * 0.1;
                rotateY.setAngle(angleY[0]);
                rotateX.setAngle(angleX[0]);
                coordinatesX[0] = e.getSceneX();
                coordinatesY[0] = e.getSceneY();
            }
        });

        // Scroll function
        scene.setOnScroll(e -> {
            double delta = e.getDeltaY();
            double fovNow = perspectiveCam.getFieldOfView();
            double newFov = fovNow - delta * zoomSpeed;
            perspectiveCam.setFieldOfView(newFov);

        });
    }
    public static void resetCameraSettings(SubScene scene, PerspectiveCamera camera, double width, double height){
        camera.setFieldOfView(defaultPOV);
        rotateX.setAngle(0);
        rotateY.setAngle(0);
        
        scene.setOnMouseDragged(null);
        scene.setOnScroll(null);
        addCameraControls(scene, camera, width, height);
    }

}