package peval3;

import java.util.Scanner;

/**
 * 
 * @author Javier Tienda González
 * @date 18/11/2022
 * @version 1.0
 * 
 * @info Class that has all general methods used by the program
 * 
 */

public class GMethods {
	
	/**
	 * Variable to collect data typed by keyboard
	 */
	private static Scanner keyb;
	
	/**
	 * Constants for the color of the error print
	 */
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_RESET = "\u001B[0m";
	
	/**
	 * Empty class constructor
	 */
	GMethods(){
		
	}

	/**
	 * Method prints a text that you pass as a parameter
	 * 
	 * @param txt Text that its going to be printed
	 */
	public static void println(String txt) {
		System.out.println(txt);
	}

	/**
	 * Method prints a split text
	 */
	public static void printDiv() {
		System.out.println("------------------------------------------");
	}
	
	/**
	 * Method prints a text that you pass as a parameter
	 * 
	 * @param txt Text that its going to be printed
	 */
	public static void printError(String txt) { System.out.println("***"+ txt + "***");}
	
	/**
	 * Method that receives a String
	 * 
	 * @param text
	 * @return number selection
	 */
	public static byte keyBByte(String text) {
		println(text);
		keyb = new Scanner(System.in);
		return keyb.nextByte();
	}

	/**
	 * Method that receives a String
	 *
	 * @param text
	 * @return number
	 */
	public static int keyBInt(String text) {
		println(text);
		keyb = new Scanner(System.in);
		return keyb.nextInt();
	}

	/**
	 * Method that receives a String
	 *
	 * @param text
	 * @return number
	 */
	public static double keyBDouble(String text) {
		println(text);
		keyb = new Scanner(System.in);
		return keyb.nextDouble();
	}

	/**
	 * Method that receives a String
	 * 
	 * @param text
	 * @return string
	 */
	public static String keyBString(String text) {
		println(text);
		keyb = new Scanner(System.in);
		return keyb.nextLine();
	}
	
}
