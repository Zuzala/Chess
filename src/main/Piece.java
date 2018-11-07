package main;
//Author: Andy Malinsky

//piece class where pieces are initially created and used

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Piece {

	private String[] pieces = new String[64];

	public Piece(boolean whiteTurn, String movePString, String[] pieces) {

		for (int x = 0; x < pieces.length; x++) {
			this.pieces[x] = pieces[x];
		}

	}

	public Piece() {
		fillPieces();

	}

	private ArrayList<ImageIcon> BlackPieces = new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> WhitePieces = new ArrayList<ImageIcon>();
	private String[] pieceGifs = { "BP.gif", "BN.gif", "BB.gif", "BR.gif", "BQ.gif", "BK.gif", "WP.gif", "WN.gif",
			"WB.gif", "WR.gif", "WQ.gif", "WK.gif" };

	public void fillPieces() {
		ImageIcon blackPawn = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[0]));
		ImageIcon blackKnight = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[1]));
		ImageIcon blackBishop = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[2]));
		ImageIcon blackRook = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[3]));
		ImageIcon blackQueen = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[4]));
		ImageIcon blackKing = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[5]));

		BlackPieces.add(blackPawn);
		BlackPieces.add(blackKnight);
		BlackPieces.add(blackBishop);
		BlackPieces.add(blackRook);
		BlackPieces.add(blackQueen);
		BlackPieces.add(blackKing);

		ImageIcon whitePawn = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[6]));
		ImageIcon whiteKnight = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[7]));
		ImageIcon whiteBishop = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[8]));
		ImageIcon whiteRook = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[9]));
		ImageIcon whiteQueen = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[10]));
		ImageIcon whiteKing = new ImageIcon(getClass().getResource("/Images/" + pieceGifs[11]));

		WhitePieces.add(whitePawn);
		WhitePieces.add(whiteKnight);
		WhitePieces.add(whiteBishop);
		WhitePieces.add(whiteRook);
		WhitePieces.add(whiteQueen);
		WhitePieces.add(whiteKing);

	}

	public ImageIcon getBlackPiece(int index) {
		return BlackPieces.get(index);
	}

	public ImageIcon getWhitePiece(int index) {
		return WhitePieces.get(index);
	}

	public String getPieceGif(int index) {
		return pieceGifs[index];
	}
}
