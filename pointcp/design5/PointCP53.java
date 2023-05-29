/* 
 * This class is an adaptation of PointCP (design 1) which only stores coordinates in cartesian form and computes polar coordinates on demand. 
 * It inherits from the PointCP5 superclass. 
*/


public class PointCP53 extends PointCP5
{
  //Instance variables ************************************************

  //Horizontal and vertical coordinate instance variables that define point
  private double x;
  private double y;
  
  //Constructors ******************************************************

    //Retained 'type' argument in constructor, allowing users to pass cartesian or polar coordinates
    public PointCP53(char type, double xOrRho, double yOrTheta)
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
  
  //Rotates the specified point by the specified number of degrees
  public PointCP53 rotatePoint(double rotation)
  {
    double[] rotatedCoords = getRotatedCoordinates(rotation);        
    return new PointCP53('C', rotatedCoords[0], rotatedCoords[1]);
  }
  
}