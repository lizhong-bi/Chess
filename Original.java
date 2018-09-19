public class Original {
	static String [][] chessBoard = {
		{"r","k","b","q","a","b","k","r"},
		{"p","p","p","p","p","p","p","p"},
		{" "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "},
		{" "," "," "," "," "," "," "," "},
		{"R"," "," ","A"," "," "," ","R"},
};
	static int kingPositionC;
	static int kingPositionL;
	public static void main(String [] args)
	{
		while(!"A".equals(chessBoard[kingPositionC/8][kingPositionC%8])) {kingPositionC++;}
		while(!"a".equals(chessBoard[kingPositionL/8][kingPositionL%8])) {kingPositionL++;}
//		JFrame frame = new JFrame("Chess");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		UserInterface ui = new UserInterface();
//		frame.add(ui);
//		frame.setSize(500,500);
//		frame.setVisible(true);
		System.out.println(possibleMoves());
	}
	
/**
 * 
 * @return
 */
	public static String possibleMoves(){
		String list = "";
		for(int i=0;i<64;i++)
		{
			switch(chessBoard[i/8][i%8])
			{
			case "P": list+=possibleP(i);
				break;
//			case "R":Rook rook = new Rook(i,chessBoard,kingPositionC,kingPositionL);
//				list+=rook.possibleMove(i);
//				break;
			case "K": list+=possibleK(i);
				break;
			case "B": list+=possibleB(i);
				break;
			case "Q": list+=possibleQ(i);
				break;
			case "A": list+=possibleA(i);
				break;
			}
		}
		return list;//x1,y1,x2,y2,captured piece
	}
	
	
	public static String possibleP(int i)
	{		String list = "";
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
	
/**
 * 
 * @param i
 * @return
 */
//	public static String possibleR(int i)
//	{
//		String list = "";
//		String oldPiece;
//		int r = i/8;
//		int c = i%8;
//		int temp =1;
//		for(int j = -1;j<=1;j+=2)
//		{
//			try
//			{
//				while(" ".equals(chessBoard[r][c+temp*j]))
//				{
//					oldPiece = chessBoard[r][c+temp*j];
//					chessBoard[r][c] = " ";
//					chessBoard[r][c+temp*j] = "R";
//					if(isKingSafe())
//					{
//						list = list+r+c+r+(c+temp*j)+oldPiece;
//					}
//					chessBoard[r][c] = "R";
//					chessBoard[r][c+temp*j] = oldPiece;
//					temp++;
//				}
//				if(Character.isLowerCase(chessBoard[r][c+temp*j].charAt(0)))
//				{
//					oldPiece = chessBoard[r][c+temp*j];
//					chessBoard[r][c] = " ";
//					chessBoard[r][c+temp*j] = "R";
//					if(isKingSafe())
//					{
//						list = list+r+c+r+(c+temp*j)+oldPiece;
//					}
//					chessBoard[r][c] = "R";
//					chessBoard[r][c+temp*j] = oldPiece;
//				}
//			}catch (Exception e)	{}
//			temp =1;
//			try
//			{
//				while(" ".equals(chessBoard[r+temp*j][c]))
//				{
//					oldPiece = chessBoard[r+temp*j][c];
//					chessBoard[r][c] = " ";
//					chessBoard[r+temp*j][c] = "R";
//					if(isKingSafe())
//					{
//						list = list+r+c+(r+temp*j)+c+oldPiece;
//					}
//					chessBoard[r][c] = "R";
//					chessBoard[r+temp*j][c] = oldPiece;
//					temp++;
//				}
//				if(Character.isLowerCase(chessBoard[r+temp*j][c].charAt(0)))
//				{
//					oldPiece = chessBoard[r+temp*j][c];
//					chessBoard[r][c] = " ";
//					chessBoard[r+temp*j][c] = "R";
//					if(isKingSafe())
//					{
//						list = list+r+c+(r+temp*j)+c+oldPiece;
//					}
//					chessBoard[r][c] = "R";
//					chessBoard[r+temp*j][c] = oldPiece;
//				}
//			}catch (Exception e)	{}
//			temp=1;
//		}
//		return list;
//		
//	}
	
/**
 * 
 * @param i
 * @return
 */
	public static String possibleK(int i)
	{
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
	
/**
 * 
 * @param i
 * @return
 */
	public static String possibleB(int i)
	{String list = "";
	String oldPiece;
	int r = i/8;
	int c = i%8;
	int temp = 1;
	for(int j =-1; j <= 1; j+=2)
	{
		for(int k = -1;k<=1; k+=2)
		{
				try
				{
					while(" ".equals(chessBoard[r+temp*j][c+temp*k]))
					{
						oldPiece = chessBoard[r+temp*j][c+temp*k];
						chessBoard[r][c]=" ";
						chessBoard[r+temp*j][c+temp*k] = "B";
						if(isKingSafe())
						{
							list = list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
						}
						chessBoard[r][c]="B";
						chessBoard[r+temp*j][c+temp*k] = oldPiece;
						temp++;
					}
					if(Character.isLowerCase(chessBoard[r+temp*j][c+temp*k].charAt(0)))
					{

						oldPiece = chessBoard[r+temp*j][c+temp*k];
						chessBoard[r][c]=" ";
						chessBoard[r+temp*j][c+temp*k] = "B";
						if(isKingSafe())
						{
							list = list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
						}
						chessBoard[r][c]="B";
						chessBoard[r+temp*j][c+temp*k] = oldPiece;
					}
				}catch (Exception e)	{}
				temp=1;
		}
	}
		return list;
		
	}
	
/**
 * 
 * @param i
 * @return
 */
	public static String possibleQ(int i)
	{
		String list = "";
		String oldPiece;
		int r = i/8;
		int c = i%8;
		int temp = 1;
		for(int j =-1; j <= 1; j++)
		{
			for(int k = -1;k<=1; k++)
			{
				if(j!=0||k!=0)
				{
					try
					{
						while(" ".equals(chessBoard[r+temp*j][c+temp*k]))
						{
							oldPiece = chessBoard[r+temp*j][c+temp*k];
							chessBoard[r][c]=" ";
							chessBoard[r+temp*j][c+temp*k] = "Q";
							if(isKingSafe())
							{
								list = list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
							}
							chessBoard[r][c]="Q";
							chessBoard[r+temp*j][c+temp*k] = oldPiece;
							temp++;
						}
						if(Character.isLowerCase(chessBoard[r+temp*j][c+temp*k].charAt(0)))
						{
	
							oldPiece = chessBoard[r+temp*j][c+temp*k];
							chessBoard[r][c]=" ";
							chessBoard[r+temp*j][c+temp*k] = "Q";
							if(isKingSafe())
							{
								list = list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
							}
							chessBoard[r][c]="Q";
							chessBoard[r+temp*j][c+temp*k] = oldPiece;
						}
					}catch (Exception e)	{}
					temp=1;
				}
			}
		}
		return list;
		
	}
	
/**
 * 
 * @param i
 * @return
 */
	public static String possibleA(int i)
	{	String list = "";
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
	public static boolean isKingSafe()
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
