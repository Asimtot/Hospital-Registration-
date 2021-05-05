/**
 *  @author Efe Can Tepe
 *  This is the parent class of the PieChart and BarChart
 *  These 2 charts both have common attributes such as having a database connection
 *  a String which represents which quantity is compared
 *  a String which represents which quantity will be compared respect to
 */

package Statistics;

import Database.Database;

public class GeneralChartClass {

    public Database database;
    public String comparedBy;
    public String comparedTo;

    public GeneralChartClass (){
        database = new Database(); // Getting the database connection for creating statistics
    }

}