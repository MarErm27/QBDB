package com.qbwc.db.main;

import com.qbwc.db.entities.*;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyQBDBTest {
    private static Configuration con;
    private static MyQBDB qbdb;

    @BeforeAll
    static void setUpBeforeClass() throws Exception{
        // Create the Flyway instance and point it to the database
        Flyway flyway =
                Flyway.configure()
                        .dataSource( "jdbc:mysql://localhost:3307/qb_test?createDatabaseIfNotExist=true&serverTimezone=UTC" , "root" , "MaratErmeshev27" )  // (url, user, password)
                        .load()                                                            // Returns a `Flyway` object.
                ;
        // Start the migration
        flyway.migrate();

        con = new Configuration().configure("testHibernate.cfg.xml");
        qbdb = new MyQBDB();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception{
        con = con.addAnnotatedClass(BillEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();
        Query q = session.createSQLQuery("DROP DATABASE qb_test");
        q.executeUpdate();

        session.getTransaction().commit();

        session.close();
        sf.close();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void addOrder1() throws QBDB.DbException {
        System.out.println("addOrder1");

        int order = 1234;
        String orderNumber = "220000\\/00000000000000";
        String json = "[{\"items\":[{\"type\":null,\"name\":\"Tissot:T099.407.11.048.00\",\"quantity\":\"1\",\"serial\":null,\"amount\":\"495.00\",\"tax\":null,\"fee\":null},{\"type\":null,\"name\":\"PayPal\",\"quantity\":1,\"serial\":null,\"amount\":\"-9.71\",\"tax\":null,\"fee\":null}],\"date\":\"2020-06-11 11:43:50\",\"billTo\":null,\"shipTo\":{\"address1\":\"2204 59th Ave NW\",\"address2\":null,\"city\":\"Olympia\",\"state\":\"WA\",\"country\":\"US\",\"zipcode\":\"98502-3412\",\"firstname\":\"Daniel\",\"lastname\":\"Scolaro\",\"name\":\"Daniel Scolaro\"},\"type\":\"invoice\",\"invoiceSource\":\"Ebay\",\"orderNumber\":\"220663\\/0HD1353598116225W\",\"terms\":\"PayPal\",\"shipdate\":\"2020-06-11 12:03:20\",\"shipVia\":\"FedEx Pak\",\"email\":\"danscolaro@yahoo.com\",\"salesTax\":\"0.00\",\"total\":\"495.00\",\"fee\":\"9.71\",\"last4digits\":null,\"qbtotal\":\"485.29\",\"subTotal\":\"495.00\",\"shipping\":\"0.00\"}";
        qbdb.addOrder(order, orderNumber, json,con);

        con = con.addAnnotatedClass(OrdersEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        OrdersEntity orderObj = new OrdersEntity();
        orderObj.setOrderId(order);
        orderObj.setOrderNumber(orderNumber);
        orderObj.setOrderData(json);

        OrdersEntity orderObjFromDB = session.find(OrdersEntity.class,order);

        session.close();
        sf.close();

        Assertions.assertNotNull(orderObjFromDB.getCreatedAt());
        Assertions.assertNotNull(orderObjFromDB.getUpdatedAt());

        orderObj.setCreatedAt(orderObjFromDB.getCreatedAt());
        orderObj.setUpdatedAt(orderObjFromDB.getUpdatedAt());

        Assertions.assertEquals(orderObj,orderObjFromDB);

    }
    @Test
    @Order(2)
    void addOrder2() throws QBDB.DbException {
        System.out.println("addOrder2");

        int order = 2134;
        String orderNumber = "220000\\/00000000000000";
        String json = "[{\"items\":[{\"type\":null,\"name\":\"Tissot:T099.407.11.048.00\",\"quantity\":\"1\",\"serial\":null,\"amount\":\"495.00\",\"tax\":null,\"fee\":null},{\"type\":null,\"name\":\"PayPal\",\"quantity\":1,\"serial\":null,\"amount\":\"-9.71\",\"tax\":null,\"fee\":null}],\"date\":\"2020-06-11 11:43:50\",\"billTo\":null,\"shipTo\":{\"address1\":\"2204 59th Ave NW\",\"address2\":null,\"city\":\"Olympia\",\"state\":\"WA\",\"country\":\"US\",\"zipcode\":\"98502-3412\",\"firstname\":\"Daniel\",\"lastname\":\"Scolaro\",\"name\":\"Daniel Scolaro\"},\"type\":\"invoice\",\"invoiceSource\":\"Ebay\",\"orderNumber\":\"220663\\/0HD1353598116225W\",\"terms\":\"PayPal\",\"shipdate\":\"2020-06-11 12:03:20\",\"shipVia\":\"FedEx Pak\",\"email\":\"danscolaro@yahoo.com\",\"salesTax\":\"0.00\",\"total\":\"495.00\",\"fee\":\"9.71\",\"last4digits\":null,\"qbtotal\":\"485.29\",\"subTotal\":\"495.00\",\"shipping\":\"0.00\"}";
        qbdb.addOrder(order, orderNumber, json,con);

        con = con.addAnnotatedClass(OrdersEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        OrdersEntity orderObj = new OrdersEntity();
        orderObj.setOrderId(order);
        orderObj.setOrderNumber(orderNumber);
        orderObj.setOrderData(json);

        OrdersEntity orderObjFromDB = session.find(OrdersEntity.class,order);

        session.close();
        sf.close();

        Assertions.assertNotNull(orderObjFromDB.getCreatedAt());
        Assertions.assertNotNull(orderObjFromDB.getUpdatedAt());

        orderObj.setCreatedAt(orderObjFromDB.getCreatedAt());
        orderObj.setUpdatedAt(orderObjFromDB.getUpdatedAt());

        Assertions.assertEquals(orderObj,orderObjFromDB);

    }
    @Test
    @Order(3)
    void updateOrder() throws QBDB.DbException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("updateOrder");

        int order = 1234;
        String orderNumber = "test00\\/00000000000000";
        String json = "[{\"items\":[{\"type\":null,\"name\":\"Tissot:T099.407.11.048.00\",\"quantity\":\"1\",\"serial\":null,\"amount\":\"495.00\",\"tax\":null,\"fee\":null},{\"type\":null,\"name\":\"PayPal\",\"quantity\":1,\"serial\":null,\"amount\":\"-9.71\",\"tax\":null,\"fee\":null}],\"date\":\"2020-06-11 11:43:50\",\"billTo\":null,\"shipTo\":{\"address1\":\"2204 59th Ave NW\",\"address2\":null,\"city\":\"Olympia\",\"state\":\"WA\",\"country\":\"US\",\"zipcode\":\"98502-3412\",\"firstname\":\"Daniel\",\"lastname\":\"Scolaro\",\"name\":\"Daniel Scolaro\"},\"type\":\"invoice\",\"invoiceSource\":\"Ebay\",\"orderNumber\":\"220663\\/0HD1353598116225W\",\"terms\":\"PayPal\",\"shipdate\":\"2020-06-11 12:03:20\",\"shipVia\":\"FedEx Pak\",\"email\":\"danscolaro@yahoo.com\",\"salesTax\":\"0.00\",\"total\":\"495.00\",\"fee\":\"9.71\",\"last4digits\":null,\"qbtotal\":\"485.29\",\"subTotal\":\"495.00\",\"shipping\":\"0.00\"}";
        qbdb.addOrder(order, orderNumber, json,con);

        con = con.addAnnotatedClass(OrdersEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        OrdersEntity orderObj = new OrdersEntity();
        orderObj.setOrderId(order);
        orderObj.setOrderNumber(orderNumber);
        orderObj.setOrderData(json);

        OrdersEntity orderObjFromDB = session.find(OrdersEntity.class,order);

        session.close();
        sf.close();

        Assertions.assertNotNull(orderObjFromDB.getCreatedAt());
        Assertions.assertNotNull(orderObjFromDB.getUpdatedAt());
        Assertions.assertEquals(orderNumber,orderObjFromDB.getOrderNumber());
        Assertions.assertNotEquals(orderObjFromDB.getCreatedAt(),orderObj.getUpdatedAt());

        orderObj.setCreatedAt(orderObjFromDB.getCreatedAt());
        orderObj.setUpdatedAt(orderObjFromDB.getUpdatedAt());

        Assertions.assertEquals(orderObj,orderObjFromDB);
    }


    @Test
    @Order(4)
    void addOrderError() throws QBDB.DbException {
        System.out.println("addOrderError");
        int order = 2134;
        String err = "Unexpected error";

        con = con.addAnnotatedClass(OrderErrorEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        OrderErrorEntity orderErrObj = new OrderErrorEntity();
        orderErrObj.setOrderId(order);
        orderErrObj.setErr(err);

        Query q = session.createQuery("select count(*) from OrderErrorEntity OrderErrorEntity where OrderErrorEntity.orderId = :order");
        q.setParameter("order", order);
        long countOfElelments = (Long) q.uniqueResult();

        qbdb.addOrderError(order,err,con);

        Query q2 = session.createQuery("select count(*) from OrderErrorEntity OrderErrorEntity where OrderErrorEntity.orderId = :order");
        q2.setParameter("order", order);
        long newCountOfElelments = (Long) q.uniqueResult();
        Assertions.assertEquals(countOfElelments+1,newCountOfElelments);
        session.close();
        sf.close();
    }

    @Test
    @Order(5)
    void hasInvoiceFail() throws QBDB.DbException {
        System.out.println("hasInvoiceFail");

        int invoice = 4123;
        Boolean result = qbdb.hasInvoice(invoice,con);
        Assertions.assertEquals(false, result);
    }

    @Test
    @Order(6)
    void addInvoice() throws QBDB.DbException {
        System.out.println("addInvoice");
        int invoice = 4123;
        int order = 1234;

        qbdb.addInvoice(order, invoice, con);

        con = con.addAnnotatedClass(InvoicesEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        InvoicesEntity invoiceObj = new InvoicesEntity();
        invoiceObj.setOrderId(order);
        invoiceObj.setInvoiceId(invoice);

        InvoicesEntity invoiceObjFromDB = session.find(InvoicesEntity.class,invoice);

        session.close();
        sf.close();

        Assertions.assertNotNull(invoiceObjFromDB.getCreatedAt());
        Assertions.assertNotNull(invoiceObjFromDB.getUpdatedAt());

        invoiceObj.setCreatedAt(invoiceObjFromDB.getCreatedAt());
        invoiceObj.setUpdatedAt(invoiceObjFromDB.getUpdatedAt());

        Assertions.assertEquals(invoiceObj,invoiceObjFromDB);
    }

    @Test
    @Order(7)
    void hasInvoiceSuccess() throws QBDB.DbException {
        System.out.println("hasInvoiceSuccess");

        int invoice = 4123;
        Boolean result = qbdb.hasInvoice(invoice,con);
        Assertions.assertEquals(true, result);
    }

    @Test
    @Order(8)
    void updateInvoice() throws QBDB.DbException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("updateInvoice");
        int invoice = 4123;
        int order = 2134;
        qbdb.addInvoice(order, invoice,con);

        con = con.addAnnotatedClass(InvoicesEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        InvoicesEntity invoiceObj = new InvoicesEntity();
        invoiceObj.setOrderId(order);
        invoiceObj.setInvoiceId(invoice);

        InvoicesEntity invoiceObjFromDB = session.find(InvoicesEntity.class,invoice);

        session.close();
        sf.close();

        Assertions.assertNotNull(invoiceObjFromDB.getCreatedAt());
        Assertions.assertNotNull(invoiceObjFromDB.getUpdatedAt());
        Assertions.assertEquals(order,invoiceObjFromDB.getOrderId());
        Assertions.assertNotEquals(invoiceObjFromDB.getCreatedAt(),invoiceObjFromDB.getUpdatedAt());

        invoiceObj.setCreatedAt(invoiceObjFromDB.getCreatedAt());
        invoiceObj.setUpdatedAt(invoiceObjFromDB.getUpdatedAt());

        Assertions.assertEquals(invoiceObj,invoiceObjFromDB);
    }

    @Test
    @Order(9)
    void addBill1() throws QBDB.DbException {
        System.out.println("addBill1");
        int bill = 12341;
        String billNumber = "110000\\/00000000000000";
        String json = "[{\"items\":[{\"type\":null,\"name\":\"Tissot:T099.407.11.048.00\",\"quantity\":\"1\",\"serial\":null,\"amount\":\"495.00\",\"tax\":null,\"fee\":null},{\"type\":null,\"name\":\"PayPal\",\"quantity\":1,\"serial\":null,\"amount\":\"-9.71\",\"tax\":null,\"fee\":null}],\"date\":\"2020-06-11 11:43:50\",\"billTo\":null,\"shipTo\":{\"address1\":\"2204 59th Ave NW\",\"address2\":null,\"city\":\"Olympia\",\"state\":\"WA\",\"country\":\"US\",\"zipcode\":\"98502-3412\",\"firstname\":\"Daniel\",\"lastname\":\"Scolaro\",\"name\":\"Daniel Scolaro\"},\"type\":\"invoice\",\"invoiceSource\":\"Ebay\",\"orderNumber\":\"220663\\/0HD1353598116225W\",\"terms\":\"PayPal\",\"shipdate\":\"2020-06-11 12:03:20\",\"shipVia\":\"FedEx Pak\",\"email\":\"danscolaro@yahoo.com\",\"salesTax\":\"0.00\",\"total\":\"495.00\",\"fee\":\"9.71\",\"last4digits\":null,\"qbtotal\":\"485.29\",\"subTotal\":\"495.00\",\"shipping\":\"0.00\"}";
        qbdb.addBill(bill, billNumber, json,con);

        con = con.addAnnotatedClass(BillEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillEntity billObj = new BillEntity();
        billObj.setBillId(bill);
        billObj.setBillNumber(billNumber);
        billObj.setBillData(json);

        BillEntity billObjFromDB = session.find(BillEntity.class,bill);

        session.close();
        sf.close();

        Assertions.assertNotNull(billObjFromDB.getCreatedAt());
        Assertions.assertNotNull(billObjFromDB.getUpdatedAt());

        billObj.setCreatedAt(billObjFromDB.getCreatedAt());
        billObj.setUpdatedAt(billObjFromDB.getUpdatedAt());

        Assertions.assertEquals(billObj,billObjFromDB);
    }

    @Test
    @Order(10)
    void addBill2() throws QBDB.DbException {
        System.out.println("addBill2");
        int bill = 21345;
        String billNumber = "110000\\/00000000000000";
        String json = "[{\"items\":[{\"type\":null,\"name\":\"Tissot:T099.407.11.048.00\",\"quantity\":\"1\",\"serial\":null,\"amount\":\"495.00\",\"tax\":null,\"fee\":null},{\"type\":null,\"name\":\"PayPal\",\"quantity\":1,\"serial\":null,\"amount\":\"-9.71\",\"tax\":null,\"fee\":null}],\"date\":\"2020-06-11 11:43:50\",\"billTo\":null,\"shipTo\":{\"address1\":\"2204 59th Ave NW\",\"address2\":null,\"city\":\"Olympia\",\"state\":\"WA\",\"country\":\"US\",\"zipcode\":\"98502-3412\",\"firstname\":\"Daniel\",\"lastname\":\"Scolaro\",\"name\":\"Daniel Scolaro\"},\"type\":\"invoice\",\"invoiceSource\":\"Ebay\",\"orderNumber\":\"220663\\/0HD1353598116225W\",\"terms\":\"PayPal\",\"shipdate\":\"2020-06-11 12:03:20\",\"shipVia\":\"FedEx Pak\",\"email\":\"danscolaro@yahoo.com\",\"salesTax\":\"0.00\",\"total\":\"495.00\",\"fee\":\"9.71\",\"last4digits\":null,\"qbtotal\":\"485.29\",\"subTotal\":\"495.00\",\"shipping\":\"0.00\"}";
        qbdb.addBill(bill, billNumber, json,con);

        con = con.addAnnotatedClass(BillEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillEntity billObj = new BillEntity();
        billObj.setBillId(bill);
        billObj.setBillNumber(billNumber);
        billObj.setBillData(json);

        BillEntity billObjFromDB = session.find(BillEntity.class,bill);

        session.close();
        sf.close();

        Assertions.assertNotNull(billObjFromDB.getCreatedAt());
        Assertions.assertNotNull(billObjFromDB.getUpdatedAt());

        billObj.setCreatedAt(billObjFromDB.getCreatedAt());
        billObj.setUpdatedAt(billObjFromDB.getUpdatedAt());

        Assertions.assertEquals(billObj,billObjFromDB);
    }
    @Test
    @Order(11)
    void updateBill() throws QBDB.DbException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("updateBill");
        int bill = 12341;
        String billNumber = "test00\\/00000000000000";
        String json = "[{\"items\":[{\"type\":null,\"name\":\"Tissot:T099.407.11.048.00\",\"quantity\":\"1\",\"serial\":null,\"amount\":\"495.00\",\"tax\":null,\"fee\":null},{\"type\":null,\"name\":\"PayPal\",\"quantity\":1,\"serial\":null,\"amount\":\"-9.71\",\"tax\":null,\"fee\":null}],\"date\":\"2020-06-11 11:43:50\",\"billTo\":null,\"shipTo\":{\"address1\":\"2204 59th Ave NW\",\"address2\":null,\"city\":\"Olympia\",\"state\":\"WA\",\"country\":\"US\",\"zipcode\":\"98502-3412\",\"firstname\":\"Daniel\",\"lastname\":\"Scolaro\",\"name\":\"Daniel Scolaro\"},\"type\":\"invoice\",\"invoiceSource\":\"Ebay\",\"orderNumber\":\"220663\\/0HD1353598116225W\",\"terms\":\"PayPal\",\"shipdate\":\"2020-06-11 12:03:20\",\"shipVia\":\"FedEx Pak\",\"email\":\"danscolaro@yahoo.com\",\"salesTax\":\"0.00\",\"total\":\"495.00\",\"fee\":\"9.71\",\"last4digits\":null,\"qbtotal\":\"485.29\",\"subTotal\":\"495.00\",\"shipping\":\"0.00\"}";
        qbdb.addBill(bill, billNumber, json,con);

        con = con.addAnnotatedClass(BillEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillEntity billObj = new BillEntity();
        billObj.setBillId(bill);
        billObj.setBillNumber(billNumber);
        billObj.setBillData(json);

        BillEntity billObjFromDB = session.find(BillEntity.class,bill);

        session.close();
        sf.close();

        Assertions.assertNotNull(billObjFromDB.getCreatedAt());
        Assertions.assertNotNull(billObjFromDB.getUpdatedAt());
        Assertions.assertEquals(billNumber,billObjFromDB.getBillNumber());
        Assertions.assertNotEquals(billObjFromDB.getCreatedAt(),billObj.getUpdatedAt());

        billObj.setCreatedAt(billObjFromDB.getCreatedAt());
        billObj.setUpdatedAt(billObjFromDB.getUpdatedAt());

        Assertions.assertEquals(billObj,billObjFromDB);
    }

    @Test
    @Order(12)
    void addBillError() throws QBDB.DbException {
        System.out.println("addBillError");
        int bill = 12341;
        String err = "Unexpected error";

        con = con.addAnnotatedClass(BillErrorEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillErrorEntity billErrObj = new BillErrorEntity();
        billErrObj.setBillId(bill);
        billErrObj.setErr(err);

        Query q = session.createQuery("select count(*) from BillErrorEntity BillErrorEntity where BillErrorEntity.billId = :bill");
        q.setParameter("bill", bill);
        long countOfElelments = (Long) q.uniqueResult();

        qbdb.addBillError(bill,err,con);

        Query q2 = session.createQuery("select count(*) from BillErrorEntity BillErrorEntity where BillErrorEntity.billId = :bill");
        q2.setParameter("bill", bill);
        long newCountOfElelments = (Long) q.uniqueResult();
        Assertions.assertEquals(countOfElelments+1,newCountOfElelments);

        session.close();
        sf.close();
    }

    @Test
    @Order(13)
    void hasReferenceFail() throws QBDB.DbException {
        System.out.println("hasReferenceFail");
        int reference = 12341;
        Boolean result = qbdb.hasReference(reference,con);
        Assertions.assertEquals(false, result);
    }

    @Test
    @Order(14)
    void addReference() throws QBDB.DbException {
        System.out.println("addReference");
        int reference = 412345;
        int bill = 12341;
        qbdb.addReference(bill,reference,con);

        con = con.addAnnotatedClass(BillReferencesEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillReferencesEntity BillReferenceObj = new BillReferencesEntity();
        BillReferenceObj.setBillId(bill);
        BillReferenceObj.setRefId(reference);

        BillReferencesEntity billReferenceObjFromDB = session.find(BillReferencesEntity.class,reference);

        session.close();
        sf.close();

        Assertions.assertNotNull(billReferenceObjFromDB.getCreatedAt());
        Assertions.assertNotNull(billReferenceObjFromDB.getUpdatedAt());

        BillReferenceObj.setCreatedAt(billReferenceObjFromDB.getCreatedAt());
        BillReferenceObj.setUpdatedAt(billReferenceObjFromDB.getUpdatedAt());

        Assertions.assertEquals(BillReferenceObj,billReferenceObjFromDB);
    }

    @Test
    @Order(15)
    void hasReferenceSuccess() throws QBDB.DbException {
        System.out.println("hasReferenceSuccess");
        int reference = 412345;
        Boolean result = qbdb.hasReference(reference,con);
        Assertions.assertEquals(true, result);
    }

    @Test
    @Order(16)
    void updateReference() throws QBDB.DbException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("updateReference");
        int reference = 41235;
        int bill = 21345;
        qbdb.addReference(bill,reference,con);

        con = con.addAnnotatedClass(BillReferencesEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillReferencesEntity BillReferenceObj = new BillReferencesEntity();
        BillReferenceObj.setBillId(bill);
        BillReferenceObj.setRefId(reference);

        BillReferencesEntity billReferenceObjFromDB = session.find(BillReferencesEntity.class,reference);

        session.close();
        sf.close();

        Assertions.assertNotNull(billReferenceObjFromDB.getCreatedAt());
        Assertions.assertNotNull(billReferenceObjFromDB.getUpdatedAt());
        Assertions.assertEquals(bill,billReferenceObjFromDB.getBillId());
        Assertions.assertNotEquals(billReferenceObjFromDB.getCreatedAt(),BillReferenceObj.getUpdatedAt());

        BillReferenceObj.setCreatedAt(billReferenceObjFromDB.getCreatedAt());
        BillReferenceObj.setUpdatedAt(billReferenceObjFromDB.getUpdatedAt());

        Assertions.assertEquals(BillReferenceObj,billReferenceObjFromDB);
    }

    @Test
    @Order(17)
    void deleteInvoice() {
        System.out.println("deleteInvoice");

        int invoice = 4123;

        con = con.addAnnotatedClass(InvoicesEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        InvoicesEntity invoiceObjFromDB = session.find(InvoicesEntity.class, invoice);
        session.beginTransaction();
        session.delete(invoiceObjFromDB);
        session.getTransaction().commit();
        InvoicesEntity deletedInvoiceObjFromDB = session.find(InvoicesEntity.class, invoice);

        Assertions.assertNull(deletedInvoiceObjFromDB);

        session.close();
        sf.close();
    }

    @Test
    @Order(18)
    void deleteOrderError() {
        System.out.println("deleteOrderError");
        int order = 1234;
        con = con.addAnnotatedClass(OrderErrorEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        List<OrderErrorEntity> errorsToDelete1  = (List<OrderErrorEntity>) session.createQuery("from OrderErrorEntity OrderErrorEntity where OrderErrorEntity.orderId = :order").setParameter("order",order).list();
        session.beginTransaction();

        for (OrderErrorEntity orderErr : errorsToDelete1) {
            session.delete(orderErr);
        }
        session.getTransaction().commit();
        List<OrderErrorEntity> errorsToDelete2 = (List<OrderErrorEntity>) session.createQuery("from OrderErrorEntity OrderErrorEntity where OrderErrorEntity.orderId = :order").setParameter("order",order).list();
        Assertions.assertEquals(0,errorsToDelete2.size());
        session.close();
        sf.close();
    }

    @Test
    @Order(19)
    void deleteBill() {
        System.out.println("deleteBill");
        int bill = 12341;


        con = con.addAnnotatedClass(BillEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        BillEntity billObjFromDB = session.find(BillEntity.class,bill);
        session.beginTransaction();
        session.delete(billObjFromDB);
        session.getTransaction().commit();
        BillEntity deletedBillObjFromDB = session.find(BillEntity.class,bill);

        Assertions.assertNull(deletedBillObjFromDB);

        session.close();
        sf.close();
    }

    @Test
    @Order(20)
    void deleteBillError() {
        System.out.println("deleteBillError");
        int bill = 12341;

        con = con.addAnnotatedClass(BillErrorEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        List<BillErrorEntity> errorsToDelete1  = (List<BillErrorEntity>) session.createQuery("from BillErrorEntity BillErrorEntity where BillErrorEntity.billId = :bill").setParameter("bill",bill).list();
        session.beginTransaction();

        for (BillErrorEntity billErr : errorsToDelete1) {
            session.delete(billErr);
        }

        session.getTransaction().commit();

        List<BillErrorEntity> errorsToDelete2 = (List<BillErrorEntity>) session.createQuery("from BillErrorEntity BillErrorEntity where BillErrorEntity.billId = :bill").setParameter("bill",bill).list();
        Assertions.assertEquals(0,errorsToDelete2.size());

        session.close();
        sf.close();

    }

    @Test
    @Order(21)
    void deleteReference() throws QBDB.DbException {
        System.out.println("deleteReference");
        int reference = 41235;

        con = con.addAnnotatedClass(BillReferencesEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();

        BillReferencesEntity billReferenceObjFromDB = session.find(BillReferencesEntity.class,reference);
        session.beginTransaction();
        session.delete(billReferenceObjFromDB);
        session.getTransaction().commit();
        BillReferencesEntity deletedBillReferenceObjFromDB = session.find(BillReferencesEntity.class,reference);

        Assertions.assertNull(deletedBillReferenceObjFromDB);

        session.close();
        sf.close();
    }

    @Test
    @Order(22)
    void deleteOrder() throws QBDB.DbException {
        System.out.println("deleteOrder");
        int order = 1234;
        con = con.addAnnotatedClass(OrdersEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();


        OrdersEntity orderObjFromDB = session.find(OrdersEntity.class,order);
        session.beginTransaction();
        session.delete(orderObjFromDB);
        session.getTransaction().commit();
        OrdersEntity deletedOrderObjFromDB = session.find(OrdersEntity.class,order);

        Assertions.assertNull(deletedOrderObjFromDB);

        session.close();
        sf.close();
    }

    @Test
    @Order(23)
    void putTermsRef() throws QBDB.DbException, JAXBException {
        System.out.println("putTermsRef");
        con = con.addAnnotatedClass(TermsRefEntity.class);
        String refName = "key1";
        testXMLMarshalUnmarshal ref = new testXMLMarshalUnmarshal();
        ref.setTestField("test");
        qbdb.putTermsRef(refName, ref, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
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
            throw new QBDB.DbException("putTermsRef marshal error: ", e);
        }
        termsRef.setRef(xml);

        TermsRefEntity testXMLMarshalUnmarshalFromDB = session.find(TermsRefEntity.class,refName);
        session.close();
        sf.close();
        Assertions.assertEquals(termsRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(24)
    void getTermsRef() throws JAXBException, QBDB.DbException {
        System.out.println("getTermsRef");
        con = con.addAnnotatedClass(TermsRefEntity.class);
        String refName = "key1";
        testXMLMarshalUnmarshal termsRef = qbdb.getTermsRef(refName, con);

        Assertions.assertNotNull(termsRef);
    }

    @Test
    @Order(25)
    void putVendorRef() throws QBDB.DbException, JAXBException {
        System.out.println("putVendorRef");
        con = con.addAnnotatedClass(VendorRefEntity.class);
        String vendorRefName = "key1";
        testXMLMarshalUnmarshal ref = new testXMLMarshalUnmarshal();
        ref.setTestField("test");
        qbdb.putVendorRef(vendorRefName, ref, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
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
            throw new QBDB.DbException("putTermsRef marshal error: ", e);
        }
        vendorRef.setVendorRef(xml);

        VendorRefEntity testXMLMarshalUnmarshalFromDB = session.find(VendorRefEntity.class,vendorRefName);
        session.close();
        sf.close();
        Assertions.assertEquals(vendorRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(26)
    void getVendorRef() throws JAXBException, QBDB.DbException {
        System.out.println("getVendorRef");
        con = con.addAnnotatedClass(VendorRefEntity.class);
        String vendorRefName = "key1";
        testXMLMarshalUnmarshal vendorRef = qbdb.getVendorRef(vendorRefName, con);

        Assertions.assertNotNull(vendorRef);
    }

    @Test
    @Order(27)
    void putCustomerRef() throws QBDB.DbException, JAXBException {
        System.out.println("putCustomerRef");
        con = con.addAnnotatedClass(CustomerRefEntity.class);
        String customerRefName = "key1";
        testXMLMarshalUnmarshal ref = new testXMLMarshalUnmarshal();
        ref.setTestField("test");
        qbdb.putCustomerRef(customerRefName, ref, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
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
            throw new QBDB.DbException("putTermsRef marshal error: ", e);
        }
        customerRef.setCustomerRef(xml);

        CustomerRefEntity testXMLMarshalUnmarshalFromDB = session.find(CustomerRefEntity.class,customerRefName);
        session.close();
        sf.close();
        Assertions.assertEquals(customerRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(28)
    void getCustomerRef() throws JAXBException, QBDB.DbException {
        System.out.println("getCustomerRef");
        con = con.addAnnotatedClass(CustomerRefEntity.class);
        String customerRefName = "key1";
        testXMLMarshalUnmarshal customerRef = qbdb.getCustomerRef(customerRefName, con);

        Assertions.assertNotNull(customerRef);
    }

    @Test
    @Order(29)
    void putItemRef() throws QBDB.DbException, JAXBException {
        System.out.println("putItemRef");
        con = con.addAnnotatedClass(ItemRefEntity.class);
        String itemRefName = "key1";
        testXMLMarshalUnmarshal ref = new testXMLMarshalUnmarshal();
        ref.setTestField("test");
        qbdb.putItemRef(itemRefName, ref, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
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
            throw new QBDB.DbException("putTermsRef marshal error: ", e);
        }
        itemRef.setItemRef(xml);

        ItemRefEntity testXMLMarshalUnmarshalFromDB = session.find(ItemRefEntity.class,itemRefName);
        session.close();
        sf.close();
        Assertions.assertEquals(itemRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(30)
    void getItemRef() throws JAXBException, QBDB.DbException {
        System.out.println("getItemRef");
        con = con.addAnnotatedClass(ItemRefEntity.class);
        String itemRefName = "key1";
        testXMLMarshalUnmarshal itemRef = qbdb.getItemRef(itemRefName, con);

        Assertions.assertNotNull(itemRef);
    }

    @Test
    @Order(31)
    void putInventoryRet() throws QBDB.DbException, JAXBException {
        System.out.println("putInventoryRet");
        con = con.addAnnotatedClass(InventoryRetEntity.class);
        String inventoryRetName = "key1";
        testXMLMarshalUnmarshal ref = new testXMLMarshalUnmarshal();
        ref.setTestField("test");
        qbdb.putInventoryRet(inventoryRetName, ref, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
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
            throw new QBDB.DbException("putTermsRef marshal error: ", e);
        }
        inventoryRet.setInventoryRet(xml);

        InventoryRetEntity testXMLMarshalUnmarshalFromDB = session.find(InventoryRetEntity.class,inventoryRetName);
        session.close();
        sf.close();
        Assertions.assertEquals(inventoryRet,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(32)
    void getInventoryRet() throws JAXBException, QBDB.DbException {
        System.out.println("getInventoryRet");
        con = con.addAnnotatedClass(InventoryRetEntity.class);
        String inventoryRetName = "key1";
        testXMLMarshalUnmarshal inventoryRet = qbdb.getInventoryRet(inventoryRetName, con);

        Assertions.assertNotNull(inventoryRet);
    }

    @Test
    @Order(33)
    void hasInventoryAdjustmentsFail() throws QBDB.DbException {
        System.out.println("hasInventoryAdjustmentsFail");
        String inventoryAdjustment = "test";
        Boolean result = qbdb.hasInventoryAdjustments(inventoryAdjustment,con);
        Assertions.assertEquals(false, result);
    }

    @Test
    @Order(34)
    void addInventoryAdjustments() throws QBDB.DbException {
        System.out.println("addInventoryAdjustments");
        String inventoryAdjustment = "test";

        qbdb.addInventoryAdjustments(inventoryAdjustment, con);

        con = con.addAnnotatedClass(InventoryAdjustmentsEntity.class);
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        InventoryAdjustmentsEntity inventoryAdjustmentObj = new InventoryAdjustmentsEntity();
        inventoryAdjustmentObj.setInventoryAdjustments(inventoryAdjustment);

        InventoryAdjustmentsEntity inventoryAdjustmentObjFromDB = session.find(InventoryAdjustmentsEntity.class,inventoryAdjustment);

        session.close();
        sf.close();

        Assertions.assertEquals(inventoryAdjustmentObj,inventoryAdjustmentObjFromDB);
    }

    @Test
    @Order(35)
    void hasInventoryAdjustmentsSuccess() throws QBDB.DbException {
        System.out.println("hasInventoryAdjustmentsSuccess");

        String inventoryAdjustment = "test";
        Boolean result = qbdb.hasInventoryAdjustments(inventoryAdjustment,con);
        Assertions.assertEquals(true, result);
    }

    @Test
    @Order(36)
    void putRefNumber() throws QBDB.DbException {
        System.out.println("putRefNumber");
        con = con.addAnnotatedClass(RefNumberEntity.class);
        String poNumber = "key1";
        String ref = "test";
        qbdb.putRefNumber(poNumber, ref, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        RefNumberEntity refNumber = new RefNumberEntity();
        refNumber.setPoNumber(poNumber);
        refNumber.setRefNumber(ref);

        RefNumberEntity testXMLMarshalUnmarshalFromDB = session.find(RefNumberEntity.class,poNumber);
        session.close();
        sf.close();
        Assertions.assertEquals(refNumber,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(37)
    void getRefNumber() throws QBDB.DbException {
        System.out.println("getRefNumber");
        con = con.addAnnotatedClass(RefNumberEntity.class);
        String poNumber = "key1";
        String refNumber = qbdb.getRefNumber(poNumber,con);

        Assertions.assertNotNull(refNumber);
    }

    @Test
    @Order(38)
    void putAccountRef() throws QBDB.DbException {
        System.out.println("putAccountRef");
        con = con.addAnnotatedClass(AccountRefEntity.class);
        String fullName = "key1";
        String listId = "test";
        qbdb.putAccountRef(fullName, listId, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        AccountRefEntity accountRef = new AccountRefEntity();
        accountRef.setFullName(fullName);
        accountRef.setListId(listId);

        AccountRefEntity testXMLMarshalUnmarshalFromDB = session.find(AccountRefEntity.class,fullName);
        session.close();
        sf.close();
        Assertions.assertEquals(accountRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(39)
    void getAccountRef() throws QBDB.DbException {
        System.out.println("getAccountRef");
        con = con.addAnnotatedClass(AccountRefEntity.class);
        String fullName = "key1";
        String refNumber = qbdb.getAccountRef(fullName,con);

        Assertions.assertNotNull(refNumber);
    }

    @Test
    @Order(40)
    void putCustomerSalesTaxCodeRef() throws QBDB.DbException {
        System.out.println("putCustomerSalesTaxCodeRef");
        con = con.addAnnotatedClass(AccountRefEntity.class);
        String customerName = "key1";
        String listId = "test";
        qbdb.putCustomerSalesTaxCodeRef(customerName, listId, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        AccountRefEntity accountRef = new AccountRefEntity();
        accountRef.setFullName(customerName);
        accountRef.setListId(listId);

        AccountRefEntity testXMLMarshalUnmarshalFromDB = session.find(AccountRefEntity.class,customerName);
        session.close();
        sf.close();
        Assertions.assertEquals(accountRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(41)
    void getCustomerSalesTaxCodeRef() throws QBDB.DbException {
        System.out.println("getCustomerSalesTaxCodeRef");
        con = con.addAnnotatedClass(AccountRefEntity.class);
        String customerName = "key1";
        String refNumber = qbdb.getCustomerSalesTaxCodeRef(customerName,con);

        Assertions.assertNotNull(refNumber);
    }

    @Test
    @Order(42)
    void putSalesReceipt() throws QBDB.DbException {
        System.out.println("putSalesReceipt");
        con = con.addAnnotatedClass(SalesReceiptEntity.class);
        String memo = "key1";
        String refNumber = "test";
        qbdb.putSalesReceipt(memo, refNumber, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        SalesReceiptEntity salesReceipt = new SalesReceiptEntity();
        salesReceipt.setMemo(memo);
        salesReceipt.setRefNumber(refNumber);

        SalesReceiptEntity testXMLMarshalUnmarshalFromDB = session.find(SalesReceiptEntity.class,memo);
        session.close();
        sf.close();
        Assertions.assertEquals(salesReceipt,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(43)
    void getSalesReceipt() throws QBDB.DbException {
        System.out.println("getSalesReceipt");
        con = con.addAnnotatedClass(SalesReceiptEntity.class);
        String memo = "key1";
        String refNumber = qbdb.getSalesReceipt(memo,con);

        Assertions.assertNotNull(refNumber);
    }

    @Test
    @Order(44)
    void putBillRef() throws QBDB.DbException {
        System.out.println("putBillRef");
        con = con.addAnnotatedClass(BillRefEntity.class);
        String poNumber = "key1";
        String refNumber = "test";
        qbdb.putBillRef(poNumber, refNumber, con);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        BillRefEntity billRef = new BillRefEntity();
        billRef.setPoNumber(poNumber);
        billRef.setRefNumber(refNumber);

        BillRefEntity testXMLMarshalUnmarshalFromDB = session.find(BillRefEntity.class,poNumber);
        session.close();
        sf.close();
        Assertions.assertEquals(billRef,testXMLMarshalUnmarshalFromDB);
    }

    @Test
    @Order(45)
    void getBillRef() throws QBDB.DbException {
        System.out.println("getBillRef");
        con = con.addAnnotatedClass(BillRefEntity.class);
        String poNumber = "key1";
        String refNumber = qbdb.getBillRef(poNumber,con);

        Assertions.assertNotNull(refNumber);
    }
}
