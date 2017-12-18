package frames;

import settings.Settings;

import javax.swing.*;

public class SettingsPanel extends JPanel{

    public SettingsPanel(Settings settings) {
        super();
        JLabel label = new JLabel("SettingsPanel");
        add(label);
        setVisible(true);
    }
}
