package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyEventCapture implements KeyListener {

    public boolean IsInFrame;
    public boolean IsPressed;
    public KeyInfo keyInfo;
    private WindowMain main;

    public KeyEventCapture(boolean Is,WindowMain w)
    {
    	main=w;
    	IsInFrame=false;
    	IsPressed=false;
    	keyInfo=KeyInfo.NONE;
 
    }
	@Override
	public void keyPressed(KeyEvent e) {
		if(main.IsPause==true )
			return;
		if(!IsInFrame && !IsPressed)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_W:
			case KeyEvent.VK_UP:
				keyInfo=KeyInfo.KEY_UP;
				break;
			case KeyEvent.VK_A:
			case KeyEvent.VK_LEFT:
				keyInfo=KeyInfo.KEY_LEFT;
				break;
			case KeyEvent.VK_S:
			case KeyEvent.VK_DOWN:
				keyInfo=KeyInfo.KEY_DOWN;
				break;
			case KeyEvent.VK_D:
			case KeyEvent.VK_RIGHT:
				keyInfo=KeyInfo.KEY_RIGHT;
				break;
				default:break;
			
			}
			IsPressed=true;
			IsInFrame=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
			if(main.IsPause==true )
				return;
			if(IsPressed)
			{
				IsPressed=false;
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	
	

}
