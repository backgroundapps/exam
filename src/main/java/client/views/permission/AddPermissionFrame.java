package client.views.permission;

import client.Client;
import client.views.components.DefaultProperties;
import common.PluginImpl;
import common.User;
import server.process.PermissionProcess;
import server.process.PluginProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.Vector;

public class AddPermissionFrame extends JDialog {
    private Long userId;
    private User user;


    JLabel nameLabel;
    JLabel functionalityLabel = new JLabel("Functionalities : ");

    JComboBox functionalityComboBox = new JComboBox();

    JButton addButton = new JButton("Add Permission");
    JButton cancelButton = new JButton("Cancel");


    public AddPermissionFrame(Long userId) {
        this.userId = userId;
        setUser();
        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

    }

    private void setUser() {

        try {
            user = new Client().getServer().findUsersById(this.userId);


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void setupUI() {

        this.setTitle("ADD PERMISSION");

        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addButton.setBackground(Color.blue);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        nameLabel = new JLabel("User: " + user.getFullName());
        topPanel.add(nameLabel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        topPanel.add(functionalityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        //TODO Update root method
        Vector<String> vp = new Vector<>();
        vp.add("");
        vp.addAll(Arrays.asList(getFunctionalityNames()));
        functionalityComboBox = new JComboBox<>(vp);

        topPanel.add(functionalityComboBox, gbc);

        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);

    }



    private void setUpListeners() {

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {addPermission(); }
        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddPermissionFrame.this.setVisible(false);
            }
        });
    }

    private void addPermission() {
        try {
            new PermissionProcess().addPermission(user, Client.getServer().getFunctionalityByName((String)this.functionalityComboBox.getSelectedItem()));
            JOptionPane.showMessageDialog(null, "DONE!");
            setVisible(false);
        }catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "Functionality already registered!");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    public String[] getFunctionalityNames() {
        try {

            return Client.getServer().getFunctionalityMappedNames();

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