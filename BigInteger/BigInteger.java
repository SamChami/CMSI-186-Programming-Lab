public class BigInteger {
	private String digits;

	public BigInteger (String number) {
		this.digits = number;

		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				if (i == 0 && number.substring(i, i+1).equals("-")) {
					continue;
				} else {
					throw new UnsupportedOperationException();
				}
			}
		}
	}

	public static final BigInteger ZERO = new BigInteger("0"); // a classwide constant for zero
	public static final BigInteger ONE = new BigInteger("1");  // a classwide constant for one
	public static final BigInteger TEN = new BigInteger("10"); // a classwide constant for ten

	public BigInteger add (BigInteger val) {
		String value = this.toUsableNumber(val);
		String input = val.toUsableNumber(this);
		String result = "";
		int carryOne = 0;
		int temporaryCheck = 0;

		if ((value.substring(0,1).equals("-") && !input.substring(0,1).equals("-")) || (!value.substring(0,1).equals("-") && input.substring(0,1).equals("-"))) {
			if (input.substring(0,1).equals("-")) {
				input = input.substring(1);
			}
			if (value.substring(0,1).equals("-")) {
				value = value.substring(1);
			}
    		BigInteger revisedInput = new BigInteger(input);
    		BigInteger revisedValue = new BigInteger(value);
			result = revisedInput.subtract(revisedValue).toString();
		} else {
			for (int i = value.length(); i > 0; i--) {
				if (value.substring(i - 1,i).equals("-") && input.substring(i - 1,i).equals("-")) {
					result = "-" + result;
					break;
				}

			 	temporaryCheck = Integer.parseInt(value.substring(i - 1,i)) + Integer.parseInt(input.substring(i - 1,i)) + carryOne;
			 	carryOne = 0;
				if (temporaryCheck >= 10 && i != 1) {
					carryOne = 1;
					temporaryCheck = temporaryCheck - 10;
				}
				result = Integer.toString(temporaryCheck) + result;	
			}
		}
		return new BigInteger(result).removeZeros();
	}

	public BigInteger subtract ( BigInteger val ) {
		String input =  val.toUsableNumber(this).substring(this.toString().indexOf("-") + 1);
		String value =  this.toUsableNumber(val).substring(val.toString().indexOf("-") + 1);
		String negative = "";
		String result = "";
		int number1 = 0;
		int number2 = 0;
		int borrow = 0;
		int borrowedMultiple = 0;
		int temporaryCheck = 0;

		if (new BigInteger(input).compareTo(new BigInteger(value)) == -1) {
				input =  this.toUsableNumber(val).substring(val.toString().indexOf("-") + 1);
				value =  val.toUsableNumber(this).substring(this.toString().indexOf("-") + 1);
		}

		if (this.compareTo(val) == -1) {
			negative = "-";
		}

		if ((this.toString().substring(0,1).equals("-") && !val.toString().substring(0,1).equals("-")) || (!this.toString().substring(0,1).equals("-") && val.toString().substring(0,1).equals("-"))) {
    		BigInteger revisedInput = new BigInteger(input);
    		BigInteger revisedValue = new BigInteger(value);
			result = revisedInput.add(revisedValue).toString();
		} else {
			for (int i = input.length(); i > 0; i--) {
				number1 = Integer.parseInt(value.substring(i - 1,i));
				number2 = Integer.parseInt(input.substring(i - 1,i));
				number2 = number2 - borrow;
				borrow = 0;
				if (number2 < number1) {
					borrowedMultiple = 10;
				}
			 	temporaryCheck = (borrowedMultiple + (number2 - borrow)) - number1;
			 	if (number2 < number1) {
			 		borrow = 1;
			 	}
			 	borrowedMultiple = 0;
				result = Integer.toString(temporaryCheck) + result;
			}
		}
		return new BigInteger(negative + result).removeZeros();
	}

	public BigInteger multiply ( BigInteger val ) {
		BigInteger value =  new BigInteger(val.toString().substring(val.toString().indexOf("-") + 1));
		BigInteger result = ZERO;
		Integer temporaryQuotient = 0;
		Integer index = 0;
		String negative = "";
		String input =  this.toString().substring(this.toString().indexOf("-") + 1);
		String divisionResult = "";
		String temporaryCarryNumber = input.substring(0,1);

		if ((val.toString().substring(0,1).equals("-") && !this.toString().substring(0,1).equals("-")) || (!val.toString().substring(0,1).equals("-") && this.toString().substring(0,1).equals("-")) && !input.equals("0")) {
			negative = "-";
		}

		if (Integer.parseInt(input.substring(input.length() - 1)) % 2 != 0) {
				result = value;				
		}

		while (!input.equals("1")) {
			value = value.add(value);
			while(index < input.length()) { 
				temporaryQuotient = Integer.parseInt(temporaryCarryNumber) / 2;
				divisionResult = divisionResult + Integer.toString(temporaryQuotient); 
				index += 1;
				if (input.length() == index) {
					temporaryCarryNumber = Integer.toString( Integer.parseInt(temporaryCarryNumber) - temporaryQuotient * 2 ) + input.substring(index);
				} else {
					temporaryCarryNumber = Integer.toString( Integer.parseInt(temporaryCarryNumber) - temporaryQuotient * 2 ) + input.substring(index, index + 1);	}	
			} 
			index = 0; 
			input = divisionResult;

			while (input.substring(0,1).equals("0") && input.length() != 1) {
				input = input.substring(1);
			}
			divisionResult = "";
			temporaryCarryNumber = input.substring(0,1);

			if (Integer.parseInt(input.substring(input.length() - 1)) % 2 != 0) {
				result = result.add(value);				
			}
		}
		return new BigInteger(negative + result.toString());
	} 

	public BigInteger divide ( BigInteger val ) {
		throw new UnsupportedOperationException();
	}

	public BigInteger remainder ( BigInteger val ) {
		throw new UnsupportedOperationException();
	} 

	public String toString () {
		return this.digits;
	} 		

	public BigInteger removeZeros () {
		String result = this.toString();
		while(result.substring(0,1).equals("0") && result.length() != 1) {
			result = result.substring(1);		
		}		
		while (result.substring(0,1).equals("-") && result.substring(1,2).equals("0")) {
				result = "-" + result.substring(2);
		}
		return new BigInteger(result);
	}																							

	public String toUsableNumber (BigInteger val) {
		String value = val.toString();
		String thisInput = this.toString();
		if (value.length() == 1 && thisInput.length() == 1) {
			value = 0 + value;
			thisInput = 0 + thisInput;
		}
		while(value.length() < thisInput.length()) {
			if (value.substring(0,1).equals("-")) {
				value = "-0" + value.substring(1);
			} else {
				value = "0" + value;
			}
		}
		return value;
	}

	public int compareTo ( BigInteger val ) {
		String value = this.toUsableNumber(val);
		String input = val.toUsableNumber(this);
		int result = 0;
		if (input.equals(value)) {
			result = 0;			
		} else {
			for (int i = 0; i < value.length(); i++) {
				if (Integer.parseInt(input.substring(i, i+2)) < Integer.parseInt(value.substring(i, i+2))) {
					result = -1;
					break;
				} else if (Integer.parseInt(input.substring(i, i+2)) > Integer.parseInt(value.substring(i, i+2))) {
					result = 1;
					break;
				}
			}
		}	
		return result;
	} 	

	public boolean equals (Object x) {
		String value = x.toString();
		String input = this.toString();

		while (value.substring(0, 1).equals("0")) {
			if (value.length() == 1) {
				break;
			}
			value = value.substring(1);
		}

		while (input.substring(0, 1).equals("0")) {
			if (input.length() == 1) {
				break;
			}
			input = input.substring(1);
		}

		if (input.equals(value)) {
			return true;
		}	else {
			return false;
		}
	} 

	public static BigInteger valueOf ( long val ) {
		if (val == 0) {
			return ZERO;
		} else if (val == 1) {
			return ONE;
		} else if (val == 10) {
			return TEN;
		} else {
			return new BigInteger(Long.toString(val));
		}
	}
}