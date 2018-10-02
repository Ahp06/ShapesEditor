package graphics.shapes;

public interface ShapeVisitor {

	public void visitRectangle(SRectangle rect) throws ShapeException;
	
	public void visitCircle(SCircle circle) throws ShapeException; 
	
	public void visitText(SText text) throws ShapeException; 
	
	public void visitCollection(SCollection composite) throws ShapeException;

	public void visitLine(SLine sLine) throws ShapeException;

	public void visitImage(SImage sImage); 
	
	
}
