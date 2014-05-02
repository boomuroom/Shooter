package shooter.util;

public final class Rectangle {
	
	public double x;
	public double y;
	public double width;
	public double height;
	
	public Rectangle() { }
	
	public Rectangle(Rectangle r) {
		copy(r);
	}

	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void copy(Rectangle r) {
		this.x = r.x;
		this.y = r.y;
		this.width = r.width;
		this.height = r.height;
	}
	
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public double getLeft() {
		return x;
	}

	public double getRight() {
		return x+width;
	}
	
	public double getTop() {
		return y;
	}
	
	public double getBottom() {
		return y+height;
	}
	
    public boolean intersects(Rectangle r) {
        double tw = this.width;
        double th = this.height;
        double rw = r.width;
        double rh = r.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.x;
        double ty = this.y;
        double rx = r.x;
        double ry = r.y;
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
	
	@Override
	public String toString() {
		return String.format("%s[%.3f, %.3f, %.3f, %.3f]", getClass().getName(), x, y, width, height);
	}
}
