import java.util.Comparator;

public class GradeComparator implements Comparator<Student> {
	
	@Override
	public int compare(Student o1, Student o2) {
		
		return o2.getGrade() - o1.getGrade();
		
	}

}
