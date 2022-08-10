package org.embl.ebi.demo.mapper;

import org.embl.ebi.demo.model.dto.MouseGeneRelationResponse;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MouseGeneRelationMapper {
  MouseGeneRelationMapper INSTANCE = Mappers.getMapper(MouseGeneRelationMapper.class);

  MouseGeneRelationResponse toMouseGeneRelationResponse(MouseGeneRelationDetail detail);
}
