package at.ac.uni.das.beispiele;

import java.util.ArrayList;
import java.util.List;

import org.jfree.ui.RefineryUtilities;

import at.ac.uni.das.util.BoxAndWhiskerCreator;
import at.ac.uni.das.util.BoxAndWhiskerCreator1File;

public class Bsp6 {

	private static List<Integer> diffList = new ArrayList<Integer>();
	
	public static void DifferenzBlot(List<Integer> datei1, List<Integer> datei2) {
		for(int i=0; i<datei1.size(); i++){
			int wert1=datei1.get(i);
			int wert2= datei2.get(i);
			
			if(wert1<wert2)
				diffList.add( wert2-wert1 );
			else
				diffList.add(wert1-wert2);
		}
		
		final BoxAndWhiskerCreator1File boxPlot = new BoxAndWhiskerCreator1File("BoxPlot Bsp6", diffList);
		boxPlot.pack();
        RefineryUtilities.centerFrameOnScreen(boxPlot);
        boxPlot.setVisible(true);
		
		System.out.println("-------------------------");
		System.out.println("Differenz Liste:");
		for(int i=0; i<datei1.size(); i++)
			System.out.println(diffList.get(i));
		System.out.println("-------------------------\n");
		
	}

}
