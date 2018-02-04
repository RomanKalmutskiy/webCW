package models;


public class User {
    private String login;
    private String password;
    private String name;
    private String gender;
    private String area;
    private String comments;
    private String amigo;
    

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public User() {

    }
    
    

    public User(String login, String password, String name, String gender, String area, String comments, String amigo) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.area = area;
		this.comments = comments;
		this.amigo = amigo;
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAmigo() {
		return amigo;
	}

	public void setAmigo(String amigo) {
		this.amigo = amigo;
	}
    
    
    
    
}
