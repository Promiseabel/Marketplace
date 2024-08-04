
import java.util.*;

public class SearchPostControl {

	private PostObject postObject;

	private DataManager dataManager;
	
	public SearchPostControl(DataManager dm) {
		this.dataManager = dm;
	}

	public ArrayList<PostObject> handleSearchPost(ArrayList<String> keywords) {
		return dataManager.searchPostByKeywords(keywords);
	}
}