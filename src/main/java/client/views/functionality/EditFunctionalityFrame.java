package client.views.functionality;

import client.Client;
import client.views.components.DefaultProperties;
import common.Functionality;
import common.FunctionalityImpl;
import common.PluginImpl;
import server.process.FunctionalityProcess;
import server.process.PluginProcess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Vector;

public class EditFunctionalityFrame extends JDialog {

    private Long functionalityId;
    private Functionality functionality;

    JLabel nameLabel = new JLabel("Name : ");
    JLabel descriptionLabel = new JLabel("Description : ");
    JLabel startDateLabel = new JLabel("Start Date : ");
    JLabel startDateLabelValue = new JLabel();
    JLabel pluginLabel = new JLabel("Plugins : ");

    JTextField nameField = new JTextField();
    JTextField descriptionField = new JTextField();
    JComboBox<String> pluginsComboBox;

    JButton deleteButton = new JButton("Remove");
    JButton saveButton = new JButton("Save");
    JButton cancelButton = new JButton("Cancel");


    public EditFunctionalityFrame(Long functionalityId) {
        this.functionalityId = functionalityId;

        setupUI();
        setUpListeners();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);

        setPlugin();


    }

    private void setPlugin(){
        try {
            functionality = new FunctionalityProcess().findById(this.functionalityId);
            addFunctionalityViewValues();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addFunctionalityViewValues() {
        nameField.setText(functionality.getName());
        descriptionField.setText(functionality.getDescription());
        startDateLabelValue.setText(new SimpleDateFormat("dd/MM/yyyy").format(functionality.getStartDate()));
        pluginsComboBox.setSelectedItem(getSelectedItem(functionality));
    }

    public void setupUI() {

        this.setTitle("EDIT FUNCTIONALITY");

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

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        topPanel.add(pluginLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
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
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure about removing this functionality ?", "Attention", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) delete();
            }

        });

        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                EditFunctionalityFrame.this.setVisible(false);
            }
        });
    }

    private void save() {
        try {
            new FunctionalityProcess().update(new FunctionalityImpl( this.nameField.getText(), this.descriptionField.getText(), getSelectedPluginIdByName()), functionalityId);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Done!");
        this.setVisible(false);

    }

    private void delete() {
        try {
            new FunctionalityProcess().delete(functionalityId);

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
            JOptionPane.showMessageDialog(null, "Please! Inform functionality name");
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

    private Object getSelectedItem(Functionality functionality) {
        try {
            return (String)new PluginProcess().findById(functionality.getPluginId()).getName();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Long getSelectedPluginIdByName() {
        try {
            return new PluginProcess().findByName((String)this.pluginsComboBox.getSelectedItem()).getId();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}