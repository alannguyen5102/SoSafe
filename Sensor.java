/**
 * 
 */
package sosafesystems;
import java.time.LocalTime;
import java.util.*;

/**
 * @author alannguyen
 *
 */
public abstract class Sensor extends java.util.Observable
{
	protected static Integer count = 0;
	private Integer idNum;
	private String location;
	private Boolean powerStatus;
	private Boolean manualStatus;
	private Boolean alarmStatus;
	
	private LocalTime fromTime;
	private LocalTime toTime;
	
	public Sensor(String location)
	{
		++count;
		this.location = location;
		idNum = count;
		powerStatus = true;
		manualStatus = true;
		alarmStatus = false;
		fromTime = null;
		toTime = null;	
	}

}
