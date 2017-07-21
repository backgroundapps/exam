package common;

import java.util.Date;

public class FunctionalityImpl implements Functionality {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Plugin plugin;

    public FunctionalityImpl(Long id){
        this.id = id;
    }

    public FunctionalityImpl(String name, String description, Plugin plugin ){
        this.name = name;
        this.description = description;
        this.plugin = plugin;
    }

    public FunctionalityImpl(String name, String description, Date startDate, Plugin plugin ){
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.plugin = plugin;
    }

    public FunctionalityImpl(Long id, String name, String description, Date startDate, Plugin plugin){
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.plugin = plugin;

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
    public Date getStartDate() { return startDate; }
    @Override
    public Plugin getPlugin() { return plugin; }
}
