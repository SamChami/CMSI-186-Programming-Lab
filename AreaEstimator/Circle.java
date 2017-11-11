public class Circle {
	private double x;
	private double y;
	private double radius;

	private double leftBound;
	private double rightBound;
	private double upperBound;
	private double lowerBound;

	public Circle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;

	}

	public double getLeftBound() {
		return this.x - this.radius;
	}
	public double getRightBound() {
		return this.x + this.radius;
	}
	public double getUpperBound() {
		return this.y + this.radius;
	}
	public double getLowerBound() {
		return this.y - this.radius;
	}
	public double getRadius() {
		return this.radius;
	}
}