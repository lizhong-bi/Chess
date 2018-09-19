
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoard extends JPanel
{

	// Program constants (declared at the top, these can be used by any method)
	private final int SQUARE_SIZE = 64;
	private final int NO_OF_ROWS = 10;
	private final int NO_OF_COLUMNS = 9;
	private final char playerS = 'S';
	private final char playerN = 'N';
	public final Dimension BOARD_SIZE = new Dimension(900, 640);
	private final Image boardImage = new ImageIcon("theRealChessBoard.png")
			.getImage();
	// Define Rectangular areas are hot spots for graphical buttons
	final Rectangle EXIT_BUTTON = new Rectangle(275, 420, 60, 25);
	final Rectangle PREV_BUTTON = new Rectangle(30, 420, 45, 25);
	final Rectangle NEXT_BUTTON = new Rectangle(520, 420, 45, 25);

	// Program variables (declared at the top, these can be
	// used or changed by any method)
	private piece[][] board;
	private boolean gameOver;
	private char currentPlayer;
	private piece selectedPiece;
	private int selectedRow, selectedColumn;
	private int win;
	private piece KingN;
	private piece KingS;
	private Image mainRule, AdvisorRule, CannonRule, ChariotRule, ElephantRule,
			HorseRule, KingRule, SoldierRule, imageArrowNext, imageArrowBack,
			imageExitHelp;
	private int noOfMoves;
	private int helpScreen;

	/**
	 * Constructs a new ChessBoard object
	 */
	public ChessBoard()
	{
		// Sets up the board area, loads in piece images and starts a new game
		setPreferredSize(BOARD_SIZE);
		setBackground(new Color(100, 250, 50));

		// Load up the images for the pieces, background, etc
		mainRule = new ImageIcon("MainRule.png").getImage();
		AdvisorRule = new ImageIcon("AdvisorRule.png").getImage();
		CannonRule = new ImageIcon("CannonRule.png").getImage();
		ChariotRule = new ImageIcon("ChariotRule.png").getImage();
		ElephantRule = new ImageIcon("ElephantRule.png").getImage();
		HorseRule = new ImageIcon("HorseRule.png").getImage();
		KingRule = new ImageIcon("KingRule.png").getImage();
		SoldierRule = new ImageIcon("SoldierRule.png").getImage();
		imageArrowNext = new ImageIcon("arrow_nexty.png").getImage();
		imageArrowBack = new ImageIcon("arrow_backy.png").getImage();
		imageExitHelp = new ImageIcon("help_exity.png").getImage();

		// Don't show help to start
		helpScreen = 0;

		// Add mouse listeners to the drawing panel
		this.addMouseListener(new MouseHandler());
		this.addMouseMotionListener(new MouseMotionHandler());

		// Sets up the board array and starts a new game
		board = new Piece[NO_OF_ROWS][NO_OF_COLUMNS];
		newGame();
	}

	/**
	 * Use public method to show help screens. ChessMain could access it when
	 * "Rule?.." is clicked.
	 * @param helpScreenToShow the page of help screen to show.
	 */
	public void setHelpScreen(int helpScreenToShow)
	{
		helpScreen = helpScreenToShow;
	}

	/**
	 * Starts a new game
	 */
	public void newGame()
	{
		// Let Southern King Starts first
		currentPlayer = playerS;
		// Initialize the variables
		noOfMoves = 0;
		gameOver = false;
		win = 0;
		clearBoard();

		// Sets the northern king's pieces on the board
		KingN = new King('N', 0, 4);
		board[0][0] = new Chariot('N', 0, 0);
		board[0][1] = new Horse('N', 0, 1);
		board[0][2] = new Elephant('N', 0, 2);
		board[0][3] = new Advisor('N', 0, 3);
		board[0][4] = KingN;
		board[0][8] = new Chariot('N', 0, 8);
		board[0][7] = new Horse('N', 0, 7);
		board[0][6] = new Elephant('N', 0, 6);
		board[0][5] = new Advisor('N', 0, 5);
		board[2][1] = new Cannon('N', 2, 1);
		board[2][7] = new Cannon('N', 2, 7);
		board[3][2] = new Soldier('N', 3, 2);
		board[3][4] = new Soldier('N', 3, 4);
		board[3][6] = new Soldier('N', 3, 6);
		board[3][8] = new Soldier('N', 3, 8);
		board[3][0] = new Soldier('N', 3, 0);

		// Sets the Southern king's pieces on the board
		KingS = new King('S', 9, 4);
		board[9][0] = new Chariot('S', 9, 0);
		board[9][1] = new Horse('S', 9, 1);
		board[9][2] = new Elephant('S', 9, 2);
		board[9][3] = new Advisor('S', 9, 3);
		board[9][4] = KingS;
		board[9][8] = new Chariot('S', 9, 8);
		board[9][7] = new Horse('S', 9, 7);
		board[9][6] = new Elephant('S', 9, 6);
		board[9][5] = new Advisor('S', 9, 5);
		board[7][1] = new Cannon('S', 7, 1);
		board[7][7] = new Cannon('S', 7, 7);
		board[6][2] = new Soldier('S', 6, 2);
		board[6][4] = new Soldier('S', 6, 4);
		board[6][6] = new Soldier('S', 6, 6);
		board[6][8] = new Soldier('S', 6, 8);
		board[6][0] = new Soldier('S', 6, 0);

		selectedPiece = null;
		selectedColumn = -1;
		selectedRow = -1;
		// show any changes on the screen
		repaint();

	}

	/**
	 * To check if there is a winner
	 * @return the winner
	 */
	public Piece winner()
	{
		// Check if northern king exist
		if (board[0][3] != KingN && board[0][4] != KingN
				&& board[0][5] != KingN && board[1][3] != KingN
				&& board[1][4] != KingN && board[1][5] != KingN
				&& board[2][3] != KingN && board[2][4] != KingN
				&& board[2][5] != KingN)
		{
			return KingS;
		}
		// Check if southern king exist
		if (board[9][3] != KingS && board[9][4] != KingS
				&& board[9][5] != KingS && board[8][3] != KingS
				&& board[8][4] != KingS && board[8][5] != KingS
				&& board[7][3] != KingS && board[7][4] != KingS
				&& board[7][5] != KingS)
		{
			return KingN;
		}
		// No winner
		return null;

	}

	/**
	 * To check if game is over
	 */
	public void over()
	{
		// No winner, the game goes on
		if (winner() == null)
		{
			gameOver = false;
		}
		// game over when there is a winner
		if (winner() == KingS || winner() == KingN)
		{
			gameOver = true;
		}
	}

	/**
	 * Clears the game board
	 */
	private void clearBoard()
	{
		for (int row = 0; row < NO_OF_ROWS; row++)
			for (int column = 0; column < NO_OF_COLUMNS; column++)
				board[row][column] = null;
	}

	/**
	 * To move the a selected piece
	 * @param selectedPiece the piece is selected to move
	 * @param moveToRow the row to move to
	 * @param moveToColumn the column to move to
	 */
	public void makeMove(Piece selectedPiece, int moveToRow, int moveToColumn)
	{
		// Show any changes on the screen
		repaint();
		// Don't make any moves once the game is over
		if (gameOver)
		{
			JOptionPane.showMessageDialog(this,
					"Please Select Game...New to start a new game",
					"Game Over", JOptionPane.WARNING_MESSAGE);
			return;
		}
		noOfMoves++;

		// To find out the player's turn
		if (noOfMoves % 2 == 0)
		{
			currentPlayer = playerN;
		}
		else if (noOfMoves % 2 != 0)
		{
			currentPlayer = playerS;
		}

		// If player clicks, when it is not his turn show the panel.
		if (selectedPiece.side != currentPlayer)
		{
			JOptionPane
					.showMessageDialog(
							this,
							"It is the other King's turn.",
							"Wait!!!", JOptionPane.WARNING_MESSAGE);
			this.selectedPiece = null;
			selectedRow = -1;
			selectedColumn = -1;
			noOfMoves--;
			return;
		}
		
		// Make the move if it is valid
		if (selectedPiece.isValidMove(board, moveToRow, moveToColumn) == true)
		{

			board[selectedRow][selectedColumn].move(moveToRow, moveToColumn);
			board[moveToRow][moveToColumn] = board[selectedRow][selectedColumn];
			board[selectedRow][selectedColumn] = null;

			selectedRow = -1;
			selectedColumn = -1;
			this.selectedPiece = null;
		}
		else if (selectedPiece.isValidMove(board, moveToRow, moveToColumn) == false
				|| selectedPiece.side != currentPlayer)
		{
			JOptionPane.showMessageDialog(this,
					"This is not a valid move!", "Read the Rule",
					JOptionPane.INFORMATION_MESSAGE);
			noOfMoves--;
		}

		// Check if the game is over
		over();

		// show the panel when one of the player wins
		if (gameOver && winner() == KingS && win < 1)
		{
			JOptionPane
					.showMessageDialog(
							this,
							"Southern King Wins!!! (Please Select Game...New to start a new game)",
							"Game Over", JOptionPane.WARNING_MESSAGE);
			win++;
			return;

		}
		if (gameOver && winner() == KingN && win < 1)
		{
			JOptionPane
					.showMessageDialog(
							this,
							"Nouthern King Wins!!! (Please Select Game...New to start a new game)",
							"Game Over", JOptionPane.WARNING_MESSAGE);
			win++;
			return;

		}
		// Show any chances.
		repaint();

	}

	/**
	 * Repaint the board's drawing panel
	 * @param g The Graphics context
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Draw the board, rectangles and name
		g.drawImage(boardImage, 0, 0, this);
		g.drawRect(630, 20, 220, 600);

		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("Liu Bang", 700, 550);
		g.drawString("Xiang Yu", 700, 100);

		// Draw the piece when it is selected or moved
		for (int row = 0; row < NO_OF_ROWS; row++)
			for (int column = 0; column < NO_OF_COLUMNS; column++)
			{

				int xPos = column * SQUARE_SIZE;
				int yPos = row * SQUARE_SIZE;

				if (row == selectedRow && column == selectedColumn)
				{
					g.setColor(Color.GREEN);
					g.fillRect(xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);
				}

				if (board[row][column] != null)
					board[row][column].draw(g);
			}

		// Draw the attack signs
		if (noOfMoves % 2 != 0)
		{
			g.setColor(Color.RED);
			g.drawRect(670, 150, 140, 50);
			g.drawString("Attack!", 704, 182);

		}
		if (noOfMoves % 2 == 0)
		{
			g.setColor(Color.RED);
			g.drawRect(670, 440, 134, 50);
			g.drawString("Attack!", 704, 472);

		}

		// Draw all the help screens
		if (helpScreen == 1)
		{
			g.drawImage(mainRule, 0, 170, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 2)
		{
			g.drawImage(KingRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 3)
		{
			g.drawImage(AdvisorRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 4)
		{
			g.drawImage(ElephantRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 5)
		{
			g.drawImage(HorseRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 6)
		{
			g.drawImage(ChariotRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 7)
		{
			g.drawImage(SoldierRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageArrowNext, 525, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
		else if (helpScreen == 8)
		{
			g.drawImage(CannonRule, 0, 170, this);
			g.drawImage(imageArrowBack, 30, 420, this);
			g.drawImage(imageExitHelp, 275, 420, this);
		}
	} // paint component method

	/**
	 * Responds when mouse is pressed
	 */
	private class MouseHandler extends MouseAdapter
	{
		/**
		 * Responds to a mousePressed event
		 * @param event Information about the mouse pressed event.
		 */
		@Override
		public void mousePressed(MouseEvent event)
		{
			// Convert mouse-pressed position to board row and column
			int pressedOnColumn = event.getX() / SQUARE_SIZE;
			int pressedOnRow = event.getY() / SQUARE_SIZE;
			Point pressed = event.getPoint();

			// Respond if a mouse button was pressed while mouse was located
			// within the buttons on the help screen
			if (helpScreen > 0)
			{
				// De-selected the selected piece when the help screen is shown
				selectedRow = -1;
				selectedColumn = -1;
				selectedPiece = null;
				// Check for the forward arrow on main rule.
				if (helpScreen == 1 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the backward arrow and forward arrow on help screen
				// King rule
				else if (helpScreen == 2 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				else if (helpScreen == 2 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the backward arrow and forward arrow on
				// advisor rule
				else if (helpScreen == 3 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				else if (helpScreen == 3 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the backward arrow and forward arrow on
				// elephant rule
				else if (helpScreen == 4 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				else if (helpScreen == 4 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the backward arrow and forward arrow on
				// horse rule
				else if (helpScreen == 5 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				else if (helpScreen == 5 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the backward arrow and forward arrow on
				// chariot rule
				else if (helpScreen == 6 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				else if (helpScreen == 6 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the backward arrow and forward arrow on
				// soldier rule
				else if (helpScreen == 7 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				else if (helpScreen == 7 && NEXT_BUTTON.contains(pressed))
					helpScreen++;
				// Check for the forward arrow on cannon rule
				else if (helpScreen == 8 && PREV_BUTTON.contains(pressed))
					helpScreen--;
				// Check if exit image was pressed in either help screen
				else if (EXIT_BUTTON.contains(pressed))
					helpScreen = 0; // zero means don't show any help screen

				// Repaint the screen to show any changes
				repaint();
			}

			// Handle the situation when you click on nothing or the situation
			// when you selected a piece
			if (selectedPiece == null
					&& board[pressedOnRow][pressedOnColumn] != null)
			{
				selectedRow = pressedOnRow;
				selectedColumn = pressedOnColumn;
				selectedPiece = board[selectedRow][selectedColumn];
			}
			// Handle the situation when you want to capture a piece
			else if (selectedPiece != null
					&& (board[pressedOnRow][pressedOnColumn] == null || board[pressedOnRow][pressedOnColumn].side != selectedPiece.side))
			{
				makeMove(selectedPiece, pressedOnRow, pressedOnColumn);
				setCursor(Cursor.getDefaultCursor());
			}
			// Handle the situation when you reselecte a piece
			else if (selectedPiece != null
					&& board[pressedOnRow][pressedOnColumn].side == selectedPiece.side)
			{
				selectedRow = pressedOnRow;
				selectedColumn = pressedOnColumn;
				selectedPiece = board[selectedRow][selectedColumn];
			}

		}
	}

	// Inner Class to handle mouse movements over the drawing panel
	/**
	 * Monitors mouse movement over the game panel and responds
	 */
	private class MouseMotionHandler extends MouseMotionAdapter
	{
		/**
		 * Changes the mouse cursor to a hand, if it is a valid move.
		 * @param event information about the mouse released event
		 */
		public void mouseMoved(MouseEvent event)
		{
			Point pos = event.getPoint();

			if (selectedPiece != null)
			{
				// Convert mouse-pressed position to board row and column
				int column = pos.x / SQUARE_SIZE;
				int row = pos.y / SQUARE_SIZE;

				// the move is valid,so set the mouse cursor to a hand
				if (selectedPiece.isValidMove(board, row, column))
					setCursor(Cursor
							.getPredefinedCursor(Cursor.HAND_CURSOR));
				else
					setCursor(Cursor.getDefaultCursor());

			}
			// Repaint the screen to show any changes
			repaint();
		}
	} // MouseMotionHandler

	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent event)
	{

	}

}

