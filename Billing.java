/**
 * 
 */
package sosafesystems;

import java.util.ArrayList;
import java.util.*;

/**
 * @author alannguyen
 *
 */
public abstract class Billing implements Observer {

	private Integer serviceContractId;
	private String customerName;
	private String addressProperty;
	private ArrayList<String> contactNumber;
	private String customerContact;
	
	//Change these later
	private String fromDate;
	private String toDate;

	private Integer numSensors;
	private Integer numCalls;
	private Double totalCharge;
	
	

	/**
	 * @param serviceContractId
	 * @param customerName
	 * @param addressProperty
	 * @param contactNumber
	 * @param customerContact
	 * @param fromDate
	 * @param toDate
	 */
	public Billing(Integer serviceContractId, String customerName, String addressProperty,
			ArrayList<String> contactNumber, String customerContact) {
		this.serviceContractId = serviceContractId;
		this.customerName = customerName;
		this.addressProperty = addressProperty;
		this.contactNumber = contactNumber;
		this.customerContact = customerContact;
		this.numSensors = 0;
		this.numCalls = 0;
		this.fromDate = new String("01-01");
		this.toDate = new String("12-31");
	}

	/**
	 * @param serviceContractId
	 * @param customerName
	 * @param addressProperty
	 * @param contactNumber
	 * @param customerContact
	 * @param fromDate
	 * @param toDate
	 */
	public Billing(Integer serviceContractId, String customerName, String addressProperty,
			ArrayList<String> contactNumber, String customerContact, String fromDate, String toDate) {
		this.serviceContractId = serviceContractId;
		this.customerName = customerName;
		this.addressProperty = addressProperty;
		this.contactNumber = contactNumber;
		this.customerContact = customerContact;
		this.numSensors = 0;
		this.numCalls = 0;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}


	


	/**
	 * @param serviceContractId
	 * @param customerName
	 * @param addressProperty
	 * @param contactNumber
	 * @param customerContact
	 * @param fromDate
	 * @param toDate
	 * @param numSensors
	 * @param numCalls
	 * @param totalCharge
	 */
	public Billing(Integer serviceContractId, String customerName, String addressProperty,
			ArrayList<String> contactNumber, String customerContact, String fromDate, String toDate, Integer numSensors,
			Integer numCalls, Double totalCharge) {
		this.serviceContractId = serviceContractId;
		this.customerName = customerName;
		this.addressProperty = addressProperty;
		this.contactNumber = contactNumber;
		this.customerContact = customerContact;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.numSensors = numSensors;
		this.numCalls = numCalls;
		this.totalCharge = totalCharge;
	}





	/**
	 * @return the serviceContractId
	 */
	public Integer getServiceContractId() {
		return serviceContractId;
	}

	/**
	 * @param serviceContractId the serviceContractId to set
	 */
	public void setServiceContractId(Integer serviceContractId) {
		this.serviceContractId = serviceContractId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the addressProperty
	 */
	public String getAddressProperty() {
		return addressProperty;
	}

	/**
	 * @param addressProperty the addressProperty to set
	 */
	public void setAddressProperty(String addressProperty) {
		this.addressProperty = addressProperty;
	}

	/**
	 * @return the contactNumber
	 */
	public ArrayList<String> getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(ArrayList<String> contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the customerContact
	 */
	public String getCustomerContact() {
		return customerContact;
	}

	/**
	 * @param customerContact the customerContact to set
	 */
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the numSensors
	 */
	public Integer getNumSensors() {
		return numSensors;
	}

	/**
	 * @param numSensors the numSensors to set
	 */
	public void setNumSensors(Integer numSensors) {
		this.numSensors = numSensors;
	}

	/**
	 * @return the numCalls
	 */
	public Integer getNumCalls() {
		return numCalls;
	}

	/**
	 * @param numCalls the numCalls to set
	 */
	public void setNumCalls(Integer numCalls) {
		this.numCalls = numCalls;
	}

	/**
	 * @return the totalCharge
	 */
	public Double getTotalCharge() {
		return totalCharge;
	}

	/**
	 * @param totalCharge the totalCharge to set
	 */
	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}


	/**
	 * @return the cost per call 
	 */
	public abstract Double getCallConstant();
	
	/**
	 * @return the callCharge which is number of calls * price per call
	 */
	public Double getCallCharge() {
		return getNumCalls().doubleValue() * getCallConstant();
	}

	/**
	 * @return the initalCharge
	 */
	public abstract Double getInitalCharge();
	
	/**
	 * @return the cost per sensor
	 */
	public abstract Double getSensorConstant();

	/**
	 * @return the numSensorCharge which is number of sensors * price per sensor
	 */
	public Double getNumSensorsCharge() {
		return getNumSensors().doubleValue() * getSensorConstant();
	}
	
	
	
	/**
	 * @returns the initial charge, sensor charge, and call charges
	 */
	public Double generateTotalCharge() {
		return getInitalCharge() + getNumSensorsCharge() + getCallCharge();
	}
	
	public void showCustomerInformation() {
		System.out.println("Service Contract ID: " + this.serviceContractId);
		System.out.println("Customer Name      : " + this.customerName);
		System.out.println("Property Address   : " + this.addressProperty);
		for(String elem : this.contactNumber) {
			System.out.println("Contact Number     : " + elem);
		}
		System.out.println("Customer Contact   : " + this.customerContact);
		System.out.println("Service From Date  : " + this.fromDate);
		System.out.println("Service To Date    : " + this.toDate);
		
	}
	
	public void showCharges () {
		System.out.println("Installation Charge: $" + getInitalCharge());
		System.out.println("Sensor Installation: $" + getNumSensorsCharge());
		System.out.println("----" + getSensorConstant() + "*" + getNumSensors());
		System.out.println("Monitoring Calls   : $" + getCallCharge());
		System.out.println("----" + getCallConstant() + "*" + getNumCalls());
		System.out.println("Total Cost: $" + generateTotalCharge());
	}
	
	/**
	 * Increments the number of calls
	 */
	
	public void incrementCall() {
		this.numCalls++;
	}
	
	/**
	 * Increments the number of sensors
	 */
	
	public void incrementSensors() {
		this.numSensors++;
	}
	
	
	public void update( Observable observable, Object numCalls ) {
		this.numCalls = ((Integer)numCalls); 
		generateTotalCharge();
	}
	
	
}
