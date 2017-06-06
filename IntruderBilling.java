/**
 * 
 */
package sosafesystems;

import java.util.ArrayList;

/**
 * @author alannguyen
 *
 */
public class IntruderBilling extends Billing {

	/**
	 * @param serviceContractId
	 * @param customerName
	 * @param addressProperty
	 * @param contactNumber
	 * @param customerContact
	 * @param fromDate
	 * @param toDate
	 */
	public IntruderBilling(Integer serviceContractId, String customerName, String addressProperty,
			ArrayList<String> contactNumber, String customerContact, String fromDate, String toDate) {
		super(serviceContractId, customerName, addressProperty, contactNumber, customerContact, fromDate, toDate);
		// TODO Auto-generated constructor stub
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
	public IntruderBilling(Integer serviceContractId, String customerName, String addressProperty,
			ArrayList<String> contactNumber, String customerContact, String fromDate, String toDate, Integer numSensors,
			Integer numCalls, Double totalCharge) {
		super(serviceContractId, customerName, addressProperty, contactNumber, customerContact, fromDate, toDate,
				numSensors, numCalls, totalCharge);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see sosafesystems.Billing#getCallConstant()
	 */
	@Override
	public Double getCallConstant() {
		return 20.00;
	}

	/* (non-Javadoc)
	 * @see sosafesystems.Billing#getInitalCharge()
	 */
	@Override
	public Double getInitalCharge() {
		return 200.00;
	}

	/* (non-Javadoc)
	 * @see sosafesystems.Billing#getSensorConstant()
	 */
	@Override
	public Double getSensorConstant() {
		return 50.00;
	}

}
