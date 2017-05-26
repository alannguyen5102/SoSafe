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
			
			//Checks if sensor is off, alarm cannot trip if sensor is off
			if (powerStatus == false)
			{
				alarmStatus = false;
				return false;
			}
			
			//Check if the sensors are on a schedule
			else if (manualStatus == false)
			{
				//Checks if fromTime is before toTime
				if (fromTime.isBefore(toTime))
				{
					//In this case, current time is after fromTime and before toTime
					if (currentTime.isAfter(fromTime) && currentTime.isBefore(toTime))
					{
						alarmStatus = true;
						setChanged();
						notifyObservers(new Boolean(alarmStatus));
						return true;
					}
					
					//If not in the scheduled time, does not trip
					else
					{
						alarmStatus = false;
						return false;
					}
				}
				
				//Checks if fromTime is after toTime
				//This has a special case
				else
				{
					//currentTime can be both after fromTime and toTime, or aslso before fromTime and toTime
					if ((currentTime.isAfter(fromTime) && currentTime.isAfter(toTime)) || (currentTime.isBefore(fromTime) && currentTime.isBefore(toTime) ) )
					{
						alarmStatus = true;
						setChanged();
						notifyObservers(new Boolean(alarmStatus));
						return true;
					}
					
					//If not in the scheduled time, does not trip
					else
					{
						alarmStatus = false;
						return false;
					}
				}
				


			}
			
			//If the alarm is tripped, on, and in manual mode, rings the alarm
			alarmStatus = true;
			setChanged();
			notifyObservers(new Boolean(alarmStatus));
			return true;

		}
		
		//Otherwise alarm is already tripped
		else
		{
			return alarmStatus;
		}
	}
	
	public void setTime(String fromTime, String toTime) 
	{
		this.fromTime = LocalTime.parse(fromTime);
		this.toTime = LocalTime.parse(toTime);
	}
	
	
	
	

}
