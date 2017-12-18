package parser;

import settings.Settings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с данными Y
 *
 * Created by maria on 09.11.17.
 */
public class Ydata {

    private Settings settingsY;
    private List<BigDecimal> y;

    public List<BigDecimal> getY() {
        return y;
    }

    public void setY(List<BigDecimal> newY) {
        y = newY;
    }

    public Settings getSettingsY() {
        return settingsY;
    }

    public void setSettingsY(Settings settingsY) {
        this.settingsY = settingsY;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Конвертирует из !!!файла в необходимые единицы измерения
     *
     * @param settings - настройки величин в которые необходимо перевести данные
     * @return Ydata
     */
//static?
    public Ydata convertTo(Settings settings) {
        Ydata ydata_new = new Ydata();
        ydata_new.setSettingsY(settings);
        List<BigDecimal> newY = new ArrayList<>();
        for (BigDecimal yLine : this.getY()) {
            //формула перевода в статика-динамика+единицы измерения
            newY.add(yLine.multiply(settings.getType().getK())
                    .subtract(settings.getType().getB())
                    .multiply(settings.getUnit().getValue()));
        }
        ydata_new.setY(newY);
        return ydata_new;
    }
}
