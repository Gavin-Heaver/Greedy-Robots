// Author: Gavin Heaver
// NID: ga518853
// Course: CS2
// Professor: Dr. Award Mohammed Ali
// Semester: Fall 2025
// Assignment 3

import java.io.*;
import java.util.*;

public class GreedyRobots {
  // Variables
  int[] maxRobotDistance;
  int[] distanceFromDispatch;
  boolean[] buildingChecker;
  int successfulDeliveries;
  int unsuccessfulDeliveries;
  
  // Constructor
  public GreedyRobots(int availableRobots, int numberOfBuildings, String robotsFile, String buildingsFile)
  {
    // Set all variables within constructor
    this.successfulDeliveries = 0;
    this.unsuccessfulDeliveries = numberOfBuildings;
    this.maxRobotDistance = new int[availableRobots];
    this.distanceFromDispatch = new int[numberOfBuildings];
    this.buildingChecker = new boolean[numberOfBuildings];
    
    // Call readFiles
    readFiles(robotsFile, buildingsFile);
  }
  
  // The read file method
  public void readFiles(String robotsFile, String buildingsFile)
  {
    // Open the robots file
    File file1 = new File(robotsFile);
    
    try{
      // Scan the file details
      Scanner scan = new Scanner(file1);
      
      // Initialize an index number
      int index = 0;
      
      // Loop while numbers are in the file
      while(scan.hasNextInt())
      {
        // Add robot distance array from values in file
        this.maxRobotDistance[index] = scan.nextInt();
        
        // Increase the index by 1
        index++;
      }
      
      // Close the scanner
      scan.close();
    } catch (FileNotFoundException e){
      // Error if a file doesn't open
    }
    
    // Open the buildingsFile
    File file2 = new File(buildingsFile);
    
    try{
      // Scan the file details
      Scanner scan = new Scanner(file2);
      
      // Initialize an index number
      int index = 0;
      
      // Loop while numbers are in the file
      while(scan.hasNextInt())
      {
        // Add building distance array from value in file
        this.distanceFromDispatch[index] = scan.nextInt();
        
        // Increase the index by 1
        index++;
      }
      
      // Close the scanner
      scan.close();
    } catch (FileNotFoundException e){
      // Error if a file doesn't open
    }
    
    // Sort the robot distance array from smallest to largest
    Arrays.sort(this.maxRobotDistance);
    
    // Now reverse the array to sort it from largest to smallest
    for(int i = 0; i < this.maxRobotDistance.length / 2; i++)
    {
      int temp = this.maxRobotDistance[i];
      this.maxRobotDistance[i] = this.maxRobotDistance[this.maxRobotDistance.length - i - 1];
      this.maxRobotDistance[this.maxRobotDistance.length - i - 1] = temp;
    }
    
    
    // Sort the building distance from smallest to largest
    Arrays.sort(this.distanceFromDispatch);
    
    // Now reverse the array to sort it from largest to smallest
    for(int i = 0; i < this.distanceFromDispatch.length / 2; i++)
    {
      int temp = this.distanceFromDispatch[i];
      this.distanceFromDispatch[i] = this.distanceFromDispatch[this.distanceFromDispatch.length - i - 1];
      this.distanceFromDispatch[this.distanceFromDispatch.length - i - 1] = temp;
    }
    
  }
  
  // The assign deliveries method 
  public void assignDeliveries()
  {
    // Loop for the number of robots there are
    for(int i = 0; i <  this.maxRobotDistance.length; i++)
    {
      // Loop for the number of buildings there are
      for(int j = 0; j < this.distanceFromDispatch.length; j++)
      {
        // Make sure that the robot can travel to the building, and that the building has already been delivered to
        if(this.maxRobotDistance[i] >= this.distanceFromDispatch[j] && this.buildingChecker[j] == false)
        {
          // Subtract the building distance from the robots max distance
          this.maxRobotDistance[i] = this.maxRobotDistance[i] - this.distanceFromDispatch[j];
          
          // Mark that the building has been visited
          this.buildingChecker[j] = true; 
          
          // Add one to the successful deliveries, and subtract one from the unsuccessful deliveries
          this.successfulDeliveries++;
          this.unsuccessfulDeliveries--;
        }
      }
    }
  }
  
  // The display results method
  public void displayResults()
  {
    // Print out the successful and unsuccessful deliveries 
    System.out.println("Successful Deliveries: " + this.successfulDeliveries);
    System.out.println("Unserved Buildings: " + this.unsuccessfulDeliveries);
  }  
  
}