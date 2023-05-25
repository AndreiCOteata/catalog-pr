package org.unibuc.persistance.repository.base;

import org.unibuc.logger.BaseLogger;
import org.unibuc.persistance.DatabaseConnection;
import org.unibuc.util.ActionService;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, V, K> {
    BaseLogger logger = BaseLogger.getInstance();
    ActionService actionService = ActionService.getInstance();
    DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    void add(T t);

    Optional<T> findById(V v);

    Optional<T> findBy(K k);

    List<T> findAll();

    void delete(V v);

    void update(T t);
}
