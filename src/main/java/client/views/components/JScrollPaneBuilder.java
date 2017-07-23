package client.views.components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class JScrollPaneBuilder {

    private AbstractTableModel tableModel;

    public JScrollPaneBuilder(AbstractTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JScrollPane getTable(){
        JTable table = new JTable(tableModel);

        //Set the column sorting functionality on
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        //Place the JTable object in a JScrollPane for a scrolling table
        JScrollPane tableScrollPane = new JScrollPane(table);

        return tableScrollPane;
    }
}
