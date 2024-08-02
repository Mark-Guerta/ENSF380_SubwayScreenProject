package ca.ucalgary.edu.ensf380;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class advertisementDisplay extends Display {
	private JLabel adverstiment;
	public advertisementDisplay() {
		super();
	}
	private Image rescaleImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = resizedImg.createGraphics();

	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.drawImage(srcImg, 0, 0, w, h, null);
	    g2d.dispose();

	    return resizedImg;
	}
	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub
		
	}
}
