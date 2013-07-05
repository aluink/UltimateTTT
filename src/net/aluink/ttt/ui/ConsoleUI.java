package net.aluink.ttt.ui;

import java.util.Scanner;

import net.aluink.ttt.board.Piece;
import net.aluink.ttt.board.TTTBoard;

public class ConsoleUI {
	
	char pieceToChar(Piece p){
		if(p == Piece.X) return 'X';
		if(p == Piece.O) return 'O';
		return ' ';
	}
	
	void printBoard(TTTBoard b){
		
		int [] lookups = {
			0,1,2,    9,10,11,  18,19,20,
			3,4,5,    12,13,14, 21,22,23,
			6,7,8,    15,16,17, 24,25,26,
			
			27,28,29, 36,37,38, 45,46,47,
			30,31,32, 39,40,41, 48,49,50,
			33,34,35, 42,43,44, 51,52,53,
			
			54,55,56, 63,64,65, 72,73,74,
			57,58,59, 66,67,68, 75,76,77,
			60,61,62, 69,70,71, 78,79,80
		};
		
		for(int i = 0;i < 9;i++){
			if((i%3) == 0)
				System.out.println("+=======================+");
			else
				System.out.println("+ ----- | ----- | ----- +");
			System.out.print("+ ");
			for(int j = 0;j < 9;j++){
				System.out.print(pieceToChar(b.getPos(lookups[i*9+j])));
				if((j % 3) != 2) System.out.print("|");
				else if(j == 8) System.out.println(" +");
				else System.out.print(" | ");
			}
		}
		System.out.println("+=======================+");

//		System.out.println("+ X|O|X | X|O|X | X|O|X |");
	}
	
	public ConsoleUI(){
		TTTBoard b = new TTTBoard();
		Scanner s = new Scanner(System.in);
		while(true){
			printBoard(b);
			System.out.print("Enter command: ");
			String input = s.nextLine();
			if(input.equals("quit")) break;
			else if(input.equals("print")){
				printBoard(b);
			}
			else if(input.startsWith("go")){
				input = input.substring(3);
				byte pos = (byte)((input.charAt(0)-'1')*9+(input.charAt(1)-'1'));
				//if(b.isLegal(pos))
					b.makemove(pos);
				//else
				//	System.out.println("Illegal move");
			}
		}
	}
	
	public static void main(String[] args) {
		new ConsoleUI();
	}
}
