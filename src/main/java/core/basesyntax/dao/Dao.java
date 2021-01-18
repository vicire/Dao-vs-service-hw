package core.basesyntax.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T create(T value);

    Optional<T> get(Long id);

    List<T> getAll();

    T update(T value);

    boolean delete(Long id);
}
