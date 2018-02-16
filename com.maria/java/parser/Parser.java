package parser;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.max;
import static settings.Settings.newDefaultInstance;

/**
 * Класс парсера
 *
 * Created by maria on 01.09.17.
 */
public class Parser {

    private Ydata y = new Ydata();
    private Xdata x = new Xdata();
    private String fileName = null;


    public String getFileName() { return fileName;}

    public Ydata getParserY() {
        return y;
    }

    public void setParserY(Ydata y) {
        this.y = y;
    }

    public Xdata getParserX() {
        return x;
    }


    public List<String> getXYMax(Parser p) {
        return null;
    }


    public Parser getXY(File file) throws ParseException {
        Scanner s = null;
        try {
            s = new Scanner(file, "windows-1251");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<BigDecimal> y_0 = new ArrayList<>();
        List<BigInteger> x = new ArrayList<>();

        if (s.hasNextLine()) while (s.hasNextLine()) {
            String line = s.nextLine();
            if ((line.length() != 0) && (!line.contains("Отсчеты"))) {
                int first = line.indexOf(";");
                int last = line.lastIndexOf(";");
                String lineY = line.substring(first + 1, last);
                double bdY = toDouble(lineY, ',');
                x.add(new BigInteger(line.substring(0, first)));
                y_0.add(new BigDecimal(bdY).round(MathContext.DECIMAL32));
            } else {
                System.out.println("символьная строка");
            }
        }
        else System.out.println( "следующей строки не найдено");

        this.x.setX(x);
        this.x.setUnit("step", new BigDecimal(1d), null);
        this.y.setY(y_0);
        this.y.setSettingsY(newDefaultInstance());
        this.fileName = file.getName();
        return this;
    }

    ///////////////////////////////////////////////////////////////////////

    public static XYSeriesCollection createDataSet(List<Parser> parserList) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        for (Parser p : parserList) {
            int size = p.getParserY().getY().size();
            if (size != 0) {
                BigDecimal maxValueY = max(p.getParserY().getY()).round(MathContext.DECIMAL32);
                XYSeries series = new XYSeries("Файл: "+ p.getFileName() + " макс.нагузка = "+ maxValueY + " ");
                for(int i=0; i < size; i++){
                    series.add(p.getParserX().getX().get(i),
                            p.getParserY().getY().get(i), true);
                }
                dataset.addSeries(series);
            } else System.out.println("Y пуст");
        }
        return dataset;
    }

    public static double toDouble(String s, char decimalSeparator) throws ParseException {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(decimalSeparator);
        df.setDecimalFormatSymbols(dfs);
        return df.parse(s).doubleValue();
    }
}
