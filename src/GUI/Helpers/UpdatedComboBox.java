package GUI.Helpers;

import javax.swing.*;
import java.util.List;

public abstract class UpdatedComboBox<E> extends JComboBox<String> {
    private List<E> list;
    private String[] options;

    public void update(){
        options = createOptions();
        setModel(new javax.swing.DefaultComboBoxModel<>(options));
    };

    public abstract String[] createOptions();


    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public List<E> getList() {
        return list;
    }
}
