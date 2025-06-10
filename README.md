# Solar_System_Simulation
This projects simulates the solar system together with rocket launch to Titan, moon of Saturn. 
Mission starts on April 1st 2025, then the simulation chooses best conditions to launch the rocket.
The Newton's gravitational force is implemented to ensure correct behavior of planets. 
All the physics is put together with GUI ensuring high level of detail.

## Getting started
Run [Main.java](./src/Main.java) to start the simulation. 

## Physics Engine
All inner works of the [Physics Engine](./src/Physics_Engine) is located here. 
It consists of:
- Runge-Kutta 4th order solver 
- Differential equations
- Simulated objects
- Rocket behavior logic
This toghether produces output that is passed by to GUI.

To test the physics engine [TEST.java](./src/Physics_Engine/src/Physics_Engine/WorkingSolarSystem/TEST.java) puts everything togheter.

## GUI
The heart of application lies in [Controller](./src/Controller) all the resources from [Util](./src/Util), [Resources](./src/Resources), [View](./src/View) and builds the application.
Enables customization of the scene and uses [Physics Engine](./src/Physics_Engine) to update its objects.

## Project Memebers
- Keci Francisco Chilala
- Yalçin Karakurum
- Krzysztof Koszewnik
- Oleksandr Trutenko Trutenko
- Marcos Vargas Garcia
- Alexander Weinberger





