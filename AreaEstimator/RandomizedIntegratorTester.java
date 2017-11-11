public class RandomizedIntegratorTester {
    public static void main(String[] args) {
    	testConstructor();
    	testNaturalLog();
    	testExponent();
    	testSin();
    	testCos();
		testSqrt();
		testPolynomial();
    }

    static void testConstructor() {
    	System.out.println("---------------------CONSTRUCTOR TESTS---------------------");
    	try {
	    	String args[] = {"sqrt", "-1.5", "284"};
	    	RandomizedIntegrator.main(args);
	    	System.out.println("Works if result is close to 3190.7");
    	} 
    	catch (Exception e) {
    		System.out.println("Not switching negative value to zero.");
    	}
    	try {
	    	String args[] = {"log", "1.5e", "284"};
	    	RandomizedIntegrator.main(args);
	    	System.out.println("Allowed letter to be passed through class");
    	} 
    	catch (Exception e) {
    		System.out.println("Working without issue");
    	}
    }

    static void testNaturalLog() {
    	System.out.println("---------------------NATURAL LOG TESTS---------------------");
    	try {
	    	String args[] = {"log", "1.5", "284"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "1321.2";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of log(x) from 1.5 to 284.");
    	}
    	try {
	    	String args[] = {"log", "3", "10"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "12.73";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of log(x) from 3 to 10.");
    	}
    }
    static void testExponent() {
    	System.out.println("---------------------EXPONENT TESTS---------------------");
    	try {
	    	String args[] = {"exp", "1.5", "28"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "1.44626 x 10^12";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of e^x from 1.5 to 284.");
    	}
    	try {
	    	String args[] = {"exp", "3", "10"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "22006";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of e^x from 3 to 10.");
    	}
    	try {
	    	String args[] = {"exp", "-3", "3"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "20.036";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of e^x from -3 to 3.");
    	}
    }
    static void testSin() {
    	System.out.println("---------------------SIN TESTS---------------------");
    	try {
    		String args[] = {"sin", "1.5", "28"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "1.03334";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of sin(x) from 1.5 to 28.");
    	}
    	try {
	    	String args[] = {"sin", "3", "10"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "-0.15092";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of sin(x) from 3 to 10.");
    	}
    	try {
	    	String args[] = {"sin", "-100", "100"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "0";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of sin(x) from -100 to 100.");
    	}
    }
    static void testCos() {
    	System.out.println("---------------------COS TESTS---------------------");
    	try {
	    	String args[] = {"cos", "1.5", "28"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "-0.726589";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of cos(x) from 1.5 to 284.");
    	}
    	try {
	    	String args[] = {"cos", "3", "10"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "-0.685";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of cos(x) from 3 to 10.");
    	}
    	try {
	    	String args[] = {"cos", "-100", "100"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "-1.0127";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of cos(x) from -100 to 100.");
    	}
    }
    static void testSqrt() {
    	System.out.println("---------------------SQUARE ROOT TESTS---------------------");
    	try {
	    	String args[] = {"sqrt", "1.5", "284"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "3189.48";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of sqrt(x) from 1.5 to 284.");
    	}
    	try {
	    	String args[] = {"sqrt", "3", "10"};
	    	System.out.println("	" + args[0] + " from " + args[1] + " to " + args[2] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "17.618";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of sqrt(x) from 3 to 10.");
    	}
    }
    static void testPolynomial() {
    	System.out.println("---------------------POLYNOMIAL TESTS---------------------");
    	try {
	    	String args[] = {"poly", "1.5", "28", "283", "32", "1", "2"};
	    	System.out.println("	" + args[0] + " from " + args[args.length - 2] + " to " + args[args.length - 1] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "823.83";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of a polynomial.");
    	}
    	try {
	    	String args[] = {"poly", "3", "10", "-2", "-2", "0"};
	    	System.out.println("	" + args[0] + " from " + args[args.length - 2] + " to " + args[args.length - 1] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "-19.333";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of a polynomial.");
    	}
    	try {
	    	String args[] = {"poly", "-100", "1", "3"};
	    	System.out.println("	" + args[0] + " from " + args[args.length - 2] + " to " + args[args.length - 1] + ": ");
	    	RandomizedIntegrator.main(args);
	    	String actual = "-200";
	    	System.out.println("Actual Answer: " + actual);
    	} 
    	catch (Exception e) {
    		System.out.println("Exception thrown from Integral of  a polynomial.");
    	}
    }
} 