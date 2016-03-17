package school;

public class Course {
	private String courseID;
	private String description;
	private Integer numCredits;
	private String departmentID;

	public Course(String courseID, String description, Integer numCredits,
			String departmentID) throws NullPointerException,
			InvalidDataException {
		if (courseID == null || description == null || numCredits == null
				|| departmentID == null) {
			throw new NullPointerException();
		}
		this.courseID = courseID;
		this.description = description;
		if (numCredits < 0 || numCredits > 250) {
			throw new InvalidDataException();
		} else {
			this.numCredits = numCredits;
		}
		this.departmentID = departmentID;

	}

	public String getCourseID() {
		return courseID;
	}

	public String getDescription() {
		return description;
	}

	public Integer getNumCredits() {
		return numCredits;
	}

	public String getDepartmentID() {
		return departmentID;
	}

	// compareTo()
	public int compareTo(Course other) {
		return this.courseID.compareTo(other.getCourseID());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (courseID == null ? 0 : courseID.hashCode());
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
		Course other = (Course) obj;
		if (courseID == null) {
			if (other.courseID != null) {
				return false;
			}
		} else if (!courseID.equals(other.courseID)) {
			return false;
		}
		return true;
	}

	// toString()
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n\nCourse id: " + this.courseID);
		buffer.append("\nDescription: " + this.description);
		buffer.append("\nNumber of credits: " + this.numCredits);
		buffer.append("\nDepartmentID " + this.departmentID);
		return buffer.toString();

	}

}
