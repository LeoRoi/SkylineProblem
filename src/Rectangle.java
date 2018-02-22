import java.awt.Color;

/**
 * rectangle figure
 */
class Rectangle {
	int x, y, w, h;
	Color color;

	Rectangle(int tX, int tY, int tB, int tH, Color nC) {
		this.x = tX;
		this.y = tY;
		this.w = tB;
		this.h = tH;
		this.color = nC;
		System.out.println(this.toString());
	}
	/*
	@Override
	public String toString() {
		return "Rectangle created [x = " + x + ", y = " + y + ", width = " + w + ", hight = " + h + ", color = " + color + "]";
	}
	*/
	@Override
	public String toString() {
		return "Rectangle created [x1 = " + x + ", y1 = " + y + ", x2 = " + (x + w) + ", y2 = " + 460 + "]";
	}
}
