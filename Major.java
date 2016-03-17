package school;

public enum Major {
	ACCT("Accounting"), ART("Art"),
	BIOL("Biology"),
	CHEM("Chemistry"), CPSC("Computer Science"),
	ECON("Economics"), EDUC("Education"), ENGL("English"), ENGR("Engineering"),
	FRCH("French"), 
	GEOG("Geology"), GERM("German"), GREE("Greek"), 
	HIST("History"),
	JUDA("Judaic Studies"),
	MATH("Math"), MUSC("Music"),
	NURS("Nursing"),
	PHIL("Philosophy"), PE("Physical Ed"), PHYS("Physics"), POLS("Political Science"), PSYC("Psychology"),
	RELI("Religion"),
	SOCI("Sociology"), SPEE("Speech"),
	UDCD("Undecided");
	
	private String majorName;
	
	private Major(String majorName){
		this.majorName=majorName;
	}
	public String getMajor(){
		return majorName;

	
	}
}
