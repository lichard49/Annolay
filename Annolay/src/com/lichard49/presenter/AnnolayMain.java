package com.lichard49.presenter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Starts the application by creating the frame and controller and making
 * things visible 
 * 
 * @author Richard
 *
 */
public class AnnolayMain
{
	/** translucent color that has a unit of alpha in order to be tangible **/
	private static final Color TRANSLUCENT_BACKGROUND = new Color(0, 0, 0, 1);
	//private static final Color TRANSPARENT_BACKGROUND = new Color(0, 0, 0, 0);
	
	/**
	 * Main method puts the frame and controller together and configures the
	 * mouse appearance
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// toolkit
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		// create frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		// create controller
		Controller controller = new Controller(frame);
		
		// frame configuration
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setBackground(TRANSLUCENT_BACKGROUND);
		frame.setContentPane(controller.getOverlayView());
		
		// frame size
		Dimension screenSize = tk.getScreenSize();
		int xSize = (int) screenSize.getWidth();  
		int ySize = (int) screenSize.getHeight();  
		frame.setSize(xSize,ySize);
		
		// show frame and dialog
		frame.setVisible(true);
		controller.setSettingsDialogVisible(true);
		
		// change mouse icon to a pen
		Cursor c = tk.createCustomCursor(tk.getImage("res/pen_cursor.png"),
				new Point(frame.getX(), frame.getY()), "img");
		frame.setCursor(c);
	}
}
