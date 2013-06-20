package com.lichard49.presenter;

import java.awt.Color;
import java.awt.Dimension;
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
	private static final Color TRANSLUCENT_BACKGROUND = new Color(0, 0, 0, 1);
	//private static final Color TRANSPARENT_BACKGROUND = new Color(0, 0, 0, 0);
	
	public static void main(String[] args)
	{
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		int xSize = (int) screenSize.getWidth();  
		int ySize = (int) screenSize.getHeight();  
		frame.setSize(xSize,ySize);
		
		// show frame and dialog
		frame.setVisible(true);
		controller.setSettingsDialogVisible(true);
	}
}
