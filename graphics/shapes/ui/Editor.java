package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class Editor extends JFrame {
	
	ShapesView sview;
	SCollection model;
	

	public Editor() {
		
		super("Shapes Editor");
		
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});

		this.buildModel();
		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(500, 500));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
		this.addKeyListener(new ShapesController(this.model));
		
		// Extension : Menu 
		Menu shapesMenu = new Menu(this); 
		shapesMenu.initMenu(); 
		this.setJMenuBar(shapesMenu);

	}

	private void buildModel() {
		this.model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());

		SRectangle r1 = new SRectangle(new Point(350, 200), 100, 100);
		r1.addAttributes(new ColorAttributes(true, false, Color.BLUE, Color.BLUE));
		r1.addAttributes(new SelectionAttributes());
		
		
		SCollection sc = new SCollection(); 
		sc.addAttributes(new SelectionAttributes());
		
		SCircle c = new SCircle();
		c.addAttributes(new ColorAttributes(true, false, Color.MAGENTA, Color.MAGENTA));
		c.addAttributes(new SelectionAttributes());
		
		SText t = new SText(new Point(100, 150), "Ceci est un partiel");
		t.addAttributes(new ColorAttributes(true, true, Color.BLUE, Color.BLUE));
		t.addAttributes(new FontAttributes(Color.ORANGE));
		t.addAttributes(new SelectionAttributes());
		
		SRectangle r2 = new SRectangle(new Point(150,20), 70, 80);
		r2.addAttributes(new ColorAttributes(true, true, Color.YELLOW, Color.BLACK));
		r2.addAttributes(new SelectionAttributes());
		
		
		sc.add(c);
		sc.add(t);
		sc.add(r2);	
		
		this.model.add(r1);
		this.model.add(sc);
	}

	public static void main(String[] args) {
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);

	}
}
 