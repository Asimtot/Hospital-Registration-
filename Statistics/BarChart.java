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

    public class BarChart extends GeneralChartClass {

        private static final long serialVersionUID = 1L;
        private String comparedBy;
        private String comparedTo;
        private JPanel chartPanel;

        /**
         * Creates a new demo instance.
         *
         * @param comparedBy  the frame title.
         */
        public BarChart(String comparedBy, String comparedTo) throws SQLException {

            this.comparedBy = comparedBy;
            this.comparedTo = comparedTo;

            CategoryDataset dataset = createDataset();
            JFreeChart chart = createChart(dataset);
            ChartPanel chartPanel = new ChartPanel(chart, false);
            chartPanel.setFillZoomRectangle(true);
            chartPanel.setMouseWheelEnabled(true);
            chartPanel.setPreferredSize(new Dimension(500, 270));

            this.chartPanel = chartPanel;
        }


        private CategoryDataset createDataset() throws SQLException {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            if(comparedBy.equals("Hospital")){

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

            // ******************************************************************
            //  More than 150 demo applications are included with the JFreeChart
            //  Developer Guide...for more information, see:
            //
            //  >   http://www.object-refinery.com/jfreechart/guide.html
            //
            // ******************************************************************

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setDrawBarOutline(false);
            chart.getLegend().setFrame(BlockBorder.NONE);
            return chart;
        }

        public JPanel createDemoPanel(){
            return chartPanel;
        }

}
