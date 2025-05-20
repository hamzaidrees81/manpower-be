package com.manpower.pos.enums;

public enum AliveStatus {
    ACTIVE, //all things required for calculation
    DELETED, //items to not show in any query
    INACTIVE //items in pending state such as an invoice item that can be shown but not in calculation such as dummy invoice
}
