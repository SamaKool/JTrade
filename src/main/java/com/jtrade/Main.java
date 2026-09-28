package com.jtrade;
import com.jtrade.account.Account;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Account a = new Account("ACC-001", new BigDecimal("1000000"), new BigDecimal("100100"), new BigDecimal("50000"), new BigDecimal("10000"));
        a.addCash(new BigDecimal("50000"));
        a.deductCash(new BigDecimal("20000"));
        System.out.println(a.hasSufficientCash(new BigDecimal("2000000")));

        System.out.println(a.getAvailableCash());
    }
}
