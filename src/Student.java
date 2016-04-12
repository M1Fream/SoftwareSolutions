
public class Student {
	private int myID;
	private String myName;
	private int myGrade;
	private boolean paid;
	public Student(int ID) throws IDoutOfRangeException {
		if (ID<100000 || ID>999999) {
			throw new IDoutOfRangeException();
		}
		myID=ID;
		myName="Bob"; //look up name in database
		myGrade=13; //look up grade in database
		paid=false; //look up in database
		if(myID==328714) {
			System.out.println("Hello master");
		}
	}
	public Student() {
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
		this.paid=paid; //add sanity checks to this code
	}
	public String toString() {
		return myName+", "+myID+", "+myGrade+", paid: "+paid;
	}
}
