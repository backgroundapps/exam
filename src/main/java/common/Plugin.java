package common;

import java.util.Date;

public interface Plugin {

    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    Date getStartDate();
    void setStartDate(Date startDate);
}
