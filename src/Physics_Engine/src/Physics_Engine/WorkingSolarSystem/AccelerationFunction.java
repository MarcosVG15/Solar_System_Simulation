package Physics_Engine.src.Physics_Engine.WorkingSolarSystem;




import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.function;
import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.vectorInterface;

import java.util.ArrayList;

public class AccelerationFunction implements function {
    public static final double G = 6.67430e-11;


    @Override
    public vectorInterface computeDerivative(int planet, vectorInterface VectorPosition, ArrayList< AstralObject> solarSystem){
        double [] accelerationValues = new double[3];


        for(int i = 0 ; i<accelerationValues.length ;i++){

            double summation = 0;

            for(int j = 0 ; j<solarSystem.size();j++){
                if(j==planet){
                    continue;
                }
                AstralObject current = solarSystem.get(j);

                double modulus = getModulus(VectorPosition,current.getPositionVector());
                double MassDividedModulus = current.getMass()/Math.pow(Math.abs(modulus),3);

                double[] planetAPosition = VectorPosition.getVector();
                double[] currentPosition = current.getPositionVector().getVector();

                summation+= MassDividedModulus*(currentPosition[i]-planetAPosition[i]);

            }
            accelerationValues[i] = G*summation;

        }

        Vector acceleration = new Vector(accelerationValues[0],accelerationValues[1],accelerationValues[2]);

        return acceleration;
    }

    private double getModulus(vectorInterface v1 , vectorInterface v2){
        double modulus = 0;
        double[] valueV1 = v1.getVector();
        double[] valueV2 = v2.getVector();

        double differenceSum = 0 ;

        for(int i = 0 ; i<valueV2.length ; i++){

            differenceSum+=Math.pow(valueV1[i]-valueV2[i],2);

        }

        modulus = Math.sqrt(differenceSum);

        return modulus;
    }


}
