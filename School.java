package school;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class School {
	private String schoolName;
	private Address address;
	private String phoneNumber;
	private ArrayList<Person> StudentsTeachers;
	private ArrayList<Course> courses;
	private ArrayList<Department> departments;

	public School(String schoolName, Address address, String phoneNumber)
			throws NullPointerException, FileNotFoundException,
			InvalidDataException, InsufficientLengthException {
		this(schoolName, address, phoneNumber, null, null, null, null);

	}

	public School(String schoolName, Address address, String phoneNumber,
			String teachFileName, String studentFileName,
			String departmentFileName, String courseFileName)
					throws NullPointerException, FileNotFoundException,
					InvalidDataException, InsufficientLengthException {
		if (schoolName == null || address == null || phoneNumber == null) {
			throw new NullPointerException();
		}
		this.schoolName = schoolName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.StudentsTeachers = new ArrayList<Person>();
		this.courses = new ArrayList<Course>();
		this.departments = new ArrayList<Department>();

		readTeachFile(teachFileName);

		readStudentFile(studentFileName);
		readDepartmentFile(departmentFileName);
		readCourseFile(courseFileName);

	}

	// read in the teacher file
	private void readTeachFile(String teachFileName)
			throws FileNotFoundException, NullPointerException,
			InvalidDataException, InsufficientLengthException {
		Scanner inputFile = new Scanner(new File("teachers.txt"));
		while (inputFile.hasNext()) {
			String teacherID = inputFile.next();
			String firstName = inputFile.next();
			String lastName = inputFile.nextLine();
			String street = inputFile.nextLine();
			String[] parts = inputFile.nextLine().split(",");
			String city = parts[0];
			String state = parts[1];
			String zipCode = inputFile.nextLine();
			String telNumber = inputFile.next();
			Character gender = inputFile.next().charAt(0);
			String hireDate = inputFile.next();
			String birthDate = inputFile.next();
			String employeeType = inputFile.next();
			String departmentCode = inputFile.next();
			String socialSecurityNum = inputFile.next();
			String degree = inputFile.next();
			String major = inputFile.next();
			Double salary = inputFile.nextDouble();

			// convert hire date to a Gregorian calendar
			String[] dateSplit1 = hireDate.split("/");
			GregorianCalendar hDate = new GregorianCalendar(
					Integer.parseInt(dateSplit1[2]),
					Integer.parseInt(dateSplit1[0]) - 1,
					Integer.parseInt(dateSplit1[1]));

			// convert birthdate to Gregorian calendar
			String[] dateSplit2 = birthDate.split("/");
			GregorianCalendar bDate = new GregorianCalendar(
					Integer.parseInt(dateSplit2[2]),
					Integer.parseInt(dateSplit2[0]) - 1,
					Integer.parseInt(dateSplit2[1]));

			// create new Address
			Address address = new Address(street, city, state, zipCode);

			// create a new instance of a teacher
			Teacher aTeacher = new Teacher(teacherID, firstName, lastName,
					null, address, telNumber, gender, hDate, bDate,
					employeeType, departmentCode, socialSecurityNum, degree,
					major, salary);
			StudentsTeachers.add(aTeacher);
		}
		inputFile.close();
	}

	// read in the student file
	private void readStudentFile(String studentFileName)
			throws FileNotFoundException, NullPointerException,
			InsufficientLengthException, InvalidDataException {
		Scanner inputFile = new Scanner(new File(studentFileName));
		while (inputFile.hasNext()) {
			String studentID = inputFile.next();
			String lastName = inputFile.next();
			String firstName = inputFile.next();
			Character midInitial = inputFile.next().charAt(0);
			inputFile.nextLine();
			String street = inputFile.nextLine();
			String[] parts = inputFile.nextLine().split(",");
			String city = parts[0];
			String state = parts[1];
			if (state.length() != 2) {
				state = state.trim();
			}
			String zip = inputFile.nextLine();
			String phoneNumber = inputFile.next();
			Character gender = inputFile.next().charAt(0);
			String major = inputFile.nextLine();
			major = major.trim();
			String birthDate = inputFile.next();
			String enrolledDate = inputFile.next();
			String socialSecurityNum = inputFile.nextLine();
			// create new Address
			Address address = new Address(street, city, state, zip);

			// convert birthdate to a Gregorian calendar
			String[] dateSplit1 = birthDate.split("/");
			GregorianCalendar bDate = new GregorianCalendar(
					Integer.parseInt(dateSplit1[2]),
					Integer.parseInt(dateSplit1[0]) - 1,
					Integer.parseInt(dateSplit1[1]));

			// convert enrolleddate to Gregorian calendar
			String[] dateSplit2 = enrolledDate.split("/");
			GregorianCalendar eDate = new GregorianCalendar(
					Integer.parseInt(dateSplit2[2]),
					Integer.parseInt(dateSplit2[0]) - 1,
					Integer.parseInt(dateSplit2[1]));
			// create new instance of a student
			Student aStudent = new Student(studentID, firstName, lastName,
					midInitial, address, phoneNumber, gender, major, eDate,
					bDate, socialSecurityNum);
			StudentsTeachers.add(aStudent);
		}
		inputFile.close();

	}

	// read in the department file
	private void readDepartmentFile(String departmentFileName)
			throws FileNotFoundException, NullPointerException,
			InsufficientLengthException {
		Scanner inputFile = new Scanner(new File(departmentFileName));
		while (inputFile.hasNext()) {
			String[] parts = inputFile.nextLine().split(";");
			String departmentID = parts[0];
			String departmentName = parts[1];
			String location = parts[2];
			String phoneNumber = parts[3];
			String faxNumber = parts[4];
			String DepartmentChairID = parts[5];

			// create a new instance of department
			Department aDepartment = new Department(departmentID,
					departmentName, location, phoneNumber, faxNumber,
					DepartmentChairID);
			departments.add(aDepartment);
		}
		inputFile.close();
	}

	// read in the course file
	private void readCourseFile(String courseFileName)
			throws FileNotFoundException, NullPointerException,
			InvalidDataException {
		Scanner inputFile = new Scanner(new File(courseFileName));
		while (inputFile.hasNext()) {
			String[] parts = inputFile.nextLine().split(";");
			String courseID = parts[0];
			String description = parts[1];
			String departmentID = parts[2];
			Integer numCredits = Integer.parseInt(parts[3]);

			// create a new instance of course
			Course aCourse = new Course(courseID, description, numCredits,
					departmentID);
			courses.add(aCourse);
		}
		inputFile.close();
	}

	// getters

	public String getPhoneNumber() {
		return phoneNumber;
	}

	// phoneNumber may be modified
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public Address getAddress() {
		return address;
	}

	public ArrayList<Person> getStudentsTeachers() {
		return StudentsTeachers;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Department> getDepartments() {
		return departments;
	}

	// toString()
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("SCHOOL");
		buffer.append("School name: " + this.schoolName);
		buffer.append("\nAddress " + this.address.toString());
		buffer.append("Phone Number " + this.phoneNumber);
		buffer.append("Teachers: ");
		for (Person person : StudentsTeachers) {
			if (person instanceof Teacher) {
				buffer.append(person);
			}
		}
		buffer.append("Students: ");
		for (Person person : StudentsTeachers) {
			if (person instanceof Student) {
				buffer.append(person);
			}
		}

		buffer.append("Courses " + courses.toString());
		buffer.append("Departments " + departments.toString());

		return buffer.toString();
	}

	// add a teacher
	public void addTeacher(String ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Character gender, GregorianCalendar hireDate,
			GregorianCalendar dateOfBirth, String employeeTypeID,
			String departmentID, String socialSecurityNum, String degree,
			String majorID, Double salary) throws NullPointerException,
			InsufficientLengthException, InvalidDataException {
		Teacher aTeacher = new Teacher(ID, firstName, lastName, midInitial,
				address, phoneNumber, gender, hireDate, dateOfBirth,
				employeeTypeID, departmentID, socialSecurityNum, degree,
				majorID, salary);

		// check that this teacher was not already added to the arraylist - by
		// using the Id of the teacher because no two teachers
		// will have the same ID number
		for (Person theTeacher : StudentsTeachers) {
			if (theTeacher.getID().compareTo(ID) == 0) {
				throw new InvalidDataException();
			}
		}
		StudentsTeachers.add(aTeacher); // the exception has not been thrown and
		// therefore this teacher has a unique
		// id
	}

	// add a student
	public void addStudent(String ID, String firstName, String lastName,
			Character midInitial, Address address, String phoneNumber,
			Character gender, String major, GregorianCalendar enrolledDate,
			GregorianCalendar dateOfBirth, String socialSecurityNum)
					throws NullPointerException, InsufficientLengthException,
					InvalidDataException {
		Student aStudent = new Student(ID, firstName, lastName, midInitial,
				address, phoneNumber, gender, major, enrolledDate, dateOfBirth,
				socialSecurityNum);

		// check that this student was not already added to the arraylist
		for (Person theStudent : StudentsTeachers) {
			if (theStudent.getID().compareTo(ID) == 0) {
				throw new InvalidDataException();
			}
		}
		StudentsTeachers.add(aStudent);
	}

	// add a course
	public void addCourse(String courseID, String description,
			Integer numCredits, String departmentID)
					throws NullPointerException, InvalidDataException {
		Course aCourse = new Course(courseID, description, numCredits,
				departmentID);

		// check that this course was not already added to the arraylist
		for (Course theCourse : courses) {
			if (theCourse.getCourseID().compareTo(courseID) == 0) {
				throw new InvalidDataException();

			}
		}
		courses.add(aCourse);
	}

	// add a department
	public void addDepartment(String departmentID, String departmentName,
			String location, String phoneNumber, String faxNumber,
			String departmentChairperson) throws NullPointerException,
			InsufficientLengthException, InvalidDataException {
		Department aDepartment = new Department(departmentID, departmentName,
				location, phoneNumber, faxNumber, departmentChairperson);

		// check that this department was not already added to the arraylist
		for (Department theDepartment : departments) {
			if (theDepartment.getDepartmentID().compareTo(departmentID) == 0) {
				throw new InvalidDataException();
			}
		}
		departments.add(aDepartment);
	}

	// remove a teacher
	public void removeTeacher(String teacherID) throws NotFoundException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					StudentsTeachers.remove(p);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// remove a student
	public void removeStudent(String studentID) throws NotFoundException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					StudentsTeachers.remove(p);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// remove a course
	public void removeCourse(String courseID) throws NotFoundException {
		boolean found = false;
		for (Course c : courses) {
			if (c.getCourseID().compareTo(courseID) == 0) {
				courses.remove(c);
				found = true;
				break;
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// modify teacher last name
	public void modifyTeacherLastName(String teacherID, String newLastName)
			throws NotFoundException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					p.setLastName(newLastName);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// modify teachers address
	public void modifyTeachersAddress(String teacherID, Address address)
			throws NotFoundException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					p.setAddress(address);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// modifyTeacherDegree
	public void modifyTeacherDegree(String teacherID, Degree degree, Major major)
			throws NotFoundException, InvalidDataException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					((Teacher) p).setDegree(degree);
					((Teacher) p).setMajor(major);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// giveTeacherRaise
	public void giveTeacherRaise(String teacherID, Double percent)
			throws NotFoundException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					((Teacher) p).applyRaise(percent);
					found = true;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// give teacher raise
	public void giveTeacherRaise(String teacherID, Integer amount)
			throws NotFoundException, InvalidDataException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					((Teacher) p).setSalary(amount);
					found = true;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// modify student last name
	public void modifyStudentLastName(String studentID, String newLastName)
			throws NotFoundException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					p.setLastName(newLastName);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();

		}

	}

	// modify student phone number
	public void modifyStudentPhoneNumber(String studentID, String newPhoneNumber)
			throws NotFoundException, InsufficientLengthException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					p.setPhoneNumber(newPhoneNumber);
					found = true;
					break;
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// add completed Course
	public void addCompletedCourse(String studentID, String courseID,
			Grade grade) throws NotFoundException, NullPointerException,
			InvalidDataException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					for (Course c : courses) {
						if (c.getCourseID().compareTo(courseID) == 0) {
							((Student) p).CompleteCourse(c, grade);
							found = true;
							break;
						}
					}
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// get student Gpa
	public Double getStudentGPA(String studentID) throws NotFoundException {
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					return ((Student) p).getGPA();

				}
			}
		}

		throw new NotFoundException();

	}

	// get grade of course
	public Grade getGradeOfCourse(String studentID, String courseID)
			throws NotFoundException {
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					return ((Student) p).getGradeOfCourse(courseID);
				}
			}
		}
		throw new NotFoundException();
	}

	// get courses by department
	public ArrayList<CompletedCourse> getCoursesByDepartment(String studentID,
			String departmentID) throws NotFoundException {
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					return ((Student) p).getCoursesByDepartment(departmentID);

				}
			}
		}
		throw new NotFoundException();
	}

	// get courses by grade
	public ArrayList<CompletedCourse> getCoursesByGrade(String studentID,
			Grade g) throws NotFoundException {
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				if (p.getID().compareTo(studentID) == 0) {
					return ((Student) p).getCoursesByGrade(g);
				}
			}
		}
		throw new NotFoundException();
	}

	// get Teachers sorted by name
	public ArrayList<Teacher> getTeachersSortedByName() {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				teachers.add((Teacher) p);
			}
		}
		Collections.sort(teachers, new Comparator<Person>() {
			@Override
			public int compare(Person one, Person other) {
				return one.getLastName().compareTo(other.getLastName());
			}
		});
		return teachers;

	}

	// getTeachers-sort by id
	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				teachers.add((Teacher) p);

			}
		}
		Collections.sort(teachers, new Comparator<Person>() {
			@Override
			public int compare(Person one, Person other) {
				return one.getID().compareTo(other.getID());
			}
		});
		return teachers;

	}

	// getStudents-sort by id
	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				students.add((Student) p);
			}
		}
		Collections.sort(students, new Comparator<Person>() {
			@Override
			public int compare(Person one, Person other) {
				return one.getID().compareTo(other.getID());
			}
		});
		return students;

	}

	// get students by name
	public ArrayList<Student> getStudentsByName() {
		ArrayList<Student> students = new ArrayList<Student>();
		for (Person p : StudentsTeachers) {
			if (p instanceof Student) {
				students.add((Student) p);
			}
		}

		Collections.sort(students, new Comparator<Person>() {
			@Override
			public int compare(Person one, Person other) {
				return one.getLastName().compareTo(other.getLastName());
			}
		});
		return students;

	}

	// add taught course
	public void addTaughtCourse(String teacherID, String courseID,
			Integer year, Semester semester, Section section)
					throws NotFoundException, InvalidDataException {
		boolean found = false;
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					for (Course c : courses) {
						if (c.getCourseID().compareTo(courseID) == 0) {
							((Teacher) p).taughtCourse(c, teacherID, year,
									semester, section);
							found = true;
							break;
						}
					}
				}
			}
		}
		if (found == false) {
			throw new NotFoundException();
		}
	}

	// how many courses teacher taught in a specific semester
	public int howManyCoursesPerSemester(String teacherID, Integer year,
			Semester semester) throws NotFoundException {
		for (Person p : StudentsTeachers) {
			if (p instanceof Teacher) {
				if (p.getID().compareTo(teacherID) == 0) {
					return ((Teacher) p).howManyCoursesPerSemester(year,
							semester);
				}
			}
		}
		throw new NotFoundException();
	}

}
