package com.lichard49.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;


public class PointerListener implements MouseListener, MouseMotionListener 
{
	private ArrayList<MousePath> paths;
	private MousePath currentPath;
	
	public PointerListener()
	{
		paths = new ArrayList<MousePath>();
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		currentPath = new MousePath(e.getX(), e.getY());
		paths.add(currentPath);
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		currentPath.appendPoint(e.getX(), e.getY());
	}
	
	public ArrayList<MousePath> getPaths()
	{
		return paths;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { }
	
	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
	@Override
	public void mouseMoved(MouseEvent e) { }
}
