/*
1. You do not need a separate token (number) for each operator. All of them should be translated to one token (number) which is OPERATOR. See below
2. In the switch the code for identifier and literal is complete. Do not change it

3. Symbol e means epsilon.

 BNF grammar of Mini Language

 Program" --> "("Sequence State")".
 Sequence --> "("Statements")".
 Statements --> Statements  Stmt | e
 Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
 State -->  "("Pairs")".
 Pairs -->  Pairs Pair | e.
 Pair --> "("Identifier Literal")".
 NullStatement --> "skip".
 Assignment --> "assign" Identifier Expression.
 Conditional --> "conditional" Expression Stmt Stmt.
 Loop --> "loop" Expression Stmt.
 Block --> "block" Statements.
 Expression --> Identifier | Literal | "("Operation Expression Expression")".
 Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".

 Note: Treat Identifier and Literal as terminal symbols. Every symbol inside " and " is a terminal symbol. The rest are non terminals.

Input file: test.txt
Output:
 Line: 1, spelling = [(], kind = 9
 Line: 1, spelling = [)], kind = 10
 Line: 1, spelling = [sum], kind = 0
 Line: 1, spelling = [a], kind = 0
 Line: 1, spelling = [2], kind = 1
 Line: 1, spelling = [xyz], kind = 0
 Line: 2, spelling = [skip], kind = 6
 Line: 2, spelling = [assign], kind = 2
 Line: 2, spelling = [conditional], kind = 3
 Line: 2, spelling = [loop], kind = 4
 Line: 2, spelling = [block], kind = 5
 Line: 3, spelling = [1234], kind = 1
 Line: 4, spelling = [+], kind = 11
 Line: 4, spelling = [-], kind = 11
 Line: 4, spelling = [*], kind = 11
 Line: 4, spelling = [/], kind = 11
 Line: 4, spelling = [<], kind = 11
 Line: 4, spelling = [<=], kind = 11
 Line: 4, spelling = [>], kind = 11
 Line: 4, spelling = [>=], kind = 11
 Line: 4, spelling = [=], kind = 11
 Line: 4, spelling = [!=], kind = 11
 Line: 4, spelling = [or], kind = 8
 Line: 4, spelling = [and], kind = 7
 Line: 5, spelling = [-], kind = 11
 Line: 5, spelling = [1234], kind = 1
 Line 6: wrong token !

Note: After you get an error message for the symbol = remove this symbol and
run the program. Repeat this until the last wrong token which is: ?

You should get the following error messges:
 Line 6: wrong token !
 Line 6: wrong token ?
*/
package parserproject;

/**
 *
 * @author alana
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Scanner{
  private char currentChar;
  private byte currentKind;
  private StringBuffer currentSpelling;
  private BufferedReader inFile;
  private static int line = 1;

  public Scanner(BufferedReader inFile){
    this.inFile = inFile;
    try{
      int i = this.inFile.read();
      if(i == -1) //end of file
        currentChar = '\u0000';
      else
        currentChar = (char)i;
    }
    catch(IOException e){
        System.out.println(e);
    }
  }

  private void takeIt(){
    currentSpelling.append(currentChar);
    //System.out.println(currentSpelling +" char:"+ currentChar);
    try{
      int i = inFile.read();
      if(i == -1) //end of file
        currentChar = '\u0000';
      else
        currentChar = (char)i;
    }
    catch(IOException e){
        System.out.println(e);
    }
  }

  private void discard(){
    try{
      int i = inFile.read();
      if(i == -1) //end of file
        currentChar = '\u0000';
      else
        currentChar = (char)i;
    }
    catch(IOException e){
        System.out.println(e);
    }
  }

  private byte scanToken(){
    String check;
    switch(currentChar){
      //Your switch
        case 's':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            check = currentSpelling.toString();
            if (check.equals("skip")) {
            return 6;
            } else {
            return 0;
            }
        
        case 'a':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            check = currentSpelling.toString();
            if (check.equals("assign")) {
            return 2;
            } else if (check.equals("and")) {
            return 7;
            } else {
            return 0;
            }
        
        case 'c':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            check = currentSpelling.toString();
            if (check.equals("conditional")) {
            return 3;
            } else {
            return 0;
            }
            
        case 'l':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            check = currentSpelling.toString();
            if (check.equals("loop")) {
            return 4;
            } else {
            return 0;
            }
            
        case 'b':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            check = currentSpelling.toString();
            if (check.equals("block")) {
            return 5;
            } else {
            return 0;
            }
        
         case 'o':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            check = currentSpelling.toString();
            if (check.equals("or")) {
            return 8;
            } else {
            return 0;
            }
            
        case '0': case '1': case '2': case '3': case '4':  case '5':  case '6':  case '7':  case '8':  case '9': 
            while (isDigit(currentChar) == true) {
                takeIt();
            }
            return 1;
            
        case 'd': case 'e': case 'f': case 'g': case 'h': case 'i': case 'j': case 'k': case 'm': case 'n': case 'p': case 'q': case 'r': case 't': case 'u': case 'v': case 'w': case 'x': case 'y': case 'z':
            while (isLetter(currentChar) == true) {
                takeIt();
            }
            return 0;
        
        case '+': case'-': case'*': case'/':  case'=':
            takeIt();
            return 11;
            
        case'<': case'>': case'!':
            StringBuffer h = new StringBuffer("");
            h.append(currentChar);
            //System.out.println(h.toString());
            takeIt();
            while (isGraphic(currentChar) == true) {
                takeIt();
                check = currentSpelling.toString();
                if (check.equals("<=") || check.equals(">=") || check.equals("!=")) {
                    break;
                } else {
                    currentSpelling = h;
                    break;
                }
            }
            check = currentSpelling.toString();
            if (check.equals("<") || check.equals(">") || check.equals("<=") || check.equals(">=") || check.equals("!=")) {
                return 11;
            }
            return 13;
            
        case '(':
            takeIt();
            return 9;
        
        case ')':
            takeIt();
            return 10;
            
        case '?':
            takeIt();
            return 13;
            
         default:
            return 12;
    }
    
  }
    

  private void scanSeparator(){
    switch(currentChar){
      case ' ': case '\n': case '\r': case '\t':
        if(currentChar == '\n')
          line++;
        discard();
    }
  }

  public Token scan(){
    currentSpelling = new StringBuffer("");
    while(currentChar == ' ' || currentChar == '\n' || currentChar == '\r')
      scanSeparator();
    currentKind = scanToken();
    return new Token(currentKind, currentSpelling.toString(), line);
  }

  private boolean isGraphic(char c){
    return c == '\t' ||(' ' <= c && c <= '~');
  }

  private boolean isDigit(char c){
    return '0' <= c && c <= '9';
  }

  private boolean isLetter(char c){
    return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
  }
}

