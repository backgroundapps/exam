package client.views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchableFrame extends JDialog {

    private String windowName;
    private String fieldName;

    private JLabel label;
    private JTextField field = new JTextField();
    private JButton search = new JButton("Search");

    private ActionListener event;

    public SearchableFrame(String windowName, String fieldName, ActionListener event){
        this.windowName = windowName;
        this.fieldName = fieldName;
        this.event = event;

        setupUI();
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);
    }

    public void setupUI() {

        this.setTitle(this.windowName);

        label = new JLabel(this.fieldName);
        field = new JTextField();
        search = new JButton("Search");
        search.addActionListener(event);


        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(search);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        topPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        topPanel.add(field, gbc);

        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.weightx = 0;
        topPanel.add(search, gbc);

        this.add(topPanel);
    }

}