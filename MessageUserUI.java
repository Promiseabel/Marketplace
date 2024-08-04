import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class MessageUserUI {

	private MessageUserControl messageUserControl;
	private Main main;


	public MessageUserUI(MessageUserControl control, Main main) 
	{
		messageUserControl = control;
		this.main = main;
	}

	
	public ScrollPane msgList()
	{
		ScrollPane sp = new ScrollPane();
		try
		{
			sp.setStyle("-fx-background-color: #EFBC95;");
			
			VBox vbox = new VBox(10);
	
			vbox.setPadding(new Insets(15, 12, 15, 12));
			vbox.setSpacing(10);
			vbox.setAlignment(Pos.CENTER_LEFT);
			vbox.setStyle("-fx-background-color: #EFBC95;");
			
			ArrayList<String> msgs = messageUserControl.handleDisplayInbox(main.liControl.getMemberObject().memberID);
			
			int buttonsSize = msgs.size();
			Button[] butt = new Button[buttonsSize]; 
			for(int i=0; i<buttonsSize; i++){
				String chatMember = msgs.get(i);
				butt[i] = new Button(chatMember);
				
				
				
				butt[i].setOnAction(e -> 
				{
					main.ResetGrid(main.grid);
					main.setGridStyleMSG(main.vmpUI.ViewUserProfile(chatMember), this.messages(chatMember));
				});
				

				vbox.getChildren().add(butt[i]);
			}
			vbox.setAlignment(Pos.TOP_LEFT);
	        sp.setContent(vbox);
			sp.fitToWidthProperty().set(true);
			sp.fitToHeightProperty().set(true);			
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong with message list");
		}
		

		return sp;
	}
	
	public BorderPane messages(String memberID)
	{
		BorderPane parent = new BorderPane();
		try
		{
			
			ScrollPane child = new ScrollPane();
			GridPane grandChild = new GridPane();
			grandChild.setPadding(new Insets(10,10,10,10));
			grandChild.setStyle("-fx-background-color: #F4C8B1;");
			grandChild.setGridLinesVisible(false);
			HBox bottom = new HBox(10);
			bottom.setPadding(new Insets(15, 12, 15, 12));
			TextField text = new TextField();
			HBox.setHgrow(text, Priority.ALWAYS);
			text.setPrefWidth(175);
			text.setPromptText("Message...");
			Button send = new Button("Send");
			
			MemberObject me = main.liControl.getMemberObject();
			MemberObject them = main.currentMember;
			
			ArrayList<MessageObject> msgs = messageUserControl.handleDisplayChatRoom(me.memberID, them.memberID);
			
			for(int i=0; i<msgs.size(); i++)
			{	
				VBox msgField = new VBox(2);
				msgField.setPadding(new Insets(10,10,10,10));
				
				if(msgs.get(i).memberID1.equals(me.memberID))
				{
					msgField.setStyle("-fx-border-color: #ffffff;");
					Text message = new Text(msgs.get(i).message);
					Text date = new Text(msgs.get(i).date.toString());
					msgField.getChildren().addAll(message, date);
					grandChild.add(msgField, 0, i);
					
				}
				else 
				{
					msgField.setStyle("-fx-border-color: #2E282A;");
					Text message = new Text(msgs.get(i).message);
					Text date = new Text(msgs.get(i).date.toString());
					msgField.getChildren().addAll(message, date);
					msgField.setAlignment(Pos.CENTER_RIGHT);
					grandChild.add(msgField, 1, i);
				} 
			}
			ColumnConstraints column1 = new ColumnConstraints();
			column1.setPercentWidth(50);
			grandChild.getColumnConstraints().add(column1);
			ColumnConstraints column2 = new ColumnConstraints();
			column2.setPercentWidth(50);
			grandChild.getColumnConstraints().add(column2);
			child.setVvalue(child.getVmax());
	
			child.prefHeightProperty().bind(parent.heightProperty());
			child.prefWidthProperty().bind(parent.widthProperty());
			grandChild.prefHeightProperty().bind(child.heightProperty());
			grandChild.prefWidthProperty().bind(child.widthProperty());
			text.setOnAction(e ->
			{
				if(!text.getText().trim().isEmpty())
				{
					messageUserControl.handleSendMessage(me.memberID, text.getText(), them.memberID);
					main.ResetGrid(main.grid);
					main.setGridStyleMSG(main.vmpUI.ViewUserProfile(main.currentMember.memberID), messages(main.currentMember.memberID));
				}
	
			});
			
			
			send.setOnAction(e -> 
			{
				if(!text.getText().trim().isEmpty())
				{
					messageUserControl.handleSendMessage(me.memberID, text.getText(), them.memberID);
					main.ResetGrid(main.grid);
					main.setGridStyleMSG(main.vmpUI.ViewUserProfile(main.currentMember.memberID), messages(main.currentMember.memberID));
				}
			});
			child.fitToHeightProperty().set(true);
			child.fitToWidthProperty().set(true);
			child.setContent(grandChild);
			parent.setCenter(child);
			bottom.getChildren().addAll(text, send);
			parent.setBottom(bottom);
			parent.setPadding(new Insets(0, 0, 0, 0));
			parent.setStyle("-fx-background-color: #f5a791;");
	
						
		}catch(Exception error)
		{
			main.errorDialog("Something went wrong with the messages");
		}
		return parent;
	}
	
}