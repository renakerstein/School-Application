package school;

import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ManageSchool {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		School aSchool = null;
		// get the info. to instantiate a school and the text files to read in

		try {

			System.out.println("Enter the school name:");
			String name = keyboard.next();
			System.out.println("street:");
			keyboard.nextLine();
			String st = keyboard.nextLine();

			System.out.println("city:");
			String cit = keyboard.next();
			System.out.println("state:");
			String sta = keyboard.next();
			System.out.println("zip code:");
			String zip = keyboard.next();

			Address a = new Address(st, cit, sta, zip);
			System.out.println("Phone number:");
			String num = keyboard.next();
			aSchool = new School(name, a, num, "teachers.txt", "students1.txt",
					"departments.txt", "courses.txt");
		} catch (FileNotFoundException ex1) {
			System.out.println("file not found-contact IT");
			System.exit(1);
		} catch (NullPointerException ex2) {
			System.out.println(ex2.getMessage());
			System.exit(1);
		} catch (InvalidDataException ex3) {
			System.out.println(ex3.getMessage());
			System.exit(1);
		} catch (InsufficientLengthException ex4) {
			System.out.println(ex4.getMessage());
			System.exit(1);
		}

		// call the menu
		int choice = menu();
		while (choice != 0) {
			switch (choice) {
			// add a new teacher
			case 1:
				Address aAddress;
				try {
					System.out.println("What is your teacher ID?");
					String ID = keyboard.next();
					System.out.println("What is your first name?");
					String fName = keyboard.next();
					System.out.println("What is your last name?");
					String lName = keyboard.next();
					System.out
							.println("Do you have a mid initial - (enter Y or N) ");
					Character answer = keyboard.next().charAt(0);
					keyboard.nextLine();
					Character midInitial;
					if (answer == 'Y' || answer == 'y') {
						System.out.println("Mid initial:");
						midInitial = keyboard.next().charAt(0);
						keyboard.nextLine();
					} else {
						midInitial = null;
					}
					System.out.println("What is your street?");
					String street = keyboard.nextLine();
					System.out.println("What is your city?");
					String city = keyboard.next();
					System.out.println("What is your state?");
					String state = keyboard.next();
					System.out.println("What is your zip?");
					String zipCode = keyboard.next();
					aAddress = new Address(street, city, state, zipCode);
					System.out
							.println("Would you like to enter your phone number?");
					Character a = keyboard.next().charAt(0);
					keyboard.nextLine();
					String number;
					if (a == 'Y' || a == 'y') {
						System.out.println("Phone number:");
						number = keyboard.nextLine();
					} else {
						number = null;
					}

					System.out.println("What is your gender?");
					Character gender = keyboard.next().charAt(0);
					System.out.println("Hire date: (mm//dd/yyyy)");
					String hDate = keyboard.next();
					// convert hire date to a Gregorian calendar
					String[] dateSplit1 = hDate.split("/");
					GregorianCalendar hireDate = new GregorianCalendar(
							Integer.parseInt(dateSplit1[2]),
							Integer.parseInt(dateSplit1[0]) - 1,
							Integer.parseInt(dateSplit1[1]));
					System.out.println("Birthdate: (mm//dd/yyyy) ");
					String birthDate = keyboard.next();
					// convert birthdate to Gregorian calendar
					String[] dateSplit2 = birthDate.split("/");
					GregorianCalendar bDate = new GregorianCalendar(
							Integer.parseInt(dateSplit2[2]),
							Integer.parseInt(dateSplit2[0]) - 1,
							Integer.parseInt(dateSplit2[1]));
					System.out.println("Employee Type: ");
					String employeeType = keyboard.next();
					System.out.println("Department: ");
					String dept = keyboard.next();
					System.out.println("Social Security #: ");
					String social = keyboard.next();
					System.out.println("Degree: ");
					String degree = keyboard.next();
					System.out.println("Major: ");
					String major = keyboard.next();

					System.out.println("Salary: ");
					Double salary = keyboard.nextDouble();
					aSchool.addTeacher(ID, fName, lName, midInitial, aAddress,
							number, gender, hireDate, bDate, employeeType,
							dept, social, degree, major, salary);
					System.out.println("New teacher has been added.");

				} catch (InsufficientLengthException ex1) {
					System.out.println(ex1.getMessage());
					System.exit(1);
				} catch (NullPointerException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				} catch (InvalidDataException e) {
					System.out.println(e.getMessage());
					System.exit(1);
				}
				break;

			// add a new student
			case 2:
				try {
					System.out.println("What is your ID number?");
					String ID = keyboard.next();
					System.out.println("What is your first name?");
					String fName = keyboard.next();
					System.out.println("What is your last name?");
					String lName = keyboard.next();
					System.out.println("Do you have a mid initial ");
					Character answer = keyboard.next().charAt(0);
					keyboard.nextLine();
					Character midInitial;
					if (answer == 'Y' || answer == 'y') {
						System.out.println("Mid initial:");
						midInitial = keyboard.next().charAt(0);
						keyboard.nextLine();
					} else {
						midInitial = null;
					}
					System.out.println("What is your street?");
					String street = keyboard.nextLine();
					System.out.println("What is your city?");
					String city = keyboard.next();
					System.out.println("What is your state?");
					String state = keyboard.next();
					System.out.println("What is your zip?");
					String zipCode = keyboard.next();
					Address add = new Address(street, city, state, zipCode);
					System.out
							.println("Would you like to enter your phone number?");
					Character a = keyboard.next().charAt(0);
					keyboard.nextLine();
					String number;
					if (a == 'Y' || a == 'y') {
						System.out.println("Phone number:");
						number = keyboard.nextLine();
					} else {
						number = null;
					}
					System.out.println("What is your gender?");
					Character gender = keyboard.next().charAt(0);
					System.out.println("Have you decided on your major? ");
					Character ans = keyboard.next().charAt(0);
					keyboard.nextLine();
					String major;
					if (ans == 'Y' || ans == 'y') {
						System.out.println("Major:");
						major = keyboard.next();

					} else {
						major = null;
					}
					System.out.println("Enrolled date: (mm//dd/yyyy)");
					String enrolledD = keyboard.next();
					// convert hire date to a Gregorian calendar
					String[] dateSplit1 = enrolledD.split("/");
					GregorianCalendar enrolledDate = new GregorianCalendar(
							Integer.parseInt(dateSplit1[2]),
							Integer.parseInt(dateSplit1[0]) - 1,
							Integer.parseInt(dateSplit1[1]));
					System.out.println("Birthdate: ");
					String birthDate = keyboard.next();
					// convert birthdate to Gregorian calendar
					String[] dateSplit2 = birthDate.split("/");
					GregorianCalendar bDate = new GregorianCalendar(
							Integer.parseInt(dateSplit2[2]),
							Integer.parseInt(dateSplit2[0]) - 1,
							Integer.parseInt(dateSplit2[1]));
					System.out.println("Social Security #: ");
					String social = keyboard.next();
					aSchool.addStudent(ID, fName, lName, midInitial, add,
							number, gender, major, enrolledDate, bDate, social);
					System.out.println("New student has been added.");
				} catch (NullPointerException | InvalidDataException
						| InsufficientLengthException e) {

					System.out.println(e.getMessage());
				}
				break;
			// Add a new course
			case 3:
				System.out.println("Course ID: ");
				String courseID = keyboard.next();
				System.out.println("Description: ");
				String desc = keyboard.next();
				System.out.println("Number of Credits");
				Integer credits = keyboard.nextInt();
				System.out.println("Department ID: ");
				String deptID = keyboard.next();
				try {
					aSchool.addCourse(courseID, desc, credits, deptID);
					System.out.println("New course has been added.");
				} catch (NullPointerException | InvalidDataException e1) {
					System.out.println(e1.getMessage());
				}
				break;
			// Add a new department
			case 4:
				System.out.println("Department ID: ");
				String ID = keyboard.next();
				System.out.println("Department name: ");
				String name = keyboard.next();
				System.out
						.println("Would you like to include the location of the department?");
				Character answer = keyboard.next().charAt(0);
				keyboard.nextLine();
				String location;
				if (answer == 'Y' || answer == 'y') {
					System.out.println("Location:");
					location = keyboard.nextLine();
				} else {
					location = null;
				}
				System.out.println("Would you like to enter the phone number?");
				Character a = keyboard.next().charAt(0);
				keyboard.nextLine();
				String num;
				if (a == 'Y' || a == 'y') {
					System.out.println("Phone number:");
					num = keyboard.nextLine();
					keyboard.nextLine();
				} else {
					num = null;
				}
				System.out.println("Would you like to enter the fax number?");
				Character an = keyboard.next().charAt(0);
				keyboard.nextLine();
				String fax;
				if (an == 'Y' || an == 'y') {
					System.out.println("fax number:");
					fax = keyboard.next();
				} else {
					fax = null;
				}
				System.out
				.println("Would you like to include the department chair?");
				Character ans = keyboard.next().charAt(0);
				keyboard.nextLine();
				String deptChair;
				if (ans == 'Y' || ans == 'y') {
					System.out.println("Department chair: ");
					deptChair = keyboard.next();
				} else {
					deptChair = null;
				}

				try {
					aSchool.addDepartment(ID, name, location, num, fax,
							deptChair);
					System.out.println("New department has been added.");
				} catch (NullPointerException | InsufficientLengthException
						| InvalidDataException e) {
					System.out.println(e.getMessage());
				}
				break;
			// Remove a teacher
			case 5:
				System.out.println("What is the teacher ID?");
				String teacherID = keyboard.next();
				try {
					aSchool.removeTeacher(teacherID);
					System.out.println("Teacher has been removed.");
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			// remove a student
			case 6:
				System.out.println("What is your student ID?");
				String studentID = keyboard.next();
				try {
					aSchool.removeStudent(studentID);
					System.out.println("Student has been removed.");
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			// remove a course
			case 7:
				System.out.println("What is your course ID?");
				String cID = keyboard.next();
				try {
					aSchool.removeCourse(cID);
					System.out.println("Course has been removed.");
				} catch (NotFoundException ex2) {
					System.out.println(ex2.getMessage());
				}
				break;

			// Modify a teacher's last name
			case 8:
				System.out.println("What is the teacher ID?");
				String tID = keyboard.next();
				System.out.println("What is the new last name?");
				String newLastName = keyboard.next();
				try {
					aSchool.modifyTeacherLastName(tID, newLastName);
					for (Person p : aSchool.getStudentsTeachers()) {
						if (p instanceof Teacher) {
							if (p.getID().compareTo(tID) == 0) {
								System.out
										.println("The last name has been changed to "
												+ p.getLastName());
							}
						}
					}
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			// Modify a teacher's address
			case 9:
				try {
					System.out.println("What is the teacher ID?");
					String teachID = keyboard.next();
					keyboard.nextLine();
					System.out.println("Street: ");
					String st = keyboard.nextLine();
					keyboard.nextLine();
					System.out.println("City: ");
					String city = keyboard.next();
					System.out.println("State: ");
					String state = keyboard.next();
					System.out.println("Zip Code");
					String zipCode = keyboard.next();
					Address add = new Address(st, city, state, zipCode);
					aSchool.modifyTeachersAddress(teachID, add);
					for (Person p : aSchool.getStudentsTeachers()) {
						if (p instanceof Teacher) {
							if (p.getID().compareTo(teachID) == 0) {
								System.out
										.println("The address has been changed to "
												+ p.getAddress().toString());
							}
						}
					}
				} catch (NullPointerException | InvalidDataException
						| InsufficientLengthException ex5) {
					System.out.println(ex5.getMessage());
				} catch (NotFoundException ex6) {
					System.out.println(ex6.getMessage());
				}
				break;

			// Modify teacher's degree
			case 10:
				// if the user enters an invalid value for an enumerated type,
				// it will set it to null
				System.out.println("What is the teacher's ID?");
				String teachID = keyboard.next();
				System.out.println("What is your new degree?");
				String degree = keyboard.next();
				Degree d = isDegreeValid(degree);
				while (d == null) {
					System.out.println("Invalid -reenter:");
					degree = keyboard.next().toUpperCase();
					d = isDegreeValid(degree);

				}
				System.out.println("What is your new major?");
				String major = keyboard.next();
				Major m = isMajorIdValid(major);
				while (m == null) {
					System.out.println("Invalid -reenter:");
					major = keyboard.next().toUpperCase();
					m = isMajorIdValid(major);

				}
				try {
					aSchool.modifyTeacherDegree(teachID, d, m);
					for (Person p : aSchool.getStudentsTeachers()) {
						if (p instanceof Teacher) {
							if (p.getID().compareTo(teachID) == 0) {
								System.out
										.println("The teacher's degree has been changed to "
												+ ((Teacher) p).getDegree());
							}
						}
					}
				} catch (NotFoundException | InvalidDataException ex1) {
					System.out.println(ex1.getMessage());
				}
				break;

			// give teacher raise
			case 11:
				System.out.println("What is the teacher's ID?");
				String teachersID = keyboard.next();
				System.out
						.println("Would you like to apply raise by percentage or by amount?");
				String answ = keyboard.next().toLowerCase();
				if (answ.equalsIgnoreCase("amount")) {
					System.out.println("Amount:");
					Integer amount = keyboard.nextInt();
					try {
						aSchool.giveTeacherRaise(teachersID, amount);
					} catch (NotFoundException e) {
						System.out.println(e.getMessage());
					} catch (InvalidDataException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out
							.println("By what percentage is the salary being raised?");
					Double percent = keyboard.nextDouble();
					Double per = percent / 100;
					try {
						aSchool.giveTeacherRaise(teachersID, per);
						for (Person p : aSchool.getStudentsTeachers()) {
							if (p instanceof Teacher) {
								if (p.getID().compareTo(teachersID) == 0) {
									System.out
											.println("The teacher's new salary is "
													+ ((Teacher) p).getSalary());
								}
							}
						}
					} catch (NotFoundException ex1) {
						System.out.println(ex1.getMessage());
					}
				}
				break;

			// modify student's last name
			case 12:
				System.out.println("What is the student's ID?");
				String stuID = keyboard.next();
				System.out.println("What is the new last name?");
				String newName = keyboard.next();
				try {
					aSchool.modifyStudentLastName(stuID, newName);
					for (Person p : aSchool.getStudentsTeachers()) {
						if (p instanceof Student) {
							if (p.getID().compareTo(stuID) == 0) {
								System.out
										.println("The last name has been changed to "
												+ p.getLastName());
							}
						}
					}
				} catch (NotFoundException ex2) {
					System.out.println(ex2.getMessage());
				}
				break;

			// modify student's phone number
			case 13:
				System.out.println("What is the student's ID?");
				String studentsID = keyboard.next();
				System.out.println("What is the new phone number?");
				String newNum = keyboard.next();
				try {
					aSchool.modifyStudentPhoneNumber(studentsID, newNum);
					for (Person p : aSchool.getStudentsTeachers()) {
						if (p instanceof Student) {
							if (p.getID().compareTo(studentsID) == 0) {
								System.out
										.println("The phone number has been changed to "
												+ p.getPhoneNumber());
							}
						}
					}
					System.out.println("Phone number has been changed in file");
				} catch (NotFoundException ex2) {
					System.out.println(ex2.getMessage());
				} catch (InsufficientLengthException ex3) {
					System.out.println(ex3.getMessage());
				}
				break;

			// add a completed course
			case 14:
				System.out.println("What is the student's ID?");
				String sID = keyboard.next();
				System.out.println("What is the course ID?");
				String coursesID = keyboard.next().toUpperCase();
				System.out
						.println("What is the grade you received in this course?");
				String grade = keyboard.next().toUpperCase();
				Grade g = isGradeValid(grade);
				while (g == null) {
					System.out.println("Invalid -reenter:");
					grade = keyboard.next().toUpperCase();
					g = isGradeValid(grade);

				}
				try {
					aSchool.addCompletedCourse(sID, coursesID, g);
					System.out.println("Comleted course " + coursesID
							+ " has been added");
				} catch (NullPointerException | NotFoundException
						| InvalidDataException e) {
					System.out.println(e.getMessage());
				}
				break;

			// get student GPA
			case 15:
				System.out.println("What is the student's ID?");
				String stu = keyboard.next();
				try {
					System.out.println("GPA " + aSchool.getStudentGPA(stu));
				} catch (NotFoundException ex4) {
					System.out.println(ex4.getMessage());
				}
				break;

			// get grade of course
			case 16:
				System.out.println("What is the student's ID?");
				String sId = keyboard.next();
				System.out.println("What is the course ID?");
				String cId = keyboard.next().toUpperCase();
				try {
					System.out.println("Grade: "
							+ aSchool.getGradeOfCourse(sId, cId));
				} catch (NotFoundException ex5) {
					System.out.println(ex5.getMessage());
				}
				break;

			// get courses by department
			case 17:
				System.out.println("What is the student's ID?");
				String studentId = keyboard.next();
				System.out.println("What is the department ID?");
				String deptId = keyboard.next().toUpperCase();
				try {
					System.out.println("Completed courses in department "
							+ deptId
							+ aSchool.getCoursesByDepartment(studentId, deptId)
									.toString());
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			// get courses by grade
			case 18:
				System.out.println("What is the student's ID?");
				String id = keyboard.next();
				System.out.println("Grade: ");
				String theGrade = keyboard.next().toUpperCase();
				Grade gr = isGradeValid(theGrade);
				while (gr == null) {
					System.out.println("Invalid -reenter:");
					theGrade = keyboard.next().toUpperCase();
					gr = isGradeValid(theGrade);

				}
				try {
					System.out.println("Courses by grade "
							+ aSchool.getCoursesByGrade(id, gr).toString());
				} catch (NotFoundException ex9) {
					System.out.println(ex9.getMessage());
				}
				break;

			// get teachers sorted by name
			case 19:
				System.out.println("Teachers sorted by last name: "
						+ aSchool.getTeachersSortedByName().toString());
				break;

			// get teachers
			case 20:
				System.out.println("Teachers sorted by ID: "
						+ aSchool.getTeachers().toString());
				break;

			// get students
			case 21:
				System.out.println("Students sorted by ID: "
						+ aSchool.getStudents().toString());

				break;

			// get students by name
			case 22:
				System.out.println("Students sorted by last name: "
						+ aSchool.getStudentsByName().toString());
				break;

			// add taught course
			case 23:
				// if the user enters an invalid value for an enumerated type,
				// it will set it to null
				System.out.println("What is the teacher's ID?");
				String teacher = keyboard.next();
				System.out.println("What is the course ID?");
				String course = keyboard.next().toUpperCase();
				System.out.println("What is the year the course was taught?");
				Integer yr = keyboard.nextInt();
				System.out.println("What semester?");
				String semester = keyboard.next().toUpperCase();
				Semester s = isSemesterValid(semester);
				while (s == null) {
					System.out.println("Invalid-reenter:");
					semester = keyboard.next().toUpperCase();
					s = isSemesterValid(semester);
				}
				System.out.println("What section is the course under?");
				String sect = keyboard.next().toUpperCase();
				Section sec = isSectionValid(sect);
				while (sec == null) {
					System.out.println("Invalid-reenter:");
					sect = keyboard.next().toUpperCase();
					sec = isSectionValid(sect);
				}
				try {
					aSchool.addTaughtCourse(teacher, course, yr, s, sec);
				} catch (NotFoundException ex1) {
					System.out.println(ex1.getMessage());
				} catch (InvalidDataException ex2) {
					System.out.println(ex2.getMessage());
				}
				break;

			// how many courses per semester
			case 24:
				System.out.println("Teacher ID");
				String teach = keyboard.next();
				System.out.println("Year");
				Integer year = keyboard.nextInt();
				System.out.println("Semester");
				String semest = keyboard.next().toUpperCase();
				Semester se = isSemesterValid(semest);
				while (se == null) {
					System.out.println("Invalid-reenter:");
					semest = keyboard.next().toUpperCase();
					se = isSemesterValid(semest);
				}
				try {
					System.out.println(aSchool.howManyCoursesPerSemester(teach,
							year, se));
				} catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;

			// exit
			case 25:
				System.out.println("Have a good day!");
				keyboard.close();
				System.exit(0);
				break;
			}
			choice = menu();
		}

	}

	public static int menu() {
		Scanner keyboard = new Scanner(System.in);
		int choice;
		System.out.println("Please enter your choice: ");
		do {
			System.out
					.println("1.  "
							+ "Add a new teacher"
							+ "\n2.  Add a new student"
							+ "\n3.  Add a new course"
							+ "\n4.  Add a new department"
							+ "\n5.  Remove a teacher "
							+ "\n6.  Remove a student "
							+ "\n7.  Remove a course"
							+ "\n8.  Modify a teacher's last name "
							+ "\n9.  Modify a teacher's address "
							+ "\n10. Modify a teacher's degree "
							+ "\n11. Give Teacher a raise "
							+ "\n12. Modify a student's last name "
							+ "\n13. Modify a student's phone number"
							+ "\n14. Add a completed course "
							+ "\n15. Get student GPA "
							+ "\n16. Get grade of a course "
							+ "\n17. Get courses by department "
							+ "\n18. Get courses by grade "
							+ "\n19. Get teachers sorted by name "
							+ "\n20. Get teachers"
							+ "\n21. Get students "
							+ "\n22. Get students by name "
							+ "\n23. Add a taught course "
							+ "\n24. See how many courses a teacher taught per a semester"
							+ "\n25. Exit application");
			choice = keyboard.nextInt();
		} while (choice < 0 || choice > 25);

		return choice;

	}

	private static Grade isGradeValid(String grade) {
		for (Grade g : Grade.values()) {
			if (g.name().equalsIgnoreCase(grade)) {
				return g;
			}
		}
		return null;
	}

	private static Semester isSemesterValid(String semester) {
		for (Semester se : Semester.values()) {
			if (se.name().equalsIgnoreCase(semester)) {
				return se;
			}
		}
		return null;
	}

	private static Section isSectionValid(String sectionID) {
		for (Section s : Section.values()) {
			if (s.name().equalsIgnoreCase(sectionID)) {
				return s;
			}
		}
		return null;
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