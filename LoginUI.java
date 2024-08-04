import java.io.FileInputStream;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
public class LoginUI {
	
	private LoginControl loginControl;
	private Main main;
	public LoginUI(LoginControl control, Main main) {
		loginControl = control;
		this.main = main;
	}
	
	public ScrollPane LogIn() {

		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #BE9473;");
		
		

		Label label= new Label("Username");

		main.loginTF = new TextField();

		main.loginTF.setAlignment(Pos.CENTER);
		main.loginTF.setPrefWidth(main.widthCap);
		

		Label label2= new Label("Password");
		main.pwdField = new PasswordField();

		main.pwdField.setAlignment(Pos.CENTER);
		main.pwdField.setPrefWidth(main.widthCap);
		

		main.option1 = new Button("LogIn");
		main.option1.setPrefSize(110, 20);

		main.option1.setOnAction(main::eventHandler);
		main.option1.setDefaultButton(true);

		main.option2 = new Button("Create Account");

		main.option2.setOnAction(main::eventHandler);
		main.option2.setPrefSize(110, 20);
		

		main.optionF = new Button("Admin LogIn");

		main.optionF.setOnAction(main::eventHandler);
		main.optionF.setPrefSize(110, 20);
		

		vbox.getChildren().addAll(label, main.loginTF, label2, main.pwdField, main.option1, main.optionF, main.option2);
		

		vbox.setPrefWidth(main.widthCap);
		ScrollPane scrollable = new ScrollPane();
		scrollable.setContent(vbox);
		scrollable.setStyle("-fx-background-color: #BE9473;");
		scrollable.fitToWidthProperty().set(true);
		scrollable.fitToHeightProperty().set(true);
		scrollable.setHbarPolicy(ScrollBarPolicy.NEVER);
		return scrollable;
	}
	
	public ScrollPane LoggedIn() 
	{
		ScrollPane scrollable = new ScrollPane();
		try
		{

			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(15, 12, 15, 12));
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER);
			vbox.setStyle("-fx-background-color: #F4C8B1;");

			ImageView userIcon = null;
			try{
				userIcon = new ImageView(

				new Image(new FileInputStream(main.userIconFileDir)));
				userIcon.setFitWidth(100);
				userIcon.setPreserveRatio(true);
				userIcon.setSmooth(true);
				userIcon.setCache(true);
				vbox.getChildren().add(userIcon);

			}catch(Exception e){
				System.out.println("User Icon File Not Found!");
			}

			Label label1 = new Label("Name");
			Text text1 = new Text(main.liControl.getMemberObject().name);
			Label label2 = new Label("Location");
			Text text2 = new Text(main.liControl.getMemberObject().location);
			Label label3 = new Label("Email");
			Text text3 = new Text(main.liControl.getMemberObject().email);
			Label label4 = new Label("Phone Number");
			Text text4 = new Text(main.liControl.getMemberObject().phonenumber);
			Label label5 = new Label("Bio");
			String bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "+
			"sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, ";
			Text text5 = new Text(main.liControl.getMemberObject().bio);
			text5.setTextAlignment(TextAlignment.CENTER);
			text5.setWrappingWidth(main.widthCap-30);

			main.optionF = new Button("Create Post");
			main.optionF.setOnAction(main::eventHandler);
			main.optionF.setPrefSize(110, 20);
			
			main.option1 = new Button("View Profile");
			main.option1.setOnAction(main::eventHandler);
			main.option1.setPrefSize(110, 20);
	
			main.option3 = new Button("View WishList");
			main.option3.setOnAction(main::eventHandler);
			main.option3.setPrefSize(110, 30);
			
			main.option2 = new Button("LogOut");
			main.option2.setOnAction(main::eventHandler);
			main.option2.setPrefSize(110, 20);
			main.option2.setCancelButton(true);
			

			vbox.getChildren().addAll(label1, text1, label2, text2, label3, text3, 
									label4, text4, label5, text5, main.optionF, main.option1, main.option3, main.option2);
			
			vbox.setPrefWidth(main.widthCap);
			scrollable.setContent(vbox);
			scrollable.setStyle("-fx-background-color: #F4C8B1;");
			scrollable.fitToWidthProperty().set(true);
			scrollable.fitToHeightProperty().set(true);
			scrollable.setHbarPolicy(ScrollBarPolicy.NEVER);
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong when logging in");
		}
		return scrollable;
	}
	
	public ScrollPane AdminLoggedIn() 
	{
		ScrollPane scrollable = new ScrollPane();
		try
		{

			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(15, 12, 15, 12));
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER);
			vbox.setStyle("-fx-background-color: #F4C8B1;");

			ImageView userIcon = null;
			try{
				userIcon = new ImageView(

				new Image(new FileInputStream(main.userIconFileDir)));
				userIcon.setFitWidth(100);
				userIcon.setPreserveRatio(true);
				userIcon.setSmooth(true);
				userIcon.setCache(true);
				vbox.getChildren().add(userIcon);

			}catch(Exception e){
				System.out.println("User Icon File Not Found!");
			}
			
			AdminObject myAdmin = main.liControl.getAdminObject();

			Label label1 = new Label("Admin ID");
			Text text1 = new Text(""+myAdmin.adminID);
			Label label2 = new Label("Name");
			Text text2 = new Text(myAdmin.name);
			Label label3 = new Label("Rank");
			Text text3 = new Text(""+myAdmin.rank);
			Label label4 = new Label("Email");
			Text text4 = new Text(myAdmin.email);
			Label label5 = new Label("Phone Number");
			Text text5 = new Text(myAdmin.phonenumber);

			main.option2.setCancelButton(true);

			main.option2 = new Button("LogOut");
			main.option2.setOnAction(main::eventHandler);
			main.option2.setPrefSize(110, 20);
			

			vbox.getChildren().addAll(label1, text1, label2, text2, label3, text3, 
									label4, text4, label5, text5, main.option2);
			
			vbox.setPrefWidth(main.widthCap);
			vbox.setMinWidth(main.widthCap);
			scrollable.setContent(vbox);
			scrollable.setStyle("-fx-background-color: #F4C8B1;");
			scrollable.fitToWidthProperty().set(true);
			scrollable.fitToHeightProperty().set(true);
			scrollable.setHbarPolicy(ScrollBarPolicy.NEVER);
			scrollable.setMinWidth(main.widthCap);		
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong when logging in");
		}

		return scrollable;
	}
	
}

	
