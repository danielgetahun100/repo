/**
 * @author Tekabe
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CourseDBStructure<T> implements CourseDBStructureInterface{

	LinkedList<CourseDBElement>[] hashTable;
	CourseDBElement CDE;
	int size;

	public CourseDBStructure(int num_of_course){
		int targetSize = (int) Math.ceil(num_of_course / 1.5);
		while(!isPrime(targetSize)||(targetSize-3)%4!=0) {

			targetSize++;
		}



		size=targetSize;
		hashTable=new LinkedList[size];
	}
	/**
	 * 
	 * @param num
	 * @return
	 */
	private boolean isPrime(int num) {
		if (num <= 1) return false; // 0 and 1 are not prime
		if (num <= 3) return true; // 2 and 3 are prime
		if (num % 2 == 0 || num % 3 == 0) return false; // Check for divisibility by 2 and 3

		for (int i = 5; i * i <= num; i += 6) {
			if (num % i == 0 || num % (i + 2) == 0) {
				return false; // Found a divisor, not prime
			}
		}
		return true; // No divisors found, it's prime
	}
	public CourseDBStructure(String testing,int size){
		this.size=size;
		hashTable=new LinkedList[this.size];
	}

	@Override
	public void add(CourseDBElement element) {
		int code; 
		String valueString=String.valueOf(element.getCRN());
		code=Math.abs(valueString.hashCode()%size);


		LinkedList<CourseDBElement> linkedL=hashTable[code];

		if(linkedL==null) {
			linkedL=new LinkedList<CourseDBElement>();
			hashTable[code]=linkedL;
		}
		for (CourseDBElement existingElement : linkedL) {
			if (existingElement.getCRN() ==element.getCRN() ) {
				existingElement.setCourseId(element.getID());
				existingElement.setCredits(element.getCredits());
				existingElement.setInstructor(element.getInstructorName());
				existingElement.setRoomNum(element.getRoomNum());
				return; // Duplicate found, so return without adding
			}
		}
		linkedL.add(element);




	}

	@Override
	/**
	 * returns element based on crn
	 */
	public CourseDBElement get(int crn) throws IOException {
		String crnString=String.valueOf(crn);
		int code=Math.abs(crnString.hashCode())%size;

		LinkedList<CourseDBElement> linkedL=hashTable[code];

		if(linkedL==null) {
			throw new IOException("No course element found");
		}

		for(CourseDBElement element : linkedL) {


			if(element.getCRN()==crn) {


				element.setCourseId(element.getID()); 
				element.setCredits(element.getCredits()); 
				element.setInstructor(element.getInstructorName()); 
				element.setRoomNum(element.getRoomNum());
				return element;
			}

		}
		throw new IOException("No CourseDBElement found for CRN: " + crn);
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> allCourses = new ArrayList<>();

		for(int i=0; i<size;i++) {

			if(hashTable[i]!=null&&!hashTable[i].isEmpty()) {

				for(int j=0;j<hashTable[i].size();j++) {

					CourseDBElement element=hashTable[i].get(j);
					// Manually format the string as expected
					String formattedCourse = "Course:"+ element.getcourse()+" CRN:" + element.getCRN() +
							" Credits:" + element.getCredits() +
							" Instructor:" + element.getInstructorName() +
							" Room:" + element.getRoomNum();
					allCourses.add("\n" + formattedCourse);
				}      	
			}

		}
		return allCourses;
	}

	@Override
	public int getTableSize() {
		return size;
	}



}
