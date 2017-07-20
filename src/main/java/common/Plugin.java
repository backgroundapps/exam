package common;

import java.util.Date;
import java.util.List;

public interface Plugin {


    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    Date getStartDate();
    void setStartDate(Date startDate);
    List<Functionality> getFunctionalities();
    void setFunctionalities(List<Functionality> functionalities);
}
