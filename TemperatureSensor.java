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
	}

	/**
	 * @param location
	 * @param fromTime
	 * @param toTime
	 */
	public TemperatureSensor(String location, String fromTime, String toTime) {
		super(location, fromTime, toTime);
	}

}
