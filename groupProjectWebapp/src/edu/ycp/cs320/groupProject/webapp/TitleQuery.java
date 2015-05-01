package edu.ycp.cs320.groupProject.webapp;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.ycp.cs320.groupProject.webapp.UserPass;
import edu.ycp.cs320.groupProject.webapp.DatabaseProvider;
import edu.ycp.cs320.groupProject.webapp.IDatabase;

public class TitleQuery {
	public static void main(String[] args) throws Exception {
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.print("Enter a user: ");
		String title = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		List<UserPass> userList = db.findUserByUserId(title);
		Iterator<UserPass> userItr = userList.iterator();
		while (userItr.hasNext())
			System.out.println(userItr.next());
		}
	}

