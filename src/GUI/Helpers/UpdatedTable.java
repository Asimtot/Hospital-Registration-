package GUIHelpers;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public abstract class UpdatedTable<E> extends JTable {
    private String[] headers;
    private List<E> list;
    private String[][] table;
    private boolean[] editable;

    public UpdatedTable(String[] headers, boolean editable, int rowNumber) {
        this.headers = headers;
        setEditable(editable,rowNumber);
    }

    public void update(){
        table = createTable();
        putTable();
    };

    public abstract String[][] createTable();

    private void putTable(){
        setModel(new javax.swing.table.DefaultTableModel(
                table,
                headers
        ) {
            boolean[] canEdit = editable;
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    public void setEditable(boolean editable, int rowNumber){
        boolean[] booleans = new boolean[rowNumber];
        Arrays.fill(booleans, editable);
        this.editable = booleans;
    }

    public void setEditableByRow(boolean[] editable) {
        this.editable = editable;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public List<E> getList() {
        return list;
    }
}
