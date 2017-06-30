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
public class FileSaveSyntax {
    private String filePath;
    private String fileName;
    private String fileId;
    
    public void setFilePath(String path) {
        filePath = path;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFileName(String name) {
        fileName = name;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileId(String id) {
        fileId = id;
    }
    
    public String getFileId() {
        return fileId;
    }
    
}
