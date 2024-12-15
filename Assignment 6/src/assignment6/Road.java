package assignment6;

public class Road implements Comparable<Road>{
	
	private Town source;
	private Town destination;
	private int degrees;
	private String name;
	
/**
 * @author Daniel
 * @param source
 * @param destination
 * @param degrees
 * @param name
 */
	public Road(Town source, Town destination, int degrees, String name) {
		
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.name = name;
	}
public Road(Town source, Town destination, String name) {
		
		this.source = source;
		this.destination = destination;
		this.degrees=1;
		this.name = name;
	}


	public Town getSource() {
		return source;
	}

	public void setSource(Town source) {
		this.source = source;
	}

	public Town getDestination() {
		return destination;
	}

	public void setDestination(Town destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return degrees;
	}

	public void setWeight(int degrees) {
		this.degrees = degrees;
	}

	public String getName() {
		return name;
	}

	public void setNames(String names) {
		this.name = names;
	}
	
	public boolean contains(Town town) {
		
		if(destination.equals(town)||source.equals(town)) {
			return true;
		}
		return false;
		
	}
	@Override
	public String toString() {
		return name;
	}
	@Override
	public int compareTo(Road o) {
		return this.name.compareTo(o.name);
	}
	@Override
	public boolean equals(Object r) {
		if(this==r) {
			return true;
		}
		if(r==null || getClass()!= r.getClass()) {
			return false;
			}
		Road rd=(Road)r;
		return  (source.equals(rd.source) && destination.equals(rd.destination)) ||
	               (source.equals(rd.destination) && destination.equals(rd.source));
		
		
		}

}
