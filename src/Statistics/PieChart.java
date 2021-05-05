


/**
 *  Library Used: JFreeChart
 *  @see {https://www.jfree.org/jfreechart}
 *
 *  Inspired from JFreeChart github account
 *
 *  @version 05.02.2021
 *  @author Efe Can Tepe
 *  @author Kardelen Ceren
 *
 *  This is the class for representing the pie chart in a panel.
 *  Parent class is the GeneralChartClass
 */


package Statistics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

import Person.Hospital;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleInsets;

public class PieChart extends GeneralChartClass {

    private static final long serialVersionUID = 1L;

    // The code block will work when the first code will executed
    static {
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",true));

    }

    String comparedBy;
    String comparedTo;

    public PieChart(String comparedBy, String comparedTo){

        this.comparedBy = comparedBy;
        this.comparedTo = comparedTo;

        System.out.println("Constructor Worked");


    }

    /**
     *  @author Efe Can Tepe
     *  @return
     *  @throws SQLException
     *
     *  This method return a dataset for drawing the necessary chart
     *  For drawing PieChart we use PieDataset
     *  @see PieDataset
     */
    private PieDataset createDataset() throws SQLException {

        DefaultPieDataset dataset = new DefaultPieDataset();

        // For comparing the items
        if(comparedBy.equals("Hospital")){

            // For comparing the items with respect to what
            if(comparedTo.equals("Icu Capacity")){
                ArrayList<Hospital> holderList = database.getAllHospital();

                for(int a = 0; a < holderList.size(); a++){
                    dataset.setValue(holderList.get(a).getHospitalName(), holderList.get(a).getIcuCapacity());
                }

            }

            else if(comparedTo.equals("Currently in hospital")){

                ArrayList<Hospital> holderList = database.getAllHospital();

                for(int a = 0; a < holderList.size(); a++){
                    dataset.setValue(holderList.get(a).getHospitalName(), holderList.get(a).getAllPatients().size());
                }
            }

            else if(comparedTo.equals("Number of Doctors")){
                ArrayList<Hospital> holderList = database.getAllHospital();

                for(int a = 0; a < holderList.size(); a++){
                    dataset.setValue(holderList.get(a).getHospitalName(), holderList.get(a).getHospitalDoctors().size());
                }
            }

        }

        System.out.println("Create DataSet Worked");

        return dataset;
    }

    private JFreeChart createChart(PieDataset pieDataset){

        JFreeChart chart = ChartFactory.createPieChart(comparedBy,pieDataset,false,true,false);

        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
                new Color(20, 20, 20), new Point(400, 200), Color.GREEN));


        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        // Pie Plot
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);
        // Pie Plot

        // use gradients and white borders for the section colours

        plot.setSectionPaint("",
                createGradientPaint(new Color(200, 200, 255), Color.BLUE));
        plot.setSectionPaint("",
                createGradientPaint(new Color(255, 200, 200), Color.RED));
        plot.setSectionPaint("",
                createGradientPaint(new Color(200, 255, 200), Color.GREEN));
        plot.setSectionPaint("",
                createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
        plot.setDefaultSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));

        // customise the section label appearance
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        // add a subtitle giving the data source

        System.out.println("Create Chart Worked");

        return chart;
    }


    private RadialGradientPaint createGradientPaint(Color c1, Color c2) {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};

        System.out.println("Create GradientPaint Worked");

        return new RadialGradientPaint(center, radius, dist,
                new Color[] {c1, c2});
    }

    public JPanel createDemoPanel() throws SQLException {
        JFreeChart chart = createChart(createDataset());
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart, true);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));

        System.out.println("Create Demo Panel Worked");

        return panel;
    }

    /** For testing purposes main class is created.
     *  We are not deleting it makes the maintainability of the code much more easier for other developers
     *  @author Efe Can Tepe
     *  @param args
     *  @throws SQLException
     */
    public static void main (String [] args) throws SQLException {
        JFrame frame = new JFrame();

        frame.add(new PieChart("Hospital", "hasan").createDemoPanel());

        frame.pack();
        frame.setVisible(true);
    }


}
