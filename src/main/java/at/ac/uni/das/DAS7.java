package at.ac.uni.das;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DAS7 {

	private static Scanner sc= new Scanner(System.in);
	//
	private static List<Integer> datei1 = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		System.out.println("Bitte geben Sie den Dateipfad ein:");
		//
		String pfad = sc.next();
		//
		importDatei(pfad);

	}
	
	public static void importDatei(String pfad){
		try {
			
			FileReader fr= new FileReader(pfad);
			BufferedReader br= new BufferedReader(fr);
			
			String zeile = new String();

			while((zeile=br.readLine())!=null){
				datei1
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
