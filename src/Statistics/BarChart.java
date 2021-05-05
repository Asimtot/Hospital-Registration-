/**
 *  Library Used: JFreeChart
 *  @see {https://www.jfree.org/jfreechart}
 *
 *  Inspired from JFreeChart github account
 *
 *  @author Efe Can Tepe
 *  This is the class for creating a BarChart with respect to information on the database
 *  Its parent class is the GeneralChart class and it belongs to package statistics
 */

package Statistics;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import Person.Hospital;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

    // Constructor
    public class BarChart extends GeneralChartClass {

        private static final long serialVersionUID = 1L;
        private String comparedBy;
        private String comparedTo;
        private JPanel chartPanel; // Chart panel for returning the panel to Tabbed Pane.

        /*
         *  throws SQLException for any exception caused by SQL
        */
        public BarChart(String comparedBy, String comparedTo) throws SQLException {

            this.comparedBy = comparedBy;
            this.comparedTo = comparedTo;

            CategoryDataset dataset = createDataset(); // Creating the dataset
            JFreeChart chart = createChart(dataset); // We need to pass the dataset for creating the chart
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setFillZoomRectangle(true);
            chartPanel.setMouseWheelEnabled(true);
            chartPanel.setPreferredSize(new Dimension(500, 270));

            this.chartPanel = chartPanel;
        }

        /**
         *  @author Efe Can Tepe
         *  @return
         *  @throws SQLException
         *
         *  It will create a dataset and returns it.
         *  In basic terms, dataset is a necessary information for drawing a chart
         */
        private CategoryDataset createDataset() throws SQLException {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            // It checks the which item we are iterating in the combo box
            if(comparedBy.equals("Hospital")){


                // Value which is represented in the bar chart
                if(comparedTo.equals("Icu Capacity")){
                    ArrayList<Hospital> holderList = database.getAllHospital();

                    for(int a = 0; a < holderList.size(); a++){
                        dataset.setValue(holderList.get(a).getIcuCapacity(), holderList.get(a).getHospitalName(), holderList.get(a).getHospitalName());
                    }
                }

                if(comparedTo.equals("Currently in Hospital")){
                    ArrayList<Hospital> holderList = database.getAllHospital();

                    for(int a = 0; a < holderList.size(); a++){
                        dataset.setValue(holderList.get(a).getAllPatients().size(),holderList.get(a).getHospitalName(),holderList.get(a).getHospitalName());
                    }
                }

                if(comparedTo.equals("Number of Doctors")){
                    ArrayList<Hospital> holderList = database.getAllHospital();

                    for(int a = 0; a < holderList.size(); a++){
                        dataset.setValue(holderList.get(a).getHospitalDoctors().size(), holderList.get(a).getHospitalName(), holderList.get(a).getHospitalName());
                    }
                }

            }

            return dataset;
        }


        private static JFreeChart createChart(CategoryDataset dataset) {
            JFreeChart chart = ChartFactory.createBarChart(
                    "", null /* x-axis label*/,
                    "" /* y-axis label */, dataset);
            chart.addSubtitle(new TextTitle(""
                    + ""));
            chart.setBackgroundPaint(Color.WHITE);
            CategoryPlot plot = (CategoryPlot) chart.getPlot();

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            chart.getLegend().setFrame(BlockBorder.NONE);
            return chart;
        }

        /**
         *  @author Efe Can Tepe
         *  @return
         *
         *  Returning the chart panel which holds the chart for adding panel into the chartPanel
         */
        public JPanel createDemoPanel(){
            return chartPanel;
        }

}
