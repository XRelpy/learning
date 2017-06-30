/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systemfileanalyse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author mark
 */
public class FileDataSave {
    private static FileDataSave fileDataSave;
    private Connection connection = null;
    private static final boolean DEBUG = false;
    private Statement stmt = null;
    
    public static FileDataSave getInstance() {
        if (fileDataSave == null) {
            synchronized (FileDataSave.class) {
                if (fileDataSave == null) {
                    fileDataSave = new FileDataSave();
                }
            }
        }
        return fileDataSave;
    }
    
    private FileDataSave() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:analyse.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();
            stmt.executeUpdate("drop table if exists FileData");
            stmt.executeUpdate("CREATE TABLE [FileData](\n" +
                               "    [FileName] TEXT NOT NULL, \n" +
                               "    [FilePath] TEXT NOT NULL, \n" +
                               "    [FileId] TEXT);");
        } catch (Exception ex ) {
            log(ex.toString());
        }
    }
    
    private void log(String value) {
        if (DEBUG) {
            System.out.println("[DB]:" +value);
        }
    }
    
    public void saveData(FileSaveSyntax syntax) {
        log(syntax.getFileName() + " -- " + syntax.getFileId());
               
        try {            
            String sql = "INSERT INTO FileData (FileName,FilePath,FileId) " +
                   "VALUES (" +"\""+ syntax.getFileName()+"\"" + ","+"\"" + syntax.getFilePath() +"\""+","  + "\""+syntax.getFileId() +"\""+");"; 
            String sql1 = "INSERT INTO FileData (FileName,FilePath,FileId)" + "VALUES(1,2,3);";
            log(sql);
            stmt.executeUpdate(sql);
        } catch(Exception ex) {
            log(ex.toString());
        } finally {
            if (stmt != null) { 
                try {
                    stmt.close();
                    connection.commit();
                } catch(Exception ex) {
                
                }
            }
        }
    }
}
