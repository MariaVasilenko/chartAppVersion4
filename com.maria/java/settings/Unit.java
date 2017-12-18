package settings;

import com.sun.org.apache.xpath.internal.functions.Function;

import java.math.BigDecimal;
import java.text.ParseException;

import static parser.Parser.toDouble;

/**
 * Класс единиц измерения
 *
 * Created by maria on 09.11.17.
 */
public class Unit {

    private String key;
    private BigDecimal value;
    private Function function;

    public Unit(String key, BigDecimal value, Function function) {
        this.key = key;
        this.value = value;
        this.function = function;
    }

    public Unit() {
        try{
            this.key = System.getProperty("chart.unit", "kH");
            this.value =new BigDecimal(toDouble(System.getProperty("chart.unit.value", "1.0"), '.'));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
