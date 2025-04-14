package com.eCommerce;

public interface IStockManager {
    boolean checkStock(int quantity);
    void updateStock(int quantity);
}
