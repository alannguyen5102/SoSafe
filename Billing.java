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
			ArrayList<String> contactNumber, String customerContact, String fromDate, String toDate) {
		this.serviceContractId = serviceContractId;
		this.customerName = customerName;
		this.addressProperty = addressProperty;
		this.contactNumber = contactNumber;
		this.customerContact = customerContact;
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
	 * @param initalCharge
	 * @param numSensorCharge
	 * @param callCharge
	 * @param totalCharge
	 */
	public Billing(Integer serviceContractId, String customerName, String addressProperty,
			ArrayList<String> contactNumber, String customerContact, String fromDate, String toDate,
			Double initalCharge, Double numSensorCharge, Double callCharge, Double totalCharge) {
		
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
	public Double getNumSensorCharge() {
		return getNumSensors().doubleValue() * getSensorConstant();
	}
	
	
	
	/**
	 * @returns the initial charge, sensor charge, and call charges
	 */
	public Double generateTotalCharge() {
		return getInitalCharge() + getNumSensorCharge() + getCallCharge();
	}
	
	/**
	 * Increments the number of calls
	 */
	
	public void incrementCall() {
		this.numCalls++;
	}
	
	
}
