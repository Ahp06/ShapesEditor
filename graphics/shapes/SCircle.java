package graphics.shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SCircle extends Shape{
	
	private int radius ; 
	private Point loc ; 
	
	public SCircle(Point loc, int radius){
		this.loc = loc ; 
		this.radius = radius ; 
	}
	
	public SCircle(int radius){
		this.loc = new Point(0,0); 
		this.radius = radius ; 
	}
	
	public SCircle(Point loc){
		this.loc = loc ; 
		this.radius = 50 ; 
	}
	
	public SCircle(){
		this.loc = new Point(0,0); 
		this.radius = 50 ; 
	}
	
	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub
		this.loc.setLocation(p);
		
	}

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return this.loc;
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(new Point(this.loc.x,this.loc.y),new Dimension(2*radius,2*radius));
	}
	
	@Override
	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
		this.loc.translate(dx, dy);
	}

	@Override
	public void accept(ShapeVisitor v) throws ShapeException {
		// TODO Auto-generated method stub
		v.visitCircle(this);
	}
	
	public String toString() {
		StringBuilder tmp = new StringBuilder("SCircle(");
		tmp.append(this.loc.getX());
		tmp.append(",");
		tmp.append(this.loc.getY());
		tmp.append(",");
		tmp.append(this.radius);
		tmp.append(")");

		return tmp.toString();
	}

	@Override
	public Shape copy() throws ShapeException {
		// TODO Auto-generated method stub	
		SCircle copy = new SCircle(this.radius);
		copy.addAttributes(new SelectionAttributes());
		
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null )
			ca = new ColorAttributes(true,false,Color.BLACK,Color.BLACK);
		
		copy.addAttributes(ca);
		
		
		return copy ; 
	}

	

}
