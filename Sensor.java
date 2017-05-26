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
	
	//Need to change this
	private LocalTime fromTime;
	private LocalTime ToTime;
	

}
