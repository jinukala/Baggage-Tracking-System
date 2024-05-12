package com.gl.app.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gl.app.dao.UserDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.util.BaggageUtil;
public class UserDAOImpl  implements UserDAO{
	  BaggageUtil baggageUtil = new BaggageUtil();
	@Override
	public void registerNewUser(User user) throws SQLException {
		//write the code to register user
		Connection conn = BaggageUtil.getConnection();
		String query = "INSERT INTO baggagetracking.users VALUES (?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1,user.userId());
		statement.setString(2,user.firstName());
		statement.setString(3, user.lastName());
		statement.setString(4, user.email());
		statement.execute();
	}

	public void checkInBaggage(Baggage baggage) throws SQLException {
       //write the code to check-in baggage
		Connection conn = BaggageUtil.getConnection();
		String query = "INSERT INTO baggagetracking.baggage VALUES(?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, baggage.getClaimId());
		statement.setString(2,baggage.getLocation());
		statement.setString(3,baggage.getStatus());
		statement.setString(4, baggage.getUserId());
		statement.execute();
    }
	
	public Baggage getBaggageInfo(String claimTagId) throws SQLException {
		//write the code to get baggage info
		Connection conn = BaggageUtil.getConnection();
		String query = "SELECT claimId,location,status FROM baggagetracking.baggage where claimId=?";
		PreparedStatement statement =conn.prepareStatement(query);
		statement.setString(1,claimTagId);
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			String cliamId = resultSet.getString("claimId");
			String location = resultSet.getString("location");
			String status = resultSet.getString("status");
			//String userId = resultSet.getString("userid");
			return new Baggage(cliamId,location,status,null);
		}
	    throw new BaggageNotFoundException("Baggage Not Found");
	}

}
