package client.views.plugin;

import client.views.user.EditUserFrame;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class JScrollPluginPanelBuilder<T> {

    private AbstractTableModel tableModel;
    private JTable table;
    public JScrollPluginPanelBuilder(AbstractTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JScrollPane getTable(){
        table = new JTable(tableModel);

        //Set the column sorting functionality on
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    new EditPluginFrame((Long)table.getValueAt(row, 0)).setVisible(true);
                }


            }
        });

        //Place the JTable object in a JScrollPane for a scrolling table
        JScrollPane tableScrollPane = new JScrollPane(table);

        return tableScrollPane;
    }
}
