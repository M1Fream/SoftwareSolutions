import java.util.Date;

/** it's a student */
public class Student {
	private int myID;
	private String myLastName;
	private String myFirstName;
	private int myGrade;
	private String myPeriod;
	/** looks up student data in database through IO class
	 * does basic sanity checking
	 * @param ID
	 * @throws IDoutOfRangeException
	 */
	public Student(int ID) throws IDoutOfRangeException {
		if (ID<100000 || ID>999999) {
			throw new IDoutOfRangeException();
		}
		myID=ID;
		//look up name in database
		myLastName = IO.get(ID, "LAST");
		myFirstName = IO.get(ID, "FIRST"); 
		myGrade = Integer.parseInt(IO.get(ID, "GR")); //look up grade in database
		if(myID==328714) {
			System.out.println("Hello master");
		}
		if(myID==372290){
			System.out.println("Hey GUI-Maker");
		}
		myPeriod = getPeriod();
	}
	/** provided only for internal use, should never be used
	 *
	 */
	public Student() { //SHOULD NOT BE INSTANTIATED
		System.out.println("SOMEONE HAS MADE A TERRIBLE MISTAKE");
		System.out.println("INSTANTIATING A DEFAULT STUDENT");
		myID=999999;
		myLastName = "THIS IS NOT A STUDENT";
		myFirstName = "THIS IS STILL NOT A STUDENT";
		myGrade = 0;
		myPeriod = "0";
	}
	public Student(int ID, String lastName, String firstName, int grade){
		myID = ID;
		myLastName = lastName;
		myFirstName = firstName;
		myGrade = grade;
		myPeriod = getPeriod();
	}
	public String toString() {
		return myLastName+", "+myFirstName+", "+myID+", "+myGrade+", "+myPeriod;
	}
	public String getLastName(){
		return myLastName;
	}
	
	public String getFirstName(){
		return myFirstName;
	}
	
	public int getID(){
		return myID;
	}
	
	public int getGrade(){
		return myGrade;
	}
	
	public String getPeriod(){
		Date myDate = new Date();
		int myHour = myDate.getHours();
		int myMin = myDate.getMinutes();
		
		if(myHour==7 || (myHour==8&&myMin<32)){
			return "1";
		}else if(myHour==8 || (myHour==9&&myMin<23)){
			return "2";
		}else if(myHour==9 || (myHour==10&&myMin<20)){
			return "3";
		}else if(myHour==10 || (myHour==11&&myMin<11)){
			return "4";
		}else if(myHour==11 && myMin<57){
			return "LUNCH";
		}else if(myHour==12 && myMin<48){
			return "5";
		}else if(myHour==12 || (myHour==13&&myMin<39)){
			return "6";
		}else if(myHour==13 || (myHour==14&&myMin<31)){
			return "7";
		}else if(myHour==14 || (myHour==15&&myMin<25)){
			return "8";
		}else{
			return "INVALID PERIOD";
		}
	}
	
	public String getOut() {
		return (new Date()).toString() + "," + myPeriod /*PeriodPanel.getPeriod()*/ + "," + myLastName + "," + myFirstName + "," + myID + "," + myGrade;
	}
	
	public boolean equals(Student other) {
		return myID==other.myID;
	}
}