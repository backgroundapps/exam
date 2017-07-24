package client.views.components;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class JScrollPaneBuilder {

    private AbstractTableModel tableModel;
    private JTable table;
    public JScrollPaneBuilder(AbstractTableModel tableModel) {
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
                    JOptionPane.showMessageDialog(null, table.getValueAt(row, 0));

                }
            }
        });

        //Place the JTable object in a JScrollPane for a scrolling table
        JScrollPane tableScrollPane = new JScrollPane(table);

        return tableScrollPane;
    }
}
