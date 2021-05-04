package Statistics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  Pie chart
 *  @version 05.02.2021
 *  @author Efe Can Tepe
 */



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
import javax.swing.JPanel;

import Person.Hospital;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
//import org.jfree.ui.HorizontalAlignment;
//import org.jfree.ui.RectangleInsets;

public class PieChart extends GeneralChartClass {

    private static final long serialVersionUID = 1L;

    static { // The code block will work when the first code will executed
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",true));
    }

    String comparedBy;
    String comparedTo;

    public PieChart(String comparedBy, String comparedTo){

        this.comparedBy = comparedBy;
        this.comparedTo = comparedTo;

    }

    private PieDataset createDataset() throws SQLException {

        DefaultPieDataset dataset = new DefaultPieDataset();

        if(comparedBy.equals("Hospital")){

            if(comparedTo.equals("Icu Capacity")){
                ArrayList<Hospital> holderList = database.getAllHospital();

                for(int a = 0; a < holderList.size(); a++){
                    dataset.setValue(holderList.get(a).getHospitalName(), holderList.get(a).getIcuCapacity());
                }
            }

        }

        return dataset;
    }

    private JFreeChart createChart(PieDataset pieDataset){

        JFreeChart chart = ChartFactory.createPieChart(comparedBy,pieDataset,false,true,false);

        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
                new Color(20, 20, 20), new Point(400, 200), Color.GREEN));


        TextTitle t = chart.getTitle();
//        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
//        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        // Pie Plot
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);
        // Pie Plot

        // use gradients and white borders for the section colours


        // customise the section label appearance
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        // add a subtitle giving the data source


        return chart;
    }


    private RadialGradientPaint createGradientPaint(Color c1, Color c2) {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};
        return new RadialGradientPaint(center, radius, dist,
                new Color[] {c1, c2});
    }

    public JPanel createDemoPanel() throws SQLException {
        JFreeChart chart = createChart(createDataset());
//        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel = new ChartPanel(chart, false);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(600, 300));


        return panel;
    }


}
