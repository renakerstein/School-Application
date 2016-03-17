package school;

public class TaughtCourse extends Course {
	private String teacherID;
	private Integer year;
	private Semester semesterID;
	private Section sectionID;

	public TaughtCourse(Course course, String teacherID, Integer year,
			Semester semesterID, Section sectionID)
					throws NullPointerException, InvalidDataException {
		super(course.getCourseID(), course.getDescription(), course
				.getNumCredits(), course.getDepartmentID()); // call the parent
		// class
		// -"Course"
		if (teacherID == null || year == null || semesterID == null
				|| sectionID == null) {
			throw new NullPointerException();
		}
		this.teacherID = teacherID;
		this.year = year;
		this.semesterID = semesterID;
		this.sectionID = sectionID;

	}

	public String getTeacherID() {
		return teacherID;
	}

	public Integer getYear() {
		return year;
	}

	public Semester getSemesterID() {
		return semesterID;
	}

	public Section getSectionID() {
		return sectionID;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("TAUGHT COURSE");
		buffer.append(super.toString());
		buffer.append("Teacher Id: " + this.teacherID);
		buffer.append("Year " + this.year);
		buffer.append("SemesterId " + this.semesterID);
		buffer.append("Section Id: " + this.sectionID);
		return buffer.toString();
	}

	public int compareTo(TaughtCourse other) {
		return this.getCourseID().compareTo(other.getCourseID());
	}
}