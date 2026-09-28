package com.jtrade;
import com.jtrade.account.Account;
import com.jtrade.position.Position;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Account a = new Account("ACC-001", new BigDecimal("1000000"), new BigDecimal("100100"), new BigDecimal("50000"), new BigDecimal("10000"));
        a.addCash(new BigDecimal("50000"));
        a.deductCash(new BigDecimal("20000"));
        // System.out.println(a.hasSufficientCash(new BigDecimal("2000000")));

        // System.out.println(a.getAvailableCash());

        Position p = new Position("ACC-001", "RELIANCE", 500, new BigDecimal("2850"));
        System.out.println("market value: " + p.marketValue(new BigDecimal("2900")));
        System.out.println("unrealised P&L: " + p.unrealisedPnL(new BigDecimal("2900")));
    }
}
