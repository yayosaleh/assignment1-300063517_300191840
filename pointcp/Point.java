/* This interface is used to simplify testing of different PointCP-based designs. */

public interface Point {
    public double getX();
    public double getY();
    public double getRho();
    public double getTheta();
    public double getDistance(Point pointB); 
    public Point rotatePoint(double rotation);
    public String toString();
}
