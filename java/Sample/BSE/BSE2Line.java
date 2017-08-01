/*************************************************************************
	> File Name: BSE2Line.java
	> Author: 
	> Mail: 
	> Created Time: 2017年07月26日 星期三 11时44分58秒
 ************************************************************************/

import java.io.PrintWriter;
import java.io.FileWriter;
public class BSE2Line {
    private Line ab;
    private Line bc;
    private Point a;
    private Point b;
    private Point c;
    public BSE2Line(Line l1, Line l2) {
        try {
            Line ll1 = (Line)l1.clone();
            Line ll2 = (Line)l2.clone();
            initPoint(l1, l2);
        } catch (Exception ex) {

        }
    }

    private void initPoint(Line l1, Line l2) {
        Point temp1 = new Point(l1.getStartPoint().getX(), l1.getStartPoint().getY());
        Point temp2 = new Point(l1.getEndPoint().getX(), l1.getEndPoint().getY());
        Point temp3 = new Point(l2.getStartPoint().getX(), l2.getStartPoint().getY());
        Point temp4 = new Point(l2.getEndPoint().getX(), l2.getEndPoint().getY());
        
        if (checkPointSame(temp1, temp3)) {
            a = new Point(temp2.getX(), temp2.getY());
            b = new Point(temp1.getX(), temp1.getY());
            c = new Point(temp4.getX(), temp4.getY());
        } else if (checkPointSame(temp1, temp4)) {
            a = new Point(temp2.getX(), temp2.getY());
            b = new Point(temp1.getX(), temp1.getY());
            c = new Point(temp3.getX(), temp3.getY());
        } else if (checkPointSame(temp2, temp3)) {
            a = new Point(temp1.getX(), temp1.getY());
            b = new Point(temp2.getX(), temp2.getY());
            c = new Point(temp4.getX(), temp4.getY());
        } else if (checkPointSame(temp2, temp4)) {
            a = new Point(temp1.getX(), temp1.getY());
            b = new Point(temp2.getX(), temp2.getY());
            c = new Point(temp3.getX(), temp3.getY());
        }

        if (checkPointSame(a, c)) {
            System.out.println("Error: Line 1 the same as Line2");
        } else {
            ab = new Line(a, b);
            bc = new Line(b, c);
        }
    }

    private boolean checkPointSame(Point a1, Point a2) {
        if ((a1.getX() == a2.getX()) && (a1.getY() == a2.getY())) {
            return true;
        } else {
            return false;
        }
    }

    public void Compute() {
        //System.out.println("----X-------Y----");
        try {
            PrintWriter out = new PrintWriter(new FileWriter("Bsizer.log"), true);
            for (int i = 0; i < 1000; i++) {
                float rate = (float) i / 1000f;
                Point p1 = ab.getRatePoint(rate);
                Point p2 = bc.getRatePoint(rate);
                //System.out.println("l1:" + (ab.getEndPoint().getX()));
                //System.out.println("P1:" + (p1.getX()) + " -- " + (p1.getY()) + " -- " + rate);
                Point p3 = (new Line(p1, p2).getRatePoint(rate));
                out.printf("%8.2f,%8.2f\n",(p3.getX()), (p3.getY()));
            }
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }   
    }
}
