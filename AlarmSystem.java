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
/**
 * @author alannguyen
 *
 */
public class AlarmSystem {

	Boolean includesFireSystem;
	private ArrayList<MotionSensor> motionSensors;
	private ArrayList<TemperatureSensor> temperatureSensors;
	private IntruderBilling billingIntrusion;
	private FireBilling billingFire;

	
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
		String [] tokens = line.split(",");
		int sensorId = Integer.parseInt(tokens[2]);
		String location = tokens[3];
		Boolean powerStatus = Boolean.valueOf(tokens[4]);
		Boolean manualStatus = Boolean.valueOf(tokens[5]);
		Boolean alarmStatus = Boolean.valueOf(tokens[6]);
		String fromTime = tokens[7];
		String toTime = tokens[8];
		if ( "F".equals(tokens[1]) || "M".equals(tokens[1]) ) {
			MotionSensor newMotionSensor = new MotionSensor(sensorId, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
			motionSensors.add(newMotionSensor);
		}
		if ("F".equals(tokens[1])) {
			TemperatureSensor newTemperatureSensor = new TemperatureSensor(sensorId, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
			temperatureSensors.add(newTemperatureSensor);
		}
		
		
	}
	

}
