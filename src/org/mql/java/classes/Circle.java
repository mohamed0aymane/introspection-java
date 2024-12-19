package org.mql.java.classes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle implements Shape{
	//x et y sont des coordonnes du centre du cercle
	//ray le rayon du cercle
	private int x,y;
	private int ray;
	private Color color;
	public int a;
	public String m;
	public Integer mq;
	protected String q;

	public Circle(int x, int y, int ray) {
		super();
		this.x = x;
		this.y = y;
		this.ray = ray;
	}
	//surchage=surdefinition
	public Circle(int x, int y, int ray, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.ray = ray;
		this.color = color;
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRay() {
		return ray;
	}
	public void setRay(int ray) {
		this.ray = ray;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	//drawOval est utilisée pour dessiner un ovale
	//on calcule la position de l’ovale avec (x - ray, y - ray) 
	//pour obtenir la position du coin supérieur gauche de l'ovale englobant.
	//Les dimensions de l'ovale sont 2 * ray 
	//pour obtenir le diamètre, ce qui le transforme en cercle parfait.
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.drawOval(x-ray, y-ray, 2*ray,2*ray);//draw pour dessin un circle /fill pour dessin un cercle et le remplis
		
	}
	
}
