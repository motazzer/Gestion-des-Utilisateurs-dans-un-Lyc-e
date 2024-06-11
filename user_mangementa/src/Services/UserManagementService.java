package Services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Methods.UserList;
import Models.Profile;
import Models.Role;
import Models.User;

@WebService
public class UserManagementService {
    private UserList userList = new UserList();
    private User currentUser;

    @WebMethod
    public boolean registerUser(String username, String role, String branch,String password) {
    	Profile profile = new Profile(branch);
    	
    	if (role.equals("ADMINISTRATOR")) {
            profile.setBranch("admin-dont-have-branche");
        }
		User newUser = new User(username, Role.valueOf(role), profile,password);
		return userList.addUser(newUser);
    }
    
    @WebMethod
    public User loginUser(String username,String password) {
        User user = userList.loginUser(username,password);
        if (user != null) {
            currentUser = user; 
        }
        return user;
    }

    @WebMethod
    public boolean deleteUser(String username) {
    	if (currentUser != null) {
            return userList.deleteUser(username, currentUser);
        }
        return false;

    }

    @WebMethod
    public boolean editUser(String username, String branch) {
    	if (currentUser != null) {
        	Profile profile = new Profile(branch);
            User user = new User(username,currentUser.getRole() ,profile, "");
            return userList.editUser(user,currentUser);
        }
        return false;
    }
    
    @WebMethod
    public List<User> getAllUsers() {
        return userList.getAllUsers();
    }
    @WebMethod
    public List<User> getAllUsersByRole(String role) {
        return userList.getAllUsersByRole(role);
    }
    @WebMethod
    public List<User> getAllStudentsByBranch(String branch) {
        return userList.getAllStudentsByBranch(branch);
    }
    
    @WebMethod
    public boolean transferUserToBranch(String username, String newBranch) {
    	if (currentUser != null) {
            return userList.transfer_User_To_other_Branch(username, newBranch, currentUser);
        }
        return false;
    }
    
    @WebMethod
    public boolean addStudentResult(String username, float marks) {
    	if (currentUser != null) {
            return userList.addStudentResult(username, marks, currentUser);
        }
        return false;
    }

    @WebMethod
    public boolean editStudentResult(String username, float marks) {
    	if (currentUser != null) {
        return userList.editStudentResult(username, marks, currentUser);
    	}
        return false;
    }

    @WebMethod
    public boolean deleteStudentResult(String username) {
    	if (currentUser != null) {
        return userList.deleteStudentResult(username, currentUser);
    }
    return false;
    }
}

