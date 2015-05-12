package at.ac.uni.das;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import at.ac.uni.das.beispiele.Bsp2;
import at.ac.uni.das.beispiele.Bsp3;
import at.ac.uni.das.beispiele.Bsp4;
import at.ac.uni.das.beispiele.Bsp5;
import at.ac.uni.das.beispiele.Bsp6;

public class DAS7 {

	private static Scanner sc= new Scanner(System.in);
	//
	private static List<Integer> datei1 = new ArrayList<Integer>();
	private static List<Integer> datei2 = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		for(int i=1; i<=2;i++){
		
			System.out.println("Bitte geben Sie den Dateipfad zur "+i+".Datei ein:");
			//String pfad = sc.next();
			String pfad="/home/losfamos/git/Uebung7/Datei"+i+".txt";
			importDatei(pfad, i);
		}
		
		StringBuilder menu= new StringBuilder();
		menu.append("Bitte wählen Sie die gewünschte Funktion:\n");
		menu.append("2 BoxPlot anzeigen.\n");
		menu.append("3 Datensätze auf linearen Zusammenhang untesrsuchen.\n");
		menu.append("4 Mittelwert und Konfidenzintervall berechnen.\n");
		menu.append("5 Balkendiagramm erstellen.\n");
		menu.append("6 Boxplot der Differenz der Datensätze anzeigen.\n");
		
		boolean stop= false;
		while(!stop){
			System.out.println(menu.toString());
			int eingabe=sc.nextInt();
			
			switch (eingabe) {
				case 2:
					Bsp2.boxPlot(datei1, datei2);
					break;
				case 3:
					Bsp3.linearerZusammenhang(datei1, datei2);
					break;
				case 4:
					Bsp4.mittelKonf(datei1, datei2);
					break;
				case 5:
					Bsp5.Balkendiagramm(datei1, datei2);
					break;
				case 6:
					Bsp6.DifferenzBlot(datei1, datei2);
					break;

				default:
					stop=true;
					break;
			}
		}

	}
	
	public static void importDatei(String pfad, int i){
		try {
			FileReader fr= new FileReader(pfad);
			BufferedReader br= new BufferedReader(fr);
			
			String zeile = new String();

			
			while((zeile=br.readLine())!=null){
				if(i==1)
					datei1.add(Integer.parseInt(zeile));
				else
					datei2.add(Integer.parseInt(zeile));
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
