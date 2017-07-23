package common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface Plugin extends Serializable{


    Long getId();
    String getName();
    String getDescription();
    Date getStartDate();
}
