/*
Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;




public class Bishop
{
   private ChessGUI2 g; 
   private int p;
   private String movePString;
   
   public Bishop(int position, String movePStrin, ChessGUI2 gee) 
   {
      p = position;
      this.movePString = movePStrin;
      g = gee;
      
      highlightMovesB();
   }
   
   
   public void highlightMovesB()
   { 
      int caseCounterB = 0;
      boolean continueLine = true;
      
      if(g.orientation == 'w')
      {
         if(g.whiteTurn)
         {
            while(caseCounterB < 4)
                  {
                     if(caseCounterB == 0)
                     {
                        continueLine = true;
                        
                        if(p - 9 >= 0 && g.pieces[p-9].charAt(0) != 'W')
                        {
                           nWestDiagCheck:
                           for(int nWestDiag = p-9; nWestDiag >= 0; nWestDiag -= 9)
                           {
                              if(g.pieces[nWestDiag].charAt(0) == 'W' || g.pieces[nWestDiag].charAt(0) == 'B' || nWestDiag == 0 || nWestDiag == 8 || nWestDiag == 16 || nWestDiag == 24 || 
                                 nWestDiag == 32 || nWestDiag == 40 || nWestDiag == 48 || nWestDiag == 1 || nWestDiag == 2 || nWestDiag == 3 || 
                                 nWestDiag == 4 || nWestDiag == 5 || nWestDiag == 6)
                              {
                                 continueLine = false;
                              }
                              
                              if(p == 0 || p == 8 || p == 16 || p == 24 || 
                                 p == 32 || p == 40 || p == 48 || p == 56 || p == 1 || p == 2 || p == 3 || p == 4 || p == 5 || p == 6 || p == 7)
                              {
                                 break nWestDiagCheck;
                              }
                                 
                              int posPosition = nWestDiag;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              } 
                              if(continueLine == false)
                              {
                                 break nWestDiagCheck;
                              }
                           }
                        } 
                        caseCounterB++;                       
                     }
                     if(caseCounterB == 1)
                     {
                        continueLine = true;
                        if(p - 7 >= 0 && g.pieces[p-7].charAt(0) != 'W')
                        {
                           nEastDiagCheck:
                           for(int nEastDiag = p-7; nEastDiag >= 0; nEastDiag -= 7)
                           {
                              if(g.pieces[nEastDiag].charAt(0) == 'W' || g.pieces[nEastDiag].charAt(0) == 'B' || nEastDiag == 7 || nEastDiag == 15 || nEastDiag == 23 || nEastDiag == 31 || 
                                 nEastDiag == 39 || nEastDiag == 47 || nEastDiag == 55 || nEastDiag == 1 || nEastDiag == 2 || nEastDiag == 3 || 
                                 nEastDiag == 4 || nEastDiag == 5 || nEastDiag == 6)
                              {
                                 continueLine = false;
                              }
                              
                              if(p == 7 || p == 15 || p == 23 || p == 31 || 
                                 p == 39 || p == 47 || p == 55 || p == 0 || p == 1 || p == 2 || p == 3 || 
                                 p == 4 || p == 5 || p == 6)
                              {
                                 break nEastDiagCheck;
                              }
                              
                              int posPosition = nEastDiag;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }     
                              if(continueLine == false)
                              {
                                 break nEastDiagCheck;
                              }
                        }
                     } 
                        caseCounterB++;                       
                  }
                     if(caseCounterB == 2)
                     {
                        continueLine = true;
                        if(p + 7 <= 63 && g.pieces[p+7].charAt(0) != 'W')
                        {
                           sWestDiagCheck:
                           for(int sWestDiag = p+7; sWestDiag >= 0; sWestDiag += 7)
                           {
                              if(g.pieces[sWestDiag].charAt(0) == 'W' || g.pieces[sWestDiag].charAt(0) == 'B' || sWestDiag == 8 || sWestDiag == 16 || sWestDiag == 24 || sWestDiag == 32 || 
                              sWestDiag == 40 || sWestDiag == 48 || sWestDiag == 56 || sWestDiag == 57 || sWestDiag == 58 || sWestDiag == 59 || 
                                 sWestDiag == 60 || sWestDiag == 61 || sWestDiag == 62)
                              {
                                 continueLine = false;
                              }
                                 
                              if(p == 8 || p == 16 || p == 24 || p == 32 || 
                                 p == 40 || p == 48 || p == 56 || p == 57 || p == 58 || p == 59 || 
                                 p == 60 || p == 61 || p == 62 || p == 63)
                              {
                                 break sWestDiagCheck;
                              }

                                 
                              int posPosition = sWestDiag;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                              {
                                 break sWestDiagCheck;
                              }
                        }
                     } 
                     caseCounterB++;                       
                  }
                     if(caseCounterB == 3)
                     {
                        continueLine = true;
                        if(p + 9 <= 63 && g.pieces[p+9].charAt(0) != 'W')
                        {
                           sEastDiagCheck:
                           for(int sEastDiag = p+9; sEastDiag >= 0; sEastDiag += 9)
                           {  
                              if(g.pieces[sEastDiag].charAt(0) == 'W' || g.pieces[sEastDiag].charAt(0) == 'B' || sEastDiag == 15 || sEastDiag == 23 || sEastDiag == 31 || sEastDiag == 39 || 
                              sEastDiag == 47 || sEastDiag == 55 || sEastDiag == 63 || sEastDiag == 57 || sEastDiag == 58 || sEastDiag == 59 || 
                                 sEastDiag == 60 || sEastDiag == 61 || sEastDiag == 62)
                              {
                                 continueLine = false;
                              }
                               
                              if(p == 7 || p == 15 || p == 23 || p == 31 || p == 39 || 
                                 p == 47 || p == 55 || p == 63 || p == 57 || p == 58 || p == 59 || 
                                 p == 60 || p == 61 || p == 62)
                              {
                                 break sEastDiagCheck;
                              } 
                                  
                              int posPosition = sEastDiag;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                              {
                                 break sEastDiagCheck;
                              }
                           }
                        }
                     } 
                        caseCounterB++;                       
                  }
         }
      
      if(g.whiteTurn == false)
      {
         while(caseCounterB < 4)
                  {
                     if(caseCounterB == 0)
                     {
                        continueLine = true;
                        if(p - 9 >= 0 && g.pieces[p-9].charAt(0) != 'B')
                        {
                           nWestDiagCheck:
                           for(int nWestDiag = p-9; nWestDiag >= 0; nWestDiag -= 9)
                           {
                                 if(g.pieces[nWestDiag].charAt(0) == 'B' || g.pieces[nWestDiag].charAt(0) == 'W' || nWestDiag == 0 || nWestDiag == 8 || nWestDiag == 16 || nWestDiag == 24 || 
                                 nWestDiag == 32 || nWestDiag == 40 || nWestDiag == 48 || nWestDiag == 1 || nWestDiag == 2 || nWestDiag == 3 || 
                                 nWestDiag == 4 || nWestDiag == 5 || nWestDiag == 6)
                                 {
                                    continueLine = false;
                                 }
                                 
                                 if(p == 0 || p == 8 || p == 16 || p == 24 || 
                                    p == 32 || p == 40 || p == 48 || p == 1 || p == 2 || p == 3 || p == 4 || p == 5 || p == 6 || p == 7)
                                 {
                                    break nWestDiagCheck;
                                 }
                                 
                                 int posPosition = nWestDiag;
                                 if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                                 {
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                                 if(continueLine == false)
                                 {
                                    break nWestDiagCheck;
                                 }
                           }
                        } 
                        caseCounterB++;                       
                     }
                     if(caseCounterB == 1)
                     {
                        continueLine = true;
                        if(p - 7 >= 0 && g.pieces[p-7].charAt(0) != 'B')
                        {
                           nEastDiagCheck:
                           for(int nEastDiag = p-7; nEastDiag >= 0; nEastDiag -= 7)
                           {
                                 if(g.pieces[nEastDiag].charAt(0) == 'B' || g.pieces[nEastDiag].charAt(0) == 'W' || nEastDiag == 7 || nEastDiag == 15 || nEastDiag == 23 || nEastDiag == 31 || 
                                 nEastDiag == 39 || nEastDiag == 47 || nEastDiag == 55 || nEastDiag == 1 || nEastDiag == 2 || nEastDiag == 3 || 
                                 nEastDiag == 4 || nEastDiag == 5 || nEastDiag == 6)
                                 {
                                    continueLine = false;
                                 }
                                 
                                 if(p == 7 || p == 15 || p == 23 || p == 31 || 
                                    p == 39 || p == 47 || p == 55 || p == 0 || p == 1 || p == 2 || p == 3 || 
                                    p == 4 || p == 5 || p == 6)
                                 {
                                    break nEastDiagCheck;
                                 }
                                 
                                 int posPosition = nEastDiag;
                                 if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                                 {
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                                 if(continueLine == false)
                                 {
                                    break nEastDiagCheck;
                                 }
                           }
                        } 
                        caseCounterB++;                       
                     }
                     if(caseCounterB == 2)
                     {
                        continueLine = true;
                        if(p + 7 <= 63 && g.pieces[p+7].charAt(0) != 'B')
                        {
                           sWestDiagCheck:
                           for(int sWestDiag = p+7; sWestDiag >= 0; sWestDiag += 7)
                           {
                                 if(g.pieces[sWestDiag].charAt(0) == 'B' || g.pieces[sWestDiag].charAt(0) == 'W' || sWestDiag == 8 || sWestDiag == 16 || sWestDiag == 24 || sWestDiag == 32 || 
                              sWestDiag == 40 || sWestDiag == 48 || sWestDiag == 56 || sWestDiag == 57 || sWestDiag == 58 || sWestDiag == 59 || 
                                 sWestDiag == 60 || sWestDiag == 61 || sWestDiag == 62)
                                 {
                                    continueLine = false;
                                 }
                                 
                                 if(p == 8 || p == 16 || p == 24 || p == 32 || 
                                    p == 40 || p == 48 || p == 56 || p == 57 || p == 58 || p == 59 || 
                                    p == 60 || p == 61 || p == 62 || p == 63)
                                 {
                                    break sWestDiagCheck;
                                 }
                                 
                                 int posPosition = sWestDiag;
                                 if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                                 {
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                                 if(continueLine == false)
                                 {
                                    break sWestDiagCheck;
                                 }
                           }
                        } 
                        caseCounterB++;                       
                     }
                     if(caseCounterB == 3)
                     {
                        continueLine = true;
                        if(p + 9 <= 63 && g.pieces[p+9].charAt(0) != 'B')
                        {
                           sEastDiagCheck:
                           for(int sEastDiag = p+9; sEastDiag >= 0; sEastDiag += 9)
                           {
                                 if(g.pieces[sEastDiag].charAt(0) == 'B' || g.pieces[sEastDiag].charAt(0) == 'W' || sEastDiag == 15 || sEastDiag == 23 || sEastDiag == 31 || sEastDiag == 39 || 
                                 sEastDiag == 47 || sEastDiag == 55 || sEastDiag == 63 || sEastDiag == 57 || sEastDiag == 58 || sEastDiag == 59 || 
                                 sEastDiag == 60 || sEastDiag == 61 || sEastDiag == 62)
                                 {
                                    continueLine = false;
                                 }
                                 
                                 if(p == 7 || p == 15 || p == 23 || p == 31 || p == 39 || 
                                    p == 47 || p == 55 || p == 63 || p == 57 || p == 58 || p == 59 || 
                                    p == 60 || p == 61 || p == 62)
                                 {
                                    break sEastDiagCheck;
                                 }
                                 
                                 int posPosition = sEastDiag;
                                 if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                                 {
                                    g.possibleMoves.add(posPosition);
                                    g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                                 }
                                 
                                 if(continueLine == false)
                                 {
                                    break sEastDiagCheck;
                                 }
                           }
                        } 
                        caseCounterB++;                       
                     }
                  }

      
      
      
      }
      
      
      
      }
   
   }

}