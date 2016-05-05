package Game;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class WindowMain extends JFrame implements MouseListener,MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Timer GAMEtimer;
	private javax.swing.Timer timer;
	private GameEngine gameEngine;
    private KeyEventCapture keyEvent;
    
    public boolean IsPause=false; 
    private ImageIcon icon1=new ImageIcon(getClass().getResource("/image/pause_on.png")); 
   // private ImageIcon icon1=new ImageIcon(getClass().getResource("image.pause_on")); 

    private ImageIcon icon2=new ImageIcon(getClass().getResource("/image/pause_down.png"));
    private ImageIcon icon3=new ImageIcon(getClass().getResource("/image/play_on.png"));
    private ImageIcon icon4=new ImageIcon(getClass().getResource("/image/play_down.png"));
    private ImageIcon icon=icon1;
    
	public WindowMain()
	{	
		this.setUndecorated(true);
		//this.getContentPane().setSize(300,420);
		this.setSize(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setBackground(Constant.WINDOW_BACKGROUND);
		
		this.setVisible(true);
		
		//System.out.println(getClass().getResource("/image/pause_on.png").getPath());
		init();
	   
	}
	

	
	protected void init()
	{
		if(icon2==null)
			System.out.println("NULL");
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		gameEngine=new GameEngine();
		
		keyEvent=new KeyEventCapture(IsPause,this);
		this.addKeyListener(keyEvent);
		
		gameEngine.setKeyEventCapture(keyEvent);
		
		GAMEtimer=new Timer();
		
		timer=new javax.swing.Timer(1000, null);
		
		TimerStart();  //为了测试，将来由按钮来触发
	}
	
	
	private void TimerStart()
	{
		timer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				   if( gameEngine.IsStarted)
				   {
					   gameEngine.Run();
			       				   
				   }
				   else if( !gameEngine.IsStarted) //游戏中死亡
				   {
				   }
				   repaint();
			}			
			
		});
		
		timer.setInitialDelay(1000);
		timer.setDelay(30);
		timer.start();
		
//		GAMEtimer.schedule(new TimerTask() {			
//			@Override
//			public void run() 
//			{
//				// TODO Auto-generated method stub
//			   if( gameEngine.IsStarted)
//			   {
//				   gameEngine.Run();
//		       				   
//			   }
//			   else if( !gameEngine.IsStarted) //游戏中死亡
//			   {
//			   }
//			   repaint();
//			}
//		}, 0, 30);
		
	}
	

	public Image iBuffer;
	public Graphics gBuffer;
	
	public int startUIY1,flag=0;
	public boolean IsFinStart=false;
	public int startWidth=120,startHeight=45;
	
	public Color color1=new Color(193,210,240);
	public Color color2=new Color(150,170,240);
	public Color color=color1;
	/**
	 * double buffer
	 */
	@Override
	public void paint(Graphics g_main)
	{
		if(iBuffer==null)
		{
			iBuffer=this.createImage(Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT);
			gBuffer=iBuffer.getGraphics();
		}
		super.paint(gBuffer);
		
		gBuffer.setColor(Constant.WINDOW_BACKGROUND);
		
		gBuffer.fillRect(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
		

	    gBuffer.drawImage(icon.getImage(), Constant.IMAGE_X, 
				Constant.IMAGE_Y, Constant.IMAGEWIDTH, Constant.IMAGEHEIGHT, null);
	    
		paintAllElement(gBuffer);
		
		 if(!gameEngine.IsStarted) //刚进入游戏
		 {
			 if(flag==0)
			 {
				 startUIY1=0;
				 flag=1;
			 }
			 
			 gBuffer.setColor(new Color(230,230,230,150));
			 
			 if(startUIY1<=215)  
			 {
				 gBuffer.fillRect(0, 0, Constant.WINDOW_WIDTH, startUIY1);
				 gBuffer.fillRect(0, Constant.WINDOW_HEIGHT-startUIY1, Constant.WINDOW_WIDTH, startUIY1);
				 startUIY1+=5;
			 }
			 else
			 {
			
					 IsFinStart=true;
					 gBuffer.fillRect(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
					 
					 String str="Start";		
					 
					 gBuffer.setColor(color);
					 gBuffer.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
					 gBuffer.drawString(str, Constant.START_X, Constant.START_Y);
					 
//					 FontMetrics fm=gBuffer.getFontMetrics();
//					 Rectangle2D rec=fm.getStringBounds(str, gBuffer);
//					 startWidth=(int)rec.getWidth();
//					 startHeight=(int)rec.getHeight();
//					 System.out.println(startWidth+"  "+startHeight);
				 
			 }
		
			   
		 }	   

		
		g_main.drawImage(iBuffer, 0, 0, Constant.WINDOW_WIDTH,Constant.WINDOW_HEIGHT,this);
	
	}

	/**
	 * primary rendering engine
	 */
	public void paintAllElement(Graphics g_buffer)
	{
		gameEngine.RenderAllElement(g_buffer);
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		
		if(gameEngine.IsStarted)
		{
			if(x>=Constant.IMAGE_X && x<=Constant.IMAGE_X+Constant.IMAGEWIDTH &&
					y>=Constant.IMAGE_Y && y<=Constant.IMAGE_Y+Constant.IMAGEHEIGHT)
			{
				if(IsPause==false)
				{
					icon=icon4;
					//GAMEtimer.cancel();
					IsPause=true;
					//System.gc();
					timer.stop();
				}
				else 
				{
					IsPause=false;
					icon=icon2;
					timer.restart();
//					GAMEtimer=new Timer();
//					GAMEtimer.schedule(new TimerTask() {			
//						@Override
//						public void run() 
//						{
//							// TODO Auto-generated method stub
//						   if(gameEngine.IsStarted)
//						   {
//							   gameEngine.Run();
//					       
//							   repaint();
//							   
//						   }
//						}
//					}, 0, 30);
					
				}
			}
		}
		else if( !gameEngine.IsStarted) //刚进入游戏
		{
			if(x>=Constant.START_X&&x<=Constant.START_X+startWidth
					&& y>=Constant.START_Y-40 && y<=Constant.START_Y+startHeight-40)
			{
				gameEngine.IsStarted=true;
				gameEngine.Initialize();
				flag=0;
				IsFinStart=false;
				
			}
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		if(gameEngine.IsStarted )
		{
			if(x>=Constant.IMAGE_X && x<=Constant.IMAGE_X+Constant.IMAGEWIDTH &&
					y>=Constant.IMAGE_Y && y<=Constant.IMAGE_Y+Constant.IMAGEHEIGHT)
			{
				if(IsPause==false)
					icon=icon2;
				else 
					icon=icon4;
			}
			else
			{
				if(IsPause==false)
					icon=icon1;
				else 
					icon=icon3;
			}
		}
		else if(!gameEngine.IsStarted) //刚进入游戏
		{
			if(x>=Constant.START_X&&x<=Constant.START_X+startWidth
					&& y>=Constant.START_Y-40 && y<=Constant.START_Y+startHeight-40)
			{
				color=color2;
			}
			else color=color1;
		}
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseDragged(MouseEvent e) {}

}
