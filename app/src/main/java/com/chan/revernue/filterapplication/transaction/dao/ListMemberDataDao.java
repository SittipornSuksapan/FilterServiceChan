package com.chan.revernue.filterapplication.transaction.dao;

public class ListMemberDataDao {
    private String customer_name,customer_adress, product_id_connected,product_brand
            ,product_name,product_system,equipment_id_connected
            ,equipment_type,equipment_description, equipment_spare_parts
            ,equipment_warning_date,equipment_installation_date;

    public ListMemberDataDao() {
    }

    public ListMemberDataDao(String customer_name,String customer_adress,
                            String product_id_connected,String product_brand
            ,String product_name,String product_system,String equipment_id_connected
            ,String equipment_type,String equipment_description,String  equipment_spare_parts
            ,String equipment_warning_date,String equipment_installation_date) {
        this.customer_name = customer_name;
        this.customer_adress = customer_adress;
        this.product_id_connected = product_id_connected;
        this.product_brand = product_brand;
        this.product_name = product_name;
        this.product_system = product_system;
        this.equipment_id_connected = equipment_id_connected;
        this.equipment_type = equipment_type;
        this.equipment_description = equipment_description;
        this.equipment_spare_parts = equipment_spare_parts;
        this.equipment_warning_date = equipment_warning_date;
        this.equipment_installation_date = equipment_installation_date;

    }

    public String getProduct_id_connected() {
        return product_id_connected;
    }

    public void setProduct_id_connected(String product_id_connected) {
        this.product_id_connected = product_id_connected;
    }

    public String getCustomer_adress() {
        return customer_adress;
    }

    public void setCustomer_adress(String customer_adress) {
        this.customer_adress = customer_adress;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_system() {
        return product_system;
    }

    public void setProduct_system(String product_system) {
        this.product_system = product_system;
    }

    public String getEquipment_id_connected() {
        return equipment_id_connected;
    }

    public void setEquipment_id_connected(String equipment_id_connected) {
        this.equipment_id_connected = equipment_id_connected;
    }

    public String getEquipment_type() {
        return equipment_type;
    }

    public void setEquipment_type(String equipment_type) {
        this.equipment_type = equipment_type;
    }

    public String getEquipment_description() {
        return equipment_description;
    }

    public void setEquipment_description(String equipment_description) {
        this.equipment_description = equipment_description;
    }

    public String getEquipment_spare_parts() {
        return equipment_spare_parts;
    }

    public void setEquipment_spare_parts(String equipment_spare_parts) {
        this.equipment_spare_parts = equipment_spare_parts;
    }

    public String getEquipment_warning_date() {
        return equipment_warning_date;
    }

    public void setEquipment_warning_date(String equipment_warning_date) {
        this.equipment_warning_date = equipment_warning_date;
    }

    public String getEquipment_installation_date() {
        return equipment_installation_date;
    }

    public void setEquipment_installation_date(String equipment_installation_date) {
        this.equipment_installation_date = equipment_installation_date;
    }
}