import java.util.*;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.time.LocalDate;

public class BrowsePostUI {

	private  BrowsePostControl browsePostControl;
	private Main main;
	
	public BrowsePostUI(BrowsePostControl browsePostControl, Main main) 
	{
		this.browsePostControl = browsePostControl;
		this.main = main;
	}

	public void selectAndApplyFilters(boolean fromAfter, Date date, String memberID) {
		ArrayList<PostObject> posts = browsePostControl.handleBrowsePost(fromAfter, date, memberID);
	}

	public void displayFiltersForm() {
		boolean fromAfter; Date date; String memberID = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to filter using a memberID? (type 1 for yes, 0 for no)");
		if(scanner.next().equals("1")){
			System.out.println("Enter a memberID");
			memberID = scanner.next();
		}
		fromAfter = false;
		date = Date.valueOf(LocalDate.now());
		
		scanner.close();
		
		selectAndApplyFilters(fromAfter, date, memberID);
	}
	
	public ScrollPane PostsList(Boolean mP) {
		ScrollPane sp = new ScrollPane();
		sp.setStyle("-fx-background-color: #EFBC95;");

        sp.setContent(main.mPosts);
		sp.fitToWidthProperty().set(true);
		sp.fitToHeightProperty().set(true);
		return sp;
	}
	
	public VBox Posts(Boolean wL, ArrayList<PostObject> posts) {
		VBox vbox = new VBox(10);
		try
		{
			vbox.setPadding(new Insets(15, 12, 15, 12));
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER_LEFT);
			vbox.setStyle("-fx-background-color: #EFBC95;");
			

			int buttonsSize = posts.size();
			Button[] butt = new Button[buttonsSize]; 
			for(int i=0; i<buttonsSize; i++){
				butt[i] = new Button(posts.get(i).title);

				
				String postMemberID = posts.get(i).memberID;
				int postId = posts.get(i).postID;
				
				butt[i].disableProperty().bind(Bindings.createBooleanBinding(()->(false==main.loggedIn)));
				
				if(wL){
					butt[i].setOnAction(e -> main.GoToPostWL(postMemberID, postId));				
				}else
					butt[i].setOnAction(e -> main.GoToPost(postMemberID, postId));
				

				vbox.getChildren().add(butt[i]);
			}

			vbox.setAlignment(Pos.TOP_LEFT);			
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong with the posts");
		}
		
		return vbox;
	}
}