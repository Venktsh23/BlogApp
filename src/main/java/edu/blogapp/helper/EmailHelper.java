package edu.blogapp.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;



public class EmailHelper {


    public static String getEmailOfLoggedInUser(Authentication authentication){


        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            // Check if the principal is an instance of UserDetails
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // Assuming the email is used as the username
                System.out.println(userDetails.getUsername());
                return userDetails.getUsername();
            }
        }
        return null; 
    }

}
