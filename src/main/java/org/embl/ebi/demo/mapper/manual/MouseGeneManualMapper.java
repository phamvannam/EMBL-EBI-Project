package org.embl.ebi.demo.mapper.manual;

import java.util.List;
import java.util.stream.Collectors;
import org.embl.ebi.demo.mapper.MouseGeneMapper;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.embl.ebi.demo.model.entity.OrthologEntity;
import org.embl.ebi.demo.model.internal.HumanGeneDetail;
import org.embl.ebi.demo.model.internal.HumanGeneRelationDetail;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;
import org.embl.ebi.demo.service.MouseGeneSynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class MouseGeneManualMapper {

  private MouseGeneSynonymService mouseGeneSynonymService;

  @Autowired
  public void setMouseGeneSynonymService(MouseGeneSynonymService mouseGeneSynonymService) {
    this.mouseGeneSynonymService = mouseGeneSynonymService;
  }

  public MouseGeneRelationDetail convertToMouseGeneRelationDetail(MouseGeneEntity entity) {
    if (entity == null) {
      return null;
    }
    MouseGeneRelationDetail detail = new MouseGeneRelationDetail();
    detail.setId(entity.getId());
    detail.setMgiGeneAccId(entity.getMgiGeneAccId());
    detail.setSymbol(entity.getSymbol());
    detail.setName(entity.getName());
    buildHumanGeneId(detail, entity.getOrthologEntities());
    return detail;
  }

  public MouseGeneDetail buildMouseGeneDetail(MouseGeneEntity mouseGeneEntity) {
    if (mouseGeneEntity == null) {
      return null;
    }
    MouseGeneDetail mouseGeneDetail = MouseGeneMapper.INSTANCE.toMouseGeneDetail(mouseGeneEntity);
    List<String> synonyms =
        mouseGeneSynonymService.getSynonymsByMouseGeneId(mouseGeneEntity.getId());
    mouseGeneDetail.setSynonyms(synonyms);
    return mouseGeneDetail;
  }

  protected void buildHumanGeneId(
      MouseGeneRelationDetail detail, List<OrthologEntity> orthologEntities) {
    if (detail == null) {
      return;
    }
    if (CollectionUtils.isEmpty(orthologEntities)) {
      return;
    }
    List<HumanGeneRelationDetail> humanGeneRelationDetails =
        orthologEntities.stream()
            .map(this::transformToHumanGeneRelationDetail)
            .collect(Collectors.toList());
    detail.setHumanGeneRelationDetails(humanGeneRelationDetails);
  }

  protected HumanGeneRelationDetail transformToHumanGeneRelationDetail(OrthologEntity entity) {
    if (entity == null) {
      return null;
    }
    HumanGeneRelationDetail humanGeneRelationDetail = new HumanGeneRelationDetail();
    humanGeneRelationDetail.setHumanGene(
        HumanGeneDetail.builder().humanGeneId(entity.getHumanGeneId()).build());
    humanGeneRelationDetail.setSupportCount(entity.getSupportCount());
    return humanGeneRelationDetail;
  }
}
