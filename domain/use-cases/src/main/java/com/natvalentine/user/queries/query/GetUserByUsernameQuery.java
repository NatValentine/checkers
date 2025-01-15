package com.natvalentine.user.queries.query;

import com.natvalentine.generics.utils.Query;

public class GetUserByUsernameQuery extends Query {
    private final String username;

    public GetUserByUsernameQuery(String username) {
        super(null);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
