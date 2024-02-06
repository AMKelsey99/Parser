/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserproject;

/**
 *
 * @author alana
 */
import java.io.*;

public class SourceFile{
  public BufferedReader openFile(){
    String fileName="";
    BufferedReader inFile=null;
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Source file = ");
    System.out.flush();
    try{
      fileName = stdin.readLine();
      inFile = new BufferedReader(new FileReader(fileName));
    }
    catch(FileNotFoundException e){
      System.out.println("The source file " + fileName + " was not found.");
    }
    catch(IOException e){
      System.out.println(e);
    }
    return inFile;
  }
}
