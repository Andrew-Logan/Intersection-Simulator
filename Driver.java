/**
 * Assignment: Project 2
 * Due Date: 11/11/13
 * Instructor: Dr. DePasquale
 * Submitted by: Andrew Logan
 */

import java.util.*;
import java.io.*;
import java.io.PrintStream;

/**
 * The Driver class instantiates a Simulator object to run the simulate method.
 * Driver class contains the main method.
 * @author Andrew Logan
 */

public class Driver
{
	public static void main(String args[])
	{
		Simulator simulator = new Simulator();
		
		try
		{
			PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
			System.setOut(out);
			System.setErr(out);
		}
		catch(FileNotFoundException exception)
		{
			System.out.println("File not found.");
		}
		
		simulator.simulate();
		
	}
}