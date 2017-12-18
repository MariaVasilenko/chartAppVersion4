package frames;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import parser.Parser;
import parser.Ydata;
import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static parser.Parser.createDataSet;


public class ChartFrame extends JFrame {
    private Settings settings;
    private TopMenu topMenu;
    private SettingsPanel settingsPanel;
    private ChartPanel panel;
    private JTable table;
    private GridBagLayout gb;
    private GridBagConstraints gbc;


    private List<String> fileNames;
    private List<BigDecimal> Ymax;


    public ChartFrame(OpenFileFrame openFileFrame) throws HeadlessException {
        super("Chart by value");
        //размер экрана пользователя
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        setPreferredSize(dim);
        setSize(dim);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        settings = new Settings();

        gb = new GridBagLayout();
        setLayout(gb);

        gbc = new GridBagConstraints();

        topMenu = new TopMenu();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        addComponent(topMenu,0,0, 1, 4);


        settingsPanel = new SettingsPanel(settings);
        gbc.fill=GridBagConstraints.VERTICAL;
        addComponent(settingsPanel,1,0, 10, 2);

        panel = write(openFileFrame);

        table = new DataTable().createDataTable(fileNames,Ymax);
        gbc.fill=GridBagConstraints.HORIZONTAL;
        addComponent(table,1,1, 2, 3);

        gbc.fill=GridBagConstraints.HORIZONTAL;
        addComponent(panel,1,0, GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE);

        pack();
        setVisible(true);
    }

    public void addComponent(Component c, int row, int col, int nrow, int ncol) {
        gbc.gridx=col;
        gbc.gridy=row;
        gbc.gridwidth=ncol ;
        gbc.gridheight=nrow;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.ipadx = 3;
        gbc.ipady = 3;
        gbc.insets = new Insets(3, 3, 3, 3);
        gb.setConstraints(c, gbc);
        this.add(c);
    }


    private JFreeChart createChart(XYDataset dataset) {
        return createChart(dataset, settings);
    }

    private JFreeChart createChart(XYDataset dataset, Settings settings) {
        String unitStr = settings.getUnit().getKey();
        String typeStr = settings.getType().getKey();
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "График " + typeStr + " нагрузки " + " в " + unitStr,
                "Время",
                "Нагрузка " + unitStr,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );

        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.white);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setBaseShapesVisible(false);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;

    }

    public ChartPanel write(OpenFileFrame openFileFrame){
        File[] files = openFileFrame.getFiles();
        List<Parser> listParserForChart = new ArrayList<>();
        fileNames =  new ArrayList<>();
        Ymax = new ArrayList<>();
        for (File f : files) {
            try {
                Parser parser = new Parser().getXY(f);
                parser.setParserY(parser.getParserY().convertTo(settings));
                Ymax.add(max(parser.getParserY().getY()).round(MathContext.DECIMAL32));
                fileNames.add(f.getName());
                listParserForChart.add(parser);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        XYSeriesCollection dataSet = createDataSet(listParserForChart);
        JFreeChart chart = createChart(dataSet);
        return new ChartPanel(chart);
    }



}
