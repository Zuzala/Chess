package main;
/*
Author: Andy Malinsky

*/


import java.util.ArrayList;
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
   private Integer[] baseCases = {0, 0};
   
   private int whiteKingPosition;
   private int blackKingPosition;   
   private boolean whiteTurn;
   

   private boolean piecePinned = false;
   
   public Discover(ChessGUI2 gee)
   {
       
      g = gee;
      whiteKingPosition = g.getKingPosition('w');
      blackKingPosition = g.getKingPosition('b');
      whiteTurn = g.getTurn();
      pieces = g.getPieces();
   }

  


   private ArrayList<Boolean> whiteKingInCheck = new ArrayList<Boolean>();
   private ArrayList<Boolean> blackKingInCheck = new ArrayList<Boolean>();
   private Map<Boolean, Integer> directionCheckCases = new HashMap<Boolean, Integer>();
   private ArrayList<Integer> blockCheckSquares = new ArrayList<Integer>();

   
 public ArrayList<Boolean> isKingInCheck()
 {  
	 whiteKingInCheck.clear();
	 blackKingInCheck.clear();
	 
	   if(whiteTurn)  //check if black king in check
	   {
		   knightSweep = new Knight(whiteKingPosition, g, true);
		   whiteKingInCheck.add(knightSweep.kingInCheck());
		   queenSweep = new Queen(whiteKingPosition, g);
		   whiteKingInCheck.add(queenSweep.kingInCheck());
		   
		   return whiteKingInCheck;
	   }
	   
	   else  //check if white king in check
	   {
		   knightSweep = new Knight(blackKingPosition, g, true);
		   whiteKingInCheck.add(knightSweep.kingInCheck());
		   queenSweep = new Queen(blackKingPosition, g);
		   whiteKingInCheck.add(queenSweep.kingInCheck());
	   
		   return blackKingInCheck;
	   }
 }






   private boolean connectedToFriendlyKing;
   private Map<Boolean, Integer[]> directionPinCase = new HashMap<Boolean, Integer[]>();
   
   public Map<Boolean, Integer[]> getDirectionPinnedFrom(int position)
   {
	   queenSweep = new Queen(position, g);
	   
	   //is piece connected to friendly king by diagonal or horizontal
	   connectedToFriendlyKing = queenSweep.isKingConnected();
	   
	   //if connected, is there an enemy piece pinning from opposite way pinning it
	   if(connectedToFriendlyKing)
	   {
		   directionPinCase = queenSweep.getPinFromDirection();
		   for(Map.Entry<Boolean, Integer[]> entry : directionPinCase.entrySet())
		   {
			   boolean connectedOnDiagKey = entry.getKey();
			   Integer[] caseNum = entry.getValue();
			   
			   
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
				   directionPinCase.remove(connectedOnDiagKey);
			   }
		   }
		   
	   }
	   
	   
	   return directionPinCase;
   }
   

   





}