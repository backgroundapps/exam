package common;

import java.io.Serializable;
import java.util.Date;

public interface Functionality extends Serializable {

    Long getId();

    String getName();

    String getDescription();

    Date getStartDate();

    Plugin getPlugin();

}
