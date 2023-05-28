//Name: Necati
//Surname: Koçak
//Student ID: 150120053

import java.text.SimpleDateFormat;
import java.util.*;

public class SmartPlug extends SmartObject implements Programmable {

	private boolean status;
	private boolean programAction;
	private Calendar programTime = new GregorianCalendar();
	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat x = new SimpleDateFormat("HH:mm:ss");
	Date date = new Date();

	public SmartPlug(String alias, String macId) {
		super.setAlias(alias);
		super.setMacId(macId);
	}

	public void turnOn() {
		programTime= new GregorianCalendar();
		if (isStatus() == false && isConnectionStatus() == true) {
			this.status = true;
			programAction = false;
			System.out.println(getClass().getName() + " - " + super.getAlias() + " is turned on now (Current time: "
					+ formatter.format(programTime.getTime())+")");
		}
		else if (isStatus() == true && super.isConnectionStatus() == true) {
			System.out.println(getClass().getName() + " - " + super.getAlias() + " has been already turned on");

		}
	}

	public void turnOff() {
		programTime= new GregorianCalendar();
		if (isStatus() == true && super.isConnectionStatus() == true) {
			this.status = false;
			programAction = true;
			System.out.println(getClass().getName() + " - " + super.getAlias() + " is turned off now (Current time: "
					+ formatter.format(programTime.getTime())+")");
		}
		else if (isStatus() == false && super.isConnectionStatus() == true) {
			System.out.println(getClass().getName() + " - " + super.getAlias() + " has been already turned off");

		}
	}

	@Override
	public boolean testObject() {
		if (super.isConnectionStatus() == true) {
			System.out.println("Test is starting for " + getClass().getName());
			super.SmartObjectToString();
			System.out.println("Test is starting for " + getClass().getName());
			turnOn();
			turnOff();
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
			if (isStatus() == true) {
				super.SmartObjectToString();
				turnOff();
				return true;
			}
		}
		return false;
	}

	@Override
	public void setTimer(int seconds) {
		programTime= new GregorianCalendar();
		if (super.isConnectionStatus()) {
			if (isStatus()) {
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
				if (programAction = false) {
					turnOff();
					System.out.println("RunProgram ->  Smart Light - " + getAlias());
					System.out.println(
							"Smart Light - " + getAlias() + "is turned off now (Current time: " + x.format(date) + ")");
				} else {
					turnOn();
					System.out.println("RunProgram -> " + getClass().getName() + " - " + getAlias());
					System.out.println(getClass().getName() + " - " + getAlias() + "is turned on now (Current time: "
							+ x.format(date) + ")");

				}
			}
			programTime = null;
		}
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
