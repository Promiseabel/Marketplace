
import java.util.*;
import java.sql.Date;

public class BrowsePostControl {

	private DataManager dataManager;
	
	public BrowsePostControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	public ArrayList<PostObject> handleBrowsePost(boolean fromAfter, Date date, String memberID) {
		return dataManager.getPostList(fromAfter, date, memberID);
	}
}