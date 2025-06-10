# Solar_System_Simulation
This projects simulates the solar system together with rocket launch to Titan, moon of Saturn. <br>
Mission starts on April 1st 2025, then the simulation chooses best conditions to launch the rocket.<br>
The Newton's gravitational force is implemented to ensure correct behavior of planets. <br>
All the physics is put together with GUI ensuring high level of detail.

## Getting started
- Go to .vscode directory inside the project
- Open up launch.json
- Find "vmArgs" arguments and inside each of them change and add filepath to your javaFX lib location
- like this "--module-path \"\<input your filepath here>" --add-modules javafx.controls,javafx.fxml"

  ![AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA](https://github.com/user-attachments/assets/de67271d-d386-46ab-91b2-4c10f43a6829)


Finally, run [Main.java](./src/Main.java) to start the simulation. 

## Physics Engine
All inner works of the [Physics Engine](./src/Physics_Engine) is located here. <br>
It consists of:
- Runge-Kutta 4th order solver 
- Differential equations
- Simulated objects
- Rocket behavior logic
This toghether produces output that is passed by to GUI.

To test the physics engine [TEST.java](./src/Physics_Engine/ODESolverRK4/TEST.java) puts everything togheter.

For rocket mission test enviorment please look at [TEST.java](./src/Physics_Engine/RocketMissson/TEST.java).

Finally, for probe mission testing there is a file [TEST.java](./src/Physics_Engine/ProbeMission/TEST.java).

## GUI
The heart of application lies in [Controller](./src/Controller) all the resources from [Util](./src/Util), [Resources](./src/Resources), [View](./src/View) and builds the application. <br>
Enables customization of the scene and uses [Physics Engine](./src/Physics_Engine) to update its objects.




## Testing 
For tests please visit this [project](https://github.com/MarcosVG15/Physics-Engine---Orbital-Dynamics), which includes all physics engine related code. <br>
Code coverage report can be found in [here](./code_coverage) <br>
Proof of all the test passing can be found [here](./code_coverage/testResults.md).



## Project Memebers
- Keci Francisco Chilala
- Yalçin Karakurum
- Krzysztof Koszewnik
- Oleksandr Trutenko Trutenko
- Marcos Vargas Garcia
- Alexander Weinberger





