package settings;

import java.math.BigDecimal;

/**
 * Класс настроек графика
 * <p>
 * Created by maria on 01.09.17.
 */
public class Settings {

    private Type type;
    private Unit unit;

//    static {
//        try {
//            k_static = toDouble(System.getProperty("chart.static.k", "7485.3547"), '.');
//            b_static = toDouble(System.getProperty("chart.static.b", "0.7952"), '.');
//            k_dinamic = toDouble(System.getProperty("chart.dynamic.k", "2989.1136"), '.');
//            b_dinamic = toDouble(System.getProperty("chart.dynamic.b", "0.2865"), '.');
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    public Settings(Type type, Unit unit) {
        this.type = type;
        this.unit = unit;
    }
//По умолчанию читаем из настроек
    public Settings() {
        type = new Type();
        unit = new Unit();
    }

    public static Settings newDefaultInstance() {
        return new Settings(new Type("default", new BigDecimal(1d), new BigDecimal(1d)),
                new Unit("default", new BigDecimal(1d), null));
    }

    public Settings getSettings() {
        return this;
    }

    public void setSettings(Type type, Unit unit) {
        this.type = type;
        this.unit = unit;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public Type getType() {
        return this.type;
    }

//    public List<List> getUnitsList() {
//        return null;
//    }
//
//    public void addUnitToFile(String key, BigDecimal value, Function function) {
//    }
//
//    public Settings addUnitToSettings(String key, BigDecimal value, Function function) {
//        return null;
//    }
//
//    public Settings updateTypeParam(String key, BigDecimal newK, BigDecimal newB) {
//        return null;
//    }
}
