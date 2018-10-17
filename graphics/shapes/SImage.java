package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;
public class SImage extends Shape{
	
	private String imgPath;
        private Point loc;
        private BufferedImage bi;
        
        public SImage(Point loc, String path) throws IOException{
		this.loc = loc ; 
		this.imgPath = path ;
                this.bi = ImageIO.read(new File(this.imgPath));
	}
	
	public SImage(String path) throws IOException{
		this.loc = new Point(0,0); 
		this.imgPath = path ; 
                this.bi = ImageIO.read(new File(this.imgPath));
	}

	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub
                this.loc=p;
		
	}

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return this.loc;
	}
        public BufferedImage getBufferedImage(){
            return this.bi;
        }

	@Override
	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
                this.loc.translate(dx, dy);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(this.loc.x, this.loc.y, bi.getWidth(), bi.getHeight());
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
