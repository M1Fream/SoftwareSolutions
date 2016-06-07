import java.util.Date;

/** it's a student */
public class Student {
	private int myID;
	private String myLastName;
	private String myFirstName;
	private int myGrade;
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
	}
	/** provided only for internal use, should never be used
	 *
	 */
	public Student() { //SHOULD NOT BE INSTANTIATED
		System.out.println("SOMEONE HAS MADE A TERRIBLE MISTAKE");
		System.out.println("INSTANTIATING A DEFAULT STUDENT");
		myID=999999;
		myLastName="THIS IS NOT A STUDENT";
		myFirstName="THIS IS STILL NOT A STUDENT";
		myGrade=0;
	}
	public Student(int ID, String lastName, String firstName, int grade, boolean paid) {
		myID=ID;
		myLastName=lastName;
		myFirstName=firstName;
		myGrade=grade;
	}
	public String toString() {
		return myLastName+", "+myFirstName+", "+myID+", "+myGrade;
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
	public String getOut() {
		return (new Date()).toString() + "," + myLastName + "," + myFirstName + "," + myID + "," + myGrade;
	}
	public boolean equals(Student other) {
		return myID==other.myID;
	}
}
