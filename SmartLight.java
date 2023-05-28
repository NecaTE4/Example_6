//Name: Necati
//Surname: Koçak
//Student ID: 150120053

import java.text.SimpleDateFormat;
import java.util.*;

public class SmartLight extends SmartObject implements LocationControl, Programmable {

	private boolean hasLightTurned;
	private Calendar programTime = new GregorianCalendar();
	private boolean programAction;
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat x = new SimpleDateFormat("HH:mm:ss");
	Date date = new Date();

	public SmartLight(String alias, String macId) {
		super.setAlias(alias);
		super.setMacId(macId);
	}

	public void turnOnLight() {
		programTime= new GregorianCalendar();
		if (this.hasLightTurned == false && isConnectionStatus() == true) {
			this.hasLightTurned = true;
			programAction = false;
			System.out.println(getClass().getName() + " - " + super.getAlias() + " is turned on now (Current time: "
					+ formatter.format(programTime.getTime())+")");
		}
		else if (this.hasLightTurned == true && super.isConnectionStatus() == true) {
			System.out.println(getClass().getName() + " - " + super.getAlias() + " has been already turned on");

		}
	}

	public void turnOffLight() {
		programTime= new GregorianCalendar();
		if (this.hasLightTurned == true && super.isConnectionStatus() == true) {
			this.hasLightTurned = false;
			programAction = true;
			System.out.println(getClass().getName() + " - " + super.getAlias() + " is turned off now (Current time: "
					+ formatter.format(programTime.getTime())+")");
		}
		else if (this.hasLightTurned == true && super.isConnectionStatus() == true) {
			System.out.println(getClass().getName() + " - " + super.getAlias() + " has been already turned off");

		}
	}

	@Override
	public void setTimer(int seconds) {
		programTime= new GregorianCalendar();
		if (super.isConnectionStatus()) {
			if (this.hasLightTurned) {
				System.out.println(getClass().getName() + " - " + super.getAlias() + " will be turned off " + seconds
						+ " seconds later!" + " (Current time: " + formatter.format(programTime.getTime())+")");

			} else {
				System.out.println(getClass().getName() + " - " + super.getAlias() + " will be turned on " + seconds
						+ " seconds later!" + " (Current time: " + formatter.format(programTime.getTime())+")");
			}
			programTime.set(Calendar.SECOND, Calendar.SECOND + 5);
		}
	}

	@Override
	public void cancelTimer() {
		if (super.isConnectionStatus() == true) {
			programTime = null;
		}
	}

	@Override
	public void runProgram() {
		programTime= new GregorianCalendar();
		if (isConnectionStatus()) {
			if (programTime.getTime().equals((date))) {
				if (isProgramAction() == false) {
					turnOffLight();
					System.out.println("RunProgram ->  Smart Light - " + getAlias());
					System.out.println(
							"Smart Light - " + getAlias() + "is turned off now (Current time: " + x.format(date) + ")");
				} else {
					turnOnLight();
					System.out.println("RunProgram -> " + getClass().getName() + " - " + getAlias());
					System.out.println(getClass().getName() + " - " + getAlias() + "is turned on now (Current time: "
							+ x.format(date) + ")");

				}
			}
			programTime = null;
		}
	}

	@Override
	public void onLeave() {
		if (this.hasLightTurned == true && super.isConnectionStatus() == true) {
			System.out.println("On Leave -> " + getClass().getName() + " - " + getAlias());
			turnOffLight();
		}
	}

	@Override
	public void onCome() {
		if (this.hasLightTurned == false && super.isConnectionStatus() == true) {
			System.out.println("On Come -> " + getClass().getName() + " - " + getAlias());
			turnOnLight();
		}
	}

	@Override
	public boolean testObject() {
		if (super.isConnectionStatus() == true) {
			System.out.println("Test is starting for " + getClass().getName());
			super.SmartObjectToString();
			turnOnLight();
			turnOffLight();
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
			if (this.hasLightTurned == true) {
				super.SmartObjectToString();
				turnOffLight();
				return true;
			}
		}
		return false;
	}

	public boolean isHasLightTurned() {
		if (this.hasLightTurned) {
			return true;
		} else {
			return false;
		}
	}

	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
	}

	public Calendar getProgramTime() {
		return programTime;
	}

	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	public boolean isProgramAction() {
		return programAction;
	}

	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}

}
