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
	
	/** Initializes the IO class, must be run before using other methods
	 * @throws IOException */
	public static void init() throws IOException {
		Scanner file = null;
		try {
			file = new Scanner(new File("in.mer"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

			outFile = new File(Globals.savePath+"//signout.csv");

		try {
			outFile.createNewFile();
			outFileWriter = new FileWriter(outFile, true);
			outPrintWriter = new PrintWriter(outFileWriter);
		} catch (IOException e) {
			throw e;
		}
		
		//Create Map of Fields (line 1)
		field = new HashMap<String,Integer>();
		int i = 0;
		for(String fieldName: file.nextLine().split(",")){
			field.put(fieldName.trim(), i++);
		}
		
		//Load in student data
		studentData = new ArrayList<ArrayList<String>>();
		while(file.hasNext()){
			ArrayList<String> aStudent = new ArrayList<String>();
			for(String studentField: file.nextLine().split("\",\"")){
				aStudent.add(studentField.replaceAll("\"", "").trim());
			}
			studentData.add(aStudent);
		}
		//Eliminate duplicates
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
	 * @param int ID
	 * @author Mitchell Fream
	 * @return String in
	 * @throws IDoutOfRangeException*/
	public static String get(int ID, String in) throws IDoutOfRangeException {
		for(ArrayList<String> student: studentData){
			if (Integer.parseInt(student.get(field.get("ID")))==ID) {
				return student.get(field.get(in));
			}
			student.get(field.get(in));
		}
		throw new IDoutOfRangeException();
	}
	
	/** wrapper for a PrintStream println
	 * @param String in*/
	public static void write(String in) {
		System.out.println(in);
		outPrintWriter.println(in);
		outPrintWriter.flush();
	}
	public static void write(Student in) {
		write(in.getOut());
	}

	public static void write(ArrayList<Student> in) throws IOException{
		File sortedOutFile = new File(Globals.savePath+"//sortedSignout.csv");
		
		try {
			sortedOutFile.createNewFile();
			outFileWriter = new FileWriter(sortedOutFile, true);
			outPrintWriter = new PrintWriter(outFileWriter);
		} catch (IOException e) {
			throw e;
		}
		
		for(Student s:in){
			write(s);
		}
	}
}