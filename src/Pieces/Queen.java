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
   private boolean connectedToFriendlyKing = false;
   private ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
   private ArrayList<Color> possibleMoveSquareColors = new ArrayList<Color>();
   private Integer[] caseNums = new Integer[2];
   
   private boolean pinCases;
   private Map<Boolean, Integer[]> directionPinCases = new HashMap<Boolean, Integer[]>();
   private boolean enemyPinnerFound;
   private boolean isPinned;
   private boolean pinnedOnDiag;
   private Integer[] directionPinnedFromCaseNums;
   private boolean pinSweeping;
   
   private boolean checkCases;
   private Map<Boolean, Integer> directionCheckCases = new HashMap<Boolean, Integer>();
   private boolean kingInCheck;
   private boolean checkedOnDiag;
   private int directionCheckedFromCaseNum;
   private boolean checkSweeping;
   
   public Queen(int position, ChessGUI2 gee) 
   {
      p = position;
      g = gee;
      this.whiteTurn = g.getTurn();
      this.pieces = g.getPieces();
      this.isPinned = g.getIsPinned();
      this.pinSweeping = g.pinSweeping();
      this.checkSweeping = g.checkSweeping();
      
      if(!checkSweeping)
      {
    	  if(isPinned) {
        	  this.pinnedOnDiag = g.getDirectionPinnedFrom();
        	  this.directionPinnedFromCaseNums = g.getDirectionPinnedFromCaseNums();
        	  if(pinnedOnDiag)  //if pinned on diag
        	  {
        		  queenDiagCheck(directionPinnedFromCaseNums);
        	  }
        	  else  //if pinned on horiz
        	  {
        		  queenHorizCheck(directionPinnedFromCaseNums);
        	  }
          }
          else
          {
        	  caseNums[0] = 0;
        	  caseNums[1] = 0;
        	  highlightMovesQ(caseNums);
          }
      }
      else
      {
    	  if(kingInCheck)
    	  {
    		  this.checkedOnDiag = g.getDirectionCheckedFrom();
        	  this.directionCheckedFromCaseNum = g.getDirectionCheckedFromCaseNum();
        	  caseNums[0] = directionCheckedFromCaseNum;
        	  caseNums[1] = directionCheckedFromCaseNum;
        	  if(checkedOnDiag)  //if checked on diag
        	  {
        		  queenDiagCheck(caseNums);
        	  }
        	  else  //if checked on horiz
        	  {
        		  queenHorizCheck(caseNums);
        	  }
    	  }
    	  else
    	  {
    		  caseNums[0] = 0;
        	  caseNums[1] = 0;
        	  highlightMovesQ(caseNums);
    	  }
      }
      
      
   }
   

   
   //checks if piece is connected to friendly king by diagonal or horizontal
   public boolean isKingConnected()
   {
	   return connectedToFriendlyKing;
   }
   
   
   public Map<Boolean, Integer[]> getPinFromDirection()
   {
	   return directionPinCases;
   }
   
   public boolean wasEnemyPinnerFound()
   {
	   return enemyPinnerFound;
   }
   
   public Map<Boolean, Integer> getCheckFromDirection()
   {
	   return directionCheckCases;
   }
   
   public boolean kingInCheck()
   {
	   return kingInCheck;
   }
   
   
   public void queenDiagCheck(Integer[] cases)
   {
	   int caseCounterQB;
	   pinCases = false;
	   enemyPinnerFound = false;
	   
		   if(cases[0] == 0 && cases[1] == 0)
		   {
			   caseCounterQB = 0;
		   }
		   else
		   {
			   pinCases = true;
			   caseCounterQB = cases[0];	   
		   }


	   
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
                            if(pieces[nWestDiag] != " " || nWestDiag == 0 || nWestDiag == 8 || nWestDiag == 16 || nWestDiag == 24 || 
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
                            	if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            	{
                            		kingInCheck = true;
                            		directionCheckCases.put(true, caseCounterQB);
                            	}
                            	
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                            	{
                            		connectedToFriendlyKing = true;
                            		caseNums[0] = caseCounterQB;
                            		caseNums[1] = 3;
                            		directionPinCases.put(true, caseNums);
                            	}
                            	else
                            	{
                            		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            		
                            	}
                               
                            } 
                            if(continueLine == false)
                            {
                               break nWestDiagCheck;
                            }
                         }
                      } 
                      
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                      	{
                      		return;
                      	}
                      	else
                      	{
                      		caseCounterQB = cases[1];
                      	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }
                                        
                   }
                   if(caseCounterQB == 1)
                   {
                      continueLine = true;
                      if(p - 7 >= 0 && pieces[p-7].charAt(0) != 'W' || (p - 7 >= 0 && pieces[p-7] != " " &&  pieces[p-7].charAt(0) == 'W' && pieces[p-7].charAt(1) == 'K'))
                      {
                         nEastDiagCheck:
                         for(int nEastDiag = p-7; nEastDiag >= 0; nEastDiag -= 7)
                         {
                            if(pieces[nEastDiag] != " " || nEastDiag == 7 || nEastDiag == 15 || nEastDiag == 23 || nEastDiag == 31 || 
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
                            	if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            	{
                            		kingInCheck = true;
                            		directionCheckCases.put(true, caseCounterQB);
                            	}
                            	
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                            	{
                            		connectedToFriendlyKing = true;
                            		caseNums[0] = caseCounterQB;
                            		caseNums[1] = 2;
                            		directionPinCases.put(true, caseNums);
                            	}
                            	else
                            	{
                            		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                               
                            }     
                            if(continueLine == false)
                            {
                               break nEastDiagCheck;
                            }
                      }
                   } 
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                        	{
                        		return;
                        	}
                        	else
                        	{
                        		caseCounterQB = cases[1];
                        	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }                      
                }
                   if(caseCounterQB == 2)
                   {
                      continueLine = true;
                      if(p + 7 <= 63 && pieces[p+7].charAt(0) != 'W'  || (p + 7 <= 63 && pieces[p+7] != " " && pieces[p+7].charAt(0) == 'W' && pieces[p+7].charAt(1) == 'K'))
                      {
                         sWestDiagCheck:
                         for(int sWestDiag = p+7; sWestDiag >= 0; sWestDiag += 7)
                         {
                            if(pieces[sWestDiag] != " " || sWestDiag == 8 || sWestDiag == 16 || sWestDiag == 24 || sWestDiag == 32 || 
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
                            	if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            	{
                            		kingInCheck = true;
                            		directionCheckCases.put(true, caseCounterQB);
                            	}
                            	
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                            	{
                            		connectedToFriendlyKing = true;
                            		caseNums[0] = 1;
                            		caseNums[1] = caseCounterQB;
                            		directionPinCases.put(true, caseNums);
                            	}
                            	else
                            	{
                            		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                               
                            }
                            if(continueLine == false)
                            {
                               break sWestDiagCheck;
                            }
                      }
                   } 
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                        	{
                        		return;
                        	}
                        	else
                        	{
                        		caseCounterQB = cases[1];
                        	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }                       
                }
                   if(caseCounterQB == 3)
                   {
                      continueLine = true;
                      if(p + 9 <= 63 && pieces[p+9].charAt(0) != 'W' || (p + 9 <= 63 && pieces[p+9] != " " && pieces[p+9].charAt(0) == 'W' && pieces[p+9].charAt(1) == 'K'))
                      {
                         sEastDiagCheck:
                         for(int sEastDiag = p+9; sEastDiag >= 0; sEastDiag += 9)
                         {  
                            if(pieces[sEastDiag] != " " || sEastDiag == 15 || sEastDiag == 23 || sEastDiag == 31 || sEastDiag == 39 || 
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
                            	if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            	{
                            		kingInCheck = true;
                            		directionCheckCases.put(true, caseCounterQB);
                            	}
                            	
                            	if(connectedToFriendlyKing)
                            	{
                            		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                            		{
                            			enemyPinnerFound = true;
                            		}
                            		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                            	
                            	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                            	{
                            		connectedToFriendlyKing = true;
                            		caseNums[0] = 0;
                            		caseNums[1] = caseCounterQB;
                            		directionPinCases.put(true, caseNums);
                            	}
                            	else
                            	{
                            		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                            	}
                               
                            }
                            if(continueLine == false)
                            {
                               break sEastDiagCheck;
                            }
                         }
                      }
                   } 
                   if(isPinned && caseCounterQB == cases[1])
                   {
                   	return;
                   }
                   else if(pinCases)
                   {
                	   if(caseCounterQB == cases[1])
                     	{
                     		return;
                     	}
                     	else
                     	{
                     		caseCounterQB = cases[1];
                     	}
                   }
                   else
                   {
                   	caseCounterQB++; 
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
                      if(p - 9 >= 0 && pieces[p-9].charAt(0) != 'B' || (p - 9 >= 0 && pieces[p-9] != " " && pieces[p-9].charAt(0) == 'B' && pieces[p-9].charAt(1) == 'K'))
                      {
                         nWestDiagCheck:
                         for(int nWestDiag = p-9; nWestDiag >= 0; nWestDiag -= 9)
                         {
                               if(pieces[nWestDiag] != " " || nWestDiag == 0 || nWestDiag == 8 || nWestDiag == 16 || nWestDiag == 24 || 
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
                            	   if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               	{
                               		kingInCheck = true;
                               		directionCheckCases.put(true, caseCounterQB);
                               	}
                            	   
                            	   if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                               	{
                               		connectedToFriendlyKing = true;
                               		caseNums[0] = caseCounterQB;
                            		caseNums[1] = 3;
                               		directionPinCases.put(true, caseNums);
                               	}
                               	else
                               	{
                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                                  
                               }
                               if(continueLine == false)
                               {
                                  break nWestDiagCheck;
                               }
                         }
                      } 
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                        	{
                        		return;
                        	}
                        	else
                        	{
                        		caseCounterQB = cases[1];
                        	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }                      
                   }
                   if(caseCounterQB == 1)
                   {
                      continueLine = true;
                      if(p - 7 >= 0 && pieces[p-7].charAt(0) != 'B' || (p - 7 >= 0 && pieces[p-7] != " " && pieces[p-7].charAt(0) == 'B' && pieces[p-7].charAt(1) == 'K'))
                      {
                         nEastDiagCheck:
                         for(int nEastDiag = p-7; nEastDiag >= 0; nEastDiag -= 7)
                         {
                               if(pieces[nEastDiag] != " " || nEastDiag == 7 || nEastDiag == 15 || nEastDiag == 23 || nEastDiag == 31 || 
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
                            	   if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                                  	{
                                  		kingInCheck = true;
                                  		directionCheckCases.put(true, caseCounterQB);
                                  	}
                            	   
                            	   if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                               	{
                               		connectedToFriendlyKing = true;
                               		caseNums[0] = caseCounterQB;
                            		caseNums[1] = 2;
                               		directionPinCases.put(true, caseNums);
                               	}
                               	else
                               	{
                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                                  
                               }
                               if(continueLine == false)
                               {
                                  break nEastDiagCheck;
                               }
                         }
                      } 
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                        	{
                        		return;
                        	}
                        	else
                        	{
                        		caseCounterQB = cases[1];
                        	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }                       
                   }
                   if(caseCounterQB == 2)
                   {
                      continueLine = true;
                      if(p + 7 <= 63 && pieces[p+7].charAt(0) != 'B' || (p + 7 <= 63 && pieces[p+7] != " " && pieces[p+7].charAt(0) == 'B' && pieces[p+7].charAt(1) == 'K'))
                      {
                         sWestDiagCheck:
                         for(int sWestDiag = p+7; sWestDiag >= 0; sWestDiag += 7)
                         {
                               if(pieces[sWestDiag] != " " || sWestDiag == 8 || sWestDiag == 16 || sWestDiag == 24 || sWestDiag == 32 || 
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
                            	   if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                                  	{
                                  		kingInCheck = true;
                                  		directionCheckCases.put(true, caseCounterQB);
                                  	}
                            	   
                            	   if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                               	{
                               		connectedToFriendlyKing = true;
                               		caseNums[0] = 1;
                            		caseNums[1] = caseCounterQB;
                               		directionPinCases.put(true, caseNums);
                               	}
                               	else
                               	{
                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                                  
                               }
                               if(continueLine == false)
                               {
                                  break sWestDiagCheck;
                               }
                         }
                      } 
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                        	{
                        		return;
                        	}
                        	else
                        	{
                        		caseCounterQB = cases[1];
                        	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }                      
                   }
                   if(caseCounterQB == 3)
                   {
                      continueLine = true;
                      if(p + 9 <= 63 && pieces[p+9].charAt(0) != 'B' || (p + 9 <= 63 && pieces[p+9] != " " && pieces[p+9].charAt(0) == 'B' && pieces[p+9].charAt(1) == 'K'))
                      {
                         sEastDiagCheck:
                         for(int sEastDiag = p+9; sEastDiag >= 0; sEastDiag += 9)
                         {
                               if(pieces[sEastDiag] != " " || sEastDiag == 15 || sEastDiag == 23 || sEastDiag == 31 || sEastDiag == 39 || 
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
                            	   if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                                  	{
                                  		kingInCheck = true;
                                  		directionCheckCases.put(true, caseCounterQB);
                                  	}
                            	   
                            	   if(connectedToFriendlyKing)
                               	{
                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'B' || pieces[posPosition].charAt(1) == 'Q'))
                               		{
                               			enemyPinnerFound = true;
                               		}
                               		if(pinCases || !pinSweeping)
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                               	
                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
                               	{
                               		connectedToFriendlyKing = true;
                               		caseNums[0] = 0;
                            		caseNums[1] = caseCounterQB;
                               		directionPinCases.put(true, caseNums);
                               	}
                               	else
                               	{
                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
                            		{
                            			possibleMoves.add(posPosition);
                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
                            		}
                               	}
                                  
                               }
                               
                               if(continueLine == false)
                               {
                                  break sEastDiagCheck;
                               }
                         }
                      } 
                      if(isPinned && caseCounterQB == cases[1])
                      {
                      	return;
                      }
                      else if(pinCases)
                      {
                    	  if(caseCounterQB == cases[1])
                        	{
                        		return;
                        	}
                        	else
                        	{
                        		caseCounterQB = cases[1];
                        	}
                      }
                      else
                      {
                      	caseCounterQB++; 
                      }                      
                   }
                }
                 

       }
   }
   
   public void queenHorizCheck(Integer[] cases)
   {
	   int caseCounterQR;
	   pinCases = false;
	   enemyPinnerFound = false;
	   
		   if(cases[0] == 0 && cases[1] == 0)
		   {
			   caseCounterQR = 0;
		   }
		   else
		   {
			   pinCases = true;
			   caseCounterQR = cases[0];	   
		   }
		   
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
	                              if(pieces[eHoriz] != " " || eHoriz == 7 || eHoriz == 15 || eHoriz == 23 || eHoriz == 31 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = caseCounterQR;
	                            		caseNums[1] = 1;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break eHorizCheck;
	                                 }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     } 
	                     
	                     if(caseCounterQR == 1)
	                     {
	                       continueLine = true;
	                        if(p - 1 >= 0 && pieces[p-1].charAt(0) != 'W' || (p - 1 >= 0 && pieces[p-1] != " " && pieces[p-1].charAt(0) == 'W' && pieces[p-1].charAt(1) == 'K'))
	                        {
	                           wHorizCheck:
	                           for(int wHoriz = p-1; wHoriz >= 0;  wHoriz--)
	                           {  
	                              if(pieces[wHoriz] != " " || wHoriz == 0 || wHoriz == 8 || wHoriz == 16 || wHoriz == 24 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = 0;
	                            		caseNums[1] = caseCounterQR;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                              {
	                                 break wHorizCheck;
	                              }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     } 
	                     if(caseCounterQR == 2)
	                     {
	                       continueLine = true;
	                        if(p - 8 >= 0 && pieces[p-8].charAt(0) != 'W' || (p - 8 >= 0 && pieces[p-8] != " " && pieces[p-8].charAt(0) == 'W' && pieces[p-8].charAt(1) == 'K'))
	                        {
	                           nHorizCheck:
	                           for(int nHoriz = p-8; nHoriz >= 0; nHoriz -= 8)
	                           {  
	                              if(pieces[nHoriz] != " " || nHoriz == 0 || nHoriz == 1 || nHoriz == 2 || nHoriz == 3 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = caseCounterQR;
	                            		caseNums[1] = 3;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                              {
	                                 break nHorizCheck;
	                              }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     } 
	                     if(caseCounterQR == 3)
	                     {
	                       continueLine = true;
	                        if(p + 8 <=63 && pieces[p+8].charAt(0) != 'W' || (p + 8 <= 63 && pieces[p+8] != " " && pieces[p+8].charAt(0) == 'W' && pieces[p+8].charAt(1) == 'K'))
	                        {
	                           sHorizCheck:
	                           for(int sHoriz = p+8; sHoriz <= 63; sHoriz += 8)
	                           {  
	                              if(pieces[sHoriz] != " " || sHoriz == 56 || sHoriz == 57 || sHoriz == 58 || sHoriz == 59 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'B' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = 2;
	                            		caseNums[1] = caseCounterQR;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'W' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                              {
	                                 break sHorizCheck;
	                              }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                        	{
	                        		return;
	                        	}
	                        	else
	                        	{
	                        		caseCounterQR = cases[1];
	                        	}
	                      	  
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
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
	                              if(pieces[eHoriz] != " " || eHoriz == 7 || eHoriz == 15 || eHoriz == 23 || eHoriz == 31 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = caseCounterQR;
	                            		caseNums[1] = 1;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break eHorizCheck;
	                                 }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     } 
	                     
	                     if(caseCounterQR == 1)
	                     {
	                       continueLine = true;
	                        if(p - 1 >= 0 && pieces[p-1].charAt(0) != 'B' || (p - 1 >= 0 && pieces[p-1] != " " && pieces[p-1].charAt(0) == 'B' && pieces[p-1].charAt(1) == 'K'))
	                        {
	                           wHorizCheck:
	                           for(int wHoriz = p-1; wHoriz >= 0;  wHoriz--)
	                           {  
	                              if(pieces[wHoriz] != " " || wHoriz == 0 || wHoriz == 8 || wHoriz == 16 || wHoriz == 24 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = 0;
	                            		caseNums[1] = caseCounterQR;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break wHorizCheck;
	                                 }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     } 
	                     if(caseCounterQR == 2)
	                     {
	                       continueLine = true;
	                        if(p - 8 >= 0 && pieces[p-8].charAt(0) != 'B' || (p - 8 >= 0 && pieces[p-8] != " " && pieces[p-8].charAt(0) == 'B' && pieces[p-8].charAt(1) == 'K'))
	                        {
	                           nHorizCheck:
	                           for(int nHoriz = p-8; nHoriz >= 0; nHoriz -= 8)
	                           {  
	                              if(pieces[nHoriz] != " " || nHoriz == 0 || nHoriz == 1 || nHoriz == 2 || nHoriz == 3 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = caseCounterQR;
	                            		caseNums[1] = 3;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break nHorizCheck;
	                                 }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     } 
	                     if(caseCounterQR == 3)
	                     {
	                       continueLine = true;
	                        if(p + 8 <=63 && pieces[p+8].charAt(0) != 'B' || (p + 8 <= 63 && pieces[p+8] != " " && pieces[p+8].charAt(0) == 'B' && pieces[p+8].charAt(1) == 'K'))
	                        {
	                           sHorizCheck:
	                           for(int sHoriz = p+8; sHoriz <= 63; sHoriz += 8)
	                           {  
	                              if(pieces[sHoriz] != " " || sHoriz == 56 || sHoriz == 57 || sHoriz == 58 || sHoriz == 59 || 
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
	                            	  if(checkSweeping && pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                                 	{
	                                 		kingInCheck = true;
	                                 		directionCheckCases.put(false, caseCounterQR);
	                                 	}
	                            	  
	                            	  if(connectedToFriendlyKing)
	                               	{
	                               		if(pieces[posPosition] != " " && pieces[posPosition].charAt(0) == 'W' && (pieces[posPosition].charAt(1) == 'R' || pieces[posPosition].charAt(1) == 'Q'))
	                               		{
	                               			enemyPinnerFound = true;
	                               		}
	                               		if(pinCases || !pinSweeping)
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                               	
	                               	else if(!connectedToFriendlyKing && pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K' && pinSweeping)
	                               	{
	                               		connectedToFriendlyKing = true;
	                               		caseNums[0] = 2;
	                            		caseNums[1] = caseCounterQR;
	                               		directionPinCases.put(false, caseNums);
	                               	}
	                               	else
	                               	{
	                               		if(!(pieces[posPosition] != " " &&  pieces[posPosition].charAt(0) == 'B' && pieces[posPosition].charAt(1) == 'K'))
	                            		{
	                            			possibleMoves.add(posPosition);
	                                        possibleMoveSquareColors.add(g.getSquareColor(posPosition));
	                            		}
	                               	}
	                                  
	                               }
	                              if(continueLine == false)
	                                 {
	                                    break sHorizCheck;
	                                 }
	                           }
	                        }
	                        if(isPinned && caseCounterQR == cases[1])
	                        {
	                        	return;
	                        }
	                        else if(pinCases)
	                        {
	                        	if(caseCounterQR == cases[1])
	                          	{
	                          		return;
	                          	}
	                          	else
	                          	{
	                          		caseCounterQR = cases[1];
	                          	}
	                        }
	                        else
	                        {
	                        	caseCounterQR++; 
	                        }
	                     }
	                  }

	         }
   }
   
   
   
   
   public void highlightMovesQ(Integer[] cases)
   { 
	      possibleMoves.clear();
	      possibleMoveSquareColors.clear();
	      
	  queenDiagCheck(cases);
      queenHorizCheck(cases);
      



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