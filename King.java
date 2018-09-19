
public class King extends piece{

	private int i;
	private static int kingPositionC;
	private int kingPositionL;
	private static String [][] chessBoard;
	
	public King(int i,String [][] chessBoard,int kingPositionC,int kingPositionL,char side,int row,int col)
	{
		super("king" + side + ".png", side, row, col);
//		this.i = i;
//		this.chessBoard = chessBoard;
//		this.kingPositionC = kingPositionC;
//		this.kingPositionL = kingPositionL;
	}
	
	
	String possibleMove(int i) {
		String list = "";
		String oldPiece;
		int r = i/8; int c = i%8;
		for(int j = 0; j <9; j++)
		{
			if(j!=4)
			{
				try {
					if(Character.isLowerCase(chessBoard[r-1+j/3][c-1+j%3].charAt(0))||" ".equals(chessBoard[r-1+j/3][c-1+j%3]))
					{
						oldPiece = chessBoard[r-1+j/3][c-1+j%3];
						chessBoard[r][c]=" ";
						chessBoard[r-1+j/3][c-1+j%3] = "A";
						int kingTemp = kingPositionC;
						kingPositionC = i+(j/3)*8+j%3-9;
						if(isKingSafe())
						{
							list = list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
						}
						chessBoard[r][c] = "A";
						chessBoard[r-1+j/3][c-1+j%3] = oldPiece;
						kingPositionC = kingTemp;
					}
				}
				catch (Exception e)	{}
			}
		}
		return list;
		//Need to add castling later;
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

