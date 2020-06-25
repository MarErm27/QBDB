package com.qbwc.db.main;

import org.hibernate.cfg.Configuration;

/**
 * Interface to work with DB storing processed data.
 * 
 * DB:
 * 
 * Tables:
 * 
 * orders
 *   int order
 *   String[128] orderNumber
 *   String[4096] orderData
 *   
 * invoices
 *   int order
 *   int invoice
 *   
 * orderErrors
 *   int order
 *   String[4096] error
 *
 * bills
 *   int bill
 *   String[128] billNumber
 *   String[4096] billData
 *
 * billReferences
 *   int bill
 *   int reference
 *
 * billErrors
 *   int bill
 *   String[4096] error
 * 
 */
public interface QBDB {
  public static class DbException extends Exception {
    public DbException(String error_text, Throwable reason) {
      super(reason);
    }
  }

  boolean hasInvoice(int order, Configuration con) throws DbException;

  void addOrder(int order, String orderNumber, String json, Configuration con) throws DbException;

  void addInvoice(int order, int invoice, Configuration con) throws DbException;

  void addOrderError(int order, String error, Configuration con) throws DbException;

  boolean hasReference(int bill, Configuration con) throws DbException;

  void addBill(int bill, String billNumber, String json, Configuration con) throws DbException;

  void addReference(int bill, int reference, Configuration con) throws DbException;

  void addBillError(int bill, String error, Configuration con) throws DbException;

}
