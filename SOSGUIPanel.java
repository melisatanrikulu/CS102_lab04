/*
 * Creates the view for SOS game.
 * @author Melisa Tanrikulu
 * @version 09.07.2021
 */

import javax.swing.*; // JComboBox, JLabel
import cs101.sosgame.SOS;
import java.awt.*; // Color
import java.awt.event.*; // MouseListener, MouseEvent

public class SOSGUIPanel extends JPanel {
	// Instance Data Members
	// Constants
	private final static int WIDTH = 100;
	private final static int HEIGHT = 80;
	
	private final static int X = 30;
	private final static int Y = 40;
	
	// Variables
	private JPanel canvas;
	private String player1;
	private String player2;
	private JComboBox<String> comboBox;
	private JLabel scoreLabel;
	private JLabel playerLabel1;
	private JLabel playerLabel2;
	private int score1;
	private int score2;	
	private SOS game;
	
	// Constructor
	
	/*
	 * Creates the view for SOS game.
	 * @param game
	 * @param name of the first player
	 * @param name of the second player
	 */
	public SOSGUIPanel(SOS game, String player1, String player2 ) {
		// Captures the game
		this.game = game;
		
		// Creates the canvas
		canvas = new SOSCanvas(this.game);
		
		// Creates a listener and adds it to the canvas
		MouseListener listener = new mouseListener();
		canvas.addMouseListener(listener);
		
		// Captures the name of the players
		this.player1 = player1;
		this.player2 = player2;
		
		// Obtains the scores
		this.score1 = game.getPlayerScore1();
		this.score2 = game.getPlayerScore2();
		
		// Creates components
		createLabel();
		createComboBox();
		
		// Adds the components to the panel
		createPanel();
	}	
	
	/*
	 * Creates label.
	 */
	public void createLabel() {
		// Constructs labels seperately
		playerLabel1 = new JLabel(player1);
		scoreLabel = new JLabel(" " + score1 + " - " + score2 + " ");
		playerLabel2 = new JLabel(player2);
		
		// Changes the label of the first player's name to indicate it's their turn
		playerLabel1.setForeground(Color.GREEN);
	}
	
	/*
	 * Creates combo box.
	 */
	public void createComboBox() {
		// Variables
		comboBox = new JComboBox<String>();
		
		// Adds the choices to the combo box
		comboBox.addItem("S");
		comboBox.addItem("O");
	}
	
	/*
	 * Adds the components to the panel
	 */
	public void createPanel() {
		add(playerLabel1);
		add(scoreLabel);
		add(playerLabel2);
		add(canvas);
		add(comboBox);		
	}
	
	/*
	 * Listener class for mouse clicks on canvas.
	 */
	class mouseListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent event) { 
			// Obtains the coordinates
			int x = event.getX();
			int y = event.getY();
			
			// Computes the row and column
			int row = ( y - Y ) / HEIGHT + 1;
			int column = ( x - X ) / WIDTH + 1;
			
			// Obtains the letter the user chose
			char letter = Character.toLowerCase(((String) comboBox.getSelectedItem()).charAt(0));
			
			// Plays the game
			game.play(letter, row, column);
			
			// Updates the score
			updateScore();
			
			// If game is over, shows a dialog accordingly
			if ( game.isGameOver() )
				createDialog();

			// If game is not over, indicates whose turn it is
			else 
				updatePlayer();
		}
	}
	
	/*
	 * Changes the name of the players to indicate whose turn it is.
	 */
	public void updatePlayer() {
		// If its first player's turn, changes the colors accordingly
		if ( game.getTurn() == 1 ) {
			playerLabel1.setForeground(Color.GREEN);
			playerLabel2.setForeground(Color.BLACK);
		}
	
		// If its second player's turn, changes the colors accordingly
		else if ( game.getTurn() == 2 ) {
			playerLabel1.setForeground(Color.BLACK);
			playerLabel2.setForeground(Color.GREEN);
		}
		
		// Updates the GUI
		repaint();
	}
	
	/*
	 * Updates the scores of the players in GUI.
	 */
	public void updateScore() {
		// Gets the scores and updates the score label
		score1 = game.getPlayerScore1();
		score2 = game.getPlayerScore2();
		scoreLabel.setText(" " + score1 + " - " + score2 + " ");
		
		// Updates the GUI
		repaint();
	}
	
	/*
	 * Creates a corresponding dialog if the game is over.
	 */
	public void createDialog() {
		// If the players scores are equal, shows a corresponding dialog
		if ( score1 == score2 )	
			JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		
		// If the first player wins, shows a corresponding dialog
		else if ( score1 > score2 )
			JOptionPane.showMessageDialog(this, player1 + " is the winner!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		
		// If the second player wins, shows a corresponding dialog
		else
			JOptionPane.showMessageDialog(this, player2 + " is the winner!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}

}
