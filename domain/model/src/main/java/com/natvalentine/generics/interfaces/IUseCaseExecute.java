package com.natvalentine.generics.interfaces;

import com.natvalentine.generics.utils.Command;
import org.reactivestreams.Publisher;

//9. Generics creation to apply DDD: IUseCase - Interface to execute use cases
public interface IUseCaseExecute<T extends Command, R> {
    Publisher<R> execute(T request);

}
