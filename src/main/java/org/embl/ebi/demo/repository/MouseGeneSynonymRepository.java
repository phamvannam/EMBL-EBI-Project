package org.embl.ebi.demo.repository;

import java.util.List;
import org.embl.ebi.demo.model.entity.MouseGeneSynonymEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MouseGeneSynonymRepository extends JpaRepository<MouseGeneSynonymEntity, Long> {
  @Query(
      "SELECT mgs.synonym FROM MouseGeneSynonymEntity mgs"
          + " JOIN  MouseGeneSynonymRelationEntity mgsr ON mgsr.mouseGeneSynonymId = mgs.id"
          + " WHERE mgsr.mouseGeneId = :mouseGeneId")
  List<String> getSynonymsByMouseGeneId(Long mouseGeneId);

  @Query(
      "SELECT mgsr.mouseGeneId FROM MouseGeneSynonymEntity mgs"
          + " JOIN  MouseGeneSynonymRelationEntity mgsr ON mgsr.mouseGeneSynonymId = mgs.id"
          + " WHERE mgs.synonym = :synonym")
  Long getMouseGeneIdBySynonym(String synonym);
}
