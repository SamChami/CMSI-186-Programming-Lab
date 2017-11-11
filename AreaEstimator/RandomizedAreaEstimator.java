import java.lang.Math;

public class RandomizedAreaEstimator {
	public static double NUMBER_OF_DARTS = 1000000;
	public static void main(String[] args) {
		try {
    		for (int i = 0; i < args.length; i++) {
    			if (args[i].equals("circle")) {
    				for (int j = 1; j < 4; j++) {
	    				Double.parseDouble(args[i + j]);	
	    			}
	    		} else {
	    			Double.parseDouble(args[i]);
	    		}
    			
    			if (args[i].equals("triangle")) {
    				for (int j = 1; j < 7; j++) {
	    				Double.parseDouble(args[i + j]);	
	    			}
    			} else {
	    			Double.parseDouble(args[i]);
	    		}  		
    		}
    	} catch (Exception e) {
      		System.err.println("Incorrect coordinate inputs");
      		System.exit(1);
    	}
		
		int inputLength = args.length;
		int numberOfCircles = 0;
		int numberOfTriangles = 0;
		int triangleIndex = 0;
		int circleIndex = 0;

		for (int i = 0; i < inputLength; i++) {
			if (args[i].toLowerCase().equals("circle")) {
				 numberOfCircles++;
			} else if (args[i].toLowerCase().equals("triangle")) {
				numberOfTriangles++;
			}
		}

		Circle[] circleArr = new Circle[numberOfCircles];
		Triangle[] triArr = new Triangle[numberOfTriangles];

		for (int i = 0; i < inputLength; i++) {
			if (args[i].toLowerCase().equals("circle")) {
				circleArr[circleIndex] = new Circle(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]));
				circleIndex++; 
			} else if (args[i].toLowerCase().equals("triangle")) {
				triArr[triangleIndex] = new Triangle(Double.parseDouble(args[i+1]), Double.parseDouble(args[i+2]), Double.parseDouble(args[i+3]), Double.parseDouble(args[i+4]), Double.parseDouble(args[i+5]), Double.parseDouble(args[i+6]));
				triangleIndex++; 
			}
		}

		double lowestValue = 1000.0;
		double highestValue = -1000.0;
		double leftValue = 1000.0;
		double rightValue = -1000.0;

		for (int i = 0; i < circleArr.length; i++) {
			if (circleArr[i].getLowerBound() < lowestValue) {
				lowestValue = circleArr[i].getLowerBound();
			}
			if (circleArr[i].getRightBound() > rightValue) {
				rightValue = circleArr[i].getRightBound();
			}
			if (circleArr[i].getUpperBound() > highestValue) {
				highestValue = circleArr[i].getUpperBound();
			}
			if (circleArr[i].getLeftBound() < leftValue) {
				leftValue = circleArr[i].getLeftBound();
			}
		}
		for (int i = 0; i < triArr.length; i++) {
			if (triArr[i].getLowerBound() < lowestValue) {
				lowestValue = triArr[i].getLowerBound();
			}
			if (triArr[i].getRightBound() > rightValue) {
				rightValue = triArr[i].getRightBound();
			}
			if (triArr[i].getUpperBound() > highestValue) {
				highestValue = triArr[i].getUpperBound();
			}
			if (triArr[i].getLeftBound() < leftValue) {
				leftValue = triArr[i].getLeftBound();
			}
		}
		
		BoundingBox boundingBox = new BoundingBox(lowestValue, highestValue, leftValue, rightValue);
		System.out.println("Intersection Area: " + getIntersectArea(boundingBox, circleArr, triArr));
		System.out.println("Union Area: " +getUnionArea(boundingBox, circleArr, triArr));
	}

	public static double getIntersectArea(BoundingBox boundingBox, Circle[] circleArr, Triangle[] triArr) {
		double pointsInArea = 0;
		for (int i = 0; i < NUMBER_OF_DARTS; i++) {
			Point checkerPoint = new Point(boundingBox.lowestValue, boundingBox.highestValue, boundingBox.leftValue, boundingBox.rightValue);
			double pointsToAdd = 0;
			boolean continueToTriangle = true;
			for (int j = 0; j < circleArr.length; j++) {
				if (checkerPoint.isInCircle(circleArr[j])) {
					pointsToAdd = 1;
				} else {
					pointsToAdd = 0;
					continueToTriangle = false;
					break;
				}
			}
			for (int k = 0; k < triArr.length; k++) {
				if (checkerPoint.isInTriangle(triArr[k]) && continueToTriangle) {
					pointsToAdd = 1;
				} else {
					pointsToAdd = 0;
					break;
				}
			} 
			pointsInArea = pointsInArea + pointsToAdd;
		}
		return (pointsInArea / NUMBER_OF_DARTS) * ((boundingBox.highestValue) - boundingBox.lowestValue) * (boundingBox.rightValue - boundingBox.lowestValue);
	}

	//Returns addition of all individual areas and prints the individual areas within the method
	public static double getIndividualAreas(BoundingBox boundingBox, Circle[] circleArr, Triangle[] triArr) {
		double pointsInArea = 0;
		double shapeNumber = 0;
		double resultOfAreas = 0;
		double pointsToAdd = 0;
		String isIntersecting = "";

		for (int i = 0; i < circleArr.length; i++) {
			pointsToAdd = 0;
			pointsInArea = 0;
			shapeNumber++;
			for (int j = 0; j < NUMBER_OF_DARTS; j++) {
				Point checkerPoint = new Point(circleArr[i].getLowerBound(), circleArr[i].getUpperBound(), circleArr[i].getLeftBound(), circleArr[i].getRightBound());
					if (checkerPoint.isInCircle(circleArr[i])) {
						pointsInArea++;
					} 
				}
			resultOfAreas = resultOfAreas + (pointsInArea / NUMBER_OF_DARTS) * ((circleArr[i].getUpperBound()) - circleArr[i].getLowerBound()) * (circleArr[i].getRightBound() - circleArr[i].getLeftBound());
			System.out.println("Area of Circle " + shapeNumber + ": " + (pointsInArea / NUMBER_OF_DARTS) * ((circleArr[i].getUpperBound()) - circleArr[i].getLowerBound()) * (circleArr[i].getRightBound() - circleArr[i].getLeftBound()));
		}
		shapeNumber = 0;
		for (int i = 0; i < triArr.length; i++) {
			pointsInArea = 0;
			shapeNumber++;
			for (int j = 0; j < NUMBER_OF_DARTS; j++) {
				Point checkerPoint = new Point(triArr[i].getLowerBound(), triArr[i].getUpperBound(), triArr[i].getLeftBound(), triArr[i].getRightBound());
					if (checkerPoint.isInTriangle(triArr[i])) {
						pointsInArea++;
					} 
			}
			resultOfAreas = resultOfAreas + (pointsInArea / NUMBER_OF_DARTS) * ((triArr[i].getUpperBound()) - triArr[i].getLowerBound()) * (triArr[i].getRightBound() - triArr[i].getLeftBound());
			System.out.println("Area of Triangle " + shapeNumber + ": " + (pointsInArea / NUMBER_OF_DARTS) * ((triArr[i].getUpperBound()) - triArr[i].getLowerBound()) * (triArr[i].getRightBound() - triArr[i].getLeftBound()));
		}
		return resultOfAreas;
	}

	public static double getUnionArea(BoundingBox boundingBox, Circle[] circleArr, Triangle[] triArr) { //can be shortened a lot
		return getIndividualAreas(boundingBox, circleArr, triArr) - getIntersectArea(boundingBox, circleArr, triArr);
	}

	public static void getNonIntersectingAreas(BoundingBox boundingBox, Circle[] circleArr, Triangle[] triArr) {
		throw new UnsupportedOperationException();
	}
}