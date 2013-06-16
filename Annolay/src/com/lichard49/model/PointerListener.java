package com.lichard49.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;


public class PointerListener implements MouseListener, MouseMotionListener 
{
	private static Stack<MousePath> paths;
	private MousePath currentPath;
	
	public PointerListener()
	{
		paths = new Stack<MousePath>();
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		currentPath = new MousePath(e.getX(), e.getY());
		paths.push(currentPath);
	}

	public static MousePath undoPath()
	{
		if(!paths.isEmpty())
			return paths.pop();
		return null;
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
	
	public Stack<MousePath> getPaths()
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
