
public class CreateAccountControl {
	

	private DataManager dataManager;
	
	public CreateAccountControl(DataManager dm) {
		dataManager = dm;
	}

	public Boolean handleCreateAccount(String id, String password, String name, String location, String email,
			String phonenumber, String bio) {	
		return dataManager.addMemberInfo(id, password, name, location, email, phonenumber, bio);
	}
	
}
