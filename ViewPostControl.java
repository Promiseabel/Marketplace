public class ViewPostControl {

	private DataManager dm;
	
	public ViewPostControl(DataManager dataManager)
	{
		dm = dataManager;
	}

	public PostObject handleViewPost(int postID) {
		return dm.getPost(postID);
	}
}