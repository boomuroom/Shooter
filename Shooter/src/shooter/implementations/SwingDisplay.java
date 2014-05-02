package shooter.implementations;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import javax.swing.JComponent;
import shooter.Display;
import shooter.util.Color;
import shooter.util.Font;
import shooter.util.Rectangle;

public class SwingDisplay implements Display {
	
	private Dimension size;
	private Component component;
	private Graphics canvas;
	private BufferStrategy strat;
	private final double fontFactor = 1.225;
	public SwingDisplay(Window comp) {
		this.component = comp;
		strat = comp.getBufferStrategy();
	}
	
	@Override
	public void drawString(String str, Font font, double centerX, double centerY) {
		canvas.setFont(new java.awt.Font(null, 0, (int)(font.size * size.height * fontFactor)));
		canvas.setColor(new java.awt.Color(font.color.argb & 0xFFFFFF));
		FontMetrics fm = canvas.getFontMetrics();
		int width = fm.stringWidth(str);
		int height = fm.getAscent();
		int sx = (int)(size.width * centerX - width/2);
		int sy = (int)(size.height * centerX + height/2);
		canvas.drawString(str, sx, sy);
	}

	@Override
	public void start() {
		this.size = component.getSize();
		this.canvas = strat.getDrawGraphics();
	}
	
	@Override
	public void finish() {
		canvas.dispose();
		strat.show();
	}
	
	@Override
	public void shadeRectangle(Rectangle shape, Color color) {
		//if(component != null) {
			int xs = (int)(shape.x * size.width);
			int ys = (int)(shape.y * size.height);
			int width = (int)(shape.width * size.width);
			int height = (int)(shape.height * size.height);
			canvas.setColor(new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
			canvas.fillRect(xs, ys, width, height);
		//}
	}
	
	@Override
	public void outlineRectangle(Rectangle shape, Color color) {
		//if(component != null) {
			int xs = (int)(shape.x * size.width);
			int ys = (int)(shape.y * size.height);
			int xe = (int)(shape.getRight() * size.width)-1;
			int ye = (int)(shape.getBottom()* size.height)-1;
			canvas.setColor(new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
			canvas.drawLine(xs, ys, xe, ys);
			canvas.drawLine(xe, ys, xe, ye);
			canvas.drawLine(xe, ye, xs, ye);
			canvas.drawLine(xs, ye, xs, ys);
		//}
	}
}
