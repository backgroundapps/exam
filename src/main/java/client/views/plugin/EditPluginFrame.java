package client.views.plugin;

import client.views.components.DefaultProperties;
import common.Plugin;
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
import java.text.SimpleDateFormat;

public class EditPluginFrame extends JDialog {

    private Long pluginId;
    private Plugin plugin;

    JLabel nameLabel = new JLabel("Name : ");
    JLabel descriptionLabel = new JLabel("Description : ");
    JLabel startDateLabel = new JLabel("Start Date : ");
    JLabel startDateLabelValue = new JLabel();


    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();

    JButton deleteButton = new JButton("Remove");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");


    public EditPluginFrame(Long userId) {
        this.pluginId = userId;

        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

        setPlugin();


    }

    private void setPlugin(){
        try {
            plugin = new PluginProcess().findById(this.pluginId);
            addPluginViewValues();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addPluginViewValues() {
        nameField.setText(plugin.getName());
        descriptionField.setText(plugin.getDescription());
        startDateLabelValue.setText(new SimpleDateFormat("dd/MM/yyyy").format(plugin.getStartDate()));
    }

    public void setupUI() {

        this.setTitle("EDIT PLUGIN");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton.setBackground(Color.RED);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
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



        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        topPanel.add(startDateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(startDateLabelValue, gbc);

        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {

        nameField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    save();
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(isValidView()){
                    save();
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure about removing this user?", "Attention", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) delete();
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditPluginFrame.this.setVisible(false);
            }
        });
    }

    private void save() {
        try {
            new PluginProcess().update(new PluginImpl( this.nameField.getText(), this.descriptionField.getText()), pluginId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");
        this.setVisible(false);

    }

    private void delete() {
        try {
            new PluginProcess().delete(pluginId);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Done!");
        this.setVisible(false);
    }

    private boolean isValidView(){
        boolean result = true;

        if(this.nameField.getText().isEmpty()){
            result = false;
            JOptionPane.showMessageDialog(null, "Please! Inform plugin name");
        }

        return result;
    }


}