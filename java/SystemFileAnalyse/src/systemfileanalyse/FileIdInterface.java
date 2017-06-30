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
public interface FileIdInterface {
    public static int Method_MD5 = 0;
    public String createFileId(String filepath);
    public String createFileId(String filepath, int Medthod);
}
