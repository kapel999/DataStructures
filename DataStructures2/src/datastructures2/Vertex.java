/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures2;

/**
 * Vertex class. This class will store the name of the train station.
 * 
 * @author 184510
 * @version April 2019
 */
public class Vertex {
    
    //Variable that will be used in that class
    private String name;
    
    /**
     * Constructor sets the name of the train station.
     * @param name String
     */
    public Vertex(String name){
        this.name = name;
    }
    
    /**
     * This method will return the name of the station.
     * @return station name
     */
    public String getStationName(){
        return name;
    }
    
    /**
     * This method will set a name of the station to a one that user decides to 
     * enter
     * @param name 
     */
    public void setStationName(String name){
        this.name = name;
    }
}