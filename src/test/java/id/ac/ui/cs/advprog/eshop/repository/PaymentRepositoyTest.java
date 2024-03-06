package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoyTest {
    PaymentRepositoy paymentRepositoy;
    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp(){
        paymentRepositoy = new PaymentRepositoy();

        payments = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudarajat");
        orders.add(order1);

        Payment payment1 = new Payment("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",orders.get(0),
                "", null);
        Payment payment2 = new Payment("bbbbbbbb-bbbb-bbbb-bbbb-aaaaaaaaaaaa",orders.get(0),
                "", null);
        payments.add(payment1);
        payments.add(payment2);


        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP00000000AAA");
        Payment paymentV1 = new PaymentVoucher("bbbbbbbb-cccc-bbbb-bbbb-aaaaaaaaaaaa",orders.get(0),
                "VOUCHER", paymentDataVoucher);
        payments.add(paymentV1);

        Map<String, String> paymentDataBank = new HashMap<>();
        paymentDataBank.put("bankName", "a");
        paymentDataBank.put("referenceCode","0");
        Payment paymentB1 = new PaymentBankTransfer("bbbbbbbb-dddd-bbbb-bbbb-aaaaaaaaaaaa",orders.get(0),
                "BANK", paymentDataBank);
        payments.add(paymentB1);
    }

    @Test
    void testAddPaymentSuccess(){
        Payment payment = payments.get(1);
        Payment result = paymentRepositoy.save(payment);

        Payment findResult = paymentRepositoy.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentVoucherSuccess(){
        Payment payment = payments.get(2);
        Payment result = paymentRepositoy.save(payment);

        Payment findResult = paymentRepositoy.findById(payments.get(2).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testAddPaymentDuplicatedId(){
        Payment payment1 = payments.get(1);
        Payment result = paymentRepositoy.save(payment1);

        Payment payment2 = new Payment(payment1.getId(), payment1.getOrder(), payment1.getMethod(), payment1.getPaymentData());

        assertThrows(IllegalStateException.class, ()->{
            paymentRepositoy.save(payment2);
        });
    }

    @Test
    void testFindByIdIfIdFound(){
        for (Payment payment : payments){
            paymentRepositoy.save(payment);
        }
        Payment findResult = paymentRepositoy.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertSame(payments.get(1).getPaymentData(), findResult.getPaymentData());
        assertSame(payments.get(1).getOrder(), findResult.getOrder());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
    }

    @Test
    void testFindByIdIfIdNotFound(){
        assertNull(paymentRepositoy.findById("zczc"));
    }

    @Test
    void testGetAllPayments(){
        for (Payment payment : payments){
            paymentRepositoy.save(payment);
        }
        List<Payment> result = paymentRepositoy.getAllPayments();
        assertEquals(4, result.size());
    }
}