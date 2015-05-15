package at.ac.uni.das.util;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
 
public class HistoFarbig extends Frame {
 
    // Fensterbreite
    public static final int FRAME_WIDTH  = 640;
 
    // Fensterhoehe
    public static final int FRAME_HEIGHT = 480;
 
    // Fenstertitel
    public static final String APP_TITLE = "Histogramm";
 
    private static double diff1;
    private static double diff2;
    static int max1;
    static int max2;
    static Map<Integer,Integer> werte1=new HashMap<Integer,Integer>();
	static Map<Integer,Integer> werte2=new HashMap<Integer,Integer>();
	
    Color[] farben  = {
        Color.black,
        Color.blue,
        Color.cyan,
        Color.gray,
        Color.magenta,
        Color.green,
        Color.red,
    };
    static Frame f1 = null;
 
    public HistoFarbig(double diff12, double diff22,
			Map<Integer, Integer> werte12, Map<Integer, Integer> werte22, int max1, int max2) {
		this.diff1=diff1;
		this.diff2=diff2;
		this.werte1=werte1;
		this.werte2=werte2;
		this.max1=max1;
		this.max2=max2;
		
    	//HistoFarbig f1 = new HistoFarbig(diff1,diff2, werte1, werte2, max1, max2);
        f1.setTitle(APP_TITLE);
        f1.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f1.setVisible(true);
 
        // "WindowListener" setzen, damit wenigstens der Close-Button
        // eine Funktion (System.exit(0)) hat. Alle anderen Methoden
        // _muessen_ auch implementiert werden, haben hier aber keine
        // Funktion.
        f1.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {}
            public void windowIconified(WindowEvent e) {}
            public void windowDeiconified(WindowEvent e) {}
            public void windowDeactivated(WindowEvent e) {}
            public void windowClosing(WindowEvent e) { System.exit(0); }
            public void windowClosed(WindowEvent e) {}
            public void windowActivated(WindowEvent e) {}
        });
  
    }
 
   
	public void paint (Graphics g) {
        super.paint(g);
 
        // Double, um ohne Rundungsfehler rechnen zu koennen:
        double frameWidth  = new Double(this.getWidth());
        double frameHeight = new Double(this.getHeight());
 
        Graphics2D g2 = (Graphics2D) g;
 
        // Die maximale Breite, die ein Rechteck des Charts haben soll:
        // Die Gesamtbreite gleichmaessig auf die Anzahl der Zahlen
        // aufgeteilt und davon 60%.
       // double width  = frameWidth / zahlen.length * .6;
        //double xSpace = ( frameWidth / zahlen.length - width ) / 2;
	      double width  = frameWidth / diff1;
	      double xSpace = ( frameWidth / werte1.size() - width ) / 2;
        
        // Die maximale Hohe, die ein Rechteck des Charts haben soll:
        // 90% der Gesamthoehe
        double maxHeight = frameHeight * .90;
        int i=0;
        // Messwertreihe durchgehen und Balkengrafiken aufbauen
      //  for (int i = 0; i < zahlen.length; i++) {
        for(Map.Entry e : werte1.entrySet()){
			  System.out.println(e.getKey() + " = " + e.getValue());
			  
            // Einfaerben
            g2.setColor(farben[2]);
 
            // Hoehe des zu erstellenden Balken in Pixel. Der aktuelle _Wert_
            // wird mit dem groessten Wert innerhalb des Array in Beyiehung
            // gesetzt und der entstandene Faktor anschliessend mit "maxHeight"
            // auf seine finale Groesse gestreckt.
            double height = (Double)e.getValue() / max1 * maxHeight;
 
            // xPos soll die horizontale Position sein, an der der Balken
            // erstellt werden soll. Die Position leitet sich aus der aktuellen
            // Balkenzahl ab, die in Verhaeltnis zur Balkenanzahl gebracht wird:
            // Balken 3 von 5 muesste in der Mitte der Chartflaeche aufgebaut
            // werden. Der entstandene Faktor mit "frameWidth" gestreckt.
            //double xPos = new Double(i) / new Double(zahlen.length) * frameWidth + xSpace ;
            double xPos = new Double(i) / new Double(werte1.size()) * frameWidth + xSpace ;
            // Die vertikale Position, an der der Balken erstellt werden soll.
            double yPos = frameHeight - height;
 
            // Ein Rechteck wird anhand seiner X-Position, Y-Position, Breite und
            // Hoehe erstellt.
            Rectangle2D.Double r = new Rectangle2D.Double( xPos , yPos, width, height);
 
            // Balken zeichnen: "fill()" statt "draw()", damit das Rechteck ausgemalt
            // und nicht nur umrandet ist.
            g2.fill(r);
            i++;
        }
    }
}