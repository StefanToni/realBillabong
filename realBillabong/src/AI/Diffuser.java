package AI;

public class Diffuser {
	public double[][] weights;
	
	public Diffuser()
	{
		weights = new double[14][16];
		
		for(int i = 0; i<14; i++)
		{
			for(int j = 0; j<16; j++)
			{
				weights[i][j] = 0;
			}
		}
		
		for(int i = 6; i<8; i++)
		{
			for(int j = 6; j<10; j++)
			{
				weights[i][j] = -1000000;
			}
		}
		
		
		
		weights[13][8] = 1000000.0;
		//System.out.println(weights[8][13]);
		
		weights[12][8] = 1000000.0 * (48.0/56.0); //*temp;
		
		weights[11][8] = 1000000.0 * (40.0/48.0);
		
		weights[10][8] = 1000000.0 * (32.0/40.0);
		
		weights[9][8]  = 1000000.0 * (24.0/32.0);
		
		weights[8][8]  = 1000000.0 * (16.0/24.0);
		
		
		//outer ring
		for(int i = 9; i<16; i++)
		{
			weights[13][i] = 0.9 * weights[13][i-1];
		}
		
		for(int i = 12; i>=0; i--)
		{
			weights[i][15] = 0.9 * weights[i+1][15];
		}
		
		for(int i = 14; i>=0; i--)
		{
			weights[0][i] = 0.9 * weights[0][i+1];
		}
		
		for(int i = 1; i<14; i++)
		{
			weights[i][0] = 0.9 * weights[i-1][0];
		}
		
		for(int i = 1; i<8; i++)
		{
			weights[13][i] = 0.9 * weights[13][i-1]; 
		}
		
		
		
		
		
		
		
		
		//1 ring in
		for(int i = 9; i<15; i++)
		{
			weights[12][i] = 0.9 * weights[12][i-1];// + 0.1; //0.9 * weights[12][i-1] + 0.9 * weights[13][i];
		}
		
		for(int i = 11; i>=1; i--)
		{
			weights[i][14] = 0.9 * weights[i+1][14];// + 0.1; //0.9 * weights[i+1][14] + 0.9 * weights[i][15];
		}
		
		for(int i = 13; i>=1; i--)
		{
			weights[1][i] = 0.9 * weights[1][i+1];// + 0.1; //0.9 * weights[1][i+1] + 0.9 * weights[0][i];
		}
		
		for(int i = 2; i<13; i++)
		{
			weights[i][1] = 0.9 * weights[i-1][1];// + 0.1; //0.9 * weights[i-1][1] + 0.9 * weights[i][0];
		}
		
		for(int i = 2; i<8; i++)
		{
			weights[12][i] = 0.9 * weights[12][i-1];// + 0.1; //0.9 * weights[12][i-1] + 0.9 * weights[13][i]; 
		}
		
		
		
		
		//2 rings in
		
		for(int i = 9; i<14; i++)
		{
			weights[11][i] = 0.9 * weights[11][i-1];// + 0.2; //0.9 * weights[11][i-1] + 0.9 * weights[12][i];
		}
		
		for(int i = 10; i>=2; i--)
		{
			weights[i][13] = 0.9 * weights[i+1][13];// + 0.2; //0.9 * weights[i+1][13] + 0.9 * weights[i][14];
		}
		
		for(int i = 12; i>=2; i--)
		{
			weights[2][i] = 0.9 * weights[2][i+1];// + 0.2; //0.9 * weights[2][i+1] + 0.9 * weights[1][i];
		}
		
		for(int i = 3; i<12; i++)
		{
			weights[i][2] = 0.9 * weights[i-1][2];// + 0.2; //0.9 * weights[i-1][2] + 0.9 * weights[i][1];
		}
		
		for(int i = 3; i<8; i++)
		{
			weights[11][i] = 0.9 * weights[11][i-1];// + 0.2; //0.9 * weights[11][i-1] + 0.9 * weights[12][i]; 
		}
		
		//3 rings in
		
		for(int i = 9; i<13; i++)
		{
			weights[10][i] = 0.9 * weights[10][i-1];// + 0.3; //0.9 * weights[10][i-1] + 0.9 * weights[11][i];
		}
		
		for(int i = 9; i>=3; i--)
		{
			weights[i][12] = 0.9 * weights[i+1][12];// + 0.3; //0.9 * weights[i+1][12] + 0.9 * weights[i][13];
		}
		
		for(int i = 11; i>=3; i--)
		{
			weights[3][i] = 0.9 * weights[3][i+1];// + 0.3; //0.9 * weights[3][i+1] + 0.9 * weights[2][i];
		}
		
		for(int i = 4; i<11; i++)
		{
			weights[i][3] = 0.9 * weights[i-1][3];// + 0.3; //0.9 * weights[i-1][3] + 0.9 * weights[i][2];
		}
		
		for(int i = 4; i<8; i++)
		{
			weights[10][i] = 0.9 * weights[10][i-1];// + 0.3; //0.9 * weights[10][i-1] + 0.9 * weights[11][i]; 
		}
		
		
		
		
		//4 rings in
		
		for(int i = 9; i<12; i++)
		{
			weights[9][i] = 0.9 * weights[9][i-1];// + 0.4; //0.9 * weights[9][i-1] + 0.9 * weights[10][i];
		}
		
		for(int i = 8; i>=4; i--)
		{
			weights[i][11] = 0.9 * weights[i+1][11];// + 0.4; //0.9 * weights[i+1][11] + 0.9 * weights[i][12];
		}
		
		for(int i = 10; i>=4; i--)
		{
			weights[4][i] = 0.9 * weights[4][i+1];// + 0.4; //0.9 * weights[4][i+1] + 0.9 * weights[3][i];
		}
				
		for(int i = 5; i<10; i++)
		{
			weights[i][4] = 0.9 * weights[i-1][5];// + 0.4; //0.9 * weights[i-1][4] + 0.9 * weights[i][3];
		}
				
		for(int i = 5; i<9; i++)
		{
			weights[9][i] = 0.9 * weights[9][i-1];// + 0.4; //0.9 * weights[9][i-1] + 0.9 * weights[10][i]; 
		}
		
		
		
		
		
		//5 rings in
		
		for(int i = 9; i<11; i++)
		{
			weights[8][i] = 0.9 * weights[8][i-1];// + 0.5; //0.9 * weights[8][i-1] + 0.9 * weights[9][i];
		}
		
		for(int i = 7; i>=5; i--)
		{
			weights[i][10] = 0.9 * weights[i+1][10];// + 0.5; //0.9 * weights[i+1][10] + 0.9 * weights[i][11];
		}
		
		for(int i = 9; i>=5; i--)
		{
			weights[5][i] = 0.9 * weights[5][i+1];// + 0.5; //0.9 * weights[5][i+1] + 0.9 * weights[4][i];
		}
				
		for(int i = 6; i<9; i++)
		{
			weights[i][5] = 0.9 * weights[i-1][5];// + 0.5; //0.9 * weights[i-1][5] + 0.9 * weights[i][4];
		}
				
		for(int i = 6; i<8; i++)
		{
			weights[8][i] = 0.9 * weights[8][i-1];// + 0.5; //0.9 * weights[8][i-1] + 0.9 * weights[9][i]; 
		}
		
//		//outer ring
//				for(int i = 8; i<14; i++)
//				{
//					weights[i][8] = 1000000;
//				}
//				
//				for(int i = 9; i<16; i++)
//				{
//					weights[13][i] = 0.9 * weights[13][i-1];
//				}
//				
//				for(int i = 12; i>=0; i--)
//				{
//					weights[i][15] = 0.9 * weights[i+1][15];
//				}
//				
//				for(int i = 14; i>=0; i--)
//				{
//					weights[0][i] = 0.9 * weights[0][i+1];
//				}
//				
//				for(int i = 1; i<14; i++)
//				{
//					weights[i][0] = 0.9 * weights[i-1][0];
//				}
//				
//				for(int i = 1; i<8; i++)
//				{
//					weights[13][i] = 0.9 * weights[13][i-1]; 
//				}
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				//1 ring in
//				for(int i = 9; i<15; i++)
//				{
//					weights[12][i] = weights[13][i] + 0.1; //0.9 * weights[12][i-1] + 0.9 * weights[13][i];
//				}
//				
//				for(int i = 11; i>=1; i--)
//				{
//					weights[i][14] = weights[i][15] + 0.1; //0.9 * weights[i+1][14] + 0.9 * weights[i][15];
//				}
//				
//				for(int i = 13; i>=1; i--)
//				{
//					weights[1][i] = weights[0][i] + 0.1; //0.9 * weights[1][i+1] + 0.9 * weights[0][i];
//				}
//				
//				for(int i = 2; i<13; i++)
//				{
//					weights[i][1] = weights[i][0] + 0.1; //0.9 * weights[i-1][1] + 0.9 * weights[i][0];
//				}
//				
//				for(int i = 2; i<8; i++)
//				{
//					weights[12][i] = weights[13][i] + 0.1; //0.9 * weights[12][i-1] + 0.9 * weights[13][i]; 
//				}
//				
//				
//				
//				
//				//2 rings in
//				
//				for(int i = 9; i<14; i++)
//				{
//					weights[11][i] = weights[12][i] + 0.2; //0.9 * weights[11][i-1] + 0.9 * weights[12][i];
//				}
//				
//				for(int i = 10; i>=2; i--)
//				{
//					weights[i][13] = weights[i][14] + 0.2; //0.9 * weights[i+1][13] + 0.9 * weights[i][14];
//				}
//				
//				for(int i = 12; i>=2; i--)
//				{
//					weights[2][i] = weights[1][i] + 0.2; //0.9 * weights[2][i+1] + 0.9 * weights[1][i];
//				}
//				
//				for(int i = 3; i<12; i++)
//				{
//					weights[i][2] = weights[i][1] + 0.2; //0.9 * weights[i-1][2] + 0.9 * weights[i][1];
//				}
//				
//				for(int i = 3; i<8; i++)
//				{
//					weights[11][i] = weights[12][i] + 0.2; //0.9 * weights[11][i-1] + 0.9 * weights[12][i]; 
//				}
//				
//				//3 rings in
//				
//				for(int i = 9; i<13; i++)
//				{
//					weights[10][i] = weights[11][i] + 0.3; //0.9 * weights[10][i-1] + 0.9 * weights[11][i];
//				}
//				
//				for(int i = 9; i>=3; i--)
//				{
//					weights[i][12] = weights[i][13] + 0.3; //0.9 * weights[i+1][12] + 0.9 * weights[i][13];
//				}
//				
//				for(int i = 11; i>=3; i--)
//				{
//					weights[3][i] = weights[2][i] + 0.3; //0.9 * weights[3][i+1] + 0.9 * weights[2][i];
//				}
//				
//				for(int i = 4; i<11; i++)
//				{
//					weights[i][3] = weights[i][2] + 0.3; //0.9 * weights[i-1][3] + 0.9 * weights[i][2];
//				}
//				
//				for(int i = 4; i<8; i++)
//				{
//					weights[10][i] = weights[11][i] + 0.3; //0.9 * weights[10][i-1] + 0.9 * weights[11][i]; 
//				}
//				
//				
//				
//				
//				//4 rings in
//				
//				for(int i = 9; i<12; i++)
//				{
//					weights[9][i] = weights[10][i] + 0.4; //0.9 * weights[9][i-1] + 0.9 * weights[10][i];
//				}
//				
//				for(int i = 8; i>=4; i--)
//				{
//					weights[i][11] = weights[i][12] + 0.4; //0.9 * weights[i+1][11] + 0.9 * weights[i][12];
//				}
//				
//				for(int i = 10; i>=4; i--)
//				{
//					weights[4][i] = weights[3][i] + 0.4; //0.9 * weights[4][i+1] + 0.9 * weights[3][i];
//				}
//						
//				for(int i = 5; i<10; i++)
//				{
//					weights[i][4] = weights[i][3] + 0.4; //0.9 * weights[i-1][4] + 0.9 * weights[i][3];
//				}
//						
//				for(int i = 5; i<8; i++)
//				{
//					weights[9][i] = weights[10][i] + 0.4; //0.9 * weights[9][i-1] + 0.9 * weights[10][i]; 
//				}
//				
//				
//				
//				
//				
//				//5 rings in
//				
//				for(int i = 8; i<11; i++)
//				{
//					weights[8][i] = weights[9][i] + 0.5; //0.9 * weights[8][i-1] + 0.9 * weights[9][i];
//				}
//				
//				for(int i = 7; i>=5; i--)
//				{
//					weights[i][10] = weights[i][11] + 0.5; //0.9 * weights[i+1][10] + 0.9 * weights[i][11];
//				}
//				
//				for(int i = 9; i>=5; i--)
//				{
//					weights[5][i] = weights[4][i] + 0.5; //0.9 * weights[5][i+1] + 0.9 * weights[4][i];
//				}
//						
//				for(int i = 6; i<9; i++)
//				{
//					weights[i][5] = weights[i][4] + 0.5; //0.9 * weights[i-1][5] + 0.9 * weights[i][4];
//				}
//						
//				for(int i = 6; i<8; i++)
//				{
//					weights[8][i] = weights[9][i] + 0.5; //0.9 * weights[8][i-1] + 0.9 * weights[9][i]; 
//				}
		
		
		
		for(int i = 0; i<14; i++)
		{
			for(int j = 0; j<16; j++)
			{
				System.out.print(" "+(int)weights[i][j]+" ");
			}
			System.out.println("-");
		}
		
	}
	
	public double getWeight(int x, int y)
	{
		return weights[y][x];
	}

}
