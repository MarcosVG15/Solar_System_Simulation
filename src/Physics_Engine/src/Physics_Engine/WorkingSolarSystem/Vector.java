package Physics_Engine.src.Physics_Engine.WorkingSolarSystem;


import Physics_Engine.src.Physics_Engine.WorkingSolarSystem.Interfaces.vectorInterface;

/**
 * Allows for opperations and copy of vectors ;
 */
public class Vector implements vectorInterface {

    private double[] values ;

    /**
     *
     * @param x - position in x plane
     * @param y - position in y plane
     * @param z - position in z plane
     */
    public Vector(double x , double  y , double z){
        this.values = new double[]{x, y, z};;

    }

    @Override
    public double[] getVector() {
        return values;
    }

    /**
     * computes the norm of the current vector
     * @return - the norm of the vector
     */
    @Override
    public double computeNorm() {
        double sum = 0;

        for (double value : values) {
            sum += Math.pow(value,2);
        }

        return Math.sqrt(sum);
    }

    /**
     * copies the vector without passing by reference
     * @param v - vector that we want to copy
     */
    @Override
    public void setVector(vectorInterface v) {
        values[0] = v.getX();
        values[1] = v.getY();
        values[2] = v.getZ();
    }

    public void print(String  type ){
        System.out.printf(type+ "Vector : x : %f , | y : %f , | z : %f \n"
                , values[0] , values[1], values[2]);
    }




    @Override
    public double getX() {
        return values[0];
    }

    @Override
    public double getY() {
        return values[1];
    }

    @Override
    public double getZ() {
        return values[2];
    }



}
