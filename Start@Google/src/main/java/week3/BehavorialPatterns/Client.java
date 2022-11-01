package week3.BehavorialPatterns;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        List<Appliance> applianceList = new ArrayList<>();
        applianceList.add(new Group());
        applianceList.add(new User());
        applianceList.add(new Asset());

        ExportToJSON exportToJSON = new ExportToJSON();
        for (Appliance appliance: applianceList) {
            appliance.visit(exportToJSON);
        }
    }
}
