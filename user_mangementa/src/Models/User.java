package Models;


public class User {
	private String userid;
    private String username;
    private Role role;
    private Profile profile;
    private String password;

    

    public User(String username, Role role, Profile profile, String password) {
        this.username = username;
        this.role = role;
        this.profile = profile;
        this.password = password;
    }
    


	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean hasRole(Role checkRole) {
        return this.role == checkRole;
    }

}

