package com.lichard49.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.lichard49.model.MousePath;
import com.lichard49.model.PointerListener;

public class OverlayView extends JPanel implements ActionListener
{
	Timer t = new Timer(10, this);
	PointerListener listener;
	
	public OverlayView(PointerListener m)
	{
		listener = m;
		this.addMouseListener(m);
		this.addMouseMotionListener(m);
		t.start();
	}
	
	public void paintComponent(Graphics g)
	{
		for(MousePath mp: listener.getPaths())
		{
			mp.draw(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
}
