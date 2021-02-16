import com.company.model.Grade
import com.company.model.Student

/**
 * 
 */

/**
 * @author Jean Carlos Alarcón
 *
 */
class ReserveGrade {
	Integer number
	String parallel
	List<Student> reserveStudents = new ArrayList<Student>()
	List<Student> qualifyStudents = new ArrayList<Student>()
	List<Student> notQualifyStudent = new ArrayList<Student>()
	Integer amount
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.number.toString() + '-' + this.parallel + '-' + this.amount.toString();
	}
	
	public List<Student> getReserveStudents() {
		return reserveStudents;
	}
	
	public void addToReserveStudents(Student addTo) {
		this.reserveStudents.add(addTo);
	}
	
	public List<Student> getQualifyStudents() {
		return qualifyStudents;
	}
	
	public void addToQualifyStudents(Student addTo) {
		this.qualifyStudents.add(addTo);
	}	
}
