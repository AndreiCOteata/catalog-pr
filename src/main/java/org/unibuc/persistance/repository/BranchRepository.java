package org.unibuc.persistance.repository;

import org.unibuc.persistance.model.Branch;
import org.unibuc.persistance.repository.base.BaseRepository;

import java.util.Optional;

public interface BranchRepository extends BaseRepository<Branch, Long, String> {
    Optional<Long> findByCode(Long code);
}
