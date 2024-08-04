
public class ReportControl {

	private DataManager dm;

	public ReportControl(DataManager dataManager)
	{
		dm = dataManager;
	}
	
	public Boolean handleReport(Boolean isPost, String memberID, int postID, String reason) {
		if(isPost)
		{
			return dm.flagPost(postID, reason);
		}
		else
		{
			return dm.flagMember(memberID, reason);
		}
	}
	
}