package edu.ycp.cs320.groupProject.webapp;

import java.util.List;

public interface IDatabase {

	public List<UserPass> findUPByUser(String user);

}
