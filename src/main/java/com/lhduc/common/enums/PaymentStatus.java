package com.lhduc.common.enums;

public enum PaymentStatus {
    UNPAID,
    PAID,
    CANCELLED;

    public boolean isEditable() {
        return this.equals(UNPAID);
    }

    public boolean isNotEditable() {
        return !isEditable();
    }
}
