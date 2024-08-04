
import java.util.ArrayList;
import java.util.Set;


public class MessageUserControl {

	private MessageObject messageObject;

	private DataManager dataManager;

	
	public MessageUserControl (DataManager dataManager) {
		this.dataManager = dataManager;
	}

	public ArrayList<MessageObject> handleDisplayChatRoom(String thisMember, String secondMember) {

		
		return dataManager.getMessages(thisMember, secondMember);

	}

	public ArrayList<String> handleDisplayInbox(String memberObj) {
		return dataManager.getChats(memberObj);
	}

	public Boolean handleSendMessage(String mem1, String message,  String mem2) {

		return dataManager.sendMessage(mem1, message, mem2);

	}
}