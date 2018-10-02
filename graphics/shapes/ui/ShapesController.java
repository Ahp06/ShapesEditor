package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JColorChooser;
import graphics.shapes.SCollection;
import graphics.shapes.SLine;
import graphics.shapes.Shape;
import graphics.shapes.ShapeException;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {

	private Point click = new Point();
	private Point previousClick = new Point();

	public ShapesController(Object newModel) {
		// TODO Auto-generated constructor stub

		super(newModel);

	}

	public Shape getTarget(MouseEvent e) {
		SCollection model = (SCollection) this.getModel();
		// System.out.println(model);
		for (Iterator<Shape> it = model.iterator(); it.hasNext();) {
			Shape s = it.next();
			if (s.getBounds().contains(e.getPoint())) {
				return s;
			}
		}
		return null;

	}

	public void translateSelected(int x, int y) throws ShapeException {
		SCollection model = (SCollection) this.getModel();
		for (Iterator<Shape> it = model.iterator(); it.hasNext();) {
			Shape s = it.next();
			SelectionAttributes sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			if (sa.isSelected() == true)
				s.translate(x, y);
		}

	}

	public void unselectAll() throws ShapeException {
		SCollection model = (SCollection) this.getModel();
		if (model == null)
			return;
		for (Iterator<Shape> it = model.iterator(); it.hasNext();) {
			Shape s = it.next();
			((SelectionAttributes) s.getAttributes(SelectionAttributes.ID)).unselect();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		this.click = (Point) e.getPoint().clone();
		Shape target = (Shape) this.getTarget(e);
		// System.out.println(target);
		try {
			if (!e.isShiftDown())
				this.unselectAll();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (target != null)
			try {
				((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).toggleSelection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		super.getView().repaint();

	}

	// Extension : Palette de couleurs si clic droit sur une forme ou ailleurs
	@Override
	public void mouseReleased(MouseEvent e) {

		this.previousClick = null;
		this.click = null;

		SCollection model = (SCollection) this.getModel();
		SelectionAttributes sa = null;
		ColorAttributes ca = null;
		SCollection composite = new SCollection() ; 
		
		if (e.getButton() == MouseEvent.BUTTON3) {

			for (Shape s : model.shapeList) {

				try {
					sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
					ca = (ColorAttributes) s.getAttributes(ColorAttributes.ID);
				} catch (ShapeException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				//Si une forme est sélectionnée 
				if (sa.isSelected() && s.getBounds().contains(e.getX(), e.getY())) {
					Color colorChoosen = JColorChooser.showDialog(null, "Choose a new Color for this shape", null);
					if (colorChoosen != null) {
						ca = new ColorAttributes(true, true, colorChoosen, colorChoosen);
						s.addAttributes(ca);
						this.getView().repaint();

					}
				}
				
				//Si une forme composite est sélectionnée 
				if(sa.isSelected() && s.getClass()==(new SCollection()).getClass()){
					composite = (SCollection) s ; 
					for(int i = 0 ; i < composite.shapeList.size() ; i++){
						composite.shapeList.get(i).addAttributes(ca); 
					}
				}
					
			}
			
			//Si l'arrière plan est sélectionné 
			
			if (this.getTarget(e) == null) {
				Color colorChoosen = JColorChooser.showDialog(null, "Choose a new Color for the background", null);
				if (colorChoosen != null)
					this.getView().setBackground(colorChoosen);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public boolean shiftDown(KeyEvent evt) {
		return evt.isShiftDown();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		SCollection model = (SCollection) this.getModel();
		for (Shape s : model.shapeList) {
			SelectionAttributes sa = null;
			try {
				sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			} catch (ShapeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (s.getBounds().contains(e.getX(), e.getY())) {
				if (e.isShiftDown())
					sa.toggleSelection();

				else
					sa.select();
			}

		}

		this.getView().repaint();
	}

	// Extension : si touche Alt enfoncé on peut dessiner en déplacant la souris
	// click gauche enfoncé

	@Override
	public void mouseDragged(MouseEvent evt) {
		int x = evt.getX();
		int y = evt.getY();

		int dx = x - this.click.x;
		int dy = y - this.click.y;

		SCollection model = (SCollection) this.getModel();
		SelectionAttributes sa = new SelectionAttributes(false);

		for (Shape s : model.shapeList) {

			try {
				sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (sa.isSelected()) {
				try {
					this.translateSelected(dx, dy);
				} catch (ShapeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.click = evt.getPoint();
			}

		}

		if (evt.isAltDown()) {

			this.previousClick = this.click;
			this.click = evt.getPoint();
			SLine line = new SLine(previousClick, click);
			line.addAttributes(new SelectionAttributes(false));
			model.add(line);
			this.getView().repaint();

		}

		this.getView().repaint();

	}

	// Extension suppression de Shape(s) avec la touche SUPPR du clavier
	@Override
	public void keyPressed(KeyEvent evt) {

		SelectionAttributes sa = null ; 
		SCollection model = (SCollection) this.getModel();
		ArrayList<Shape> shapesSelected = new ArrayList<Shape>();

		for (Shape s : model.shapeList) {
			try {
				sa = (SelectionAttributes) s.getAttributes(SelectionAttributes.ID);
			} catch (ShapeException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (sa.isSelected()) {
				shapesSelected.add(s);
			}

		}

		if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
			model.shapeList.removeAll(shapesSelected);
		}
		


	}
}
