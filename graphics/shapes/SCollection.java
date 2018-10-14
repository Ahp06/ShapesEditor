package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.SImage;

public class SCollection extends Shape {

	public ArrayList<Shape> shapeList;
	private Point loc;

	public SCollection() {
		this.shapeList = new ArrayList<Shape>();
		this.loc = new Point(0, 0);

	}

	public void add(Shape s) {
		this.shapeList.add(s);
	}

	// Extension
	public void removeShape(Shape s) {

		this.shapeList.remove(s);
	}

	@Override
	public void setLoc(Point p) {
		this.loc = p;
	}

	@Override
	public Point getLoc() {

		return this.loc;

	}

	@Override
	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
		for (Shape s : shapeList) {
			s.translate(dx, dy);
		}

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub

		Rectangle bounds = new Rectangle();

		/*
		 * for (int i = 0; i < shapeList.size(); i++) { bounds =
		 * bounds.union(shapeList.get(i).getBounds()); }
		 */

		int xMin = this.shapeList.get(0).getBounds().x;
		int yMin = this.shapeList.get(0).getBounds().y;
		int xMax = 0;
		int yMax = 0;

		for (int i = 0; i < this.shapeList.size(); i++) {

			xMin = Math.min(xMin, this.shapeList.get(i).getBounds().x);

			yMin = Math.min(yMin, this.shapeList.get(i).getBounds().y);

			xMax = Math.max(xMax, this.shapeList.get(i).getBounds().x + this.shapeList.get(i).getBounds().width);

			yMax = Math.max(yMax, this.shapeList.get(i).getBounds().y + this.shapeList.get(i).getBounds().height);

		}

		bounds = new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);

		return bounds;

	}

	@Override
	public void accept(ShapeVisitor visitor) throws ShapeException {
		// TODO Auto-generated method stub
		visitor.visitCollection(this);
	}

	public String toString() {
		StringBuilder tmp = new StringBuilder("SCollection : ");

		for (Shape s : shapeList) {
			tmp.append(s.toString());
			tmp.append(" ; ");
		}
		return tmp.toString();

	}

	public Iterator<Shape> iterator() {
		// TODO Auto-generated method stub
		return this.shapeList.iterator();
	}

	@Override
	public Shape copy() throws ShapeException {
		// TODO Auto-generated method stub
		SCollection copy = new SCollection();
		copy.addAttributes(new SelectionAttributes());

		for (int i = 0; i < this.shapeList.size(); i++) {
			copy.add(shapeList.get(i).copy());
			copy.shapeList.get(i).translate(shapeList.get(i).getLoc().x, shapeList.get(i).getLoc().y);
			
		}

		copy.translate(-this.getBounds().x,-this.getBounds().y); //Positionnement au point (0,0) 
		
		

		return copy;
	}

}
