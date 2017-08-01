/*************************************************************************
	> File Name: Test.java
	> Author: 
	> Mail: 
	> Created Time: 2017年07月26日 星期三 09时56分41秒
 ************************************************************************/

public class Test {
    //绘制贝塞尔曲线
    public static void main(String[] args){
        Point a = new Point(50, 100);
        Point b = new Point(150, 200);
        Point c = new Point(300, 150);
        Line l = new Line(a, b);
        Line l2 = new Line(c, b);

        System.out.println(l.getLineLength());
        
        System.out.println(l.getRatePoint(0.5f).getX());

        BSE2Line bse2 = new BSE2Line(l, l2);
        bse2.Compute();
    }   
}
