package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A graph with towns and roads
 * @author Daniel Tekabe
 */
public class Graph  implements GraphInterface<Town,Road>{
	
	
	private List<Town> towns =new ArrayList<Town>();
	private int[][] adjacencyMatrix;
	 private Map<Town, Town> predecessors;
	 private Set<Road> roads;
	
	public Graph() {
	    towns = new ArrayList<Town>(); 
	    adjacencyMatrix = new int[0][0]; 
	    predecessors = new HashMap<>();
	    roads = new HashSet<>();
	}
	
	
	
	

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
        for (Road road : roads) {
            if (road.contains(sourceVertex) && road.contains(destinationVertex)) {
                return road;
            }
        }
        return null;
    }

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String roadName) {
        int srcIndex = towns.indexOf(sourceVertex);
        int destIndex = towns.indexOf(destinationVertex);

        if (srcIndex == -1 || destIndex == -1) {
            throw new IllegalArgumentException("One or both vertices are not in the graph.");
        }

        // Update adjacency matrix
        adjacencyMatrix[srcIndex][destIndex] = weight;
        adjacencyMatrix[destIndex][srcIndex] = weight;

        // Create and store the road
        Road road = new Road(sourceVertex, destinationVertex, weight, roadName);
        roads.add(road);

        return road;
    }
	@Override
	public boolean addVertex(Town v) {
		if(v==null) {
			throw new NullPointerException();
		}
		if(this.containsVertex(v)) {
			return false;
		}
		
		towns.add(v);
		resizeAdjacencyMatrix();
		return true;
	}
	private void resizeAdjacencyMatrix() {
        int newSize = towns.size();
        int[][] newMatrix = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                if (i < adjacencyMatrix.length && j < adjacencyMatrix.length) {
                    newMatrix[i][j] = adjacencyMatrix[i][j];
                } else {
                    newMatrix[i][j] = Integer.MAX_VALUE; // Default value for no edge
                }
            }
        }
        adjacencyMatrix=newMatrix;
        }

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
	
		int srcIndex = towns.indexOf(sourceVertex);
        int destIndex = towns.indexOf(destinationVertex);
        
        if(srcIndex==-1 || destIndex==-1) {
        	return false;
        }
        
        return adjacencyMatrix[srcIndex][destIndex]!=Integer.MAX_VALUE;
	}

	@Override
	public boolean containsVertex(Town v) {
		if(v==null) {
			return false;
		}
		return towns.contains(v);
	}

	@Override
	public Set<Road> edgeSet() {
	    return new HashSet<>(roads);
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
        if (vertex == null) {
            throw new NullPointerException("Vertex cannot be null.");
        }
        if (!this.containsVertex(vertex)) {
            throw new IllegalArgumentException("Vertex not found in the graph.");
        }

        Set<Road> vertexEdges = new HashSet<>();
        for (Road road : roads) {
            if (road.contains(vertex)) {
                vertexEdges.add(road);
            }
        }

        return vertexEdges;
    }

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
	 int srcIndex=this.towns.indexOf(sourceVertex);
	 int destIndex=this.towns.indexOf(destinationVertex);
	 
	 if(adjacencyMatrix[srcIndex][destIndex] != Integer.MAX_VALUE) {
		 adjacencyMatrix[srcIndex][destIndex]=Integer.MAX_VALUE;
		 adjacencyMatrix[destIndex][srcIndex] = Integer.MAX_VALUE;
		 return new Road(sourceVertex,destinationVertex,weight,description);
		 }
	 return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		int index= towns.indexOf(v);
		if(index==-1) {
			return false;
		}
		
		towns.remove(index);
		for(int i=0;i<adjacencyMatrix.length;i++) {
			for(int j=0;j<adjacencyMatrix[i].length;j++) {
				adjacencyMatrix[i][j]=Integer.MAX_VALUE;
			}
		}
		resizeAdjacencyMatrix();
		return true;
	}

	@Override
	public Set<Town> vertexSet() {
		return new HashSet<>(towns);
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
	    dijkstraShortestPath(sourceVertex);

	    ArrayList<String> path = new ArrayList<>();
	    Town step = destinationVertex;

	    // Build the path in reverse 
	    while (step != null && predecessors.containsKey(step)) {
	        Town previous = predecessors.get(step);
	        if (previous == null) break;

	        // Retrieve the edge connecting the towns
	        Road connectingRoad = getEdge(previous, step);
	        if (connectingRoad == null) {
	            throw new IllegalStateException("No connecting road between towns");
	        }

	        
	        path.add(0, previous.getName() + " via " + connectingRoad.getName() + " to " + step.getName() 
	                     + " " + connectingRoad.getWeight() + " mi");
	        step = previous;
	    }


	    return path;
	}

		 @Override
		 public void dijkstraShortestPath(Town sourceVertex) {
			    if (!towns.contains(sourceVertex)) {
			        throw new IllegalArgumentException("Source vertex not in graph");
			    }

			    Set<Town> open = new HashSet<>(towns); 
			    Set<Town> closed = new HashSet<>();  
			    Map<Town, Integer> distances = new HashMap<>(); 
			    predecessors.clear(); 

			   
			    for (Town town : towns) {
			        distances.put(town, Integer.MAX_VALUE);
			    }
			    distances.put(sourceVertex, 0);

			    while (!open.isEmpty()) {
			        
			        Town currentVertex = null;
			        int minDistance = Integer.MAX_VALUE;

			        for (Town vertex : open) {
			            int distance = distances.get(vertex);
			            if (distance < minDistance) {
			                minDistance = distance;
			                currentVertex = vertex;
			            }
			        }

			        

			        // Move current vertex from open to closed
			        open.remove(currentVertex);
			        closed.add(currentVertex);

			        // Update distances for adjacent vertices
			        for (Town neighbor : getAdjVerticesInSet(currentVertex, open)) {
			            int edgeWeight = adjacencyMatrix[towns.indexOf(currentVertex)][towns.indexOf(neighbor)];
			            int newDistance = distances.get(currentVertex) + edgeWeight;

			            if (newDistance < distances.get(neighbor)) {
			                distances.put(neighbor, newDistance);
			                predecessors.put(neighbor, currentVertex);
			            }
			        }
			    }
			}
		 private Set<Town> getAdjVerticesInSet(Town vertex, Set<Town> open) {
			    Set<Town> adjVertices = new HashSet<>();
			    int vertexIndex = towns.indexOf(vertex);

			    for (int i = 0; i < adjacencyMatrix[vertexIndex].length; i++) {
			        if (adjacencyMatrix[vertexIndex][i] != Integer.MAX_VALUE && open.contains(towns.get(i))) {
			            adjVertices.add(towns.get(i));
			        }
			    }
			    return adjVertices;
			}
		 
}
		
	

	


