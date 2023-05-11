package ca.jrvs.apps.trading.model.dto;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;

import java.util.List;

public class PortfolioView {
    private List<SecurityRows> securityRows;

    public List<SecurityRows> getSecurityRows() {
        return securityRows;
    }

    public void setSecurityRows(List<SecurityRows> securityRows) {
        this.securityRows = securityRows;
    }
}
