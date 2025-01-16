package com.natvalentine.aggregateGame;

import com.natvalentine.generics.domain.DomainActionsContainer;

public class GameHandler extends DomainActionsContainer {
    public GameHandler(Game game) {
        /*
        addDomainActions((AccountCreated event) -> {
            Account account = new Account(
                    AccountId.of(event.getAccountId()),
                    NumberAcc.of(event.getAccountNumber()),
                    Name.of(event.getName()),
                    Balance.of(event.getAccountBalance()),
                    Status.of(event.getStatus()));
            customer.setAccount(account);
        });
        */
    }
}
