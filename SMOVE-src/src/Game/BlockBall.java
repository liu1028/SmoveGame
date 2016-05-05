package Game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class BlockBall
{
	public int x;
	public int y;
	public int speed;
	public Direction dir;
	
	public int srcID;
	
	public boolean IsLife;
	public boolean IsVisible;
	
	public Point centreCircle;
	
	public BlockBall()
	{
		x=0;
		y=0;
		speed=3;
		IsLife=true;
		IsVisible=false;
		centreCircle=new Point(this.x+Constant.BLOCKBALLWIDTH/2, this.y+Constant.BLOCKBALLHEIGHT/2);
	}
	
	public void moveAuto()
	{
		if(dir==Direction.LEFT)      //´Ó×ó±ßÀ´
		{
			if(x+Constant.BLOCKBALLWIDTH>0)
				IsVisible=true;
			if(x>Constant.WINDOW_WIDTH)
			{
				IsVisible=false;
				IsLife=false;
			}
			x+=speed;
		}
		else if(dir==Direction.RIGHT)
		{
			if(x<Constant.WINDOW_WIDTH)
				IsVisible=true;
			if(x+Constant.BLOCKBALLWIDTH<0)
			{
				IsVisible=false;
				IsLife=false;
			}
			x-=speed;
		}
		else if(dir==Direction.UP)
		{
			if(y+Constant.BLOCKBALLHEIGHT>0)
				IsVisible=true;
			if(y>Constant.WINDOW_HEIGHT)
			{
				IsVisible=false;
				IsLife=false;
			}
			y+=speed;
		}
		else if(dir==Direction.DOWN)
		{
			if(y<Constant.WINDOW_HEIGHT)
				IsVisible=true;
			if(y+Constant.BLOCKBALLHEIGHT<0)
			{
				IsVisible=false;
				IsLife=false;
			}
			y-=speed;
		}
		centreCircle.x=this.x+Constant.BLOCKBALLWIDTH/2;
		centreCircle.y=this.y+Constant.BLOCKBALLHEIGHT/2;
	}
	
	Random ran=new Random();
	
	public void autoProduce(int eatBallCnt,int[]pos,int []bianNum,ArrayList<BlockBall> blockBalls)
	{
		int n;
		if(eatBallCnt<=10)
		{
			if(bianNum[0]==0)
			{
				n=ran.nextInt(3);
				x=Constant.PLAYBALLSTARTX+n*(10+Constant.BLOCKBALLWIDTH);
				y=(ran.nextInt(Constant.BLOCKBALLHEIGHT*3)+Constant.BLOCKBALLHEIGHT)*(-1);
				dir=Direction.UP;
				bianNum[0]++;
				this.srcID=n+0*3;
			}
			else if(bianNum[1]==0)
			{
				n=ran.nextInt(3);
				y=Constant.PLAYBALLSTARTY+n*(10+Constant.BLOCKBALLHEIGHT);
				x=Constant.WINDOW_WIDTH+ran.nextInt(Constant.BLOCKBALLWIDTH*2);
				dir=Direction.RIGHT;
				bianNum[1]++;
				this.srcID=n+1*3;
			}
			else if(bianNum[2]==0)
			{
				n=ran.nextInt(3);
				x=Constant.PLAYBALLSTARTX+n*(10+Constant.BLOCKBALLWIDTH);
				y=ran.nextInt(Constant.BLOCKBALLHEIGHT*2)+Constant.WINDOW_HEIGHT;
				dir=Direction.DOWN;
				bianNum[2]++;
				this.srcID=n+2*3;
			}
			else if(bianNum[3]==0)
			{
				n=ran.nextInt(3);
				y=Constant.PLAYBALLSTARTY+n*(10+Constant.BLOCKBALLHEIGHT);
				x=(ran.nextInt(Constant.BLOCKBALLWIDTH*3)+Constant.BLOCKBALLWIDTH)*(-1);
				dir=Direction.LEFT;
				bianNum[3]++;
				this.srcID=n+3*3;
			}
		}
		else if(eatBallCnt<=20)
		{
			int dirNum,flag=0;
			do{
				dirNum=ran.nextInt(4);
			}while(bianNum[dirNum]>=2);
			
			do
			{
				flag=0;
				switch(dirNum)
				{
				case 0:
					n=ran.nextInt(3);
					x=Constant.PLAYBALLSTARTX+n*(10+Constant.BLOCKBALLWIDTH);
					y=(ran.nextInt(Constant.BLOCKBALLHEIGHT*3)+Constant.BLOCKBALLHEIGHT)*(-1);
					dir=Direction.UP;
					break;
				case 1:
					n=ran.nextInt(3);
					y=Constant.PLAYBALLSTARTY+n*(10+Constant.BLOCKBALLHEIGHT);
					x=Constant.WINDOW_WIDTH+ran.nextInt(Constant.BLOCKBALLWIDTH*2);
					dir=Direction.RIGHT;
					break;
				case 2:
					n=ran.nextInt(3);
					x=Constant.PLAYBALLSTARTX+n*(10+Constant.BLOCKBALLWIDTH);
					y=ran.nextInt(Constant.BLOCKBALLHEIGHT*2)+Constant.WINDOW_HEIGHT;
					dir=Direction.DOWN;
					break;
				case 3:
					n=ran.nextInt(3);
					y=Constant.PLAYBALLSTARTY+n*(10+Constant.BLOCKBALLHEIGHT);
					x=(ran.nextInt(Constant.BLOCKBALLWIDTH*3)+Constant.BLOCKBALLWIDTH)*(-1);
					dir=Direction.LEFT;
					break;
				default:n=0;break;
				}	
				
				for(BlockBall ball:blockBalls)
				{
					if(Constant.IsBallCrashed(ball.centreCircle, 
							new Point(x+Constant.BLOCKBALLWIDTH/2,y+Constant.BLOCKBALLHEIGHT/2)))
					{
						flag=1;
						break;
					}
				}
				if(flag==0) break;
			}while(true);
			
			bianNum[dirNum]++;
			this.srcID=n+dirNum*3;
			
		}
		
		centreCircle.x=this.x+Constant.BLOCKBALLWIDTH/2;
		centreCircle.y=this.y+Constant.BLOCKBALLHEIGHT/2;

	}
}
