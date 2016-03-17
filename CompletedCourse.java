package school;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompletedCourse extends Course {
	private String studentID;
	private Grade grade;
	private GregorianCalendar completedDate;

	public CompletedCourse(String courseID, String description,
			Integer numCredits, String departmentID, String studentID,
			Grade grade, GregorianCalendar completedDate)
					throws NullPointerException, InvalidDataException {
		super(courseID, description, numCredits, departmentID);
		this.studentID = studentID;
		this.grade = grade;
		this.completedDate = new GregorianCalendar();
	}

	public String getStudentID() {
		return studentID;
	}

	public Grade getGrade() {
		return grade;
	}

	// return a deep copy
	public GregorianCalendar getCompletedDate() {
		GregorianCalendar date = new GregorianCalendar(
				completedDate.get(Calendar.YEAR),
				completedDate.get(Calendar.MONTH),
				completedDate.get(Calendar.DAY_OF_MONTH));
		return date;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");
		buffer.append(super.toString());
		buffer.append("\nStudent ID " + this.studentID);
		buffer.append("\nGrade " + this.grade);
		buffer.append("\nCompleted date "
				+ formatter.format(this.completedDate.getTime()));
		return buffer.toString();
	}
}
