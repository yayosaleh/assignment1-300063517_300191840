/* This an abstract adaptation of PointCP (design 1) */

public abstract class PointCP5 implements Point {
    
    //Abstract methods **************************************************

    public abstract double getX();
    public abstract double getY();
    public abstract double getRho();
    public abstract double getTheta();
    public abstract PointCP5 rotatePoint(double rotation);
    
    //Concrete methods **************************************************

    //Calculates distance between current and passed point using the Pythagorean theorem
    public double getDistance(Point pointB)
    {
        double deltaX = getX() - pointB.getX();
        double deltaY = getY() - pointB.getY();   
        return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }

    //Returns coordinates of point (cartesian) after performing rotation for use by subclasses
    protected double[] getRotatedCoordinates(double rotation)
    {
        double radRotation = Math.toRadians(rotation);
        double X = getX();
        double Y = getY();
        double[] rotatedCoords = {(Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
            (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y)};
        return rotatedCoords;
    }

    //Returns information about the coordinates in both cartesian and polar form
    public String toString()
    {
        return "As Cartesian (" + getX() + "," + getY() + ")" + "\n" + "As Polar [" + getRho() + "," + getTheta() + "]" + "\n";
    }
}
