/**
 * 
 */
package sosafesystems;

/**
 * @author alannguyen
 *
 */
public class TemperatureSensor extends Sensor {

	/**
	 * @param location
	 */
	public TemperatureSensor(String location) {
		super(location);
		message = new String("FIRE");
	}

	/**
	 * @param location
	 * @param fromTime
	 * @param toTime
	 */
	public TemperatureSensor(String location, String fromTime, String toTime) {
		super(location, fromTime, toTime);
		message = new String("FIRE");
	}
	public TemperatureSensor(Integer idNum, String location, Boolean powerStatus, Boolean manualStatus, Boolean alarmStatus, String fromTime, String toTime) {
		super(idNum, location, powerStatus, manualStatus, alarmStatus, fromTime, toTime);
		message = new String("FIRE");
	}

}
