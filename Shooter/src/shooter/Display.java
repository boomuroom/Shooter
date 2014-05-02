package shooter;

import shooter.util.Color;
import shooter.util.Font;
import shooter.util.Rectangle;

public interface Display {
	public void start();
	public void finish();
	public void shadeRectangle(Rectangle shape, Color color);
	public void outlineRectangle(Rectangle shape, Color color);
	public void drawString(String str, Font color, double centerX, double centerY);
}
