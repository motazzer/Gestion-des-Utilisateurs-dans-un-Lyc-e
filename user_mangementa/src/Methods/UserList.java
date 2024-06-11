package Methods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Profile;
import Models.Role;
import Models.User;
import connection.DatabaseConnection;

public class UserList {
	
	private boolean isUserExistInDatabase(String username) {
	    String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        
	        statement.setString(1, username);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                int count = resultSet.getInt(1);
	                return count > 0;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error checking user existence in the database for: " + username);
	    }
	    
	    return false;
	}

	public boolean addUser(User user) {
	    if (isUserExistInDatabase(user.getUsername())) {
	        System.out.println("User named: " + user.getUsername() + " already exists in the database.");
	        return false;
	    }

	    String sql = "INSERT INTO users (username, role, branch, password, marks) VALUES (?, ?, ?, ?, ?)";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, user.getUsername());
	        statement.setString(2, user.getRole().name());
	        statement.setString(3, user.getProfile().getBranch());
	        statement.setString(4, user.getPassword());
	        statement.setFloat(5, (float) 0.0);

	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("User added to the database: " + user.getUsername());
	            return true;
	        } else {
	            System.out.println("Failed to add user to the database: " + user.getUsername());
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error adding user to the database: " + user.getUsername());
	    }
	    return false;
	}


    
    
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String fetchedUsername = resultSet.getString("username");
                    Role role = Role.valueOf(resultSet.getString("role"));
                    String branch = resultSet.getString("branch");

                    Profile profile = new Profile(branch);
                    User user = new User(fetchedUsername, role, profile, password);

                    System.out.println("Login into " + user.getUsername());
                    return user;
                } else {
                    System.out.println("No user with this name or incorrect password.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error during login.");
        }

        return null;
    }


    public boolean editUser(User user ,User currentUser) {
    	 if (currentUser.hasRole(Role.ADMINISTRATOR)) {
             String sql = "UPDATE users SET branch = ? WHERE username = ?";

             try (Connection connection = DatabaseConnection.getConnection();
                  PreparedStatement statement = connection.prepareStatement(sql)) {

                 statement.setString(1, user.getProfile().getBranch());
                 statement.setString(2, user.getUsername());

                 int rowsUpdated = statement.executeUpdate();
                 if (rowsUpdated > 0) {
                     System.out.println("User named "+user.getUsername()+" updated to new branch "+user.getProfile().getBranch());
                     return true;
                 } else {
                     System.out.println("Failed to update user in the database.");
                 }

             } catch (SQLException e) {
                 e.printStackTrace();
                 System.out.println("Error updating user in the database.");
             }
         } else {
             System.out.println("Insufficient privileges to edit user.");
         }
         return false;
     }

    public boolean deleteUser(String username, User currentUser) {
    	 if (currentUser.hasRole(Role.ADMINISTRATOR)) {
             String sql = "DELETE FROM users WHERE username = ?";

             try (Connection connection = DatabaseConnection.getConnection();
                  PreparedStatement statement = connection.prepareStatement(sql)) {

                 statement.setString(1, username);

                 int rowsDeleted = statement.executeUpdate();
                 if (rowsDeleted > 0) {
                     System.out.println("User deleted from the database.");
                     return true;
                 } else {
                     System.out.println("Failed to delete user from the database.");
                 }

             } catch (SQLException e) {
                 e.printStackTrace();
                 System.out.println("Error deleting user from the database.");
             }
         } else {
             System.out.println("Insufficient privileges to delete user.");
         }
         return false;
     }
    
    
    
    
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                Role role = Role.valueOf(resultSet.getString("role"));
                String branch = resultSet.getString("branch");
                String password = resultSet.getString("password");

                Profile profile = new Profile(branch);
                User user = new User(username, role, profile, password);

                allUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving users from the database.");
        }

        return allUsers;
    }

    
    
    
    
    
    public List<User> getAllUsersByRole(String role) {
        List<User> usersByRole = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, role);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    Role userRole = Role.valueOf(resultSet.getString("role"));
                    String branch = resultSet.getString("branch");
                    String password = resultSet.getString("password");

                    Profile profile = new Profile(branch);
                    User user = new User(username, userRole, profile, password);

                    usersByRole.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving users by role from the database.");
        }

        return usersByRole;
    }

    
    
    
    
    public List<User> getAllStudentsByBranch(String branch) {
        List<User> studentsByBranch = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = 'STUDENT' AND branch = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, branch);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    Role role = Role.STUDENT;
                    String studentBranch = resultSet.getString("branch");
                    String password = resultSet.getString("password");

                    Profile profile = new Profile(studentBranch);
                    User student = new User(username, role, profile, password);

                    studentsByBranch.add(student);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving students by branch from the database.");
        }

        return studentsByBranch;
    }

    
    
    
    
    public boolean transfer_User_To_other_Branch(String username, String newBranch, User currentUser) {
    	if (currentUser.hasRole(Role.ADMINISTRATOR)) {
            String sql = "UPDATE users SET branch = ? WHERE username = ? AND role = 'STUDENT'";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, newBranch);
                statement.setString(2, username);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("User named " + username + " transferred to branch: " + newBranch);
                    return true;
                } else {
                    System.out.println("User not found or not a student. Transfer failed.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error transferring user to another branch.");
            }
        } else {
            System.out.println("Only administrators can change a student's branch.");
        }
        return false;
    }
    
    
    
    
    
    
    public boolean addStudentResult(String username, float marks, User currentUser) {
        if (currentUser.hasRole(Role.ADMINISTRATOR)) {
            String sql = "UPDATE users SET marks = ? WHERE username = ? AND role = 'STUDENT'";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setFloat(1, marks);
                statement.setString(2, username);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("New marks = " + marks + " added for user: " + username);
                    return true;
                } else {
                    System.out.println("User not found or not a student. Adding marks failed.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error adding student marks to the database.");
            }
        } else {
            System.out.println("Only administrators can add student marks.");
        }
        return false;
    }

    
    
    
    
    
    public boolean editStudentResult(String username, float marks, User currentUser) {
        if (currentUser.hasRole(Role.ADMINISTRATOR)) {
            String sql = "UPDATE users SET marks = ? WHERE username = ? AND role = 'STUDENT'";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setFloat(1, marks);
                statement.setString(2, username);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Marks edited with value = " + marks + " for user: " + username);
                    return true;
                } else {
                    System.out.println("User not found or not a student. Editing marks failed.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error editing student marks in the database.");
            }
        } else {
            System.out.println("Only administrators can edit student marks.");
        }
        return false;
    }
    
    
    
    
    

    public boolean deleteStudentResult(String username, User currentUser) {
        if (currentUser.hasRole(Role.ADMINISTRATOR)) {
            String sql = "UPDATE users SET marks = 0 WHERE username = ? AND role = 'STUDENT'";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, username);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Marks deleted for user: " + username);
                    return true;
                } else {
                    System.out.println("User not found or not a student. Deleting marks failed.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error deleting student marks from the database.");
            }
        } else {
            System.out.println("Only administrators can delete student marks.");
        }
        return false;
    }

}

