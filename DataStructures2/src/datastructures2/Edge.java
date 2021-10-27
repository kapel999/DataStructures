/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures2;

/**
 * Edge class. This class will store the name of the path between two vertex.
 * 
 * @author 184510
 * @version April 2019
 */
public class Edge {
    
    //Variable that will be used in that class
    private String name;
    private Vertex v;
    private Vertex w;
    
    /**
     * Constructor sets the name of the path.
     * @param v Vertex
     * @param w Vertex
     * @param name String
     */
    public Edge(Vertex v, Vertex w, String name){
        this.name = name;
        this.v = v;
        this.w = w;
    }
    
    /**
     * This method will return the name of the line.
     * @return edge name
     */
    public String getEdgeName(){
        return name;
    }  
    
    /**
     * This method will return name of the station at the end of the line 
     * opposite to the station entered by the user
     * @param nameOfStation Vertex
     * @return name of the station 
     */
    public Vertex getNameOfOtherStation(Vertex nameOfStation){
        if(nameOfStation.equals(nameOfStation)){
            return w;
        }
        else if(nameOfStation.equals(nameOfStation)){
            return v;
        }
        else{
            System.out.println("Incorrect station name!");
            return null;
        }
    }
    
    /**
     * This method will return station name at position v
     * @return station name
     */
    public Vertex getStationNameV(){
        return v;
    }
    
    /**
     * This method will return station name at position w
     * @return station name
     */
    public Vertex getStationNameW(){
        return w;
    }
    
    /**
     * This method will set a name of the edge to a one that user decides to 
     * enter
     * @param name 
     */
    public void setEdgeName(String name){
        this.name = name;
    }
}
