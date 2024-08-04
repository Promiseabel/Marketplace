
import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CreateAccountUI {

	private CreateAccountControl createAccountControl;
	private Main main;
	
	public CreateAccountUI(CreateAccountControl control, Main main) {
		createAccountControl = control;
		this.main = main;
	}

	public GridPane AccountProfile(Boolean edit) {

		GridPane grid2 = new GridPane();

        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(10, 10, 10, 10));
		grid2.setGridLinesVisible(main.showGridLines);
		grid2.setStyle("-fx-background-color: #8E705B;");
		

		Label label = new Label("MemberID");

		main.addToCreateEditGridL(grid2, label, 0, 0);
		main.memberidTF = new TextField();
		if(edit) main.memberidTF.setText(main.liControl.getMemberObject().memberID);

		main.addToCreateEditGridR(grid2, main.memberidTF, 1, 0);
		
		Label label6 = new Label("Password");
		main.addToCreateEditGridL(grid2, label6, 0, 1);
		main.pwdTF = new TextField();

		main.addToCreateEditGridR(grid2, main.pwdTF, 1, 1);
		
		Label label1 = new Label("Name");
		main.addToCreateEditGridL(grid2, label1, 0, 2);
		main.nameTF = new TextField();
		if(edit) main.nameTF.setText(main.liControl.getMemberObject().name);
		main.addToCreateEditGridR(grid2, main.nameTF, 1, 2);
		
		Label label2 = new Label("Location");
		main.addToCreateEditGridL(grid2, label2, 0, 3);
		main.locationTF = new TextField();;
		if(edit) main.locationTF.setText(main.liControl.getMemberObject().location);
		main.addToCreateEditGridR(grid2, main.locationTF, 1, 3);
		
		Label label3 = new Label("Email");
		main.addToCreateEditGridL(grid2, label3, 0, 4);
		main.emailTF = new TextField();
		if(edit) main.emailTF.setText(main.liControl.getMemberObject().email);
		main.addToCreateEditGridR(grid2, main.emailTF, 1, 4);
		
		Label label4 = new Label("Phone Number");
		main.addToCreateEditGridL(grid2, label4, 0, 5);
		main.phoneNoTF = new TextField();
		if(edit) main.phoneNoTF.setText(main.liControl.getMemberObject().phonenumber);
		main.addToCreateEditGridR(grid2, main.phoneNoTF, 1, 5);
		
		Label label5 = new Label("Bio");
		main.addToCreateEditGridL(grid2, label5, 0, 6);

		main.bioTA = new TextArea();
		main.bioTA.setWrapText(true);
		if(edit) main.bioTA.setText(main.liControl.getMemberObject().bio);
		main.addToCreateEditGridR(grid2, main.bioTA, 1, 6, true);
		
		
		return grid2;
	}
	
	BorderPane CreateEdit(GridPane createEdit, String but1, String but2){
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(0, 0, 0, 0));
		borderPane.setCenter(createEdit);
		
		BorderPane ceBorder = new BorderPane();
		ceBorder.setPadding(new Insets(0, 0, 0, 0));
		ceBorder.setStyle("-fx-background-color: #2E282A;");
		
		main.option1 = new Button(but1);
		main.option1.setOnAction(main::eventHandler);
		main.option1.setPrefSize(200, 30);
		
		main.option2 = new Button(but2);
		main.option2.setOnAction(main::eventHandler);
		main.option2.setPrefSize(200, 30);
	
		BorderPane.setAlignment(main.option1, Pos.CENTER_LEFT);
		BorderPane.setMargin(main.option1, new Insets(0,0,0,30));
		BorderPane.setAlignment(main.option2, Pos.CENTER_RIGHT);
		BorderPane.setMargin(main.option2, new Insets(0,30,0,0));
		
		ceBorder.setPrefHeight(50);
		
		ceBorder.setLeft(main.option1);
		ceBorder.setRight(main.option2);
		if(but1=="Edit Post")
		{
			main.option3 = new Button("Delete Post");
			main.option3.setOnAction(main::eventHandler);
			main.option3.setPrefSize(200, 30);
			BorderPane.setAlignment(main.option3, Pos.CENTER);
			ceBorder.setCenter(main.option3);
		}
		borderPane.setBottom(ceBorder);
		return borderPane;
	}
	
}
