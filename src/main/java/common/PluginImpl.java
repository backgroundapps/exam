package common;

import java.util.Date;
import java.util.List;

public class PluginImpl implements Plugin {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private List<Functionality> functionalities;

    public PluginImpl(){}

    public PluginImpl(Long id){
        this.id= id;
    }

    public PluginImpl(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PluginImpl(String name, String description, Date startDate){
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public PluginImpl(Long id, String name, String description, Date startDate){
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    @Override
    public Long getId() { return id; }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Date getStartDate() {
        return startDate;
    }
}
