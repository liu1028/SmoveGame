package Game;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameEngine {
    /**
     *  some properties	
     */
	public int eatBallCnt;  //the number of eaten RewardBall
	//public boolean IsEnded;
	public boolean IsStarted;
	private ArrayList<BlockBall> blockBalls;    //the number of the blockBalls
	private PlayBall playBall;
	private RewardBall rewardBall;
    private KeyEventCapture keyEvent;
    
    private int []pos;
	private int []bianNum;
	
	public GameEngine()
	{
		//还有一些初始化工作要做
		eatBallCnt=0;
		//IsEnded=false;
		IsStarted=false;
		
		playBall=new PlayBall();
		blockBalls=new ArrayList<BlockBall>();
		rewardBall=new RewardBall();
		
		/**
		 *  pos[12]表示每边分三个格子方向，0、1、2来自UP，3,4,5来自RIGHT
		 *  										6,7,8来自DOWN，9,10,11来自LEFT
		 */
		pos=new int[12];
		for(int i=0;i<12;i++)
			pos[i]=0;
		
		bianNum=new int[4];
		for(int i=0;i<4;i++)
			bianNum[i]=0;
	}
	
	
	/**
	 *  some functions
	 */
	public void Run()
	{
		MoveAllElement();
	}
	
	public void Initialize()
	{
		eatBallCnt=0;
		
		playBall=new PlayBall();
		
		blockBalls.clear();
		rewardBall=new RewardBall();
		
		pos=new int[12];
		for(int i=0;i<12;i++)
			pos[i]=0;
		
		bianNum=new int[4];
		for(int i=0;i<4;i++)
			bianNum[i]=0;
		
	    frame=0;
		
		eatFlag=false;
		eatFrame=0;
        keyEvent.keyInfo=KeyInfo.NONE;
		
		System.gc();
	}
	
	int frame=0;
	
	boolean eatFlag=false;
	int eatFrame=0;
	
    public void  MoveAllElement()
    {
    	/**
    	 *    playball behavior
    	 */
    	
    	//TODO 玩家球的碰撞检测
    	for(BlockBall ball:blockBalls)
    	{
    		if(Constant.IsBallCrashed(playBall.centreCircle, ball.centreCircle))
    		{
    			IsStarted=false;
    		}
    	}

    	if(keyEvent.IsInFrame && frame!=4 )
    	{
    		frame+=1;
    		playBall.move(keyEvent.keyInfo);   		
    	}
    	else 
    	{
    		frame=0;
    		keyEvent.IsInFrame=false;
    	}
    	
    	
    	//TODO 玩家球的吃球情况
    	if(rewardBall.IsLife&&Constant.IsBallCrashed(playBall.centreCircle, rewardBall.centreCircle))
    	{
    		eatFlag=true;
    		rewardBall.IsLife=false;
    		eatBallCnt++;
    	}
    	
    	if(eatFlag)
    	{
    		eatFrame++;
    	}
    	
    	/**
    	 *   Blockball behavior
    	 */
    	
      	//blockball 的碰撞检测
    	for(BlockBall ball:blockBalls)
    	{
    		if(Constant.IsBallCrashed(playBall.centreCircle, ball.centreCircle))
    		{
    			IsStarted=false;
    		}
    	}

    	
    	for(BlockBall ball:blockBalls)
    	{
    		if(!ball.IsLife)
    		{
    			pos[ball.srcID]=0;
    			bianNum[Math.floorDiv(ball.srcID, 3)]--;
    			ball.autoProduce(eatBallCnt,pos,bianNum,blockBalls);
    			ball.IsLife=true;
    			ball.IsVisible=false;
    		}
    		ball.moveAuto();
    	}
    	if(eatBallCnt<=10)
    	{
    		while(blockBalls.size()<4)
    		{
    			BlockBall ball=new BlockBall();
    			ball.autoProduce(eatBallCnt,pos,bianNum,blockBalls);
    			blockBalls.add(ball);
    		}
    	}
    	else if(eatBallCnt<=20)
    	{
    		while(blockBalls.size()<6)
    		{
    			BlockBall ball=new BlockBall();
    			ball.autoProduce(eatBallCnt,pos,bianNum,blockBalls);
    			blockBalls.add(ball);    			
    		}
    	}//else if   未完待续。。。。
    	
  
    	
    	
    	/**
    	 * rewardBall behavior
    	 */
    	if(!rewardBall.IsLife&&eatFrame>=7)
    	{
    		rewardBall.produceRewardBall(blockBalls, playBall);
    		if(rewardBall.IsLife)
    		{
    			eatFlag=false;
    			eatFrame=0;
    		}
    	}
    }
    
    public void RenderAllElement(Graphics g)
    {
    	paintBackGround(g);
    	paintRewardBall(g); 
    	paintPlayBall(g);
    	paintBlockBalls(g);  
    	paintString(g);
    }
    
    private BasicStroke basic;
	private void paintBackGround(Graphics g)
    {
	    basic=new BasicStroke(3);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setStroke(basic);
		g2d.setColor(Constant.MATRIXCOLOR);
		g2d.drawRoundRect(Constant.MATRIX_TOPLEFT_X,Constant.MATRIX_TOPLEFT_Y,
				Constant.MATRIX_WIDTH,Constant.MATRIX_HEIGHT
				,Constant.MATRIX_ROUND_arc,Constant.MATRIX_ROUND_arc);
		
		basic=new BasicStroke(2);
		g2d.setStroke(basic);
		//vertical
		g2d.drawLine(120, 153, 120, 258);
		g2d.drawLine(160, 153,160, 258);
		//horizontal
		g2d.drawLine(88, 185, 193, 185);
		g2d.drawLine(88, 225, 193, 225);
    }
	
	private void paintPlayBall(Graphics g)
	{
		// TODO Auto-generated method stub
		g.setColor(Constant.PLAYBALLCOLOR);
		g.fillOval(playBall.x, playBall.y, Constant.PLAYBALLWIDTH, Constant.PLAYBALLHEIGHT);
	}

    private void paintBlockBalls(Graphics g) 
    {
		// TODO Auto-generated method stub
    	for(BlockBall ball:blockBalls)
    	{
    		g.setColor(Constant.BLOCKBALLCOLOR);
    		g.fillOval(ball.x, ball.y, Constant.BLOCKBALLWIDTH, Constant.BLOCKBALLHEIGHT);
    	}
		
	}

	private void paintRewardBall(Graphics g) 
	{
		// TODO Auto-generated method stub
		if(!rewardBall.IsLife)
			return;
		g.setColor(Constant.REWARDBALLCOLOR);
		g.fillOval(rewardBall.x, rewardBall.y,Constant.PLAYBALLWIDTH,Constant.PLAYBALLHEIGHT);
	}

	private void paintString(Graphics g)
	{
		String str=String.valueOf(eatBallCnt);
		
		g.setColor(Constant.MATRIXCOLOR);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
		g.drawString(str, 10, 50);
	}
	
    public void setKeyEventCapture(KeyEventCapture key)
    {
    	this.keyEvent=key;
    }
    
}
