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
/*
EBNF of Mini Language
Program" --> "("Sequence State")".
Sequence --> "("Statements")".
Statements --> Stmt*
Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
State -->  "("Pairs")".
Pairs -->  Pair*.
Pair --> "("Identifier Literal")".
NullStatement --> "skip".
Assignment --> "assign" Identifier Expression.
Conditional --> "conditional" Expression Stmt Stmt.
Loop --> "loop" Expression Stmt.
Block --> "block" Statements.
Expression --> Identifier | Literal | "("Operation Expression Expression")".
Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".
*/
public class Parser{
  private Token currentToken;
  Scanner scanner;

  private void accept(byte expectedKind) {
    if (currentToken.kind == expectedKind) {
      System.out.println("Line: " + currentToken.line + ", spelling = [" + currentToken.spelling + "], kind = " + currentToken.kind);
      currentToken = scanner.scan();
    } else {
      new Error("Syntax error: " + currentToken.spelling + " is not expected. ",
                currentToken.line);
      if (currentToken.kind == Token.EOT && currentToken.kind != Token.NOTHING) {
          acceptIt();
      } else {
      System.exit(0);
      }
    }
  }

  private void acceptIt() {
    currentToken = scanner.scan();
  }

  public void parse() {
    SourceFile sourceFile = new SourceFile();
    scanner = new Scanner(sourceFile.openFile());
    currentToken = scanner.scan();
    parseProgram();
    if (currentToken.kind != Token.EOT) {
      new Error("Syntax error: Redundant characters at the end of program.",
                currentToken.line);
            System.exit(0);
    }
  }

  //Program" --> "("Sequence State")".
  private void parseProgram() {
      if (currentToken.kind != 13) {
          
          switch(currentToken.kind) {
              case 6: case 5: case 4: case 3: case 2:
                  parseSequence();
              case 0: case 1: case 11: case 7: case 8:
                  parseExpression();
              case 9:
                  accept((byte)9);
                  parseProgram();
               case 10:
                  accept((byte)10);
                  parseProgram();
          }
      } 
  }

  //Sequence --> "("Statements")".
  private void parseSequence(){
      parseStatements();
  }

  //Statements --> Stmt*
  private void parseStatements(){
      parseStmt();
  }

  //Stmt --> "(" {NullStatement | Assignment | Conditional | Loop | Block}")".
  private void parseStmt(){
      switch(currentToken.kind) {
          case 2:
              parseAssignment();
          case 3:
              parseConditional();
          case 4:
              parseLoop();
          case 5:
              parseBlock();
          case 6:
              parseNullStatement();
      }
  }

  //State -->  "("Pairs")".
  private void parseState(){
      parsePairs();
  }

  //Pairs --> Pair*.
  private void parsePairs(){
      parsePair();
      parseProgram();
  }

  //Pair --> "("Identifier Literal")".
  private void parsePair(){
      switch(currentToken.kind) {
          case 0:
              accept((byte)0);
              break;
          case 1:
              accept((byte)1);
              break;
      }
      parsePair();
  }

  //NullStatement --> skip.
  private void parseNullStatement(){
      accept((byte)6);
      parseProgram();
  }

  //Assignment --> "assign" Identifier Expression.
  private void parseAssignment(){
      accept((byte)2);
      parseExpression();
      parseExpression();
      parseProgram();
  }

  //Conditional --> "conditional" Expression Stmt Stmt.
  private void parseConditional(){
      accept((byte)3);
      parseExpression();
      parseStmt();
      parseStmt();
      parseProgram();
  }

  //Loop --> "loop" Expression Stmt.
  private void parseLoop(){
      accept((byte)4);
      parseExpression();
      parseStmt();
      parseProgram();
  }

  //Block --> "block" Statements.
  private void parseBlock(){
      accept((byte)5);
      parseStatements();
      parseProgram();
  }

  //Expression --> Identifier | Literal | "("Operation Expression Expression")".
  private void parseExpression(){
      switch (currentToken.kind) {
          case 0:
              accept((byte)0);
              break;
          case 1:
              accept((byte)1);
              break;
          case 7: case 8: case 11:
              parseOperation();
      }
      parseProgram();
  }

  //Operation --> "+" |"-" | "*" | "/" | "<" | "<=" | ">" | ">=" | "=" | "!=" | "or" | "and".
  private void parseOperation(){
      
      switch(currentToken.kind) {
          case 11:
              accept((byte)11);
              break;
          case 7:
            accept((byte)7);
            break;
          case 8:
              accept((byte)8);
              break;
      }
      parseProgram();
  }
}

