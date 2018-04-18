package com.chan.revernue.filterapplication.transaction.dao;

public class ListCustomerDao {
    private String customer_name,customer_id,customer_status,customer_adress,id_member;


    public ListCustomerDao() {
    }

    public ListCustomerDao(String customer_name, String customer_id,
                           String customer_status,String customer_adress,String id_member) {

        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.customer_status = customer_status;
        this.customer_adress = customer_adress;
        this.id_member = id_member;

    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_status() {
        return customer_status;
    }

    public void setCustomer_status(String customer_status) {
        this.customer_status = customer_status;
    }

    public String getCustomer_adress() {
        return customer_adress;
    }

    public void setCustomer_adress(String customer_adress) {
        this.customer_adress = customer_adress;
    }

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String id_member) {
        this.id_member = id_member;
    }
}