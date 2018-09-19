import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class UserInterface extends JPanel implements MouseListener,MouseMotionListener{
	static int x=0,y=0;
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.gray);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		g.setColor(new Color(190,50,120));
		g.fillRect(100,100,200,200);
		g.setColor(new Color(100,100,100));
		//g.fillRect(x-20,y-20,40,40);
		//g.drawString("Lizhong", x, y);
		Image chessImage;
		chessImage = new ImageIcon("chess.png").getImage();
		g.drawImage(chessImage,x,y,x+64,y+64,0,0,300,300,this);
	}
	@Override
	public void mouseMoved(MouseEvent e){
		x=e.getX();
		y=e.getY();
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e){}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void mouseClicked(MouseEvent e){
//		x=e.getX();
//		y=e.getY();
//		repaint();
	}
	@Override
	public void mouseDragged(MouseEvent e){}
	@Override
	public void mouseEntered(MouseEvent e){}
	@Override
	public void mouseExited(MouseEvent e){}
	//https://www.youtube.com/watch?v=NMAdwramt1g&list=PLQV5mozTHmaffB0rBsD6m9VN1azgo5wXl&index=3



}
//https://www.youtube.com/watch?v=STjW3eH0Cik
//https://www.youtube.com/watch?v=6ELUvkSkCts
