package edu.ycp.cs320.groupProject.webapp;

import java.util.Scanner;

import edu.ycp.cs320.groupProject.webapp.DatabaseProvider;
//import edu.ycp.cs320.booksdb.persist.FakeDatabase;
import edu.ycp.cs320.groupProject.webapp.SqliteDatabase;

public class InitDatabase {
	public static void init(Scanner keyboard) {
		//.out.print("Which database (0=fake, 1=sqlite): ");
		//int which = Integer.parseInt(keyboard.nextLine());
		//if (which == 0) {
			//DatabaseProvider.setInstance(new FakeDatabase());
		//} else if (which == 1) {
			DatabaseProvider.setInstance(new SqliteDatabase());
		//} else {
			//throw new IllegalArgumentException("Invalid choice: " + which);
		//}
	}
}
