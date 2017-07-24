package client.views.functionality;

import client.views.components.DefaultProperties;
import client.views.plugin.JScrollPluginPanelBuilder;
import client.views.plugin.PluginTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFunctionalityFrame extends JDialog {

    JButton cancelButton = new JButton("Cancel");
    private Object[][] data;


    public ResultFunctionalityFrame(Object[][] data) {
        this.data = data;
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("RESULT FUNCTIONALITY");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(new JLabel("To sort the result click on the column title."));
        buttonPanel.add(cancelButton);
        this.add(new JScrollFunctionalityPanelBuilder(new FunctionalityTableModel(data)).getTable());
        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {


        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultFunctionalityFrame.this.setVisible(false);
            }
        });
    }

}