package settings;

import java.math.BigDecimal;
import java.text.ParseException;

import static parser.Parser.toDouble;

/**
 * Тип графика
 *
 * Created by maria on 09.11.17.
 */
public class Type {
    private String key;
    private BigDecimal k;
    private BigDecimal b;


    public Type() {
        try {
            this.key = System.getProperty("chart.type", "dynamic");
//            this.k = new BigDecimal(toDouble(System.getProperty("chart.k", "7485.3547"), '.'));
//            this.b = new BigDecimal(toDouble(System.getProperty("chart.b", "0.7952"), '.'));

            this.k = new BigDecimal(toDouble(System.getProperty("chart.k", "2989.1136"), '.'));
            this.b = new BigDecimal(toDouble(System.getProperty("chart.b", "0.2865"), '.'));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Type(String key, BigDecimal k, BigDecimal b) {
        this.key = key;
        this.k = k;
        this.b = b;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getK() {
        return k;
    }

    public void setK(BigDecimal k) {
        this.k = k;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }
}
