package settings;

import com.sun.org.apache.xpath.internal.functions.Function;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс для настроек графика
 *
 * Created by maria on 01.09.17.
 */
public interface SettingsBase {
    /**
     * Метод получения настроек графика
     */
    Settings getSettings();

    void setSettings(Type type, Unit unit);

    /**
     * Units
     */

   List<List> getUnitsList();

   List<String> getUnit(String key);

   void addUnitToFile(String key, BigDecimal value, Function function);

   Settings addUnitToSettings(String key, BigDecimal value, Function function);

    /**
     * Types
     */
    List<String> getType();

    List<String> getType(String key);

    Settings updateTypeParam(String key, BigDecimal newK, BigDecimal newB);

}
