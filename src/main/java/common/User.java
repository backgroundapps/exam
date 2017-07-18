package common;

import java.io.Serializable;

public interface User extends Serializable {
    Long getId();

    void setId(Long id);

    String getLogin();

    void setLogin(String login);

    String getFullName();

    void setFullName(String fullName);

    String getStatus();

    void setStatus(String status);

    String getCurrentManagement();

    void setCurrentManagement(String currentManagement);
}
