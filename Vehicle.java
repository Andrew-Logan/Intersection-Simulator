/**
 * Assignment: Project 2
 * Due Date: 11/11/13
 * Instructor: Dr. DePasquale
 * Submitted by: Andrew Logan
 */

import java.util.*;
import java.io.*;

/**
 * The Vehicle class encapsulates the representation of a newly created car in the 
 * intersection during simulation.
 *
 * @author Andrew Logan
 */

public class Vehicle
{
	/**
 	 * Represents the number of the Vehicle in order of creation.
 	 */

	private int vehicleNumber;
	
	/**
 	 * Represents the street on which the Vehicle is on.
 	 */
	
	private Street street;
	
	/**
 	 * Represents the direction the Vehicle is moving.
 	 */
	
	private Direction direction;
	
	/**
 	 * Represents the time in which the Vehicle was initialized.
 	 */
	
	private int arrivalTime;
	
	/**
 	 * Represents the time that the Vehicle moved through the intersection.
 	 */ 
	
	private int departureTime;
	
	/**
	 * Represents whether or not the Vehicle is turning.
	 */
	
	private boolean toTurn;
	
	/**
 	 * Represents the possible directions the Vehicle could be initialized with.
 	 */
	
	enum Direction {N, S, E, W}
	
	/**
 	 * Represents the possible streets the Vehicle could be initialized with. 
 	 */
 	 
	enum Street {Church, Main}
	
	/**
 	 * Sets the instance variables for the newly created Vehicle from the inputted data.
 	 *
 	 * @param inNumber The number for the Vehicle.
 	 * @param inStreet The street the Vehicle is placed on.
 	 * @param inDirection The direction the Vehicle is facing.
 	 * @param inArrivalTime The time the Vehicle was created at.
 	 * @param inTurn Tells whether car is turning or not.
 	 */
	
	public Vehicle(int inNumber, int inStreet, int inDirection, int inArrivalTime, boolean inTurn)
	{
		vehicleNumber = inNumber;
		arrivalTime = inArrivalTime;
		toTurn = inTurn;
		
		if(inStreet == 1)
		{
			street = Street.Church;
			if(inDirection == 1)
				direction = Direction.N;
			else
				direction = Direction.S;
		}
		else
		{
			street = Street.Main;
			if(inDirection == 1)
				direction = Direction.E;
			else
				direction = Direction.W;
		}	
	}
	
	/**
 	 * Returns the vehicle number.
 	 *
 	 * @return int The vehicle's number.
 	 */

	public int getVehicleNumber()
	{
		return vehicleNumber;
	}
	
	/**
 	 * Returns the street that the Vehicle is on.
 	 *
 	 * @return String The street the Vehicle is on.
 	 */
	
	public String getStreet()
	{
		switch(street)
		{
			case Church:
				return "Church";
			case Main:
				return "Main";
			
		}
		return "";
	}
	
	/**
 	 * Returns the vehicle's direction.
 	 *
 	 * @return String The vehicle's direction.
 	 */
	
	public String getDirection()
	{
		switch(direction)
		{
			case N:
				return "north";
			case S:
				return "south";
			case E:
				return "east";
			case W:
				return "west";
		}
		return "";
	}
	
	/**
 	 * Returns the time the Vehicle arrived in the intersection.
 	 *
 	 * @return int The vehicle's time of creation.
 	 */
	
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	
	/**
 	 * Returns the time the vehicle moved through the intersection.
 	 *
 	 * @return int The vehicle's departure time.
 	 */
	
	public int getDepartureTime()
	{
		return departureTime;
	}
	
	/**
 	 * Sets the time in which the vehicle moved through the intersection.
 	 */
	
	public void setDepartureTime(int inTime)
	{
		departureTime = inTime;
	}
	
	public String turn()
	{
		String result = "";
		
		if(!toTurn)
			result += "continued straight.";
		else
		{
			if(getStreet().equals("Church"))
				if(getDirection().equals("north"))
					result += "turned right and headed eastbound.";
				else
					result += "turned right and headed westbound.";
			else
				if(getDirection().equals("east"))
					result += "turned right and headed southbound.";
				else
					result += "turned right and headed northbound.";
		}
			
		return result;
	}
	
	/**
 	 * Returns the String representation of the Vehicle class.
 	 *
 	 * @return String Representation of the Vehicle class.
 	 */
	
	public String toString()
	{		
		String result = "";
		
		result += ("[Time " + departureTime + "] ");
		result += ("Vehicle #" + vehicleNumber + " ");
		result += ("(" + getDirection() + "bound) ");
		result += (turn() + " ");
		result += ("Total wait time " + (getDepartureTime()-getArrivalTime()) + " seconds.");
		
		return result;
	}
}