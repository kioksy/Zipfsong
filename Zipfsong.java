import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Antonios Kioksoglou
 *
 */
public class Zipfsong {
	
	private static LinkedList<Track> album = new LinkedList<Track>();
	private static final String INPUT_VALIDATION = "^[a-z0-9_]{0,30}$";
	private static long maxPlays = (long) Math.pow(10, 12);
	private int nrOfSongs;
	
	public Zipfsong(){}
	
	/*
	 * Gets the user input via stdin and also goes through some rules
	 * 1. first line must contain integers only
	 * 2. other lines starts with longs and after the whitespace comes a string
	 * 3. the long must be in the interval 0-10^2 and the string can only be
	 * 1-30 characters long and contain a-z,0-9 and (_) only!
	 * stores all passed values as "Track".
	 */
	public void getInput(){
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		try{	    
	    String[] numbers = stdin.readLine().split(" ");
	    
	    if(numbers.length > 1){
		
		int totalS = Integer.parseInt(numbers[0]);
		nrOfSongs = Integer.parseInt(numbers[1]);
		
		if(inInterval(0, 50000,totalS) && totalS>=nrOfSongs)
			for(int i=0; i<totalS; i++){
				String input = stdin.readLine();
				String[] nrAndSong = input.split(" ");
				 
				 if(nrAndSong[1] != null){
				
					 long nrOfPlays = Long.parseLong(nrAndSong[0]);
					 String song = nrAndSong[1];
		
						if(inInterval(0,maxPlays,nrOfPlays))
						{
							if(validate_input(song)){
								double quality = nrOfPlays * (i+1);
								album.add(new Track(song, quality, i));
							}
						}
				 }
			}
	    }
	    stdin.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * given numberOfSongs it prints the number of rows
	 * you want to be printed
	 */
	public void handleOutput(){
		Collections.sort(album);
		for(int i=0; i<nrOfSongs; i++){
			System.out.println(album.get(i).getName());
		}
	}
	
	/*
	 * checks if values are in given interval
	 */
	public static boolean inInterval(long low, long high, long n) {
	    return n >= low && n <= high;
	}
	
	/*
	 * validates input for Strings in line 2->n
	 */
	public static boolean validate_input(String input){
		  Pattern pattern = Pattern.compile(INPUT_VALIDATION);
		  Matcher matcher = pattern.matcher(input);
		  return matcher.matches();
	  }
	
	/*
	 * main class to run the file
	 */
	public static void main(String[] args) throws java.io.IOException{
		long startTime = System.nanoTime();
		Zipfsong run = new Zipfsong();
		try{
			run.getInput();
			run.handleOutput();
		long stopTime = System.nanoTime() - startTime;
		System.out.println("took: " + stopTime);
		}catch(IndexOutOfBoundsException e){
			e.printStackTrace();
		}catch(NumberFormatException e){
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}catch(RuntimeException e){
			e.printStackTrace();
		}
	}
	
}
