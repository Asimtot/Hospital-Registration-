package Statistics;

import Database.Database;

public class GeneralChartClass {

    public Database database;
    public String comparedBy;
    public String comparedTo;

    public GeneralChartClass (){
        database = new Database();
    }

}