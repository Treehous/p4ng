package edu.ycp.cs320.groupProject.webapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.groupProject.webapp.UserPass;


public class SqliteDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	@Override
	public List<UserPass> findUserByUserId(final String title) {
		return executeTransaction(new Transaction<List<UserPass>>() {
			@Override
			public List<UserPass> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select userTab.*" +
							"  from userTab " +
							"    and userTab.id = ?"
					);
					stmt.setString(1, title);
					
					List<UserPass> result = new ArrayList<UserPass>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						UserPass userPass = new UserPass();
						loadUserPass(userPass, resultSet, 1);
						result.add(userPass);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public List<UserPass> findUPByUser(final String user) {
		return executeTransaction(new Transaction<List<UserPass>>() {
			@Override
			public List<UserPass> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select userTab.*" +
							"  from userTab " +
							"    where userTab.user = ?"
					);
					stmt.setString(1, user);
					
					List<UserPass> result = new ArrayList<UserPass>();
					
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						UserPass userPass = new UserPass();
						loadUserPass(userPass, resultSet, 1);
						result.add(userPass);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:test.db");
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadUserPass(UserPass e, ResultSet resultSet, int index) throws SQLException {
		e.setId(resultSet.getInt(index++));
		e.setPass(resultSet.getString(index++));
		e.setUser(resultSet.getString(index++));
	}
	
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt2 = null;
				
				try {
					
					stmt2 = conn.prepareStatement(
							"create table userTab (" +
							"    id integer primary key," +
							"    pass varchar(40)," +
							"    user varchar(40)" +
							")");
					stmt2.executeUpdate();
					
					return true;
				} finally {

					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	public void dropTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"drop table userTab; ");
					stmt.executeUpdate();
					
					return true;
				} finally {

					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<UserPass> credList;
				
				try {
					credList = InitialData.getCredList();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAuthor = null;
				

				try {
					insertAuthor = conn.prepareStatement("insert into userTab values (?, ?, ?)");
					for (UserPass author : credList) {
						insertAuthor.setInt(1, author.getId());
						insertAuthor.setString(2, author.getPass());
						insertAuthor.setString(3, author.getUser());
						insertAuthor.addBatch();
					}
					insertAuthor.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertAuthor);
				}
			}
		});
	}
	public void populateTables(){
		System.out.println("Creating tables...");
		SqliteDatabase db = new SqliteDatabase();
		try{
			db.dropTables();
		}finally{
			db.createTables();
			
			System.out.println("Loading initial data...");
			db.loadInitialData();
			
			System.out.println("Success!");
	}}
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		SqliteDatabase db = new SqliteDatabase();
		try{
			db.dropTables();
		}finally{
			db.createTables();
			
			System.out.println("Loading initial data...");
			db.loadInitialData();
			
			System.out.println("Success!");
	}}

}
