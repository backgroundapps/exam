package client.views.user;

import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel {

    String[] columnNames = {"Login", "Full Name", "Status"
            , "Current Manager", "Plugins", "Functionalities", "Detail" };

    Object[][] data = {
            {"César Cielo", "Filho", "Brazil", "50m freestyle",1 , "21.30","Details"},
            {"Amaury", "Leveaux", "France", "50m freestyle", 2, "21.45", false },
            {"Hugues", "Duboscq", "France", "100m breaststroke", 3, "59.37", false }
    };

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
        return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) { return false; }

}
