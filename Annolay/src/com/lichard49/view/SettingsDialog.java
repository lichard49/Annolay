package com.lichard49.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;

import com.lichard49.model.PointerListener;

public class SettingsDialog extends JDialog implements WindowListener
{
	JFrame parent;
	public static Color color = Color.BLUE;
	
	public SettingsDialog(JFrame frame)
	{
		super(frame, "ANNOtation overLAY");
		parent = frame;
		
		addWindowListener(this);
		setSize(100, 100);
		setVisible(true);
		setLayout(new GridLayout(1, 2));
		
		JButton colorChooserButton = new JButton("Choose color");
		colorChooserButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				color = JColorChooser.showDialog(parent, "Pick a Color", color);
			}
		});
		add(colorChooserButton);
		
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PointerListener.undoPath();
			}
		});
		add(undoButton);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
