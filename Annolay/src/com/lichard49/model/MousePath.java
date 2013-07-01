package com.lichard49.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

/**
 * Data structure that stores information about a mouse's travel path,
 * including its visual components
 * 
 * @author Richard
 *
 */

public class MousePath implements Serializable
{	
	/** the points that make up this path **/
	private Stack<Point> points;
	/** the color this path should be rendered with **/
	private Color color;
	/** the stroke created based on the width **/
	private BasicStroke stroke;
	
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
		
		setColor(c);
		setWidth(w);
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
	public Stack<Point> getPoints() { return points; }
	
	/**
	 * Get the color of this path
	 * 
	 * @return color
	 */
	public Color getColor() { return color; }
	
	/**
	 * Get the stroke of this path
	 * 
	 * @return stroke
	 */
	public Stroke getStroke() { return stroke; }
	
	/**
	 * Set the color of this path
	 * 
	 * @param c
	 */
	private void setColor(Color c) { color = c; }
	
	/**
	 * Set the points that make up this path
	 * 
	 * @param p
	 */
	private void setPoints(Stack<Point> p) { points = p; }
	
	/**
	 * Set the width of this path
	 * 
	 * @param w
	 */
	private void setWidth(float w)
	{
		stroke = new BasicStroke(w, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);
	}
	
	/**
	 * Serialization behavior writes the attributes to the output stream in the
	 * following order: color, stroke width, and points
	 * 
	 * Overrides Serializable method
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.writeObject(color);
		out.writeFloat(stroke.getLineWidth());
		out.writeObject(points);
	}
	
	/**
	 * Serialization behavior reads the attributes out of the input stream in
	 * the following order: color, stroke width, and points
	 * 
	 * Overrides Serializable method
	 * 
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		setColor((Color) in.readObject());
		setWidth(in.readFloat());
		setPoints((Stack<Point>)in.readObject());
	}
}
