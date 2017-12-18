package parser;

import com.sun.org.apache.xpath.internal.functions.Function;
import settings.Unit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Класс для работы с данными X
 *
 * Created by maria on 01.09.17.
 */
public class Xdata {

    private Unit unit;
    private List<BigInteger> x;

    public List<BigInteger> getX() {
        return x;
    }

    public void setX(List<BigInteger> newX) {
        x = newX;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit u) {
        unit = u;
    }

    public void setUnit(String key, BigDecimal value, Function function) {
        unit = new Unit(key, value, function);
    }

}
