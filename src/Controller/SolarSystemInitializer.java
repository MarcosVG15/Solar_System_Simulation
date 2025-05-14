package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Planets;
import Physics_Engine.src.Physics_Engine.SolarSystem.AstralObject;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * The {@code SolarSystemInitializer} class is responsible for initializing and setting up
 * the solar system simulation with accurate astronomical data.
 * It creates and positions all celestial bodies with their initial conditions including
 * position, velocity, and mass.
 */
public class SolarSystemInitializer {
    /** 
     * Scaling factor for converting astronomical units to scene units.
     * Current scale is 1:10^7 to make the visualization manageable.
     */
    public static double scale = 1/1e7;

    /**
     * Initializes the solar system by creating all celestial bodies and adding them to the scene.
     * Sets up initial conditions for each planet including:
     *   Position coordinates (x, y, z)
     *   Velocity components
     *   Mass
     *   Texture mapping
     * Also prints debug information about each planet's position.
     *
     * @param group The JavaFX Group node to which all planet objects will be added
     * @return ArrayList of AstralObject instances representing all bodies in the solar system
     */
    public static ArrayList<AstralObject> initializeSystem(Group group) {
        ArrayList<AstralObject> solarSystem = new ArrayList<>();
        Planets[] eachPlanet = {
        new Planets(0,0,0,0,0,0,1.99E30, "Sun", "file:src\\Resources\\2k_sun.jpg"),//Sun
        new Planets((-5.67E7), (-3.23E+07),2.58E+06, 1.39E+01,-4.03E+01 ,-4.57E+00,3.30E+23, "Mercury", "file:src\\Resources\\2k_mercury.jpg"),//Mercury
        new Planets(-1.04E+08,-3.19E+07,5.55E+06,9.89E+00,-3.37E+01,-1.03E+00,4.87E+24, "Venus", "file:src\\Resources\\2k_venus.jpg"),//Venus
        new Planets(-1.47E+08,-2.97E+07,2.75E+04,5.31E+00,-2.93E+01,6.69E-04,5.97E+24, "Earth", "file:src\\Resources\\2k_earth_daymap.jpg"),//Earth
        new Planets(-1.47E+08,-2.95E+07,5.29E+04,4.53E+00,-2.86E+01,6.73E-02,7.35E+22, "Moon", "file:src\\Resources\\2k_moon.jpg"),//Moon
        new Planets(-2.15E+08,1.27E+08,7.94E+06,-1.15E+01,-1.87E+01,-1.11E-01,6.42E+23, "Mars", "file:src\\Resources\\2k_mars.jpg"),//Mars
        new Planets(5.54E+07,7.62E+08,-4.40E+06,-1.32E+01,1.29E+01,5.22E-02,1.90E+27, "Jupiter","file:src\\Resources\\2k_jupiter.jpg"),//Jupiter
        new Planets(1.42E+09,-1.91E+08,-5.33E+07,7.48E-01,9.55E+00,-1.96E-01,5.68E+26, "Saturn","file:src\\Resources\\2k_saturn.jpg"),//Saturn
        new Planets(1.42E+09,-1.92E+08,-5.28E+07,5.95E+00,7.68E+00,2.54E-01,1.35E+23, "Titan","file:src\\Resources\\2k_titan.jpg"),//Titan
        new Planets(1.62E+09,2.43E+09,-1.19E+07,-5.72E+00,3.45E+00,8.70E-02,8.68E+25, "Uranus","file:src\\Resources\\2k_uranus.jpg"),//Uranus
        new Planets(4.47E+09,-5.31E+07,-1.02E+08,2.87E-02,5.47E+00,-1.13E-01,1.02E+26, "Neptune", "file:src\\Resources\\2k_neptune.jpg")//Neptune
        };

        for(Planets planet : eachPlanet){
            solarSystem.add(planet.getAstralObject());
            group.getChildren().add(planet);
            planet.updatePosition(scale);

            System.out.println(planet.getName() + " position:");
            System.out.println("  X = " + planet.getTranslateX());
            System.out.println("  Y = " + planet.getTranslateY());
            System.out.println("  Z = " + planet.getTranslateZ());
        }

        return solarSystem;
    }

    /**
     * Retrieves all planet objects from the scene graph.
     * Filters through all nodes in the provided group and collects only
     * instances of the {@link Planets} class.
     *
     * @param group The JavaFX Group node containing the planet objects
     * @return ArrayList of all Planet instances in the scene
     */
    public static ArrayList<Planets> getAllPlanets(Group group){
        ArrayList<Planets> planets = new ArrayList<>();
        for(Node node : group.getChildren()){
            if(node instanceof Planets){
                planets.add((Planets) node);
            }
        }
        return planets;
    }
}
