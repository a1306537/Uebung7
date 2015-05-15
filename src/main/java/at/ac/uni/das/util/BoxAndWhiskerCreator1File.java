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
public class BoxAndWhiskerCreator1File extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	private List<Integer> diffList = new ArrayList<Integer>();

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public BoxAndWhiskerCreator1File(final String title, List<Integer> diffList) {
        super(title);
        
        this.diffList = diffList;
        
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
        	System.out.println("------------------------");
	        System.out.println("Werte für den Boxplot:");
	        System.out.println("Minimum:         "+ dataset.getMinRegularValue("Plot", "Werte"));
	        System.out.println("Unteres Quantil: "+ dataset.getQ1Value("Plot", "Werte"));
	        System.out.println("Median:          "+ dataset.getMedianValue("Plot", "Werte"));
	        System.out.println("Oberes Quantil:  "+ dataset.getQ3Value("Plot", "Werte"));
	        System.out.println("Maximum:         "+ dataset.getMaxRegularValue("Plot", "Werte"));
	        System.out.println("------------------------");
	        System.out.println();

    }

    /**
     * Creates a sample dataset.
     * 
     * @return A sample dataset.
     */
    private BoxAndWhiskerCategoryDataset createSampleDataset() {
        final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();     
        dataset.add(diffList, "Plot", "Werte");
        return dataset;
    }
    
}