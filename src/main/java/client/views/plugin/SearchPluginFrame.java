package client.views.plugin;

import client.views.components.DefaultProperties;
import server.process.PluginProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class SearchPluginFrame extends JDialog {

    JLabel nameLabel = new JLabel("Login : ");


    JTextField nameField = new JTextField();

    JButton searchButton = new JButton("Search");
    JButton cancelButton = new JButton("Cancel");


    public SearchPluginFrame() {
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {
        this.setTitle("SEARCH PLUGIN");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        topPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(nameField, gbc);


        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {

        nameField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    search();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SearchPluginFrame.this.setVisible(false);
            }
        });
    }


    private void search()  {
        try {

            Object[][] data = new PluginProcess().getFullPluginData(
                    this.nameField.getText()
            );

            if(data != null && data.length > 0){
                new ResultPluginFrame(data).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "DATA NOT FOUND!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}