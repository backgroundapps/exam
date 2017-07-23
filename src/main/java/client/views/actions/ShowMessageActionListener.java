package client.views.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMessageActionListener implements ActionListener{
    private String message;

    public ShowMessageActionListener(String message) {
        this.message = message;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, message);

    }
}
