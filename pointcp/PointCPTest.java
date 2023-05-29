/* This is a modified version of PointCPTest adapted to test all 5 PointCP-based designs. */

import java.io.*;

public class PointCPTest
{

  public static void main(String[] args)
  {
    /*
     * CHANGE: instead of creating and testing a single instance of PointCP,
     * we populate an array of type Point (interface) containing instances of all
     * 5 PointCP-based designs, and test each of those instances.
     */

    Point[] points = new Point[5];

    System.out.println("Cartesian-Polar Coordinates Conversion Program");

    // Check if the user input coordinates from the command line
    // If he did, create the PointCP object from these arguments.
    // If he did not, prompt the user for them.
    try
    {
      //CHANGE: helper method createPoints() called instead of creating new PointCP instance
      points = createPoints(args[0].toUpperCase().charAt(0), 
        Double.valueOf(args[1]).doubleValue(), 
        Double.valueOf(args[2]).doubleValue());
    }
    catch(Exception e)
    {
      // If we arrive here, it is because either there were no
      // command line arguments, or they were invalid
      if(args.length != 0)
        System.out.println("Invalid arguments on command line");
      try
      {
        points = getInput();
      }
      catch(IOException ex)
      {
        System.out.println("Error getting input. Ending program.");
        return;
      }
    }
    //CHANGE: each design's methods are tested instead of just PointCP; conversion tests omitted (not applicable)
    System.out.println("\n***Testing PointCP*** \n");
    testPointMethods(points[0]);
    System.out.println("***Testing PointCP2*** \n");
    testPointMethods(points[1]);
    System.out.println("***Testing PointCP3*** \n");
    testPointMethods(points[2]);
    System.out.println("***Testing PointCP5 (design 2)*** \n");
    testPointMethods(points[3]);
    System.out.println("***Testing PointCP5 (design 3)*** \n");
    testPointMethods(points[4]);
  }

  //ADDED: used to create array of points from command line arguments
  private static Point[] createPoints(char coordType, double a, double b)
  {
    Point[] points = new Point[5];
    points[0] = new PointCP(coordType, a, b);
    points[1] = new PointCP2(coordType, a, b);
    points[2] = new PointCP3(coordType, a, b);
    points[3] = new PointCP52(coordType, a, b);
    points[4] = new PointCP53(coordType, a, b);
    return points; 
  }

  //CHANGE: returns array of points (instances of different designs) rather than single PointCP instance
  private static Point[] getInput() throws IOException
  {
    byte[] buffer = new byte[1024];  //Buffer to hold byte input
    boolean isOK = false;  // Flag set if input correct
    String theInput = "";  // Input information
    
    //Information to be passed to the constructor
    char coordType = 'A'; // Temporary default, to be set to P or C
    double a = 0.0;
    double b = 0.0;

    // Allow the user to enter the three different arguments
    for (int i = 0; i < 3; i++)
    {
      while (!(isOK))
      {
        isOK = true;  //flag set to true assuming input will be valid
          
        // Prompt the user
        if (i == 0) // First argument - type of coordinates
        {
          System.out.print("Enter the type of Coordinates you "
            + "are inputting ((C)artesian / (P)olar): ");
        }
        else // Second and third arguments
        {
          System.out.print("Enter the value of " 
            + (coordType == 'C' 
              ? (i == 1 ? "X " : "Y ")
              : (i == 1 ? "Rho " : "Theta ")) 
            + "using a decimal point(.): ");
        }

        // Get the user's input      
       
        // Initialize the buffer before we read the input
        for(int k=0; k<1024; k++)
        	buffer[k] = '\u0020';        
             
        System.in.read(buffer);
        theInput = new String(buffer).trim();
        
        // Verify the user's input
        try
        {
          if (i == 0) // First argument -- type of coordinates
          {
            if (!((theInput.toUpperCase().charAt(0) == 'C') 
              || (theInput.toUpperCase().charAt(0) == 'P')))
            {
              //Invalid input, reset flag so user is prompted again
              isOK = false;
            }
            else
            {
              coordType = theInput.toUpperCase().charAt(0);
            }
          }
          else  // Second and third arguments
          {
            //Convert the input to double values
            if (i == 1)
              a = Double.valueOf(theInput).doubleValue();
            else
              b = Double.valueOf(theInput).doubleValue();
          }
        }
        catch(Exception e)
        {
        	System.out.println("Incorrect input");
        	isOK = false;  //Reset flag as so not to end while loop
        }
      }

      //Reset flag so while loop will prompt for other arguments
      isOK = false;
    }
    
    //CHANGE: array of points (containing instances of different designs) returned rather than single PointCP instance 
    Point[] points = new Point[5];
    points[0] = new PointCP(coordType, a, b);
    points[1] = new PointCP2(coordType, a, b);
    points[2] = new PointCP3(coordType, a, b);
    points[3] = new PointCP52(coordType, a, b);
    points[4] = new PointCP53(coordType, a, b);
    return points; 

  }

  //ADDED: method to test all Point methods (except for getters which implicitly tested)
  private static void testPointMethods(Point point){
    PointCP pointB = new PointCP('C', 0, 0);
    double rotation = 90.0; 
    System.out.println(point.toString());
    System.out.println("Distance from origin (0,0): " + point.getDistance(pointB));
    System.out.println("After 90 degree rotation: \n" + point.rotatePoint(rotation).toString());
  }

}