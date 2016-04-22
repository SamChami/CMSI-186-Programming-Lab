public class GeneralizedChangemaker {

    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            return;
        }

        try {
            int amount = Integer.parseInt(args[0]);
            if (amount < 0) {
                System.out.println("Change cannot be made for negative amounts.");
                System.out.println();
                printUsage();
                return;
            }

            String[] denominationStrings = args[1].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("Denominations must all be greater than zero.");
                    System.out.println();
                    printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("Duplicate denominations are not allowed.");
                        System.out.println();
                        printUsage();
                        return;
                    }
                }
            }

            Tuple change = makeChangeWithDynamicProgramming(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("It is impossible to make " + amount + " cents with those denominations.");
            } else {
                int coinTotal = change.total();
                System.out.println(amount + " cents can be made with " + coinTotal + " coin" +
                        getSimplePluralSuffix(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            getSimplePluralSuffix(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.");
            System.out.println();
            printUsage();
        }
    }

    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {
    	int numOfDenoms = denominations.length;
    	Tuple [][] tuples = new Tuple[numOfDenoms][amount + 1];

    	for (int i = 0; i < numOfDenoms; i++) {
    		tuples[i][0] = new Tuple(numOfDenoms);  
    	}

        for (int row = 0; row < numOfDenoms; row++) {
            for (int col = 1; col < amount + 1; col++) {
                tuples[row][col] = new Tuple(numOfDenoms);      
                if (col >= denominations[row]) {
                    tuples[row][col].setElement(row, 1); 
                    if (tuples[row][col - denominations[row]].isImpossible()) {
                        tuples[row][col] = Tuple.IMPOSSIBLE;
                    } else {
                        tuples[row][col] = tuples[row][col].add(tuples[row][col - denominations[row]]);
                    }
                } else {
                    tuples[row][col] = Tuple.IMPOSSIBLE;
                }
            if (row != 0) {
                if ((tuples[row][col].total() > tuples[row - 1][col].total() && tuples[row - 1][col].total() != 0) || (tuples[row][col].total() == 0 || tuples[row][col].isImpossible())) {
                    tuples[row][col] = tuples[row - 1][col];
                }
            }
        }   
    }
    return tuples[numOfDenoms - 1][amount];
}

    private static void dump(Tuple [][] tuples) {

    	for (int i = 0; i < tuples.length; i++) {
    		for (int j = 0; j < tuples.length; j++) {
    			System.out.println(tuples[i][j] + " ");
    	}
    	System.out.println();
    }
    System.out.println();
}

    private static void printUsage() {
        System.out.println("Usage: java GeneralizedChangemaker <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "s";
    }

}