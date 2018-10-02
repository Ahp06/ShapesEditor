package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

public abstract class Shape {

	public Attributes attribute;
	public Rectangle bounds;
	public ShapeVisitor visitor;
	public HashMap<String, Attributes> attributes;

	public Shape() {

		this.attributes = new HashMap<String, Attributes>();

	}

	public void addAttributes(Attributes attribut) {
		this.attributes.put(attribut.getId(), attribut);
	}

	public Attributes getAttributes(String ID) throws ShapeException {
		return this.attributes.get(ID);
	}
	
	public abstract void setLoc(Point p);

	public abstract Point getLoc();

	public abstract void translate(int x, int y);

	public abstract Rectangle getBounds();

	public abstract void accept(ShapeVisitor visitor) throws ShapeException;

	public abstract Shape copy() throws ShapeException; 

}
