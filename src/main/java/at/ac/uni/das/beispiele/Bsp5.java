package at.ac.uni.das.beispiele;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  	Berechnen Sie in dieser Funktion die Differenz zwischen dem kleinsten und dem höchstem Wert des jeweiligen Datensatzes (zwei Werte).
	Die Ausgabe dieser Funktion sind zwei Balkendiagramme die die Häufigkeiten der Werte in jeweils
	10% der Differenz darstellt (10 Balken pro Diagramm).
 */

public class Bsp5 {
	
 private static int max;
 private static int min;
 private static double diff1;
 private static double diff2;
	
	public static void Balkendiagramm(List<Integer> datei1, List<Integer> datei2) {
		Map<Integer,Integer> werte1=new HashMap<Integer,Integer>();
		Map<Integer,Integer> werte2=new HashMap<Integer,Integer>();
		
		max=datei1.get(0);
		min=datei1.get(0);
		for(int i=0; i<datei1.size(); i++){
			if(max<datei1.get(i))
				max=datei1.get(i);
			if(min>datei1.get(i))
				min=datei1.get(i);
			int cache = 0;
			if(werte1.get(datei1.get(i))==null)
				cache=1;
			else{
				cache=werte1.get(datei1.get(i));
				cache++;
			}
			werte1.put(datei1.get(i), cache);
		}
		diff1=max-min;
		//10% ermitteln
		diff1=diff1*0.1;
		
		max=datei2.get(0);
		min=datei2.get(0);
		for(int i=0; i<datei2.size(); i++){
			if(max<datei2.get(i))
				max=datei2.get(i);
			if(min>datei2.get(i))
				min=datei2.get(i);
			
			int cache = 0;
			if(werte2.get(datei2.get(i))==null)
				cache=1;
			else{
				cache=werte2.get(datei2.get(i));
				cache++;
			}
			werte2.put(datei2.get(i), cache);
		}
		diff2=max-min;
		diff2=diff2*0.1;
	
		for(Map.Entry e : werte1.entrySet()){
			  System.out.println(e.getKey() + " = " + e.getValue());
		}
		
		
		
	}

}
