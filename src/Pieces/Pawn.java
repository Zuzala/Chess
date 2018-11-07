package Pieces;

/*
Author: Andy Malinsky

*/

import java.util.*;
import java.awt.*;
import main.ChessGUI2;

public class Pawn {
	private ChessGUI2 g;
	private String[] pieces;
	private int p;
	private String movePString;
	private boolean whiteTurn;

	private ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
	private ArrayList<Color> possibleMoveSquareColors = new ArrayList<Color>();
	private boolean isPinned;
	private boolean pinCases;
	private boolean pinnedOnDiag;
	private Integer[] caseNums = new Integer[2];
	private Integer[] diagPinCaseNums = { 1, 1 };
	private Integer[] horizPinCaseNums = { 0, 0 };

	public Pawn(int position, String movePString, ChessGUI2 gee) {
		p = position;
		this.movePString = movePString;
		g = gee;
		this.whiteTurn = g.getTurn();
		this.pieces = g.getPieces();
		this.isPinned = g.getIsPinned();
		this.pinnedOnDiag = g.getDirectionPinnedFrom();

		if (isPinned) {
			if (pinnedOnDiag) {
				highlightMovesP(diagPinCaseNums);
			} else {
				highlightMovesP(horizPinCaseNums);
			}

		} else {
			caseNums[0] = 0;
			caseNums[1] = 0;
			highlightMovesP(caseNums);
		}
	}

	public void highlightMovesP(Integer[] cases) {
		int caseCounterP;
		pinCases = false;

		if (cases[0] == 0 && cases[1] == 0) {
			caseCounterP = 0;
		} else {
			pinCases = true;
			caseCounterP = cases[0];
		}

		if (g.getOrientation() == 'w') // if g.getOrientation() is white starting on bottom
		{
			if (whiteTurn) {
				while (caseCounterP < 4) {
					if (caseCounterP == 0) {
						for (int c = 0; c < movePString.length(); c++) {
							char h = movePString.charAt(c);

							if (h == 'F' && pieces[p - 8] == " " && pieces[p - 16] == " ") {
								int posPosition = p - 16;
								possibleMoves.add(posPosition);
								possibleMoveSquareColors.add(g.getSquareColor(posPosition));
							}
						}

						if (isPinned && !pinnedOnDiag && pieces[p - 8] == " ") {
							int posPosition = p - 8;
							possibleMoves.add(posPosition);
							possibleMoveSquareColors.add(g.getSquareColor(posPosition));
						}

						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 1) {
						if ((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || (p >= 33 && p <= 38)
								|| (p >= 41 && p <= 46) || (p >= 49 && p <= 54)) {
							if (!isPinned) {
								if (p >= 25 && p <= 30) {
									if (g.getTurnCount() == g.passantTurn) {
										if ((p - 1) == g.passantPawn) {
											int posPosition = p - 9;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}
										if ((p + 1) == g.passantPawn) {
											int posPosition = p - 7;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}

									}

								}

								if (pieces[p - 8] == " ") {
									int posPosition = p - 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 9].charAt(0) == 'B') {
									int posPosition = p - 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 7].charAt(0) == 'B') {
									int posPosition = p - 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							} else {
								if (pieces[p - 9].charAt(0) == 'B') {
									int posPosition = p - 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 7].charAt(0) == 'B') {
									int posPosition = p - 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}

						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 2) {
						for (int a = 0; a < g.squaresAFileW.length; a++) {
							if (p == g.squaresAFileW[a]) {
								if (g.getTurnCount() == g.passantTurn) {
									if ((p + 1) == g.passantPawn) {
										int posPosition = p - 7;
										g.passantTakePos = posPosition;
										possibleMoves.add(posPosition);
										possibleMoveSquareColors.add(g.getSquareColor(posPosition));
									}
								}

								if (pieces[p - 8] == " ") {
									int posPosition = p - 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 7].charAt(0) == 'B') {
									int posPosition = p - 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 3) {
						for (int h = 0; h < g.squaresHFileW.length; h++) {
							if (g.getTurnCount() == g.passantTurn) {
								if ((p - 1) == g.passantPawn) {
									int posPosition = p - 9;
									g.passantTakePos = posPosition;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}

							if (p == g.squaresHFileW[h]) {
								if (pieces[p - 8] == " ") {
									int posPosition = p - 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 9].charAt(0) == 'B') {
									int posPosition = p - 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
				}
			}

			if (whiteTurn == false) // if black's turn and same as white's piece cases
			{
				while (caseCounterP < 4) {
					if (caseCounterP == 0) {
						for (int c = 0; c < movePString.length(); c++) {
							char h = movePString.charAt(c);

							if (h == 'F' && pieces[p + 8] == " " && pieces[p + 16] == " ") {
								int posPosition = p + 16;
								possibleMoves.add(posPosition);
								possibleMoveSquareColors.add(g.getSquareColor(posPosition));
							}
						}

						if (isPinned && !pinnedOnDiag && pieces[p - 8] == " ") {
							int posPosition = p - 8;
							possibleMoves.add(posPosition);
							possibleMoveSquareColors.add(g.getSquareColor(posPosition));
						}

						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 1) {
						if ((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || (p >= 33 && p <= 38)
								|| (p >= 41 && p <= 46) || (p >= 49 && p <= 54)) {
							if (!isPinned) {
								if (p >= 33 && p <= 38) {
									if (g.getTurnCount() == g.passantTurn) {
										if ((p - 1) == g.passantPawn) {
											int posPosition = p + 7;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}
										if ((p + 1) == g.passantPawn) {
											int posPosition = p + 9;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}

									}
								}

								if (pieces[p + 8] == " ") {
									int posPosition = p + 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 7].charAt(0) == 'W') {
									int posPosition = p + 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 9].charAt(0) == 'W') {
									int posPosition = p + 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							} else {
								if (pieces[p + 7].charAt(0) == 'W') {
									int posPosition = p + 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 9].charAt(0) == 'W') {
									int posPosition = p + 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}

						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 2) {
						for (int a = 0; a < g.squaresAFileW.length; a++) {
							if (p == g.squaresAFileW[a]) {
								if (g.getTurnCount() == g.passantTurn) {
									if ((p + 1) == g.passantPawn) {
										int posPosition = p + 9;
										g.passantTakePos = posPosition;
										possibleMoves.add(posPosition);
										possibleMoveSquareColors.add(g.getSquareColor(posPosition));
									}
								}

								if (pieces[p + 8] == " ") {
									int posPosition = p + 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 9].charAt(0) == 'W') {
									int posPosition = p + 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 3) {
						for (int h = 0; h < g.squaresHFileW.length; h++) {
							if (p == g.squaresHFileW[h]) {
								if (g.getTurnCount() == g.passantTurn) {
									if ((p - 1) == g.passantPawn) {
										int posPosition = p + 7;
										g.passantTakePos = posPosition;
										possibleMoves.add(posPosition);
										possibleMoveSquareColors.add(g.getSquareColor(posPosition));
									}
								}

								if (pieces[p + 8] == " ") {
									int posPosition = p + 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 7].charAt(0) == 'W') {
									int posPosition = p + 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
				}

			}

		}

		if (g.getOrientation() == 'b') // if g.getOrientation() is black starting on bottom
		{
			if (whiteTurn) {
				while (caseCounterP < 4) {
					if (caseCounterP == 0) {
						for (int c = 0; c < movePString.length(); c++) {
							char h = movePString.charAt(c);

							if (h == 'F' && pieces[p + 8] == " " && pieces[p + 16] == " ") {
								int posPosition = p + 16;
								possibleMoves.add(posPosition);
								possibleMoveSquareColors.add(g.getSquareColor(posPosition));
							}
						}

						if (isPinned && !pinnedOnDiag && pieces[p - 8] == " ") {
							int posPosition = p - 8;
							possibleMoves.add(posPosition);
							possibleMoveSquareColors.add(g.getSquareColor(posPosition));
						}

						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 1) {
						if ((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || (p >= 33 && p <= 38)
								|| (p >= 41 && p <= 46) || (p >= 49 && p <= 54)) {
							if (!isPinned) {
								if (p >= 33 && p <= 38) {
									if (g.getTurnCount() == g.passantTurn) {
										if ((p - 1) == g.passantPawn) {
											int posPosition = p + 7;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}
										if ((p + 1) == g.passantPawn) {
											int posPosition = p + 9;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}

									}

								}

								if (pieces[p + 8] == " ") {
									int posPosition = p + 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 7].charAt(0) == 'B') {
									int posPosition = p + 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 9].charAt(0) == 'B') {
									int posPosition = p + 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							} else {
								if (pieces[p + 7].charAt(0) == 'B') {
									int posPosition = p + 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 9].charAt(0) == 'B') {
									int posPosition = p + 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}

						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 2) {
						for (int a = 0; a < g.squaresAFileB.length; a++) {
							if (p == g.squaresAFileB[a]) {
								if (g.getTurnCount() == g.passantTurn) {
									if ((p - 1) == g.passantPawn) {
										int posPosition = p + 7;
										g.passantTakePos = posPosition;
										possibleMoves.add(posPosition);
										possibleMoveSquareColors.add(g.getSquareColor(posPosition));
									}
								}

								if (pieces[p + 8] == " ") {
									int posPosition = p + 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 7].charAt(0) == 'B') {
									int posPosition = p + 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 3) {
						for (int h = 0; h < g.squaresHFileB.length; h++) {
							if (g.getTurnCount() == g.passantTurn) {
								if ((p + 1) == g.passantPawn) {
									int posPosition = p + 9;
									g.passantTakePos = posPosition;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}

							if (p == g.squaresHFileB[h]) {
								if (pieces[p + 8] == " ") {
									int posPosition = p + 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p + 9].charAt(0) == 'B') {
									int posPosition = p + 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
				}
			}

			if (whiteTurn == false) // if black's turn, same as white's piece cases
			{
				while (caseCounterP < 4) {
					if (caseCounterP == 0) {
						for (int c = 0; c < movePString.length(); c++) {
							char h = movePString.charAt(c);

							if (h == 'F' && pieces[p - 8] == " " && pieces[p - 16] == " ") {
								int posPosition = p - 16;
								possibleMoves.add(posPosition);
								possibleMoveSquareColors.add(g.getSquareColor(posPosition));
							}
						}

						if (isPinned && !pinnedOnDiag && pieces[p - 8] == " ") {
							int posPosition = p - 8;
							possibleMoves.add(posPosition);
							possibleMoveSquareColors.add(g.getSquareColor(posPosition));
						}

						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 1) {
						if ((p >= 9 && p <= 14) || (p >= 17 && p <= 22) || (p >= 25 && p <= 30) || (p >= 33 && p <= 38)
								|| (p >= 41 && p <= 46) || (p >= 49 && p <= 54)) {
							if (!isPinned) {
								if (p >= 25 && p <= 30) {
									if (g.getTurnCount() == g.passantTurn) {
										if ((p - 1) == g.passantPawn) {
											int posPosition = p - 9;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}
										if ((p + 1) == g.passantPawn) {
											int posPosition = p - 7;
											g.passantTakePos = posPosition;
											possibleMoves.add(posPosition);
											possibleMoveSquareColors.add(g.getSquareColor(posPosition));
										}

									}
								}

								if (pieces[p - 8] == " ") {
									int posPosition = p - 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 7].charAt(0) == 'W') {
									int posPosition = p - 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 9].charAt(0) == 'W') {
									int posPosition = p - 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							} else {
								if (pieces[p - 7].charAt(0) == 'W') {
									int posPosition = p - 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 9].charAt(0) == 'W') {
									int posPosition = p - 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}

						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 2) {
						for (int a = 0; a < g.squaresAFileB.length; a++) {
							if (p == g.squaresAFileB[a]) {
								if (g.getTurnCount() == g.passantTurn) {
									if ((p - 1) == g.passantPawn) {
										int posPosition = p - 9;
										g.passantTakePos = posPosition;
										possibleMoves.add(posPosition);
										possibleMoveSquareColors.add(g.getSquareColor(posPosition));
									}
								}

								if (pieces[p - 8] == " ") {
									int posPosition = p - 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 9].charAt(0) == 'W') {
									int posPosition = p - 9;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
					if (caseCounterP == 3) {
						for (int h = 0; h < g.squaresHFileB.length; h++) {
							if (p == g.squaresHFileB[h]) {
								if (g.getTurnCount() == g.passantTurn) {
									if ((p + 1) == g.passantPawn) {
										int posPosition = p - 7;
										g.passantTakePos = posPosition;
										possibleMoves.add(posPosition);
										possibleMoveSquareColors.add(g.getSquareColor(posPosition));
									}
								}

								if (pieces[p - 8] == " ") {
									int posPosition = p - 8;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
								if (pieces[p - 7].charAt(0) == 'W') {
									int posPosition = p - 7;
									possibleMoves.add(posPosition);
									possibleMoveSquareColors.add(g.getSquareColor(posPosition));
								}
							}
						}
						if (isPinned && caseCounterP == cases[1]) {
							return;
						} else {
							caseCounterP++;
						}
					}
				}

			}

		}

	}

	public ArrayList<Integer> getPossibleMoves() {
		return this.possibleMoves;
	}

	public ArrayList<Color> getPossibleMoveSquareColors() {
		return this.possibleMoveSquareColors;
	}

}