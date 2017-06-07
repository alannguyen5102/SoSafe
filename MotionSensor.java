/**
 * 
 */
package sosafesystems;

/**
 * @author alannguyen
 *
 */
public class MotionSensor extends Sensor {

	/**
	 * @param location
	 */
	public MotionSensor(String location) {
		super(location);
		message = new String("INTRUDER");
	}

	/**
	 * @param location
	 * @param fromTime
	 * @param toTime
	 */
	public MotionSensor(String location, String fromTime, String toTime) {
		super(location, fromTime, toTime);
		message = new String("INTRUDER");
	}
	
	public MotionSensor(Integer idNum, String location, Boolean powerStatus, Boolean manualStatus, Boolean alarmStatus, String fromTime, String toTime) {
		super(idNum, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
		message = new String("INTRUDER");
	}

}
