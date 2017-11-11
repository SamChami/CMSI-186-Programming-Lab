public class RandomizedIntegrator {
	public static double testerResult;
    public static void main(String[] args) {
 	    try {
    		for (int i = 1; i < args.length; i++) {
    			Double.parseDouble(args[i]);
    		}
    	} catch (Exception e) {
      		System.err.println("Incorrect number input. Please enter in this form:   java RandomizedIntegrator functionName additionalDescriptors lowerBound upperBound");
    	}
  		try {
    		if (!(args[0].equals("sin") ||args[0].equals("cos") || args[0].equals("log") || args[0].equals("exp") || args[0].equals("poly") ||args[0].equals("sqrt"))) {
    			throw new Exception();   		
    		}
    	} catch (Exception e) {
    		System.err.println("Incorrect function name");
    	} 
    	try {
    		if ((args[0].equals("log") || args[0].equals("sqrt")) && (Double.parseDouble(args[1]) < 0 || Double.parseDouble(args[2]) < 0)) {
    			throw new Exception();   		
    		}
    	} catch (Exception e) {
    		System.err.println("Nagative Value Not Allowed. Assuming negative values are 0");
    	} 

    	double NUMBER_OF_DARTS = 1000000;
    	String functionType = args[0];
    	double lowestBound = 0;
    	double highestBound = 0;
    	double leftBound = Double.parseDouble(args[args.length - 2]);
    	double rightBound = Double.parseDouble(args[args.length - 1]);
    	double[] coefficients = new double[args.length - 1];

    	for (int i = 1; i < args.length; i++) {
    		coefficients[i - 1] = Double.parseDouble(args[i]);
    	}

    	MathFunctions function = new MathFunctions();
    	MathOperation graphType = function.sin;

    	if (args[0].equals("sin")) {
    		graphType = function.sin;
    		lowestBound = -1;
    		highestBound = 1;
    	} else if (args[0].equals("log")) {
    		graphType = function.log;
    		highestBound = Math.log(rightBound);
    		lowestBound = 0;
    	} else if (args[0].equals("cos")) {
    		graphType = function.cos;
    		lowestBound = -1;
    		highestBound = 1;
    	} else if (args[0].equals("exp")) { 
    		graphType = function.exp;
    		lowestBound = 0;
    		highestBound = Math.exp(rightBound);
    	}  else if (args[0].equals("sqrt")) {
    		graphType = function.sqrt;
    		lowestBound = 0;
    		highestBound = Math.sqrt(rightBound);
    	} else if (args[0].equals("poly")) {
    		graphType = function.poly;
    		for (double i = leftBound; i < rightBound; i = i + 0.0005) {
    			if (graphType.eval(i, coefficients) < lowestBound) {
    				lowestBound = graphType.eval(i, coefficients);
    			} else if (graphType.eval(i, coefficients) > highestBound) {
    				highestBound = graphType.eval(i, coefficients);
    			}
    		}
    	}
    	
    	double areaPoint = 0;
    	for (int i = 0; i < NUMBER_OF_DARTS; i++) {	
    		Point checkerPoint = new Point(lowestBound, highestBound, leftBound, rightBound);
    		if (checkerPoint.getY() <= graphType.eval(checkerPoint.getX(), coefficients) && graphType.eval(checkerPoint.getX(), coefficients) > 0 && checkerPoint.getY() > 0) {
    			areaPoint = areaPoint + 1;
    		} else if (checkerPoint.getY() >= graphType.eval(checkerPoint.getX(), coefficients) && graphType.eval(checkerPoint.getX(), coefficients) < 0 && checkerPoint.getY() < 0) {
    			areaPoint = areaPoint - 1;
    		} 
    	}
    	testerResult = (areaPoint / NUMBER_OF_DARTS) * (highestBound - lowestBound) * (rightBound - leftBound);
    	System.out.println("Approximate Area Under the Curve: " + (areaPoint / NUMBER_OF_DARTS) * (highestBound - lowestBound) * (rightBound - leftBound));
    }
}