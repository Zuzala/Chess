package main;
//Author: Andy Malinsky

//import libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import Pieces.*;

//main GUI class
public class ChessGUI2 extends JFrame
{

   private String mainEntry; // field for one time main constructor
   
   public ChessGUI2()
   {
   }

	//CONSTRUCTOR
	public ChessGUI2(String mainEntry)
	{
		this.mainEntry = mainEntry;
      
      setTitle("Chess");  //sets the title of window
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //default close operation
	   setLayout(new BorderLayout());
		
      //build all the panels
		buildBoardPanel();
		buildNotationPanel();
      buildActionPanel();
		
      //sets the turn to white
      updateTurn();
      
		add(BoardPanel, BorderLayout.CENTER);
		add(NotationPanel, BorderLayout.EAST);
		add(ActionPanel, BorderLayout.SOUTH);
	   
		
		//size everything to fit and make it visible
		setSize(1100,1000);
		setResizable(false);
		setVisible(true);
	}
	
   //BoardPanel fields
	private JPanel BoardPanel;
	private JButton[] squares = new JButton[64];
	public Color brown = new Color(156, 93, 82);
   public Color[] squareColors = new Color[64];
	public String[] pieces = new String[64];
	
	private Piece piece = new Piece();
	
	public void buildBoardPanel()
	{
	   BoardPanel = new JPanel();
		BoardPanel.setPreferredSize(new Dimension(300,300));
		BoardPanel.setLayout(new GridLayout(8, 8));
      
      //creates jbuttons on chess board
		for (int x = 0; x < squares.length; x++)
		{	
		   squares[x] = new JButton();
         
         if(x == 0 || x == 7)  //Black Rook
			{
				squares[x].setIcon(piece.getBlackPiece(3));
            pieces[x] = piece.getPieceGif(3) + "F";
         }
			else if(x == 1 || x == 6)  //Black Knight
			{
				squares[x].setIcon(piece.getBlackPiece(1));
            pieces[x] = piece.getPieceGif(1);
			}
			else if(x == 2 || x == 5)  //Black Bishop
			{
				squares[x].setIcon(piece.getBlackPiece(2));
            pieces[x] = piece.getPieceGif(2);
			}
			else if(x == 3)  //Black Queen
			{
				squares[x].setIcon(piece.getBlackPiece(4));
            pieces[x] = piece.getPieceGif(4);
			}
			else if(x == 4)  //Black King
			{
				squares[x].setIcon(piece.getBlackPiece(5));
            pieces[x] = piece.getPieceGif(5) + "F";
			}
			else if(x >= 8 && x <= 15)  //Black Pawn
			{
				squares[x].setIcon(piece.getBlackPiece(0));
            pieces[x] = piece.getPieceGif(0) + "F";
			}
			else if(x >= 48 && x <= 55)  //White Pawn
			{
				squares[x].setIcon(piece.getWhitePiece(0));
            pieces[x] = piece.getPieceGif(6) + "F";
			}
			else if(x == 56 || x == 63)  //White Rook
			{
				squares[x].setIcon(piece.getWhitePiece(3));
            pieces[x] = piece.getPieceGif(9) + "F";
			}
			else if(x == 57 || x == 62)  //White Knight
			{
				squares[x].setIcon(piece.getWhitePiece(1));
            pieces[x] = piece.getPieceGif(7);
			}
			else if(x == 58 || x == 61)  //White Bishop
			{
				squares[x].setIcon(piece.getWhitePiece(2));
            pieces[x] = piece.getPieceGif(8);
			}
			else if(x == 59)  //White Queen
			{
				squares[x].setIcon(piece.getWhitePiece(4));
            pieces[x] = piece.getPieceGif(10);
			}
			else if(x == 60)  //White King
			{
				squares[x].setIcon(piece.getWhitePiece(5));
            pieces[x] = piece.getPieceGif(11) + "F";
			}
         
         //sets the standard colors of squares on board
         if (x == 1 || x == 3 || x == 5 || x == 7 || x == 8 || x == 10 || 
				 x == 12 || x == 14 || x == 17 || x == 19 || x == 21 || x == 23 || 
				 x == 24 || x == 26 || x == 28 || x == 30 || x == 33 || x == 35 || 
		   	 x == 37 || x == 39 || x == 40 || x == 42 || x == 44 || x == 46 || 
				 x == 49 || x == 51 || x == 53 || x == 55 || x == 56 || x == 58 || x == 60 || x == 62)
			{	
				squares[x].setBackground(brown);
            squareColors[x] = brown;
			}
			else
			{
				squares[x].setBackground(Color.white);
            squareColors[x] = Color.white;
			}
			
         //sets the string to empty if no piece in square
         if(squares[x].getIcon() == null)
         {
            pieces[x] = " ";
         }
         
         squares[x].putClientProperty("board-index", x);
         squares[x].addActionListener(new ButtonListener());  //adds an action listener to each square
         BoardPanel.add(squares[x]);  //add each square jbutton
		}
   }
	
   //action listener fields
   private int position;
   private String positionString;
   private boolean pieceHighLighted;
   private String movePieceString;
   private Color squareColor;
   private int highlightedPiecePosition;
   private boolean legalMove;
   public int passantPawn;
   public int passantTurn;
   public int passantTakePos;
      
   public ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
   public ArrayList<Color> possibleMoveSquareColors = new ArrayList<Color>();
   
   private Discover discover;
   private boolean whiteKingInCheck;
   private boolean blackKingInCheck;
   private int whiteCheckedTurn;
   private int blackCheckedTurn;
   private Color whiteKingSquareColor;
   private Color blackKingSquareColor;
   private int whiteKingPosition;
   private int blackKingPosition;
   
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         //for(int x = 0; x < squares.length; x++)
         //{         
            //if(e.getSource().equals(squares[x]))
            //{
               JButton btn = (JButton) e.getSource();
               position = (Integer) btn.getClientProperty("board-index");  //sets the position to square on board
            
               //if player picks an opposite color piece
               if(whiteTurn && pieces[position].charAt(0) == 'B' && pieceHighLighted == false)
               {
                  JOptionPane.showMessageDialog(null, "Illegal Move");
               
               }
               if(whiteTurn == false && pieces[position].charAt(0) == 'W' && pieceHighLighted == false)
               {
                  JOptionPane.showMessageDialog(null, "Illegal Move");
               
               }
               

               //if white's turn
               whiteTurnChecks:
               if(whiteTurn)
               {
                  blackKingInCheck = false;
                  
                  if(whiteKingInCheck)
                  {
                     
                     break whiteTurnChecks;   
                  }
                 
                  if((pieceHighLighted == true) && (position == highlightedPiecePosition))
                  {
                     squares[highlightedPiecePosition].setBackground(squareColor);
                     
                     for(int hi = 0; hi < possibleMoveSquareColors.size(); hi++)
                     {
                        squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                     }
                     
                     pieceHighLighted = false;
                     
                     break whiteTurnChecks;
                  }
                  
                  if((pieces[position].charAt(0) == 'W') && (pieceHighLighted == true) && (position != highlightedPiecePosition))
                  {
                     squares[highlightedPiecePosition].setBackground(squareColor);
                     for(int hi = 0; hi < possibleMoves.size(); hi++)
                     {
                        squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                     }
                     
                     movePieceString = pieces[position];
                      
                     squareColor = squares[position].getBackground();
                     highlightedPiecePosition = position;
                     
                     squares[position].setBackground(Color.yellow);
                     
                     highlightMoves(highlightedPiecePosition, movePieceString);                     
                     
                     break whiteTurnChecks;
                  }

                  if((pieces[position].charAt(0) == 'W') && (pieceHighLighted == false))
                  {
                     movePieceString = pieces[position];
                     
                     squareColor = squares[position].getBackground();
                     highlightedPiecePosition = position;

                     squares[position].setBackground(Color.yellow);
                     pieceHighLighted = true;
                     
                     highlightMoves(highlightedPiecePosition, movePieceString);
                     
                     break whiteTurnChecks;
                  }

                  if((pieces[position] == " ") && (pieceHighLighted == true))
                  {
                     legalMove = checkMove(position, false);
                                          
                     if(legalMove == true)
                     {
                        if(movePieceString.charAt(1) == 'P')
                        {
                           //checks if the pawn was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder pawnUpdate = new StringBuilder(movePieceString);
                                 pawnUpdate.deleteCharAt(c);
                                 movePieceString = pawnUpdate.toString();
                              }
                           }
                           
                           if(orientation == 'w')
                           {
                                    if(highlightedPiecePosition - position == 16)
                                    {
                                       passantPawn = position;
                                       passantTurn = turnCounter + 1;
                                    }
                            
                              if(position == passantTakePos)
                              {
                                 showTakenPiece(pieces[position+8]);
                                 pieces[passantPawn] = " ";
                              }
                            
                            }
                                 if(orientation == 'b')
                                 {
                                    if(position - highlightedPiecePosition == 16)
                                    {
                                       passantPawn = position;
                                       passantTurn = turnCounter + 1;
                                    }
                                    if(position == passantTakePos)
                                    {
                                       showTakenPiece(pieces[position-8]);
                                       pieces[passantPawn] = " ";
                                    }
                                 }
                                 
                           
                                 
                        }
                        if(movePieceString.charAt(1) == 'R')
                        {
                           //checks if the rook was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder rookUpdate = new StringBuilder(movePieceString);
                                 rookUpdate.deleteCharAt(c);
                                 movePieceString = rookUpdate.toString();
                              }
                           }
                        }
                        if(movePieceString.charAt(1) == 'K')
                        {
                           //checks if the king was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder kingUpdate = new StringBuilder(movePieceString);
                                 kingUpdate.deleteCharAt(c);
                                 movePieceString = kingUpdate.toString();
                              }
                           }
                        }
                                                
                        pieces[position] = movePieceString;
                        squares[highlightedPiecePosition].setBackground(squareColor);
                        pieces[highlightedPiecePosition] = " ";
                        
//                        discover = new Discover(position, blackKingPosition);
//                        blackKingInCheck = discover.isBlackKingChecked();
                        
                        
                        if(blackKingInCheck)
                        {
                           blackCheckedTurn = turnCounter + 1;
                           blackKingSquareColor = squares[blackKingPosition].getBackground();
                           squares[blackKingPosition].setBackground(Color.red);
                           
                           
                        }
                        
                        
                        
                        switch(orientation)
                        {
                           case 'w':
                              if(position == 62 && highlightedPiecePosition == 60)
                              {
                                 pieces[61] = "WR.gif";
                                 pieces[63] = " ";
                              }
                              if(position == 58 && highlightedPiecePosition == 60)
                              {
                                 pieces[59] = "WR.gif";
                                 pieces[56] = " ";
                              }
                              break;
                           case 'b':
                              if(position == 1 && highlightedPiecePosition == 3)
                              {
                                 pieces[2] = "WR.gif";
                                 pieces[0] = " ";
                              }
                              if(position == 5 && highlightedPiecePosition == 3)
                              {
                                 pieces[4] = "WR.gif";
                                 pieces[7] = " ";
                              }
                              break;
                        }
                       
                       //sets the squares back to original color
                        for(int hi = 0; hi < possibleMoves.size(); hi++)
                        {
                           squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                        }
                        
                        updateTurn();
                     }
                     
                     if(legalMove == false)
                     {
                        JOptionPane.showMessageDialog(null, "Illegal Move");
                     }
                  }
                  
                  //if trying to take piece
                  if((pieces[position].charAt(0) == 'B') && (pieceHighLighted == true))
                  {
                     legalMove = checkMove(position, true);
                  
                     if(legalMove == true)
                     {
                        if(movePieceString.charAt(1) == 'P')
                        {
                           //checks if the pawn was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder pawnUpdate = new StringBuilder(movePieceString);
                                 pawnUpdate.deleteCharAt(c);
                                 movePieceString = pawnUpdate.toString();
                              }
                           }
                        }
                        if(movePieceString.charAt(1) == 'R')
                        {
                           //checks if the rook was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder rookUpdate = new StringBuilder(movePieceString);
                                 rookUpdate.deleteCharAt(c);
                                 movePieceString = rookUpdate.toString();
                              }
                           }
                        }
                        if(movePieceString.charAt(1) == 'K')
                        {
                           //checks if the king was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder kingUpdate = new StringBuilder(movePieceString);
                                 kingUpdate.deleteCharAt(c);
                                 movePieceString = kingUpdate.toString();
                              }
                           }
                        }

                        pieces[position] = movePieceString;
                        squares[highlightedPiecePosition].setBackground(squareColor);
                        pieces[highlightedPiecePosition] = " ";
                        
                        for(int hi = 0; hi < possibleMoves.size(); hi++)
                        {
                           squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                        }
                      
                        updateTurn();
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(null, "Illegal Move");
                     }
                  }
               }
               
               //if black's turn cases
               blackTurnChecks:
               if(whiteTurn == false)
               {
                  if((pieceHighLighted == true) && (position == highlightedPiecePosition))
                  {
                     squares[highlightedPiecePosition].setBackground(squareColor);
                     
                     for(int hi = 0; hi < possibleMoveSquareColors.size(); hi++)
                     {
                        squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                     }
                     
                     pieceHighLighted = false;
                     
                     break blackTurnChecks;
                  }
                  
                  if((pieces[position].charAt(0) == 'B') && (pieceHighLighted == true) && (position != highlightedPiecePosition))
                  {
                     squares[highlightedPiecePosition].setBackground(squareColor);
                     for(int hi = 0; hi < possibleMoves.size(); hi++)
                     {
                        squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                     }
                     
                     movePieceString = pieces[position];
                      
                     squareColor = squares[position].getBackground();
                     highlightedPiecePosition = position;
                     
                     squares[position].setBackground(Color.yellow);
                     
                     highlightMoves(highlightedPiecePosition, movePieceString);                     
                     
                     break blackTurnChecks;
                  }

                  if((pieces[position].charAt(0) == 'B') && (pieceHighLighted == false))
                  {
                     movePieceString = pieces[position];
                     
                     squareColor = squares[position].getBackground();
                     highlightedPiecePosition = position;

                     squares[position].setBackground(Color.yellow);
                     pieceHighLighted = true;
                     
                     highlightMoves(highlightedPiecePosition, movePieceString);
                     
                     break blackTurnChecks;
                  }

                  if((pieces[position] == " ") && (pieceHighLighted == true))
                  {
                     legalMove = checkMove(position, false);
                                          
                     if(legalMove == true)
                     {
                        if(movePieceString.charAt(1) == 'P')
                        {
                           //checks if the pawn was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder pawnUpdate = new StringBuilder(movePieceString);
                                 pawnUpdate.deleteCharAt(c);
                                 movePieceString = pawnUpdate.toString();
                              }
                           }
                           
                           if(orientation == 'w')
                                 {
                                   if(position - highlightedPiecePosition == 16)
                                    {
                                       passantPawn = position;
                                       passantTurn = turnCounter + 1;
                                    }
                                    if(position == passantTakePos)
                                    {
                                       showTakenPiece(pieces[position-8]);
                                       pieces[passantPawn] = " ";
                                    }
                                 }
                                 if(orientation == 'b')
                                 {
                                    if(highlightedPiecePosition - position == 16)
                                    {
                                       passantPawn = position;
                                       passantTurn = turnCounter + 1;
                                    }
                                    if(position == passantTakePos)
                                    {
                                       showTakenPiece(pieces[position+8]);
                                       pieces[passantPawn] = " ";
                                    }
                                 }

                        }
                        if(movePieceString.charAt(1) == 'R')
                        {
                           //checks if the rook was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder rookUpdate = new StringBuilder(movePieceString);
                                 rookUpdate.deleteCharAt(c);
                                 movePieceString = rookUpdate.toString();
                              }
                           }
                        }
                        if(movePieceString.charAt(1) == 'K')
                        {
                           //checks if the king was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder kingUpdate = new StringBuilder(movePieceString);
                                 kingUpdate.deleteCharAt(c);
                                 movePieceString = kingUpdate.toString();
                              }
                           }
                        }
                        
                        pieces[position] = movePieceString;
                        squares[highlightedPiecePosition].setBackground(squareColor);
                        pieces[highlightedPiecePosition] = " ";
                        
                        switch(orientation)
                        {
                           case 'w':
                              if(position == 6 && highlightedPiecePosition == 4)
                              {
                                 pieces[5] = "BR.gif";
                                 pieces[7] = " ";
                              }
                              if(position == 2 && highlightedPiecePosition == 4)
                              {
                                 pieces[3] = "BR.gif";
                                 pieces[0] = " ";
                              }
                              break;
                           case 'b':
                              if(position == 57 && highlightedPiecePosition == 59)
                              {
                                 pieces[58] = "BR.gif";
                                 pieces[56] = " ";
                              }
                              if(position == 61 && highlightedPiecePosition == 59)
                              {
                                 pieces[60] = "BR.gif";
                                 pieces[63] = " ";
                              }
                              break;
                        }
                        
                        for(int hi = 0; hi < possibleMoves.size(); hi++)
                        {
                           squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                        }
                        
                        updateTurn();
                     }
                     
                     if(legalMove == false)
                     {
                        JOptionPane.showMessageDialog(null, "Illegal Move");
                     }
                  }
               
                  if((pieces[position].charAt(0) == 'W') && (pieceHighLighted == true))
                  {
                     legalMove = checkMove(position, true);
                  
                     if(legalMove == true)
                     {
                        if(movePieceString.charAt(1) == 'P')
                        {
                           //checks if the pawn was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder pawnUpdate = new StringBuilder(movePieceString);
                                 pawnUpdate.deleteCharAt(c);
                                 movePieceString = pawnUpdate.toString();
                              }
                           }
                        }
                        if(movePieceString.charAt(1) == 'R')
                        {
                           //checks if the rook was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder rookUpdate = new StringBuilder(movePieceString);
                                 rookUpdate.deleteCharAt(c);
                                 movePieceString = rookUpdate.toString();
                              }
                           }
                        }
                        if(movePieceString.charAt(1) == 'K')
                        {
                           //checks if the king was moved
                           for(int c = 0; c < movePieceString.length(); c++)
                           {
                              char h = movePieceString.charAt(c);
                           
                              if(h == 'F')
                              {
                                 StringBuilder kingUpdate = new StringBuilder(movePieceString);
                                 kingUpdate.deleteCharAt(c);
                                 movePieceString = kingUpdate.toString();
                              }
                           }
                        }
                        
                        pieces[position] = movePieceString;
                        squares[highlightedPiecePosition].setBackground(squareColor);
                        pieces[highlightedPiecePosition] = " ";
                        
                        for(int hi = 0; hi < possibleMoves.size(); hi++)
                        {
                           squares[possibleMoves.get(hi)].setBackground(possibleMoveSquareColors.get(hi));
                        }
                      
                        updateTurn();
                     }
                     else
                     {
                        JOptionPane.showMessageDialog(null, "Illegal Move");
                     }
                  }
               }  
            //}              
         //}
      
         if(pieceHighLighted)
         {
            flipBtn.setEnabled(false);
         }
         else
         {
            flipBtn.setEnabled(true);
         }
      } 
   }
   
   //method for highlighting possible moves on board
   public int[] squaresAFileW = {0, 8, 16, 24, 32, 40, 48, 56};
   public int[] squaresHFileW = {7, 15, 23, 31, 39, 47, 55, 63};
   public int[] squaresAFileB = {7, 15, 23, 31, 39, 47, 55, 63};
   public int[] squaresHFileB = {0, 8, 16, 24, 32, 40, 48, 56};
   private Pawn pawn;
   private Knight knight;
   private Bishop bishop;
   private Rook rook;
   private Queen queen;
   private King king;
   
   public void highlightMoves(int p, String movePString)
   {
      //clears arraylists of possible moves and their square colors
      possibleMoves.clear();
      possibleMoveSquareColors.clear();    
       
         if(whiteTurn)
         {
            switch(movePString.charAt(1))
            {
               case 'P':  //cases for pawn moves
                     pawn = new Pawn(p, movePString, this);
                  break;
               
               case 'N':  //cases for knight moves
                  knight = new Knight(p, movePString, this);            
                 break;
               
               case 'B':  //cases for bishop moves
                     bishop = new Bishop(p, movePString, this);
                  break;  
               
               case 'R':  //cases for rook moves
                     rook = new Rook(p, movePString, this); 
                  break;
               
               case 'K':  //cases for king moves              
                     king = new King(p, movePString, this);
                  break;
                  
                  case 'Q':  //cases for queen moves
                     queen = new Queen(p, movePString, this); 
                  break;
               }   
            
               for(int m = 0; m < possibleMoves.size(); m++)
                   {
                        int pos = possibleMoves.get(m);
                        
                   
                           if(pieces[pos].charAt(0) == 'B')
                           {
                              squares[pos].setBackground(Color.red);
                           }
                           if(pieces[pos] == " ")
                           {
                              if(pos == passantTakePos)
                              {
                                 squares[pos].setBackground(Color.red);
                              }
                              else
                              squares[pos].setBackground(Color.yellow);
                              
                           }                      
                     }
            }
            
            
            if(whiteTurn == false)  //if black's turn and same as white's piece cases
            {
               switch(movePString.charAt(1))
               {
                  case 'P':
                     pawn = new Pawn(p, movePString, this);
                     break;                  
                  
                  case 'N': 
                  knight = new Knight(p, movePString, this);
                 break;
               case 'B':
                     bishop = new Bishop(p, movePString, this);
                  break; 
               case 'R':
                     rook = new Rook(p, movePString, this); 
                  break;
               case 'K':  //cases for king moves              
                     king = new King(p, movePString, this); 
                  break;
                  
                  case 'Q':
                     queen = new Queen(p, movePString, this);
                  break;
                }   
            }
            
            for(int m = 0; m < possibleMoves.size(); m++)
                     {
                        int pos = possibleMoves.get(m);

                        if(pieces[pos].charAt(0) == 'W')
                        {
                           squares[pos].setBackground(Color.red);
                        }
                        if(pieces[pos] == " ")
                        {
                            if(pos == passantTakePos)
                              {
                                 squares[pos].setBackground(Color.red);
                              }
                              else
                              squares[pos].setBackground(Color.yellow);
                        } 
                     } 
      
   }

   //checkMove fields
   private boolean legal;
   private boolean legalCheck;
   private int positionDesired;
   private boolean tryingToTakePiece;
   private ArrayList<Boolean> legalMoves = new ArrayList<Boolean>();
   private boolean ownKingDiscovered;
   private boolean enemyKingDiscovered;

   //checks if attempted move is legal and returns boolean
   public boolean checkMove(int positionDes, boolean takingPiece)
   {
      positionDesired = positionDes;
      tryingToTakePiece = takingPiece;
      legalMoves.clear();
      
      for(int c = 0; c < possibleMoves.size(); c++)
      {
         int posPos = possibleMoves.get(c);
            
         if(posPos == positionDesired)
         {
            legalCheck = true;
            
            if(tryingToTakePiece)
            {
               showTakenPiece(pieces[positionDesired]);
            }
            
         }
         
         else
         {
            legalCheck = false;
            
         }   
         
         legalMoves.add(legalCheck);
      }
         
         if(legalMoves.contains(true))
         {
//            discover = new Discover(highlightedPiecePosition, whiteKingLocation, blackKingLocation, this);
//            ownKingDiscovered = discover.isOwnKingDiscover();
            
            if(ownKingDiscovered)
            {
               legal = false;
            }
            
            else if(!ownKingDiscovered)
            {
            
               legal = true;
            }
         }
         else if(legalMoves.contains(true) == false)
         {
            legal = false;
         }
      
      
      return legal;
   }












   //notation panel fields
	private JPanel NotationPanel;
	private JTextArea moves;
	private JScrollPane scrollPane;
	
	public void buildNotationPanel()
	{
		NotationPanel = new JPanel();
		NotationPanel.setPreferredSize(new Dimension(250,300));
		NotationPanel.setLayout(new BorderLayout());
		
		moves = new JTextArea(100,2);
      moves.setEditable(false);
		moves.setFont(new Font("Verdana", Font.PLAIN, 20));
      moves.append("White          Black");
		
		scrollPane = new JScrollPane(moves);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		NotationPanel.add(scrollPane);
	}
	
   
   //panel foe taken pieces, points, and buttons
	private JPanel ActionPanel;
   private JLabel blackPlayer;
   private JLabel whitePlayer;
   private JLabel[] capPieces = new JLabel[30];
   
   private boolean[] BPTaken = {false, false, false, false,
                                false, false, false, false};
   private boolean[] BNTaken = {false, false};
   private boolean[] BBTaken = {false, false};
   private boolean[] BRTaken = {false, false};
   private boolean[] WPTaken = {false, false, false, false,
                                false, false, false, false};
   private boolean[] WNTaken = {false, false};
   private boolean[] WBTaken = {false, false};
   private boolean[] WRTaken = {false, false};
   
   private JLabel blackPoints;
   private JLabel whitePoints;
   private ImageIcon flip;
   private JButton flipBtn;
   private JButton colorBtn;
   
	public void buildActionPanel()
	{
      ActionPanel = new JPanel();
		ActionPanel.setLayout(new GridLayout(2,18));
		ActionPanel.setPreferredSize(new Dimension(1100,250));

      blackPlayer = new JLabel("Black", SwingConstants.CENTER);
      blackPlayer.setFont(new Font("Serif", Font.BOLD, 16));
      blackPlayer.setBorder(BorderFactory.createMatteBorder(1,1,0,1, Color.black));
      
      whitePlayer = new JLabel("White", SwingConstants.CENTER);
      whitePlayer.setFont(new Font("Serif", Font.BOLD, 16));
      whitePlayer.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
      
      
      blackPoints = new JLabel("+5", SwingConstants.CENTER);
      blackPoints.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
      whitePoints = new JLabel("nil", SwingConstants.CENTER);
      whitePoints.setBorder(BorderFactory.createMatteBorder(0,1,1,1, Color.black));
      
      BufferedImage img = null;
      try
      {
          img = ImageIO.read(getClass().getResource("/Images/flipImg.png"));
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
      
      Image flipImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
      flip = new ImageIcon(flipImg);
      flipBtn = new JButton();
      flipBtn.setIcon(flip);
      flipBtn.addActionListener(new FlipListener());
      colorBtn = new JButton();
      
      for(int x = 0; x < capPieces.length; x++)
      {
         capPieces[x] = new JLabel();
         capPieces[x].setVisible(false);
         
         if(x >= 0 && x <= 7)
         {
            capPieces[x].setIcon(piece.getBlackPiece(0));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.black));
         }   
         if(x >= 8 && x <= 9)
         {
            capPieces[x].setIcon(piece.getBlackPiece(1));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.black));
         }   
         if(x >= 10 && x <= 11)
         {
            capPieces[x].setIcon(piece.getBlackPiece(2));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.black));
         }   
         if(x >= 12 && x <= 13)
         {
            capPieces[x].setIcon(piece.getBlackPiece(3));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.black));
         }   
         if(x == 14)
         {
            capPieces[x].setIcon(piece.getBlackPiece(4));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.black));
         }    
         if(x >= 15 && x <= 22)
         {
            capPieces[x].setIcon(piece.getWhitePiece(0));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.black)); 
         }   
         if(x >= 23 && x <= 24)
         {
            capPieces[x].setIcon(piece.getWhitePiece(1));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.black));
         }   
         if(x >= 25 && x <= 26)
         {
            capPieces[x].setIcon(piece.getWhitePiece(2));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.black));
         }   
         if(x >= 27 && x <= 28)
         {
            capPieces[x].setIcon(piece.getWhitePiece(3));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.black));
         }   
         if(x == 29)
         {
            capPieces[x].setIcon(piece.getWhitePiece(4));
            capPieces[x].setBorder(BorderFactory.createMatteBorder(1,0,1,0, Color.black));
         }   
      }
      
      ActionPanel.add(blackPlayer);
      for (int x = 0; x < 15; x++)
      {
         ActionPanel.add(capPieces[x]);
      }
      ActionPanel.add(blackPoints);
      ActionPanel.add(flipBtn);
      ActionPanel.add(whitePlayer);
      for (int x = 15; x < 30; x++)
      {
         ActionPanel.add(capPieces[x]);
      }
      ActionPanel.add(whitePoints);
      ActionPanel.add(colorBtn);
	}
   
   //shows taken pieces on panel
   public void showTakenPiece(String pTaken)
   {
      String pieceTaken = pTaken;
      
      char pieceColor = pieceTaken.charAt(0);
      
      if(pieceColor == 'B')
      {
         switch(pieceTaken.charAt(1))
         {
            case 'P':
               for(int x = 0; x < BPTaken.length; x++)
               {
                  if(BPTaken[x] == false)
                  {
                     BPTaken[x] = true;
                     capPieces[x].setVisible(true);
                     break;
                  }
               }
               break;
            
            case 'N':
               for(int x = 0; x < BNTaken.length; x++)
               {
                  if(BNTaken[x] == false)
                  {
                     if(x == 0)
                     {
                        BNTaken[x] = true;
                        capPieces[8].setVisible(true);
                        break;
                     }
                    if(x == 1)
                     {
                        BNTaken[x] = true;
                        capPieces[9].setVisible(true);
                        break;
                     }
                  }
               }
               break;
            
            case 'B':
               for(int x = 0; x < BBTaken.length; x++)
               {
                  if(BBTaken[x] == false)
                  {
                     if(x == 0)
                     {
                        BBTaken[x] = true;
                        capPieces[10].setVisible(true);
                        break;
                     }
                    if(x == 1)
                     {
                        BBTaken[x] = true;
                        capPieces[11].setVisible(true);
                        break;
                     }
                  }
               }
               break;
            
            case 'R':
               for(int x = 0; x < BRTaken.length; x++)
               {
                  if(BRTaken[x] == false)
                  {
                     if(x == 0)
                     {
                        BRTaken[x] = true;
                        capPieces[12].setVisible(true);
                        break;
                     }
                    if(x == 1)
                     {
                        BNTaken[x] = true;
                        capPieces[13].setVisible(true);
                        break;
                     }
                  }
               }
               break;
            
            case 'Q':
               capPieces[14].setVisible(true);
               break;
         }
      }
      
      if(pieceColor == 'W')
      {
         switch(pieceTaken.charAt(1))
         {
            case 'P':
               for(int x = 0; x < WPTaken.length; x++)
               {
                  if(WPTaken[x] == false)
                  {
                     WPTaken[x] = true;
                     capPieces[x + 15].setVisible(true);
                     break;
                  }
               }
               break;
            
            case 'N':
               for(int x = 0; x < WNTaken.length; x++)
               {
                  if(WNTaken[x] == false)
                  {
                     if(x == 0)
                     {
                        WNTaken[x] = true;
                        capPieces[x + 23].setVisible(true);
                        break;
                     }
                    if(x == 1)
                     {
                        WNTaken[x] = true;
                        capPieces[x + 23].setVisible(true);
                        break;
                     }
                  }
               }
               break;
            
            case 'B':
               for(int x = 0; x < WBTaken.length; x++)
               {
                  if(WBTaken[x] == false)
                  {
                     if(x == 0)
                     {
                        WBTaken[x] = true;
                        capPieces[x + 25].setVisible(true);
                        break;
                     }
                    if(x == 1)
                     {
                        WBTaken[x] = true;
                        capPieces[x + 25].setVisible(true);
                        break;
                     }
                  }
               }
               break;
            
            case 'R':
               for(int x = 0; x < WRTaken.length; x++)
               {
                  if(WRTaken[x] == false)
                  {
                     if(x == 0)
                     {
                        WRTaken[x] = true;
                        capPieces[x + 27].setVisible(true);
                        break;
                     }
                    if(x == 1)
                     {
                        WNTaken[x] = true;
                        capPieces[x + 27].setVisible(true);
                        break;
                     }
                  }
               }
               break;
            
            case 'Q':
               capPieces[29].setVisible(true);
               break;
         }
      }     
   }
   
   //flips the board
   private int pieceOpposite;
   private String tempString;
   private int orientationCounter = 2;
   public char orientation = 'w';
   
   private class FlipListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         pieceOpposite = 63;
         
         for(int x = 0; x <= 31; x++)
         {  
            tempString = pieces[x];
            pieces[x] = pieces[pieceOpposite];
            pieces[pieceOpposite] = tempString;
            
            pieceOpposite--;
         }
      
         for(int x = 0; x < squares.length; x++)
         {
           if(pieces[x].charAt(0) == 'B')
            {
               switch(pieces[x].charAt(1))
               {
                  case 'P':
                     squares[x].setIcon(piece.getBlackPiece(0));
                     break;
                  case 'N':
                     squares[x].setIcon(piece.getBlackPiece(1));
                     break;
                  case 'B':
                     squares[x].setIcon(piece.getBlackPiece(2));
                     break;
                  case 'R':
                     squares[x].setIcon(piece.getBlackPiece(3));
                     break;
                  case 'Q':
                     squares[x].setIcon(piece.getBlackPiece(4));
                     break;
                  case 'K':
                     squares[x].setIcon(piece.getBlackPiece(5));
                     break;
               }
            }
            
            if(pieces[x].charAt(0) == 'W')
            {
               switch(pieces[x].charAt(1))
               {
                  case 'P':
                     squares[x].setIcon(piece.getWhitePiece(0));
                     break;
                  case 'N':
                     squares[x].setIcon(piece.getWhitePiece(1));
                     break;
                  case 'B':
                     squares[x].setIcon(piece.getWhitePiece(2));
                     break;
                  case 'R':
                     squares[x].setIcon(piece.getWhitePiece(3));
                     break;
                  case 'Q':
                     squares[x].setIcon(piece.getWhitePiece(4));
                     break;
                  case 'K':
                     squares[x].setIcon(piece.getWhitePiece(5));
                     break;
               }
            }
            
            if(pieces[x] == " ")
            {
               squares[x].setIcon(null);
            }
         }
      
         orientationCounter++;
         
         if((orientationCounter % 2) == 0)
         {
            orientation = 'w';
         }
         else if((orientationCounter % 2) != 0)
         {
            orientation = 'b';
         }  
      }
    }
   
   
   
   public int turnCounter = 0;
	
   public void updateTurn()
	{
		turnCounter++;
      setTurn(turnCounter);
      
      pieceHighLighted = false;
      
      
      for(int x = 0; x < squares.length; x++)
      {
         if(pieces[x] != " ")
         {
            switch(pieces[x].charAt(0))
            {
               case 'W':
                  switch(pieces[x].charAt(1))
                  {
                     case 'P':
                        squares[x].setIcon(piece.getWhitePiece(0));
                        break;
                     case 'N':
                        squares[x].setIcon(piece.getWhitePiece(1));
                        break;
                     case 'B':
                        squares[x].setIcon(piece.getWhitePiece(2));
                        break;
                     case 'R':
                        squares[x].setIcon(piece.getWhitePiece(3));
                        break;
                     case 'Q':
                        squares[x].setIcon(piece.getWhitePiece(4));
                        break;
                     case 'K':
                        squares[x].setIcon(piece.getWhitePiece(5));
                        break;
                  }
                  break;
            
               case 'B':
                  switch(pieces[x].charAt(1))
                  {
                     case 'P':
                        squares[x].setIcon(piece.getBlackPiece(0));
                        break;
                     case 'N':
                        squares[x].setIcon(piece.getBlackPiece(1));
                        break;
                     case 'B':
                        squares[x].setIcon(piece.getBlackPiece(2));
                        break;
                     case 'R':
                        squares[x].setIcon(piece.getBlackPiece(3));
                        break;
                     case 'Q':
                        squares[x].setIcon(piece.getBlackPiece(4));
                        break;
                     case 'K':
                        squares[x].setIcon(piece.getBlackPiece(5));          
                        break;
                  }
                  break;
            }
         }
         
         else
         {
            squares[x].setIcon(null);
         }
      }
	}
   
   //sets the turn
   public boolean whiteTurn;
   public void setTurn(int turnCount)
   {
      if((turnCount % 2) == 0)
      {
         whiteTurn = false;
         colorBtn.setBackground(brown);
      }
      else
      {
         whiteTurn = true;
         colorBtn.setBackground(Color.white);
      }  
   }    
   
   public boolean getTurn()
   {
      return whiteTurn;
   }

   public char getOrientation()
   {
      return orientation;
   }






}
