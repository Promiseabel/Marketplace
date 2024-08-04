import java.sql.Date;

public class EditPostControl {

	private MemberObject memberObject;

	private DataManager dataManager;
	
	private LoginControl LogControl;
	
	
	
	
	public EditPostControl(DataManager dm) {
		
		this.dataManager = dm;
	}

	public Boolean handleEditPost(int postid, String title, String description) {

		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		
		
		return dataManager.editPost(postid, title,  description, sqlDate);

	}

	public Boolean handleDeletePost(int postID) {
		
		return dataManager.deletePost(postID);

	}
}