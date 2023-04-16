package com.example.core.order;

public interface OrderService {
    Order creatOrder(long memberId, String itemName, int itemPrice);
}
