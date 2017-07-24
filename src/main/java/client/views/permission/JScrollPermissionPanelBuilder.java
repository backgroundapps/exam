package client.views.permission;

import client.views.functionality.EditFunctionalityFrame;
import server.dao.UserFunctionalityPermissionDAO;
import server.process.PermissionProcess;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;

public class JScrollPermissionPanelBuilder<T> {

    private AbstractTableModel tableModel;
    private JTable table;
    private Long userId;
    private ResultPermissionFrame resultPermissionFrame;

    public JScrollPermissionPanelBuilder(AbstractTableModel tableModel, Long userId, ResultPermissionFrame resultPermissionFrame) {
        this.tableModel = tableModel;
        this.userId = userId;
        this.resultPermissionFrame = resultPermissionFrame;
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
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure about removing this functionality ?", "Attention", JOptionPane.YES_NO_OPTION);

                    if(reply == JOptionPane.YES_OPTION){
                        delete(userId, (Long)table.getValueAt(row, 0));
                        resultPermissionFrame.setVisible(false);
                    }
                }


            }

            private void delete(Long userId, Long functionalityId) {
                try {
                    new PermissionProcess().removePermissionById(userId, functionalityId);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //Place the JTable object in a JScrollPane for a scrolling table
        JScrollPane tableScrollPane = new JScrollPane(table);

        return tableScrollPane;
    }



}
