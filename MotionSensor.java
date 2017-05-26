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
	}

	/**
	 * @param location
	 * @param fromTime
	 * @param toTime
	 */
	public MotionSensor(String location, String fromTime, String toTime) {
		super(location, fromTime, toTime);
	}

}
