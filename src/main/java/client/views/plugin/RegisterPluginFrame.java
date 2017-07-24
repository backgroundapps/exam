package client.views.plugin;

import client.views.components.DefaultProperties;
import common.PluginImpl;
import common.UserImpl;
import server.process.PluginProcess;
import server.process.UserProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class RegisterPluginFrame extends JDialog {

    JLabel nameLabel = new JLabel("Name : ");
    JLabel descriptionLabel = new JLabel("Description : ");

    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();

    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");


    public RegisterPluginFrame() {
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("REGISTER PLUGIN");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(saveButton);
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



        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        topPanel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(descriptionField, gbc);

        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {

        nameField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    register();
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(isValidView()){
                    register();
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPluginFrame.this.setVisible(false);
            }
        });
    }

    private void register() {
        try {
            new PluginProcess().create(new PluginImpl(
                    this.nameField.getText(),
                    this.descriptionField.getText()
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");
        this.setVisible(false);

    }

    private boolean isValidView(){
        boolean result = true;

        if(this.nameField.getText().isEmpty()){
            result = false;
            JOptionPane.showMessageDialog(null, "Please! Inform plugin name.");
        }

        return result;
    }


}