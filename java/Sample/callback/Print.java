/*************************************************************************
	> File Name: Print.java
	> Author: 
	> Mail: 
	> Created Time: 2017年06月06日 星期二 10时18分38秒
 ************************************************************************/
import java.util.HashMap;

public class Print implements Fun.CallBack {
    private HashMap<String, Boolean> hash = new HashMap<String, Boolean>();
    public void test1(){
        System.out.println("test1");
    }

    Fun f = new Fun();
    public void init() {
        f.setListener(this);
    }

    public void run() {
        f.test();
        String v = "Test";
        hash.put(v,true);
        System.out.println("1:" + hash.get(v));
        hash.put(v, false);
        System.out.println("2:" + hash.get(v));
        synchronized(hash) {
            System.out.println("Test");
        }
    }
}
