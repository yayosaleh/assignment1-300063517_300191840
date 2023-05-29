/* 
 * This class is an adaptation of PointCP (design 1) which only stores coordinates in polar form and computes cartesian coordinates on demand. 
 * It inherits from the PointCP5 superclass. 
*/

public class PointCP52 extends PointCP5
{
  //Instance variables ************************************************

  //Radius and angle instance variables that define point
  private double rho;
  private double theta;
  
  //Constructors ******************************************************

  //Retained 'type' argument in constructor, allowing users to pass cartesian or polar coordinates
  public PointCP52(char type, double xOrRho, double yOrTheta)
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
  
  //Rotates the specified point by the specified number of degrees
  public PointCP52 rotatePoint(double rotation)
  {
    double[] rotatedCoords = getRotatedCoordinates(rotation);        
    return new PointCP52('C', rotatedCoords[0], rotatedCoords[1]);
  }

}