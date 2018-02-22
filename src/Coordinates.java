/**
 * represents a 2D point like A(2/4)
 */
class Coordinates {
	int x, y;
	
	Coordinates() {
		this.x = 0;
		this.y = 0;
	}
	
	Coordinates(int a, int b) {
		this.x = a;
		this.y = b;
	}

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}
}
