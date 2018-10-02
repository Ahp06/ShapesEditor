package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SImage;
import graphics.shapes.SLine;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.ShapeException;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ShapeDraftman implements ShapeVisitor {

	public final static ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes(true, false, Color.BLACK,Color.BLACK);
	private Graphics g;
	private Color drawColor = Color.BLACK ; 

	public ShapeDraftman(Graphics g) {
		this.g = g;
	}

	@Override
	public void visitRectangle(SRectangle rect) throws ShapeException {
		// TODO Auto-generated method stub

		ColorAttributes colorAtt = (ColorAttributes) rect.getAttributes("color");
		this.g.setColor(Color.BLACK);

		if (colorAtt == null)
			colorAtt = DEFAULTCOLORATTRIBUTES;

		if (colorAtt.filled == true) {
			this.g.setColor(colorAtt.filledColor);
			this.g.fillRect(rect.getLoc().x, rect.getLoc().y, rect.getRect().width, rect.getRect().height);

		} else if (colorAtt.stroked == true) {
			this.g.setColor(colorAtt.strokedColor);
			this.g.drawRect(rect.getLoc().x, rect.getLoc().y, rect.getRect().width, rect.getRect().height);
		}

		SelectionAttributes sa = (SelectionAttributes) rect.getAttributes(SelectionAttributes.ID);
		if (sa.isSelected() == true)
			this.drawHandler(rect.getBounds());

	}

	@Override
	public void visitCircle(SCircle circle) throws ShapeException {
		// TODO Auto-generated method stub

		ColorAttributes colorAtt = (ColorAttributes) circle.getAttributes("color");
		this.g.setColor(Color.BLACK);

		if (colorAtt == null)
			colorAtt = DEFAULTCOLORATTRIBUTES;

		if (colorAtt.filled == true) {
			this.g.setColor(colorAtt.filledColor);
			this.g.fillOval(circle.getLoc().x, circle.getLoc().y, circle.getBounds().width, circle.getBounds().height);

		} else if (colorAtt.stroked == true) {
			this.g.setColor(colorAtt.strokedColor);
			this.g.drawOval(circle.getLoc().x, circle.getLoc().y, circle.getBounds().width, circle.getBounds().height);
		}

		SelectionAttributes sa = (SelectionAttributes) circle.getAttributes(SelectionAttributes.ID);
		if (sa.isSelected() == true)
			this.drawHandler(circle.getBounds());

	}

	@Override
	public void visitText(SText text) throws ShapeException {
		// TODO Auto-generated method stub

		ColorAttributes colorAtt = (ColorAttributes) text.getAttributes("color");
		FontAttributes fontAtt = (FontAttributes) text.getAttributes("font");

		if (colorAtt == null)
			colorAtt = new ColorAttributes(true,true,Color.WHITE,Color.WHITE);

		if (colorAtt.filled == true) {

			this.g.setColor(colorAtt.filledColor);
			this.g.fillRect(text.getLoc().x, text.getLoc().y, text.getBounds().width, text.getBounds().height);

		} else if (colorAtt.stroked == true) {

			this.g.setColor(colorAtt.strokedColor);
			this.g.drawRect(text.getLoc().x, text.getLoc().y, text.getBounds().width, text.getBounds().height);
		}

		this.g.setColor(fontAtt.fontColor);
		this.g.setFont(fontAtt.font);
		this.g.drawString(text.getText(), text.getBounds().x+2 ,text.getBounds().y+text.getBounds().height-5);

		SelectionAttributes sa = (SelectionAttributes) text.getAttributes(SelectionAttributes.ID);
		if (sa.isSelected() == true)
			this.drawHandler(text.getBounds());

	}

	@Override
	public void visitCollection(SCollection composite) throws ShapeException {
		// TODO Auto-generated method stub
		SelectionAttributes sa = (SelectionAttributes) composite.getAttributes(SelectionAttributes.ID);

		for (Shape s : composite.shapeList) {
				s.accept(this);
		}

		if (sa.isSelected() == true)
			this.drawHandler(composite.getBounds());
	}

	public void drawHandler(Rectangle r) {

		g.setColor(Color.GRAY);
		int width = 10 ; 
		int height = 10 ;
		
		g.fillRect(r.x - 5, r.y - 5, width, height);
		g.fillRect(r.x + r.width - 5, r.y + r.height - 5, width, height);
		g.fillRect(r.x + r.width - 5, r.y - 5, width, height);
		g.fillRect(r.x - 5, r.y + r.height - 5, width, height);

	}

	@Override
	public void visitLine(SLine sLine) throws ShapeException {
		// TODO Auto-generated method stub
		ColorAttributes colorAtt = (ColorAttributes) sLine.getAttributes(ColorAttributes.ID);
		SelectionAttributes sa = (SelectionAttributes) sLine.getAttributes(SelectionAttributes.ID);

		if (colorAtt == null) 
			colorAtt = new ColorAttributes(false,true,this.getDrawColor(),this.getDrawColor());

		if (colorAtt.stroked == true) {
			this.g.setColor(colorAtt.strokedColor);
			this.g.drawLine(sLine.getFirstPoint().x, sLine.getFirstPoint().y, sLine.getSecondPoint().x,sLine.getSecondPoint().y);
		}

		if (sa.isSelected()) {

		}
	}

	public Color getDrawColor() {
		return drawColor;
	}

	public void setDrawColor(Color drawColor) {
		this.drawColor = drawColor;
	}

	@Override
	public void visitImage(SImage sImage) {
		// TODO Auto-generated method stub
		
	}
	
	

}
