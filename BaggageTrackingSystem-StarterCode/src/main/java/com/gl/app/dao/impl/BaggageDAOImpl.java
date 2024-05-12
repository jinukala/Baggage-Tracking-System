package com.gl.app.dao.impl;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.util.BaggageUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaggageDAOImpl implements BaggageDAO {
    private Connection connection;
    private BaggageUtil baggageUtils;
    

    public BaggageDAOImpl() {
    }
    public BaggageDAOImpl(BaggageDAO baggageDAO){


    }
    @Override
    public String getBaggageStatus(String claimTagId) throws SQLException {
    //write the code to get baggage status
       connection = BaggageUtil.getConnection();
        String query = "SELECT claimId,location,status FROM baggagetracking.baggage where claimId=?";
        PreparedStatement statement =connection.prepareStatement(query);
        statement.setString(1,claimTagId);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            String status = resultSet.getString("status");
            return "Status : "+status;
        }
        throw new BaggageNotFoundException("Baggage Not Found");

    }
    @Override
    public void updateBaggageStatus(String claimTagId, String status) throws SQLException {
    	//write the code to update baggage status

        connection = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE baggagetracking.baggage SET status = ? WHERE claimid  = ?");
        preparedStatement.setString(1,status);
        preparedStatement.setString(2, claimTagId);
        int affectedRows = preparedStatement.executeUpdate();
        if(affectedRows==0){
            throw new BaggageNotFoundException("Baggage with claimId " + claimTagId + " not found.");
        }
        else {
            System.out.println("Baggage Status Updates Successfully with a given claimId "+claimTagId);
        }

    }
    @Override
    public String getBaggageLocation(String claimTagId) throws SQLException {
        connection  = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  location FROM baggagetracking.baggage where claimid = ?");
        preparedStatement.setString(1,claimTagId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String location = resultSet.getString("location");
            return "location : "+location+" With claimId :"+claimTagId;
        }
		throw new BaggageNotFoundException("Baggage not found with given claimId"+claimTagId);
     }
    @Override
    public void updateBaggageLocation(String claimTagId, String location) throws SQLException {
        connection = BaggageUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE baggagetracking.baggage SET location = ? WHERE claimid  = ?");
        preparedStatement.setString(1, location);
        preparedStatement.setString(2, claimTagId);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new BaggageNotFoundException("Baggage with claimId " + claimTagId + " not found.");
        }
        else{
            System.out.println("Baggage Location Changed Successfully");
        }
    }
    @Override
    public void claimBaggage(String claimTagId) throws SQLException {
		//write the code to claim baggage
        connection = BaggageUtil.getConnection();
        String query ="DELETE FROM baggagetracking.baggage where claimid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,claimTagId);
        int affectedRows = preparedStatement.executeUpdate();
        if(affectedRows==0){
            throw new BaggageNotFoundException("Baggage not found with a given claimTagId ");
        }

	}
    @Override
    public List<Baggage> getAllCheckedInBaggage() throws SQLException {
    	//write the code to get all checked-in baggage
        connection = BaggageUtil.getConnection();
        List<Baggage> baggage = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM baggagetracking.baggage where status = 'Checked in'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            String claimId = resultSet.getString("claimid");
            String location = resultSet.getString("location");
            String status = resultSet.getString("status");
            String userid = resultSet.getString("userid");
            baggage.add(new Baggage(claimId,location,status, userid));
        }
            return baggage;
    }
}

