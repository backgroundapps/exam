package client.views.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Samsung on 20/07/2017.
 */
public class CloseFrameActionListener implements ActionListener{
    JFrame frame;

    public CloseFrameActionListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
    }
}
