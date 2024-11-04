import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 
 */
public class CourseDBManager implements CourseDBManagerInterface{
	
	private CourseDBStructure Manage;
	   private int tableSize;
	   private LinkedList<CourseDBElement>[] hashTable;
	
	public CourseDBManager(){
		Manage= new CourseDBStructure(260);
	}
	public CourseDBManager(int num_of_courses){ 
		Manage=new CourseDBStructure(num_of_courses);
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		
		CourseDBElement courses=new CourseDBElement(id,crn,credits,roomNum, instructor);
		
		Manage.add(courses);
		
	}

	@Override
	public CourseDBElement get(int crn) {
	
		try {
			return Manage.get(crn);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		 Scanner scan= new Scanner(input);
		  
		 String output;
		 String[] parts;
		 CourseDBElement elem;
		 int crn;
		 int credit;
		 while(scan.hasNext()) {
			 output=scan.nextLine();
			 parts=output.split(" ", 5);
			 
			 
			 crn= Integer.parseInt(parts[1]);
			 credit=Integer.parseInt(parts[2]);
			 
			 elem=new CourseDBElement(parts[0],crn,credit,parts[3],parts[4]);
			 Manage.add(elem);
			 
		 }
	
	}

	@Override
	public ArrayList<String> showAll() {
		return Manage.showAll();
		
	}

}
