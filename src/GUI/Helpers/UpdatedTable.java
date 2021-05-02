package GUI.Helpers;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.util.Arrays;
import java.util.List;

public abstract class UpdatedTable<E> extends JTable {
    private String[] headers;
    private List<E> list;
    private String[][] table;
    private boolean[] editable;
    private TableRowSorter<DefaultTableModel> tableRowSorter;

    public UpdatedTable(String[] headers, boolean editable, int rowNumber) {
        this.headers = headers;
        setEditable(editable,rowNumber);
    }

    public void update(){
        table = createTable();
        putTable();
    };

    public void update(String[][] table){
        setTable(table);
        putTable();
    }

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

    public void search(String searchKey){
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        tableRowSorter = new TableRowSorter<>(model);
        this.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey));
    }

    public int getRow(){
        return convertRowIndexToModel(this.getSelectedRow());
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

    public TableRowSorter<DefaultTableModel> getTableRowSorter() {
        return tableRowSorter;
    }
}
