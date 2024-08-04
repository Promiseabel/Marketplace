
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class SearchPostUI {

	private SearchPostControl searchPostControl;

	private Main main;
	
	public SearchPostUI(SearchPostControl control, Main main) {
		searchPostControl = control;
		this.main = main;
	}

	public HBox SearchBrowse() 
	{
		
		HBox hbox = new HBox();
		try
		{
			hbox.setPadding(new Insets(15, 12, 15, 12));
			hbox.setSpacing(10);
			hbox.setStyle("-fx-background-color: #BE9473;");
			
			TextField keywordsTF, memberidTF;
			keywordsTF = new TextField();
			HBox.setHgrow(keywordsTF, Priority.ALWAYS);
			keywordsTF.setPromptText("Search For Keywords");
			keywordsTF.setPrefWidth(175);
			memberidTF = new TextField();
			memberidTF.setMinWidth(50);
			memberidTF.setPromptText("Search For Member");
			
			main.searchButton = new Button("Search");
			
			if(main.loggedIn) main.searchButton.setDefaultButton(true);
			main.searchButton.setOnAction(e -> {
						if((!keywordsTF.getText().equals("")) && (memberidTF.getText().equals(""))) {
							ArrayList<String> keywordList = new ArrayList<String>();
							Scanner scanner = new Scanner(keywordsTF.getText());
							while (scanner.hasNext()) {
								String keyword = scanner.next();
								
								keywordList.add(keyword);
							}
							scanner.close();
							main.mPosts = main.bpUI.Posts(false, searchPostControl.handleSearchPost(keywordList));
							main.ResetGrid(main.grid);
							if(main.loggedIn)
								main.setGridStyleMain(main.liUI.LoggedIn(), SearchBrowse(), main.bpUI.PostsList(false));
							else
								main.setGridStyleMain(main.liUI.LogIn(), SearchBrowse(), main.bpUI.PostsList(false));
						}else {
							main.mPosts = main.bpUI.Posts(false, main.bpControl.handleBrowsePost(false, Date.valueOf(LocalDate.now()), memberidTF.getText()));
							if(main.loggedIn) {
								main.ResetGrid(main.grid);
								main.setGridStyleMain(main.liUI.LoggedIn(), SearchBrowse(), main.bpUI.PostsList(false));

							}else {
								main.ResetGrid(main.grid);
								main.setGridStyleMain(main.liUI.LogIn(), SearchBrowse(), main.bpUI.PostsList(false));
							}
						}
			});
			main.searchButton.setPrefSize(60, 20);
			
			hbox.getChildren().addAll(keywordsTF, memberidTF, main.searchButton);			
		}catch(Exception error)
		{
			main.errorDialog("Something went wrong with the search browse");
		}

		return hbox;
	}
}