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
		gui.main();
	}

}
