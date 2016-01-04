/**
 * Assignment: Project 2
 * Due Date: 11/11/13
 * Instructor: Dr. DePasquale
 * Submitted by: Andrew Logan
 */

import java.util.*;
import java.io.*;
import java.lang.Math;
import jsjf.*;

/**
 * The Simulator class is the primary class involved in running the simulation of traffic
 * at an intersection. It holds an array of LinkedQueues to represent the lanes that newly 
 * instantiated Vehicle objects can be placed in.
 * 
 * @author Andrew Logan
 */

public class Simulator
{
	/**
	 * The array of LinkedQueues to represent the lanes in the intersection.
	 */

	private LinkedQueue<Vehicle> lanes[] = new LinkedQueue[8];
	
	/**
	 * Counter for the number of cars created.
	 */
	
	private int carCount = 1;
	
	/**
	 * Counter for number of cars moved through intersection.
	 */
	 
	private int carMovedCount = 0;
	
	/**
	 * Represents the time that has passed during simulation.
	 */
	 
	private int time = 0;
	
	/**
	 * Maximum amount of cars allowed to be created.
	 */
	 
	private int CAR_LIMIT = 121;
	
	/**
	 * Boolean value for whether car generation is to continue or not.
	 */
	 
	private boolean continueGen = true;
	
	/**
	 * Boolean value for whether simulation is to continue or not.
	 */
	 
	private boolean continueSim = true;
	
	/**
	 * Simulates traffic moving through an intersection using the array of LinkedQueues.
	 */
	
	public void simulate()
	{
		/**
	 	 * Number of cars to be generated.
	 	 */
	
		int carGenNum; 
		
		/**
	 	 * Represents the lane for new Vehicle to be placed into. 
	 	 */
	 	 
		int laneGenNum;
		
		/**
	 	 * Represents the direction to be given to new Vehicle.
	 	 */
	 	 
		int directionGen;
		
		/**
	 	 * Represents the street to be given to new Vehicle.
	 	 */
		
		int streetGen;
		
		/**
		 * Represents whether or not Vehicle is in turning lane.
		 */
		 
		boolean toTurn;
		
		carGenNum = (int)(Math.random() * 6) + 7;
		
		for(int i = 0; i < 8; i++)
		{
			lanes[i] = new LinkedQueue();
		}
		
		/**
	 	 * Initial generation of Vehicles for the intersection.
	 	 */
		
		for(int i = 0; i < carGenNum; i++)
		{
			laneGenNum = (int)(Math.random()*8);
			if(laneGenNum < 4)
			{
				streetGen = 1;
				if(laneGenNum >= 2)
					directionGen = 2;
				else
					directionGen = 1;
			}
			else
			{
				streetGen = 2;
				if(laneGenNum >= 6)
					directionGen = 2;
				else
					directionGen = 1;
			}
			
			if(laneGenNum % 2 == 0)
				toTurn = false;
			else
				toTurn = true;
			
			lanes[laneGenNum].enqueue(new Vehicle(carCount, streetGen, directionGen, time, toTurn));
			carCount++;
		}
		
		System.out.println("---Start of simulation, time set to 0.");
		
		/**
	 	 * Simulation of the cars moving through the intersection.
	 	 */
		
		while(continueSim)
		{
			/**
	 	 	* Generation of 3-15 Vehicles.
	 	 	*/
			
			if(time > 0 && continueGen)
			{
				carGenNum = (int)(Math.random() * 13) + 3;
				for(int i = 0; i < carGenNum; i++)
				{
					laneGenNum = (int)(Math.random()*8);
					
					if(laneGenNum < 4)
					{
						streetGen = 1;
						if(laneGenNum >= 2)
							directionGen = 2;
						else
							directionGen = 1;
					}
					else
					{
						streetGen = 2;
						if(laneGenNum >= 6)
							directionGen = 2;
						else
							directionGen = 1;
					}
					
					if(laneGenNum % 2 == 0)
						toTurn = false;
					else
						toTurn = true;
					
					lanes[laneGenNum].enqueue(new Vehicle(carCount, streetGen, directionGen, time, toTurn));
					carCount++;
					if(carCount == CAR_LIMIT)
					{
						continueGen = false;
						break;
					}
				}
			}
			
			if(time == 0)
				time+=3;
			
			System.out.println("---Light changed. Now processing north/south-bound traffic---");
			Vehicle moving;
			
			/**
	 	 	* Moving Vehicles in north/south lanes through intersection.
	 	 	*/
			
			for(int i = 0; i < 2; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					if(!(lanes[j].isEmpty()))
					{
						moving = (Vehicle)lanes[j].dequeue();
						moving.setDepartureTime(time);
						System.out.println(moving.toString());
					}
				}
				time+=3;
			}
			
			System.out.println();
			
			/**
	 	 	* Generation of 8-15 Vehicles.
	 	 	*/
			
			if(continueGen)
			{
				carGenNum = (int)(Math.random() * 8) + 8;
				for(int i = 0; i < carGenNum; i++)
				{
					laneGenNum = (int)(Math.random()*8);
					
					if(laneGenNum < 4)
					{
						streetGen = 1;
						if(laneGenNum >= 2)
							directionGen = 2;
						else
							directionGen = 1;
					}
					else
					{
						streetGen = 2;
						if(laneGenNum >= 6)
							directionGen = 2;
						else
							directionGen = 1;
					}
					
					if(laneGenNum % 2 == 0)
						toTurn = false;
					else
						toTurn = true;
					
					lanes[laneGenNum].enqueue(new Vehicle(carCount, streetGen, directionGen, time, toTurn));
					carCount++;
					if(carCount == CAR_LIMIT)
					{
						continueGen = false;
						break;
					}
				}
			}
			
			System.out.println("---Light changed. Now processing east/west-bound traffic---");
			
			/**
	 	 	* Moving Vehicles in east/west lanes through intersection.
	 	 	*/
			
			for(int i = 0; i < 3; i++)
			{
				for(int j = 4; j < 8; j++)
				{
					if(!(lanes[j].isEmpty()))
					{
						moving = (Vehicle)lanes[j].dequeue();
						moving.setDepartureTime(time);
						System.out.println(moving.toString());
					}
				}
				time+=3;
			}
			
			System.out.println();
			
			if(endOfSim())
			{
				continueSim = false;
				System.out.println("---End of simulation.");
			}
		}
	}
	
	/**
	 * Checks to see if the array is completely empty in order to end the simulation.
	 * 
	 * @return boolean True if array is empty, false if it is not.
	 */
	
	public boolean endOfSim()
	{
		boolean result = true;
		
		for(int i = 0; i < 8; i++)
		{
			if(!(lanes[i].isEmpty()))
				return false;
		}
		
		return result;
	}
}