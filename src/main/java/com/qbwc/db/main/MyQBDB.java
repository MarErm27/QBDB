package com.qbwc.db.main;

import com.qbwc.db.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;


public class MyQBDB implements QBDB {

    public void addOrder(int order, String orderNumber, String json, Configuration con) throws DbException {
        OrdersEntity orderObj = new OrdersEntity();
        orderObj.setOrderId(order);
        orderObj.setOrderNumber(orderNumber);
        orderObj.setOrderData(json);
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(OrdersEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(orderObj);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addOrder error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public void addOrderError(int order, String error, Configuration con) throws DbException {
        OrderErrorEntity orderErrorObj = new OrderErrorEntity();
        orderErrorObj.setOrderId(order);
        orderErrorObj.setErr(error);
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(OrderErrorEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(orderErrorObj);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addOrderError error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public void addInvoice(int order, int invoice, Configuration con) throws DbException {
        InvoicesEntity invoiceObj = new InvoicesEntity();
        invoiceObj.setOrderId(order);
        invoiceObj.setInvoiceId(invoice);
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(InvoicesEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(invoiceObj);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addInvoice error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public boolean hasInvoice(int invoice, Configuration con) throws DbException {
        con = con.addAnnotatedClass(InvoicesEntity.class);
        SessionFactory sf = null;
        Session session = null;
        boolean invoiceFound = false;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            Query q = session.createQuery("select case when count(*)>0 then true else false end from InvoicesEntity invoices where invoiceId = :invoice", Boolean.class).setParameter("invoice", invoice);
            invoiceFound = (Boolean) q.uniqueResult();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("hasInvoice error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return invoiceFound;
    }



    public void addBill(int bill, String billNumber, String json, Configuration con) throws DbException {
        BillEntity billObj = new BillEntity();
        billObj.setBillId(bill);
        billObj.setBillNumber(billNumber);
        billObj.setBillData(json);
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(BillEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(billObj);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addBill error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public void addBillError(int bill, String error, Configuration con) throws DbException {
        BillErrorEntity billErrorObj = new BillErrorEntity();
        billErrorObj.setBillId(bill);
        billErrorObj.setErr(error);
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(BillErrorEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(billErrorObj);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addBillError error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public void addReference(int bill, int reference, Configuration con) throws DbException {
        BillReferencesEntity billReferencesObj = new BillReferencesEntity();
        billReferencesObj.setBillId(bill);
        billReferencesObj.setRefId(reference);
        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(BillReferencesEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(billReferencesObj);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addReference error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public boolean hasReference(int reference, Configuration con) throws DbException {
        con = con.addAnnotatedClass(BillReferencesEntity.class);
        SessionFactory sf = null;
        Session session = null;
        boolean referenceFound = false;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            Query q = session.createQuery("select case when count(*)>0 then true else false end from BillReferencesEntity references where refId = :reference", Boolean.class).setParameter("reference", reference);
            referenceFound = (Boolean) q.uniqueResult();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("hasReference error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return referenceFound;
    }

    public void putTermsRef(String refName, testXMLMarshalUnmarshal ref, Configuration con) throws DbException, JAXBException {
        TermsRefEntity termsRef = new TermsRefEntity();
        termsRef.setRefName(refName);

        String xml = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?> <?qbxml version=\"13.0\"?>");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(ref, boas);
            xml = boas.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("putTermsRef marshal error: ", e);
        }
        termsRef.setRef(xml);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(TermsRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(termsRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putTermsRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }


    public testXMLMarshalUnmarshal getTermsRef(String refName, Configuration con) throws DbException, JAXBException {
        TermsRefEntity termsRef;
        con = con.addAnnotatedClass(TermsRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            termsRef = session.find(TermsRefEntity.class, refName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getTermsRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }

        JAXBContext jaxbContext;
        testXMLMarshalUnmarshal testObject;
        try {
            jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(termsRef.getRef());
            testObject = (testXMLMarshalUnmarshal) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("getTermsRef unmarshal error: ", e);
        }
        return testObject;
    }

    public void putVendorRef(String vendorRefName, testXMLMarshalUnmarshal ref, Configuration con) throws DbException, JAXBException {
        VendorRefEntity vendorRef = new VendorRefEntity();
        vendorRef.setVendorRefName(vendorRefName);

        String xml = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?> <?qbxml version=\"13.0\"?>");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(ref, boas);
            xml = boas.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("putVendorRef marshal error: ", e);
        }
        vendorRef.setVendorRef(xml);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(VendorRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(vendorRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putVendorRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public testXMLMarshalUnmarshal getVendorRef(String vendorRefName, Configuration con) throws DbException, JAXBException {
        VendorRefEntity vendorRef;
        con = con.addAnnotatedClass(VendorRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            vendorRef = session.find(VendorRefEntity.class, vendorRefName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getVendorRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }

        JAXBContext jaxbContext;
        testXMLMarshalUnmarshal testObject;
        try {
            jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(vendorRef.getVendorRef());
            testObject = (testXMLMarshalUnmarshal) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("getVendorRef unmarshal error: ", e);
        }
        return testObject;
    }

    public void putCustomerRef(String customerRefName, testXMLMarshalUnmarshal ref, Configuration con) throws DbException, JAXBException {
        CustomerRefEntity customerRef = new CustomerRefEntity();
        customerRef.setCustomerRefName(customerRefName);

        String xml = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?> <?qbxml version=\"13.0\"?>");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(ref, boas);
            xml = boas.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("putCustomerRef marshal error: ", e);
        }
        customerRef.setCustomerRef(xml);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(CustomerRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(customerRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putCustomerRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }


    public testXMLMarshalUnmarshal getCustomerRef(String customerRefName, Configuration con) throws DbException, JAXBException {
        CustomerRefEntity customerRef;
        con = con.addAnnotatedClass(TermsRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            customerRef = session.find(CustomerRefEntity.class, customerRefName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getCustomerRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }

        JAXBContext jaxbContext;
        testXMLMarshalUnmarshal testObject;
        try {
            jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(customerRef.getCustomerRef());
            testObject = (testXMLMarshalUnmarshal) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("getCustomerRef unmarshal error: ", e);
        }
        return testObject;
    }
    
    public void putItemRef(String itemRefName, testXMLMarshalUnmarshal ref, Configuration con) throws DbException, JAXBException {
        ItemRefEntity itemRef = new ItemRefEntity();
        itemRef.setItemRefName(itemRefName);

        String xml = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?> <?qbxml version=\"13.0\"?>");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(ref, boas);
            xml = boas.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("putTermsRef marshal error: ", e);
        }
        itemRef.setItemRef(xml);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(ItemRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(itemRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putTermsRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }


    public testXMLMarshalUnmarshal getItemRef(String itemRefName, Configuration con) throws DbException, JAXBException {
        ItemRefEntity itemRef;
        con = con.addAnnotatedClass(ItemRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            itemRef = session.find(ItemRefEntity.class, itemRefName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getItemRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }

        JAXBContext jaxbContext;
        testXMLMarshalUnmarshal testObject;
        try {
            jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(itemRef.getItemRef());
            testObject = (testXMLMarshalUnmarshal) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("getItemRef unmarshal error: ", e);
        }
        return testObject;
    }

    public void putInventoryRet(String inventoryRetName, testXMLMarshalUnmarshal ref, Configuration con) throws DbException, JAXBException {
        InventoryRetEntity inventoryRet = new InventoryRetEntity();
        inventoryRet.setInventoryRetName(inventoryRetName);

        String xml = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\"?> <?qbxml version=\"13.0\"?>");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(ref, boas);
            xml = boas.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("putInventoryRet marshal error: ", e);
        }
        inventoryRet.setInventoryRet(xml);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(InventoryRetEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(inventoryRet);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putInventoryRet error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public testXMLMarshalUnmarshal getInventoryRet(String inventoryRetName, Configuration con) throws DbException, JAXBException {
        InventoryRetEntity inventoryRet;
        con = con.addAnnotatedClass(InventoryRetEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            inventoryRet = session.find(InventoryRetEntity.class, inventoryRetName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getInventoryRet error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }

        JAXBContext jaxbContext;
        testXMLMarshalUnmarshal testObject;
        try {
            jaxbContext = JAXBContext.newInstance(testXMLMarshalUnmarshal.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(inventoryRet.getInventoryRet());
            testObject = (testXMLMarshalUnmarshal) jaxbUnmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            throw new JAXBException("getInventoryRet unmarshal error: ", e);
        }
        return testObject;
    }

    public void addInventoryAdjustments(String inventoryAdjustment, Configuration con) throws DbException {
        InventoryAdjustmentsEntity inventoryAdjustments = new InventoryAdjustmentsEntity();
        inventoryAdjustments.setInventoryAdjustments(inventoryAdjustment);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(InventoryAdjustmentsEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(inventoryAdjustments);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("addInventoryAdjustments error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public boolean hasInventoryAdjustments(String inventoryAdjustment, Configuration con) throws DbException {
        con = con.addAnnotatedClass(InventoryAdjustmentsEntity.class);
        SessionFactory sf = null;
        Session session = null;
        boolean inventoryAdjustmentsFound = false;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            Query q = session.createQuery("select case when count(*)>0 then true else false end from InventoryAdjustmentsEntity inventoryAdjustment where inventoryAdjustments = :inventoryAdjustment", Boolean.class).setParameter("inventoryAdjustment", inventoryAdjustment);
            inventoryAdjustmentsFound = (Boolean) q.uniqueResult();
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("hasInventoryAdjustments error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return inventoryAdjustmentsFound;
    }

    public void putRefNumber(String poNumber, String ref, Configuration con) throws DbException {
        RefNumberEntity refNumber = new RefNumberEntity();
        refNumber.setPoNumber(poNumber);
        refNumber.setRefNumber(ref);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(RefNumberEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(refNumber);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putRefNumber error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public String getRefNumber(String poNumber, Configuration con) throws DbException {
        RefNumberEntity refNumber;
        con = con.addAnnotatedClass(RefNumberEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            refNumber = session.find(RefNumberEntity.class, poNumber);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getRefNumber error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return refNumber.getRefNumber();
    }

    public void putAccountRef(String fullName, String listId, Configuration con) throws DbException {
        AccountRefEntity accountRef = new AccountRefEntity();
        accountRef.setFullName(fullName);
        accountRef.setListId(listId);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(AccountRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(accountRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putAccountRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public String getAccountRef(String fullName, Configuration con) throws DbException {
        AccountRefEntity accountRef;
        con = con.addAnnotatedClass(AccountRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            accountRef = session.find(AccountRefEntity.class, fullName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getAccountRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return accountRef.getListId();
    }

    public void putCustomerSalesTaxCodeRef(String customerName, String listId, Configuration con) throws DbException {
        CustomerSalesTaxCodeRefEntity customerSalesTaxCodeRef = new CustomerSalesTaxCodeRefEntity();
        customerSalesTaxCodeRef.setCustomerName(customerName);
        customerSalesTaxCodeRef.setListId(listId);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(CustomerSalesTaxCodeRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(customerSalesTaxCodeRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putCustomerSalesTaxCodeRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public String getCustomerSalesTaxCodeRef(String customerName, Configuration con) throws DbException {
        CustomerSalesTaxCodeRefEntity customerSalesTaxCodeRef;
        con = con.addAnnotatedClass(CustomerSalesTaxCodeRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            customerSalesTaxCodeRef = session.find(CustomerSalesTaxCodeRefEntity.class, customerName);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getCustomerSalesTaxCodeRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return customerSalesTaxCodeRef.getListId();
    }

    public void putSalesReceipt(String memo, String refNumber, Configuration con) throws DbException {
        SalesReceiptEntity salesReceipt = new SalesReceiptEntity();
        salesReceipt.setMemo(memo);
        salesReceipt.setRefNumber(refNumber);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(SalesReceiptEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(salesReceipt);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putsalesReceipt error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public String getSalesReceipt(String memo, Configuration con) throws DbException {
        SalesReceiptEntity salesReceipt;
        con = con.addAnnotatedClass(SalesReceiptEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            salesReceipt = session.find(SalesReceiptEntity.class, memo);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getsalesReceipt error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return salesReceipt.getRefNumber();
    }

    public void putBillRef(String poNumber, String refNumber, Configuration con) throws DbException {
        BillRefEntity billRef = new BillRefEntity();
        billRef.setPoNumber(poNumber);
        billRef.setRefNumber(refNumber);

        SessionFactory sf = null;
        Session session = null;
        Transaction tx = null;
        try {
            con = con.addAnnotatedClass(BillRefEntity.class);
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(billRef);
            tx.commit();
        } catch (HibernateException hibernateEx) {
            try {
                tx.rollback();
            } catch (RuntimeException runtimeEx) {
                System.err.printf("Couldn't Roll Back Transaction", runtimeEx);
            }
            hibernateEx.printStackTrace();
            throw new DbException("putBillRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
    }

    public String getBillRef(String poNumber, Configuration con) throws DbException {
        BillRefEntity billRef;
        con = con.addAnnotatedClass(BillRefEntity.class);
        SessionFactory sf = null;
        Session session = null;
        try {
            ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
            sf = con.buildSessionFactory(reg);
            session = sf.openSession();
            billRef = session.find(BillRefEntity.class, poNumber);
        } catch (HibernateException hibernateEx) {
            hibernateEx.printStackTrace();
            throw new DbException("getBillRef error: ", hibernateEx);
        } finally {
            if (session != null) {
                session.close();
            }
            if (sf != null) {
                sf.close();
            }
        }
        return billRef.getRefNumber();
    }
}
