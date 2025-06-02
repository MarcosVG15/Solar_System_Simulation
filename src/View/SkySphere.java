package View;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;

public class SkySphere extends Sphere{
    public SkySphere(double radius) {
        super(radius);
        this.setCullFace(CullFace.NONE);

        PhongMaterial material = new PhongMaterial();
        Image texture = new Image(getClass().getResource("/Resources/8k_stars_milky_way.jpg").toExternalForm());
        material.setSelfIlluminationMap(texture);
        material.setDiffuseMap(texture);

        this.setMaterial(material);
    }
}

