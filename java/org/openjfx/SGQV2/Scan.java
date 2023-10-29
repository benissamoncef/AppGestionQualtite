package org.openjfx.SGQV2;

public class Scan {

	
	public String scanForDrawing(String Textfield) {
		
		int index = Textfield.lastIndexOf('-');
			
		Textfield = Textfield.substring(0, index);
		
		return Textfield;
	}
	
	
	
	

}
