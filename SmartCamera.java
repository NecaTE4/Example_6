//Name: Necati
//Surname: Koçak
//Student ID: 150120053

public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {

	private boolean status;
	private int batteryLife;
	private boolean nightVision;

	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		super.setAlias(alias);
		super.setMacId(macId);
		this.nightVision = nightVision;
		this.batteryLife = batteryLife;

	}

	public void recordOn(boolean isDay) {
		if (status == false && isConnectionStatus() == true) {
			status = true;
			System.out.println(getClass().getName() + " - " + super.getAlias() + " is turned on now ");
		}
		else if (status == true && isConnectionStatus() == true) {
			System.out.println(getClass().getName() + " - " + super.getAlias() + " has been already turned on");
		}
		else if (isDay == false && isNightVision() == false && (status == true || status == false)
				&& isConnectionStatus() == true) {
			System.out.println(
					"Sorry! " + getClass().getName() + " - " + getAlias() + " does not have night vision feature.");
		}
	}

	public void recordOff() {
		if (status == true && isConnectionStatus() == true) {
			status = false;
			System.out.println(getClass().getName() + " - " + super.getAlias() + " is turned off now ");
		}
		else if (status == false && isConnectionStatus() == true) {
			System.out.println(getClass().getName() + " - " + super.getAlias() + " has been already turned off");

		}
	}

	@Override
	public boolean testObject() {
		if (super.isConnectionStatus() == true) {
			System.out.println("Test is starting for " + getClass().getName());
			super.SmartObjectToString();
			System.out.println("Test is starting for " + getClass().getName() + " day time");
			recordOn(true);
			recordOff();
			System.out.println("Test is starting for " + getClass().getName() + " night time");
			recordOn(false);
			recordOff();
			System.out.println("Test completed for " + getClass().getName());
			System.out.println();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean shutDownObject() {
		if (super.isConnectionStatus() == true) {
			if (status == true) {
				super.SmartObjectToString();
				recordOff();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		if (hasMotion == false) {
			System.out.println("Motion not detected!");
			return false;
		} else {
			System.out.println("Motion detected!");
			recordOn(isDay);
			return true;
		}

	}

	@Override
	public int compareTo(SmartCamera smartCamera) {
		if (batteryLife > smartCamera.getBatteryLife()) {
			return 1;
		} else if (batteryLife == smartCamera.getBatteryLife()) {
			return 0;
		} else {
			return -1;
		}
	}

	public String toString() {
		String asd = getClass().getName() + " -> " + getAlias() + "'s battery life is " + getBatteryLife()
				+ " status is recording";
		return asd;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	public boolean isNightVision() {
		return nightVision;
	}

	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}

}
