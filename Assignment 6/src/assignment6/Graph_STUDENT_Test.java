package assignment6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Graph_STUDENT_Test {

	private Graph graph;
    private Town townA, townB, townC, townD;
    private Road roadAB, roadBC;

    @BeforeEach
    void setUp() {
        graph = new Graph();

        townA = new Town("TownA");
        townB = new Town("TownB");
        townC = new Town("TownC");
        townD = new Town("TownD");

        graph.addVertex(townA);
        graph.addVertex(townB);
        graph.addVertex(townC);
    }

    @Test
    void testAddVertex() {
        assertTrue(graph.addVertex(townD)); 
        assertFalse(graph.addVertex(townA)); 
    }

    @Test
    void testContainsVertex() {
        assertTrue(graph.containsVertex(townA));
        assertFalse(graph.containsVertex(townD)); 
    }

    @Test
    void testAddEdge() {
        roadAB = graph.addEdge(townA, townB, 5, "RoadAB");
        assertNotNull(roadAB);
        assertEquals(5, roadAB.getWeight());
        assertEquals("RoadAB", roadAB.getName());
    }

    @Test
    void testContainsEdge() {
        graph.addEdge(townA, townB, 5, "RoadAB");
        assertTrue(graph.containsEdge(townA, townB));
        assertFalse(graph.containsEdge(townA, townC));
    }

    @Test
    void testGetEdge() {
        roadAB = graph.addEdge(townA, townB, 5, "RoadAB");
        assertEquals(roadAB, graph.getEdge(townA, townB));
    }

    @Test
    void testRemoveEdge() {
        graph.addEdge(townA, townB, 5, "RoadAB");
        Road removedRoad = graph.removeEdge(townA, townB, 5, "RoadAB");
        assertFalse(graph.containsEdge(townA, townB));
    }

    @Test
    void testRemoveVertex() {
        assertTrue(graph.removeVertex(townA));
        assertFalse(graph.containsVertex(townA));
        assertFalse(graph.removeVertex(townD)); 
    }

    @Test
    void testVertexSet() {
        Set<Town> vertices = graph.vertexSet();
        assertEquals(3, vertices.size());
        assertTrue(vertices.contains(townA));
        assertTrue(vertices.contains(townB));
        assertTrue(vertices.contains(townC));
    }

    @Test
    void testEdgeSet() {
        graph.addEdge(townA, townB, 5, "RoadAB");
        graph.addEdge(townB, townC, 10, "RoadBC");

        Set<Road> edges = graph.edgeSet();
        assertEquals(2, edges.size());
    }

    @Test
    void testEdgesOf() {
        graph.addEdge(townA, townB, 5, "RoadAB");
        graph.addEdge(townA, townC, 10, "RoadAC");

        Set<Road> edges = graph.edgesOf(townA);
        assertEquals(2, edges.size());
    }

    @Test
    void testShortestPath() {
        graph.addEdge(townA, townB, 5, "RoadAB");
        graph.addEdge(townB, townC, 10, "RoadBC");
        graph.addEdge(townA, townC, 20, "RoadAC");

        ArrayList<String> path = graph.shortestPath(townA, townC);
        assertEquals(2, path.size());
        assertEquals("TownA via RoadAB to TownB 5 mi", path.get(0));
        assertEquals("TownB via RoadBC to TownC 10 mi", path.get(1));
    }
}
