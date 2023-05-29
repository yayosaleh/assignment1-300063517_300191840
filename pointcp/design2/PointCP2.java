/* This class is an adaptation of PointCP (design 1) which only stores coordinates in polar form and computes cartesian coordinates on demand. */

public class PointCP2 implements Point
{
  //Instance variables ************************************************

  //Radius and angle instance variables that define point
  private double rho;
  private double theta;
  
  //Constructors ******************************************************

  //Retained 'type' argument in constructor, allowing users to pass cartesian or polar coordinates
  public PointCP2(char type, double xOrRho, double yOrTheta)
  {
    if(type != 'C' && type != 'P')
      throw new IllegalArgumentException();
    if (type == 'C'){
      this.rho = Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2));
      this.theta = Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
    } else {
      this.rho = xOrRho;
      this.theta = yOrTheta;
    }
  }
	
  //Instance methods **************************************************
 
  //Computes and returns point's x-coordinate 
  public double getX()
  {
    return (Math.cos(Math.toRadians(theta)) * rho);
  }
  
  //Computes and returns point's y-coordinate 
  public double getY()
  {
    return (Math.sin(Math.toRadians(theta)) * rho);
  }
  
  //Returns point's radius, rho
  public double getRho()
  {
    return rho;
  }
  
  //Returns point's angle, theta
  public double getTheta()
  {
    return theta;
  }
  
  //Calculates distance between current and passed point using the Pythagorean theorem
  public double getDistance(Point pointB)
  {
    // Obtain differences in X and Y, sign is not important as these values
    // will be squared later.
    double deltaX = getX() - pointB.getX();
    double deltaY = getY() - pointB.getY();
    
    return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
  }

  //Rotates the specified point by the specified number of degrees
  public PointCP2 rotatePoint(double rotation)
  {
    double radRotation = Math.toRadians(rotation);
    double X = getX();
    double Y = getY();
        
    return new PointCP2('C',
      (Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
      (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
  }

  //Returns information about the coordinates in both cartesian and polar form
  public String toString()
  {
    return "As Cartesian (" + getX() + "," + getY() + ")" + "\n" + "As Polar [" + getRho() + "," + getTheta() + "]" + "\n";
  }
}