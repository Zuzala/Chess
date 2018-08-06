package Pieces;

/*
Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;
import main.ChessGUI2;



public class Queen
{
   private ChessGUI2 g; 
   private int p;
   private boolean whiteTurn;
   private boolean checkingForCheck;
   
   public Queen(int position, ChessGUI2 gee, boolean checkingForCheck) 
   {
      p = position;
      g = gee;
      this.checkingForCheck = checkingForCheck;
      this.whiteTurn = g.getTurn();
      
      highlightMovesQ();
   }
   
   
   public void highlightMovesQ()
   { 
      int caseCounterQB = 0;
      int caseCounterQR = 0;
      boolean continueLine = true;
      
         if(whiteTurn)
         {
            while(caseCounterQB < 4)
                  {
                     if(caseCounterQB == 0)
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
                        caseCounterQB++;                       
                     }
                     if(caseCounterQB == 1)
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
                        caseCounterQB++;                       
                  }
                     if(caseCounterQB == 2)
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
                     caseCounterQB++;                       
                  }
                     if(caseCounterQB == 3)
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
                        caseCounterQB++;                       
                  }
                  while(caseCounterQR < 4)
                  {
                     if(caseCounterQR == 0)
                     {
                       continueLine = true;
                        if(p + 1 <= 63 && g.pieces[p+1].charAt(0) != 'W')
                        {
                           rHorizCheck:
                           for(int rHoriz = p+1; rHoriz <= 63;  rHoriz++)
                           {  
                              if(g.pieces[rHoriz].charAt(0) == 'W' || g.pieces[rHoriz].charAt(0) == 'B' || rHoriz == 7 || rHoriz == 15 || rHoriz == 23 || rHoriz == 31 || 
                              rHoriz == 39 || rHoriz == 47 || rHoriz == 55 || rHoriz == 63)
                              {
                                 continueLine = false;
                              }
                              if(p == 7 || p == 15 || p == 23 || p == 31 || 
                               p == 39 || p == 47 || p == 55 || p == 63)
                              {
                                 break rHorizCheck;
                              }      
                              int posPosition = rHoriz;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                                 {
                                    break rHorizCheck;
                                 }
                           }
                        }
                        caseCounterQR++;
                     } 
                     
                     if(caseCounterQR == 1)
                     {
                       continueLine = true;
                        if(p - 1 >= 0 && g.pieces[p-1].charAt(0) != 'W')
                        {
                           lHorizCheck:
                           for(int lHoriz = p-1; lHoriz >= 0;  lHoriz--)
                           {  
                              if(g.pieces[lHoriz].charAt(0) == 'W' || g.pieces[lHoriz].charAt(0) == 'B' || lHoriz == 0 || lHoriz == 8 || lHoriz == 16 || lHoriz == 24 || 
                              lHoriz == 32 || lHoriz == 40 || lHoriz == 48 || lHoriz == 56)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 8 || p == 16 || p == 24 || 
                               p == 32 || p == 40 || p == 48 || p == 56)
                              {
                                 break lHorizCheck;
                              }
   
                                 
                              int posPosition = lHoriz;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                              {
                                 break lHorizCheck;
                              }
                           }
                        }
                        caseCounterQR++;
                     } 
                     if(caseCounterQR == 2)
                     {
                       continueLine = true;
                        if(p - 8 >= 0 && g.pieces[p-8].charAt(0) != 'W')
                        {
                           verticalCheck:
                           for(int vertical = p-8; vertical >= 0; vertical -= 8)
                           {  
                              if(g.pieces[vertical].charAt(0) == 'W' || g.pieces[vertical].charAt(0) == 'B' || vertical == 0 || vertical == 1 || vertical == 2 || vertical == 3 || 
                              vertical == 4 || vertical == 5 || vertical == 6 || vertical == 7)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 1 || p == 2 || p == 3 || 
                               p == 4 || p == 5 || p == 6 || p == 7)
                              {
                                 break verticalCheck;
                              }
   
                                 
                              int posPosition = vertical;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                              {
                                 break verticalCheck;
                              }
                           }
                        }
                        caseCounterQR++;
                     } 
                     if(caseCounterQR == 3)
                     {
                       continueLine = true;
                        if(p + 8 <=63 && g.pieces[p+8].charAt(0) != 'W')
                        {
                           lowCheck:
                           for(int low = p+8; low <= 63; low += 8)
                           {  
                              if(g.pieces[low].charAt(0) == 'W' || g.pieces[low].charAt(0) == 'B' || low == 56 || low == 57 || low == 58 || low == 59 || 
                              low == 60 || low == 61 || low == 62 || low == 63)
                              {
                                 continueLine = false;
                              }
                              if(p == 56 || p == 57 || p == 58 || p == 59 || 
                               p == 60 || p == 61 || p == 62 || p == 63)
                              {
                                 break lowCheck;
                              }
   
                                 
                              int posPosition = low;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'B')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                              {
                                 break lowCheck;
                              }
                           }
                        }
                        caseCounterQR++;
                     }
                  }

         }
         
         if(whiteTurn == false)
         {
            while(caseCounterQB < 4)
                  {
                     if(caseCounterQB == 0)
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
                        caseCounterQB++;                       
                     }
                     if(caseCounterQB == 1)
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
                        caseCounterQB++;                       
                     }
                     if(caseCounterQB == 2)
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
                        caseCounterQB++;                       
                     }
                     if(caseCounterQB == 3)
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
                        caseCounterQB++;                       
                     }
                  }
                   
                  while(caseCounterQR < 4)
                  {
                     if(caseCounterQR == 0)
                     {
                       continueLine = true;
                        if(p + 1 <= 63 && g.pieces[p+1].charAt(0) != 'B')
                        {
                           rHorizCheck:
                           for(int rHoriz = p+1; rHoriz <= 63;  rHoriz++)
                           {  
                              if(g.pieces[rHoriz].charAt(0) == 'W' || g.pieces[rHoriz].charAt(0) == 'B' || rHoriz == 7 || rHoriz == 15 || rHoriz == 23 || rHoriz == 31 || 
                              rHoriz == 39 || rHoriz == 47 || rHoriz == 55 || rHoriz == 63)
                              {
                                 continueLine = false;
                              }
                              
                              if(p == 7 || p == 15 || p == 23 || p == 31 || 
                               p == 39 || p == 47 || p == 55 || p == 63)
                              {
                                 break rHorizCheck;
                              }
                                 
                              int posPosition = rHoriz;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                                 {
                                    break rHorizCheck;
                                 }
                           }
                        }
                        caseCounterQR++;
                     } 
                     
                     if(caseCounterQR == 1)
                     {
                       continueLine = true;
                        if(p - 1 >= 0 && g.pieces[p-1].charAt(0) != 'B')
                        {
                           lHorizCheck:
                           for(int lHoriz = p-1; lHoriz >= 0;  lHoriz--)
                           {  
                              if(g.pieces[lHoriz].charAt(0) == 'W' || g.pieces[lHoriz].charAt(0) == 'B' || lHoriz == 0 || lHoriz == 8 || lHoriz == 16 || lHoriz == 24 || 
                              lHoriz == 32 || lHoriz == 40 || lHoriz == 48 || lHoriz == 56)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 8 || p == 16 || p == 24 || 
                               p == 32 || p == 40 || p == 48 || p == 56)
                              {
                                 break lHorizCheck;
                              }
   
                                 
                              int posPosition = lHoriz;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                                 {
                                    break lHorizCheck;
                                 }
                           }
                        }
                         caseCounterQR++;
                     } 
                     if(caseCounterQR == 2)
                     {
                       continueLine = true;
                        if(p - 8 >= 0 && g.pieces[p-8].charAt(0) != 'B')
                        {
                           verticalCheck:
                           for(int vertical = p-8; vertical >= 0; vertical -= 8)
                           {  
                              if(g.pieces[vertical].charAt(0) == 'W' || g.pieces[vertical].charAt(0) == 'B' || vertical == 0 || vertical == 1 || vertical == 2 || vertical == 3 || 
                              vertical == 4 || vertical == 5 || vertical == 6 || vertical == 7)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 1 || p == 2 || p == 3 || 
                               p == 4 || p == 5 || p == 6 || p == 7)
                              {
                                 break verticalCheck;
                              }
   
                                 
                              int posPosition = vertical;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                                 {
                                    break verticalCheck;
                                 }
                           }
                        }
                        caseCounterQR++;
                     } 
                     if(caseCounterQR == 3)
                     {
                       continueLine = true;
                        if(p + 8 <=63 && g.pieces[p+8].charAt(0) != 'B')
                        {
                           lowCheck:
                           for(int low = p+8; low <= 63; low += 8)
                           {  
                              if(g.pieces[low].charAt(0) == 'W' || g.pieces[low].charAt(0) == 'B' || low == 56 || low == 57 || low == 58 || low == 59 || 
                              low == 60 || low == 61 || low == 62 || low == 63)
                              {
                                 continueLine = false;
                              }
                              if(p == 56 || p == 57 || p == 58 || p == 59 || 
                               p == 60 || p == 61 || p == 62 || p == 63)
                              {
                                 break lowCheck;
                              }
   
                              int posPosition = low;
                              if(g.pieces[posPosition] == " " || g.pieces[posPosition].charAt(0) == 'W')
                              {
                                 g.possibleMoves.add(posPosition);
                                 g.possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(continueLine == false)
                                 {
                                    break lowCheck;
                                 }
                           }
                        }
                        caseCounterQR++;
                     }
                  }

         }
      



   }


}