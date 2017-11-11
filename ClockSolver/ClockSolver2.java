class ClockSolver2 {
	public static void main(String[] args) {
		double slice = 60;
		double angle = 180;
		try {
			slice = Double.parseDouble(args[0]);
			angle = Double.parseDouble(args[1]);
			if (angle <= 0 || angle >= 360) {
				System.err.println("Angle must be between 0 and 360.");
			}
			if (slice < 0 || slice >= 1800) {
				System.err.println("Slice must be positive and below 1800.");
				angle = 0;
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
			if ((Math.abs(c.getMinuteAngle() - c.getHourAngle()) >= angle - sliceFactor  && Math.abs(c.getMinuteAngle() - c.getHourAngle()) <= angle + sliceFactor) || 360 - (Math.abs(c.getMinuteAngle() - c.getHourAngle())) >= angle - sliceFactor  && 360 - (Math.abs(c.getMinuteAngle() - c.getHourAngle())) <= angle + sliceFactor) {
				if (highestAngle != 0) {
					System.out.println(highestTime);
					otherAngle = 0;
					highestAngle = 0;
				}

				otherTime = highestTime;
				otherAngle = highestAngle;

				highestAngle = Math.abs(c.getMinuteAngle() - c.getHourAngle());
				highestTime = c.toString();

				if (Math.abs(angle - otherAngle) < Math.abs(angle - highestAngle)) {
					highestTime = otherTime;
					highestAngle = otherAngle;
				}
			} 
			c.tick();	
		} 
		System.out.println(highestTime);
	}
}