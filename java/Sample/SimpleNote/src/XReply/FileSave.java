/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XReply;

import java.io.FileWriter;  
/**
 *
 * @author joker
 */
public class FileSave {
    private static String file="SimpleNote.txt";
    private boolean state = false;
    
    public void saveFile(String info) {
        try {
            FileWriter write = new FileWriter(file, true);
            write.write(info);
            write.close();
            state = true;
        } catch(Exception ex) {
            state = false;
        }
    }
    
    public boolean getState() {
        return state;
    }
    
    public String getFileName() {
        return file;
    }
}
