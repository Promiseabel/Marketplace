
import java.io.FileInputStream;
import java.util.*;

import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ManageMemberUI {

	private ManageMemberControl control;
	private Main main;

	public ManageMemberUI(ManageMemberControl manageMemberControl, Main main)
	{
		control = manageMemberControl;
		this.main = main;
	}
	
	public ScrollPane reportList(int choice) {
		ScrollPane sp = new ScrollPane();
		try
		{
		sp.setStyle("-fx-background-color: #EFBC95;");

		if(choice==0)
		{
			main.mReports = reports(control.handlePostReports());
		}
		else if(choice==1)
		{
			main.mReports = reports(control.handleMemberReports());
		}
		else 
		{
			main.mReports = reports(control.handleReports());
		}

        sp.setContent(main.mReports);
		sp.fitToWidthProperty().set(true);
		sp.fitToHeightProperty().set(true);			
		}catch(Exception error)
		{
			main.errorDialog("Something went wrong with the report list");
		}
		

		return sp;
	}
	
	public VBox reports(ArrayList<ReportObject> reports) {
		VBox vbox = new VBox(10);

		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER_LEFT);
		vbox.setStyle("-fx-background-color: #EFBC95;");

		int buttonsSize = reports.size();
		Button[] butt = new Button[buttonsSize]; 
		for(int i=0; i<buttonsSize; i++){
			ReportObject thisReport = reports.get(i);
			butt[i] = new Button();
			
			if(reports.get(i).postID!=0)
			{
				butt[i].setText("Post Title: "+thisReport.post.title);
				butt[i].setOnAction(e -> main.GoToFlaggedPost(thisReport));
			}
			else
			{
				butt[i].setText("Member: "+thisReport.memberID+"\nName: "+thisReport.member.name);
				butt[i].setOnAction(e -> main.GoToFlaggedProfile(thisReport));
			}

			butt[i].disableProperty().bind(Bindings.createBooleanBinding(()->(false==main.loggedIn)));

			vbox.getChildren().add(butt[i]);
		}

		vbox.setAlignment(Pos.TOP_LEFT);			
		

		return vbox;
	}
	
	public ScrollPane ViewFlaggedUserProfile(ReportObject flag) 
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
			
			MemberObject mo = flag.member;
			main.currentReport = flag;
			
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
			
			Label label6 = new Label("Reason");
			Text text6 = new Text(flag.reason);
			
			text6.setWrappingWidth(main.widthCap-30);
			text6.setTextAlignment(TextAlignment.CENTER);
			
			vbox.getChildren().addAll(label1, text1, label2, text2, label3, text3, 
									label4, text4, label5, text5, label6, text6 );
			
			sp.setStyle("-fx-background-color: #f5a791;");
			vbox.setPrefWidth(main.widthCap);
	        sp.setContent(vbox);
			sp.fitToWidthProperty().set(true);
			sp.fitToHeightProperty().set(true);
			sp.setHbarPolicy(ScrollBarPolicy.NEVER);			
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong with viewing a flagged user's profile");
		}


		return sp;
	}
	
	public BorderPane ViewPostReport(ReportObject report) 
	{
		BorderPane borderPane = new BorderPane();
		try
		{

			GridPane grid2 = new GridPane();
	        grid2.setHgap(10);
	        grid2.setVgap(10);
	        grid2.setPadding(new Insets(10, 10, 10, 10));
			grid2.setGridLinesVisible(main.showGridLines);
			grid2.setStyle("-fx-background-color: #BE9473;");
			
			PostObject post = report.post;
			main.currentReport = report;

			Label label = new Label("Title");
			main.addToCreateEditGridL(grid2, label, 0, 0);
			Label title = new Label(post.title);
			title.setWrapText(true);
			main.addToCreateEditGridR(grid2, title, 1, 0);
			
			Label label5 = new Label("Description");
			main.addToCreateEditGridL(grid2, label5, 0, 1);
			Label desc = new Label(post.description);
			desc.setWrapText(true);
			main.addToCreateEditGridR(grid2, desc, 1, 1, true);
			
			Label label1 = new Label("Date");
			main.addToCreateEditGridL(grid2, label1, 0, 2);
			Label isbn = new Label(post.date.toString());
			isbn.setWrapText(true);
			main.addToCreateEditGridR(grid2, isbn, 1, 2);
			
			Label label2 = new Label("Reason");
			main.addToCreateEditGridL(grid2, label2, 0, 3);
			Label reason = new Label(report.reason);
			reason.setWrapText(true);
			main.addToCreateEditGridR(grid2, reason, 1, 3);
			
			
			borderPane.setPadding(new Insets(0, 0, 0, 0));
			borderPane.setCenter(grid2);
			

			BorderPane ceBorder = new BorderPane();
			ceBorder.setPadding(new Insets(0, 0, 0, 0));
			ceBorder.setStyle("-fx-background-color: #2E282A;");
	

			HBox tempHbox = new HBox(10);

			tempHbox.setPadding(new Insets(15, 12, 15, 12));
			tempHbox.setSpacing(10);
			tempHbox.setAlignment(Pos.CENTER);
			tempHbox.setStyle("-fx-background-color: #A68267;");
			
			main.option1 = new Button("Delete Post");
			main .option1.setOnAction(main::eventHandler);
			main.option1.setPrefSize(200, 30);
			
			main.option3 = new Button("Bypass Report");
			main.option3.setOnAction(main::eventHandler);
			main.option3.setPrefSize(200, 30);
			
			main.option2 = new Button("Back");
			main.option2.setOnAction(e -> 
			{
				main.ResetGrid(main.grid);
				main.setGridStyleMain(main.liUI.AdminLoggedIn(), flaggedButtons(), reportList(2));
			});
			main.option2.setPrefSize(200, 30);
			main.option2.setCancelButton(true);
			
			tempHbox.getChildren().addAll(main.option1, main.option3);
			
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
			main.errorDialog("Something went wrong with the view post report");
		}

		
		return borderPane;
	}
	
	public HBox flaggedButtons() 
	{
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #BE9473;");
				
		main.option3 = new Button("Get Flagged Posts");
		main.option4 = new Button("Get Flagged Members");
		main.optionF = new Button("Get Both");
		
		main.optionF.setMaxWidth(100);
		main.optionF.setMinWidth(100);
		main.option3.setMinWidth(100);
		main.option4.setMinWidth(100);

		main.option3.prefWidthProperty().bind(hbox.widthProperty());
		main.option4.prefWidthProperty().bind(hbox.widthProperty());
		main.optionF.prefWidthProperty().bind(hbox.widthProperty());
		
		main.option3.setOnAction(main::eventHandler);
		main.option4.setOnAction(main::eventHandler);
		main.optionF.setOnAction(main::eventHandler);
		
		hbox.getChildren().addAll(main.option3, main.optionF, main.option4);
		return hbox;
	}
	
	public HBox flagActions()
	{
		HBox tempHbox = new HBox(10);
		try
		{
			tempHbox.setPadding(new Insets(15, 12, 15, 12));
			tempHbox.setSpacing(10);
			tempHbox.setAlignment(Pos.CENTER);
			tempHbox.setStyle("-fx-background-color: #A68267;");
			
			main.option1 = new Button("Ban Member");
			main.option1.setOnAction(main::eventHandler);
			main.option1.setPrefSize(200, 30);
			
			main.option3 = new Button("Bypass Report");
			main.option3.setOnAction(main::eventHandler);
			main.option3.setPrefSize(200, 30);
			
			main.option2 = new Button("Back");
			main.option2.setOnAction(e -> 
			{
				main.ResetGrid(main.grid);
				main.setGridStyleMain(main.liUI.AdminLoggedIn(), flaggedButtons(), reportList(2));
			});
			main.option2.setPrefSize(200, 30);
			main.option2.setCancelButton(true);
			
			tempHbox.getChildren().addAll(main.option2, main.option1, main.option3);			
		}catch(Exception error)
		{
			main.errorDialog("Something went wrong with the flagActions");
		}

		
		return tempHbox;
	}
	
}