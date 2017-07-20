package client.view.permission;

import client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class PermissionFrame extends JFrame {

    public PermissionFrame() {
        initUI();
    }

    public final void initUI() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Permition Manager");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem exiteMenuItem = new JMenuItem("Exit", null);
        exiteMenuItem.setMnemonic(KeyEvent.VK_E);
        exiteMenuItem.setToolTipText("Exit application");
        exiteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });

        JMenuItem userMenuItem = new JMenuItem("Number Of Users", null);
        userMenuItem.setMnemonic(KeyEvent.VK_E);
        userMenuItem.setToolTipText("User application");
        userMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    JOptionPane.showMessageDialog(null, Client.getServer().getUsers());
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