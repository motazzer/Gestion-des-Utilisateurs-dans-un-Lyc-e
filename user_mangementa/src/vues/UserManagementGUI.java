package vues;

import javafx.application.Application;
import javafx.collections.FXCollections;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

import java.util.List;

import Models.User;
import Services.UserManagementService;

public class UserManagementGUI extends Application {
    private UserManagementService userManagementService = new UserManagementService();

    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private ChoiceBox<String> roleChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList("ADMINISTRATOR", "STUDENT"));
    private TextField marksField = new TextField();
    private TextField branchField = new TextField();
    private TextArea resultArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Management System");

        primaryStage.setScene(createLoginScene());
        primaryStage.show();
    }

    private void handleGoToRegister() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createRegisterScene());
    }
    private void handleGoToDeleteUser() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createDeleteUserScene());
    }

    private void handleGoToEditUser() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createEditUserScene());
    }

    private void handleGoToGetAllUsers() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createGetAllUsersScene());
    }
    private void handleGoToGetAllStudents() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createGetAllStudentsScene());
    }

    private void handleGoToTransferUser() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createTransferUserScene());
    }

    private void handleGoToAddResult() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createAddResultScene());
    }

    private void handleGoToEditResult() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createEditResultScene());
    }

    private void handleGoToDeleteResult() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createDeleteResultScene());
    }
    private void handleGoToLogin() {
        Stage primaryStage = (Stage) usernameField.getScene().getWindow();
        primaryStage.setScene(createLoginScene());
    }
    private Scene createLoginScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);

        Button loginButton = createButton("Login", this::handleLogin);
        Button goToRegisterButton = createButton("Go to Register", this::handleGoToRegister);
        Button goToDeleteUserButton = createButton("Go to Delete User", this::handleGoToDeleteUser);
        Button goToEditUserButton = createButton("Go to Edit User", this::handleGoToEditUser);
        Button goToGetAllUsersButton = createButton("Go to Get All Users", this::handleGoToGetAllUsers);
        Button goToGetAllStudentsButton = createButton("Go to Get All Students", this::handleGoToGetAllStudents);
        Button goToTransferUserButton = createButton("Go to Transfer User", this::handleGoToTransferUser);
        Button goToAddResultButton = createButton("Go to Add Result", this::handleGoToAddResult);
        Button goToEditResultButton = createButton("Go to Edit Result", this::handleGoToEditResult);
        Button goToDeleteResultButton = createButton("Go to Delete Result", this::handleGoToDeleteResult);
        
        GridPane.setConstraints(loginButton, 1, 2);
        GridPane.setConstraints(goToRegisterButton, 0, 2);
        GridPane.setConstraints(goToDeleteUserButton, 2, 2);
        GridPane.setConstraints(goToEditUserButton, 0, 3);
        GridPane.setConstraints(goToGetAllUsersButton, 1, 3);
        GridPane.setConstraints(goToGetAllStudentsButton, 2, 3);
        GridPane.setConstraints(goToTransferUserButton, 0, 4);
        GridPane.setConstraints(goToAddResultButton, 1, 4);
        GridPane.setConstraints(goToEditResultButton, 2, 4);
        GridPane.setConstraints(goToDeleteResultButton, 1, 5);
        
        grid.getChildren().addAll(loginButton, goToRegisterButton, goToDeleteUserButton, goToEditUserButton,
                goToGetAllUsersButton, goToGetAllStudentsButton, goToTransferUserButton, goToAddResultButton,
                goToEditResultButton, goToDeleteResultButton);

        return new Scene(grid, 600, 400);
    }

    private Scene createRegisterScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Role:"), 0, 2);
        grid.add(roleChoiceBox, 1, 2);
        grid.add(new Label("Branch:"), 0, 4);
        grid.add(branchField, 1, 4);

        Button registerButton = createButton("Register", this::handleRegister);
        Button backButton = createButton("Back", this::handleGoToLogin);
        
        GridPane.setConstraints(registerButton, 1, 5);
        GridPane.setConstraints(backButton, 0, 5); 
        grid.getChildren().addAll(registerButton, backButton);

        return new Scene(grid, 400, 300);
    }

    private Scene createDeleteUserScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);

        Button deleteUserButton = createButton("Delete User", this::handleDeleteUser);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(deleteUserButton, 1, 1);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(deleteUserButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private Scene createEditUserScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Branch:"), 0, 3);
        grid.add(branchField, 1, 3);

        Button editUserButton = createButton("Edit User", this::handleEditUser);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(editUserButton, 1, 4);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(editUserButton, backButton);

        return new Scene(grid, 400, 300);
    }

    private Scene createGetAllUsersScene() {
        GridPane grid = createGridPane();

        Button getAllUsersButton = createButton("Get All Users", this::handleGetAllUsers);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(getAllUsersButton, 1, 1);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(getAllUsersButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private Scene createGetAllStudentsScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Branch:"), 0, 0);
        grid.add(branchField, 1, 0);

        Button getAllStudentsButton = createButton("Get All Students", this::handleGetAllStudents);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(getAllStudentsButton, 1, 1);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(getAllStudentsButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private Scene createTransferUserScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("New Branch:"), 0, 1);
        grid.add(branchField, 1, 1);

        Button transferUserButton = createButton("Transfer User", this::handleTransferUser);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(transferUserButton, 1, 2);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(transferUserButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private Scene createAddResultScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Marks:"), 0, 1);
        grid.add(marksField, 1, 1);

        Button addResultButton = createButton("Add Result", this::handleAddResult);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(addResultButton, 1, 2);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(addResultButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private Scene createEditResultScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Marks:"), 0, 1);
        grid.add(marksField, 1, 1);

        Button editResultButton = createButton("Edit Result", this::handleEditResult);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(editResultButton, 1, 2);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(editResultButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private Scene createDeleteResultScene() {
        GridPane grid = createGridPane();
        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);

        Button deleteResultButton = createButton("Delete Result", this::handleDeleteResult);
        Button backButton = createButton("Back", this::handleGoToLogin);
        GridPane.setConstraints(deleteResultButton, 1, 1);
        GridPane.setConstraints(backButton, 0, 5);
        grid.getChildren().addAll(deleteResultButton, backButton);

        return new Scene(grid, 400, 200);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = userManagementService.loginUser(username, password);
        if (user != null) {
            displayResult("Login Successful\nUser: " + user.getUsername() + "\nRole: " + user.getRole());
        } else {
            displayResult("Login Failed");
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String role = roleChoiceBox.getValue();
        String branch = branchField.getText();
        String password = passwordField.getText();

        boolean result = userManagementService.registerUser(username, role, branch, password);
        displayResult(result);
    }

    private void handleDeleteUser() {
        String username = usernameField.getText();
        boolean result = userManagementService.deleteUser(username);
        displayResult(result);
    }

    private void handleEditUser() {
        String username = usernameField.getText();
        String branch = branchField.getText();

        boolean result = userManagementService.editUser(username, branch);
        displayResult(result);
    }

    private void handleGetAllUsers() {
        String role = roleChoiceBox.getValue();
        List<User> users = userManagementService.getAllUsersByRole(role);
        displayResult(users);
    }

    private void handleGetAllStudents() {
        String branch = branchField.getText();
        List<User> students = userManagementService.getAllStudentsByBranch(branch);
        displayResult(students);
    }

    private void handleTransferUser() {
        String username = usernameField.getText();
        String newBranch = branchField.getText();

        boolean result = userManagementService.transferUserToBranch(username, newBranch);
        displayResult(result);
    }

    private void handleAddResult() {
        String username = usernameField.getText();
        float marks = Float.parseFloat(marksField.getText());

        boolean result = userManagementService.addStudentResult(username, marks);
        displayResult(result);
    }

    private void handleEditResult() {
        String username = usernameField.getText();
        float marks = Float.parseFloat(marksField.getText());

        boolean result = userManagementService.editStudentResult(username, marks);
        displayResult(result);
    }

    private void handleDeleteResult() {
        String username = usernameField.getText();

        boolean result = userManagementService.deleteStudentResult(username);
        displayResult(result);
    }

    private void displayResult(boolean result) {
        resultArea.setText(result ? "Operation Successful" : "Operation Failed");
    }

    private void displayResult(String result) {
        resultArea.setText(result);
    }

    private void displayResult(List<User> users) {
        StringBuilder resultText = new StringBuilder("Users:\n");
        for (User user : users) {
            resultText.append("Username: ").append(user.getUsername()).append("\n");
            resultText.append("Role: ").append(user.getRole()).append("\n");
            resultText.append("Marks: ").append(user.getProfile().getMarks()).append("\n");
            resultText.append("Branch: ").append(user.getProfile().getBranch()).append("\n\n");
        }
        resultArea.setText(resultText.toString());
    }

    private Button createButton(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(event -> action.run());
        return button;
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);
        return grid;
    }
}
