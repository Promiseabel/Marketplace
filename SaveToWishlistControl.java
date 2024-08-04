
import java.util.*;

public class SaveToWishlistControl {

	private PostObject postObject;

	private WishListObject wishListObject;

	private MemberObject memberObject;

	private DataManager dataManager;

	public SaveToWishlistControl(DataManager dm)
	{
		this.dataManager = dm;
	}

	public ArrayList<PostObject> getWishlist(MemberObject memberObj) {

		return dataManager.getWishList(memberObj.memberID);

	}

	public Boolean postInWishList(int postID, String memberID) {

		return dataManager.postInWishList(postID, memberID);
	}

	public Boolean handleSaveToWishList(int postID, String memberID) {

		return dataManager.saveToWishList(postID, memberID);	
	}
}