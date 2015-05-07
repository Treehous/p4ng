package edu.ycp.cs320.groupProject.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import edu.ycp.cs320.groupProject.webapp.UserPass;

public class InitialData {
	public static List<UserPass> getCredList() throws IOException {
		List<UserPass> credList = new ArrayList<UserPass>();
<<<<<<< HEAD
		ReadCSV readCred = new ReadCSV("authors.csv");
=======
		ReadCSV readCred = new ReadCSV("DB.csv");
>>>>>>> refs/remotes/choose_remote_name/master
		try {
			while (true) {
				List<String> tuple = readCred.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				UserPass author = new UserPass();
				author.setId(Integer.parseInt(i.next()));
				author.setPass(i.next());
				author.setUser(i.next());
				credList.add(author);
			}
			return credList;
		} finally {
			readCred.close();
		}
	}
}
