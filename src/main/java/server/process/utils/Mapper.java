package server.process.utils;

import common.Functionality;
import common.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<String> getPluginNames(List<Plugin> objs){
        List<String> list = new ArrayList<>();
        for (Plugin obj : objs) {
            list.add(obj.getName());
        }
        return list;
    }

    public static  List<String>  getFunctionalityNames(List<Functionality> objs) {
        List<String> list = new ArrayList<>();
        for (Functionality obj : objs) {
            list.add(obj.getName());
        }
        return list;
    }
}
