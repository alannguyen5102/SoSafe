/**
 * 
 */
package sosafesystems;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	

	
	/**
	 * 
	 */
	public AlarmSystem() {
		
	}
	
	public AlarmSystem(String fileName) throws IOException {
		motionSensors = new ArrayList<MotionSensor>();
		temperatureSensors = new ArrayList<TemperatureSensor>();
		loadSensorsFromFile(fileName);
		
		
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
		System.out.println(message);
		callContacts(message);
		
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
	

}
