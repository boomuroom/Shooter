package shooter.util;

public class Color {
	
	public static final Color black    = new Color(0xFF000000);
	public static final Color white    = new Color(0xFFFFFFFF);
	public static final Color red      = new Color(0xFFFF0000);
	public static final Color green    = new Color(0xFF00FF00);
	public static final Color blue     = new Color(0xFF0000FF);
	public static final Color cyan     = new Color(0xFF00FFFF);
	public static final Color yellow   = new Color(0xFFFFFF00);
	public static final Color magenta  = new Color(0xFFFF00FF);

	public final int argb;
	public Color(int v) {
		argb = v;
	}
	
	public int getRed() {
		return (argb >> 16) & 0xFF;
	}
	
	public int getGreen() {
		return (argb >> 8) & 0xFF;
	}
	
	public int getBlue() {
		return argb & 0xFF;
	}
	
	public int getAlpha() {
		return (argb >> 24) & 0xFF;
	}
}
