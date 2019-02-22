package com.sapient.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sapient.constant.TransactionType;

/*
 * 
 */
public class Utils {

	
	public static Date parseDate(String date) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date convertedCurrentDate = sdf.parse(date);
			return convertedCurrentDate;
		}catch(Exception  ex){
			return null;
		}
	}
	
	public static Integer parseTransactionType(String type) {
		if(type.equals("BUY")){
			return TransactionType.BUY.getType();
		}if(type.equals("SELL")){
			return TransactionType.SELL.getType();
		}if(type.equals("DEPOSIT")){
			return TransactionType.DEPOSIT.getType();
		}if(type.equals("WITHDRAW")){
			return TransactionType.WITHDRAW.getType();
		}
		return null;}
	
	public static String getTypeName(Integer transactionType) {
		if(transactionType==TransactionType.BUY.getType()){
			return TransactionType.BUY.getName()+"\t";
		} else if(transactionType==TransactionType.SELL.getType()){
			return TransactionType.SELL.getName()+"\t";
		} else if(transactionType==TransactionType.DEPOSIT.getType()){
			return TransactionType.DEPOSIT.getName();
		} else if(transactionType==TransactionType.WITHDRAW.getType()){
			return TransactionType.WITHDRAW.getName();
		}
		return null;
	}
	
	public static Double parseMarketValue(String marketValue) {
		try{
			return Double.parseDouble(marketValue);
		}catch(Exception ex){
			return (double) 0;
		}
	}

	public static Boolean getPriority(String priority) {
		if(priority!= null){
			priority = priority.trim();
			if(priority.equals("Y")||priority.equals("y")){
				return true;
			} else {
				return false;
			}
		}else{
			return false;
		}
	}
}
