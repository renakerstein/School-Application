package school;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Teacher extends Employee {
	private String departmentID;
	private String socialSecurityNum;
	private Degree degree;
	private Major majorID;
	private Double salary;
	private ArrayList<TaughtCourse> taughtCourses = new ArrayList<TaughtCourse>();

	public Teacher(String ID, String firstName, String lastName,
			Address address, Character gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, String employeeTypeID,
			String departmentID, String socialSecurityNum, String degree,
			String majorID, Double salary) throws NullPointerException,
			InsufficientLengthException, InvalidDataException {
		this(ID, firstName, lastName, null, address, null, gender, hireDate,
				dateOfBirth, employeeTypeID, departmentID, socialSecurityNum,
				degree, majorID, salary);
	}

	public Teacher(String ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Character gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, String employeeTypeID,
			String departmentID, String socialSecurityNum, String degree,
			String majorID, Double salary) throws NullPointerException,
			InsufficientLengthException, InvalidDataException {
		super(ID, firstName, lastName, midInitial, address, phoneNumber,
				gender, hireDate, dateOfBirth, employeeTypeID);
		if (departmentID == null || socialSecurityNum == null || degree == null
				|| majorID == null || salary == null) {
			throw new NullPointerException();
		}
		this.departmentID = departmentID;
		this.socialSecurityNum = socialSecurityNum;
		this.degree = isDegreeValid(degree);
		this.majorID = isMajorIdValid(majorID);

		if (salary > 20000 && salary < 100000) { // set the limits of the salary
			this.salary = salary;
		} else {
			throw new InvalidDataException();
		}
	}

	public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) throws InvalidDataException {
		if (degree == null) {
			throw new InvalidDataException();
		}
		this.degree = degree;

	}

	public Major getMajor() {
		return majorID;
	}

	public void setMajor(Major major) throws InvalidDataException {
		if (major == null) {
			throw new InvalidDataException();
		}
		this.majorID = major;

	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Integer newSalary) throws InvalidDataException {
		if (newSalary > this.salary) { // With a raise the salary can go beyond
			// the limits
			this.salary = (double) newSalary;
		} else {
			throw new InvalidDataException();
		}
	}

	public String getDepartmentID() {
		return departmentID;
	}

	public String getSocialSecurityNum() {
		return socialSecurityNum;
	}

	// return deep copy??
	public ArrayList<TaughtCourse> getTaughtCourses() {
		return taughtCourses;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nDepartment ID: " + this.departmentID);
		buffer.append("\nSocial Security number: " + this.socialSecurityNum);
		buffer.append("\nDegree: " + this.degree);
		buffer.append("\nMajor: " + this.majorID);
		buffer.append("\nSalary: " + this.salary);
		return buffer.toString();
	}

	public void applyRaise(Double percent) {
		this.salary += salary * percent;
	}

	public void taughtCourse(Course c, String teacherID, Integer year,
			Semester semester, Section sectionID) throws InvalidDataException {
		boolean courseFound = false;
		TaughtCourse aCourse = new TaughtCourse(c, teacherID, year, semester,
				sectionID);
		// make sure this taught course hasn't already been added
		for (TaughtCourse theCourse : taughtCourses) {
			if (theCourse.getCourseID().compareTo(aCourse.getCourseID()) == 0) {
				courseFound = true;
				throw new InvalidDataException();
			}
		}
		if (courseFound == false) {
			taughtCourses.add(aCourse);
		}

	}

	// return how many courses this teacher taught during given semester
	public int howManyCoursesPerSemester(Integer year, Semester semesterID) {
		int totalCourses = 0;
		for (TaughtCourse aCourse : taughtCourses) {
			if (aCourse.getYear().compareTo(year) == 0
					&& aCourse.getSemesterID().compareTo(semesterID) == 0) {
				totalCourses++;

			}
		}
		return totalCourses;
	}

	public int howManyDifferentCourses() {
		ArrayList<String> courseTotal = new ArrayList<String>();
		String course1, course2;
		for (int i = 0; i < taughtCourses.size() - 1; i++) {
			course1 = taughtCourses.get(i).getCourseID();
			for (int j = i + 1; j < taughtCourses.size() - 1; j++) {
				course2 = taughtCourses.get(j).getCourseID();
				if (course1.compareTo(course2) != 0) {
					for (String c : courseTotal) {
						if (c.compareTo(course1) == 0) {
							courseTotal.add(course1);
						}// close if
					}// close for

				}// close if
			}// close for
		}// close for
		return courseTotal.size();
	}

	// compareTo
	public int compareTo(Teacher other) {
		return this.getLastName().compareTo(other.getLastName());
	}

	// check if majorID is valid
	private static Major isMajorIdValid(String majorID) {
		for (Major aMajor : Major.values()) {
			if (aMajor.name().equalsIgnoreCase(majorID)) {
				return aMajor;
			}
		}
		return null;
	}

	// check if degree is valid
	private static Degree isDegreeValid(String degree) {
		for (Degree aDegree : Degree.values()) {
			if (aDegree.name().equalsIgnoreCase(degree)) {
				return aDegree;
			}
		}
		return null;
	}
}