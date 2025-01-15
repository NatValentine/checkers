package com.natvalentine.generics.interfaces;

public interface IUseCaseAccept<AccountDTO, Void> {
    void accept(AccountDTO accountDTO);
}
