package school;

public class Department {

	private String departmentID;
	private String departmentName;
	private String location;
	private String phoneNumber;
	private String faxNumber;
	private String departmentChairperson;

	public Department(String departmentID, String departmentName)
			throws NullPointerException, InsufficientLengthException {
		this(departmentID, departmentName, null, null, null, null);
	}

	public Department(String departmentID, String departmentName,
			String location, String phoneNumber, String faxNumber,
			String departmentChairperson) throws NullPointerException,
			InsufficientLengthException {
		if (departmentID == null || departmentName == null) {
			throw new NullPointerException();
		}
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.location = location;
		// check to make sure the phoneNumber and faxNumber are valid- 10 digits
		// long,
		// and that they are not equal to null because that will make it invalid
		// and
		// it is okay for the numbers to be null, because it is not required for
		// setting up a department
		if (phoneNumber != null) {
			if (!isNumberValid(phoneNumber)) {
				throw new InsufficientLengthException();
			}
		}
		this.phoneNumber = phoneNumber;
		if (faxNumber != null) {
			if (!isNumberValid(faxNumber)) {
				throw new InsufficientLengthException();
			}
		}
		this.faxNumber = faxNumber;
		this.departmentChairperson = departmentChairperson;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) throws NullPointerException {
		if (location == null) {
			throw new NullPointerException();
		}
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws NullPointerException,
			InsufficientLengthException {
		if (phoneNumber == null) {
			throw new NullPointerException();
		}
		if (!isNumberValid(phoneNumber)) {
			throw new InsufficientLengthException();
		}
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) throws NullPointerException,
			InsufficientLengthException {
		if (faxNumber == null) {
			throw new NullPointerException();
		}
		if (!isNumberValid(faxNumber)) {
			throw new InsufficientLengthException();
		}
		this.faxNumber = faxNumber;
	}

	public String getDepartmentChairperson() {
		return departmentChairperson;
	}

	public void setDepartmentChairperson(String departmentChairperson)
			throws NullPointerException {
		if (departmentChairperson == null) {
			throw new NullPointerException();
		}
		this.departmentChairperson = departmentChairperson;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	// class method- checks if the phone numbers and fax numbers are valid
	private static boolean isNumberValid(String number) {
		if (number.length() == 10) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\nDepartmentID " + this.departmentID);
		buffer.append("\nDeaprtmentName " + this.departmentName);
		if (this.location != null) {
			buffer.append("\nLocation " + this.location);
		}
		if (this.phoneNumber != null) {
			buffer.append("\nPhone number " + this.phoneNumber);
		}
		if (this.faxNumber != null) {
			buffer.append("\nFax number " + this.faxNumber);
		}
		if (this.departmentChairperson != null) {
			buffer.append("\nDepartment chairperson "
					+ this.departmentChairperson);
		}
		return buffer.toString();
	}

	public int compareTo(Department other) {
		return this.departmentID.compareTo(other.getDepartmentID());

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (departmentID == null ? 0 : departmentID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Department other = (Department) obj;
		if (departmentID == null) {
			if (other.departmentID != null) {
				return false;
			}
		} else if (!departmentID.equals(other.departmentID)) {
			return false;
		}
		return true;
	}
}