package client.views.actions;

import client.views.user.RegisterUserFrame;
import client.views.user.SearchUserFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenSearchUserFrameActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new SearchUserFrame().setVisible(true);
    }
}
