
public class ViewMemberProfileControl {

	private MemberObject memberObject;

	private DataManager dataManager;

	public ViewMemberProfileControl(DataManager dm) {
		this.dataManager = dm;
	}

	public MemberObject handleViewMemberProfile(String memberID) {
		return dataManager.getMember(memberID);
	}
}