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
	private LocalTime currentTime;
	
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
	public Sensor(String location, String fromTime, String toTime)
	{
		++count;
		this.location = location;
		idNum = count;
		powerStatus = true;
		manualStatus = false;
		alarmStatus = false;
		this.fromTime = LocalTime.parse(fromTime);
		this.toTime = LocalTime.parse(toTime);	
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
	
	public Boolean tripSensor()
	{
		//Checks if alarm is untripped
		if (alarmStatus == false)
		{
			currentTime = LocalTime.now();
			if (powerStatus == false)
			{
				alarmStatus = false;
				return false;
			}
			else if (manualStatus == false)
			{
				if (fromTime.isAfter(currentTime) && toTime.isBefore(currentTime))
				{
					setChanged();
					alarmStatus = true;
					notifyObservers(new Boolean(alarmStatus));
					return true;
				}
				else
				{
					alarmStatus = false;
					return false;
				}
			}
			setChanged();
			notifyObservers(new Boolean(alarmStatus));
			alarmStatus = true;
			return true;
		}
		else
		{
			return alarmStatus;
		}
	}
	
	
	
	

}
