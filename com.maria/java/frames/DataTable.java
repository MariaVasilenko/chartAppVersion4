package frames;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class DataTable {

    private JTable table;

    public JTable createDataTable(List<String> fileNames, List<BigDecimal> Ymax) {
        Object[][] array = new String[fileNames.size()][2];
        Object[] headers = new String[] {"Имя файла", "макс.Y"};

        for(int i=0; i < fileNames.size(); i++){
            array[i][0] = fileNames.get(i);
            array[i][1] = Ymax.get(i).round(MathContext.DECIMAL32).toString();
        }

        table = new JTable(array, headers);

        return table;
    }

}
