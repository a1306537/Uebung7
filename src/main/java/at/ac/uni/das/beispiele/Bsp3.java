package at.ac.uni.das.beispiele;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.ac.uni.das.util.Plot;
import at.ac.uni.das.util.Plot.Data;
import at.ac.uni.das.util.Plot.DataSeriesOptions;
import at.ac.uni.das.util.Plot.PlotOptions;

public class Bsp3 {
	private static List<Double> datei1Double;
	private static List<Double> datei2Double;

	public static void linearerZusammenhang(List<Integer> datei1, List<Integer> datei2) {
		datei1Double = parseIntToDoubleList(datei1);
		datei2Double = parseIntToDoubleList(datei2);
		
	//Anfange - Erstellen der Punkte
		PlotOptions plotO = Plot.plotOpts();
        plotO.title("Bsp3 - Lineare Regression");
        plotO.legend(Plot.LegendFormat.BOTTOM);
		
		DataSeriesOptions dataSeriesO = Plot.seriesOpts();
		dataSeriesO.marker(Plot.Marker.SQUARE);
		dataSeriesO.markerColor(Color.GREEN);
		dataSeriesO.color(Color.BLACK);
		dataSeriesO.line(Plot.Line.NONE);
        
        Data data = Plot.data();
        data.xy(datei1Double, datei2Double);
        
        Plot plot = Plot.plot(plotO);
        plot.xAxis("x", Plot.axisOpts().range(0, 101));
        plot.yAxis("y", Plot.axisOpts().range(0, 101));
        plot.series("Punkte", data, dataSeriesO);
   //Ende - Erstellen der Punkte
		
		
		
	//Anfang - Berechnung der Werte, welche man für die Erstellung der Geraden benötigt
		//Siehe http://www2.jura.uni-hamburg.de/instkrim/kriminologie/Mitarbeiter/Enzmann/Lehre/StatIKrim/Regression.pdf
		//Folie 4
		double xi= sum(datei1);
		double yi= sum(datei2);
		double mittelwertX = xi/datei1.size();
		double mittelwertY = yi/datei2.size();
		double standardabweichungX = standartAbweichung(mittelwertX, datei1);
		double standardabweichungY = standartAbweichung(mittelwertY, datei2);	
		
		double sXY = berechnen(mittelwertX, mittelwertY, datei1, datei2)/datei1.size();
		double r = sXY / (standardabweichungX * standardabweichungY);
		
		double b = r * (standardabweichungX / standardabweichungY);
		double a = mittelwertY - (b * mittelwertX);

		//Formel: y(x) = a+b*x
		//Man berechnet die y-Werte für die x-Werte von 0 und 100
		double yAnStelleX0 =   a + (b*0);
		double yAnStelleX100 = a + (b*100);
		
		//Werte von X und Y werden in Arrays gespeichert:
		double xWerte[] = {0, 100};
		double yWerte[] = {yAnStelleX0, yAnStelleX100};
	//Ende - Berechnung der Werte, welche man für die Erstellung der Geraden benötigt
		
		
	//Anfang - Einzeichnen der Geraden
		DataSeriesOptions dataSeriesO2 = Plot.seriesOpts();
		dataSeriesO2.marker(Plot.Marker.DIAMOND);
		dataSeriesO2.markerColor(Color.RED);
		dataSeriesO2.color(Color.RED);
        
        Data data2 = Plot.data();
        data2.xy(xWerte, yWerte);
        
        plot.series("Lineare Gerade", data2, dataSeriesO2);
    //Ende - Einzeichnen der Geraden
		
		
		
		try {
			plot.save("Bsp3", "png");    //Bild wird gespeichert
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//Ausgabe der Daten
        System.out.println("-------------------------------------------------------------------------------------");
	    System.out.println("Berechnete Werte:");
	    System.out.println("xi= sum(datei1)                                               = "+ xi);
	    System.out.println("yi= sum(datei2)                                               = "+ yi);
	    System.out.println("mittelwertX = xi/datei1.size()                                = "+ mittelwertX);
	    System.out.println("mittelwertY = yi/datei2.size()                                = "+ mittelwertY);
	    System.out.println("standardabweichungX = standartAbweichung(mittelwertX, datei1) = "+ standardabweichungX);
	    System.out.println("standardabweichungY = standartAbweichung(mittelwertY, datei2) = "+ standardabweichungY);
	    System.out.println();
	    System.out.println("sXY = berechnen(mittelwertX, mittelwertY, datei1, datei2)/datei1.size() = "+ sXY);
	    System.out.println("r(Korrelation) = sXY / (standardabweichungX * standardabweichungY)       = "+ r);
	    System.out.println();
	    System.out.println("b = r * (standardabweichungX / standardabweichungY) = "+ b);
	    System.out.println("a = mittelwertY - (b * mittelwertX)                 = "+ a);
	    System.out.println();
	    System.out.println("y(x) = "+ a +" + "+ b +"* x" );
	    System.out.println("-------------------------------------------------------------------------------------");
	    System.out.println();
    	
	    
	}

	/**
	 * Summe (xi-mittelwertXi)+(yi-mittelwertYi)
	 */
	private static double berechnen(double mittelwertX, double mittelwertY, List<Integer> datei1, List<Integer> datei2) {
		double sum = 0;
		
		for(int i=0; i<datei1.size(); i++) {
			sum = sum + ((datei1.get(i) - mittelwertX) * (datei2.get(i) - mittelwertY));
		}
		
		return sum;
	}

	private static List<Double> parseIntToDoubleList(List<Integer> datei) {
		List<Double> dateiDouble = new ArrayList<Double>();
		
		for(int dateiSingle: datei)
			dateiDouble.add(dateiSingle*1.0);
		
		return dateiDouble;
	}
	
	/**
	 * Berechnen der Summe der Zahlenreihe
	 */
	private static int sum(List<Integer> datei){
		int sum = 0;
		
		for(int wert: datei)
			sum += wert;
		
		return sum;
	}
	
	/**
	 * Berechnet die Standardabweichung der Zahlenfolge
	 * @return Standardabweichung
	 */
	public static double standartAbweichung(double mittelwert, List<Integer> datei){
	  double sdev = 0.0;
	  
	  for(int i = 0; i < datei.size(); i++) 
		  sdev += (datei.get(i) - mittelwert) * (datei.get(i) - mittelwert);
	    
	  	  sdev = Math.sqrt(sdev / (datei.size() - 1.0));
	      return sdev;
	}	
}
