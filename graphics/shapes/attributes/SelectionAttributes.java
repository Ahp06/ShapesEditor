package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

	public static String ID = "select";
	private boolean selected = false ; 
	
	public SelectionAttributes(boolean selected){
		this.selected = selected ; 
	}
	
	public SelectionAttributes(){
		this.selected = false ; 
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public boolean isSelected(){
		return selected ; 
	}
	
	public void select(){
		this.selected = true ;  
	}
	
	public void unselect(){
		this.selected = false ; 
	}
	
	public void toggleSelection(){
		
		this.selected = ! this.selected ; 
	}
}
