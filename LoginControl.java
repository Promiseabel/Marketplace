
public class LoginControl {

	private MemberObject memberObject;

	private DataManager dataManager;

	private AdminObject adminObject;

	public LoginControl(DataManager dm) {
		this.dataManager = dm;
	}

	public AdminObject getAdminObject(int adminId) {

		adminObject =  dataManager.getAdminObject(adminId);
		return adminObject;

	}
	
	public AdminObject getAdminObject() {

		return adminObject;

	}

	public void saveAdminObject(AdminObject adminObj) {

		adminObject = adminObj;

	}

	public MemberObject getMemberObject(String memberId) {

		memberObject =  dataManager.getMemberObject(memberId);

		return memberObject;

	}
	
	public MemberObject getMemberObject() {

		return memberObject;

	}

	public void saveMemberObject(MemberObject memberObj) {

		memberObject = memberObj;
	}

	public boolean handleLogin(String ID, String Password) {

		return dataManager.loginSuccessful(ID, Password);

	}
	
	public boolean handleAdminLogin(int ID, String Password) {

		return dataManager.adminLogin(ID, Password);

	}
}