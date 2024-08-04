import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

class ViewPostUI {

	private ViewPostControl control;
	private Main main;
	
	public ViewPostUI(ViewPostControl viewPostControl, Main main)
	{
		control = viewPostControl;
		this.main = main;
	} 
	
	public BorderPane ViewPost(int postID) 
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
			
			
			PostObject post = main.vpControl.handleViewPost(postID);
			main.currentPost = post;

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
			
			if(!(main.stwControl.postInWishList(postID, main.liControl.getMemberObject().memberID)) 
					&& !(post.memberID.equals(main.liControl.getMemberObject().memberID))){
				main.option3 = new Button("Save To WishList");
				main.option3.setOnAction(main::eventHandler);
				main.option3.setPrefSize(200, 30);
				tempHbox.getChildren().add(main.option3);
			}else if(post.memberID.equals(main.liControl.getMemberObject().memberID)){
				main.option3 = new Button("Edit Post");
				main.option3.setOnAction(main::eventHandler);
				main.option3.setPrefSize(200, 30);
				tempHbox.getChildren().add(main.option3);			
			}
			
			main.option2 = new Button("Recent Posts");
			main.option2.setOnAction(e -> {
						main.mPosts = main.bpUI.Posts(false, main.bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
						main.ResetGrid(main.grid); 
						main.setGridStyleMain(main.liUI.LoggedIn(), main.spUI.SearchBrowse(), main.bpUI.PostsList(false));});
			main.option2.setPrefSize(200, 30);
			main.option2.setCancelButton(true);
			
			
			if(!post.memberID.equals(main.liControl.getMemberObject().memberID)){
				main.option1 = new Button("Go To Profile");
				main.option1.setOnAction(e -> main.GoToProfile(post.memberID));
				main.option1.setPrefSize(200, 30);
				tempHbox.getChildren().add(main.option1);
				
				main.optionF = new Button("Report");
				main.optionF.setOnAction(main::eventHandler);
				main.optionF.setPrefSize(200, 30);
				tempHbox.getChildren().add(main.optionF);						
			}
			
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
			main.errorDialog("Something went wrong with the view post");
		}

		
		return borderPane;
	}
}