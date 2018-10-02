package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SImage extends Shape{
	
	private String imgPath; 

	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void translate(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accept(ShapeVisitor visitor) throws ShapeException {
		// TODO Auto-generated method stub
		visitor.visitImage(this); 
	}

	@Override
	public Shape copy() throws ShapeException {
		// TODO Auto-generated method stub
		return null;
	}

}
