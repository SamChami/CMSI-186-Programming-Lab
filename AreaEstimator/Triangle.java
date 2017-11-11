public class Triangle {
	public double x1;
	public double y1;
	public double x2;
	public double y2;
	public double x3;
	public double y3;

	private double trianglePoints[] = new double[6];

	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;   
	}

	public double getLeftBound() {
		double minimum = this.x1;
		if (this.x2 < minimum) {
			minimum = this.x2;
		}
		if (this.x3 < minimum) {
			minimum = this.x3;
		}
		return minimum;
	}

	public double getRightBound() {
		double maximum = this.x1;
		if (this.x2 > maximum) {
			maximum = this.x2;
		}
		if (this.x3 > maximum) {
			maximum = this.x3;
		}
		return maximum;
	}

	public double getLowerBound(){
		double minimum = this.y1;
		if ( this.y2 < minimum ) {
			 minimum = this.y2;
		}
		if ( this.y3 < minimum ) {
			 minimum = this.y3;
		}
		return minimum;
	}
	
	public double getUpperBound(){
		double maximum = this.y1;
		if ( this.y2 > maximum ) {
			 maximum = this.y2;
		}
		if ( this.y3 > maximum ) {
			 maximum = this.y3;
		}
		return maximum;
	}
}