package frames;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenFileFrame extends JFileChooser {
    private File[] files;


    public OpenFileFrame() {
        super(System.getProperty("user.dir"));

        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setDialogTitle("Выберите файл для загрузки");
        setDialogType(JFileChooser.OPEN_DIALOG);
        setMultiSelectionEnabled(true);
        int ret = showDialog(null, "OK");
        if (ret == JFileChooser.APPROVE_OPTION) {
            files = getSelectedFiles();
        }
    }

    public File[] getFiles() {
        return files;
    }

}
