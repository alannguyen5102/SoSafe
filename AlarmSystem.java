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
	private LocalTime fromTime = LocalTime.NOON;
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
		String address = tokens[3];
		String service = tokens[4];
		String contact1 = tokens[5];
		String contact2 = tokens[5];
		
		ArrayList<String> contacts = new ArrayList<String>();
		contacts.add(contact1);
		contacts.add(contact2);
		if ("F".equals(tokens[4]) || "M".equals(tokens[4]) ) {
			
			billingIntrusion = new IntruderBilling(id, name, address, contacts, "999");
		}
		if ("F".equals(tokens[4])) {
			billingFire = new FireBilling(id, name, address, contacts, "999");
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
		if ( "F".equals(tokens[1]) || "M".equals(tokens[1]) ) {
			MotionSensor newMotionSensor = new MotionSensor(sensorId, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
			newMotionSensor.addObserver(this);
			motionSensors.add(newMotionSensor);
			billingIntrusion.incrementSensors();
		}
		if ("F".equals(tokens[1])) {
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
		callContacts(message);
		//TODO timer?
		
		
	}
	
	public Double generateBill() {
		Double total = 0.00;
		if (motionSensors.size() > 0) {
			total = billingIntrusion.generateTotalCharge() + billingFire.generateTotalCharge() - 60;
		}
		else {
			total = billingIntrusion.generateTotalCharge() + billingFire.generateTotalCharge();
		}
		
		return total;
	}
	
	public void callMonitoringService(String message) {
		if ("FIRE".equals(message)) {
			billingFire.incrementCall();
		}
		else if ("INTRUDER".equals(message)) {
			billingIntrusion.incrementCall();
		}
		else {
			System.out.println("Error: Wrong Message");
		}
	}
	
	public void callContacts(String message) {
		if (motionSensors.size() > 0) {
			System.out.println("Calling ");
			System.out.println(billingIntrusion.getCustomerContact());
			System.out.println(billingIntrusion.getContactNumber().get(0));
			System.out.println(billingIntrusion.getContactNumber().get(1));
			
			
		}
		else if (temperatureSensors.size() > 0) {
			System.out.println("Calling ");
			System.out.println(billingIntrusion.getCustomerContact());
			System.out.println(billingIntrusion.getContactNumber().get(0));
			System.out.println(billingIntrusion.getContactNumber().get(1));
			
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
	public void logCall(String message, LocalTime currentTime) throws NumberFormatException, IOException {
		String logString = message + " occured at " + currentTime.toString();
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
