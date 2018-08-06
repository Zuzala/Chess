package main;
/*
Author: Andy Malinsky

*/

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.Rook;

public class Discover
{
   private ChessGUI2 g;
   
   private Knight nKing;
   private Queen qKing;
   private final boolean checkingForCheck = true;
   
   private int whiteKingPosition;
   private int blackKingPosition;   
   private boolean whiteTurn;
   
   
   public Discover(ChessGUI2 gee)
   {
       
      g = gee;
      whiteKingPosition = g.getKingPosition('w');
      blackKingPosition = g.getKingPosition('b');
      whiteTurn = g.getTurn();
      
   }

  



   //checks if king is checked based on whose turn it is
   public void isKingInCheck()
   {
	   if(whiteTurn)
	   {
		   nKing = new Knight(whiteKingPosition, g, checkingForCheck);
	   }
	   
	   if(!whiteTurn)
	   {
		   
	   }
	   
	   
	   
   }










   private boolean ownKingDiscovered;

   public boolean isOwnKingDiscovered()
   {
   
   
   
   
      
   
      return ownKingDiscovered;
   }

   







}