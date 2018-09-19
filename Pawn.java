
public class Pawn extends piece{

	private int i;
	private static int kingPositionC;
	private int kingPositionL;
	private static String [][] chessBoard;
	
	public Pawn(int i,String [][] chessBoard,int kingPositionC,int kingPositionL,char side,int row,int col)
	{
		super("pawn" + side + ".png", side, row, col);
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
			try
			{//capture
				if(Character.isLowerCase(chessBoard[r-1][c+j].charAt(0))&&i>=16)
				{
					oldPiece = chessBoard[r-1][c+j];
					chessBoard[r][c] = " ";
					chessBoard[r-1][c+j] = "P";
					if(isKingSafe())
					{
						list = list+r+c+(r-1)+(c+j)+oldPiece;
					}
					chessBoard[r][c] = "P";
					chessBoard[r-1][c+j] = oldPiece;
					}
			}catch (Exception e)	{}
			try
			{//promotion&capture
				if(Character.isLowerCase(chessBoard[r-1][c+j].charAt(0))&&i<16)
				{
					String [] temp = {"Q","R","B","K"};
					for(int k = 0;k<4;k++)
					{
						oldPiece = chessBoard[r-1][c+j];
						chessBoard[r][c] = " ";
						chessBoard[r-1][c+j] = temp[k];
						if(isKingSafe())
						{
							//column1,column2,captured-piece,newpiece,piece
							list = list+c+(c+j)+oldPiece+temp[k]+"P";
						}
						chessBoard[r][c] = "P";
						chessBoard[r-1][c+j] = oldPiece;
					}
					}
			}catch (Exception e)	{}
		}
		try
		{//move up
			if(" ".equals(chessBoard[r-1][c])&&i>=16)
			{
				oldPiece = chessBoard[r-1][c];
				chessBoard[r][c] = " ";
				chessBoard[r-1][c] = "P";
				if(isKingSafe())
				{
					list = list+r+c+(r-1)+c+oldPiece;
				}
				chessBoard[r][c] = "P";
				chessBoard[r-1][c] = oldPiece;
				}
		}catch (Exception e)	{}
		try
		{//promotion and no capture
			if(" ".equals(chessBoard[r-1][c])&&i<16)
			{String [] temp = {"Q","R","B","K"};
			for(int k = 0;k<4;k++)
			{
				oldPiece = chessBoard[r-1][c];
				chessBoard[r][c] = " ";
				chessBoard[r-1][c] = temp[k];
				if(isKingSafe())
				{
					//column1,column2,captured-piece,newpiece,piece
					list = list+c+c+oldPiece+temp[k]+"P";
				}
				chessBoard[r][c] = "P";
				chessBoard[r-1][c] = oldPiece;
			}
		}
	}catch (Exception e)	{}
		
		try
		{//move two up
			if(" ".equals(chessBoard[r-2][c])&&i>=48)
			{
				oldPiece = chessBoard[r-2][c];
				chessBoard[r][c] = " ";
				chessBoard[r-2][c] = "P";
				if(isKingSafe())
				{
					list = list+r+c+(r-2)+c+oldPiece;
				}
				chessBoard[r][c] = "P";
				chessBoard[r-2][c] = oldPiece;
				}
		}catch (Exception e)	{}
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

