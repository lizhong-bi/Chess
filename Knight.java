
public class Knight extends piece{

	private int i;
	private static int kingPositionC;
	private int kingPositionL;
	private static String [][] chessBoard;
	
	public Knight(int i,String [][] chessBoard,int kingPositionC,int kingPositionL,char side,int row,int col)
	{
		super("knight" + side + ".png", side, row, col);
//		this.i = i;
//		this.chessBoard = chessBoard;
//		this.kingPositionC = kingPositionC;
//		this.kingPositionL = kingPositionL;
	}
	

	String possibleMove(int i) {

		String list = "";
		String oldPiece;
		int r = i/8;
		int c = i%8;
		for(int j = -1;j<=1;j+=2)
		{
			for(int k = -1;k<=1; k+=2)
			{
					try
					{
						if(Character.isLowerCase(chessBoard[r+j][c+k*2].charAt(0))||" ".equals(chessBoard[r+j][c+k*2]))
						{
							oldPiece = chessBoard[r+j][c+k*2];
							chessBoard[r][c] = " ";
							chessBoard[r+j][c+k*2] = "K";
							if(isKingSafe())
							{
								list = list+r+c+(r+j)+(c+k*2)+oldPiece;
							}
							chessBoard[r][c] = "K";
							chessBoard[r+j][c+k*2] = oldPiece;
							
						}
					}catch (Exception e)	{}
					try
					{
						if(Character.isLowerCase(chessBoard[r+j*2][c+k].charAt(0))||" ".equals(chessBoard[r+j*2][c+k]))
						{
							oldPiece = chessBoard[r+j*2][c+k];
							chessBoard[r][c] = " ";
							chessBoard[r+j*2][c+k] = "K";
							if(isKingSafe())
							{
								list = list+r+c+(r+j*2)+(c+k)+oldPiece;
							}
							chessBoard[r][c] = "K";
							chessBoard[r+j*2][c+k] = oldPiece;
							
						}
					}catch (Exception e)	{}
			}
		}
		return list;
		
	}


	boolean isKingSafe() {
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

