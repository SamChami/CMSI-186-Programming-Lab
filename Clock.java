class Clock {
		private double slice;
		private double hours;
		private double minutes;
		private double seconds;
	public Clock (double slice) {
		this.slice = slice;
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}

	public double hours() {
		return this.hours;
	}

	public double minutes() {
		return this.minutes;
	}

	public double seconds () {
		return this.seconds;
	}

	public void tick () {
		this.seconds+= slice;
		while (this.seconds >= 60) {
			this.minutes++;
			this.seconds-= 60;
		}

		while (this.minutes >= 60) {
			this.hours++;
			this.minutes = 0;
		}
		while (this.hours >= 12) {
			this.hours = 0;
		}
	}

	public String toString () {
		return (int)this.hours + ":" + (int)this.minutes + ":" + this.seconds;
	}

	public double getMinuteAngle() {
		return this.minutes * 6 + this.seconds * 0.1;
	}

	public double getHourAngle() {
		return this.seconds * (0.5 / 60) + (this.minutes / 2) + this.hours * 30;
	}
}