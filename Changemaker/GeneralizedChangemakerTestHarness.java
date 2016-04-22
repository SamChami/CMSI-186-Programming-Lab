public class GeneralizedChangemakerTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_USA();
        test_oddNumbers();
        test_evenNumbers();
        test_evenNumbers2();
        test_largeNumbers();
        test_smallNumbers();
        test_manyAnswers();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_USA() {
        int[] usaDenominations = new int[] { 25, 10, 5, 1 };
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 99);
        System.out.println("USA Currency");
        try {
            displaySuccessIfTrue(3 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(4 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_oddNumbers() {
        int[] usaDenominations = new int[] { 13, 11, 9, 7, 5, 3 };
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 33);
        System.out.println("Odd Number Test");
        try {
            displaySuccessIfTrue(2 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(1 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_evenNumbers() {
        int[] usaDenominations = new int[] { 44, 18, 4, 22, 16, 40 };
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 200);
        System.out.println("Even Number Test");

        try {
            displaySuccessIfTrue(5 == result.getElement(5));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_evenNumbers2() {
        int[] usaDenominations = new int[] { 44, 18, 100, 22, 16, 40 };
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 200);
        System.out.println("Even (the same) Number Test");
        try {
            displaySuccessIfTrue(2 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }
    public static void test_smallNumbers() {
        int[] usaDenominations = new int[] { 3, 5, 4, 6, 1};
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 2);
        System.out.println("Small Number Test");
        try {
            displaySuccessIfTrue(0 == result.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
        try {
            displaySuccessIfTrue(2 == result.getElement(4));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }
    public static void test_largeNumbers() {
        int[] usaDenominations = new int[] { 31, 21, 32, 9};
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 30);
        System.out.println("Large Number Test");
        try {
            displaySuccessIfTrue(1 == result.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
        try {
            displaySuccessIfTrue(1 == result.getElement(3));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_manyAnswers() {
        int[] usaDenominations = new int[] { 1, 4, 3};
        Tuple result = GeneralizedChangemaker.makeChangeWithDynamicProgramming(usaDenominations, 9);
        System.out.println("More than One Combo Test");
        try {
            displaySuccessIfTrue(3 == result.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}