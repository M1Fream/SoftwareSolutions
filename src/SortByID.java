import java.util.Comparator;


public class SortByID implements Comparator<Student>{


	public int compare(Student stuOne, Student stuTwo) {
		return stuOne.getID()-stuTwo.getID();
	}

}