package com.natvalentine.generics.interfaces;

import com.natvalentine.generics.utils.Query;
import com.natvalentine.generics.utils.QueryResponse;

public interface IUseCaseGet <Q extends Query, R> {
    //R get(Q request); // For single result
    QueryResponse<R> get(Q request);  // For multiple results
}
