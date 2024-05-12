package com.gl.app;

import static org.junit.jupiter.api.Assertions.*;

import com.gl.app.dao.BaggageDAO;
import com.gl.app.dao.impl.BaggageDAOImpl;
import com.gl.app.exception.BaggageNotFoundException;
import org.junit.jupiter.api.Test;

import com.gl.app.entity.Baggage;
import com.gl.app.service.BaggageService;
import com.gl.app.service.UserService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.service.impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

public class BaggageServiceTest {
  @Mock
  BaggageDAO baggageDAOImpl;
  @InjectMocks
  BaggageService baggageService;

  //write the code for the intialization of the object
  @BeforeEach
  public void setUp() {
    baggageDAOImpl = mock(BaggageDAOImpl.class);
    baggageService = new BaggageServiceImpl((BaggageDAOImpl) baggageDAOImpl);
  }





  @Test
  public void testGetBaggageStatus() throws SQLException, ClassNotFoundException {
    //Write your code here
    String claimID = "101";
    when(baggageDAOImpl.getBaggageStatus(claimID)).thenReturn("");
    String e = baggageService.getBaggageStatus("101");
    assertEquals("In_transit", e);
  }
}