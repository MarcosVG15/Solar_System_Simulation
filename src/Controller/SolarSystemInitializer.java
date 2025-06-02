package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.Planets;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.AstralObject;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.SolarSystem;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.SpaceObject;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * The {@code SolarSystemInitializer} class is responsible for initializing and setting up
 * the solar system simulation with accurate astronomical data.
 * It creates and positions all celestial bodies with their initial conditions including
 * position, velocity, and mass.
 */
public class SolarSystemInitializer {
    public static ArrayList<Planets> visualPlanets = new ArrayList<>();

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
    public static ArrayList<Planets> createVisualizationOfPlanets(SolarSystem system, Group group){
        for(SpaceObject object : system.getSolarSystem()){
            String texturePack = getTextureForPlanet(object.getName());
            System.out.println(texturePack);
            Planets planet = new Planets((SpaceObject)object, texturePack);
            planet.updatePosition();
            group.getChildren().add(planet);
            visualPlanets.add(planet);

            System.out.println(planet.getName() + " position:");
            System.out.println("  X = " + planet.getTranslateX());
            System.out.println("  Y = " + planet.getTranslateY());
            System.out.println("  Z = " + planet.getTranslateZ());
        }
        return visualPlanets;


    } 

    private static String getTextureForPlanet(String name) {
        switch(name.toLowerCase()){
            case "earth": return "/Resources/2k_earth_daymap.jpg";
            case "jupiter": return "/Resources/2k_jupiter.jpg";
            case "mars": return "/Resources/2k_mars.jpg";
            case "mercury": return "/Resources/2k_mercury.jpg";
            case "moon": return "/Resources/2k_moon.jpg";
            case "neptune": return "/Resources/2k_neptune.jpg";
            case "saturn": return "/Resources/2k_saturn.jpg";
            case "sun": return "/Resources/2k_sun.jpg";
            case "titan": return "/Resources/2k_titan.jpg";
            case "uranus": return "/Resources/2k_uranus.jpg";
            case "venus": return "/Resources/2k_venus.jpg";
        }
        return "No texture found for " + name;
}
}
 
