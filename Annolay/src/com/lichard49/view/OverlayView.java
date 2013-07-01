package com.lichard49.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Stack;

import javax.swing.JPanel;

import com.lichard49.model.MousePath;
import com.lichard49.presenter.Controller;

/**
 * The view in which all annotations are drawed
 * 
 * @author Richard
 *
 */
public class OverlayView extends JPanel
{
	/** interface with the controller **/
	private Controller controller;
	/** stack of paths to draw**/
	private Stack<MousePath> pathsToDraw = null;
	/** whether to clear the screen or not flag**/
	private boolean clear = false;

	int x=0;
	int y=0;
	
	/**
	 * Constructor sets appropriate mouse listeners
	 * 
	 * @param c
	 */
	public OverlayView(Controller c)
	{
		controller = c;
		
		setFocusable(true);
		
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		addMouseWheelListener(mouseListener);
		addKeyListener(keyListener);
	}
	
	/**
	 * Set the paths to be drawn
	 * 
	 * @param ptd paths to be drawn
	 * @param c clear flag
	 */
	public void setPathsToDraw(Stack<MousePath> ptd, boolean c)
	{
		pathsToDraw = ptd;
		clear = c;
		repaint();
	}
	
	/**
	 * Paint each path in the stack of paths with the appropriate attributes
	 * 
	 * @param g graphics
	 */
	public void paintComponent(Graphics g)
	{
		if(clear)
		{
			clearScreen(g);
		}
		if(pathsToDraw != null)
		{
			for(MousePath currentPath: pathsToDraw)
			{
				Graphics2D g2 = (Graphics2D) g;
				g2.translate(x, y);
				// set transparency to visible
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(currentPath.getColor());
				g2.setStroke(currentPath.getStroke());
				
				Stack<Point> points = currentPath.getPoints();
				// connect the dots
				for(int i = 1; i < points.size(); i++)
				{
					Point p1 = points.get(i-1);
					Point p2 = points.get(i);
					g2.drawLine(p1.x, p1.y, p2.x, p2.y);
				}
			}
		}
	}
	
	/**
	 * Clear the screen with a transparent color
	 * 
	 * @param g
	 */
	private void clearScreen(Graphics g)
	{
		((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		g.setColor(new Color(0, 0, 0, 1));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * Mouse listener that signals the controller of user interaction
	 */
	private MouseAdapter mouseListener = new MouseAdapter()
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			controller.mousePressed(e.getX(), e.getY());
		}
		
		@Override
		public void mouseDragged(MouseEvent e)
		{
			controller.mouseDragged(e.getX(), e.getY());
		}
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			x += e.getWheelRotation();
			clear=true;
			repaint();
		}
	};
	
	private KeyListener keyListener = new KeyListener()
	{

		@Override
		public void keyTyped(KeyEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				y-=5;
				break;
			case KeyEvent.VK_DOWN:
				y+=5;
				break;
			case KeyEvent.VK_LEFT:
				x-=5;
				break;
			case KeyEvent.VK_RIGHT:
				x+=5;
				break;
			}
			clear = true;
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
}

