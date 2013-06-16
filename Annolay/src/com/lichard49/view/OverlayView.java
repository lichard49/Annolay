package com.lichard49.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setColor(new Color(0, 0, 0, 1));
		g.fillRect(0, 0, getWidth(), getHeight());
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
