package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SRectangle extends Shape {

	private Rectangle rect;

	public SRectangle(Point p, int width, int height) {

		this.rect = new Rectangle(width, height);
		this.rect.setLocation(p);

	}

	public SRectangle(int width, int height) {

		this.rect = new Rectangle(width, height);
		this.rect.setLocation(0, 0);
	}
	
	public SRectangle(){
		this.rect = new Rectangle(100,100);
		this.rect.setLocation(0,0); 
	}

	@Override
	public void translate(int dx, int dy) {
		this.rect.translate(dx, dy);
	}

	@Override
	public void setLoc(Point p) {
		this.rect.setLocation(p);
	}

	@Override
	public Point getLoc() {
		return this.rect.getLocation();
	}

	@Override
	public Rectangle getBounds() {
		return this.rect;
	}

	@Override
	public void accept(ShapeVisitor v) throws ShapeException {
		v.visitRectangle(this);
	}

	public Rectangle getRect() {
		return (Rectangle) rect.clone();
	}

	public String toString() {
		StringBuilder tmp = new StringBuilder("SRectangle(");
		tmp.append(this.rect.getX());
		tmp.append(",");
		tmp.append(this.rect.getY());
		tmp.append(",");
		tmp.append(this.rect.getWidth());
		tmp.append(",");
		tmp.append(this.rect.getHeight());
		tmp.append(")");

		return tmp.toString();
	}

	@Override
	public Shape copy() throws ShapeException {
		// TODO Auto-generated method stub
		SRectangle copy = new SRectangle(this.rect.width,this.rect.height);
		copy.addAttributes(new SelectionAttributes());
		
		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		if(ca == null )
			ca = new ColorAttributes(true,false,Color.BLACK,Color.BLACK);

		copy.addAttributes(ca);
		
		return copy;
	}


}
