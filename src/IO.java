import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;


public class IO {
	
	static Map<String, Integer> field;
	static ArrayList<ArrayList<String>> studentData;
	static File outFile;
	static FileWriter outFileWriter;
	static PrintWriter outPrintWriter;
	public static void init() {
		Scanner file = null;
		try {
			file = new Scanner(new File("SMCS10_noGrades.mer"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		outFile = new File("out.txt");
		try {
			outFile.createNewFile();
			outFileWriter = new FileWriter(outFile, true);
			outPrintWriter = new PrintWriter(outFileWriter);
			//outPrintWriter.print("Hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		//System.out.println(studentData.size());
		/*for(ArrayList<String> student: studentData){
			System.out.println(student.get(field.get("ID")));
		}*/
		//eliminate duplicates
		//System.out.println("**********************************************");
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
	public static String get(int ID, String in) throws IDoutOfRangeException {
		for(ArrayList<String> student: studentData){
			if (Integer.parseInt(student.get(field.get("ID")))==ID) {
				return student.get(field.get(in));
			}
			student.get(field.get(in));
		}
		throw new IDoutOfRangeException();
	}
	public static void write(String in) {
		System.out.println(in);
		outPrintWriter.println(in);
		outPrintWriter.flush();
	}
	public static void write(Student in) {
		write(in.getOut());
	}

}
