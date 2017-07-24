package client.views.permission;

import client.views.components.DefaultProperties;
import client.views.functionality.FunctionalityTableModel;
import server.process.FunctionalityProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ResultPermissionFrame extends JDialog {

    JButton cancelButton = new JButton("Cancel");
    JButton permissionButton = new JButton("Add Functionality");

    private Object[][] data;
    private Long userId;

    public ResultPermissionFrame(Object[][] data, Long userId) {
        this.data = data;
        this.userId = userId;
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("THE FUNCTIONALITIES OF SELECTED USER");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(new JLabel("To remove the functionality select it."));
        permissionButton.setBackground(Color.BLUE);
        buttonPanel.add(permissionButton);
        buttonPanel.add(cancelButton);
        this.add(new JScrollPermissionPanelBuilder(new FunctionalityTableModel(data), userId, this).getTable());
        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {


        permissionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPermissionFrame(userId).setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultPermissionFrame.this.setVisible(false);
            }
        });
    }
}