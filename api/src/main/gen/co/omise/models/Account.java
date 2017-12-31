package co.omise.models;

public class Account extends co.omise.models.IdentifiableObject {
    private java.lang.String email;
    private java.lang.String currency;

    public java.lang.String email() {
        return this.email;
    }

    public void email(java.lang.String newValue) {
        this.email = newValue;
    }

    public java.lang.String currency() {
        return this.currency;
    }

    public void currency(java.lang.String newValue) {
        this.currency = newValue;
    }
}