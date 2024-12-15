package assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * 
 * @author Daniel
 */
public class Town implements Comparable<Town>{
	String name;
	List<Town> adjacentTowns;
	
	public Town(String name) {
		this.name=name;
	}
	public Town(Town templateTown) {
		this.name=templateTown.name;
		this.adjacentTowns=new ArrayList<>(templateTown.adjacentTowns);
		
	}
	
	public String getName() {
		return this.name;
	}
	

	@Override
	public int compareTo(Town o) {
		return this.name.compareTo(o.name);
	}
	
	@Override
	public String toString() {
		
		return this.name;
		
	}

	@Override 
	public int hashCode() {
		if(name==null) {
			
		}
		return Objects.hash(name);
		}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null || getClass()!=obj.getClass()) {
			return false;
		}
		Town pass=(Town)obj;
		return this.name.equals(pass.name);
		
	}

	
}
