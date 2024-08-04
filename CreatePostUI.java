import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
public class CreatePostUI {

	private CreatePostControl createPostControl;
	private Main main;

	public CreatePostUI(CreatePostControl control, Main main) {
		createPostControl = control;
		this.main = main;
	}

	public GridPane PostEdit(Boolean edit) {

		GridPane grid2 = new GridPane();

        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(10, 10, 10, 10));
		grid2.setGridLinesVisible(main.showGridLines);
		grid2.setStyle("-fx-background-color: #8E705B;");
		
		
		Label label = new Label("Title");

		main.addToCreateEditGridL(grid2, label, 0, 0);
		main.titleTF = new TextField();
		if(edit) main.titleTF.setText(main.currentPost.title);

		main.addToCreateEditGridR(grid2, main.titleTF, 1, 0);
		
		Label label5 = new Label("Description");
		main.addToCreateEditGridL(grid2, label5, 0, 1);
		main.descTA = new TextArea();
		main.descTA.setWrapText(true);
		if(edit) main.descTA.setText(main.currentPost.description);
		main.addToCreateEditGridR(grid2, main.descTA, 1, 1, true);
		
		if(!edit) {
			Label label1 = new Label("ISBN");
			main.addToCreateEditGridL(grid2, label1, 0, 2);
			main.isbnTF = new TextField();
			main.addToCreateEditGridR(grid2, main.isbnTF, 1, 2);
			
			Label label2 = new Label("Book Title");
			main.addToCreateEditGridL(grid2, label2, 0, 3);
			main.bTitleTF = new TextField();
			main.addToCreateEditGridR(grid2, main.bTitleTF, 1, 3);
			
			Label label3 = new Label("Author");
			main.addToCreateEditGridL(grid2, label3, 0, 4);
			main.authorTF = new TextField();
			main.addToCreateEditGridR(grid2, main.authorTF, 1, 4);
		}
		
		return grid2;
	}
}
