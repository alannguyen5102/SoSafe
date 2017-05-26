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
	
	//Creates a Sensor with a location
	public Sensor(String location)
	{
		++count;
		this.location = location;
		idNum = count;
		powerStatus = true;
		manualStatus = true;
		alarmStatus = false;
		this.fromTime = null;
		this.toTime = null;	
	}
	
	
	//Creates a Sensor with Timing
	public Sensor(String location, LocalTime fromTime, LocalTime toTime)
	{
		++count;
		this.location = location;
		idNum = count;
		powerStatus = true;
		manualStatus = false;
		alarmStatus = false;
		this.fromTime = fromTime;
		this.toTime = toTime;	
	}
	
	//Getter Functions
	public Integer getIdNum()
	{
		return idNum;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public Boolean getPowerStatus()
	{
		return powerStatus;
	}
	
	public Boolean getManualStatus()
	{
		return manualStatus;
	}
	
	public Boolean getAlarmStatus()
	{
		return alarmStatus;
	}
	
	public void getSchedule()
	{
		System.out.println("Start: " + fromTime);
		System.out.println("End " + toTime);
	}
	
	
	
	

}
