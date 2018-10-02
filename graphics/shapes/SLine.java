package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SLine extends Shape {

	private Point p1;
	private Point p2;

	public SLine(Point p1, Point p2) {
		// TODO Auto-generated constructor stub
		this.p1 = p1;
		this.p2 = p2;
	}

	public SLine(int x1, int y1, int x2, int y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}

	public Point getFirstPoint() {
		// TODO Auto-generated method stub
		return this.p1;
	}

	public Point getSecondPoint() {
		return this.p2;
	}

	@Override
	public void accept(ShapeVisitor visitor) throws ShapeException {
		// TODO Auto-generated method stub

		visitor.visitLine(this);
	}

	@Override
	public void translate(int x, int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(0, 0);
	}

	public String toString() {
		StringBuilder tmp = new StringBuilder("SLine[");

		tmp.append("(" + this.p1.x + "," + this.p1.y + ")");
		tmp.append(";");
		tmp.append("(" + this.p2.x + "," + this.p2.y + ")");
		tmp.append("]");

		return tmp.toString();

	}

	public void setLoc(Point p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shape copy() throws ShapeException {
		// TODO Auto-generated method stub
		return null;
	}

}
