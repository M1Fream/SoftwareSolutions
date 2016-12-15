import java.util.Comparator;


public class SortByGrade implements Comparator<Student>{

	public int compare(Student stuOne, Student stuTwo) {
		return stuOne.getGrade()-stuTwo.getGrade();
	}

}