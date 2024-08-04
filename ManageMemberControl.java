
import java.util.*;

public class ManageMemberControl {

	private DataManager dm;

	public ManageMemberControl(DataManager dataManager)
	{
		dm = dataManager;
	}
	
	public ArrayList<ReportObject> handleMemberReports() {
		ArrayList<ReportObject> reports = dm.getFlaggedMembers();
		
		return reports;
	}

	public ArrayList<ReportObject> handlePostReports() {
		ArrayList<ReportObject> reports = dm.getFlaggedPosts();
		
		return reports;
	}
	
	public ArrayList<ReportObject> handleReports() {
		ArrayList<ReportObject> reports = dm.getFlagged();
		
		return reports;
	}
	
	public ReportObject getFlag(int reportID)
	{
		return dm.getFlag(reportID);
	}

	public Boolean handleAction(ReportObject report, int action,boolean isPost) {
		boolean success = false;
		if(action==1)
		{
			success = dm.removeReportObject(report, isPost);
		}
		else
		{
			success = dm.bypassReport(report);
		}
		return success;
	}
}