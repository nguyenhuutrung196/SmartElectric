/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ReceiptData {

    private Integer billId;
    private Integer customerId;
    private Date date;
    private Double totalTprice;
    private Double discount;
    private String paymentType;

    public ReceiptData(Integer billId, Integer customerId, Date date, Double totalTprice, Double discount, String paymentType) {
        this.billId = billId;
        this.customerId = customerId;
        this.date = date;
        this.totalTprice = totalTprice;
        this.discount = discount;
        this.paymentType = paymentType;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotalTprice() {
        return totalTprice;
    }

    public void setTotalTprice(Double totalTprice) {
        this.totalTprice = totalTprice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
