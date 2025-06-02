package Controller;


import java.util.ArrayList;

import Model.Planets;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.AccelerationFunction;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.AstralObject;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.RK4_ODESolver;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.SolarSystem;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.VelocityFunction;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Animation {
    private SolarSystem solarSystem;
    private AccelerationFunction acceleration = new AccelerationFunction();
    private VelocityFunction velocity = new VelocityFunction();
    private RK4_ODESolver odeSolver = new RK4_ODESolver();
    private ArrayList<Planets> planets;

    public Animation(ArrayList<Planets> planets, SolarSystem solarSystem) {
        this.planets = planets;
        this.solarSystem = solarSystem;
    }

    public void animationStart(Group groupScene){
        new AnimationTimer() {
            private long lastTime = 0;
            private double timeAccumulator = 0;
            private double totalSimulatedTime = 0;
            private double timeStep = 0.01; // 10 milliseconds per step
            private double defaultSimulationSpeed = 1.0; // Normal speed(can be used to slow down the simulation by user's choice in a future)
            private double maximumTime = (60 * 60 * 24 * 365); // Simulate one year(60 * 60 * 24 * 365)
            private double simulationSpeed = maximumTime / 120; // Speed up the simulation so it runs in 2 minutes
            private double maxPhysicsUpdateRate = 200; // Maximum physics updates per second

            @Override
            public void handle(long now){
                if(lastTime == 0){
                    lastTime = now;
                    return;
                }

                double deltaTime = (now - lastTime) / 1e9; // Convert nanoseconds to seconds
                lastTime = now;
                timeAccumulator += deltaTime * simulationSpeed; // Scale time by simulation speed

                double amountOfSteps = 0; // Reset steps for each frame(Helps with the animation freezing issue)
                 while (timeAccumulator >= timeStep && totalSimulatedTime < maximumTime && amountOfSteps < maxPhysicsUpdateRate) { 
                    updatePhysics(timeStep);
                    timeAccumulator -= timeStep;
                    totalSimulatedTime += timeStep;
                    amountOfSteps++;
                }
                System.out.println("Total simulated time: " + totalSimulatedTime + " seconds");
                updateVisuals(); // one call per frame to update visuals
            }
            private void updatePhysics(double timeStep) {
                odeSolver.ComputeODE(timeStep, solarSystem, acceleration, velocity);
            }

           private void updateVisuals() {
            for (Planets planet : planets) {
                planet.updatePosition();
            }
}
        }.start();
    }
}
