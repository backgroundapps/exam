package common;

import java.io.Serializable;
import java.util.Date;

public interface Functionality extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Date getStartDate();

    void setStartDate(Date startDate);

    Plugin getPlugin();

    void setPlugin(Plugin plugin);
}
