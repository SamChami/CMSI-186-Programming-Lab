public class Point {
	private double x;
	private double y;

	public Point(double lowestValue, double highestValue, double leftValue, double rightValue) {
		this.x = leftValue + Math.random() * (rightValue - leftValue);
		this.y = lowestValue + Math.random() * (highestValue - lowestValue);
	}

	public boolean isInCircle(Circle c) {
		return Math.sqrt(Math.pow((this.x - (c.getLeftBound() + c.getRadius())), 2) + Math.pow((this.y - (c.getLowerBound() + c.getRadius())), 2)) < c.getRadius();
	}

	public boolean isInTriangle(Triangle t) {
		double distanceA = ((t.y2 - t.y3) * (this.x - t.x3) + (t.x3 - t.x2) * (this.y - t.y3)) / ((t.y2 - t.y3)*(t.x1 - t.x3) + (t.x3 - t.x2) * (t.y1 - t.y3));
		double distanceB = ((t.y3 - t.y1) * (this.x - t.x3) + (t.x1 - t.x3) * (t.y1 - t.y3)) / ((t.y2 - t.y3)*(t.x1 - t.x3) + (t.x3 - t.x2) * (t.y1 - t.y3));
		double distanceC = 1.0 - distanceA - distanceB;
		return 0 <= distanceA && distanceA <= 1 && 0 <= distanceB && distanceB <= 1 && 0 <= distanceC && distanceC <= 1;			
	}

	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}

	public String toString() {
		return this.x + " " + this.y;
	}
}