package school;

public class Person {
	private String ID;
	private String firstName;
	private String lastName;
	private Character midInitial;
	private Address address;
	private String phoneNumber;
	private Character gender;

	public Person(String ID, String firstName, String lastName,
			Address address, Character gender)
			throws InsufficientLengthException {
		this(ID, firstName, lastName, null, address, null, gender);
	}

	public Person(String ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Character gender) throws InsufficientLengthException {
		if (ID == null || firstName == null || lastName == null
				|| address == null || gender == null) {
			throw new NullPointerException();
		}

		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midInitial = midInitial;
		this.address = address;

		if (!isPhoneNumberValid(phoneNumber)) {
			throw new InsufficientLengthException();
		}

		this.phoneNumber = phoneNumber;
		this.gender = gender;

	}

	public static boolean isPhoneNumberValid(String phoneNumber) {
		if (phoneNumber.length() == 10 || phoneNumber.equalsIgnoreCase("N/A")) { // phoneNumber
			// is
			// optional
			// and
			// therefore
			// may be
			// null
			return true;
		} else {
			return false;
		}

	}

	public void setLastName(String lastName) {
		if (lastName == null) {
			throw new NullPointerException();
		}
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		if (address == null) {
			throw new NullPointerException();
		}
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber)
			throws InsufficientLengthException {

		if (!isPhoneNumberValid(phoneNumber)) {
			throw new InsufficientLengthException();
		}
		this.phoneNumber = phoneNumber;
	}

	public String getID() {
		return ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Character getMidInitial() {
		return midInitial;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Character getGender() {
		return gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ID == null ? 0 : ID.hashCode());
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
		Person other = (Person) obj;
		if (ID == null) {
			if (other.ID != null) {
				return false;
			}
		} else if (!ID.equals(other.ID)) {
			return false;
		}
		return true;
	}

	public int compareTo(Person other) {
		return this.ID.compareTo(other.getID());

	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\nID: " + this.ID);
		buffer.append("\nFirst name: " + this.firstName);
		buffer.append("\nLast Name: " + this.lastName);
		if (this.midInitial != null) {
			buffer.append("\nMid initial: " + this.midInitial);
		}
		buffer.append("\nAddress: " + this.address.toString()); // call the
		// toString()
		// form the
		// Address class
		if (this.phoneNumber != null) {
			buffer.append("\nPhone number: " + this.phoneNumber);
		}
		buffer.append("\nGender: " + this.gender);
		return buffer.toString();
	}
}