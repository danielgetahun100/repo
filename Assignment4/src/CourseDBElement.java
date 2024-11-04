/**
 * @author Tekabe
 */
public class CourseDBElement implements Comparable{ 
	private String course_Id;
	private int CRN;
	private int num_of_credit;
	private String room_num;
	private String instructor_name;

	public CourseDBElement(String course_Id,int CRN,int num_of_credit,String room_num,String instructor_name){
		this.course_Id=course_Id;
		this.CRN=CRN;
		this.num_of_credit=num_of_credit;
		this.room_num=room_num;
		this.instructor_name=instructor_name;

	}
	
	public CourseDBElement() {
		this.course_Id = "";
		this.CRN = 0;               
		this.num_of_credit = 0;     
		this.room_num = "";         
		this.instructor_name = "";   
	}
	public int getCRN() {
		return CRN;

	}
	public String getID(){
		return course_Id;
	}
	public String getRoomNum() {
		return room_num;
	}
	@Override
	/**
	 * compares current object to other object
	 */
	public int compareTo(Object o) {
		if(o instanceof CourseDBElement) {
			CourseDBElement other=(CourseDBElement)o;
			return Integer.compare(this.CRN, other.CRN);
		}
		return 0;
	}

	public int getCredits() {
		return num_of_credit;
	}
	public String getcourse() {
		return course_Id;
	}

	public String getInstructorName() {
		return instructor_name;
	}

	public void setCourseId(String course_Id) {
		this.course_Id=course_Id;

	}

	public void setCredits(int num_of_credit) {
		this.num_of_credit=num_of_credit;
	} 
	public void setInstructor(String instructor_name) { 
		this.instructor_name=instructor_name; 
	}
	public void setRoomNum(String room_num) {
		this.room_num=room_num;

	}
	public void setCRN(int crn) {
		this.CRN=crn;
	}


}
