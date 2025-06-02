package Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces;

public interface SpaceObject {

    public vectorInterface getVelocityVector();
    public vectorInterface getPositionVector();

    public void setVelocity(vectorInterface v);
    public void setPosition(vectorInterface v);

    public void setName(String name);
    public void print();
    public double getMass();
    public String getName() ;

    public boolean hasHitPlanet(SpaceObject astralObject , double Radius);
}
