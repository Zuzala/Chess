package main;
/*
Author: Andy Malinsky

*/


import Pieces.Knight;
import Pieces.Queen;


public class Discover
{
   private ChessGUI2 g;
   
   private Knight nwKing;
   private Knight nbKing;
   private Queen qwKing;
   private Queen qbKing;
   private final boolean checkingForCheck = true;
   
   private int whiteKingPosition;
   private int blackKingPosition;   
   private boolean whiteTurn;
   
   private boolean whiteKingInCheck = false;
   private boolean blackKingInCheck = false;
   private boolean ownKingDiscovered = false;
   
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
	   nwKing = new Knight(blackKingPosition, g, checkingForCheck);
	   nbKing = new Knight(whiteKingPosition, g, checkingForCheck);
	   qwKing = new Queen(blackKingPosition, g, checkingForCheck);
	   qbKing = new Queen(whiteKingPosition, g, checkingForCheck);
	   
	   
	   if(whiteTurn && whiteKingInCheck)
	   {
		   ownKingDiscovered = true;
	   }
	   
	   if(!whiteTurn && blackKingInCheck)
	   {
		   ownKingDiscovered = true;
	   }
	   
	   
	   
   }










  

   public boolean isOwnKingDiscovered()
   {
      return ownKingDiscovered;
   }

   







}