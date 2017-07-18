package common;

import java.util.Date;

/**
 * Created by Samsung on 14/07/2017.
 */
public class FunctionalityImpl implements Functionality {
    private Long id;
    private String name;
    private String description;
    private Date startDate;

    public FunctionalityImpl(){}

    public FunctionalityImpl(String name, String description, Date startDate ){
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    public FunctionalityImpl(Long id, String name, String description, Date startDate ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
    }

    @Override
    public Long getId() { return id; }
    @Override
    public void setId(Long id) { this.id = id; }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public Date getStartDate() {
        return startDate;
    }
    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
