
public class EditMemberProfileControl {
	
	private MemberObject memberObject;
	
	private DataManager dataManager;

	public EditMemberProfileControl(DataManager dm) {
		this.dataManager = dm;
	}

	
	public Boolean handleEditMemberProfile(String memberID, String password, String name, String location, String email, String phonenumber, String bio, String memberIDold) {
		
		return dataManager.editMemberProfile(memberID, password, name, location, email, phonenumber, bio, memberIDold);	
	}
}