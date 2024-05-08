import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class Login extends Application {
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "pass123";
    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("NHS DATA ORGANIZATION\n     AND VISUALIZATION");
        titleLabel.setFont(new Font("Times New Roman",30));
        titleLabel.setAlignment(Pos.CENTER);

        Label subTitleLabel = new Label("PLEASE LOG IN HERE");
        subTitleLabel.setFont(new Font("Times New Roman",20));

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(new Font("Times New Roman",16));
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(new Font("Times New Roman",16));

        TextField usernameField = new TextField();
        usernameField.setFont(new Font("Times New Roman",14));
        usernameField.setPrefWidth(100);
        PasswordField passwordField = new PasswordField();
        passwordField.setFont(new Font("Times New Roman",14));
        passwordField.setPrefWidth(100);

        Label resultLabel = new Label();


        Button loginButton = new Button("Login");
        loginButton.setMinHeight(20);
        loginButton.setMinWidth(120);
        loginButton.setStyle("-fx-font-size:16");

        loginButton.setOnAction(event -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            if(enteredUsername.equals(CORRECT_USERNAME) && enteredPassword.equals(CORRECT_PASSWORD)) {
                primaryStage.close();
                Stage s = new Stage();
                try {
                    new Visualizer().start(s);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            else{
                resultLabel.setText("Login failed. Please check your credentials.");
                resultLabel.setFont(new Font("Times New Roman",16));
            }
        });

        HBox user = new HBox(usernameLabel);
        user.setAlignment(Pos.CENTER);
        HBox pass = new HBox(passwordLabel);
        pass.setAlignment(Pos.CENTER);
        HBox login = new HBox(loginButton);
        login.setAlignment(Pos.CENTER);
        HBox title = new HBox(titleLabel);
        title.setAlignment(Pos.CENTER);
        HBox subTitle = new HBox(subTitleLabel);
        subTitle.setAlignment(Pos.CENTER);
        HBox result = new HBox(resultLabel);
        result.setAlignment(Pos.CENTER);

        VBox elements = new VBox();
        elements.getChildren().addAll(user,usernameField,pass,passwordField,login,result);
        elements.setSpacing(10);

        VBox root = new VBox();
        root.getChildren().addAll(title,subTitle,elements);
        root.setMargin(elements,new Insets(10,150,50,150));
        root.setSpacing(25);

        Scene scene = new Scene(root,650,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("olivia app woo");
        primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
