package client;

import client.view.functionality.FunctionalityFrame;
import client.view.permission.PermissionFrame;
import client.view.plugin.PluginFrame;
import client.view.user.UserFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class MyFrame extends JFrame {

    public MyFrame() {
        initUI();
    }

    public final void initUI() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Manager");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem exiteMenuItem = new JMenuItem("Exit", null);
        exiteMenuItem.setMnemonic(KeyEvent.VK_E);
        exiteMenuItem.setToolTipText("Exit application");
        exiteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        JMenuItem userMenuItem = new JMenuItem("User", null);
        userMenuItem.setMnemonic(KeyEvent.VK_E);
        userMenuItem.setToolTipText("User application");
        userMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                UserFrame f = new UserFrame();
                f.setVisible(true);
            }
        });

        JMenuItem functionalityMenuItem = new JMenuItem("Functionalities", null);
        functionalityMenuItem.setMnemonic(KeyEvent.VK_E);
        functionalityMenuItem.setToolTipText("Functionalities application");
        functionalityMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                FunctionalityFrame f = new FunctionalityFrame();
                f.setVisible(true);
            }
        });

        JMenuItem pluginMenuItem = new JMenuItem("Plugins", null);
        pluginMenuItem.setMnemonic(KeyEvent.VK_E);
        pluginMenuItem.setToolTipText("Plugins application");
        pluginMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PluginFrame f = new PluginFrame();
                f.setVisible(true);

            }
        });


        JMenuItem permissionMenuItem = new JMenuItem("Permissions", null);
        permissionMenuItem.setMnemonic(KeyEvent.VK_E);
        permissionMenuItem.setToolTipText("Permissions application");
        permissionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                PermissionFrame f = new PermissionFrame();
                f.setVisible(true);

            }
        });
        file.add(userMenuItem);
        file.add(functionalityMenuItem);
        file.add(pluginMenuItem);
        file.add(permissionMenuItem);
        file.add(exiteMenuItem);

        menubar.add(file);

        setJMenuBar(menubar);

        setTitle("PUC EXAM");


        JLabel l = new JLabel();
        l.setText("APP PUC");

        setContentPane(l);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}