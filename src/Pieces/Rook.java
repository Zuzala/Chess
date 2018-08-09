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
   private String[] pieces;
   private int p;
   private boolean whiteTurn;
   
   private ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
   private ArrayList<Color> possibleMoveSquareColors = new ArrayList<Color>();
   private boolean isPinned;
   private boolean pinCases;
   private Integer[] caseNums = new Integer[2];
   private Integer[] directionPinnedFromCaseNums;
   
   public Rook(int position, ChessGUI2 gee) 
   {
      p = position;
      g = gee;
      this.whiteTurn = g.getTurn();
      this.pieces = g.getPieces();
      this.isPinned = g.getIsPinned();
      
      if(isPinned) {
    	  this.directionPinnedFromCaseNums = g.getDirectionPinnedFromCaseNums();
    	  highlightMovesR(directionPinnedFromCaseNums);
      }
      else
      {
    	  caseNums[0] = 0;
    	  caseNums[1] = 0;
    	  highlightMovesR(caseNums);
      }
   }
   
   
   public void highlightMovesR(Integer[] cases)
   { 
	   int caseCounterR;
	   pinCases = false;
	   
		   if(cases[0] == 0 && cases[1] == 0)
		   {
			   caseCounterR = 0;
		   }
		   else
		   {
			   pinCases = true;
			   caseCounterR = cases[0];	   
		   }
		   
      boolean continueLine = true;
      
         if(whiteTurn)
         {
            while(caseCounterR < 4)
                  {
                     if(caseCounterR == 0)
                     {
                       continueLine = true;
                        if(p + 1 <= 63 && pieces[p+1].charAt(0) != 'W')
                        {
                           eHorizCheck:
                           for(int eHoriz = p+1; eHoriz <= 63;  eHoriz++)
                           {  
                              if(pieces[eHoriz].charAt(0) == 'W' || pieces[eHoriz].charAt(0) == 'B' || eHoriz == 7 || eHoriz == 15 || eHoriz == 23 || eHoriz == 31 || 
                              eHoriz == 39 || eHoriz == 47 || eHoriz == 55 || eHoriz == 63)
                              {
                                 continueLine = false;
                              }
                              if(p == 7 || p == 15 || p == 23 || p == 31 || 
                               p == 39 || p == 47 || p == 55 || p == 63)
                              {
                                 break eHorizCheck;
                              }

                              
                              int posPosition = eHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                                 {
                                    break eHorizCheck;
                                 }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        } 
                     } 
                     
                     if(caseCounterR == 1)
                     {
                       continueLine = true;
                        if(p - 1 >= 0 && pieces[p-1].charAt(0) != 'W')
                        {
                           wHorizCheck:
                           for(int wHoriz = p-1; wHoriz >= 0;  wHoriz--)
                           {  
                              if(pieces[wHoriz].charAt(0) == 'W' || pieces[wHoriz].charAt(0) == 'B' || wHoriz == 0 || wHoriz == 8 || wHoriz == 16 || wHoriz == 24 || 
                              wHoriz == 32 || wHoriz == 40 || wHoriz == 48 || wHoriz == 56)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 8 || p == 16 || p == 24 || 
                               p == 32 || p == 40 || p == 48 || p == 56)
                              {
                                 break wHorizCheck;
                              }
   
                                 
                              int posPosition = wHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                              {
                                 break wHorizCheck;
                              }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     } 
                     if(caseCounterR == 2)
                     {
                       continueLine = true;
                        if(p - 8 >= 0 && pieces[p-8].charAt(0) != 'W')
                        {
                           nHorizCheck:
                           for(int nHoriz = p-8; nHoriz >= 0; nHoriz -= 8)
                           {  
                              if(pieces[nHoriz].charAt(0) == 'W' || pieces[nHoriz].charAt(0) == 'B' || nHoriz == 0 || nHoriz == 1 || nHoriz == 2 || nHoriz == 3 || 
                              nHoriz == 4 || nHoriz == 5 || nHoriz == 6 || nHoriz == 7)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 1 || p == 2 || p == 3 || 
                               p == 4 || p == 5 || p == 6 || p == 7)
                              {
                                 break nHorizCheck;
                              }
   
                                 
                              int posPosition = nHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                              {
                                 break nHorizCheck;
                              }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     } 
                     if(caseCounterR == 3)
                     {
                       continueLine = true;
                        if(p + 8 <=63 && pieces[p+8].charAt(0) != 'W')
                        {
                           sHorizCheck:
                           for(int sHoriz = p+8; sHoriz <= 63; sHoriz += 8)
                           {  
                              if(pieces[sHoriz].charAt(0) == 'W' || pieces[sHoriz].charAt(0) == 'B' || sHoriz == 56 || sHoriz == 57 || sHoriz == 58 || sHoriz == 59 || 
                              sHoriz == 60 || sHoriz == 61 || sHoriz == 62 || sHoriz == 63)
                              {
                                 continueLine = false;
                              }
                              if(p == 56 || p == 57 || p == 58 || p == 59 || 
                               p == 60 || p == 61 || p == 62 || p == 63)
                              {
                                 break sHorizCheck;
                              }
   
                                 
                              int posPosition = sHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                              {
                                 break sHorizCheck;
                              }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     }
                  }

         
         }
      
         if(whiteTurn == false)
         {
            while(caseCounterR < 4)
                  {
                     if(caseCounterR == 0)
                     {
                       continueLine = true;
                        if(p + 1 <= 63 && pieces[p+1].charAt(0) != 'B')
                        {
                           eHorizCheck:
                           for(int eHoriz = p+1; eHoriz <= 63;  eHoriz++)
                           {  
                              if(pieces[eHoriz].charAt(0) == 'W' || pieces[eHoriz].charAt(0) == 'B' || eHoriz == 7 || eHoriz == 15 || eHoriz == 23 || eHoriz == 31 || 
                              eHoriz == 39 || eHoriz == 47 || eHoriz == 55 || eHoriz == 63)
                              {
                                 continueLine = false;
                              }
                              
                              if(p == 7 || p == 15 || p == 23 || p == 31 || 
                               p == 39 || p == 47 || p == 55 || p == 63)
                              {
                                 break eHorizCheck;
                              }
                                 
                              int posPosition = eHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                                 {
                                    break eHorizCheck;
                                 }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     } 
                     
                     if(caseCounterR == 1)
                     {
                       continueLine = true;
                        if(p - 1 >= 0 && pieces[p-1].charAt(0) != 'B')
                        {
                           wHorizCheck:
                           for(int wHoriz = p-1; wHoriz >= 0;  wHoriz--)
                           {  
                              if(pieces[wHoriz].charAt(0) == 'W' || pieces[wHoriz].charAt(0) == 'B' || wHoriz == 0 || wHoriz == 8 || wHoriz == 16 || wHoriz == 24 || 
                              wHoriz == 32 || wHoriz == 40 || wHoriz == 48 || wHoriz == 56)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 8 || p == 16 || p == 24 || 
                               p == 32 || p == 40 || p == 48 || p == 56)
                              {
                                 break wHorizCheck;
                              }
   
                                 
                              int posPosition = wHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                                 {
                                    break wHorizCheck;
                                 }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     } 
                     if(caseCounterR == 2)
                     {
                       continueLine = true;
                        if(p - 8 >= 0 && pieces[p-8].charAt(0) != 'B')
                        {
                           nHorizCheck:
                           for(int nHoriz = p-8; nHoriz >= 0; nHoriz -= 8)
                           {  
                              if(pieces[nHoriz].charAt(0) == 'W' || pieces[nHoriz].charAt(0) == 'B' || nHoriz == 0 || nHoriz == 1 || nHoriz == 2 || nHoriz == 3 || 
                              nHoriz == 4 || nHoriz == 5 || nHoriz == 6 || nHoriz == 7)
                              {
                                 continueLine = false;
                              }
                              if(p == 0 || p == 1 || p == 2 || p == 3 || 
                               p == 4 || p == 5 || p == 6 || p == 7)
                              {
                                 break nHorizCheck;
                              }
   
                                 
                              int posPosition = nHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                                 {
                                    break nHorizCheck;
                                 }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     } 
                     if(caseCounterR == 3)
                     {
                       continueLine = true;
                        if(p + 8 <=63 && pieces[p+8].charAt(0) != 'B')
                        {
                           sHorizCheck:
                           for(int sHoriz = p+8; sHoriz <= 63; sHoriz += 8)
                           {  
                              if(pieces[sHoriz].charAt(0) == 'W' || pieces[sHoriz].charAt(0) == 'B' || sHoriz == 56 || sHoriz == 57 || sHoriz == 58 || sHoriz == 59 || 
                              sHoriz == 60 || sHoriz == 61 || sHoriz == 62 || sHoriz == 63)
                              {
                                 continueLine = false;
                              }
                              if(p == 56 || p == 57 || p == 58 || p == 59 || 
                               p == 60 || p == 61 || p == 62 || p == 63)
                              {
                                 break sHorizCheck;
                              }
   
                              int posPosition = sHoriz;
                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                              }
                              if(continueLine == false)
                                 {
                                    break sHorizCheck;
                                 }
                           }
                        }
                        if(isPinned && caseCounterR == cases[1])
                        {
                        	return;
                        }
                        else if(pinCases)
                        {
                      	  caseCounterR = cases[1];
                        }
                        else
                        {
                        	caseCounterR++; 
                        }
                     }
                  }

         }
      



   }

   
   
   
   
   
   public ArrayList<Integer> getPossibleMoves()
   {
	   return this.possibleMoves;
   }
   
   public ArrayList<Color> getPossibleMoveSquareColors()
   {
	   return this.possibleMoveSquareColors;
   }

}