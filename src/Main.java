import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the password used to stop the program: ");
		Globals.pass=in.next();
		GUI gui = new GUI();
		while (!Globals.done) {
			try{
				System.out.print("Enter ID: ");
				int ID = in.nextInt(); // do something if this isn't an int
				Student temp = new Student(ID); // student will do it's own database lookups
				System.out.println(temp);
			} catch (IDoutOfRangeException e) {
				gui.IDoutOfRange();
				System.out.println("Out of range");
			} catch (InputMismatchException e) {
				System.out.println("ID too big or contains letters");
				in.next(); //this is magic
			}
		}
	}

}
