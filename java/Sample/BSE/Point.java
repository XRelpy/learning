/*************************************************************************
	> File Name: Point.java
	> Author: 
	> Mail: 
	> Created Time: 2017年07月26日 星期三 09时33分27秒
 ************************************************************************/

public class Point {
    private float x;
    private float y;

    public Point() {
        x = 0f;
        y = 0f;
    };
    
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

