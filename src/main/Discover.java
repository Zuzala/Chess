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

  

   private Map<ArrayList<Integer>, Map<String, Integer>> kingInCheckCases = new HashMap<ArrayList<Integer>, Map<String, Integer>>();
   private ArrayList<Integer> checkingPieces = new ArrayList<Integer>();
   //string either "knight diag horiz" and integer for knight is position while caseNum for diag and horiz
   private Map<String, Integer> directionCheckCases = new HashMap<String, Integer>();
   private ArrayList<Integer> blockCheckSquares = new ArrayList<Integer>();
   private ArrayList<String> checkParameters = new ArrayList<String>();

 public Map<ArrayList<Integer>, Map<String, Integer>> getKingInCheckCases()
 {  

	 kingInCheckCases.clear();
	 directionCheckCases.clear();
	 checkParameters.clear();
	 checkingPieces.clear();
	 
	   if(whiteTurn)  //check if white king in check
	   {
		   knightSweep = new Knight(whiteKingPosition, g, true);
		   queenSweep = new Queen(whiteKingPosition, g);
		   
		   if(knightSweep.kingInCheck())
		   {	
			   directionCheckCases.put("knight", knightSweep.getCheckedFromSquare());
			   checkingPieces.add(knightSweep.getCheckedFromSquare());
		   }
		   
		   if(queenSweep.kingInCheck())
		   {
			   directionCheckCases.putAll(queenSweep.getCheckFromDirection());
			   checkingPieces.addAll(queenSweep.getCheckingPiece());
		   }
		   
		   if(!directionCheckCases.isEmpty())
		   {
			   kingInCheckCases.put(checkingPieces, directionCheckCases);
			   setCheckParameters(directionCheckCases);
		   }
		   
		   
		   
	   }
	   
	   else  //check if black king in check
	   {
		   knightSweep = new Knight(blackKingPosition, g, true);
		   queenSweep = new Queen(blackKingPosition, g);
		   
		   if(knightSweep.kingInCheck())
		   {	
			   directionCheckCases.put("knight", knightSweep.getCheckedFromSquare());
		   }
		   
		   if(queenSweep.kingInCheck())
		   {
			   directionCheckCases.putAll(queenSweep.getCheckFromDirection());
		   }
		   
		   if(!directionCheckCases.isEmpty())
		   {
			   kingInCheckCases.put(checkingPieces, directionCheckCases);
			   setCheckParameters(directionCheckCases);
		   }
	   }
	   
	   return kingInCheckCases;
 }


 public void setCheckParameters(Map<String, Integer> cases)
 {
	 for(Map.Entry<String, Integer> entry : cases.entrySet())
	   {
		 checkParameters.add(entry.getKey());
	   }
	 
 }
 
 public ArrayList<String> getCheckParameters()
 {   

	 return checkParameters;
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
   

   
   private Map<Boolean, Integer> directionCheckedFrom = new HashMap<Boolean, Integer>();
   
   public Map<Boolean, Integer> getDirectionCheckedFrom()
   {
	   
		   for(Map.Entry<String, Integer> entry : directionCheckCases.entrySet())
		   {
			   switch(entry.getKey())
			   {
			   case"diag":
				   directionCheckedFrom.put(true, entry.getValue());
				   break;
			   case "horiz":
				   directionCheckedFrom.put(false, entry.getValue());
				   break;
			   }
		   }
	   
		   
	   return directionCheckedFrom;
   }




}