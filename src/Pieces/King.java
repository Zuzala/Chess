package Pieces;

/*

Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;
import main.ChessGUI2;



public class King
{
   private ChessGUI2 g; 
   private String[] pieces;
   private int p;
   private String movePString;
   private boolean whiteTurn;
   
   private ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
   private ArrayList<Color> possibleMoveSquareColors = new ArrayList<Color>();
   
   public King(int position, String movePString, ChessGUI2 gee) 
   {
      p = position;
      this.movePString = movePString;
      g = gee;
      this.whiteTurn = g.getTurn();
      this.pieces = g.getPieces();
      
      highlightMovesK();
      moveIntoCheckSweep();
   }
   
   
   public void highlightMovesK()
   { 


      int caseCounterK = 0;

         if(whiteTurn)
         {
            while(caseCounterK < 10)
                  {
                     if(caseCounterK == 0)
                     {
                        if(p == 60 && g.getOrientation() == 'w')
                        {
                           for(int c = 0; c < movePString.length(); c++)
                           {
                              char h = movePString.charAt(c);
                           
                              if(h == 'F' && pieces[p+1] == " " && pieces[p+2] == " " && pieces[p+3].equals("WR.gifF"))
                              {
                                 int posPosition = p + 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(h == 'F' && pieces[p-1] == " " && pieces[p-2] == " " && pieces[p-3] == " " && pieces[p-4].equals("WR.gifF"))
                              {
                                 int posPosition = p - 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        if(p == 3 && g.getOrientation() == 'b')
                        {
                           for(int c = 0; c < movePString.length(); c++)
                           {
                              char h = movePString.charAt(c);
                           
                              if(h == 'F' && pieces[p-1] == " " && pieces[p-2] == " " && pieces[p-3].equals("WR.gifF"))
                              {
                                 int posPosition = p - 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(h == 'F' && pieces[p+1] == " " && pieces[p+2] == " " && pieces[p+3] == " " && pieces[p+4].equals("WR.gifF"))
                              {
                                 int posPosition = p + 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        caseCounterK++;
                     }
                     if(caseCounterK == 1)
                     {
                        if((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) ||
                           (p >= 33 && p <= 38) || (p >= 41 && p <= 46) || (p >= 49 && p <= 54))
                        {
                           int[] posPositions = new int[8];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 7;
                           posPositions[3] = p - 1;
                           posPositions[4] = p + 1;
                           posPositions[5] = p + 7;
                           posPositions[6] = p + 8;
                           posPositions[7] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           } 
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 2)
                     {
                        if(p >= 1 && p <= 6)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 1;
                           posPositions[1] = p + 1;
                           posPositions[2] = p + 7;
                           posPositions[3] = p + 8;
                           posPositions[4] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }                        
                        }
                        caseCounterK++;
                     }   
                     if(caseCounterK == 3)
                     {
                        if(p == 8 || p == 16 || p == 24 || p == 32 || p == 40 || p == 48)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 8;
                           posPositions[1] = p - 7;
                           posPositions[2] = p + 1;
                           posPositions[3] = p + 8;
                           posPositions[4] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }               
                     if(caseCounterK == 4)
                     {
                        if(p == 15 || p == 23 || p == 31 || p == 39 || p == 47 || p == 55)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 1;
                           posPositions[3] = p + 7;
                           posPositions[4] = p + 8;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 5)
                     {
                        if(p >= 57 && p <= 62)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 7;
                           posPositions[3] = p - 1;
                           posPositions[4] = p + 1;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }      
                     if(caseCounterK == 6)
                     {
                        if(p == 0)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p + 1;
                           posPositions[1] = p + 8;
                           posPositions[2] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 7)
                     {
                        if(p == 7)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 1;
                           posPositions[1] = p + 7;
                           posPositions[2] = p + 8;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 8)
                     {
                        if(p == 56)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 8;
                           posPositions[1] = p - 7;
                           posPositions[2] = p + 1;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 9)
                     {
                        if(p == 63)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 1;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'B')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                  }

         
         }
      
         if(whiteTurn == false)
         {
            while(caseCounterK < 10)
                  {
                     if(caseCounterK == 0)
                     {
                        if(p == 4 && g.getOrientation() == 'w')
                        {
                           for(int c = 0; c < movePString.length(); c++)
                           {
                              char h = movePString.charAt(c);
                           
                              if(h == 'F' && pieces[p+1] == " " && pieces[p+2] == " " && pieces[p+3].equals("BR.gifF"))
                              {
                                 int posPosition = p + 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(h == 'F' && pieces[p-1] == " " && pieces[p-2] == " " && pieces[p-3] == " " && pieces[p-4].equals("BR.gifF"))
                              {
                                 int posPosition = p - 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        if(p == 59 && g.getOrientation() == 'b')
                        {
                           for(int c = 0; c < movePString.length(); c++)
                           {
                              char h = movePString.charAt(c);
                           
                              if(h == 'F' && pieces[p-1] == " " && pieces[p-2] == " " && pieces[p-3].equals("BR.gifF"))
                              {
                                 int posPosition = p - 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                              if(h == 'F' && pieces[p+1] == " " && pieces[p+2] == " " && pieces[p+3] == " " && pieces[p+4].equals("BR.gifF"))
                              {
                                 int posPosition = p + 2;
                                 possibleMoves.add(posPosition);
                                 possibleMoveSquareColors.add(g.squareColors[posPosition]);
                              }
                           }
                        }
                        caseCounterK++;
                     }
                     if(caseCounterK == 1)
                     {
                        if((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) ||
                           (p >= 33 && p <= 38) || (p >= 41 && p <= 46) || (p >= 49 && p <= 54))
                        {
                           int[] posPositions = new int[8];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 7;
                           posPositions[3] = p - 1;
                           posPositions[4] = p + 1;
                           posPositions[5] = p + 7;
                           posPositions[6] = p + 8;
                           posPositions[7] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           } 
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 2)
                     {
                        if(p >= 1 && p <= 6)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 1;
                           posPositions[1] = p + 1;
                           posPositions[2] = p + 7;
                           posPositions[3] = p + 8;
                           posPositions[4] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }                        
                        }
                        caseCounterK++;
                     }   
                     if(caseCounterK == 3)
                     {
                        if(p == 8 || p == 16 || p == 24 || p == 32 || p == 40 || p == 48)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 8;
                           posPositions[1] = p - 7;
                           posPositions[2] = p + 1;
                           posPositions[3] = p + 8;
                           posPositions[4] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }               
                     if(caseCounterK == 4)
                     {
                        if(p == 15 || p == 23 || p == 31 || p == 39 || p == 47 || p == 55)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 1;
                           posPositions[3] = p + 7;
                           posPositions[4] = p + 8;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 5)
                     {
                        if(p >= 57 && p <= 62)
                        {
                           int[] posPositions = new int[5];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 7;
                           posPositions[3] = p - 1;
                           posPositions[4] = p + 1;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }      
                     if(caseCounterK == 6)
                     {
                        if(p == 0)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p + 1;
                           posPositions[1] = p + 8;
                           posPositions[2] = p + 9;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 7)
                     {
                        if(p == 7)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 1;
                           posPositions[1] = p + 7;
                           posPositions[2] = p + 8;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 8)
                     {
                        if(p == 56)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 8;
                           posPositions[1] = p - 7;
                           posPositions[2] = p + 1;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                     if(caseCounterK == 9)
                     {
                        if(p == 63)
                        {
                           int[] posPositions = new int[3];
                           posPositions[0] = p - 9;
                           posPositions[1] = p - 8;
                           posPositions[2] = p - 1;
                           
                           for(int x = 0; x < posPositions.length; x++)
                           {
                              if(pieces[posPositions[x]] == " " || pieces[posPositions[x]].charAt(0) == 'W')
                              {
                                 possibleMoves.add(posPositions[x]);
                                 possibleMoveSquareColors.add(g.getSquareColor(posPositions[x]));
                              }
                           }
                        }
                         caseCounterK++;
                     }
                  }

         }
      


   }
   
   //check surrounding squares of king for illegal moves into check

   
   private Queen moveIntoCheckSweep;
   private Knight moveIntoKnightCheckSweep;

   public void moveIntoCheckSweep()
   {
	   Iterator<Integer> i = possibleMoves.iterator();
	   Iterator<Color> c = possibleMoveSquareColors.iterator();
	   while (i.hasNext() && c.hasNext()) 
	   {
	      int position = i.next();
	      Color positionSquareColor = c.next();
	      
	      moveIntoCheckSweep = new Queen(position, g);
	      moveIntoKnightCheckSweep = new Knight(position, g, true);
	      
	      if(moveIntoCheckSweep.kingInCheck() || moveIntoKnightCheckSweep.kingInCheck())
	      {
	    	  i.remove();
	    	  c.remove();
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