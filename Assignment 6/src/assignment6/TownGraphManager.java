package assignment6;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;

/**
 * A manager class to manage the graph
 * @author Daniel Tekabe
 */

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph;
	
	public TownGraphManager() {
		graph = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		try {
			if(graph.addEdge(source, destination, weight, roadName) != null) {
				return true;
			}
		} catch(Exception e) {
			e.getMessage();
		}
		
		return false;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road road = graph.getEdge(source, destination);
		if(road != null) {
			return road.getName();
		} else {
			return null;
		}
	}

	@Override
	public boolean addTown(String v) {
		Town town = new Town(v);
		
		try {
			if(graph.addVertex(town)) {
				return true;
			}
		} catch(Exception e) {
			e.getMessage();
		}
		
		return false;
	}

	@Override
	public Town getTown(String name) {
		if(containsTown(name)) {
			return new Town(name);
		} else {
			return null;
		}
	}

	@Override
	public boolean containsTown(String v) {
		Town town = new Town(v);
		return graph.containsVertex(town);
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		if(graph.containsEdge(source, destination)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> list = new ArrayList<String>();
		
		for(Road road : roads) {
			list.add(road.getName());
		}
		
		Collections.sort(list);
		return list;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		Road removed = graph.removeEdge(source, destination, 1, road);
		
		if(removed == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean deleteTown(String v) {
		Town town = new Town(v);
		return graph.removeVertex(town);
	}

	@Override
	public ArrayList<String> allTowns() {
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> list = new ArrayList<String>();
		
		for(Town town : towns) {
			list.add(town.getName());
		}
		
		Collections.sort(list);
		return list;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		ArrayList<String> path = graph.shortestPath(source, destination);
		return path;
	}

	public void populateTownGraph(File selectedFile) {
		try {
	       
	        FileReader fileReader = new FileReader(selectedFile);
	        Scanner scanner = new Scanner(fileReader);

	        // Read the file line by line
	        while (scanner.hasNextLine()) {
	            String line = scanner.nextLine().trim(); 

	            if (line.isEmpty()) continue; 

	            
	            String[] parts = line.split(";");
	            if (parts.length != 3) {
	                throw new IllegalArgumentException("Invalid line format: " + line);
	            }

	            
	            String[] roadDetails = parts[0].split(",");
	            if (roadDetails.length != 2) {
	                throw new IllegalArgumentException("Invalid road details: " + parts[0]);
	            }

	            String roadName = roadDetails[0];
	            int miles = Integer.parseInt(roadDetails[1]); // Convert miles to an integer

	            
	            String town1Name = parts[1].trim();
	            String town2Name = parts[2].trim();

	            
	            if (!this.containsTown(town1Name)) {
	                this.addTown(town1Name);
	            }
	            if (!this.containsTown(town2Name)) {
	                this.addTown(town2Name);
	            }

	           
	            if (!this.containsRoadConnection(town1Name, town2Name)) {
	                this.addRoad(town1Name, town2Name, miles, roadName);
	            }
	        }

	        
	        scanner.close();
	        fileReader.close();

	    } catch (IOException e) {
	        System.out.println(e.getMessage());
	    } catch (NumberFormatException e) {
	        System.out.println(e.getMessage());
	    }
	}

}