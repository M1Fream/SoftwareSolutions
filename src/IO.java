import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

/** input and output for the project*/
public class IO {
	
	static Map<String, Integer> field;
	static ArrayList<ArrayList<String>> studentData;
	static File outFile;
	static FileWriter outFileWriter;
	static PrintWriter outPrintWriter;
	
	/** Initializes the IO class, must be run before using other methods*/
	public static void init() {
		Scanner file = null;
		try {
			file = new Scanner(new File("in.mer"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		outFile = new File("signout.csv");
		try {
			outFile.createNewFile();
			outFileWriter = new FileWriter(outFile, true);
			outPrintWriter = new PrintWriter(outFileWriter);
			//outPrintWriter.print("Hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//create Map of Fields (line 1)
		field = new HashMap<String,Integer>();
		int i = 0;
		for(String fieldName: file.nextLine().split(",")){
			field.put(fieldName.trim(), i++);
		}
		//System.out.println(field);
		
		//Load in student data
		studentData = new ArrayList<ArrayList<String>>();
		while(file.hasNext()){
			ArrayList<String> aStudent = new ArrayList<String>();
			for(String studentField: file.nextLine().split("\",\"")){
				aStudent.add(studentField.trim());
			}
			studentData.add(aStudent);
		}
		//eliminate duplicates
		for(i = 0; i < studentData.size();  i++){
			String id = studentData.get(i).get(field.get("ID"));
			for(int j = studentData.size() - 1; j > i; j--){
				String id2 = studentData.get(j).get(field.get("ID"));
				if(id.equals(id2)){
					studentData.remove(j);
				}
			}	
		}
		
	}
	/** gets a field from the merge file 
	 * @param integer ID of the student and the name of the field
	 * @author Mitchell Fream
	 * @return the value of the field*/
	public static String get(int ID, String in) throws IDoutOfRangeException {
		for(ArrayList<String> student: studentData){
			if (Integer.parseInt(student.get(field.get("ID")))==ID) {
				return student.get(field.get(in));
			}
			student.get(field.get(in));
		}
		throw new IDoutOfRangeException();
	}
	/** wrapper for a PrintStream println*/
	public static void write(String in) {
		System.out.println(in);
		outPrintWriter.println(in);
		outPrintWriter.flush();
	}
	public static void write(Student in) {
		write(in.getOut());
	}

}
