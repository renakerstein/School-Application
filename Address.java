package school;

public class Address {
	private String street;
	private String city;
	private State state;
	private String zipCode;

	public Address(String street, String city, String state, String zipCode)
			throws NullPointerException, InvalidDataException,
			InsufficientLengthException {
		if (city == null || state == null) {
			throw new NullPointerException();
		}
		this.street = street;
		this.city = city;
		this.state = isStateAbbreviationValid(state);
		if (this.state == null) {
			throw new InvalidDataException();
		}
		if (!isZipCodeValid(zipCode)) {
			throw new InsufficientLengthException();
		}
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) throws NullPointerException {
		if (street == null) {
			throw new NullPointerException();
		}
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) throws NullPointerException {
		if (city == null) {
			throw new NullPointerException();
		}
		this.city = city;
	}

	public String getState() {
		return state.getName(); // invokes the getter in the State enum class
	}

	public void setState(String state) throws InvalidDataException {
		this.state = isStateAbbreviationValid(state);
		if (this.state == null) {
			throw new InvalidDataException();
		}
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) throws NullPointerException,
	InsufficientLengthException {
		if (zipCode == null) {
			throw new NullPointerException();
		}
		if (!isZipCodeValid(zipCode)) {
			throw new InsufficientLengthException();
		}
		this.zipCode = zipCode;
	}

	public static boolean isZipCodeValid(String zipCode) {
		if (zipCode.length() == 5 || zipCode.length() == 9) {
			return true;
		}
		return false;
	}

	private static State isStateAbbreviationValid(String state) {

		for (State aState : State.values()) {
			if (aState.name().equalsIgnoreCase(state)
					|| aState.getName().equalsIgnoreCase(state)) {
				return aState;

			}

		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" " + this.street);
		buffer.append(",  " + this.city);
		buffer.append(",  " + this.state);
		buffer.append(",  " + this.zipCode);
		return buffer.toString();
	}
}
