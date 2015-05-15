package at.ac.uni.das.util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * Demonstration of a box-and-whisker chart using a {@link CategoryPlot}.
 *
 * @author David Browning
 */
public class BoxAndWhiskerCreator extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	private List<Integer> datei1 = new ArrayList<Integer>();
	private List<Integer> datei2 = new ArrayList<Integer>();

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BoxAndWhiskerCreator(final String title, List<Integer> datei1, List<Integer> datei2) {
        super(title);
        
        this.datei1 = datei1;
        this.datei2 = datei2;
        
        final BoxAndWhiskerCategoryDataset dataset = createSampleDataset();

        final CategoryAxis xAxis = new CategoryAxis(null);
        final NumberAxis yAxis = new NumberAxis("Werte");
        yAxis.setAutoRangeIncludesZero(false);
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.WHITE);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
        renderer.setUseOutlinePaintForWhiskers(true);
        renderer.setMeanVisible(false);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        
        final JFreeChart chart = new JFreeChart(
            title,
            new Font("SansSerif", Font.BOLD, 14),
            plot,
            true
        );
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(450, 370));
        setContentPane(chartPanel);
        
        //Ausgabe der Daten
        for(int i=1; i <= 2; i++) {
        	System.out.println("------------------------");
	        System.out.println("Werte für den Boxplot "+ i +":");
	        System.out.println("Minimum:         "+ dataset.getMinRegularValue("Plot", "Werte"+i));
	        System.out.println("Unteres Quantil: "+ dataset.getQ1Value("Plot", "Werte"+i));
	        System.out.println("Median:          "+ dataset.getMedianValue("Plot", "Werte"+i));
	        System.out.println("Oberes Quantil:  "+ dataset.getQ3Value("Plot", "Werte"+i));
	        System.out.println("Maximum:         "+ dataset.getMaxRegularValue("Plot", "Werte"+i));
	        System.out.println("------------------------");
	        System.out.println();
    	}

    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private BoxAndWhiskerCategoryDataset createSampleDataset() {
        final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();     
        dataset.add(datei1, "Plot", "Werte1");
        dataset.add(datei2, "Plot", "Werte2");

        return dataset;
    }
}