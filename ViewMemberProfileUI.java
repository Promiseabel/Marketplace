import java.io.FileInputStream;
import java.sql.Date;
import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ViewMemberProfileUI {
	
	
	private ViewMemberProfileControl control;
	private Main main;

	public ViewMemberProfileUI(ViewMemberProfileControl viewProfileControl, Main main)
	{
		control = viewProfileControl;
		this.main = main;
	}
	
	
	public ScrollPane ViewUserProfile(String memberID) 
	{
		ScrollPane sp = new ScrollPane();
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
			
			MemberObject mo = main.vmpControl.handleViewMemberProfile(memberID);
			main.currentMember = mo;
			

			Label label1 = new Label("Name");
			Text text1 = new Text(mo.name);
			Label label2 = new Label("Location");
			Text text2 = new Text(mo.location);
			Label label3 = new Label("Email");
			Text text3 = new Text(mo.email);
			Label label4 = new Label("Phone Number");
			Text text4 = new Text(mo.phonenumber);
			Label label5 = new Label("Bio");
			Text text5 = new Text(mo.bio);
			text5.setWrappingWidth(main.widthCap-30);

			text5.setTextAlignment(TextAlignment.CENTER);
			
			if(main.liControl.getMemberObject().memberID==memberID)
			{
				main.option1 = new Button("Edit Profile");
				main.option1.setOnAction(main::eventHandler);
				main.option1.setPrefSize(110, 20);
				
				main.option2 = new Button("View Messages");
				main.option2.setOnAction(main::eventHandler);
				main.option2.setPrefSize(110, 20);
				
				main.optionF = new Button("Back");
				main.optionF.setOnAction(main::eventHandler);
				main.optionF.setPrefSize(110, 20);
			}
			else
			{
				main.option1 = new Button("Message");
				main.option1.setOnAction(main::eventHandler);
				main.option1.setPrefSize(110, 20);
		
				main.option2 = new Button("Report");
				main.option2.setOnAction(main::eventHandler);
				main.option2.setPrefSize(110, 20);
		
				main.optionF = new Button("Back");
				main.optionF.setOnAction(main::eventHandler);
				main.optionF.setPrefSize(110, 20);
			}
	
			
			vbox.getChildren().addAll(label1, text1, label2, text2, label3, text3, 
									label4, text4, label5, text5, main.option1, main.option2, main.optionF);
			
			sp.setStyle("-fx-background-color: #f5a791;");
			vbox.setPrefWidth(main.widthCap);
	        sp.setContent(vbox);
			sp.fitToWidthProperty().set(true);
			sp.fitToHeightProperty().set(true);
			sp.setHbarPolicy(ScrollBarPolicy.NEVER);			
		}
		catch(Exception error)
		{
			
		}

		return sp;
	}
	
	public HBox ViewUserProfile2(String memberID) 
	{
		HBox hbox = new HBox(10);
		try
		{
			hbox.setPadding(new Insets(15, 12, 15, 12));
			hbox.setSpacing(10);
			hbox.setAlignment(Pos.CENTER);
			hbox.setStyle("-fx-background-color: #A68267;");
			
			ImageView userIcon = null;
			try{
				userIcon = new ImageView(
				new Image(new FileInputStream(main.userIconFileDir)));
				//userIcon.setPrefSize(250, 250);
				userIcon.setFitWidth(100);
				userIcon.setPreserveRatio(true);
				userIcon.setSmooth(true);
				userIcon.setCache(true);
				hbox.getChildren().add(userIcon);
			}catch(Exception e){
				System.out.println("User Icon File Not Found!");
			}
			
			VBox[] vboxName = new VBox[5];
			for(int i=0; i<vboxName.length; i++){
				vboxName[i] = new VBox(10);
				vboxName[i].setPadding(new Insets(15, 12, 15, 12));
				vboxName[i].setSpacing(10);
				vboxName[i].setAlignment(Pos.CENTER);
				vboxName[i].setStyle("-fx-background-color: #F1C09E;");
			}
			
			
			MemberObject mo = main.vmpControl.handleViewMemberProfile(memberID);
			main.currentMember = mo;
			int labelsMinWidth = 100;
			
			Label label1 = new Label("Name");
			Label text1 = new Label(mo.name);
			text1.setWrapText(true);
			text1.setMinWidth(labelsMinWidth);
			text1.setMaxHeight(labelsMinWidth/2);
			label1.setAlignment(Pos.CENTER);
			text1.setAlignment(Pos.CENTER);
			vboxName[0].getChildren().addAll(label1, text1);
			
			Label label2 = new Label("Location");
			Label text2 = new Label(mo.location);
			text2.setWrapText(true);
			text2.setMinWidth(labelsMinWidth);
			text2.setMaxHeight(labelsMinWidth/2);
			label2.setAlignment(Pos.CENTER);
			text2.setAlignment(Pos.CENTER);
			vboxName[1].getChildren().addAll(label2, text2);
			
			Label label3 = new Label("Email");
			Label text3 = new Label(mo.email);
			text3.setWrapText(true);
			text3.setMinWidth(labelsMinWidth);
			text3.setMaxHeight(labelsMinWidth/2);
			label3.setAlignment(Pos.CENTER);
			text3.setAlignment(Pos.CENTER);
			vboxName[2].getChildren().addAll(label3, text3);
			
			Label label4 = new Label("Phone Number");
			Label text4 = new Label(mo.phonenumber);
			text4.setWrapText(true);
			text4.setMinWidth(labelsMinWidth);
			text4.setMaxHeight(labelsMinWidth/2);
			label4.setAlignment(Pos.CENTER);
			text4.setAlignment(Pos.CENTER);
			vboxName[3].getChildren().addAll(label4, text4);
			
			Label label5 = new Label("Bio");
			Label text5 = new Label(mo.bio);
			text5.setWrapText(true);
			text5.setMinWidth(labelsMinWidth);
			text5.setMaxHeight(labelsMinWidth/2);
			label5.setAlignment(Pos.CENTER);
			text5.setAlignment(Pos.CENTER);
			vboxName[4].getChildren().addAll(label5, text5);
			
			for(int i=0; i<vboxName.length; i++){
				hbox.getChildren().add(vboxName[i]);
			}
			
			HBox.setHgrow(vboxName[4], Priority.ALWAYS);			
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong with the user profile in view posts");
		}

		return hbox;
	}
	
	public BorderPane ViewProfile(String memberID) {
		BorderPane borderPane = new BorderPane();
		try
		{
			GridPane grid2 = new GridPane();
	        grid2.setHgap(10);
	        grid2.setVgap(10);
	        grid2.setPadding(new Insets(10, 10, 10, 10));
			grid2.setGridLinesVisible(main.showGridLines);
			grid2.setStyle("-fx-background-color: #BE9473;");
			
			MemberObject tempMo = main.liControl.getMemberObject();
					
			Label label = new Label("ID");
			main.addToCreateEditGridL(grid2, label, 0, 0);
			Label id = new Label(tempMo.memberID);
			id.setWrapText(true);
			main.addToCreateEditGridR(grid2, id, 1, 0);
			
			Label label5 = new Label("Name");
			main.addToCreateEditGridL(grid2, label5, 0, 1);
			Label name = new Label(tempMo.name);
			name.setWrapText(true);
			main.addToCreateEditGridR(grid2, name, 1, 1);
			
			Label label1 = new Label("Location");
			main.addToCreateEditGridL(grid2, label1, 0, 2);
			Label location = new Label(tempMo.location);
			location.setWrapText(true);
			main.addToCreateEditGridR(grid2, location, 1, 2);
	
			Label label2 = new Label("Email");
			main.addToCreateEditGridL(grid2, label2, 0, 3);
			Label email = new Label(tempMo.email);
			email.setWrapText(true);
			main.addToCreateEditGridR(grid2, email, 1, 3);
			
			Label label3 = new Label("Phone No");
			main.addToCreateEditGridL(grid2, label3, 0, 4);
			Label phoneNo = new Label(tempMo.phonenumber);
			phoneNo.setWrapText(true);
			main.addToCreateEditGridR(grid2, phoneNo, 1, 4);
			
			Label label4 = new Label("Bio");
			main.addToCreateEditGridL(grid2, label4, 0, 5);
			Label bio = new Label(tempMo.bio);
			bio.setWrapText(true);
			main.addToCreateEditGridR(grid2, bio, 1, 5, true);
			
			
			
			borderPane.setPadding(new Insets(0, 0, 0, 0));
			borderPane.setCenter(grid2);
			
			
			BorderPane ceBorder = new BorderPane();
			ceBorder.setPadding(new Insets(0, 0, 0, 0));
			ceBorder.setStyle("-fx-background-color: #2E282A;");
	
			main.option1 = new Button("Edit Profile");
			main.option1.setOnAction(main::eventHandler);
			main.option1.setPrefSize(200, 30);
	
			main.option2 = new Button("Recent Posts");
			main.option2.setOnAction(e -> {
						main.mPosts = main.bpUI.Posts(false, main.bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
						main.ResetGrid(main.grid); 
						main.setGridStyleMain(main.liUI.LoggedIn(), main.spUI.SearchBrowse(), main.bpUI.PostsList(false));});
			main.option2.setPrefSize(200, 30);
	
			
			HBox tempHbox = new HBox(10);
			
			tempHbox.setPadding(new Insets(15, 12, 15, 12));
			tempHbox.setSpacing(10);
			tempHbox.setAlignment(Pos.CENTER);
			tempHbox.setStyle("-fx-background-color: #BE9473;");
			tempHbox.getChildren().addAll(main.option1);
			
			
			BorderPane.setAlignment(main.option2, Pos.CENTER_LEFT);
			BorderPane.setMargin(main.option2, new Insets(0,0,0,30));
			BorderPane.setAlignment(tempHbox, Pos.CENTER_RIGHT);
			BorderPane.setMargin(tempHbox, new Insets(0,30,0,0));
			ceBorder.setPrefHeight(50);
			ceBorder.setLeft(main.option2);
			ceBorder.setRight(tempHbox);
			borderPane.setBottom(ceBorder);			
		}catch(Exception error)
		{
			main.errorDialog("Something went wrong with the view profile");
		}

		
		return borderPane;
	}
	
}