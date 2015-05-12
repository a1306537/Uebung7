package at.ac.uni.das.beispiele;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bsp4 {

	public static void mittelKonf(List<Integer> datei1, List<Integer> datei2) {
		double mean1=0;
		double mean2=0;
		Map<Double,Double> zwert=new HashMap<Double, Double>();
		zwert.put(0.9, 1.282);
		zwert.put(0.95, 1.645);
		zwert.put(0.975, 1.96);
		zwert.put(0.99, 2.326);
		zwert.put(0.995, 2.576);
		
		for(int werte:datei1){
			mean1+=werte;
		}
		mean1=mean1/datei1.size();
		
		
		for(int werte2:datei2){
			mean2+=werte2;
		}
		mean2=mean2/datei2.size();
		
		System.out.println("-------------------------");
		System.out.println("Mitelwert Datei1: "+mean1);
		System.out.println("Mitelwert Datei2: "+mean2);
		System.out.println("-------------------------\n");
		
		//Berechnen Standartabweichung
		double sdev1=standartAbweichung(mean1, datei1);
		double sdev2=standartAbweichung(mean2, datei2);
		
		Scanner sc = new Scanner(System.in);;
		System.out.println("Bitte geben Sie das Konfidenzneveau in % für Datei1 ein:");
		double alpha1=sc.nextDouble()/100;
		System.out.println("Bitte geben Sie das Konfidenzneveau in % für Datei2 ein:");
		double alpha2=sc.nextDouble()/100;
				
		double z1=zwert.get(alpha1);
		double z2=zwert.get(alpha2);
		
		//Konfidenzintervall
		double untergrenze1=mean1-z1*(sdev1/Math.sqrt(datei1.size()));
		double untergrenze2=mean2-z2*(sdev2/Math.sqrt(datei2.size()));
		
		double obergrenze1=mean1+z1*(sdev1/Math.sqrt(datei1.size()));
		double obergrenze2=mean2+z2*(sdev2/Math.sqrt(datei2.size()));
		
		System.out.println("-------------------------");
		System.out.println("Konfidenzintervall Datei1: ["+untergrenze1+","+obergrenze1+"]");
		System.out.println("Konfidenzintervall Datei2: ["+untergrenze2+","+obergrenze2+"]");
		System.out.println("-------------------------\n");
	}
	
	/**
	   * Berechnet die Standardabweichung der Zahlenfolge
	   * @return die Standardabweichung
	   */
	  public static double standartAbweichung(double mean, List<Integer> datei){
	    double sdev = 0.0;
	    
	    for(int i = 0; i < datei.size(); i++) 
	    	sdev += (datei.get(i) - mean) * (datei.get(i) - mean);
	    
	    sdev = Math.sqrt(sdev / (datei.size() - 1.0));
	    return sdev;
	  }


}
