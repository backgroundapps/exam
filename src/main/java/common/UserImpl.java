package common;

public class UserImpl implements User {
    private Long id;
    private String login;
    private String fullName;
    private String status;
    private String currentManagement;

    public UserImpl() {}

    public UserImpl(Long id) {
        this.id = id;
    }

    public UserImpl(String login, String fullName, String status, String currentManagement) {
        this.login = login;
        this.fullName = fullName;
        this.status = status;
        this.currentManagement = currentManagement;

    }

    public UserImpl(Long id, String login, String fullName, String status, String currentManagement) {
        this.id = id;
        this.login = login;
        this.fullName = fullName;
        this.status = status;
        this.currentManagement = currentManagement;

    }



    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String getLogin() {
        return login;
    }
    @Override
    public void setLogin(String login) {
        this.login = login;
    }
    @Override
    public String getFullName() {
        return fullName;
    }
    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String getCurrentManagement() {
        return currentManagement;
    }
    @Override
    public void setCurrentManagement(String currentManagement) {
        this.currentManagement = currentManagement;
    }
}
