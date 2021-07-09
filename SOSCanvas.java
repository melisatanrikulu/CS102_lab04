/*
 * Creates the view for SOS canvas.
 * @author Melisa Tanrikulu
 * @version 09.07.2021
 */

import cs101.sosgame.SOS;
import javax.swing.*; // JLabel
import java.awt.*; // Graphics

public class SOSCanvas extends JPanel {
	// Instance Data Members
	// Constants
	private final static int X = 30;
	private final static int Y = 40;
	private final static int WIDTH = 100;
	private final static int HEIGHT = 80;
	
	// Variables
	private int dimension;
	private SOS game;
	
	// Constructor
	
	/*
	 * Constructs the canvas.
	 * @param game
	 */
	public SOSCanvas( SOS game ) {
		// Initialication
		this.game = game;
		dimension = game.getDimension();
		
		// Sets the size of the canvas
		setPreferredSize( new Dimension( X + WIDTH * dimension + X, Y + HEIGHT * dimension + Y) );
	}
	
	/*
	 * Creates the canvas.
	 * @param graphics object
	 */
	public void paintComponent(Graphics g) {
		
		// Draws horizontal lines
		for ( int dim = 0; dim <= dimension; dim++ ) 
			g.drawLine(X, Y + HEIGHT * dim, X + WIDTH * dimension, Y + HEIGHT * dim);
		
		// Draws vertical lines
		for ( int dim = 0; dim <= dimension; dim++ ) 
			g.drawLine(X + WIDTH * dim, Y, X + WIDTH * dim, Y + HEIGHT * dimension);
		
		// Updates the grid
		// Increases row
		for ( int row = 0; row < dimension; row++ ) {
			// Increases column
			for ( int column = 0; column < dimension; column++)
				g.drawString( "" + game.getCellContents(row, column), Y + (HEIGHT / 2) + (WIDTH * column), X + (WIDTH / 2) + (HEIGHT * row));				
		}
	}
}
