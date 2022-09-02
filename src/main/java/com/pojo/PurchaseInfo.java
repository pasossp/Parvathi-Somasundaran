package com.pojo;

public class PurchaseInfo {
    // using the builder pattern to set values for the variables
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private String complete;

    PurchaseInfo()
    {}

    public PurchaseInfo(int id,int petId,int quantity,String shipDate, String status, String complete)
    {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;

    }

    public int getId() {
        return id;
    }

    public String getShipDate() {
        return shipDate;
    }

    public int getPetId() {
        return petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getComplete() {
        return complete;
    }

    public static class Builder
    {
        private int id;
        private int petId;
        private int quantity;
        private String shipDate;
        private String status;
        private String complete;

        public Builder(){};
        public PurchaseInfo build() {
            return new PurchaseInfo(id,petId,quantity,shipDate,status,complete);
        }


        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setComplete(String complete) {
            this.complete = complete;
            return this;
        }

        public Builder setPetId(int petId) {
            this.petId = petId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setShipDate(String shipDate) {
            this.shipDate = shipDate;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

    }





}
