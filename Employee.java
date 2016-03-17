package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Employee extends Person {
	private GregorianCalendar hireDate;
	private GregorianCalendar dateOfBirth;
	private EmployeeType employeeTypeID;

	public Employee(String ID, String firstName, String lastName,
			Address address, Character gender, String employeeID,
			GregorianCalendar hireDate, GregorianCalendar dateOfBirth,
			String employeeTypeID) throws InsufficientLengthException,
			InvalidDataException {
		this(ID, firstName, lastName, null, address, null, gender, hireDate,
				dateOfBirth, employeeTypeID);
	}

	public Employee(String ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Character gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, String employeeTypeID)
					throws NullPointerException, InsufficientLengthException,
					InvalidDataException {
		super(ID, firstName, lastName, midInitial, address, phoneNumber, gender); // call
		// the
		// parent
		// class
		if (hireDate == null || dateOfBirth == null) {
			throw new NullPointerException();
		}

		if (dateOfBirth.get(Calendar.YEAR) + 18 > hireDate.get(Calendar.YEAR)) {
			throw new InvalidDataException();
		} else {

			this.hireDate = hireDate;
			this.dateOfBirth = dateOfBirth;
		}

		this.employeeTypeID = isEmployeeTypeIdValid(employeeTypeID);
		if (employeeTypeID == null) {
			throw new InvalidDataException();
		}
	}

	public EmployeeType getEmployeeTypeID() {
		return employeeTypeID;
	}

	public void setEmployeeTypeID(String employeeTypeID)
			throws NullPointerException {
		if (employeeTypeID == null) {
			throw new NullPointerException();
		}
		this.employeeTypeID = EmployeeType.valueOf(employeeTypeID);
	}

	// return a deep copy
	public GregorianCalendar getHireDate() {
		GregorianCalendar date = new GregorianCalendar(
				hireDate.get(Calendar.YEAR), hireDate.get(Calendar.MONTH),
				hireDate.get(Calendar.DAY_OF_MONTH));
		return date;
	}

	public GregorianCalendar getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
		buffer.append(super.toString());
		buffer.append("\nHire date: "
				+ formatter.format(this.hireDate.getTime()));
		buffer.append("\nDate of birth: "
				+ formatter.format(this.dateOfBirth.getTime()));
		buffer.append("\nEmployee type ID: " + this.employeeTypeID);
		return buffer.toString();
	}

	private static EmployeeType isEmployeeTypeIdValid(String employeeTypeID) {
		for (EmployeeType aEmployee : EmployeeType.values()) {
			if (aEmployee.name().equalsIgnoreCase(employeeTypeID)) {
				return aEmployee;
			}
		}
		return null;
	}

}
