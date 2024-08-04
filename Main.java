import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.event.*;
import java.io.*;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableBooleanValue;

public class Main extends Application
{
	public Scene scene;
	public GridPane grid;
	public String userIconFileDir;
	
	public MemberObject currentMember = new MemberObject();
	public PostObject currentPost = new PostObject();
	public ReportObject currentReport = new ReportObject();

	DataManager dm = new DataManager(this);
	
	BrowsePostControl bpControl = new BrowsePostControl(dm);
	BrowsePostUI bpUI = new BrowsePostUI(bpControl, this);

	SearchPostControl spControl = new SearchPostControl(dm);
	SearchPostUI spUI = new SearchPostUI(spControl, this);

	ViewMemberProfileControl vmpControl = new ViewMemberProfileControl(dm);
	ViewMemberProfileUI vmpUI = new ViewMemberProfileUI(vmpControl, this);

	ViewPostControl vpControl = new ViewPostControl(dm);
	ViewPostUI vpUI = new ViewPostUI(vpControl, this);

	LoginControl liControl = new LoginControl(dm);
	LoginUI liUI = new LoginUI(liControl, this);

	SaveToWishlistControl stwControl = new SaveToWishlistControl(dm);
	SaveToWishlistUI stwUI = new SaveToWishlistUI(stwControl, this);

	CreatePostControl cpControl = new CreatePostControl(dm);
	CreatePostUI cpUI = new CreatePostUI(cpControl, this);

	CreateAccountControl caControl = new CreateAccountControl(dm);
	CreateAccountUI caUI = new CreateAccountUI(caControl, this);

	EditMemberProfileControl edmpControl = new EditMemberProfileControl(dm);

	ReportControl reportControl = new ReportControl(dm);
	ReportUI reportUI = new ReportUI(reportControl, this);

	ManageMemberControl manageMemberControl = new ManageMemberControl(dm);
	ManageMemberUI manageMemberUI = new ManageMemberUI(manageMemberControl, this);

	EditPostControl edpControl = new EditPostControl(dm);

	MessageUserControl msgControl = new MessageUserControl(dm);
	MessageUserUI msgUI = new MessageUserUI(msgControl, this);

	VBox mPosts, mReports;
	
	public Button searchButton, option1, option2, optionF, option3, option4;

	boolean loggedIn , showGridLines = true;

	int defSceneWidth = 1440, defSceneHeight = 610;
	int widthCap = 275;
	
	boolean res; MemberObject mo, liMo; 
	Label label, label1, label2, label3, label4, label5;
	TextField memberidTF, pwdTF, nameTF, locationTF, emailTF, phoneNoTF; TextArea bioTA;
	TextField titleTF, isbnTF, bTitleTF, authorTF; TextArea descTA;
	TextField loginTF; PasswordField pwdField;
	ArrayList<String> textInputS; ArrayList<TextField> textInput; 
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage){
		userIconFileDir = System.getProperty("user.dir") + "/bin/user.png";
		loggedIn = false;

		grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 10, 10));
		grid.setStyle("-fx-background-color: #2E282A;");
		grid.setGridLinesVisible(showGridLines);
		
		mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), null));

		setGridStyleMain(liUI.LogIn(), spUI.SearchBrowse(), bpUI.PostsList(false));

		scene = new Scene(grid, defSceneWidth, defSceneHeight);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void ConfirmationDialog(String question) 
	{
		try
		{
			res = false;
			final Stage dialogStage = new Stage();

			dialogStage.initModality(Modality.APPLICATION_MODAL);
			
			Label questionLabel = new Label(question);
			questionLabel.setAlignment(Pos.BASELINE_CENTER);
			questionLabel.setWrapText(true);
			questionLabel.setTextAlignment(TextAlignment.CENTER);
			
			Button yesBtn = new Button("Yes");
			yesBtn.setOnAction(e -> {res = true; dialogStage.close();});
			yesBtn.setDefaultButton(true);
			
			Button noBtn = new Button("No");
			noBtn.setOnAction(e -> {res = false; dialogStage.close();});
			
			HBox hBox = new HBox();
			hBox.setAlignment(Pos.BASELINE_CENTER);
			hBox.setSpacing(40.0);
			hBox.getChildren().addAll(yesBtn, noBtn);
		

			
			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(15, 12, 15, 12));
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER);
			vbox.setStyle("-fx-background-color: #A68267;");
			

			vbox.getChildren().addAll(questionLabel,hBox);
			

			vbox.setPrefWidth(widthCap);
			
			dialogStage.setScene(new Scene(vbox));

			dialogStage.showAndWait();			
		}catch(Exception error)
		{
			errorDialog("Something went wrong with the confirmation dialog");
		}

	}
	
	public void errorDialog(String err) 
	{
		res = false;
		final Stage dialogStage = new Stage();

		dialogStage.initModality(Modality.APPLICATION_MODAL);
		
		Label questionLabel = new Label(err);
		questionLabel.setAlignment(Pos.BASELINE_CENTER);
		questionLabel.setWrapText(true);
		questionLabel.setTextAlignment(TextAlignment.CENTER);
		
		Button okBtn = new Button("OK");
		okBtn.setOnAction(e -> {dialogStage.close();});
		okBtn.setDefaultButton(true);
		
		
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setSpacing(40.0);
		hBox.getChildren().addAll(okBtn);

		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(15, 12, 15, 12));
		vbox.setSpacing(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #A68267;");
		

		vbox.getChildren().addAll(questionLabel,hBox);
		

		vbox.setPrefWidth(widthCap);
		
		dialogStage.setScene(new Scene(vbox));
		dialogStage.showAndWait();
	}
	
	public void addToCreateEditGridL(GridPane g, Region region, int col, int row){
		region.setPadding(new Insets(10, 10, 10, 10));
		GridPane.setValignment(region, VPos.CENTER);
		GridPane.setHalignment(region, HPos.CENTER);
		g.add(region, col, row);
	}

	public void addToCreateEditGridR(GridPane g, Region region, int col, int row){

		region.setMaxHeight(30);
		region.setPadding(new Insets(10, 10, 10, 10));
		GridPane.setHgrow(region, Priority.ALWAYS);
		GridPane.setVgrow(region, Priority.ALWAYS);
		g.add(region, col, row);		
	}

	public void addToCreateEditGridR(GridPane g, Region region, int col, int row, Boolean bool){

		region.setPadding(new Insets(10, 10, 10, 10));
		region.setMaxHeight(100);
		GridPane.setHgrow(region, Priority.ALWAYS);
		GridPane.setVgrow(region, Priority.ALWAYS);
		g.add(region, col, row);		
	}

	public void addToCreateEditGridR(GridPane g, Region region, int col, int row, Boolean bool, int ver){

		region.setPadding(new Insets(10, 10, 10, 10));
		region.setMaxHeight(120);
		GridPane.setValignment(region, VPos.TOP);
		GridPane.setHgrow(region, Priority.ALWAYS);
		GridPane.setVgrow(region, Priority.ALWAYS);
		g.add(region, col, row);		
	}
		

	public void GoToProfile(String profileid){
		mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), profileid));
		ResetGrid(grid);
		if(option2.getText()=="View Messages")
		{
			setGridStyleVUP(vmpUI.ViewUserProfile(profileid), 
					spUI.SearchBrowse(), 
					msgUI.msgList());
			option2.setText("View Posts");
		}
		else
		{
			setGridStyleVUP(vmpUI.ViewUserProfile(profileid), 
				spUI.SearchBrowse(), 
				bpUI.PostsList(true));
		}

	}
	
	public void GoToFlaggedProfile(ReportObject flag){
		mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), flag.memberID));
		ResetGrid(grid);
		setGridStyleVUP(manageMemberUI.ViewFlaggedUserProfile(flag), 
						manageMemberUI.flagActions(), 
						manageMemberUI.reportList(1));
	}

	public void GoToPost(String profileid, int postid){
		ResetGrid(grid);
		setGridStyleVP(bpUI.PostsList(false),  
						vmpUI.ViewUserProfile2(profileid),
						vpUI.ViewPost(postid));
	}
	
	public void GoToPostWL(String profileid, int postid){
		ResetGrid(grid);
		setGridStyleVP(stwUI.WishList(),  
				vmpUI.ViewUserProfile2(profileid),
				vpUI.ViewPost(postid));
	}
	
	public void GoToFlaggedPost(ReportObject flag){
		ResetGrid(grid);
		setGridStyleVP(manageMemberUI.reportList(0),  
						vmpUI.ViewUserProfile2(flag.post.memberID),
						manageMemberUI.ViewPostReport(flag));
	}
	
	public void setGridStyleMain(ScrollPane righSideUI, HBox topBarUI, ScrollPane bottomLeftUI){
		setRightSideUI(righSideUI);
		setTopBarUI(topBarUI);
		setBottomLeftUI(bottomLeftUI);
	}
	
	//set the grid to ViewUserProfile Style
	public void setGridStyleVUP(ScrollPane leftSideUI, HBox col2TopBarUI, ScrollPane col2BottomLeftUI){
		setLeftSideUI(leftSideUI);
		set2ColTopBarUI(col2TopBarUI);
		set2ColBottomLeftUI(col2BottomLeftUI);
	}
	
	public void setGridStyleMSG(ScrollPane leftSideUI, BorderPane rightSideUI){
		setLeftSideUI(leftSideUI);
		setRightSideUI(rightSideUI);
		rightSideUI.prefHeightProperty().bind(grid.heightProperty());
		GridPane.setHgrow(rightSideUI, Priority.ALWAYS);
	}
	
	//set the grid to View Post Style
	public void setGridStyleVP(ScrollPane leftSideUI, HBox col2TopBarUI, BorderPane bottomRightUI){
		leftSideUI.setMinWidth(350);
		setLeftSideUI(leftSideUI);
		set2ColTopBarUI2(col2TopBarUI);
		setBottomRightUI(bottomRightUI);
	}
	
	//these are just to avoid repition, can't be explained too easily
	public void setRightSideUI(Node node){
		GridPane.setHalignment(node, HPos.RIGHT);
		grid.add(node, 1, 0, 1, 2);
	}
	
	public void setLeftSideUI(Node node){
		GridPane.setHalignment(node, HPos.LEFT);
		//GridPane.setHgrow(node, Priority.ALWAYS);
		grid.add(node, 0, 0, 1, 2);
	}
	
	public void setTopBarUI(Node node){
		GridPane.setHgrow(node, Priority.ALWAYS);
		GridPane.setValignment(node, VPos.TOP);
		grid.add(node, 0, 0);
	}
	
	public void set2ColTopBarUI(Node node){
		GridPane.setHgrow(node, Priority.ALWAYS);
		GridPane.setValignment(node, VPos.TOP);
		grid.add(node, 1, 0);
	}
	
	public void set2ColTopBarUI2(Node node){
		GridPane.setHgrow(node, Priority.ALWAYS);
		GridPane.setValignment(node, VPos.TOP);
		grid.add(node, 1, 0);
	}
	
	public void setBottomRightUI(Node node){
		GridPane.setVgrow(node, Priority.ALWAYS);
		GridPane.setHgrow(node, Priority.ALWAYS);
		GridPane.setValignment(node, VPos.TOP);
		grid.add(node, 1, 1);
	}
	
	public void setBottomLeftUI(Node node){
		GridPane.setVgrow(node, Priority.ALWAYS);
		GridPane.setHgrow(node, Priority.ALWAYS);
		GridPane.setValignment(node, VPos.TOP);
		grid.add(node, 0, 1);
	}
	
	public void set2ColBottomLeftUI(Node node){
		GridPane.setVgrow(node, Priority.ALWAYS);
		GridPane.setHgrow(node, Priority.ALWAYS);
		GridPane.setValignment(node, VPos.TOP);
		grid.add(node, 1, 1);
	}
	
	public void setCreateEdit(GridPane createEdit, String but1, String but2){
		BorderPane ce = caUI.CreateEdit(createEdit, but1, but2);
		GridPane.setVgrow(ce, Priority.ALWAYS);
		GridPane.setHgrow(ce, Priority.ALWAYS);
		grid.add(ce, 0, 0);
	}
	
	//clear the grid to then reload it
	public void ResetGrid(GridPane grid){
		grid.getChildren().clear();
		grid.setGridLinesVisible(showGridLines);
	}
	
	public void eventHandler(ActionEvent e)
	{
		try
		{
			if(e.getSource() == option1){
				if(option1.getText().equals("LogIn")){
					String loginID = loginTF.getText();
					if(liControl.handleLogin(loginID, pwdField.getText())){ 
						liControl.saveMemberObject(liControl.getMemberObject(loginID));
						loggedIn = true;
						mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
						ResetGrid(grid);
						setGridStyleMain(liUI.LoggedIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
					}
				}else if(option1.getText().equals("Delete Post")){
					ConfirmationDialog("Are you sure you want to delete this post?");
					if(res==true)
					{
						manageMemberControl.handleAction(currentReport, 1, true);
						ResetGrid(grid);
						setGridStyleVP(manageMemberUI.reportList(0),  
										vmpUI.ViewUserProfile2(currentReport.post.memberID),
										manageMemberUI.ViewPostReport(currentReport));
						option3.disableProperty().bind(Bindings.createBooleanBinding(()->(true==res)));
						option1.disableProperty().bind(Bindings.createBooleanBinding(()->(true==res)));
					}
				}else if(option1.getText().equals("Ban Member")){
					ConfirmationDialog("Are you sure you want to banish this member?");
					if(res==true)
					{
						manageMemberControl.handleAction(currentReport, 1, false);
						ResetGrid(grid);
						setGridStyleVUP(manageMemberUI.ViewFlaggedUserProfile(currentReport), 
										manageMemberUI.flagActions(), 
										manageMemberUI.reportList(1));
						option3.disableProperty().bind(Bindings.createBooleanBinding(()->(true==res)));
						option1.disableProperty().bind(Bindings.createBooleanBinding(()->(true==res)));
					}
				}else if(option1.getText().equals("Edit Profile") && option2.getText().equals("Cancel")){
					String q = "Are you sure you want to edit this profile?";
					ConfirmationDialog(q);
					if(res == true) {
						if(edmpControl.handleEditMemberProfile(
								memberidTF.getText(),
								pwdTF.getText(),
								nameTF.getText(),
								locationTF.getText(),
								emailTF.getText(),
								phoneNoTF.getText(),
								bioTA.getText(),
								liControl.getMemberObject().memberID
						)){
							loggedIn = false;
							mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
							ResetGrid(grid);
							setGridStyleMain(liUI.LogIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
						}
					}
				}else if(option1.getText().equals("Edit Post") && option2.getText().equals("Cancel")){
					String q = "Are you sure you want to edit this post?";
					ConfirmationDialog(q);
					if(res == true) {
						if(edpControl.handleEditPost(
								currentPost.postID,
								titleTF.getText(),
								descTA.getText()
						)){
							ResetGrid(grid);
							setGridStyleVP(bpUI.PostsList(false), vmpUI.ViewUserProfile2(currentPost.memberID), vpUI.ViewPost(currentPost.postID));
						}
					}
				}else if(option1.getText().equals("View Profile")){
					ResetGrid(grid);
					GoToProfile(liControl.getMemberObject().memberID);
				}else if(option1.getText().equals("Edit Profile")){
					ResetGrid(grid);
					setCreateEdit(caUI.AccountProfile(true), "Edit Profile", "Cancel");
				}else if(option1.getText().equals("Message")){
					ResetGrid(grid);
					setGridStyleMSG(vmpUI.ViewUserProfile(currentMember.memberID), msgUI.messages(currentMember.memberID));
				}else if(option1.getText().equals("Create Account")){
					if(pwdTF.getText().trim().isEmpty())
					{
						String err = "Please Enter a password";
						errorDialog(err);
						return;
					}
					String q = "Are you sure you want to create this account?";
					ConfirmationDialog(q);
					if(res == true)
						if(caControl.handleCreateAccount(
							memberidTF.getText(),
							pwdTF.getText(),
							nameTF.getText(),
							locationTF.getText(),
							emailTF.getText(),
							phoneNoTF.getText(),
							bioTA.getText()
						)){
						ResetGrid(grid);
						setGridStyleMain(liUI.LogIn(), spUI.SearchBrowse(), bpUI.PostsList(false));}
						else
						{
							String err = "MemberID already taken";
							errorDialog(err);
							return;
						}
					
				}else if(option1.getText().equals("Create Post")){
					String q = "Are you sure you want to post this?";
					ConfirmationDialog(q);
					if(res == true)
						if(cpControl.handleCreatePost(
								isbnTF.getText(), 
								bTitleTF.getText(), 
								authorTF.getText(), 
								titleTF.getText(), 
								liControl.getMemberObject().memberID, 
								descTA.getText()
						)){
							ResetGrid(grid);
							mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
							setGridStyleMain(liUI.LoggedIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
							
						}
						else
						{
							errorDialog("Post must contain a title and description");
						}
				}else{			
					errorDialog("Button isn't assigned to anything");	
				}
			}else if(e.getSource() == option2){
				if(option2.getText().equals("LogOut")){
					String q = "Are you sure you want to log out?";
					ConfirmationDialog(q);
					if(res == true) {
						loggedIn = false;
						mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
						ResetGrid(grid);
						setGridStyleMain(liUI.LogIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
					}
				}else if(option2.getText().equals("Create Account")){
					ResetGrid(grid);
					setCreateEdit(caUI.AccountProfile(false), "Create Account", "Cancel");
				}else if(option2.getText().equals("Report")){
					String q = "Are you sure you want to report this user?";
					ConfirmationDialog(q);
					if(res == true)
					{
						reportUI.reportBox(false, currentMember.memberID, 0);
					}
				}else if(option2.getText().equals("View Messages")){
					ResetGrid(grid);
					GoToProfile(liControl.getMemberObject().memberID);
				}else if(option2.getText().equals("View Posts")){
					ResetGrid(grid);
					GoToProfile(liControl.getMemberObject().memberID);
				}else if(option2.getText().equals("Cancel") && option1.getText().equals("Create Account")){	
					ResetGrid(grid);
					setGridStyleMain(liUI.LogIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
				}else if(option2.getText().equals("Cancel") && option1.getText().equals("Create Post")){
					ResetGrid(grid);
					setGridStyleMain(liUI.LoggedIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
				}else if(option2.getText().equals("Cancel") && option1.getText().equals("Edit Profile")){
					ResetGrid(grid);
					GoToProfile(liControl.getMemberObject().memberID);
				}else if(option2.getText().equals("Cancel") && option1.getText().equals("Edit Post")){
					ResetGrid(grid);
					setGridStyleVP(bpUI.PostsList(false), vmpUI.ViewUserProfile2(currentPost.memberID), vpUI.ViewPost(currentPost.postID));
				}else{			
					errorDialog("Button isn't assigned to anything");	
				}
			}else if(e.getSource() == optionF){
				if(optionF.getText().equals("Create Post")){
					ResetGrid(grid);
					setCreateEdit(cpUI.PostEdit(false), "Create Post", "Cancel");
				}else if(optionF.getText().equals("Admin LogIn")){
					int loginID = 0;
					 try{
				            loginID = Integer.parseInt(loginTF.getText());
				        }
				        catch (NumberFormatException ex){}
					if(liControl.handleAdminLogin(loginID, pwdField.getText())){ 
						liControl.saveAdminObject(liControl.getAdminObject(loginID));
						loggedIn = true;
						ResetGrid(grid);
						setGridStyleMain(liUI.AdminLoggedIn(), manageMemberUI.flaggedButtons(), manageMemberUI.reportList(2));	
					}
		
				}else if(optionF.getText().equals("Back")){
					mPosts = bpUI.Posts(false, bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), ""));
					ResetGrid(grid);
					setGridStyleMain(liUI.LoggedIn(), spUI.SearchBrowse(), bpUI.PostsList(false));
				}else if(optionF.getText().equals("Report")){
					String q = "Are you sure you want to report this post?";
					ConfirmationDialog(q);
					if(res == true)
					{
						reportUI.reportBox(true, "", currentPost.postID);
					}
				}else if(optionF.getText().equals("Get Both")) {
					ResetGrid(grid);
					setGridStyleMain(liUI.AdminLoggedIn(), manageMemberUI.flaggedButtons(), manageMemberUI.reportList(2));	
				}else{			
					errorDialog("Button isn't assigned to anything");	
				}
			}else if(e.getSource() == option3){
				if(option3.getText().equals("Save To WishList")){
					String q = "Are you sure you want to save this to your wishlist?";
					ConfirmationDialog(q);
					if(res == true)
						if(stwControl.handleSaveToWishList(currentPost.postID, liControl.getMemberObject().memberID)){		
							setGridStyleVP(bpUI.PostsList(false), vmpUI.ViewUserProfile2(currentPost.memberID), vpUI.ViewPost(currentPost.postID));
						}
				}else if(option3.getText().equals("View WishList")){
					ResetGrid(grid);
					setGridStyleVP(stwUI.WishList(), vmpUI.ViewUserProfile2(liControl.getMemberObject().memberID), vmpUI.ViewProfile(liControl.getMemberObject().memberID));
				}else if(option3.getText().equals("Get Flagged Posts")) {
					ResetGrid(grid);
					setGridStyleMain(liUI.AdminLoggedIn(), manageMemberUI.flaggedButtons(), manageMemberUI.reportList(0));	
				}else if(option3.getText().equals("Edit Post")){
					ResetGrid(grid);
					setCreateEdit(cpUI.PostEdit(true), "Edit Post", "Cancel");
				}else if(option3.getText().equals("Delete Post")){
					String q = "Are you sure you want to delete this post?";
					ConfirmationDialog(q);
					if(res == true) {
						if(edpControl.handleDeletePost(currentPost.postID)){
							ResetGrid(grid);
							GoToProfile(liControl.getMemberObject().memberID);
						}
					}
				}else if(option3.getText().equals("Bypass Report")){
					ConfirmationDialog("Are you sure you want to bypass this report?");
					if(res==true)
					{
						boolean isPost = false;
						if(currentReport.postID!=0) 
						{
							isPost = true;
							manageMemberControl.handleAction(currentReport, 0, isPost);
							ResetGrid(grid);
							setGridStyleVP(manageMemberUI.reportList(0),  
											vmpUI.ViewUserProfile2(currentReport.post.memberID),
											manageMemberUI.ViewPostReport(currentReport));
						}
						else
						{
							manageMemberControl.handleAction(currentReport, 0, isPost);
							ResetGrid(grid);
							setGridStyleVUP(manageMemberUI.ViewFlaggedUserProfile(currentReport), 
									manageMemberUI.flagActions(), 
									manageMemberUI.reportList(1));
						}
						option3.disableProperty().bind(Bindings.createBooleanBinding(()->(true==res)));
						option1.disableProperty().bind(Bindings.createBooleanBinding(()->(true==res)));
					}
				}
			}else if(e.getSource() == option4) {
				ResetGrid(grid);
				setGridStyleMain(liUI.AdminLoggedIn(), manageMemberUI.flaggedButtons(), manageMemberUI.reportList(1));	
			}			
		}catch(Exception error)
		{
			errorDialog("Something went wrong with the Event Handler");
		}
	}

}
