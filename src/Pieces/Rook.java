package Pieces;

/*
Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;
import main.ChessGUI2;



public class Rook
{
   private ChessGUI2 g; 
   private int p;
   
   public Rook(int position, ChessGUI2 gee) 
   {
      p = position;
      g = gee;
      
      highlightMovesR();
   }
   
   
   public void highlightMovesR()
   { 
      int caseCounterR = 0;
      boolean continueLine = true;
      
         if(g.getTurn())
         {
            while(caseCounterR < 4)
                  {
                     if(caseCounterR == 0)
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
                        caseCounterR++;
                     } 
                     
                     if(caseCounterR == 1)
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
                        caseCounterR++;
                     } 
                     if(caseCounterR == 2)
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
                        caseCounterR++;
                     } 
                     if(caseCounterR == 3)
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
                        caseCounterR++;
                     }
                  }

         
         }
      
         if(g.getTurn() == false)
         {
            while(caseCounterR < 4)
                  {
                     if(caseCounterR == 0)
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
                        caseCounterR++;
                     } 
                     
                     if(caseCounterR == 1)
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
                         caseCounterR++;
                     } 
                     if(caseCounterR == 2)
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
                        caseCounterR++;
                     } 
                     if(caseCounterR == 3)
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
                        caseCounterR++;
                     }
                  }

         }
      



   }


}