/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;
import java.util.Set;

/**
 * Graph class. This class will represent a rail way graph (map)
 *
 * @author 184510
 * @version April 2019
 */
public class Graph {

    //Variables that will be used within that class.
    private ArrayList<Vertex> verticies;
    private ArrayList<Edge> edges;

    /**
     * This constructor will initialise a new graph object with the specified
     * vertices and edges
     *
     * @param vertices
     * @param edges
     */
    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
        this.verticies = vertices;
        this.edges = edges;
    }

    /**
     * This constructor will initialise a new graph with no parameters
     */
    public Graph() {
        verticies = new ArrayList<>();
        edges = new ArrayList<>();
    }

    /**
     * Insert a new vertex with name n into the graph. Return the new vertex
     * object
     *
     * @param n String
     * @return the new Vertex
     */
    public Vertex insertVertex(String n) {
        Vertex station = new Vertex(n);
        verticies.add(station);
        return station;
    }

    /**
     * Remove the given vertex from the graph. The name of the vertex is
     * returned, or null if the graph does not contain the vertex.
     *
     * @param v Vertex
     * @return the name of the vertex or null.
     */
    public String removeVertex(Vertex v) {
        if (verticies.remove(v)) {
            incidentEdges(v).forEach((Edge t) -> {
                edges.remove(t);
            });
            return v.getStationName();
        }
        return null;
    }

    /**
     * Build a new edge with end vertices v and w and name n and insert into the
     * graph. Return the new Edge object. NB: The vertices u and v must already
     * be in the graph.
     *
     * @param v Vertex
     * @param w Vertex
     * @param n String
     * @return the new Edge
     */
    public Edge insertEdge(Vertex v, Vertex w, String n) {
        Edge line = new Edge(v, w, n);
        edges.add(line);
        return line;
    }

    /**
     * Remove the edge e from the graph. The name of the edge is returned (or
     * null if the edge is not in the graph).
     *
     * @param e the edge to be removed
     * @return the name or null if e does not exist
     */
    public String removeEdge(Edge e) {
        if (edges.remove(e)) {
            return e.getEdgeName();
        }
        return null;
    }

    /**
     * Return the endpoint vertex of edge e that is 'opposite' its other
     * endpoint vertex v. Return null if e does not exist or does not have
     * endpoint v
     *
     * @param e Edge
     * @param v Vertex
     * @return a vertex or null
     */
    public Vertex opposite(Edge e, Vertex v) {
        return e.getNameOfOtherStation(v);
    }

    /**
     * Return a collection containing of all of the vertices in the graph.
     *
     * @return a list of vertices
     */
    public ArrayList<Vertex> vertices() {
        return verticies;
    }

    /**
     * Return a collection of all of the edges in the graph.
     *
     * @return a list of edges
     */
    public ArrayList<Edge> edges() {
        return edges;
    }

    /**
     * Checks whether two vertices are adjacent (i.e. joined by a single edge)
     * or not.
     *
     * @param v a vertex
     * @param w a vertex
     * @return true if v and we are adjacent and false otherwise.
     *
     */
    public boolean areAdjacent(Vertex v, Vertex w) {
        int i = 0;
        boolean found = false;
        while (i < edges.size() && found != true) {
            if (edges.get(i).getStationNameV() == v && edges.get(i).getStationNameW() == w) {
                found = true;
            } else if (edges.get(i).getStationNameV() == w && edges.get(i).getStationNameW() == v) {
                found = true;
            }
            i = i + 1;
        }
        return found;
    }

    /**
     * Finds and returns the set of edges that are incident to a given vertex.
     *
     * @param v the vertex
     * @return a list of edges
     */
    public ArrayList<Edge> incidentEdges(Vertex v) {
        ArrayList<Edge> incidentEdges = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStationNameV().equals(v) || edges.get(i).getStationNameW().equals(v)) {
                incidentEdges.add(edges.get(i));
            }
        }
        return incidentEdges;
    }

    /**
     * Rename vertex v as n; returns the old vertex name
     *
     * @param v a vertex
     * @param n the new name
     * @return the old edge name
     */
    public String rename(Vertex v, String n) {
        String oldName = v.getStationName();
        verticies.get(verticies.indexOf(v)).setStationName(n);
        return oldName;
    }

    /**
     * Rename edge e as n; returns the old edge name
     *
     * @param e an edge
     * @param n the new name
     * @return the old edge name
     */
    public String rename(Edge e, String n) {
        String oldName = e.getEdgeName();
        edges.get(edges.indexOf(e)).setEdgeName(n);
        return oldName;
    }

    /**
     * This method will print out names of all the stations visited in order of
     * breadth-first traversal
     *
     * @param v Vertex
     */
    public void bftraverse(Vertex v) {
        ArrayList<Vertex> path = allReachable(v);
        for (Vertex vertex : path) {
            System.out.println(vertex.getStationName());
        }
    }

    /**
     * This method will print out names of all the stations names.
     */
    public void bftraverse() {
        for (int i = 0; i < verticies.size(); i++) {
            System.out.println(verticies.get(i).getStationName());
        }
    }

    /**
     * This method will return a list of all of the stations that can be reached
     * by rail when starting from v
     *
     * @param v Vertex
     * @return visited stations
     */
    public ArrayList<Vertex> allReachable(Vertex v) {
        Queue<Vertex> s = new LinkedList<>();
        ArrayList<Vertex> visited = new ArrayList<>();
        s.add(v);
        while (!s.isEmpty()) {
            Vertex current = s.remove();
            ArrayList<Edge> incident = incidentEdges(current);
            for (Edge edge : incident) {
                Vertex w = opposite(edge, v);
                if (!visited.contains(w)) {
                    visited.add(w);
                    s.add(w);
                }
            }
        }
        return visited;
    }

    /**
     * This method will return true if all stations are reachable and false if
     * not
     *
     * @return true or false depending if all stations are reachable
     */
    public boolean allConnected() {
        Vertex v = verticies.get(0);
        return allReachable(v).size() == verticies.size();
    }
    
    /**
     * This method back tracks the route
     * @param u
     * @param v
     * @return 
     */
    public ArrayList<Edge> mostDirectRoute(Vertex u, Vertex v) {
        Queue<Vertex> s = new LinkedList<>();
        ArrayList<Vertex> visited = new ArrayList<>();
        Edge[] paths = new Edge[verticies.size()];
        s.add(v);
        while (!s.isEmpty()) {
            Vertex current = s.remove();
            ArrayList<Edge> incident = incidentEdges(current);
            for (Edge edge : incident) {
                Vertex w = opposite(edge, v);
                int index = verticies.indexOf(w);
                if (!visited.contains(w)) {
                    paths[index] = edge;
                    visited.add(w);
                    s.add(w);
                }
            }
        }
        ArrayList<Edge> route = new ArrayList<>();
        Edge e;
        Vertex previous;
        do {
            e = paths[verticies.indexOf(v)];
            route.add(e);
            previous = opposite(e, v);
        } while (!previous.equals(u));
        return route;
    }
}
