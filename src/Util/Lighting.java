package Util;
import javafx.scene.AmbientLight;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;

public class Lighting {

    public static AmbientLight createAmbientLight() {
        return new AmbientLight(Color.WHITE);
    }

    public static PointLight createPointLight(double x, double y, double z) {
        PointLight light = new PointLight(Color.WHITE);
        light.setTranslateX(x);
        light.setTranslateY(y);
        light.setTranslateZ(z);
        return light;
    }
}
