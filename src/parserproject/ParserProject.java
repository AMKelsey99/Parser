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
public class ParserProject {

    /**
     * @param args the command line arguments
     */
  public static void main(String[] args){
    Parser p = new Parser();
    p.parse();
    System.out.println("The syntax of the source program is correct.");
  }
}
