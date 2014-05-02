package shooter.util;

public class Font {
	
	public static final Font def = new Font(Color.yellow, 0.1);
	
	public final Color color;
	public final double size;

	public Font(Color color, double size) {
		this.color = color;
		this.size = size;
	}
}
