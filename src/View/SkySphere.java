package View;

import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Sphere;

public class SkySphere extends Sphere{
    public SkySphere(double radius, String texturePath){
        super(radius);
        this.setCullFace(CullFace.NONE); // To show the inner part of the sphere.

        PhongMaterial material = new PhongMaterial();
        material.setSelfIlluminationMap(new Image(texturePath));
        material.setDiffuseMap(new Image(texturePath));
        this.setMaterial(material);
    }

}
