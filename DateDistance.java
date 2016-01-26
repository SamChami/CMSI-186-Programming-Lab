public class DateDistance {
	public static void main(String[] args) {
		Long month1 = Long.parseLong(args[0]);
		Long day1 = Long.parseLong(args[1]);
		Long year1 = Long.parseLong(args[2]);

		Long month2 = Long.parseLong(args[3]);
		Long day2 = Long.parseLong(args[4]);
		Long year2 = Long.parseLong(args[5]);

		System.out.println(distance(month1, day1, year1, month2, day2, year2));
	}

	public static long distance ( long month0, long day0, long year0, long month1, long day1, long year1 ) {
		long totalDays0 = day0;
		long totalDays1 = day1;

		for (long i=1; i < year0; i++) {
			if (isCommonYear(i)) {
				totalDays0 += 365;
			} else {
				totalDays0 += 366;
			}
		}
		for (long j=1; j < year1; j++) {
			if (isCommonYear(j)) {
				totalDays1 += 365;
			} else {
				totalDays1 += 366;
			}
		} 	

		for (long i = 0; i < month0; i++) {
			totalDays0 += monthLength(i, year0);	
			}
		for (long i = 0; i < month1; i++) {
			totalDays1 += monthLength(i, year1);	
			}

		return Math.abs(totalDays0 - totalDays1);
	}

	public static boolean isRealDate(long month, long day, long year) {
		return (month <= 12 && month >= 0 && year > 0 && day <= monthLength(month, year) && day > 0); 
	}
			
	public static long monthLength ( long month, long year ) {
		long length =0;
	    if (month == 2 && isCommonYear(year)) {
			length = 28;
		} else {
			if ((month == 4 || month == 6 || month == 9 || month == 11)) {
				length = 30;
			} else if (month != 2 && month <=12) {
				length = 31;
			} else if (month == 2 && isCommonYear(year) == false) {
				length = 29;
			} else {
				length = 0;
			}
		}
		return length;
	}

	public static boolean isCommonYear(Long year) {
		 return (year % 4 != 0 || year % 100 == 0) && (year % 400 != 0);
		}
}