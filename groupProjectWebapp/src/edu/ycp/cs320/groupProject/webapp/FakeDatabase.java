package edu.ycp.cs320.groupProject.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.groupProject.webapp.shared.model.UserPass;


public class FakeDatabase implements IDatabase {
	
	private List<UserPass> credList;
	
	public FakeDatabase() {
		credList = new ArrayList<UserPass>();
		
		// Add initial data
		readInitialData();
		
//		System.out.println(authorList.size() + " authors");
//		System.out.println(bookList.size() + " books");
	}

	public void readInitialData() {
		try {
			credList.addAll(InitialData.getCredList());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	
	private UserPass findUserByUserId(int authorId) {
		for (UserPass author : credList) {
			if (author.getId() == authorId) {
				return author;
			}
		}
		return null;
	}

	@Override
	public List<UserPass> findUserByUserId(String title) {
		// TODO Auto-generated method stub
		return null;
	}
}
