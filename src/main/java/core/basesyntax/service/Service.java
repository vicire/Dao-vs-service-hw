package core.basesyntax.service;

import java.util.List;

public interface Service<T, I> {
    T create(T value);

    T get(I id);

    List<T> getAll();

    T update(T value);

    boolean delete(I id);
}
