import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


public class SaveToWishlistUI {

	private SaveToWishlistControl control;
	private Main main;
	
	public SaveToWishlistUI(SaveToWishlistControl saveToWishlistControl, Main main)
	{
		this.control = saveToWishlistControl;
		this.main = main;
	}
	
	public ScrollPane WishList() {
		ScrollPane sp = new ScrollPane();
		try
		{
			sp.setStyle("-fx-background-color: #19A62F;");

			VBox wishlist = main.bpUI.Posts(true, control.getWishlist(main.liControl.getMemberObject()));

			sp.setContent(wishlist);
			sp.fitToWidthProperty().set(true);
			sp.fitToHeightProperty().set(true);			
		}
		catch(Exception error)
		{
			main.errorDialog("Something went wrong with the wishlist");
		}

		return sp;
	}
}