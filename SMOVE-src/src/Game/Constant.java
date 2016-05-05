package Game;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Constant {
	/**
	 * WINDOW CONSTANT
	 */
	public static final int WINDOW_WIDTH=280;
	public static final int WINDOW_HEIGHT=430;
	
	
	/**
	 * MATRIX CONSTANT
	 */
	public static final int MATRIX_WIDTH=120;
	public static final int MATRIX_HEIGHT=120;
	
	public static final int MATRIX_UP_DOUNDARY=145;
	public static final int MATRIX_DOWN_DOUNDARY=265;
	public static final int MATRIX_LEFT_DOUNDARY=80;
	public static final int MATRIX_RIGHT_DOUNDARY=200;
	
	public static final int MATRIX_TOPLEFT_X=80;
	public static final int MATRIX_TOPLEFT_Y=145;
	
	public static final int MATRIX_ROUND_arc=38;  
	
	public static final int MATRIX_CELL_WIDTH=40;
	public static final int MATRIX_CELL_HEIGHT=40;
	
	
	/**
	 *  PALYBALL CONSTANT
	 */
	public static final int PLAYBALLSTARTX=85;
	public static final int PLAYBALLSTARTY=150;
	public static final int PLAYBALLWIDTH=30;
	public static final int PLAYBALLHEIGHT=30;
	
	
	/**
	 *  BLOCKBALL CONSTANT
	 */
	public static final int BLOCKBALLWIDTH=30;
	public static final int BLOCKBALLHEIGHT=30;
	
	/**
	 *  IMAGE CONSTANT
	 */
	public static final int IMAGE_X=230;
	public static final int IMAGE_Y=15;
	public static final int IMAGEWIDTH=28;
	public static final int IMAGEHEIGHT=28;
	
	/**
	 *  start font settings 
	 */
	public static final int START_X=80;
	public static final int START_Y=120;
	
	/**
	 *   关于颜色的一些定义
	 */
	public static final Color WINDOW_BACKGROUND=new Color(228,244,155);
	public static final Color MATRIXCOLOR=new Color(248,242,249);
	//public static final Color PLAYBALLCOLOR=new Color(128,209,204); 淡蓝
	public static final Color PLAYBALLCOLOR=Color.white;
	public static final Color BLOCKBALLCOLOR=new Color(128,225,128);
	public static final Color REWARDBALLCOLOR=new Color(17,174,205);
	
    public static boolean IsBallCrashed(Point cen1,Point cen2)
    {    	
    	return Math.sqrt((cen1.x-cen2.x)*(cen1.x-cen2.x)+(cen1.y-cen2.y)*(cen1.y-cen2.y))<=Constant.PLAYBALLWIDTH;
    }
    
    
    public static boolean IsRectIntersection(Rectangle r1,Rectangle r2)
    {    	
    	return (r1.x<r2.x+r2.width && r1.x+r1.width>r2.x && r1.y<r2.y+r2.height
    			 && r1.y+r1.height>r2.y);
    }

}

enum KeyInfo
{
	KEY_UP(1),
	KEY_DOWN(2),
	KEY_LEFT(3),
	KEY_RIGHT(4),
	NONE(5);
	
	int no;
	KeyInfo(int n){this.no=n;}
}

enum Direction
{
	UP,DOWN,RIGHT,LEFT,NONE
}
