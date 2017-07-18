package common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Samsung on 13/07/2017.
 */
public interface Functionality extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Date getStartDate();

    void setStartDate(Date startDate);
}
