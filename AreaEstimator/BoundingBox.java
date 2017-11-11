public class BoundingBox {
	public double lowestValue;
	public double highestValue;
	public double leftValue;
	public double rightValue;

	public BoundingBox(double lowestValue, double highestValue, double leftValue, double rightValue) {
		this.lowestValue = lowestValue;
		this.highestValue = highestValue;
		this.leftValue = leftValue;
		this.rightValue = rightValue;
	}
}