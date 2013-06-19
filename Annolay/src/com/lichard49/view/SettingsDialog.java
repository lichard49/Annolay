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
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lichard49.model.PointerListener;

public class SettingsDialog extends JDialog implements WindowListener
{
	private static int DEFAULT_STROKE_WIDTH = 10;
	
	JFrame parent;
	public static Color color = Color.BLUE;
	public static int width = DEFAULT_STROKE_WIDTH;
	
	public SettingsDialog(JFrame frame)
	{
		super(frame, "ANNOtation overLAY");
		parent = frame;
		
		addWindowListener(this);
		setSize(100, 200);
		setVisible(true);
		setLayout(new GridLayout(4, 1));
		
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
		
		JLabel widthLabel = new JLabel("Pen width");
		add(widthLabel);
		final JSlider widthSlider = new JSlider(0, 20);
		widthSlider.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				width = widthSlider.getValue();
			}
		});
		add(widthSlider);
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
