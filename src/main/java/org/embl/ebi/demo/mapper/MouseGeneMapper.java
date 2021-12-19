package org.embl.ebi.demo.mapper;

import org.embl.ebi.demo.model.dto.MouseGeneResponse;
import org.embl.ebi.demo.model.dto.MouseGeneSymbolSynonymResponse;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MouseGeneMapper {
  MouseGeneMapper INSTANCE = Mappers.getMapper(MouseGeneMapper.class);

  @Mapping(target = "synonyms", ignore = true)
  MouseGeneDetail toMouseGeneDetail(MouseGeneEntity entity);

  MouseGeneResponse toMouseGeneResponse(MouseGeneDetail detail);

  MouseGeneSymbolSynonymResponse toMouseGeneSymbolSynonymResponse(MouseGeneDetail detail);
}
