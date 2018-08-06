package Pieces;

/*
Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;
import main.ChessGUI2;



public class Pawn
{
   private ChessGUI2 g; 
   private int p;
   private String movePString;
   private boolean whiteTurn;
   
   public Pawn(int position, String movePString, ChessGUI2 gee) 
   {
      p = position;
      this.movePString = movePString;
      g = gee;
      this.whiteTurn = g.getTurn();
      
      highlightMovesP();
   }
   
   
   public void highlightMovesP()
   { 
      int caseCounterP = 0;
      
      if(g.getOrientation() == 'w')  //if g.getOrientation() is white starting on bottom
      {  
         if(whiteTurn)
         {
                  while(caseCounterP < 4)
                  {
                     if(caseCounterP == 0)
                     {
                        for(int c = 0; c < movePString.length(); c++)
                        {
                           char h = movePString.charAt(c);
                           
                           if(h == 'F' && g.pieces[p-8] == " " && g.pieces[p-16] == " ")
                           {
                              int posPosition = p - 16;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                        }
                        caseCounterP++;
                     }
                     if(caseCounterP == 1)
                     {
                        if((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || 
                           (p >= 33 && p <= 38) || (p >= 41 && p <= 46) || (p >= 49 && p <= 54))
                        {
                           if(p >= 25 && p <= 30)
                           {
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                if((p - 1) == g.passantPawn)
                            {
                               int posPosition = p - 9;
                               g.passantTakePos = posPosition;
                               g.possibleMoves.add(posPosition);
                               g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                            }
                            if((p + 1) == g.passantPawn)
                            {
                                 int posPosition = p - 7;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           
                              }
                           
                           }
                           
                           
                           if(g.pieces[p-8] == " ")
                           {
                              int posPosition = p - 8;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                           }
                           if(g.pieces[p-9].charAt(0) == 'B')
                           {
                              int posPosition = p - 9;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                           if(g.pieces[p-7].charAt(0) == 'B')
                           {
                              int posPosition = p - 7;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                        }
                        caseCounterP++;
                     }                                    
                     if(caseCounterP == 2)
                     {
                        for(int a = 0; a < g.squaresAFileW.length; a++)
                        {
                           if(p == g.squaresAFileW[a])
                           {                         
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                 if((p + 1) == g.passantPawn)
                                 {
                                    int posPosition = p - 7;
                                    g.passantTakePos = posPosition;
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                              }
                           
                              
                              if(g.pieces[p-8] == " ")
                              {
                                 int posPosition = p - 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p-7].charAt(0) == 'B')
                              {
                                 int posPosition = p - 7;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        caseCounterP++;
                     }                      
                     if(caseCounterP == 3)
                     {
                        for(int h = 0; h < g.squaresHFileW.length; h++)
                        {
                           if(g.getTurnCount() == g.passantTurn)
                           {
                              if((p - 1) == g.passantPawn)
                              {
                                 int posPosition = p - 9;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                           
                           if(p == g.squaresHFileW[h])
                           {
                              if(g.pieces[p-8] == " ")
                              {
                                 int posPosition = p - 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p - 9].charAt(0) == 'B')
                              {
                                 int posPosition = p - 9;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);  
                              }
                           }
                        }
                        caseCounterP++;
                     }                       
                  }  
         }
         
         if(whiteTurn == false)  //if black's turn and same as white's piece cases
            {
                     while(caseCounterP < 4)
                     {
                        if(caseCounterP == 0)
                        {
                           for(int c = 0; c < movePString.length(); c++)
                           {
                              char h = movePString.charAt(c);
                           
                              if(h == 'F' && g.pieces[p+8] == " " && g.pieces[p+16] == " ")
                              {
                                 int posPosition = p + 16;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                           caseCounterP++;
                     }
                     if(caseCounterP == 1)
                     {
                        if((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || 
                           (p >= 33 && p <= 38) || (p >= 41 && p <= 46) || (p >= 49 && p <= 54))
                        {
                           if(p >= 33 && p <= 38)
                           {
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                if((p - 1) == g.passantPawn)
                            {
                                 int posPosition = p + 7;
                                 g.passantTakePos = posPosition;
                               g.possibleMoves.add(posPosition);
                               g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                            }
                            if((p + 1) == g.passantPawn)
                            {
                                 int posPosition = p + 9;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           
                              }                                                      
                           }
                           
                           if(g.pieces[p+8] == " ")
                           {
                              int posPosition = p + 8;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                           }
                           if(g.pieces[p+7].charAt(0) == 'W')
                           {
                              int posPosition = p + 7;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                           if(g.pieces[p+9].charAt(0) == 'W')
                           {
                              int posPosition = p + 9;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                        }
                        caseCounterP++;
                     }                                    
                     if(caseCounterP == 2)
                     {
                        for(int a = 0; a < g.squaresAFileW.length; a++)
                        {
                           if(p == g.squaresAFileW[a])
                           {  
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                 if((p + 1) == g.passantPawn)
                                 {
                                    int posPosition = p + 9;
                                    g.passantTakePos = posPosition;
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                              }
                              
                              if(g.pieces[p+8] == " ")
                              {
                                 int posPosition = p + 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p+9].charAt(0) == 'W')
                              {
                                 int posPosition = p + 9;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        caseCounterP++;
                     }                      
                     if(caseCounterP == 3)
                     {
                        for(int h = 0; h < g.squaresHFileW.length; h++)
                        {
                           if(p == g.squaresHFileW[h])
                           {
                              if(g.getTurnCount() == g.passantTurn)
                              {
                              if((p - 1) == g.passantPawn)
                              {
                                 int posPosition = p + 7;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              }
                              
                              if(g.pieces[p+8] == " ")
                              {
                                 int posPosition = p + 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p + 7].charAt(0) == 'W')
                              {
                                 int posPosition = p + 7;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);  
                              }
                           }
                        }
                        caseCounterP++;
                     }    
                  }

   }

}
      
      if(g.getOrientation() == 'b')  //if g.getOrientation() is black starting on bottom
      {  
         if(whiteTurn)
         {
                  while(caseCounterP < 4)
                  {
                     if(caseCounterP == 0)
                     {
                        for(int c = 0; c < movePString.length(); c++)
                        {
                           char h = movePString.charAt(c);
                           
                           if(h == 'F' && g.pieces[p + 8] == " " && g.pieces[p+16] == " ")
                           {
                              int posPosition = p + 16;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                        }
                        caseCounterP++;
                     }
                     if(caseCounterP == 1)
                     {
                        if((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || 
                           (p >= 33 && p <= 38) || (p >= 41 && p <= 46) || (p >= 49 && p <= 54))
                        {
                           if(p >= 33 && p <= 38)
                           {
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                if((p - 1) == g.passantPawn)
                            {
                               int posPosition = p + 7;
                               g.passantTakePos = posPosition;
                               g.possibleMoves.add(posPosition);
                               g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                            }
                            if((p + 1) == g.passantPawn)
                            {
                                 int posPosition = p + 9;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           
                              }
                           
                           }
                           
                           
                           if(g.pieces[p+8] == " ")
                           {
                              int posPosition = p + 8;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                           }
                           if(g.pieces[p+7].charAt(0) == 'B')
                           {
                              int posPosition = p + 7;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                           if(g.pieces[p+9].charAt(0) == 'B')
                           {
                              int posPosition = p + 9;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                        }
                        caseCounterP++;
                     }                                    
                     if(caseCounterP == 2)
                     {
                        for(int a = 0; a < g.squaresAFileB.length; a++)
                        {
                           if(p == g.squaresAFileB[a])
                           {                         
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                 if((p - 1) == g.passantPawn)
                                 {
                                    int posPosition = p + 7;
                                    g.passantTakePos = posPosition;
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                              }
                           
                              
                              if(g.pieces[p+8] == " ")
                              {
                                 int posPosition = p + 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p+7].charAt(0) == 'B')
                              {
                                 int posPosition = p + 7;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        caseCounterP++;
                     }                      
                     if(caseCounterP == 3)
                     {
                        for(int h = 0; h < g.squaresHFileB.length; h++)
                        {
                           if(g.getTurnCount() == g.passantTurn)
                           {
                              if((p + 1) == g.passantPawn)
                              {
                                 int posPosition = p + 9;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                           
                           if(p == g.squaresHFileB[h])
                           {
                              if(g.pieces[p+8] == " ")
                              {
                                 int posPosition = p + 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p + 9].charAt(0) == 'B')
                              {
                                 int posPosition = p + 9;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);  
                              }
                           }
                        }
                        caseCounterP++;
                     }                       
                  }  
         }
         
         if(whiteTurn == false)  //if black's turn, same as white's piece cases
            {
                     while(caseCounterP < 4)
                     {
                        if(caseCounterP == 0)
                        {
                           for(int c = 0; c < movePString.length(); c++)
                           {
                              char h = movePString.charAt(c);
                           
                              if(h == 'F' && g.pieces[p-8] == " " && g.pieces[p-16] == " ")
                              {
                                 int posPosition = p - 16;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                           caseCounterP++;
                     }
                     if(caseCounterP == 1)
                     {
                        if((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || 
                           (p >= 33 && p <= 38) || (p >= 41 && p <= 46) || (p >= 49 && p <= 54))
                        {
                           if(p >= 25 && p <= 30)
                           {
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                if((p - 1) == g.passantPawn)
                            {
                                 int posPosition = p - 9;
                                 g.passantTakePos = posPosition;
                               g.possibleMoves.add(posPosition);
                               g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                            }
                            if((p + 1) == g.passantPawn)
                            {
                                 int posPosition = p - 7;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           
                              }                                                      
                           }
                           
                           if(g.pieces[p-8] == " ")
                           {
                              int posPosition = p - 8;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                           }
                           if(g.pieces[p-7].charAt(0) == 'W')
                           {
                              int posPosition = p - 7;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                           if(g.pieces[p-9].charAt(0) == 'W')
                           {
                              int posPosition = p - 9;
                              g.possibleMoves.add(posPosition);
                              g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                           }
                        }
                        caseCounterP++;
                     }                                    
                     if(caseCounterP == 2)
                     {
                        for(int a = 0; a < g.squaresAFileB.length; a++)
                        {
                           if(p == g.squaresAFileB[a])
                           {  
                              if(g.getTurnCount() == g.passantTurn)
                              {
                                 if((p - 1) == g.passantPawn)
                                 {
                                    int posPosition = p - 9;
                                    g.passantTakePos = posPosition;
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                              }
                              
                              if(g.pieces[p-8] == " ")
                              {
                                 int posPosition = p - 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p-9].charAt(0) == 'W')
                              {
                                 int posPosition = p - 9;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        caseCounterP++;
                     }                      
                     if(caseCounterP == 3)
                     {
                        for(int h = 0; h < g.squaresHFileB.length; h++)
                        {
                           if(p == g.squaresHFileB[h])
                           {
                              if(g.getTurnCount() == g.passantTurn)
                              {
                              if((p + 1) == g.passantPawn)
                              {
                                 int posPosition = p - 7;
                                 g.passantTakePos = posPosition;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              }
                              
                              if(g.pieces[p-8] == " ")
                              {
                                 int posPosition = p - 8;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]); 
                              }
                              if(g.pieces[p - 7].charAt(0) == 'W')
                              {
                                 int posPosition = p - 7;
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);  
                              }
                           }
                        }
                        caseCounterP++;
                     }    
                  }

   }

}
      
      
      
      
      
}

}