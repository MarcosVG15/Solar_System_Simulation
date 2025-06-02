package Model;

import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.AstralObject;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Vector;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.SpaceObject;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Planets extends Sphere {
    private SpaceObject object;
    private String texturePath;
    private double scaleFactorForSun = 1e-7; // it's used to calculate radius of the sun with an appropriate size for simulation
    private double scaleFactorForPlanets = 2e-7; //it's used to calculate radius(3 times of an actual size for better visualization)
    
    public Planets(SpaceObject object, String texture) {
        this.object = object;
        this.texturePath = texture;
        initiateVisualRepresentation();
    }

    private void initiateVisualRepresentation(){
        super.setRadius(calculateRadius(object));
        PhongMaterial material = new PhongMaterial();
        try {
        Image texture = new Image(getClass().getResource(texturePath).toExternalForm());
;
        if (texture.isError()) {
            throw new IllegalArgumentException("Texture loading error");
        }
        material.setDiffuseMap(texture);
        } catch(Exception e){
            material.setDiffuseColor(javafx.scene.paint.Color.RED);
            System.err.println("Failed to load texture: " + texturePath);
        }
        this.setMaterial(material);
    }

    public void updatePosition(){
        Vector position = (Vector) object.getPositionVector();
        //System.out.println("Position for " + getName() + ": " + position.getX()  + ", " + position.getY()  + ", " + position.getZ());
        
        this.setTranslateX(position.getX() * 1e-5);
        this.setTranslateY(position.getY() * 1e-5);
        this.setTranslateZ(position.getZ() * 1e-5);
    }

    public double calculateRadius(SpaceObject obj){
        if(object.getName().equalsIgnoreCase("sun")){
            double radius = Math.cbrt(object.getMass()) * scaleFactorForSun;
            return Math.min(Math.max(radius, 2.0), 50.0);
        }
        double radius = Math.cbrt(object.getMass()) * scaleFactorForPlanets;
        return Math.min(Math.max(radius, 2.0), 50.0);
    }

    public SpaceObject getSpaceObjectOf() {
        return object;
    }

    public String getName() {
        return object.getName();
    }
}
