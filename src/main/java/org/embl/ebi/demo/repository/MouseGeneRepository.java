package org.embl.ebi.demo.repository;

import java.util.Optional;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouseGeneRepository extends JpaRepository<MouseGeneEntity, Long> {

  Optional<MouseGeneEntity> findMouseGeneBySymbol(String symbol);

  Optional<MouseGeneEntity> findMouseGeneByMgiGeneAccId(String mgiGeneAccId);
}
