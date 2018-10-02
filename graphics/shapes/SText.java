package graphics.shapes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class SText extends Shape {

	private String text;
	private Point loc;

	public SText(Point loc, String text) {
		this.loc = loc;
		this.text = text;
	}

	public SText(String text) {
		this.loc = new Point(0, 0);
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String newText) {
		this.text = newText;
	}

	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub
		this.loc.setLocation(p);
	}

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return loc;
	}

	@Override
	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
		this.loc.translate(dx, dy);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub

		Font font = null;
		try {
			font = ((FontAttributes) this.getAttributes(FontAttributes.ID)).font;
		} catch (ShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		FontMetrics metrics = new FontMetrics(font) {
		};

		Rectangle2D bounds = metrics.getStringBounds(this.getText(), null);
		int widthInPixels = (int) bounds.getWidth();
		int heightInPixels = (int) bounds.getHeight();

		Rectangle r = new Rectangle(new Point(this.loc.x, this.loc.y),
				new Dimension(widthInPixels + 5, heightInPixels + 5));
		r.setLocation(this.getLoc());

		return r;
	}

	@Override
	public void accept(ShapeVisitor v) throws ShapeException {
		// TODO Auto-generated method stub
		v.visitText(this);
	}

	public String toString() {
		StringBuilder tmp = new StringBuilder("SText(");
		tmp.append(this.loc.getX());
		tmp.append(",");
		tmp.append(this.loc.getY());
		tmp.append(",");
		tmp.append(this.getText());
		tmp.append(")");

		return tmp.toString();
	}

	@Override
	public Shape copy() throws ShapeException {
		// TODO Auto-generated method stub
		SText copy = new SText(this.getText());
		copy.addAttributes(new SelectionAttributes());

		ColorAttributes ca = (ColorAttributes) this.getAttributes(ColorAttributes.ID);
		FontAttributes fa = (FontAttributes) this.getAttributes(FontAttributes.ID);

		if (ca == null)
			ca = new ColorAttributes(true, true, Color.WHITE, Color.WHITE);

		if (fa == null)
			fa = new FontAttributes(FontAttributes.DEFAULT_FONT, FontAttributes.DEFAULT_FONT_COLOR);

		copy.addAttributes(ca);
		copy.addAttributes(fa);

		return copy;
	}

}
