package client.views.user;

import client.views.components.JScrollPaneBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ResultUserFrame extends JDialog {

    JButton cancelButton = new JButton("Cancel");
    private Object[][] data;


    public ResultUserFrame(Object[][] data) {
        this.data = data;
        setupUI();
        setUpListeners();
        setSize(400, 220);

    }

    public void setupUI() {

        this.setTitle("RESULT USER");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(new JLabel("To sort the result click on the column title."));
        buttonPanel.add(cancelButton);
        this.add(new JScrollPaneBuilder(new UserTableModel(data)).getTable());
        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {


        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ResultUserFrame.this.setVisible(false);
            }
        });
    }

}