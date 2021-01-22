package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

 public static final String  LOGIN_PAGE_TITLE = "Account Login";
 public static final String  ACCOUNT_PAGE_TITLE = "My Account";
 public static final String  ACCOUNT_PAGE_HEADER = "Your Store";
 public static final int  ACCOUNT_SECTION_COUNT = 4;
 public static final String ACCOUNT_SUCCESS_MSG = "Your Account Has Been Created!";
 public static final String REGISTER_SHEET_NAME = "Register";

 public static List<String> getAccSecList(){
   List<String> accList = new ArrayList<>();
   accList.add("My Account");
   accList.add("My Orders");
   accList.add("My Affiliate Account");
   accList.add("Newsletter");
  return accList;
 }

}
