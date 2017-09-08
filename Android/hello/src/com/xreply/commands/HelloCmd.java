/*************************************************************************
	> File   : HelloCmd.java
	> Author : Mark_Xiao
	> Mail   : Mark_Xiao@xreply.cn
	> Time   : 17/09/08 - 11:07
 ************************************************************************/
package com.xreply.commands;

public class HelloCmd {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Service s = new Service();
        s.OutPut();
    }
}
