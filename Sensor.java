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
	public String message;
	//Permanent, idNum cannot change
	private Integer idNum;
	
	//Permanent, location cannot change
	private String location;
	private Boolean powerStatus;
	private Boolean manualStatus;
	private Boolean alarmStatus;
	
	private LocalTime fromTime;
	private LocalTime toTime;
	private LocalTime currentTime;
	private LocalTime checkFromTime;
	private LocalTime checkToTime;
	
	//Creates a Sensor with a location
	/*
	 * @param location
	 */
	public Sensor(String location)
	{
		++count;
		this.location = location;
		idNum = count;
		powerStatus = true;
		manualStatus = true;
		alarmStatus = false;
		this.fromTime = LocalTime.MIN;
		this.toTime = LocalTime.MAX;	
		
		message = new String("ERROR");
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
		
		message = new String("ERROR");
	}
	
	/*
	 * Creates a sensor with all fields
	 */
	public Sensor(Integer idNum, String location, Boolean powerStatus, Boolean manualStatus, Boolean alarmStatus, String fromTime, String toTime)
	{
		this.idNum = idNum;
		this.location = location;
		this.powerStatus = powerStatus;
		this.manualStatus = manualStatus;
		this.alarmStatus = alarmStatus;
		
		this.fromTime = LocalTime.parse(fromTime);
		this.toTime = LocalTime.parse(toTime);	
		message = new String("ERROR");
	}
	
	
	//Getter Functions

	public Integer getIdNum()
	{
		return idNum;
	}
	
	//
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
	
	public LocalTime getLocalTime()
	{
		currentTime = LocalTime.now();
		return currentTime;
	}
	
	public Boolean tripSensor(LocalTime checkFromTime, LocalTime checkToTime)
	{
		System.out.println("Checking if Tripped: " + alarmStatus);
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
			
			//Check if the sensors are on
			else if (powerStatus == true)
			{
				//Checks if fromTime is before toTime
				if (fromTime.isBefore(checkToTime))
				{
					//In this case, current time is after fromTime and before toTime
					if (currentTime.isAfter(checkFromTime) && currentTime.isBefore(checkToTime))
					{
						alarmStatus = true;
						setChanged();
						notifyObservers(message + " at " + location);
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
					if ((currentTime.isAfter(checkFromTime) && currentTime.isAfter(checkToTime)) || (currentTime.isBefore(checkFromTime) && currentTime.isBefore(checkToTime) ) )
					{
						alarmStatus = true;
						setChanged();
						notifyObservers(message + " at " + location);
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
			notifyObservers(message + " at " + location);
			return true;

		}
		
		//Otherwise alarm is already tripped
		else
		{
			return alarmStatus;
		}
	}
	
	//Setter Functions
	public void setPowerStatus(Boolean powerStatus)
	{
		this.powerStatus = powerStatus;
	}
	
	public void setManualStatus(Boolean manualStatus)
	{
		this.manualStatus = manualStatus;
	}
	
	public void setSchedule(String fromTime, String toTime) 
	{
		this.fromTime = LocalTime.parse(fromTime);
		this.toTime = LocalTime.parse(toTime);
	}
	
	
	
	

}
