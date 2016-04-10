import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner("System.in");
		while (!Globals.done) {
			int ID = in.nextInt(); // do something if this isn't an int
			if(99999<ID && ID<100000) {
				Student temp = new Student(ID); // student will do it's own database lookups
			}
		}
	}

}
