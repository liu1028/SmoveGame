package Game;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class RewardBall
{
	public int x;
	public int y;
	public boolean IsLife;
	public Point centreCircle;
	
	public RewardBall()
	{
	    this.x=Constant.PLAYBALLSTARTX+2*(Constant.MATRIX_CELL_WIDTH);
	    this.y=Constant.PLAYBALLSTARTY+2*(Constant.MATRIX_CELL_HEIGHT);
		IsLife=true;
		centreCircle=new Point(this.x+Constant.PLAYBALLWIDTH/2, this.y+Constant.PLAYBALLHEIGHT/2);

	}
	
	private int a[][]=new int[3][3];
	ArrayList<Point> cellsNoBalls=new ArrayList<Point>();
	Random ran=new Random();

	
	public void produceRewardBall(ArrayList<BlockBall> blockBalls,PlayBall playBall)
	{
		int i,j;
		for( i=0;i<3;i++)
			for(j=0;j<3;j++)
				a[i][j]=0;
		
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				for(BlockBall ball:blockBalls)
				{
					if(a[i][j]==1)
						break;
					
					Rectangle r1=new Rectangle(Constant.MATRIX_LEFT_DOUNDARY+i*Constant.MATRIX_CELL_WIDTH,
							Constant.MATRIX_UP_DOUNDARY+j*Constant.MATRIX_CELL_HEIGHT,
							   Constant.MATRIX_CELL_WIDTH,Constant.MATRIX_CELL_HEIGHT);
					Rectangle r2=new Rectangle(ball.x,ball.y,Constant.PLAYBALLWIDTH,Constant.PLAYBALLHEIGHT);
					
					if(Constant.IsRectIntersection(r1, r2))
					{
						a[i][j]=1;
					}
				}
			}
		}
		
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if(a[i][j]==1)
					continue;
				Rectangle r1=new Rectangle(Constant.MATRIX_LEFT_DOUNDARY+i*Constant.MATRIX_CELL_WIDTH,
						Constant.MATRIX_UP_DOUNDARY+j*Constant.MATRIX_CELL_HEIGHT,
						   Constant.MATRIX_CELL_WIDTH,Constant.MATRIX_CELL_HEIGHT);
				Rectangle r2=new Rectangle(playBall.x,playBall.y,Constant.PLAYBALLWIDTH,Constant.PLAYBALLHEIGHT);
				
				if(Constant.IsRectIntersection(r1, r2))
				{
					a[i][j]=1;
				}
			}
		}
		
		
		cellsNoBalls.clear();
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				if(a[i][j]==0)
				{
					cellsNoBalls.add(new Point(i,j));
				}
			}
		}
		
		int num=cellsNoBalls.size();
		if(num!=0)
		{
			Point p=null;
			
			int playBallX=(playBall.x-Constant.PLAYBALLSTARTX)/Constant.MATRIX_CELL_WIDTH;
			int playBallY=(playBall.y-Constant.PLAYBALLSTARTY)/Constant.MATRIX_CELL_HEIGHT;
			
			Iterator<Point> iter=cellsNoBalls.iterator();
			while(iter.hasNext())
			{
				Point po=(Point)iter.next();
				if(Math.abs(po.x-playBallX)+Math.abs(po.y-playBallY)<=1)
				{
					iter.remove();
				}
			}
			
			if(cellsNoBalls.size()!=0)
			{
				p=cellsNoBalls.get(ran.nextInt(cellsNoBalls.size()));
				
			    this.x=Constant.PLAYBALLSTARTX+p.x*(Constant.MATRIX_CELL_WIDTH);
			    this.y=Constant.PLAYBALLSTARTY+p.y*(Constant.MATRIX_CELL_HEIGHT);
			    this.IsLife=true;
				centreCircle.x=this.x+Constant.PLAYBALLWIDTH/2;
				centreCircle.y=this.y+Constant.PLAYBALLHEIGHT/2;
			}
		}

	}
}
