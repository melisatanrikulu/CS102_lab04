/*
 * Menu for SOS game.
 * @author Melisa Tanrikulu
 * @version 09.07.2021
 */

import cs101.sosgame.SOS;
import javax.swing.*; //JFrame

public class GameMenu {
	public static void main(String[] args) {
		// Variables
		int dimension = 5;
		int width = 600;
		int heigth = 600;
		String player1 = "Melisa";
		String player2 = "Buse";
		
		// Creates the frame
		JFrame frame = new JFrame();
		
		// Creates the game
		SOS game = new SOS(dimension);
		
		// Creates the panel for the game
		JPanel gamePanel = new SOSGUIPanel(game, player1, player2);

		// Adds the panel to the frame
		frame.add(gamePanel);
		
		// Organizes the frame
		frame.setSize(width, heigth);
		frame.setTitle("MY SAUCE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
