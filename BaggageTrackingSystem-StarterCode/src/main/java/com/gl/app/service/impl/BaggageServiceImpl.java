package com.gl.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.gl.app.entity.Baggage;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.service.BaggageService;
import com.gl.app.dao.BaggageDAO;
import com.gl.app.dao.impl.BaggageDAOImpl;
import com.gl.app.util.BaggageUtil;

public class BaggageServiceImpl implements BaggageService{
	
	BaggageDAO baggageDAO = new BaggageDAOImpl();
	public BaggageServiceImpl() {

	}

	public BaggageServiceImpl(BaggageDAOImpl baggageDAO) {

	}

	public BaggageServiceImpl(Baggage baggage) {

	}

	@Override
	public String getBaggageStatus(String claimTagId) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to get baggage status
		return baggageDAO.getBaggageStatus(claimTagId);

	}

	@Override
	public void updateBaggageStatus(String claimTagId, String status) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to update baggage status

		baggageDAO.updateBaggageStatus(claimTagId,status);
	}

	@Override
	public String getBaggageLocation(String claimTagId) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to get baggage location

		return baggageDAO.getBaggageLocation(claimTagId);
	}

	@Override
	public void updateBaggageLocation(String claimTagId, String location) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to update baggage location;
		baggageDAO.updateBaggageLocation(claimTagId,location);

	}

	@Override
	public void claimBaggage(String claimTagId) throws SQLException {
		// TODO Auto-generated method stub
		//write the code to claim baggage
        baggageDAO.claimBaggage(claimTagId);


    }

	@Override
	public List<Baggage> getAllCheckedInBaggage() throws SQLException {
		// TODO Auto-generated method stub
		return baggageDAO.getAllCheckedInBaggage();
	}
	

}
