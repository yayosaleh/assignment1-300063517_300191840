/* This class tests the performance (method runtime in ms) of the 5 PointCP-based designs. */

import java.util.*; 

public class PointCPPerformanceTest {
    
    //Number of iterations and trials (control variables)
    private static int numIterations = 5000000; 
    private static int numTrials = 10; 
    
    //Parameters getDistance() and rotatePoint() methods (control variables)
    private static Point pointB = new PointCP('C', 0, 0);
    private static double rotation = 90.0;

    //Mappings for design and method names
    private static Map<Integer, String> designNames = Map.of(
        0, "PointCP",
        1, "PointCP2",
        2, "PointCP3",
        3, "PointCP5 (design 2)",
        4, "PointCP5 (design 3)"
    );
    private static Map<Integer, String> methodNames = Map.of(
        1, "getX()",
        2, "getY()",
        3, "getRho()",
        4, "getTheta()",
        5, "getDistance()",
        6, "rotatePoint()",
        7, "toString()"
    );

    public static void main(String[] args) {
        
        //Instances of different designs stored in Point array
        Point[] points = new Point[5];
        points[0] = new PointCP('C', 5.0, 10.0);
        points[1] = new PointCP2('C', 5.0, 10.0);
        points[2] = new PointCP3('C', 5.0, 10.0);
        points[3] = new PointCP52('C', 5.0, 10.0);
        points[4] = new PointCP53('C', 5.0, 10.0);
        
        //Loop to test each design
        for (int i = 0; i < 5; i++)
        {
            //Print which design is being tested
            System.out.println("*** " + designNames.get(i) + " method runtimes (ms) ***");
            
            //Loop to test each method
            for (int j = 1; j < 8; j++)
            {
                //AL to hold results from each trial
                ArrayList<Long> trials = new ArrayList<>();
                
                //Loop to do each trial
                for (int k = 0; k < numTrials; k++)
                {
                    trials.add(testMethodRuntime(j, points[i]));
                }

                //Print results
                System.out.println(methodNames.get(j) + ":");
                System.out.println("Max: " + Collections.max(trials));
                System.out.println("Min: " + Collections.min(trials));
                System.out.println("Avg: " + getAvg(trials) + "\n");
            }

            System.out.println();
        }
    }

    private static long testMethodRuntime(int flag, Point point)
    {   
        long start = System.currentTimeMillis();

        for (int i = 0; i < numIterations; i++)
        {
            //Switch introduces overhead! Simple but inefficient
            switch (flag)
            {
                case 1:
                    point.getX();
                    break; 
                case 2:
                    point.getY();
                    break;
                case 3: 
                    point.getRho();
                    break;
                case 4:
                    point.getTheta();
                    break;
                case 5:
                    point.getDistance(pointB);
                    break;
                case 6:
                    point.rotatePoint(rotation);
                    break; 
                case 7:
                    point.toString();
                    break;
                default:
                    System.out.println("Invalid flag. Please provide int flag in range [1-7]!");
                    break;
            }
        }

        long finish = System.currentTimeMillis();
        return (finish - start);

    }

    private static long getAvg(ArrayList<Long> al)
    {
        long sum = 0;
        for(long elem : al)
        {
            sum += elem;
        }
        return sum/al.size();
    }
    
}
