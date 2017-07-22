package client.views.user;

import client.Client;
import client.views.actions.CloseFrameActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class UserFrame extends JFrame {

    public UserFrame() {
        initUI();
    }

    public final void initUI() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("User Manager");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem exiteMenuItem = new JMenuItem("Exit", null);
        exiteMenuItem.setMnemonic(KeyEvent.VK_E);
        exiteMenuItem.setToolTipText("Exit application");
        exiteMenuItem.addActionListener(new CloseFrameActionListener(this));

        JMenuItem userMenuItem = new JMenuItem("Number Of Users", null);
        userMenuItem.setMnemonic(KeyEvent.VK_E);
        userMenuItem.setToolTipText("User application");
        userMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    JOptionPane.showMessageDialog(null, Client.getServer().getNumberOfUsers());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        JMenuItem registerMenuItem = new JMenuItem("Register", null);
        registerMenuItem.setMnemonic(KeyEvent.VK_E);
        registerMenuItem.setToolTipText("User application");
        registerMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    JOptionPane.showMessageDialog(null, Client.getServer().getNumberOfUsers());
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                }
            }
        });

        file.add(userMenuItem);
        file.add(registerMenuItem);
        file.add(exiteMenuItem);

        menubar.add(file);

        setJMenuBar(menubar);

        setTitle("PUC EXAM");


        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

}