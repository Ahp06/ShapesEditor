package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class FontAttributes extends Attributes {
	
	public static String ID = "font";
	public Font font ; 
	public Color fontColor ; 
	public static Font DEFAULT_FONT = new Font("Arial",Font.PLAIN,14);
	public static Color DEFAULT_FONT_COLOR = Color.BLACK;
	
	public FontAttributes(Font font, Color fontColor){
		this.font = font ; 
		this.fontColor = fontColor ; 
	}
	
	public FontAttributes(Color fontColor){
		this.font = DEFAULT_FONT; 
		this.fontColor = fontColor ; 
	}
	public FontAttributes(){
		this.font = DEFAULT_FONT ; 
		this.fontColor = DEFAULT_FONT_COLOR ; 
	}
	
	
	public String getId(){
		return FontAttributes.ID ; 
	}
	
	public Rectangle getBounds(String ID){ 
		
		AffineTransform at = new AffineTransform(); 
		FontRenderContext frc = new FontRenderContext(at,true,true); 
		
		return (Rectangle) this.font.getStringBounds(ID, frc);
	}  
	
	public String toString(){
		return "FontAttributes : " + fontColor ;  
	}

}
