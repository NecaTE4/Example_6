//Name: Necati
//Surname: Koçak
//Student ID: 150120053

abstract class SmartObject {
	private String alias;
	private String macId;
	private String IP;
	private boolean connectionStatus;

	public SmartObject() {

	}

	public boolean connect(String IP) {
		this.IP=IP; 
			setConnectionStatus(true);
			System.out.println(getAlias() + " connection established");
			return true;
		
		
	}

	public boolean disconnect() { 
		setConnectionStatus(false);

		return isConnectionStatus();
	}

	public void SmartObjectToString() {
		System.out.println("This is " + getClass().getName() + " device " + getAlias());
		System.out.println("\tMacId: " + getMacId());
		System.out.println("\tIP: " + getIP());
	}

	public boolean controlConnection() {
		if (!connectionStatus) {
			System.out.println("This device is not connected. " + getClass().getName() + " -> " + getAlias());
			return false;
		}
		return true;
	}

	public abstract boolean testObject();

	public abstract boolean shutDownObject();

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean isConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

}
