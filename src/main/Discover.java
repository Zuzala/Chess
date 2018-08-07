package main;
/*
Author: Andy Malinsky

*/


import java.util.HashMap;
import java.util.Map;

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






   private boolean connectedToFriendlyKing;
   private String pinFromDirection;
   private Map<Boolean, Integer> directionCase = new HashMap<Boolean, Integer>();
   private boolean pinnedFromDiag;
   
   public Map<Boolean, Integer> getDirectionPinnedFrom(int position)
   {
	   queenSweep = new Queen(position, g, "pin");
	   
	   //is piece connected to friendly king by diagonal or horizontal
	   connectedToFriendlyKing = queenSweep.isKingConnected();
	   
	   //if connected, is there an enemy piece pinning from opposite way pinning it
	   if(connectedToFriendlyKing)
	   {
		   directionCase = queenSweep.getPinFromDirection();
		   for(Map.Entry<Boolean, Integer> entry : directionCase.entrySet())
		   {
			   boolean connectedOnDiagKey = entry.getKey();
			   int caseNum = entry.getValue();
			   
			   if(connectedOnDiagKey)  //if connected to king on diag
			   {
				   queenSweep.queenDiagCheck(caseNum);
				   piecePinned = queenSweep.wasEnemyPinnerFound();
				   
			   }
			   else  //if connected to king on horiz
			   {
				   queenSweep.queenHorizCheck(caseNum);
				   piecePinned = queenSweep.wasEnemyPinnerFound();
			   }
			   
			   if(!piecePinned)
			   {
				   directionCase.remove(connectedOnDiagKey);
			   }
		   }
		   
	   }
	   
	   
	   return directionCase;
   }
   







}