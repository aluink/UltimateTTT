package net.aluink.ttt.board;

import java.util.Stack;

public class TTTBoard {

	short [] winScenarios = {
			0x7,0x38,0x1C0,
			0x124,0x92,0x49,
			0x111,0x24
	};
	
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
	
	public boolean isWin(short g){
		for(int i = 0;i < 8;i++){
			if((g & winScenarios[i]) == winScenarios[i]){
				return true;
			}
		} return false;
	}
	
	public boolean isOver(byte m){
		return     isWin(boards[0][m/9]) 
				|| isWin(boards[1][m/9]) 
				|| (boards[0][m/9] | boards[1][m/9]) == 0x1FF;
	}
	
	public boolean isLegal(byte m){
		if(m >= 0 && m < 81){
			if(moves.empty()) return true;
			byte pgame = (byte)(moves.peek()/9);
			byte ppos = (byte)(moves.peek()%9);
			
			byte cgame = (byte)(m/9);
			byte cpos = (byte)(m%9);
			
			// If legal target is over then as 
			// long as the new game isn't over and proper
			if(isOver(moves.peek())){
				return isOver(m) && m / 9 != ppos;
			}
			
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
		if ((boards[0][p/9] >> (p % 9) & 1) == 1) {
			return Piece.X;
		} else if ((boards[1][p/9] >> (p % 9) & 1) == 1) {
			return Piece.O;
		} else {
			return null;
		}
	}

}
