/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemfileanalyse;

/**
 *
 * @author mark
 */
public class SystemFileAnalyse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length == 1) {
            SystemFileProcess process = new SystemFileProcess();
            process.start(args[0]);
        } else {
            usage();
        }
    }
    
    public static void usage() {
        System.out.println("SystemFileAnalyse  DirPath");
    }
}
