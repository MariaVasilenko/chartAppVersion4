package frames;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenFileFrame extends JFileChooser {
    private File[] files;

    private List<String> listFileName;

    public OpenFileFrame() {
        super(System.getProperty("user.dir"));

        setFileSelectionMode(JFileChooser.FILES_ONLY);
        setDialogTitle("Выберите файл для загрузки");
        setDialogType(JFileChooser.OPEN_DIALOG);
        setMultiSelectionEnabled(true);
        int ret = showDialog(null, "OK");
        if (ret == JFileChooser.APPROVE_OPTION) {
            files = getSelectedFiles();
            listFileName = new ArrayList<>();
            for (File f : files) {
                listFileName.add(f.getName());
            }

        }
    }

    public File[] getFiles() {
        return files;
    }

    public List<String> getListFileName() {
        return listFileName;
    }

}
