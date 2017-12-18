package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopMenu extends JMenuBar {


    public TopMenu() {
        super();
        Font font = new Font("Verdana", Font.PLAIN, 16);
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // очищаем старые значения
//                clearListParser();
//                ChartPanel chartPanel = write(getSettings());
//                setNewContentInChartFrame(chartPanel);
            }
        });

        JMenuItem saveItem = new JMenuItem("SavePicture");
        openItem.setFont(font);
        fileMenu.add(saveItem);
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton settings = new JButton("Settings");
        settings.setFont(font);
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                createSettingsFrame(getSettings());
            }
        });

//        JLabel typeLabel = new JLabel(getSettings().getType().getKey());
//        JLabel unitLabel = new JLabel(getSettings().getUnit().getKey());

        add(fileMenu);
        add(settings);
        setVisible(true);
//        menuBar.add(typeLabel);
//        menuBar.add(unitLabel);

    }
}
