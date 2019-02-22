package com.sapient.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sapient.constant.Fee;
import com.sapient.constant.TransactionType;
import com.sapient.pojo.Transaction;
import com.sapient.util.Utils;

public abstract class Reader {

	List<Transaction> transactions;

	public abstract void readTransaction(File transactionFile);

	public void saveTransaction(Transaction transaction) {
		if (transactions == null) {
			transactions = new ArrayList<>();
		}
		transactions.add(calculateFeeBasedOnProcessingRule(transaction));
	}

	public Transaction setTransaction(String[] attributes) {
		Transaction transaction = new Transaction();
		transaction.setExternalTransactionID(attributes[0]);
		transaction.setClientId(attributes[1]);
		transaction.setSecurityId(attributes[2]);
		transaction.setTransactionType(Utils.parseTransactionType(attributes[3]));
		transaction.setTransactionDate(Utils.parseDate(attributes[4]));
		transaction.setMarketValue(Utils.parseMarketValue(attributes[5]));
		transaction.setPriority(Utils.getPriority(attributes[6]));
		return transaction;
	}

	private Transaction calculateFeeBasedOnProcessingRule(Transaction transaction) {
		if (isIntraday(transaction)) {
			transaction.setTransactionFee(Fee.TEN.getFee());
		} else {

			if (transaction.getPriority()) {
				transaction.setTransactionFee(Fee.FIVE_HUNDREAD.getFee());

			} else {
				if (transaction.getTransactionType() == TransactionType.SELL.getType()
						|| transaction.getTransactionType() == TransactionType.WITHDRAW.getType()) {

					transaction.setTransactionFee(Fee.HUNDREAD.getFee());

				} else if (transaction.getTransactionType() == TransactionType.BUY.getType()
						|| transaction.getTransactionType() == TransactionType.DEPOSIT.getType()) {

					transaction.setTransactionFee(Fee.FIFTY.getFee());
				}

			}

		}
		return transaction;
	}

	/*
	 * 
	 */
	private boolean isIntraday(Transaction transaction) {
		boolean isIntraDayTransaction = false;
		Transaction temp = null;
		if (transactions.size() > 0) {
			for (Transaction trans : transactions) {
				if (trans.getClientId().equals(transaction.getClientId())
						&& trans.getSecurityId().equals(transaction.getSecurityId())
						&& trans.getTransactionDate().equals(transaction.getTransactionDate())) {
					if ((trans.getTransactionType() == TransactionType.BUY.getType()
							&& transaction.getTransactionType() == TransactionType.SELL.getType())
							|| (trans.getTransactionType() == TransactionType.SELL.getType()
									&& transaction.getTransactionType() == TransactionType.BUY.getType())) {
						isIntraDayTransaction = true;
						temp = trans;
						break;
					}
				}

			}

			if (temp != null) {
				transactions.remove(temp);
				temp.setTransactionFee(Fee.TEN.getFee());
				transactions.add(temp);
			}

		} else {
			isIntraDayTransaction = false;
		}

		return isIntraDayTransaction;
	}

	public void displayReport() {
		Collections.sort(transactions, new Transaction());
		System.out.println("Client Id | Transaction Type | Transaction Date | Priority | Processing Fee    |");
		for (Transaction transaction : transactions) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(transaction.getClientId() + "\t| " + Utils.getTypeName(transaction.getTransactionType())
					+ "\t| " + transaction.getTransactionDate() + "\t| "
					+ (transaction.getPriority() ? "HIGH\t" : "NORMAL") + "\t| " + transaction.getTransactionFee()
					+ "\t|");
		}
		System.out.println("--------------------------------------------------------------------------------");
	}

}
