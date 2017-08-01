/*************************************************************************
	> File Name: Fun.java
	> Author: 
	> Mail: 
	> Created Time: 2017年06月06日 星期二 10时18分48秒
 ************************************************************************/

public class Fun {
    private CallBack cb;
    public void setListener(CallBack t) {
        cb = t;
    }

    public void test() {
        cb.test1();
    }

    interface CallBack {
        void test1();
    }
}
