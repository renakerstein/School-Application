package school;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student extends Person { // Student "is a" Person
	private Major major;
	private GregorianCalendar enrolledDate;
	private GregorianCalendar dateOfBirth;
	private Double GPA;
	private Integer creditsEarned;
	private String socialSecurityNum;
	private ArrayList<CompletedCourse> compCourses = new ArrayList<CompletedCourse>();

	public Student(String ID, String firstName, String lastName,
			Address address, Character gender, GregorianCalendar enrolledDate,
			GregorianCalendar dateOfBirth, String socialSecurityNum)
					throws InsufficientLengthException {
		this(ID, firstName, lastName, null, address, null, gender, null,
				enrolledDate, dateOfBirth, socialSecurityNum);
	}

	public Student(String ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Character gender, String major, GregorianCalendar enrolledDate,
			GregorianCalendar dateOfBirth, String socialSecurityNum)
					throws NullPointerException, InsufficientLengthException {
		super(ID, firstName, lastName, midInitial, address, phoneNumber, gender); // call
		// the
		// parent
		// class
		if (enrolledDate == null || dateOfBirth == null
				|| socialSecurityNum == null) {
			throw new NullPointerException();
		}

		if (major == null) {
			this.major = Major.UDCD;
		} else {
			this.major = isMajorValid(major);
		}
		this.enrolledDate = enrolledDate;
		this.dateOfBirth = dateOfBirth;
		this.GPA = null; // not sent it as a parameter because set using the
		// "setter"
		this.creditsEarned = 0; // not sent it as a parameter because set using
		// the "setter"
		this.socialSecurityNum = socialSecurityNum;

	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = isMajorValid(major);
		if (major == null) {
			this.major = Major.UDCD;

		}
	}

	public Double getGPA() {
		return GPA;
	}

	public void setGPA(Double GPA) throws InvalidDataException {
		// make sure GPA is a valid value
		if (GPA >= 0 || GPA <= 4.0) {
			this.GPA = GPA;
		} else {
			throw new InvalidDataException();
		}
	}

	public Integer getCreditsEarned() {
		return creditsEarned;
	}

	public void setCreditsEarned(Integer creditsEarned)
			throws InvalidDataException {
		// make sure that credits earned is a reasonable value
		if (creditsEarned < 0 || creditsEarned > 4) {
			throw new InvalidDataException();
		}
		this.creditsEarned = creditsEarned;
	}

	// return a deep copy so it should be immutable
	public GregorianCalendar getEnrolledDate() {
		GregorianCalendar date = new GregorianCalendar(
				enrolledDate.get(Calendar.YEAR),
				enrolledDate.get(Calendar.MONTH),
				enrolledDate.get(Calendar.DAY_OF_MONTH));
		return date;
	}

	public GregorianCalendar getDateOfBirth() {
		GregorianCalendar bDate = new GregorianCalendar(
				dateOfBirth.get(Calendar.YEAR),
				dateOfBirth.get(Calendar.MONTH),
				dateOfBirth.get(Calendar.DAY_OF_MONTH));
		return bDate;
	}

	public String getSocialSecurityNum() {
		return socialSecurityNum;
	}

	public ArrayList<CompletedCourse> getCourses() {
		ArrayList<CompletedCourse> coursesCopy = new ArrayList<CompletedCourse>();
		for (int i = 0; i < compCourses.size(); i++) {
			coursesCopy.add(compCourses.get(i));
		}
		return coursesCopy;
	}

	// check to see if major is one of the enumerated types
	private static Major isMajorValid(String major) {
		for (Major aMajor : Major.values()) {
			if (aMajor.name().equalsIgnoreCase(major)) {
				return aMajor;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
		buffer.append(super.toString());
		buffer.append("\nMajor " + this.major);
		buffer.append("\nEnrolled Date "
				+ formatter.format(this.enrolledDate.getTime()));
		buffer.append("\nDate of Birth "
				+ formatter.format(this.dateOfBirth.getTime()));
		buffer.append("\nGPA " + this.GPA);
		buffer.append("\nCreditsEarned " + this.creditsEarned);
		buffer.append("\nSocial Security Number " + this.socialSecurityNum);
		buffer.append("\nCompleted Courses: ");
		for (CompletedCourse course : compCourses) {
			buffer.append(course.toString());
		}

		return buffer.toString();

	}

	// instantiate new CompleteCourse, add it to ArrayList
	public void CompleteCourse(Course c, Grade g) throws NullPointerException,
	InvalidDataException {
		double sum = 0;
		GregorianCalendar completeDate = new GregorianCalendar();
		CompletedCourse aCourse = new CompletedCourse(c.getCourseID(),
				c.getDescription(), c.getNumCredits(), c.getDepartmentID(),
				super.getID(), g, completeDate);
		compCourses.add(aCourse);
		// recalculate the credits & GPA
		this.creditsEarned += c.getNumCredits();
		for (CompletedCourse cc : compCourses) {
			sum += cc.getGrade().getPointValue() * cc.getNumCredits();
		}
		this.GPA = sum / creditsEarned;
		// it was not 100% clear which formula to use to calculate the GPA
	}

	// return a deep copy of completed course
	public CompletedCourse findCompletedCourse(String courseID)
			throws NotFoundException, NullPointerException,
			InvalidDataException {
		boolean found = false;
		for (CompletedCourse aCourse : compCourses) {
			if (aCourse.getCourseID().compareTo(courseID) == 0) {
				found = true;
				return new CompletedCourse(aCourse.getCourseID(),
						aCourse.getDescription(), aCourse.getNumCredits(),
						aCourse.getDepartmentID(), aCourse.getStudentID(),
						aCourse.getGrade(), aCourse.getCompletedDate());
			}
		}
		if (found == false) { // course was not completed
			throw new NotFoundException();
		}
		return null;
	}

	// return the grade earned for a completed course
	public Grade getGradeOfCourse(String courseID) {
		for (CompletedCourse aCourse : compCourses) {
			if (aCourse.getCourseID().compareTo(courseID) == 0) {
				return aCourse.getGrade();
			}
		}
		return null;
	}

	// return an ArrayList of Completed courses that were offered by the
	// department ID
	public ArrayList<CompletedCourse> getCoursesByDepartment(String departmentID) {
		ArrayList<CompletedCourse> deptCompCourses = new ArrayList<CompletedCourse>();
		for (CompletedCourse aCourse : compCourses) {
			if (aCourse.getDepartmentID().compareTo(departmentID) == 0) {
				deptCompCourses.add(aCourse);
			}
		}
		return deptCompCourses;
	}

	// return an ArrayList of CompletedCourses in which the student earned a
	// specific grade
	public ArrayList<CompletedCourse> getCoursesByGrade(Grade g) {
		ArrayList<CompletedCourse> gradeCompCourses = new ArrayList<CompletedCourse>();
		for (CompletedCourse aCourse : compCourses) {
			if (aCourse.getGrade().compareTo(g) == 0) {
				gradeCompCourses.add(aCourse);
			}
		}
		return gradeCompCourses;
	}

	public int compareTo(Student other) {
		return this.getLastName().compareTo(other.getLastName());
	}

}
