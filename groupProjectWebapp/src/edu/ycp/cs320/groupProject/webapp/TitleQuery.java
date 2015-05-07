package edu.ycp.cs320.groupProject.webapp;


import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.groupProject.webapp.UserPass;
import edu.ycp.cs320.groupProject.webapp.DatabaseProvider;
import edu.ycp.cs320.groupProject.webapp.IDatabase;

public class TitleQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);
		
		// Create the default IDatabase instance
		InitDatabase.init();
		
		System.out.print("Enter an id: ");
		String user = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		List<UserPass> userList = db.findUPByUser(user);
		if(userList.isEmpty()){
			System.out.println("true");
		}
		for(UserPass temp : userList){
			System.out.println("Username: "+temp.getUser()+ " Password: " +temp.getPass());
		}
		keyboard.close();
	}

}