package com.Ridoh.Transaction.Util;

import java.util.Random;

public class ResponseUtil {
    public static final String USER_EXIST_CODE="001";
    public static final String USER_EXIST_MESSAGE="User provided email already exist";
    public static final int LENGTH_OF_ACCOUNT_NUMBER= 10;
    public static final String USER_SUCCESS_CODE="002";
    public static final String USER_SUCCESS_MESSAGE="User successfully created";
    public static final String ACCOUNT_CREDITED_MESSAGE="Account successfully credited";
    public static final String ACCOUNT_DEBITED_MESSAGE="Account successfully debited";
    public static final String USER_ID_NOT_FOUND_CODE="003";
    public static final String USERID_NOT_FOUND_MESSAGE="User id does not exist";
    public static final String USER_UNSUCCESSFUL_TRANSACTION_MESSAGE="Insufficient balance";
    public static final String USER_UNSUCCESSFUL_TRANSACTION_CODE="004";
    public static final String USER_SUCCESSFUL_TRANSFER_MESSAGE ="Transfer successfully done";
    public static final String USER_SUCCESSFUL_TRANSFER_CODE ="005";
    public static String generateAccountNumber(int length){
        String accountNumber="";
        int x;
        char[] stringChars=new char[length];

        for (int i = 0; i < length; i++) {
            Random random= new Random();
            x= random.nextInt(9);
            stringChars[i]=Integer.toString(x).toCharArray()[0];
        }
        accountNumber=new String(stringChars);
        return accountNumber.trim();

    }
}
