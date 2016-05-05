package Game;

import java.awt.Point;

public class PlayBall
{
	public int x;
	public int y;
	public int speed;
	public Point centreCircle;
	
	public PlayBall()
	{
		x=Constant.PLAYBALLSTARTX;
		y=Constant.PLAYBALLSTARTY;
		centreCircle=new Point(this.x+Constant.PLAYBALLWIDTH/2, this.y+Constant.PLAYBALLHEIGHT/2);
		speed=10;	
	}
	
	public void move(KeyInfo key)
	{
		if(key==KeyInfo.KEY_UP)
		{
			if(this.y-speed>Constant.MATRIX_UP_DOUNDARY)
			{
				this.y=this.y-speed;
			}
		}
		else if(key==KeyInfo.KEY_DOWN)
		{
			if(this.y+Constant.PLAYBALLHEIGHT+speed<Constant.MATRIX_DOWN_DOUNDARY)
			{
				this.y=this.y+speed;
			}
		}
		else if(key==KeyInfo.KEY_LEFT)
		{
			if(this.x-speed>Constant.MATRIX_LEFT_DOUNDARY)
			{
				this.x=this.x-speed;
			}
		}
		else if(key==KeyInfo.KEY_RIGHT)
		{
			if(this.x+Constant.PLAYBALLWIDTH+speed<Constant.MATRIX_RIGHT_DOUNDARY)
			{
				this.x=this.x+speed;
			}
		}
		
		centreCircle.x=this.x+Constant.PLAYBALLWIDTH/2;
		centreCircle.y=this.y+Constant.PLAYBALLHEIGHT/2;

	}
}
