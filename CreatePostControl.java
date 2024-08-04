public class CreatePostControl {
	
	private DataManager dataManager;
	
	private String memberId;

	public CreatePostControl(DataManager dm) {
		this.dataManager = dm;
	}
	
	public Boolean handleCreatePost(String ISBN, String bookTitle, String author, String title, String memberId, String description) {
 
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	    if(title.trim().isEmpty()||description.trim().isEmpty())
	    {
	    	return false;
	    }
		return dataManager.addPostInfo(ISBN, bookTitle, author, title, description, memberId, sqlDate);
	}
}