import java.util.Comparator;


public class SortByLastName implements Comparator<Student>{

	public int compare(Student stuOne, Student stuTwo) {
		return stuOne.getLastName().compareTo(stuTwo.getLastName());
	}
}