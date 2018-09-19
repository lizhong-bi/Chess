
public class Rook extends piece{

	private int i;
	private static int kingPositionC;
	private int kingPositionL;
	private static String [][] chessBoard;
	
	public Rook(int i,String [][] chessBoard,int kingPositionC,int kingPositionL,char side,int row,int col)
	{
		super("rook" + side + ".png", side, row, col);
//		this.i = i;
//		this.chessBoard = chessBoard;
//		this.kingPositionC = kingPositionC;
//		this.kingPositionL = kingPositionL;
	}
	
	public String possibleMove(int i)
	{
		String list = "";
		String oldPiece;
		int r = i/8;
		int c = i%8;
		int temp =1;
		for(int j = -1;j<=1;j+=2)
		{
			try
			{
				while(" ".equals(chessBoard[r][c+temp*j]))
				{
					oldPiece = chessBoard[r][c+temp*j];
					chessBoard[r][c] = " ";
					chessBoard[r][c+temp*j] = "R";
					if(isKingSafe())
					{
						list = list+r+c+r+(c+temp*j)+oldPiece;
					}
					chessBoard[r][c] = "R";
					chessBoard[r][c+temp*j] = oldPiece;
					temp++;
				}
				if(Character.isLowerCase(chessBoard[r][c+temp*j].charAt(0)))
				{
					oldPiece = chessBoard[r][c+temp*j];
					chessBoard[r][c] = " ";
					chessBoard[r][c+temp*j] = "R";
					if(isKingSafe())
					{
						list = list+r+c+r+(c+temp*j)+oldPiece;
					}
					chessBoard[r][c] = "R";
					chessBoard[r][c+temp*j] = oldPiece;
				}
			}catch (Exception e)	{}
			temp =1;
			try
			{
				while(" ".equals(chessBoard[r+temp*j][c]))
				{
					oldPiece = chessBoard[r+temp*j][c];
					chessBoard[r][c] = " ";
					chessBoard[r+temp*j][c] = "R";
					if(isKingSafe())
					{
						list = list+r+c+(r+temp*j)+c+oldPiece;
					}
					chessBoard[r][c] = "R";
					chessBoard[r+temp*j][c] = oldPiece;
					temp++;
				}
				if(Character.isLowerCase(chessBoard[r+temp*j][c].charAt(0)))
				{
					oldPiece = chessBoard[r+temp*j][c];
					chessBoard[r][c] = " ";
					chessBoard[r+temp*j][c] = "R";
					if(isKingSafe())
					{
						list = list+r+c+(r+temp*j)+c+oldPiece;
					}
					chessBoard[r][c] = "R";
					chessBoard[r+temp*j][c] = oldPiece;
				}
			}catch (Exception e)	{}
			temp=1;
		}
		return list;
		
	}
	
	public boolean isKingSafe()
	{
		//bishop
		int temp = 1;
		for(int i =-1; i <= 1; i+=2)
		{
			for(int j = -1;j<=1; j+=2)
			{
					try
					{
						while(" ".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8+temp*j]))
						{temp++;}
						if("b".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8+temp*j])||
								"q".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8+temp*j]))
						{
							return false;
						}
						
					} catch (Exception e)	{}
					temp=1;
			}
		}
		//rook/queen
		for(int i =-1; i <= 1; i+=2)
		{
					try
					{
						while(" ".equals(chessBoard[kingPositionC/8][kingPositionC%8+temp*i]))
						{temp++;}
						if("r".equals(chessBoard[kingPositionC/8][kingPositionC%8+temp*i])||
								"q".equals(chessBoard[kingPositionC/8][kingPositionC%8+temp*i]))
						{
							return false;
						}
						
					} catch (Exception e)	{}
					temp=1;
					try
					{
						while(" ".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8]))
						{temp++;}
						if("r".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8])||
								"q".equals(chessBoard[kingPositionC/8+temp*i][kingPositionC%8]))
						{
							return false;
						}
						
					} catch (Exception e)	{}
					temp=1;
		}
		//Knight
		for(int i =-1; i <= 1; i+=2)
		{
			for(int j = -1;j<=1; j+=2)
			{
					try
					{
						if("k".equals(chessBoard[kingPositionC/8+i][kingPositionC%8+j*2]))
						{
							return false;
						}
						
					} catch (Exception e)	{}
					try
					{
						if("k".equals(chessBoard[kingPositionC/8+i*2][kingPositionC%8+j]))
						{
							return false;
						}
						
					} catch (Exception e)	{}
			}
		}
		//pawn
		if(kingPositionC>=16)
		{
			try
			{
				if("p".equals(chessBoard[kingPositionC/8-1][kingPositionC%8-1]))
				{
					return false;
				}
				
			} catch (Exception e)	{}
			try
			{
				if("p".equals(chessBoard[kingPositionC/8-1][kingPositionC%8+1]))
				{
					return false;
				}
				
			} catch (Exception e)	{}
		}
		//King
		for(int i =-1; i <= 1; i++)
		{
			for(int j = -1;j<=1; j++)
			{
				if(i!=0||j!=0)
				{
					try
					{
						if("a".equals(chessBoard[kingPositionC/8+i][kingPositionC%8+j]))
						{
							return false;
						}
						
					} catch (Exception e)	{}
				}
			}
		}
		return true;
	}


}
