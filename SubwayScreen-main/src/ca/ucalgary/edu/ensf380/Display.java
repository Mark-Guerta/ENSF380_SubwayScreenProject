package ca.ucalgary.edu.ensf380;

import javax.swing.JLayeredPane;
/**
 * @author Mark Guerta
 * @author Saif Youssef
 * @version 1.0
 */
public abstract class Display {
	protected JLayeredPane display;
	/**
	 * Default constructor makes a new pane
	 */
	Display() {
		this.display = new JLayeredPane();
	}
	/**
	 * Constructor sets the display manually
	 * @param display
	 */
	Display(JLayeredPane display) {
		this.display = display;
	}
	/**
	 * Abstract method for updating display contents
	 */
	public abstract void updateDisplay();
	// Setter and Getters
	public JLayeredPane getDisplay() {
		return display;
	}
	public void setDisplay(JLayeredPane display) {
		this.display = display;
	}
}
