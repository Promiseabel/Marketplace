

import java.sql.*;
import java.util.*;
import java.sql.Date;



public class DataManager {
	
	Main main;
	
	Connection connection = null;
	
	public DataManager(Main main) {
		 try {
	         Class.forName("com.mysql.jdbc.Driver").newInstance();
	     } catch (Exception e) {
	      main.errorDialog(e.toString());
	     }
		String url = "jdbc:mysql://cs2043.cs.unb.ca:3306/cs204301ateam1";
		try {
		connection = DriverManager.getConnection(url, "cs204301ateam1", "NS00fOXQ");
		} catch (SQLException e) {
		main.errorDialog("Database connection error.");
		}
	}

	public Boolean flagMember(String memberID, String reason) {
		Boolean confirmation = false;
		
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "SELECT * FROM MemberProfile ";
			sqlQuery += "WHERE MemberID = \""+memberID+"\"";
			ResultSet rs = st.executeQuery(sqlQuery);
			if(rs.isBeforeFirst())
			{
				sqlQuery = "INSERT INTO Reports VALUES(NULL, NULL, \""+reason+"\",\""+memberID+"\")";
				st.executeUpdate(sqlQuery);
				confirmation = true;
			}			
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: flagMember");
		}
		return confirmation;
	}

	public Boolean flagPost(int postID, String reason) {
		Boolean confirmation = false;
		
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "SELECT * FROM PostInfo ";
			sqlQuery += "WHERE PostID = "+postID;
			ResultSet rs = st.executeQuery(sqlQuery);
			if(rs.isBeforeFirst())
			{
				sqlQuery = "INSERT INTO Reports VALUES(NULL, "+postID+", \""+reason+"\",NULL)";
				st.executeUpdate(sqlQuery);
				confirmation = true;
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: flagPost");
		}
		return confirmation;
	}

	public PostObject getPost(Integer postID) {
			PostObject post = new PostObject();
			try
			{
				Statement st = connection.createStatement();
				
				String sqlQuery = "select * from PostInfo where ";
				sqlQuery += "PostID = "+postID;
				ResultSet rs = st.executeQuery(sqlQuery);
				
				while(rs.next())
				{
					post.postID = rs.getInt(1);
					post.date = rs.getDate(2);
					post.description = rs.getString(3);
					post.bookID = rs.getInt(4);
					post.memberID = rs.getString(5);
					post.title= rs.getString(6);
				}
			}
			catch (SQLException e)
			{
				main.errorDialog("SQL error: getPost");
			}
			
			return post;
	}
	
	public AdminObject getAdminObject(int adminId) {
		AdminObject admin = new AdminObject();
		try
		{
			Statement st = connection.createStatement();

			String sqlQuery = "select * from AdminRecord where ";
			sqlQuery += "AdminID = " + adminId;
			ResultSet rs = st.executeQuery(sqlQuery);

			while(rs.next())
			{
				admin.adminID = rs.getInt(1);
				admin.name = rs.getString(2);
				admin.rank = rs.getInt(3);
				admin.email = rs.getString(4);
				admin.phonenumber = rs.getString(5);
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: getAdminObject");
		}

		return admin;
	}

	public MemberObject getMemberObject(String memberId) {
		MemberObject member = new MemberObject();
		try
		{
			Statement st = connection.createStatement();

			String sqlQuery = "select * from MemberProfile where ";
			sqlQuery += "MemberID = '" + memberId + "';";
			ResultSet rs = st.executeQuery(sqlQuery);

			while(rs.next())
			{
				member.memberID = rs.getString(1);
				member.name = rs.getString(2);
				member.location = rs.getString(3);
				member.email = rs.getString(4);
				member.phonenumber = rs.getString(5);
				member.bio = rs.getString(6);
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: getMemberObject");
		}

		return member;
	}

	public ArrayList<ReportObject> getFlaggedPosts() {
		ArrayList<ReportObject> postReports = new ArrayList<ReportObject>();
		
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from Reports where PostID is not null";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next())
			{
				ReportObject report = new ReportObject();
				report.reportID = rs.getInt(1);
				report.postID = rs.getInt(2);
				report.reason = rs.getString(3);
				report.memberID = rs.getString(4);
				report.post = this.getPost(report.postID);
				postReports.add(report);
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: getFlaggedPosts");
		}
		
		return postReports;
	}

	public ArrayList<ReportObject> getFlagged() {
		ArrayList<ReportObject> reports = new ArrayList<ReportObject>();
		
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from Reports";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next())
			{
				ReportObject report = new ReportObject();
				report.reportID = rs.getInt(1);
				report.postID = rs.getInt(2);
				report.reason = rs.getString(3);
				report.memberID = rs.getString(4);
				report.post = this.getPost(report.postID);
				report.member = this.getMember(report.memberID);
				reports.add(report);
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: getFlagged");
		}
		
		return reports;
	}

	public ArrayList<ReportObject> getFlaggedMembers() {
		ArrayList<ReportObject> memberReports = new ArrayList<ReportObject>();
		
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from Reports where MemberID is not null";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next())
			{
				ReportObject report = new ReportObject();
				report.reportID = rs.getInt(1);
				report.postID = rs.getInt(2);
				report.reason = rs.getString(3);
				report.memberID = rs.getString(4);
				report.member = this.getMember(report.memberID);
				memberReports.add(report);
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: getFlaggedMembers");
		}
		
		return memberReports;
	}
	
	public boolean loginSuccessful(String ID, String Password) {
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "select * from MemberProfile where MemberID = '" + ID + "' and Password = sha1('"+ Password + "')";
			ResultSet rs = st.executeQuery(sqlQuery);
			if (rs.next()) {
				return true;
			}
		}
		catch (SQLException e) {
			main.errorDialog("SQL error: getMemberLogin");
		}
		return false;
	}
	
	public boolean adminLogin(int ID, String Password) {
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "select * from AdminRecord where AdminID = " + ID + " and Password = sha1('"+ Password + "')";
			ResultSet rs = st.executeQuery(sqlQuery);
			if (rs.next()) {
				return true;
			}
		}
		catch (SQLException e) {
			main.errorDialog("SQL error: getMemberLogin");
		}
		return false;
	}
	
	public Boolean editMemberProfile(String memberID, String password, String name, String location, String email, String phonenumber, String bio, String memberIDold) {
		
		try{
			Statement st = connection.createStatement();
			String sqlQuery = "";
			if(!password.trim().isEmpty())
			{
				sqlQuery = "update MemberProfile set memberID = \"" + memberID 
								+ "\", Password = sha(\"" + password + "\") "
								+ ", Name = \"" + name + "\" "
								+ ", Location = \"" + location + "\" "
								+ ", Email = \"" + email + "\" "
								+ ", PhoneNumber = \"" + phonenumber + "\" "
								+ ", Bio = \"" + bio + "\" "
								+ "where MemberID = \"" + memberIDold + "\";";
			}
			else
			{
				sqlQuery = "update MemberProfile set memberID = \"" + memberID 
						+ "\", Name = \"" + name + "\" "
						+ ", Location = \"" + location + "\" "
						+ ", Email = \"" + email + "\" "
						+ ", PhoneNumber = \"" + phonenumber + "\" "
						+ ", Bio = \"" + bio + "\" "
						+ "where MemberID = \"" + memberIDold + "\";";
			}
			st.executeUpdate(sqlQuery);
			return true;
		}catch(SQLException e) {
			main.errorDialog("SQL error: editMemberProfile");
		}
		
		return false;

	}

	public ReportObject getFlag(int reportID) {
		ReportObject flag = new ReportObject();
		
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from Reports where ReportID = "+reportID;
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next())
			{
				flag.reportID = rs.getInt(1);
				flag.postID = rs.getInt(2);
				flag.reason = rs.getString(3);
				flag.memberID = rs.getString(4);
				flag.post = this.getPost(flag.postID);
				flag.member = this.getMember(flag.memberID);
			}
		}
		catch (SQLException e)
		{
			main.errorDialog("SQL error: getFlaggedMembers");
		}
		
		return flag;
	}

	public boolean removeReportObject(ReportObject report, boolean isPost) {
		boolean success = false;
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "";
			if(isPost)
			{
				sqlQuery = "delete from PostInfo where PostID = "+report.postID;
			}
			else
			{
				sqlQuery = "delete from MemberProfile where MemberID = \""+report.memberID+"\"";
			}
			st.executeUpdate(sqlQuery);
			success = true;
		}
		catch(SQLException e)
		{
			main.errorDialog("SQL Error: removeReportObject");
		}
		return success;
	}

	public boolean bypassReport(ReportObject report) {
		boolean success = false;
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "delete from Reports where ReportID = "+report.reportID;
			st.executeUpdate(sqlQuery);
			success = true;
		}
		catch(SQLException e)
		{
			main.errorDialog("SQL Error: removeReportObject");
		}
		return success;
	}

	public boolean addMemberInfo(String id, String password, String name, String location, String email,
			String phonenumber, String bio) {
		try {
			Statement st = connection.createStatement();
			String check = "select * from MemberProfile where memberid = '" + id + "';";
			ResultSet rs =st.executeQuery(check);
			if(rs.next()) {
				return false;
			}
			String sqlQuery = "insert into MemberProfile values('" + id + "', '" + name + "', '" +
								location + "', '"  + email + "', '" + phonenumber + "', '" + bio + "', "+ "sha1('"+ password + "'));";
								
			st.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			main.errorDialog("SQL error: addMemberInfo");
			return false;
		}
		return true;

	}
	
	public void saveMemberObject(MemberObject memberObject) {
		
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "insert into MemberProfile values('" + memberObject.memberID + "',  '" + memberObject.name + "', '" +
					memberObject.location + "', '"  + memberObject.email + "', '"  + memberObject.phonenumber + "', '"  + memberObject.bio + "');" ;
			st.executeQuery(sqlQuery);
		} catch (SQLException e) {
			main.errorDialog("SQL error: addMemberInfo");
		}
	
	}

	public boolean addPostInfo(String ISBN, String bookTitle, String author, String title, String description, String memberId, Date timeNow) {
		boolean success = false;
		try {
			Statement st = connection.createStatement();
			if(memberId == null) {
				return false;
			}
			String sqlFindPostID = "select count(*) from PostInfo";
			ResultSet rs2 = st.executeQuery(sqlFindPostID);
			rs2.next();
			int id = rs2.getInt(1) + 1;
			String check = "select * from BookInfo where Isbn = '" + ISBN + "';";
			ResultSet rs = st.executeQuery(check);
			int bookId;
			if(rs.next()) {
				bookId = rs.getInt(1);
			}
			else{
				String sqlFindBookID = "select count(*) from BookInfo;";
				ResultSet rs1 = st.executeQuery(sqlFindBookID);
				rs1.next();
				bookId = rs1.getInt(1) +1;
				String insertBook = "insert into BookInfo(Isbn, Title, Author) values(\"" + ISBN + "\", \"" + bookTitle + "\", \"" + author + "\");";
				st.executeUpdate(insertBook);
			}
			String sqlQuery = "insert into PostInfo(Date, Description, BookID, MemberID, Title) values(\"" + timeNow + "\", \"" + description + "\", \"" + bookId + "\", \""
								+ memberId + "\", \"" + title + "\");";
			st.executeUpdate(sqlQuery);
			success = true;
		} catch (SQLException e) {
			main.errorDialog("SQL error: addPostInfo");
		}
		return success;
	}


	public ArrayList<PostObject> getWishList(String memberid) {

		ArrayList<PostObject>  wishlist = new ArrayList<PostObject>(); 
		try
		{
			Statement st = connection.createStatement();
			
			String sqlQuery = "select * from WishList inner join PostInfo ";
			sqlQuery += "on WishList.PostID = PostInfo.PostID where ";
			sqlQuery += "WishList.MemberID = '" + memberid + "';";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next())
			{
				PostObject post = new PostObject();
				post.postID = rs.getInt(3);
				post.date = rs.getDate(4);
				post.description = rs.getString(5);
				post.bookID = rs.getInt(6);
				post.memberID = rs.getString(7);
				post.title= rs.getString(8);
				wishlist.add(post);
			}
			
		}catch (SQLException e){
			main.errorDialog("SQL error: getWishList");
		}
		
		return wishlist;

	}


	public Boolean saveToWishList(Integer postID, String memberID) {
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "insert into WishList values ( "+postID+", '"+ memberID +"');";
			st.executeUpdate(sqlQuery);
			return true;
		} catch (SQLException e) {
			main.errorDialog("SQL error: saving post. either postID or memberID does not exist in database");
		}
		
		return false;

	}

	public Boolean postInWishList(int postID, String memberID){
		try {
			Statement st = connection.createStatement();
			String check = "select * from WishList where MemberID = '" + memberID + "' and PostID = '" + postID + "';";
			ResultSet rs = st.executeQuery(check);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			main.errorDialog("SQL error: postInWishList");
		}
		return false;
	}

	public ArrayList<MessageObject> getMessages(String memberS, String memberR) {

		ArrayList<MessageObject> chatroom = new ArrayList <MessageObject>();
		
		try
		{
			Statement st = connection.createStatement();
			
			
			
			String sqlQuery = "select * from MessageInfo where (MemberID1 = '" + memberS + "' AND MemberID2 = '" + memberR + "') OR (MemberID1 = '" + memberR +  "' AND MemberID2 = '" + memberS + "') ";
			sqlQuery +=  " ORDER BY Date ";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next()) {
				MessageObject message = new MessageObject(); 
				
				message.message = rs.getString(1);
				message.date = rs.getTimestamp(2);
				message.memberID1 = rs.getString(3);
				message.memberID2 = rs.getString(4);
				chatroom.add(message);
				
			}
		}catch (SQLException e) {
			main.errorDialog("SQL error: getMessages");
		}
		
	
		
		return chatroom;

	}
	
	public ArrayList<String> getChats(String member) {
		
		ArrayList<String> chats = new ArrayList <String>();
		
		try
		{
			Statement st = connection.createStatement();

			
			String sqlQuery = "Select distinct MemberID2 from ((Select Max(Date) Date,MemberID2 from MessageInfo Where MemberID1 = '"+member
					+ "' group by MemberID2)UNION(Select Max(Date) Date,MemberID1 from MessageInfo Where MemberID2 = '"+member
					+ "' group by MemberID1 ) order by Date desc) AS recent;";
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while(rs.next()) {
				chats.add(rs.getString(1));
			}
		}catch (SQLException e) {
			main.errorDialog("SQL error: getChats");
		}
		
		return chats;
	}


	public Boolean sendMessage(String memberS, String message, String memberR) {
		
		try {
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
			
			Statement st = connection.createStatement();
			String sqlQuery = "insert into MessageInfo values ('" + message + "', '" + sqlDate + "', '"+memberS +"', '"+memberR+"');";
			st.executeUpdate(sqlQuery);
			return true;
		}catch(SQLException e) {
			main.errorDialog("SQL error: sendMessage");
		}
		return false;
		
	}

	public Boolean editPost(int postID, String title, String description, Date timeNow){
		
		try{
			Statement st = connection.createStatement();
			String sqlQuery = "update PostInfo set Title = \"" + title + 
							"\", Description = \"" + description + "\" "+ 
							"where PostID = \"" + postID + "\";";
			st.executeUpdate(sqlQuery);
			return true;
		}catch(SQLException e) {
			main.errorDialog("SQL error: editPost");
		}
		
		return false;
		
	}


	public Boolean deletePost(Integer postID) {
		
		try{
			Statement st = connection.createStatement();
			String sqlQuery = "DELETE FROM PostInfo WHERE PostID = "+postID;
			st.executeUpdate(sqlQuery);
			return true;
		}catch(SQLException e) {
			main.errorDialog("SQL error: deletePost");
		}
		
		return false;
		
	}


	public MemberObject getMember(String ID, String Password) {
		
		MemberObject member = new MemberObject();
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "select * from MemberProfile where MemberID = '" + ID + "' and Password = sha1('"  + Password + "')";
			ResultSet rs = st.executeQuery(sqlQuery);
			if (rs.next()) {
				member.memberID = rs.getString(1);
				member.name = rs.getString(2);
				member.location = rs.getString(3);
				member.email = rs.getString(4);
				member.phonenumber = rs.getString(5);
				member.bio = rs.getString(6);
			}
		} catch (SQLException e) {
			main.errorDialog("SQL error: getMember");
		}
		return member;

	}

	public MemberObject getMember(String ID) {
		
		MemberObject member = new MemberObject();
		try {
			Statement st = connection.createStatement();
			String sqlQuery = "select * from MemberProfile where MemberID = '" + ID + "'";
			ResultSet rs = st.executeQuery(sqlQuery);
			if (rs.next()) {
				member.memberID = rs.getString(1);
				member.name = rs.getString(2);
				member.location = rs.getString(3);
				member.email = rs.getString(4);
				member.phonenumber = rs.getString(5);
				member.bio = rs.getString(6);
			}
		} catch (SQLException e) {
			main.errorDialog("SQL error: getMember");
		}
		return member;
	
	}

	public ArrayList<PostObject> searchPostByKeywords(ArrayList<String> keywords) {
		ArrayList<PostObject> postList = new ArrayList<PostObject>();
		try {
			Statement st = connection.createStatement();
			

			String sqlQuery = "select * from PostInfo where ";
			for (int i=0; i<keywords.size(); i++) {
				if (i < keywords.size() - 1) {
					sqlQuery = sqlQuery + "PostInfo.Title like '%" + keywords.get(i) + "%' or ";
					sqlQuery = sqlQuery + "PostInfo.Description like '%" + keywords.get(i) + "%' or ";
				} else sqlQuery = sqlQuery + "PostInfo.Title like '%" + keywords.get(i) + "%' or " 
											+ "PostInfo.Description like '%" + keywords.get(i) + "%';";
			}
			
			ResultSet rs = st.executeQuery(sqlQuery);
			
			while (rs.next()) {
				PostObject post = new PostObject();
				post.postID = rs.getInt(1);
				post.date = rs.getDate(2);
				post.description = rs.getString(3);
				post.bookID = rs.getInt(4);
				post.memberID = rs.getString(5);
				post.title = rs.getString(6);
				postList.add(post);
			}
		} catch (SQLException e) {
			main.errorDialog("SQL error: searchPostByKeywords");
		}
		
		return postList;
	}


	public ArrayList<PostObject> getPostList(boolean fromAfter, Date date, String memberID) {
		ArrayList<PostObject> postList = new ArrayList<PostObject>();
		try {
			Statement st = connection.createStatement();
			

			String sqlQuery = "select * from PostInfo where ";
			sqlQuery += "PostInfo.Date " + (fromAfter?  ">= ":"<= ");
			sqlQuery += "'" +date.toLocalDate().toString()+ "' ";
			sqlQuery += ((memberID != "" && memberID != null)?  "AND PostInfo.MemberID like '" + memberID + "%' " :"");

			sqlQuery += "ORDER BY PostInfo.Date LIMIT 12;";

			ResultSet rs = st.executeQuery(sqlQuery);

			while (rs.next()) {
				PostObject post = new PostObject();
				post.postID = rs.getInt(1);
				post.date = rs.getDate(2);
				post.description = rs.getString(3);
				post.bookID = rs.getInt(4);
				post.memberID = rs.getString(5);
				post.title = rs.getString(6);
				postList.add(post);
			}
		} catch (SQLException e) {
			main.errorDialog("SQL error: getPostList");
		}
		
		return postList;
	}

}
