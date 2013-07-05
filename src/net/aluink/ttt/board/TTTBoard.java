package net.aluink.ttt.board;

import java.util.Stack;

public class TTTBoard {

	short[] posToOffset = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };

	short[][] boards;
	Stack<Byte> moves;

	public TTTBoard() {
		moves = new Stack<Byte>();
		boards = new short[2][9];
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 9; i++) {
				boards[j][i] = 0;
			}
		}
	}

	public boolean isLegal(byte m){
		if(m >= 0 && m < 81){
			if(moves.empty()) return true;
			return moves.peek() % 9 == m / 9;
		} return false;
	}
	
	public Piece getTurn(){
		if(moves.size()%2 == 0){
			return Piece.X;
		} return Piece.O;
	}
	
	public void makemove(byte m) {
		boards[moves.size()%2][m / 9] |= posToOffset[m%9];
		moves.push(m);
	}

	/**
	 * Get the piece at the given position.  The multiple
	 * of 9 of p defines the subgame, the modulus 9 of p 
	 * defines the position within that subgame.
	 * 
	 * Function assumes p in [0,80] will throw 
	 * IndexOutOfBoundException otherwise.
	 * 
	 * @param p
	 * @return The piece at the position, null if empty.
	 */
	public Piece getPos(int p) {
		if (boards[0][p/9] >> (p % 9) == 1) {
			return Piece.X;
		} else if (boards[1][p/9] >> (p % 9) == 1) {
			return Piece.O;
		} else {
			return null;
		}
	}

}
