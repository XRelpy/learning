/*************************************************************************
	> File Name: Line.java
	> Author: 
	> Mail: 
	> Created Time: 2017年07月26日 星期三 09时38分48秒
 ************************************************************************/

public class Line implements Cloneable {
    private Point la;
    private Point lb;
    
    private boolean isXLine = false;
    // 描述线的特性
    private float k;
    private float b;
    private float xline;
    private float length = 0f;

    public Line(Point a, Point b) {
        la = new Point(a.getX(), a.getY());
        lb = new Point(b.getX(), b.getY());
        if ((a.getX() == b.getX()) && (a.getY() == b.getY())) {
            System.out.println("Not a Line, is a Point");
        } else {
            lineType();
        } 
    }

    private void lineType() {
        if (la.getX() == lb.getX()) {
            isXLine = true;
            xline = la.getX();
            //System.out.println("Test");
            length = Math.abs(la.getY() - lb.getY());
        } else {
            isXLine = false;
            float x = (la.getX() - lb.getX()) * (la.getX() - lb.getX());
            float y = (la.getY() - lb.getY()) * (la.getY() - lb.getY());
            //System.out.println("Test:" + x + " -- " + y);
            length = (float)Math.sqrt((x + y)); 
            k = (lb.getY() - la.getY()) / (lb.getX() - la.getX());
            b = la.getY() - k * la.getX();
        }
    }  

    private float distance(Point a, Point b) {
        float x = (a.getX() - b.getX()) * (a.getX() - b.getX());
        float y = (a.getY() - b.getY()) * (a.getY() - b.getY());

        return (float)(Math.sqrt(x + y));
    }

    private float distance(float x1, float y1, float x2, float y2) {
        float x = (x1 - x2) * (x1 - x2);
        float y = (y1 - y2) * (y1 - y2);

        return (float)(Math.sqrt(x + y));
    }

    public float getLineLength() {
        return length;
    }

    //No direction, from a -> b
    public Point getRatePoint(float rate) {
        Point p;
        if (rate > 1f || rate < 0f) {
            p = new Point();
            return p;
        }
        if (rate == 0f || rate == 1f) {
            if (rate == 0f) {
                p = new Point(la.getX(), la.getY());
            } else {
                p = new Point(lb.getX(), lb.getY());
            }
        } else if (isXLine) {
            float y = rate * (lb.getY() - la.getY()) + la.getY();
            p = new Point(xline, y);
        } else {
           float x = rate * (lb.getX() - la.getX()) + la.getX();
           float y = k * x + b;

           p = new Point(x, y);
        }
        return p;
    }
    
    protected Object clone() throws CloneNotSupportedException {
        Line line = (Line) super.clone();
        return line;
    }
    
    public Point getStartPoint() {
        return la;
    }

    public Point getEndPoint() {
        return lb;
    }
}
