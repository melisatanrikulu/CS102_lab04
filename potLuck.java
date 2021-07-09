/*
 * Model for potLock game.
 * @author Melisa Tanrikulu
 * @version 08.07.2021
 */

import javax.swing.*; // JButton, JPanel, JFrame
import java.awt.*; // GridLayout, BorderLayout
import java.awt.event.*; // ActionEvent, ActionListener
import java.util.ArrayList;

public class potLuck extends JFrame {
	// Instance Data Members	
	// Constants
	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	
	private static final int ROW_COUNT = 4;
	private static final int COLUMN_COUNT = 4;
	
	private static final int BUTTON_COUNT = ROW_COUNT * COLUMN_COUNT;
	
	private static final int BOMB_COUNT = 2;
	
	// Variables
	private JPanel panel;
	private JPanel buttonPanel;
	private JLabel statusBar;
	private ArrayList<JButton> buttons;
	private ArrayList<Integer> indices;
	private int noOfGuesses;
	
	// Constructors
	
	/*
	 * Constructs a potLuck game.
	 */
	public potLuck() {
		// Initialization	
		noOfGuesses = 0;
		panel = new JPanel();
		buttonPanel = new JPanel();
		statusBar = new JLabel("Number of guesses: " + noOfGuesses);
		indices = new ArrayList<Integer>();
		buttons = new ArrayList<JButton>();
		
		
		// Changes the size of the JFrame
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		// Changes the layouts for components
		panel.setLayout( new BorderLayout() );
		buttonPanel.setLayout( new GridLayout(ROW_COUNT, COLUMN_COUNT));
		
		// Creates components
		constructIndices();
		createButtons();
		
		// Adds the components to the panel
		createPanel();
	}
	
	/*
	 * Constructs the indices of prize and bombs.
	 */
	public void constructIndices() {
		// Variables
		boolean noMatches;
		int prizeIndex;
		
		// Initialization
		noMatches = true;
		
		// Constructs a random index for the price button and adds it to the indices
		prizeIndex = (int) (Math.random() * 16);
		indices.add(prizeIndex);
		
		// For each bomb button, creates a random index and adds it to the indices if that button with the
		// constructed index is not taken
		for ( int constructedBombCount = 0; constructedBombCount < BOMB_COUNT; constructedBombCount++ ) {
			noMatches = true;
			
			// Constructs a random index for the bomb button
			int bombIndex = (int) (Math.random() * 16);
			
			// Checks each index in the indices for any matches
			for ( int index : indices ) {
				// If the index is taken, terminates the loop to create another random index
				if ( index == bombIndex ) {
					noMatches = false;
					constructedBombCount--;
					break;
				}
			}
			
			// If there is no match in indices, adds the index to the ArrayList
			if ( noMatches ) 
				indices.add(bombIndex);
		}
	}
	
	/*
	 * Creates ActionListener object for the buttons.
	 */
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Variables
			JButton buttonClicked;
			boolean clickedBombButton;
			boolean clickedPrizeButton;
			
			// Initialization
			clickedBombButton = false;
			clickedPrizeButton = false;
			
			// Finds the button that is clicked by the user
			buttonClicked =  (JButton) e.getSource();

			// Increments the number of guesses
			noOfGuesses++;
			
			// If the first button in the ArrayList is clicked, then the prize button is clicked
			if (buttonClicked == buttons.get( indices.get(0) )) {
				// Updates the status bar
				statusBar.setText("You got it in " + noOfGuesses + " attempts!");
				clickedPrizeButton = true;
			}
			
			// If the other buttons in the ArrayList is clicked, then a bomb is clicked
			for ( int buttonNo = 1; buttonNo <= BOMB_COUNT; buttonNo++ ) {
				if ( buttonClicked == buttons.get( indices.get( buttonNo ) ) ) {
					// Updates the status bar
					statusBar.setText("Sorry! You are blown up at attempt " + noOfGuesses + "!");
					clickedBombButton = true;
					break;
				}
			}
			
			// If neither prize button nor bomb buttons are not clicked, updates the
			// status bar
			if ( !clickedPrizeButton && !clickedBombButton )
				statusBar.setText("Number of guesses: " + noOfGuesses);
			
		}
	}
	
	/*
	 * Creates buttons and constructs a listener for them.
	 */
	public void createButtons() {
		// Constructs the listener
		ActionListener listener = new ButtonListener();
		
		// Creates buttons and for each button, adds the listener to them and
		// adds each button to the ArrayLis
		for ( int buttonNo = 0; buttonNo < BUTTON_COUNT; buttonNo++ ) {
			// Creates a button
			JButton button = new JButton("" + ((buttonNo) + 1) );
			
			// Adds the listener
			button.addActionListener(listener);
			
			// Adds the button to the ArrayList
			buttons.add(button);
			
			// Adds the button to the JPanel
			buttonPanel.add(button);
		}
	}
	
	/*
	 * Adds the components to the panel and adds panel to the frame.
	 */
	public void createPanel() {
		panel.add(statusBar, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.CENTER);
		
		add(panel);
	}
}
