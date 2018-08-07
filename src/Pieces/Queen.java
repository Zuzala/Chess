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
   private String[] pieces;
   private int p;
   private boolean whiteTurn;
   private String checkType;
   private boolean connectedToFriendlyKing = false;
   private ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
   private ArrayList<Color> possibleMoveSquareColors = new ArrayList<Color>();
   private Map<Boolean, Integer> directionCases = new HashMap<Boolean, Integer>();
   private boolean enemyPinnerFound;

   
   public Queen(int position, ChessGUI2 gee, String checkType) 
   {
      p = position;
      g = gee;
      this.checkType = checkType;
      this.whiteTurn = g.getTurn();
      this.pieces = g.getPieces();
      
   	  
      switch(checkType)
      {
      	case "pin":
      		highlightMovesQ();
      		
      		break;
      	case "check":
      		
      		break;
      	default:
      		highlightMovesQ();
      		break;
      }
      
      
   }
   

   
   //checks if piece is connected to friendly king by diagonal or horizontal
   public boolean isKingConnected()
   {
	   return connectedToFriendlyKing;
   }
   
   
   public Map<Boolean, Integer> getPinFromDirection()
   {
	   return directionCases;
   }
   
   public boolean wasEnemyPinnerFound()
   {
	   return enemyPinnerFound;
   }
   
   
   public void queenDiagCheck(int initialCase)
   {
	   int caseCounterQB = initialCase;
	   
	   if(caseCounterQB > 0)
	   {
		      possibleMoves.clear();
		      possibleMoveSquareColors.clear();
	   }
	   boolean continueLine = true;
	      
       if(whiteTurn)
       {
          while(caseCounterQB < 4)
                {
                   if(caseCounterQB == 0)
                   {
                      continueLine = true;
                      
                      if(p - 9 >= 0 && pieces[p-9].charAt(0) != 'W' || (p - 9 >= 0 && pieces[p-9] != " " && pieces[p-9].charAt(0) == 'W' && pieces[p-9].charAt(1) == 'K'))
                      {
                         nWestDiagCheck:
                         for(int nWestDiag = p-9; nWestDiag >= 0; nWestDiag -= 9)
                         {
                            if((pieces[nWestDiag].charAt(0) == 'W' && pieces[nWestDiag].charAt(1) != 'K') || pieces[nWestDiag].charAt(0) == 'B' || nWestDiag == 0 || nWestDiag == 8 || nWestDiag == 16 || nWestDiag == 24 || 
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
                            if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            {
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
                            	{
                            		connectedToFriendlyKing = true;
                            		directionCases.put(true, 3);
                            	}
                            	else
                            	{
                            		possibleMoves.add(posPosition);
                                    possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            	}
                               
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
                      if(p - 7 >= 0 && pieces[p-7].charAt(0) != 'W' || (p - 7 >= 0 && pieces[p-7] != " " &&  pieces[p-7].charAt(0) == 'W' && pieces[p-7].charAt(1) == 'K'))
                      {
                         nEastDiagCheck:
                         for(int nEastDiag = p-7; nEastDiag >= 0; nEastDiag -= 7)
                         {
                            if((pieces[nEastDiag].charAt(0) == 'W'  && pieces[nEastDiag].charAt(1) != 'K') || pieces[nEastDiag].charAt(0) == 'B' || nEastDiag == 7 || nEastDiag == 15 || nEastDiag == 23 || nEastDiag == 31 || 
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
                            if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            {
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
                            	{
                            		connectedToFriendlyKing = true;
                            		directionCases.put(true, 2);
                            	}
                            	else
                            	{
                            		possibleMoves.add(posPosition);
                                    possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            	}
                               
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
                      if(p + 7 <= 63 && pieces[p+7].charAt(0) != 'W'  || (p + 7 <= 63 && pieces[p+7] != " " && pieces[p+7].charAt(0) == 'W' && pieces[p+7].charAt(1) == 'K'))
                      {
                         sWestDiagCheck:
                         for(int sWestDiag = p+7; sWestDiag >= 0; sWestDiag += 7)
                         {
                            if((pieces[sWestDiag].charAt(0) == 'W' && pieces[sWestDiag].charAt(1) != 'K') || pieces[sWestDiag].charAt(0) == 'B' || sWestDiag == 8 || sWestDiag == 16 || sWestDiag == 24 || sWestDiag == 32 || 
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
                            if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            {
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
                            	{
                            		connectedToFriendlyKing = true;
                            		directionCases.put(true, 1);
                            	}
                            	else
                            	{
                            		possibleMoves.add(posPosition);
                                    possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            	}
                               
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
                      if(p + 9 <= 63 && pieces[p+9].charAt(0) != 'W' || (p + 9 <= 63 && pieces[p+9] != " " && pieces[p+9].charAt(0) == 'W' && pieces[p+9].charAt(1) == 'K'))
                      {
                         sEastDiagCheck:
                         for(int sEastDiag = p+9; sEastDiag >= 0; sEastDiag += 9)
                         {  
                            if((pieces[sEastDiag].charAt(0) == 'W' && pieces[sEastDiag].charAt(1) != 'K') || pieces[sEastDiag].charAt(0) == 'B' || sEastDiag == 15 || sEastDiag == 23 || sEastDiag == 31 || sEastDiag == 39 || 
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
                            if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            {
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
                            	{
                            		connectedToFriendlyKing = true;
                            		directionCases.put(true, 0);
                            	}
                            	else
                            	{
                            		possibleMoves.add(posPosition);
                                    possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            	}
                               
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
       }
       
       if(whiteTurn == false)
       {
          while(caseCounterQB < 4)
                {
                   if(caseCounterQB == 0)
                   {
                      continueLine = true;
                      if(p - 9 >= 0 && pieces[p-9].charAt(0) != 'B' || (p - 9 >= 0 && pieces[p-9] != " " && pieces[p-9].charAt(0) == 'B' && pieces[p-9].charAt(1) == 'K'))
                      {
                         nWestDiagCheck:
                         for(int nWestDiag = p-9; nWestDiag >= 0; nWestDiag -= 9)
                         {
                               if((pieces[nWestDiag].charAt(0) == 'B' && pieces[nWestDiag].charAt(1) != 'K') || pieces[nWestDiag].charAt(0) == 'W' || nWestDiag == 0 || nWestDiag == 8 || nWestDiag == 16 || nWestDiag == 24 || 
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
                               if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                               {
                               	if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
                               	{
                               		connectedToFriendlyKing = true;
                               		directionCases.put(true, 3);
                               	}
                               	else
                               	{
                               		possibleMoves.add(posPosition);
                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                               	}
                                  
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
                      if(p - 7 >= 0 && pieces[p-7].charAt(0) != 'B' || (p - 7 >= 0 && pieces[p-7] != " " && pieces[p-7].charAt(0) == 'B' && pieces[p-7].charAt(1) == 'K'))
                      {
                         nEastDiagCheck:
                         for(int nEastDiag = p-7; nEastDiag >= 0; nEastDiag -= 7)
                         {
                               if((pieces[nEastDiag].charAt(0) == 'B'  && pieces[nEastDiag].charAt(1) != 'K') || pieces[nEastDiag].charAt(0) == 'W' || nEastDiag == 7 || nEastDiag == 15 || nEastDiag == 23 || nEastDiag == 31 || 
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
                               if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                               {
                               	if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
                               	{
                               		connectedToFriendlyKing = true;
                               		directionCases.put(true, 2);
                               	}
                               	else
                               	{
                               		possibleMoves.add(posPosition);
                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                               	}
                                  
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
                      if(p + 7 <= 63 && pieces[p+7].charAt(0) != 'B' || (p + 7 <= 63 && pieces[p+7] != " " && pieces[p+7].charAt(0) == 'B' && pieces[p+7].charAt(1) == 'K'))
                      {
                         sWestDiagCheck:
                         for(int sWestDiag = p+7; sWestDiag >= 0; sWestDiag += 7)
                         {
                               if((pieces[sWestDiag].charAt(0) == 'B'  && pieces[sWestDiag].charAt(1) != 'K') || pieces[sWestDiag].charAt(0) == 'W' || sWestDiag == 8 || sWestDiag == 16 || sWestDiag == 24 || sWestDiag == 32 || 
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
                               if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                               {
                               	if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
                               	{
                               		connectedToFriendlyKing = true;
                               		directionCases.put(true, 1);
                               	}
                               	else
                               	{
                               		possibleMoves.add(posPosition);
                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                               	}
                                  
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
                      if(p + 9 <= 63 && pieces[p+9].charAt(0) != 'B' || (p + 9 >= 0 && pieces[p+9] != " " && pieces[p+9].charAt(0) == 'B' && pieces[p+9].charAt(1) == 'K'))
                      {
                         sEastDiagCheck:
                         for(int sEastDiag = p+9; sEastDiag >= 0; sEastDiag += 9)
                         {
                               if((pieces[sEastDiag].charAt(0) == 'B' && pieces[sEastDiag].charAt(1) != 'K') || pieces[sEastDiag].charAt(0) == 'W' || sEastDiag == 15 || sEastDiag == 23 || sEastDiag == 31 || sEastDiag == 39 || 
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
                               if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                               {
                               	if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
                               	{
                               		connectedToFriendlyKing = true;
                               		directionCases.put(true, 0);
                               	}
                               	else
                               	{
                               		possibleMoves.add(posPosition);
                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                               	}
                                  
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
                 

       }
   }
   
   public void queenHorizCheck(int initialCase)
   {
	   
	   int caseCounterQR = initialCase;
	   if(caseCounterQR > 0)
	   {
		      possibleMoves.clear();
		      possibleMoveSquareColors.clear();
	   }
	   boolean continueLine = true;
	      
	         if(whiteTurn)
	         {
	                  while(caseCounterQR < 4)
	                  {
	                     if(caseCounterQR == 0)
	                     {
	                       continueLine = true;
	                        if(p + 1 <= 63 && pieces[p+1].charAt(0) != 'W' || (p + 1 <= 63 && pieces[p+1] != " " && pieces[p+1].charAt(0) == 'W' && pieces[p+1].charAt(1) == 'K'))
	                        {
	                           eHorizCheck:
	                           for(int eHoriz = p+1; eHoriz <= 63;  eHoriz++)
	                           {  
	                              if((pieces[eHoriz].charAt(0) == 'W' && pieces[eHoriz].charAt(1) != 'K') || pieces[eHoriz].charAt(0) == 'B' || eHoriz == 7 || eHoriz == 15 || eHoriz == 23 || eHoriz == 31 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 1);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break eHorizCheck;
	                                 }
	                           }
	                        }
	                        caseCounterQR++;
	                     } 
	                     
	                     if(caseCounterQR == 1)
	                     {
	                       continueLine = true;
	                        if(p - 1 >= 0 && pieces[p-1].charAt(0) != 'W' || (p - 1 >= 0 && pieces[p-1] != " " && pieces[p-1].charAt(0) == 'W' && pieces[p-1].charAt(1) == 'K'))
	                        {
	                           wHorizCheck:
	                           for(int wHoriz = p-1; wHoriz >= 0;  wHoriz--)
	                           {  
	                              if((pieces[wHoriz].charAt(0) == 'W'  && pieces[wHoriz].charAt(1) != 'K') || pieces[wHoriz].charAt(0) == 'B' || wHoriz == 0 || wHoriz == 8 || wHoriz == 16 || wHoriz == 24 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 0);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                              {
	                                 break wHorizCheck;
	                              }
	                           }
	                        }
	                        caseCounterQR++;
	                     } 
	                     if(caseCounterQR == 2)
	                     {
	                       continueLine = true;
	                        if(p - 8 >= 0 && pieces[p-8].charAt(0) != 'W' || (p - 8 >= 0 && pieces[p-8] != " " && pieces[p-8].charAt(0) == 'W' && pieces[p-8].charAt(1) == 'K'))
	                        {
	                           nHorizCheck:
	                           for(int nHoriz = p-8; nHoriz >= 0; nHoriz -= 8)
	                           {  
	                              if((pieces[nHoriz].charAt(0) == 'W'  && pieces[nHoriz].charAt(1) != 'K') || pieces[nHoriz].charAt(0) == 'B' || nHoriz == 0 || nHoriz == 1 || nHoriz == 2 || nHoriz == 3 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 3);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                              {
	                                 break nHorizCheck;
	                              }
	                           }
	                        }
	                        caseCounterQR++;
	                     } 
	                     if(caseCounterQR == 3)
	                     {
	                       continueLine = true;
	                        if(p + 8 <=63 && pieces[p+8].charAt(0) != 'W' || (p + 8 <= 63 && pieces[p+8] != " " && pieces[p+8].charAt(0) == 'W' && pieces[p+8].charAt(1) == 'K'))
	                        {
	                           sHorizCheck:
	                           for(int sHoriz = p+8; sHoriz <= 63; sHoriz += 8)
	                           {  
	                              if((pieces[sHoriz].charAt(0) == 'W'  && pieces[sHoriz].charAt(1) != 'K') || pieces[sHoriz].charAt(0) == 'B' || sHoriz == 56 || sHoriz == 57 || sHoriz == 58 || sHoriz == 59 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'B' ||  (pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 2);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                              {
	                                 break sHorizCheck;
	                              }
	                           }
	                        }
	                        caseCounterQR++;
	                     }
	                  }

	         }
	         
	         if(whiteTurn == false)
	         {	                   
	                  while(caseCounterQR < 4)
	                  {
	                     if(caseCounterQR == 0)
	                     {
	                       continueLine = true;
	                        if(p + 1 <= 63 && pieces[p+1].charAt(0) != 'B' || (p + 1 <= 63 && pieces[p+1] != " " && pieces[p+1].charAt(0) == 'B' && pieces[p+1].charAt(1) == 'K'))
	                        {
	                           eHorizCheck:
	                           for(int eHoriz = p+1; eHoriz <= 63;  eHoriz++)
	                           {  
	                              if((pieces[eHoriz].charAt(0) == 'B' && pieces[eHoriz].charAt(1) != 'K') || pieces[eHoriz].charAt(0) == 'W' || eHoriz == 7 || eHoriz == 15 || eHoriz == 23 || eHoriz == 31 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 1);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break eHorizCheck;
	                                 }
	                           }
	                        }
	                        caseCounterQR++;
	                     } 
	                     
	                     if(caseCounterQR == 1)
	                     {
	                       continueLine = true;
	                        if(p - 1 >= 0 && pieces[p-1].charAt(0) != 'B' || (p - 1 >= 0 && pieces[p-1] != " " && pieces[p-1].charAt(0) == 'B' && pieces[p-1].charAt(1) == 'K'))
	                        {
	                           wHorizCheck:
	                           for(int wHoriz = p-1; wHoriz >= 0;  wHoriz--)
	                           {  
	                              if((pieces[wHoriz].charAt(0) == 'B'  && pieces[wHoriz].charAt(1) != 'K') || pieces[wHoriz].charAt(0) == 'W' || wHoriz == 0 || wHoriz == 8 || wHoriz == 16 || wHoriz == 24 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 0);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break wHorizCheck;
	                                 }
	                           }
	                        }
	                         caseCounterQR++;
	                     } 
	                     if(caseCounterQR == 2)
	                     {
	                       continueLine = true;
	                        if(p - 8 >= 0 && pieces[p-8].charAt(0) != 'B' || (p - 8 >= 0 && pieces[p-8] != " " && pieces[p-8].charAt(0) == 'B' && pieces[p-8].charAt(1) == 'K'))
	                        {
	                           nHorizCheck:
	                           for(int nHoriz = p-8; nHoriz >= 0; nHoriz -= 8)
	                           {  
	                              if((pieces[nHoriz].charAt(0) == 'B'  && pieces[nHoriz].charAt(1) != 'K') || pieces[nHoriz].charAt(0) == 'W' || nHoriz == 0 || nHoriz == 1 || nHoriz == 2 || nHoriz == 3 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 3);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break nHorizCheck;
	                                 }
	                           }
	                        }
	                        caseCounterQR++;
	                     } 
	                     if(caseCounterQR == 3)
	                     {
	                       continueLine = true;
	                        if(p + 8 <=63 && pieces[p+8].charAt(0) != 'B' || (p + 8 <= 63 && pieces[p+8] != " " && pieces[p+8].charAt(0) == 'B' && pieces[p+8].charAt(1) == 'K'))
	                        {
	                           sHorizCheck:
	                           for(int sHoriz = p+8; sHoriz <= 63; sHoriz += 8)
	                           {  
	                              if((pieces[sHoriz].charAt(0) == 'B'  && pieces[sHoriz].charAt(1) != 'K') || pieces[sHoriz].charAt(0) == 'W' || sHoriz == 56 || sHoriz == 57 || sHoriz == 58 || sHoriz == 59 || 
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
	                              if(pieces[posPosition] == " " || pieces[posPosition].charAt(0) == 'W' ||  (pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                               {
	                               	if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K')
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		directionCases.put(true, 2);
	                               	}
	                               	else
	                               	{
	                               		possibleMoves.add(posPosition);
	                                       possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break sHorizCheck;
	                                 }
	                           }
	                        }
	                        caseCounterQR++;
	                     }
	                  }

	         }
   }
   
   
   
   
   public void highlightMovesQ()
   { 
	      possibleMoves.clear();
	      possibleMoveSquareColors.clear();
	   
	  queenDiagCheck(0);
      queenHorizCheck(0);
      



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