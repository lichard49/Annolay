package com.lichard49.presenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.lichard49.model.PointerListener;
import com.lichard49.view.OverlayView;

public class AnnolayMain
{
	private static final Color TRANSPARENT_BACKGROUND = new Color(0, 0, 0, 1);
	
	public static void main(String[] args)
	{
		// create frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// frame configuration
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setBackground(TRANSPARENT_BACKGROUND);
		frame.setContentPane(new OverlayView(new PointerListener()));
		
		// frame size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		int xSize = (int) screenSize.getWidth();  
		int ySize = (int) screenSize.getHeight();  
		frame.setSize(xSize,ySize);
		
		frame.setVisible(true);
	}
}
