package Model;

import Physics_Engine.src.Physics_Engine.SolarSystem.AstralObject;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Planets extends Sphere {
    private AstralObject object;
    private String texturePath;
    private double scaleFactorForSun = 1e-7; // it's used to calculate radius of the sun with an appropriate size for simulation
    private double scaleFactorForPlanets = 2e-7; //it's used to calculate radius(3 times of an actual size for better visualization)
    private double mass;
    
    public Planets(double x , double y , double z , double Vx, double Vy, double Vz , double Mass, String name, String texture) {
        this.mass = Mass;
        this.object = new AstralObject(x, y, z, Vx, Vy, Vz, Mass, name);
        double radius = calculateRadius(object);
        super.setRadius(radius);
        this.texturePath = texture;

        PhongMaterial textures = new PhongMaterial();
        textures.setDiffuseMap(new Image(texturePath));
        this.setMaterial(textures);
    } 

    public void updatePosition(double scale){
        double[] coords = object.getCoordinates();
        double visualScale = scale * 10; 
        this.setTranslateX(coords[0] * visualScale);
        this.setTranslateY(coords[1] * visualScale);
        this.setTranslateZ(coords[2] * visualScale);
    }

    public double calculateRadius(AstralObject obj){
        if(obj.getName().toLowerCase().equals("sun")){
            double radius = Math.cbrt(mass) * scaleFactorForSun;
            return Math.min(Math.max(radius, 2.0), 50.0);
        }
        double radius = Math.cbrt(mass) * scaleFactorForPlanets;
        return Math.min(Math.max(radius, 2.0), 50.0);
    }

    public double getMass(){
        return mass;
    }

    public AstralObject getAstralObject() {
        return object;
    }

    public String getName() {
        return object.getName();
    }
}