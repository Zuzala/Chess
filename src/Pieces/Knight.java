package Pieces;

/*

Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;
import main.ChessGUI2;



public class Knight
{
   private ChessGUI2 g; 
   private int p;
   private String movePString;
   
   public Knight(int position, String movePStrin, ChessGUI2 gee) 
   {
      p = position;
      this.movePString = movePStrin;
      g = gee;
      
      highlightMovesN();
   }
   
   
   public void highlightMovesN()
   { 
      int caseCounterN = 0;
      
      if(g.orientation == 'w' || g.orientation == 'b')
      {
         while(caseCounterN < 25)
         {  
            if(caseCounterN == 0)
            {   
               if((p >= 18 && p <= 21) || (p >= 26 && p <= 29) || (p >= 34 && p <= 37) || (p >= 42 && p <= 45))
               {
                  int[] posPositions = new int[8];
                  posPositions[0] = p - 17;
                  posPositions[1] = p - 15;
                  posPositions[2] = p - 10;
                  posPositions[3] = p - 6;
                  posPositions[4] = p + 6;
                  posPositions[5] = p + 10;
                  posPositions[6] = p + 15;
                  posPositions[7] = p + 17;
                           
                  for(int x = 0; x < posPositions.length; x++)
                  {
                     if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                  }
               }
               caseCounterN++;
            }
            if(caseCounterN == 1)
            {     
               if(p == 16 || p == 24 || p == 32 || p == 40)
               {
                  int[] posPositions = new int[4];
                  posPositions[0] = p - 15;
                  posPositions[1] = p - 6;
                  posPositions[2] = p + 10;
                  posPositions[3] = p + 17;
                           
                  for(int x = 0; x < posPositions.length; x++)
                  {
                     if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                  } 
               }
               caseCounterN++;
            }
            if(caseCounterN == 2)
            {     
               if(p == 23 || p == 31 || p == 39 || p == 47)
               {
                  int[] posPositions = new int[4];
                  posPositions[0] = p - 17;
                  posPositions[1] = p - 10;
                  posPositions[2] = p + 6;
                  posPositions[3] = p + 15;
                           
                  for(int x = 0; x < posPositions.length; x++)
                  {
                     if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                  }
               }
               caseCounterN++;
            }
            if(caseCounterN == 3)
            {     
               if(p == 17 || p == 25 || p == 33 || p == 41)
               {
                  int[] posPositions = new int[6];
                  posPositions[0] = p - 17;
                  posPositions[1] = p - 15;
                  posPositions[2] = p - 6;
                  posPositions[3] = p + 10;
                  posPositions[4] = p + 15;
                  posPositions[5] = p + 17;
                           
                  for(int x = 0; x < posPositions.length; x++)
                  {
                     if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                  }
               }
               caseCounterN++;
            } 
            if(caseCounterN == 4)
            {     
               if(p == 22 || p == 30 || p == 38 || p == 46)
               {
                  int[] posPositions = new int[6];
                  posPositions[0] = p - 17;
                  posPositions[1] = p - 15;
                  posPositions[2] = p - 10;
                  posPositions[3] = p + 6;
                  posPositions[4] = p + 15;
                  posPositions[5] = p + 17;
                           
                  for(int x = 0; x < posPositions.length; x++)
                  {
                     if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           
                  }
               }
               caseCounterN++;
             }
             if(caseCounterN == 5)
             {     
                if(p == 0)
                {
                   int[] posPositions = new int[2];
                   posPositions[0] = p + 10;
                   posPositions[1] = p + 17;
                           
                   for(int x = 0; x < posPositions.length; x++)
                   {
                      if(g.whiteTurn)
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                      else
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                   }
                }
                caseCounterN++;
             }
             if(caseCounterN == 6)
             {     
                if(p == 7)
                {
                   int[] posPositions = new int[2];
                   posPositions[0] = p + 6;
                   posPositions[1] = p + 15;
                           
                   for(int x = 0; x < posPositions.length; x++)
                   {
                      if(g.whiteTurn)
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                      else
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                   } 
                }
                caseCounterN++;
             }
             if(caseCounterN == 7)
             {     
                if(p == 56)
                {
                   int[] posPositions = new int[2];
                   posPositions[0] = p - 15;
                   posPositions[1] = p - 6;
                           
                   for(int x = 0; x < posPositions.length; x++)
                   {
                      if(g.whiteTurn)
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                      else
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                   }
                }
                caseCounterN++;
             }
             if(caseCounterN == 8)
             {     
                if(p == 63)
                {
                   int[] posPositions = new int[2];
                   posPositions[0] = p - 17;
                   posPositions[1] = p - 10;
                           
                   for(int x = 0; x < posPositions.length; x++)
                   {
                      if(g.whiteTurn)
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                      else
                      {
                         if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                         {
                            g.possibleMoves.add(posPositions[x]);
                            g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                         }
                      }
                   }
                }
                caseCounterN++;
             }
                     if(caseCounterN == 9)
                     {     
                        if(p == 1)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p + 15;
                           posPositions[1] = p + 17;
                           posPositions[2] = p + 10;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 10)
                     {     
                        if(p == 6)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p + 6;
                           posPositions[1] = p + 15;
                           posPositions[2] = p + 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 11)
                     {     
                        if(p == 57)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 17;
                           posPositions[1] = p - 15;
                           posPositions[2] = p - 6;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 12)
                     {     
                        if(p == 62)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 10;
                           posPositions[1] = p - 17;
                           posPositions[2] = p - 15;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                     if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 13)
                     {     
                        if(p == 8)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 6;
                           posPositions[1] = p + 10;
                           posPositions[2] = p + 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 14)
                     {     
                        if(p == 15)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 10;
                           posPositions[1] = p + 6;
                           posPositions[2] = p + 15;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 15)
                     {     
                        if(p == 48)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 15;
                           posPositions[1] = p - 6;
                           posPositions[2] = p + 10;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 16)
                     {     
                        if(p == 55)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 17;
                           posPositions[1] = p - 10;
                           posPositions[2] = p + 6;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 17)
                     {     
                        if(p == 9)
                        {
                           int[] posPositions = new int[4];
                           posPositions[0] = p - 6;
                           posPositions[1] = p + 10;
                           posPositions[2] = p + 15;
                           posPositions[3] = p + 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 18)
                     {     
                        if(p == 14)
                        {
                           int[] posPositions = new int[4];
                           posPositions[0] = p - 10;
                           posPositions[1] = p + 6;
                           posPositions[2] = p + 15;
                           posPositions[3] = p + 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 19)
                     {     
                        if(p == 49)
                        {
                           int[] posPositions = new int[4];
                           posPositions[0] = p - 17;
                           posPositions[1] = p - 15;
                           posPositions[2] = p - 6;
                           posPositions[3] = p + 10;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 20)
                     {     
                        if(p == 54)
                        {
                           int[] posPositions = new int[4];
                           posPositions[0] = p - 17;
                           posPositions[1] = p - 15;
                           posPositions[2] = p - 10;
                           posPositions[3] = p + 6;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 21)
                     {     
                        if(p == 2 || p == 3 || p == 4 || p == 5)
                        {
                           int[] posPositions = new int[4];
                           posPositions[0] = p + 6;
                           posPositions[1] = p + 10;
                           posPositions[2] = p + 15;
                           posPositions[3] = p + 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 22)
                     {     
                        if(p == 58 || p == 59 || p == 60 || p == 61)
                        {
                           int[] posPositions = new int[4];
                           posPositions[0] = p - 6;
                           posPositions[1] = p - 10;
                           posPositions[2] = p - 15;
                           posPositions[3] = p - 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 23)
                     {    
                        if(p == 10 || p == 11 || p == 12 || p == 13)
                        {
                           int[] posPositions = new int[6];
                           posPositions[0] = p - 10;
                           posPositions[1] = p - 6;
                           posPositions[2] = p + 6;
                           posPositions[3] = p + 10;
                           posPositions[4] = p + 15;
                           posPositions[5] = p + 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                     if(caseCounterN == 24)
                     {     
                        if(p == 50 || p == 51 || p == 52 || p == 53)
                        {
                           int[] posPositions = new int[6];
                           posPositions[0] = p + 10;
                           posPositions[1] = p + 6;
                           posPositions[2] = p - 6;
                           posPositions[3] = p - 10;
                           posPositions[4] = p - 15;
                           posPositions[5] = p - 17;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(g.whiteTurn)
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'B')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                     else
                     {
                        if(g.pieces[posPositions[x]] == " " || g.pieces[posPositions[x]].charAt(0) == 'W')
                        {
                           g.possibleMoves.add(posPositions[x]);
                           g.possibleMoveSquareColors.add(g.squareColors[posPositions[x]]);
                        }
                     }
                           }
                        }
                        caseCounterN++;
                     }
                  }                                     
         
      }
   }


}