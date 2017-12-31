package co.omise.models;

public class Balance extends co.omise.models.IdentifiableObject {
    private java.lang.Long available;
    private java.lang.Long total;
    private java.lang.String currency;

    public java.lang.Long available() {
        return this.available;
    }

    public void available(java.lang.Long newValue) {
        this.available = newValue;
    }

    public java.lang.Long total() {
        return this.total;
    }

    public void total(java.lang.Long newValue) {
        this.total = newValue;
    }

    public java.lang.String currency() {
        return this.currency;
    }

    public void currency(java.lang.String newValue) {
        this.currency = newValue;
    }
}