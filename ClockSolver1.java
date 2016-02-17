class ClockSolver1 { 
	public static void main(String[] args) {
		double slice = 60;
		try {
			slice = Double.parseDouble(args[0]);
			if (slice < 0) {
				System.err.println("Slice must be positive.");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("No Arguments given");
		} catch (Exception e) {
			System.err.println(e);
		}

		Clock c = new Clock(slice);

		double sliceFactor = 5.5 / 60 * slice / 2;
		double highestAngle = 0;
		String highestTime = "";
		String otherTime = "";
		double otherAngle = 0;

		for (int i = 0; i < 43200 / slice; i++) {

			if (Math.abs(c.getMinuteAngle() - c.getHourAngle()) >= 180 - sliceFactor  && Math.abs(c.getMinuteAngle() - c.getHourAngle()) <= 180 + sliceFactor) {
				if (highestAngle != 0) {
					System.out.println(highestTime);
					otherAngle = 0;
					highestAngle = 0;
				}

				otherTime = highestTime;
				otherAngle = highestAngle;

				highestAngle = Math.abs(c.getMinuteAngle() - c.getHourAngle());
				highestTime = c.toString();

				if (Math.abs(180 - otherAngle) < Math.abs(180 - highestAngle)) {
					highestTime = otherTime;
					highestAngle = otherAngle;
				}
			} 
				c.tick();	
		} 
		System.out.println(highestTime);
	}
}