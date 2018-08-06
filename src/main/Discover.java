package main;
/*
Author: Andy Malinsky

*/


import Pieces.Knight;
import Pieces.Queen;


public class Discover
{
   private ChessGUI2 g;
   private String[] pieces;
   
   
   private Queen queenSweep;
   private Knight knightSweep;
   private final boolean checkingForCheck = true;
   
   private int whiteKingPosition;
   private int blackKingPosition;   
   private boolean whiteTurn;
   
   private boolean whiteKingInCheck = false;
   private boolean blackKingInCheck = false;
   private boolean ownKingDiscovered = false;
   private boolean piecePinned = false;
   
   public Discover(ChessGUI2 gee)
   {
       
      g = gee;
      whiteKingPosition = g.getKingPosition('w');
      blackKingPosition = g.getKingPosition('b');
      whiteTurn = g.getTurn();
      pieces = g.getPieces();
   }

  



   //checks if king is checked based on whose turn it is
   public void isKingInCheck()
   {

	   
	   
	   
	   
	   
   }






   private boolean connectedToKing;
   private String[] pinnedFrom = {"nwDiag", "neDiag", "swDiag", "seDiag",
		   						  "lHoriz", "rHoriz", "above", "below"};
   
   

   public boolean isPiecePinned(int position)
   {
	   queenSweep = new Queen(position, g, "pin");
	   
	   //is piece connected to friendly king by diagonal or horizontal
	   connectedToKing = queenSweep.isKingConnected();
	   //if connected, which way
	   if(connectedToKing)
	   {
		   
	   }
	   //is there an enemy piece pinning from opposite way
	   
	   return piecePinned;
   }
   







}