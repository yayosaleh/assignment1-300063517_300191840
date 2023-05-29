/* This class is an adaptation of PointCP (design 1) which only stores coordinates in cartesian form and computes polar coordinates on demand. */

public class PointCP3 implements Point
{
  //Instance variables ************************************************

  //Horizontal and vertical coordinate instance variables that define point
  private double x;
  private double y;
  
  //Constructors ******************************************************

    //Retained 'type' argument in constructor, allowing users to pass cartesian or polar coordinates
    public PointCP3(char type, double xOrRho, double yOrTheta)
    {
      if(type != 'C' && type != 'P')
        throw new IllegalArgumentException();
      if (type == 'C'){
        this.x = xOrRho;
        this.y = yOrTheta;
      } else {
        this.x = (Math.cos(Math.toRadians(yOrTheta)) * xOrRho);
        this.y = (Math.sin(Math.toRadians(yOrTheta)) * xOrRho);
      }
    }
	
  //Instance methods **************************************************
 
  //Computes and returns point's x-coordinate 
  public double getX()
  {
    return x; 
  }
  
  //Computes and returns point's y-coordinate 
  public double getY()
  {
    return y;
  }
  
  //Returns point's radius
  public double getRho()
  {
    return (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
  }
  
  //Returns point's angle
  public double getTheta()
  {
    return Math.toDegrees(Math.atan2(y, x));
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
  public PointCP3 rotatePoint(double rotation)
  {
    double radRotation = Math.toRadians(rotation);
    double X = getX();
    double Y = getY();
        
    return new PointCP3('C',
      (Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
      (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
  }

  //Returns information about the coordinates in both cartesian and polar form
  public String toString()
  {
    return "As Cartesian (" + getX() + "," + getY() + ")" + "\n" + "As Polar [" + getRho() + "," + getTheta() + "]" + "\n";
  }
}