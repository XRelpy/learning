/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XReply;

import java.util.Scanner;
/**
 *
 * @author joker
 */
public class EditNote {
    private static String TAG_TITLE ="title";
    private static String TAG_CONTENT = "Content";
    private static int MODE_SAVE = 0;
    private static int MODE_EXIT = 1;
    
    public void EditMode() {
        boolean editFlag = true;
        boolean isTitle = false;
        boolean isContent = false;
        int mode = MODE_SAVE;
        
        Scanner s = new Scanner(System.in);
        NoteStyle note = new NoteStyle();
        while (editFlag) {
           if (!isTitle) {
               Out(TAG_TITLE + ":");
               String t = s.nextLine();
               note.setTitle(t);
               isTitle = true;
           }
           if (!isContent) {
               Out(TAG_CONTENT + "\n");
               isContent = true;
           }
           Out(">");
           String con = s.nextLine();
           
           if (con.equals("save")) {
               editFlag = false;
               mode = MODE_SAVE;
           } else if (con.equals("exit")) {
               editFlag = false;
               mode = MODE_EXIT;
           } else {
                note.setContent(con);
           }
           
        }
        
        String info = note.getNote().toString();
        if (mode == MODE_SAVE) {
           FileSave f = new FileSave();
           f.saveFile(info);
           Out(info);
           Out("#########################\n");
           Out("Write: " + f.getFileName() + "  " + (f.getState() ? "success" : "fail")+"\n");
        } else if (mode == MODE_EXIT) {
           Out(info);
           Out("#########################\n");
           Out("Exit Not Save File\n");
        }
    }
    
    private void Out(String s) {
        System.out.print((String)s);
    }
}
