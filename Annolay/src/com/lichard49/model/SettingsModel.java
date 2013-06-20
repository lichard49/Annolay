package com.lichard49.model;

import java.awt.Color;
import java.util.Stack;

/**
 * Information holder that maintains all data to do with the application
 * 
 * @author Richard
 *
 */
public class SettingsModel
{
	/** default color the pen draws with **/
	private static final Color DEFAULT_COLOR = Color.BLUE;
	/** default width the pen draws with **/
	private static final int DEFAULT_WIDTH = 10;
	
	/** the currently selected color */
	private static Color currentColor = DEFAULT_COLOR;
	/** the currently selected width **/
	private static int currentWidth = DEFAULT_WIDTH;
	
	/** all the paths that make up this overlay **/
	private static Stack<MousePath> paths;
	/** a pointer to the top path in the stack of paths **/
	private static MousePath currentPath; 
	
	/**
	 * Constructor to create stack of paths
	 */
	public SettingsModel()
	{
		paths = new Stack<MousePath>();
	}
	
	/**
	 * Start a new path given initial coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void startNewPath(int x, int y)
	{
		currentPath = new MousePath(currentColor, currentWidth, x, y);
		paths.push(currentPath);
	}
	
	/**
	 * Continue current path by appending a new point
	 * 
	 * @param x
	 * @param y
	 */
	public void continueCurrentPath(int x, int y)
	{
		currentPath.appendPoint(x, y);
	}
	
	/**
	 * Get all paths associated with this overlay
	 * 
	 * @return stack of paths
	 */
	public Stack<MousePath> getPaths()
	{
		return paths;
	}
	
	/**
	 * Undo path by popping the top path
	 * 
	 * @return deleted path
	 */
	public MousePath undoPath()
	{
		if(!paths.isEmpty())
			return paths.pop();
		return null;
	}
	
	/**
	 * Sets the currently selected color;
	 * the next path will be drawn with this color
	 * 
	 * @param c
	 */
	public void setCurrentColor(Color c)
	{
		currentColor = c;
	}
	
	/**
	 * Sets the currently selected width;
	 * the next path will be drawn with this width
	 * @param w
	 */
	public void setCurrentWidth(int w)
	{
		currentWidth = w;
	}
	
	/**
	 * Gets the currently selected color
	 * 
	 * @return color
	 */
	public Color getCurrentColor()
	{
		return currentColor;
	}
	
	/**
	 * Gets the currently selected width
	 * 
	 * @return width
	 */
	public int getCurrentWidth()
	{
		return currentWidth;
	}
}
