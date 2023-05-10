package ca.jrvs.apps.trading.model.domain;

import ca.jrvs.apps.trading.dao.AccountDao;

public class TraderAccountView {
    private Account account;
    private Trader trader;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }
}
