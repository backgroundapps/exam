package client.views.user;

import client.Client;
import client.views.actions.CloseFrameActionListener;
import client.views.actions.OpenSearchUserFrameActionListener;
import client.views.actions.OpenUserRegisterFrameActionListener;
import client.views.actions.ShowMessageActionListener;
import client.views.components.DefaultProperties;
import client.views.components.JMenuBarFactory;
import client.views.components.JMenuItemBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserFrame extends JFrame {
    public UserFrame() {
        initUI();
    }

    public final void initUI() {
        setJMenuBar(prepareMenuBar());
        setTitle("USER MANAGER");

        setSize(DefaultProperties.WIDTH_SIZE_FRAME, DefaultProperties.HEIGHT_SIZE_FRAME);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private JMenuBar prepareMenuBar() {
        Map<String, List<JMenuItem>> mappedMenuBar = new LinkedHashMap<>();
        List<JMenuItem> items = new LinkedList<>();

        items.add(JMenuItemBuilder.build("New", new OpenUserRegisterFrameActionListener()));
        items.add(JMenuItemBuilder.build("Search", new OpenSearchUserFrameActionListener()));
        items.add(JMenuItemBuilder.build("Exit", new CloseFrameActionListener(this)));

        mappedMenuBar.put("Menu", items);

        return JMenuBarFactory.getJMenuBarFromMappedElements(mappedMenuBar);
    }

}