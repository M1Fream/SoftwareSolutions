import java.util.Date;


public class Student {
	private int myID;
	private String myName;
	private int myGrade;
	private boolean paid; //Disregard all code pertaining to paid, it was never implemented
	public Student(int ID) throws IDoutOfRangeException {
		if (ID<100000 || ID>999999) {
			throw new IDoutOfRangeException();
		}
		myID=ID;
		myName=IO.get(ID, "FIRST") +" "+ IO.get(ID, "LAST"); //look up name in database
		myGrade=Integer.parseInt(IO.get(ID, "GR")); //look up grade in database
		paid=false; //look up in database // Just kidding i have no idea what to do here
		if(myID==328714) {
			System.out.println("Hello master");
		}
		if(myID==372290){
			System.out.println("Hey GUI-Maker");
		}
	}
	public Student() { //SHOULD NOT BE INSTANTIATED
		System.out.println("SOMEONE HAS MADE A TERRIBLE MISTAKE");
		System.out.println("INSTANTIATING A DEFAULT STUDENT");
		myID=999999;
		myName="THIS IS NOT A STUDENT";
		myGrade=0;
		paid=false;
	}
	public Student(int ID, String name, int grade, boolean paid) {
		myID=ID;
		myName=name;
		myGrade=grade;
		this.paid=paid; //add sanity checks to this code // or not :)
	}
	public String toString() {
		return myName+", "+myID+", "+myGrade+", paid: "+paid;
	}
	public String getName(){
		return myName;
	}
	
	public int getID(){
		return myID;
	}
	public String getOut() {
		return (new Date()).toString() + "," + myName + "," + myID + "," + myGrade;
	}
	public boolean equals(Student other) {
		return myID==other.myID;
	}
}
