import java.util.Scanner;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportUI {

	private ReportControl control;
	private Main main;

	public ReportUI(ReportControl reportControl, Main main)
	{
		control = reportControl;
		this.main = main;
	}
	
	public void reportBox(Boolean isPost, String memberID, int postId)
	{
		try
		{
			final Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.APPLICATION_MODAL);
			
			Label questionLabel = new Label("For what reason would you like to report?");
			questionLabel.setAlignment(Pos.BASELINE_CENTER);
			
			TextField reason = new TextField();
			
			Button submitBtn = new Button("Submit");
			submitBtn.setOnAction(e -> 
			{
				main.res = true; 
				dialogStage.close();
				if(isPost)
				{
					control.handleReport(isPost, "", postId, reason.getText());
				}
				else
				{
					control.handleReport(isPost, memberID, 0, reason.getText());
				}
			});
			
			submitBtn.disableProperty().bind(Bindings.isEmpty(reason.textProperty()));
	
			
			VBox vbox = new VBox(10);
			vbox.setPadding(new Insets(15, 12, 15, 12));
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER);
			vbox.setStyle("-fx-background-color: #A68267;");
			
			vbox.getChildren().addAll(questionLabel,reason, submitBtn);
			
			vbox.setPrefWidth(main.widthCap);
			
			dialogStage.setScene(new Scene(vbox));
			dialogStage.showAndWait();
		}catch(Exception error)
		{
			main.errorDialog("Something went wrong with the report box");
		}

	}
}