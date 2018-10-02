package graphics.shapes.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import graphics.shapes.SCollection;
import graphics.shapes.ShapeException;
import graphics.ui.Controller;
import graphics.ui.View;


public class ShapesView extends View {
	
	public ShapesView(SCollection model) {
		// TODO Auto-generated constructor stub
		super(model);
	}
	
	public ShapesView(SCollection model,Dimension dim){
		super(model);
	}

	public void setThePreferredSize(Dimension dimension) {
		// TODO Auto-generated method stub
		this.setPreferredSize(dimension);
	}
	
	public Controller defaultController(Object model)
	{
		return new ShapesController(model);
	}

	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		SCollection model = (SCollection) this.getModel(); 
		ShapeDraftman draftman = new ShapeDraftman(g) ; 
		try {
			model.accept(draftman);
		} catch (ShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
