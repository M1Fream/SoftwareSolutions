import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		/*
		 * Dialogs and GUI now handle this
		 * 
		 * Scanner in = new Scanner(System.in);
		 * System.out.print("Enter the password used to stop the program: ");
		 * Globals.pass=in.next();
		 * */
		
		GUI gui = new GUI("Prom sign out");
		//Component a = new Component();
		//a.start();
		while (!Globals.done) {
			try{
				System.out.print("Enter ID: ");
				int ID = in.nextInt(); // do something if this isn't an int
				Student temp = new Student(ID); // student will do it's own database lookups
				System.out.println(temp);
			} catch (IDoutOfRangeException e) {
				gui.badID();
				System.out.println("Out of range");
			} catch (InputMismatchException e) {
				System.out.println("ID too big or contains letters");
				gui.badID();
				in.next(); //this is magic
			}
		}
	}

}
