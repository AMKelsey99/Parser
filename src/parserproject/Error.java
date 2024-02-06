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
public class Error{
  public Error(String message, int line){
    System.out.println("Line " + line + ": " + message);
  }
  
  public void Exit() {
      System.exit(0);
  }
}
