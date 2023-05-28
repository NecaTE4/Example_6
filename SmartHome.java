//Name: Necati
//Surname: Koçak
//Student ID: 150120053

import java.util.ArrayList;
import java.util.Arrays;
public class SmartHome {
	private ArrayList<SmartObject> smartObjectList = new ArrayList<SmartObject>();

	public SmartHome() {

	}

	public boolean addSmartObject(SmartObject smartObject) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Adding new SmartObject");
		System.out.println("--------------------------------------------------------------------------");

		smartObject.connect("10.0.0." + (100 + this.smartObjectList.size()));
		smartObject.testObject();
		return this.smartObjectList.add(smartObject);
	}

	public boolean removeSmartObject(SmartObject smartObject) {
		return this.smartObjectList.remove(smartObject);
	}

	public void controlLocation(boolean onCome) {
	String s;
		if (onCome) {
			s="OnCome";
		}else {
			s="OnLeave";
		}
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("LocationControl : "+s);
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (this.smartObjectList.get(i) instanceof LocationControl) {
				LocationControl xd = (LocationControl) smartObjectList.get(i);
				if (onCome) {
					xd.onCome();
				} else {
					xd.onLeave();
				}
			}
		}
	}

	public void controlMotion(boolean hasMotion, boolean isDay) {

		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("MotionControl: HasMotion, isDay");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (this.smartObjectList.get(i) instanceof MotionControl) {
				MotionControl lul = (MotionControl) smartObjectList.get(i);
				lul.controlMotion(hasMotion, isDay);
			}
		}
	}

	public void controlProgrammable() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: runProgram");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (this.smartObjectList.get(i) instanceof Programmable) {
				Programmable tsm = (Programmable) smartObjectList.get(i);
				tsm.runProgram();
			}
		}
	}

	public void controlTimer(int seconds) {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = " + seconds + " seconds");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			if (this.smartObjectList.get(i) instanceof Programmable) {
				Programmable thx = (Programmable) smartObjectList.get(i);
				if (seconds > 0) {
					thx.setTimer(seconds);
				} else {
					thx.cancelTimer();
				}

			}
		}
	}

	public void controlTimerRandomly() {
		
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = 5 or 10 seconds randomly");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 0; i < smartObjectList.size(); i++) {
			int a = (int) (Math.random() * 3);
			if (a == 0) {
				a = 0;
			} else if (a == 1) {
				a = 5;
			} else {
				a = 10;
			}
			if (this.smartObjectList.get(i) instanceof Programmable) {
				Programmable omg = (Programmable) smartObjectList.get(i);
				if (a == 5 || a == 10) {
					omg.setTimer(a);
				} else {
					omg.cancelTimer();
				}

			}
		}
	}

	public void sortCameras() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("--------------------------------------------------------------------------");
		
		   ArrayList<SmartCamera> cameras = new ArrayList<>();
		   for (SmartObject smartObject : smartObjectList) {
		   		if(smartObject instanceof Comparable) {
		   			cameras.add((SmartCamera) smartObject);
		   		}
		   }
		
		SmartCamera[] camerasArray = cameras.toArray(new SmartCamera[cameras.size()]);
		Arrays.sort(camerasArray);
		for (int i = 0;i< camerasArray.length;i++){
		System.out.println(camerasArray[i].toString());
		}
	}

	public ArrayList<SmartObject> getSmartObject() {
		return smartObjectList;
	}

	public void setSmartObject(ArrayList<SmartObject> smartObject) {
		this.smartObjectList = smartObject;
	}

}
