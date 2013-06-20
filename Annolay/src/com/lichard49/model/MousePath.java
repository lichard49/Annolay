package com.lichard49.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Stack;

/**
 * Data structure that stores information about a mouse's travel path,
 * including its visual components
 * 
 * @author Richard
 *
 */

public class MousePath
{
	/** the points that make up this path **/
	private Stack<Point> points;
	/** the color this path should be rendered with **/
	private Color color;
	/** the width this path should be rendered with **/
	private int width;
	/** the stroke created based on the width **/
	private Stroke stroke;
	
	/**
	 * Constructor that accepts an initial point, as well as color and
	 * width attributes
	 * 
	 * @param c
	 * @param w
	 * @param x0
	 * @param y0
	 */
	public MousePath(Color c, int w, int x0, int y0)
	{
		points = new Stack<Point>();
		points.add(new Point(x0, y0));
		
		color = c;
		width = w;
		stroke = new BasicStroke(width, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);
	}
	
	/**
	 * Append a point to the end of this path
	 * 
	 * @param x1
	 * @param y1
	 */
	public void appendPoint(int x1, int y1)
	{
		points.add(new Point(x1, y1));
	}

	/**
	 * Get the points that make up this path
	 * 
	 * @return stack of points
	 */
	public Stack<Point> getPoints()
	{
		return points;
	}
	
	/**
	 * Get the color of this path
	 * 
	 * @return color
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Get the stroke of this path
	 * 
	 * @return stroke
	 */
	public Stroke getStroke()
	{
		return stroke;
	}
}
