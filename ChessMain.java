
//Extra imports required for GUI code
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


public class ChessMain extends JFrame implements ActionListener
{
	// Program variables for the Menu items and the game board
	private JMenuItem newOption, exitOption, rulesMenuItem, aboutMenuItem;
	private ChessBoard gameBoard;

	/**
	 * Constructs a new ChessMain frame (sets up the Game)
	 */
	public ChessMain()
	{
		// Sets up the frame for the game
		super("Chinese Chess");
		setResizable(false);

		// Load up the icon image which is the privileges
		setIconImage(Toolkit.getDefaultToolkit().getImage("privileges.png"));

		// Sets up the Chinese Chess board that plays most of the game
		// and add it to the centre of this frame
		gameBoard = new ChessBoard();
		add(gameBoard, BorderLayout.CENTER);

		// Centre the frame in the middle (almost) of the screen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - gameBoard.BOARD_SIZE.width) / 2,
				(screen.height - gameBoard.BOARD_SIZE.height) / 2);

		// Adds the menu and menu items to the frame
		// Set up the Game MenuItems
		newOption = new JMenuItem("New Game");
		newOption.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newOption.addActionListener(this);

		exitOption = new JMenuItem("Exit Game");
		exitOption.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		exitOption.addActionListener(this);

		// Set up the Help Menu
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		rulesMenuItem = new JMenuItem("Rules?", 'R');
		rulesMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		rulesMenuItem.addActionListener(this);
		helpMenu.add(rulesMenuItem);
		aboutMenuItem = new JMenuItem("Credits", 'C');
		aboutMenuItem.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		aboutMenuItem.addActionListener(this);
		helpMenu.add(aboutMenuItem);

		// Add each MenuItem to the Game Menu (with a separator)
		JMenu gameMenu = new JMenu("Option");
		gameMenu.setMnemonic('O');
		gameMenu.add(newOption);
		gameMenu.addSeparator();
		gameMenu.add(exitOption);
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add(gameMenu);
		mainMenu.add(helpMenu);
		// Set the menu bar for this frame to mainMenu
		setJMenuBar(mainMenu);
	}// Constructor

	/**
	 * Responds to a Menu Event. This method is needed since our Chinese Chess
	 * frame implements ActionListener
	 * @param event the event that triggered this method
	 */
	public void actionPerformed(ActionEvent event)
	{
		// If the new option is selected, the board is reset and a new game
		// begins.
		if (event.getSource() == newOption)
		{
			gameBoard.newGame();
		}
		// Closes the game screen if the exit option is selected.
		else if (event.getSource() == exitOption)
		{
			this.setVisible(false);
			System.exit(0);
		}
		// Display help screen if the instructions option was selected by user.
		else if (event.getSource() == rulesMenuItem)
		{
			gameBoard.setHelpScreen(1);
			repaint();
		}
		// Displays copyright information if the about option is selected.
		else if (event.getSource() == aboutMenuItem)
		{
			JOptionPane
					.showMessageDialog(
							this,
							"\u00a9 2016 written by Lizhong Bi and Vincent Tang."
									+ "\nPiece images were made by Sheldon Liu.",
							"Credits", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// The main is the starting point of the program and constructs the game.
	public static void main(String[] args)
	{
		// Starts up the ConnectFourMain frame
		ChessMain frame = new ChessMain();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}// main method
}// class
