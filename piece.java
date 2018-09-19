import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


abstract class piece {
	
	private int i;
	private static int kingPositionC;
	private int kingPositionL;
	private static String [][] chessBoard;
	private char side;
	private int currentRow;
	private int currentCol;
	private Image pieceImage;
	
	public piece(String pieceName, char side, int currentRow, int currentCol)
	{
		this.pieceImage = new ImageIcon(pieceName).getImage();
		this.currentRow = currentRow;
		this.currentCol = currentCol;
		this.side = side;
	}
	public void draw(Graphics g)
	{
		g.drawImage(pieceImage, currentCol * 64, currentRow * 64, null);
	}
	abstract String possibleMove(int i);
	abstract boolean isKingSafe();

}
