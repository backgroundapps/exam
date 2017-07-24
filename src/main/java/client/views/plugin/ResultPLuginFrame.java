package client.views.plugin;

import client.views.components.DefaultProperties;
import client.views.user.JScrollUserPanelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPluginFrame extends JDialog {

    JButton cancelButton = new JButton("Cancel");
    private Object[][] data;


    public ResultPluginFrame(Object[][] data) {
        this.data = data;
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("RESULT PLUGIN");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(new JLabel("To sort the result click on the column title."));
        buttonPanel.add(cancelButton);
        this.add(new JScrollPluginPanelBuilder(new PluginTableModel(data)).getTable());
        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {


        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultPluginFrame.this.setVisible(false);
            }
        });
    }

}