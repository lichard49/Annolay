package com.lichard49.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

public class MousePath
{
	private ArrayList<Point> points;
	private Color color;
	private Stroke stroke;
	
	public MousePath(int x0, int y0)
	{
		points = new ArrayList<Point>();
		points.add(new Point(x0, y0));
		
		color = Color.BLUE;
		stroke = new BasicStroke(10, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND);
	}
	
	public void appendPoint(int x1, int y1)
	{
		points.add(new Point(x1, y1));
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(color);
		g2.setStroke(stroke);
		
		for(int i = 1; i < points.size(); i++)
		{
			Point p1 = points.get(i-1);
			Point p2 = points.get(i);
			g2.drawLine(p1.x, p1.y, p2.x, p2.y);
		}
	}
}
