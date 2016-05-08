package game.pack;

import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

public class ScoreManage {
	public static Scanner x;
	public static  Formatter fm;
	
	//adding new records
	public static void Update(){
		OpenFile();
		addRecords();
		CloseFile();
	}
	
	public static void OpenFile(){
		try{
			fm = new Formatter ("res/highscore/highscore.txt");
		}
		catch(Exception Ex){
			Ex.printStackTrace();
		}
	}
	
	public static void print(){
		for (int i = 0; i<10; i++){
			System.out.println(STATS.Names[i] + "   "  + STATS.Scores[i]);
		}
	}
	public static void addRecords(){
		for (int i = 0; i<10; i++){
			fm.format("%s %s\n", STATS.Names[i], STATS.Scores[i]);
		}
	}
	
	public static void CloseFile(){
		fm.close();
	}
	
	
	//Reading current records
	public static void READ(){
		openFile();
		readFile();
		closeFile();
	}
	
	
	public static void openFile(){
		try {
			x = new Scanner (new File ("res/highscore/highscore.txt"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void readFile(){
		int i = 0;
		while (x.hasNext()){
			STATS.Names[i] = x.next();
			STATS.Scores[i] = x.next();
			i++;
		}
	}
	
	public static void closeFile (){
		x.close();
	}
	
	
	//sorting the list.. :) 
	public static void SORTING(){
		int temp;
		for (int i = 9; i>=0; i--){
			temp = Integer.parseInt(STATS.Scores[i]);
			
			if (STATS.SCORE >= temp){
				STATS.Scores[i+1] = STATS.Scores[i];
				STATS.Names[i+1] = STATS.Names[i];
				
				STATS.Scores[i] = Integer.toString(STATS.SCORE);
				STATS.Names[i] = STATS.current_player;
			}
		}
	}
}
