package com.gl.app.client;
import com.gl.app.service.UserService;
import com.gl.app.service.impl.UserServiceImpl;
import com.gl.app.service.BaggageService;
import com.gl.app.service.impl.BaggageServiceImpl;
import com.gl.app.entity.User;
import com.gl.app.exception.BaggageNotFoundException;
import com.gl.app.entity.Baggage;
import com.gl.app.util.BaggageUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaggageTrackingApplication {
	  private static final Pattern EMAIL_PATTERN = null;
	  

    public static  void main(String[] args) throws SQLException {
    
    	
        UserService userService = new UserServiceImpl();
        BaggageService baggageService = new BaggageServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("1. Register User");
            System.out.println("2. Check-in Baggage");
            System.out.println("3. Get Baggage Info");
            System.out.println("4. Get Baggage Status");
            System.out.println("5. Get All Checked-in Baggage");
            System.out.println("6. Update Baggage Status");
            System.out.println("7. Update Baggage Location");
            System.out.println("8. Claim Baggage");
            System.out.println("9. Get Baggage Location");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
				//write the code to register user
                    System.out.println("Enter first name : ");
                    String firstName = scanner.next();
                    System.out.println("Enter last name");
                    String lastName = scanner.next();
                    while(true){
                        System.out.println("Enter emailId");
                        String emailId = scanner.next();
                        if(BaggageTrackingApplication.validateEmail(emailId)){
                            String userId = String.valueOf(BaggageUtil.generateUniqueId());
                          userService.registerNewUser(new User(userId,firstName,lastName,emailId));
                            System.out.println("User Created Successfully");
                            break;
                        }
                        else{
                            System.out.println("Invalid email id. Please enter a valid email id.");
                        }
                    }


                  break;
                case 2:
                		// Check-in Baggage
                	//write the code to check-in baggage
                    System.out.println("Enter UserId");
                    String userId = scanner.next();
                    String claimId = String.valueOf(BaggageUtil.generateUniqueId());
                    userService.checkInBaggage(new Baggage(claimId,"Check-in Area","Checked in",userId));
                    System.out.println("Baggage Checked in Successfully");
                case 3:
                    // Get Baggage Status
                	//write the code to get baggage status
                    System.out.println("Claim tag id:");
                    String claimTagId = scanner.next();
                    Baggage baggage = userService.getBaggageInfo(claimTagId);
                    System.out.println("claimId : "+baggage.getClaimId());
                    System.out.println("Locaton : "+baggage.getLocation());
                    System.out.println("Status : "+baggage.getStatus());

                   break;
                case 4:
                    // Get Baggage Status
                	//write the code to get baggage status
                    System.out.println("Claim tag id:");
                    String claimTagid = scanner.next();
                    System.out.println(baggageService.getBaggageStatus(claimTagid));

                    break;
                case 5:
                    // Get All Checked-in Baggage
                	//write the code to get all checked-in baggag

                    List<Baggage> list = baggageService.getAllCheckedInBaggage();
                  // list.forEach(object-> System.out.println("_______________________________________________________________________________\n"+"claimId : "+object.getClaimId()+"\n"+"Location : "+object.getLocation()+"\n"+"Status : "+object.getStatus()+"\n"+"UserId : "+object.getUserId()+"\n"+"__________________________________________________________________\n"));
                    Consumer<Baggage> consumer = baggage1 -> {
                        System.out.println("______________________________________________________________________");
                        System.out.println("claimId : "+baggage1.getClaimId());
                        System.out.println("Location : "+baggage1.getLocation());
                        System.out.println("Status : "+baggage1.getStatus());
                        System.out.println("UserId : "+baggage1.getUserId());
                        System.out.println("______________________________________________________________________");
                    };
                    list.forEach(consumer);

                    break;
                case 6:
                    // Write your code to Update Baggage Status
                    System.out.println("Enter claimTagId :");
                    String claimTag = scanner.next();
                    System.out.println("Enter status : ");
                    String status = scanner.next();
                    baggageService.updateBaggageStatus(claimTag,status);
                	
                    break;
                case 7:
                    // update Baggage Location
                	//write the code to update baggage location
                    System.out.println("Enter claimTagId : ");
                    String claimIdtag = scanner.next();
                    System.out.println("Enter Location: ");
                    String location = scanner.next();
                    baggageService.updateBaggageLocation(claimIdtag,location);
                	   break;
                case 8:
					// Claim Baggage
					//write the code to claim baggage
                    System.out.println("Enter claimTagId : ");
                    String claimIdTag = scanner.next();
                    baggageService.claimBaggage(claimIdTag);
                    System.out.println("Baggage claimed Successfully");
					break;
			case 9:
				// Get Baggage Location
			//write the code to get baggage location
                System.out.println("Enter claimTagId : ");
                String idClaimId = scanner.next();
                System.out.println(baggageService.getBaggageLocation(idClaimId));
					break;
					
               case 10:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        }
    }
    private static boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);


        return matcher.matches();
    }

}