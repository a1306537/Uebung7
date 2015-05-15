package at.ac.uni.das.beispiele;

import java.util.List;

import org.jfree.ui.RefineryUtilities;

import at.ac.uni.das.util.BoxAndWhiskerCreator;


public class Bsp2 {

	public static void boxPlot(List<Integer> datei1, List<Integer> datei2) {
		final BoxAndWhiskerCreator boxPlot = new BoxAndWhiskerCreator("BoxPlot Bsp2", datei1, datei2);
		boxPlot.pack();
        RefineryUtilities.centerFrameOnScreen(boxPlot);
        boxPlot.setVisible(true);
	}

}
