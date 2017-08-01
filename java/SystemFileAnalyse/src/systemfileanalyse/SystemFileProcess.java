/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemfileanalyse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 * @author mark
 */
public class SystemFileProcess {
    private int MAX_PROCESS = Runtime.getRuntime().availableProcessors();
    private ExecutorService fixedThreadPool;
    private ArrayList <String> list  = new ArrayList<String>();
    private boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

                File file = new File(filepath);
                File[] flist = file.listFiles();
                for (int i = 0; i < flist.length; i++) {
                    if (flist[i].isFile()) {
                            //list.add(file.getAbsolutePath());
                            //System.out.println("文件");
                            //System.out.println("path=" + flist[i].getPath());
                            //System.out.println("absolutepath=" + flist[i].getAbsolutePath());
                            //System.out.println("name=" + flist[i].getName());
                            String fileName = flist[i].getName();
                            String filePath = flist[i].getAbsolutePath();
                            fixedThreadPool.execute(new Runnable(){
                                public void run() {
                                    FileSaveSyntax syntax = new FileSaveSyntax();
                                    FileIdCreate id = new FileIdCreate();
                                    syntax.setFileName(fileName);
                                    syntax.setFilePath(filePath);
                                    syntax.setFileId(id.createFileId(syntax.getFilePath()));
                                    System.out.println("# " + syntax.getFileName() + "\t" + syntax.getFileId());
                                    FileDataSave.getInstance().saveData(syntax);
                                }
                            }); 
                    } else if (flist[i].isDirectory()) {
                            //System.out.println("文件夹");
                            readfile(flist[i].getPath() + "/");
                            //readfile(filepath + "\\"  + flist[i]);
                    }
                }
        } catch (FileNotFoundException e) {
                System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }
   
    private void ProcessInfo() {
        System.out.println("MAX Process:" + MAX_PROCESS);
    }
    
    private void computeFileId() {
        int size = list.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {  
                System.out.println(i +" : " + list.get(i));
            }
        }
    }
    public void start(String path) {
        ProcessInfo();
        try {
            fixedThreadPool =  Executors.newFixedThreadPool(MAX_PROCESS);
            readfile(path);
            //computeFileId();
        } catch(Exception ex) {
        }
    }
}
