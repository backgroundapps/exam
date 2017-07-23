package client.views.actions;

import client.views.user.RegisterUserFrame;
import client.views.user.UserFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenUserRegisterFrameActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new RegisterUserFrame().setVisible(true);
    }
}
