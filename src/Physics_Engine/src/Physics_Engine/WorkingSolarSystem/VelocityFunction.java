package Physics_Engine.src.Physics_Engine.WorkingSolarSystem;


import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.SpaceObject;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.function;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.vectorInterface;

import java.util.ArrayList;

public class VelocityFunction implements function {
    @Override
    public vectorInterface computeDerivative(int planet, vectorInterface Vector, ArrayList<SpaceObject> solarSystem) {
        return Vector;
    }
}
