package client;

import client.views.components.DefaultProperties;
import client.views.functionality.FunctionalityFrame;
import client.views.permission.PermissionFrame;
import client.views.plugin.PluginFrame;
import client.views.user.UserFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        initUI();
    }

    public final void initUI() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Menu");
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
        file.add(exiteMenuItem);

        menubar.add(file);

        setJMenuBar(menubar);
        setTitle("PUC EXAM");
        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}