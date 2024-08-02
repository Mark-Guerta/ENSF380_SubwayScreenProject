package ca.ucalgary.edu.ensf380;

import javax.swing.JLayeredPane;

public abstract class Display {
	protected JLayeredPane display;
	Display() {
		this.display = new JLayeredPane();
	}
	Display(JLayeredPane display) {
		this.display = display;
	}
	public abstract void updateDisplay();
	
	public JLayeredPane getDisplay() {
		return display;
	}
	public void setDisplay(JLayeredPane display) {
		this.display = display;
	}
}
