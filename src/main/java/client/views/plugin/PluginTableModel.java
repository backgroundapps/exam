package client.views.plugin;

import javax.swing.table.AbstractTableModel;

public class PluginTableModel extends AbstractTableModel {
    private Object[][] data;

    public PluginTableModel(Object[][] data) {
        this.data = data;
    }

    String[] columnNames = {"Plugin ID", "Name", "Description", "Start Date"};


    @Override
    public int getRowCount()
    {
        return data.length;
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        return data[row][column];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class getColumnClass(int c) {
        if(getValueAt(0, c) != null){
            return getValueAt(0, c).getClass();
        } else {
            return new Object().getClass();
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) { return false; }

}
