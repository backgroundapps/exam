package client.views.functionality;

import client.Client;
import client.views.components.DefaultProperties;
import common.FunctionalityImpl;
import common.PluginImpl;
import server.process.FunctionalityProcess;
import server.process.PluginProcess;
import server.process.UserProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

public class RegisterFunctionalityFrame extends JDialog {

    JLabel nameLabel = new JLabel("Name : ");
    JLabel descriptionLabel = new JLabel("Description : ");
    JLabel pluginLabel = new JLabel("Plugins : ");

    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();
    JComboBox<String> pluginsComboBox;


    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");


    public RegisterFunctionalityFrame() {
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    public void setupUI() {

        this.setTitle("REGISTER FUNCTIONALITY");

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


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        topPanel.add(pluginLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        //TODO Update root method
        Vector<String> vp = new Vector<>();
        vp.add("");
        vp.addAll(Arrays.asList(getPluginNames()));
        pluginsComboBox = new JComboBox<>(vp);
        topPanel.add(pluginsComboBox, gbc);


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
                RegisterFunctionalityFrame.this.setVisible(false);
            }
        });
    }

    private void register() {
        try {

            new FunctionalityProcess().create(new FunctionalityImpl(

                    this.nameField.getText(),
                    this.descriptionField.getText(),
                    getPluginIdByName((String)this.pluginsComboBox.getSelectedItem())

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
            JOptionPane.showMessageDialog(null, "Please! Inform functionality name.");
        }

        if("".equals(this.pluginsComboBox.getSelectedItem())){
            result = false;
            JOptionPane.showMessageDialog(null, "Please! Inform plugin name.");
        }


        return result;
    }

    private String[] getPluginNames(){
        try {

            return Client.getServer().getPluginMappedNames();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Long getPluginIdByName(String name) {
        try {
            return Client.getServer().getPluginByName(name).getId();

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}