/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XReply;

import java.util.Date;
import java.text.SimpleDateFormat;
/**
 *
 * @author joker
 */
public class NoteStyle {
    private String time = "";
    private StringBuffer buffer = new StringBuffer();
    
    public NoteStyle() {
        setDate();
        buffer.append("# " + time+"\n");
    }
    
    public void setTitle(String title) {
         buffer.append(": " + title + "\n");
    }
    
    public void setContent(String content) {
        buffer.append("\t" + content + "\n");
    }
    
    private void setDate() {
        long time_stamp = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd -- HH:mm:ss");
        Date d = new Date(time_stamp);
        time = df.format(d);
    }
    
    public StringBuffer getNote() {
        buffer.append("\n\n\n");
        return buffer;
    }
}
