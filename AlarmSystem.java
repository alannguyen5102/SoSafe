/**
 * 
 */
package sosafesystems;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * @author alannguyen
 *
 */
public class AlarmSystem implements Observer{

	private ArrayList<MotionSensor> motionSensors;
	private ArrayList<TemperatureSensor> temperatureSensors;
	private IntruderBilling billingIntrusion;
	private FireBilling billingFire;
	private Boolean fireAlert;
	private Boolean intruderAlert;
	private LocalTime currentTime;
	private LocalTime fromTime = LocalTime.MIN;
	private LocalTime toTime = LocalTime.MAX;

	

	


	/**
	 * 
	 */
	public AlarmSystem() {
		
	}
	
	public AlarmSystem(String fileName, String userFile, String id) throws IOException {
		motionSensors = new ArrayList<MotionSensor>();
		temperatureSensors = new ArrayList<TemperatureSensor>();
		loadUserFromFile(userFile, id);
		loadSensorsFromFile(fileName);
		System.out.print("How many motions: "+ motionSensors.size());
		System.out.print("How many temps: "+ temperatureSensors.size());
		
		
		
		
	}
	/**
	 * @return the fromTime
	 */
	public LocalTime getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(String fromTime) {
		this.fromTime = LocalTime.parse(fromTime);
	}

	/**
	 * @return the toTime
	 */
	public LocalTime getToTime() {
		return toTime;
	}

	/**
	 * @param toTime the toTime to set
	 */
	public void setToTime(String toTime) {
		this.toTime = LocalTime.parse(toTime);
	}

	/**
	 * @return the motionSensors
	 */
	public ArrayList<MotionSensor> getMotionSensors() {
		return motionSensors;
	}

	/**
	 * @return the temperatureSensors
	 */
	public ArrayList<TemperatureSensor> getTemperatureSensors() {
		return temperatureSensors;
	}

	/**
	 * @return the billingIntrusion
	 */
	public IntruderBilling getBillingIntrusion() {
		return billingIntrusion;
	}

	/**
	 * @return the billingFire
	 */
	public FireBilling getBillingFire() {
		return billingFire;
	}
	
	public Boolean checkTemperatureSensors (String location) {
		currentTime = LocalTime.now();
		for(TemperatureSensor elem : temperatureSensors){
			if (elem.getLocation().equals(location) ) {
				if (elem.tripSensor(fromTime, toTime)) {
					return true;
				}
				
			}
		}
		return false;
	
	}
	public Boolean checkMotionSensors (String location) {
		for(MotionSensor elem : motionSensors){
			if (elem.getLocation().equals(location) ) {
				if (elem.tripSensor(fromTime, toTime)) {
					return true;
				}
			}
		}
		return false;
	
	}
	
	
	public void loadUserFromFile(String fileName, String id) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
        // Loop as long as there are input lines.
        String line = null;
        while ((line=reader.readLine()) != null) {
        	String [] tokens = line.split("\\*");
        	if (tokens[0].equals(id)) {
        		addUser(line);
        	}
        	
        }
        reader.close();
	}
	public void loadSensorsFromFile(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
        // Loop as long as there are input lines.
        String line = null;
        while ((line=reader.readLine()) != null) {
        	addSensors(line);
        }
        reader.close();
	}
	public void addUser(String line) {
		String [] tokens = line.split("\\*");
		Integer id = Integer.parseInt(tokens[0]);
		String name = tokens[1];
		String phone = tokens[3];
		String address = tokens[4];
		String service = tokens[5];
		String contact1 = tokens[6];
		String contact2 = tokens[7];
		ArrayList<String> contacts = new ArrayList<String>();
		contacts.add(contact1);
		contacts.add(contact2);
		if ("B".equals(tokens[5]) || "M".equals(tokens[5]) ) {
			
			billingIntrusion = new IntruderBilling(id, name, address, contacts, phone);
		}
		if ("B".equals(tokens[5]) || "F".equals(tokens[5])) {
			billingFire = new FireBilling(id, name, address, contacts, phone);
		}
	}
	public void addSensors(String line) {
		String [] tokens = line.split("\\*");
		int sensorId = Integer.parseInt(tokens[2]);
		String location = tokens[3];
		Boolean powerStatus = Boolean.valueOf(tokens[4]);
		Boolean manualStatus = Boolean.valueOf(tokens[5]);
		Boolean alarmStatus = Boolean.valueOf(tokens[6]);
		String fromTime = tokens[7];
		String toTime = tokens[8];
		if ( "B".equals(tokens[1]) || "M".equals(tokens[1]) ) {
			MotionSensor newMotionSensor = new MotionSensor(sensorId, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
			newMotionSensor.addObserver(this);
			motionSensors.add(newMotionSensor);
			billingIntrusion.incrementSensors();
		}
		if ("B".equals(tokens[1]) || "F".equals(tokens[1])) {
			TemperatureSensor newTemperatureSensor = new TemperatureSensor(sensorId, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
			newTemperatureSensor.addObserver(this);
			temperatureSensors.add(newTemperatureSensor);
			billingFire.incrementSensors();
		}
		
		
	}
	
	

	@Override
	public void update(Observable o, Object arg) {
		String message = (String)arg;
		currentTime = LocalTime.now();
		System.out.println(message);
		

		
	}
	
	public void generateBill() {
		Double total = 0.00;
		if (temperatureSensors.size() > 0 ) {
		
			billingFire.showCustomerInformation();
			if (motionSensors.size() > 0)
			{
				billingFire.discount();
			}
			billingFire.showCharges();
		}
		if ( motionSensors.size() > 0) {
			billingIntrusion.showCustomerInformation();
			billingIntrusion.showCharges();
		}
			
		
	
		
	}
	
	public void callMonitoringService(String message) throws NumberFormatException, IOException {
		currentTime = LocalTime.now();
		if ("FIRE".equals(message)) {
			billingFire.incrementCall();
			logCall(message, currentTime);
		}
		else if ("INTRUDER".equals(message)) {
			billingIntrusion.incrementCall();
		    logCall(message, currentTime);
		}
		else {
			System.out.println("Error: Wrong Message");
			logCall("Malfunction" , currentTime);
		}
	}
	
	public void callContacts(String message) {
		if (motionSensors.size() > 0) {
			String msg = new String(message + "| Calling: " + billingIntrusion.getCustomerContact() + ", " + billingIntrusion.getContactNumber().get(0) + ", " + billingIntrusion.getContactNumber().get(1) + " at " + currentTime.toString());
			System.out.println("Calling ");
			System.out.println(billingIntrusion.getCustomerContact());
			System.out.println(billingIntrusion.getContactNumber().get(0));
			System.out.println(billingIntrusion.getContactNumber().get(1));
			
			 
		}
		else if (temperatureSensors.size() > 0) {
			String msg = new String(message + "| Calling: " + billingFire.getCustomerContact() + ", " + billingFire.getContactNumber().get(0) + ", " + billingFire.getContactNumber().get(1) + " at " + currentTime.toString());
			System.out.println("Calling ");
			System.out.println(billingFire.getCustomerContact());
			System.out.println(billingFire.getContactNumber().get(0));
			System.out.println(billingFire.getContactNumber().get(1));
//			
		}
		else {
			System.out.println("Error: No Sensors");
			
		}
	}
	/* 
	 * @param message the type of occurrence
	 * @param currentTime the time the occurrence happened 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void logCall(String message, LocalTime time) throws NumberFormatException, IOException {
		String logString = message + " occured at " + time.toString();
		writeInfoToFile("log.txt", logString);
	}
	/* 
	 * @param fileName The file name
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void writeInfoToFile(String fileName, String data) throws IOException, NumberFormatException {
		
		FileWriter fw = new FileWriter(fileName,true);
		BufferedWriter writer = new BufferedWriter(fw);
		writer.newLine();
        writer.write(data);
        writer.close();

	}
	

}
