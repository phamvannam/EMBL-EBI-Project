package org.embl.ebi.demo.service.impl;

import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.embl.ebi.demo.exception.ServiceRuntimeExceptionBuilder;
import org.embl.ebi.demo.exception.code.MouseGeneExceptionCode;
import org.embl.ebi.demo.mapper.MouseGeneMapper;
import org.embl.ebi.demo.mapper.manual.MouseGeneManualMapper;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;
import org.embl.ebi.demo.repository.MouseGeneRepository;
import org.embl.ebi.demo.service.MouseGeneService;
import org.embl.ebi.demo.service.MouseGeneSynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MouseGeneServiceImpl implements MouseGeneService {

  private MouseGeneRepository mouseGeneRepository;

  private MouseGeneSynonymService mouseGeneSynonymService;

  private MouseGeneManualMapper mouseGeneManualMapper;

  @Autowired
  public void setMouseGeneManualMapper(MouseGeneManualMapper mouseGeneManualMapper) {
    this.mouseGeneManualMapper = mouseGeneManualMapper;
  }

  @Autowired
  public void setMouseGeneSynonymService(MouseGeneSynonymService mouseGeneSynonymService) {
    this.mouseGeneSynonymService = mouseGeneSynonymService;
  }

  @Autowired
  public void setMouseGeneRepository(MouseGeneRepository mouseGeneRepository) {
    this.mouseGeneRepository = mouseGeneRepository;
  }

  @Override
  public MouseGeneDetail getById(Long id) {
    if (id == null) {
      return null;
    }
    MouseGeneEntity entity =
        mouseGeneRepository
            .findById(id)
            .orElseThrow(
                () ->
                    ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND)
                        .add(id)
                        .build());
    return MouseGeneMapper.INSTANCE.toMouseGeneDetail(entity);
  }

  @Override
  public MouseGeneDetail getMouseGeneDetailByQuery(Map<String, String> queryMap) {
    if (MapUtils.isEmpty(queryMap)) {
      return null;
    }
    String symbol = queryMap.get(FIELD_SYMBOL);
    if (StringUtils.hasText(symbol)) {
      return getMouseGeneBySymbol(symbol);
    }
    String mgiGeneAccId = queryMap.get(FIELD_MGI_GENE_ACC_ID);
    if (StringUtils.hasText(mgiGeneAccId)) {
      return getMouseGeneByMgiGeneAccId(mgiGeneAccId);
    }
    String synonym = queryMap.get(FIELD_SYNONYM);
    if (StringUtils.hasText(synonym)) {
      return getMouseGeneBySynonym(synonym);
    }
    throw ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NO_FILTER_IS_FOUND)
        .build();
  }

  public MouseGeneDetail getMouseGeneBySymbol(String symbol) {
    if (!StringUtils.hasText(symbol)) {
      return null;
    }
    MouseGeneEntity mouseGeneEntity =
        mouseGeneRepository
            .findMouseGeneBySymbol(symbol)
            .orElseThrow(
                () ->
                    ServiceRuntimeExceptionBuilder.newBuilder(
                            MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL)
                        .add(symbol)
                        .build());
    return mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntity);
  }

  public MouseGeneDetail getMouseGeneByMgiGeneAccId(String mgiGeneAccId) {
    if (!StringUtils.hasText(mgiGeneAccId)) {
      return null;
    }
    MouseGeneEntity mouseGeneEntity =
        mouseGeneRepository
            .findMouseGeneByMgiGeneAccId(mgiGeneAccId)
            .orElseThrow(
                () ->
                    ServiceRuntimeExceptionBuilder.newBuilder(
                            MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID)
                        .add(mgiGeneAccId)
                        .build());
    return mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntity);
  }

  public MouseGeneDetail getMouseGeneBySynonym(String synonym) {
    if (!StringUtils.hasText(synonym)) {
      return null;
    }
    Long mouseGeneId = mouseGeneSynonymService.getSynonymsBySynonym(synonym);
    if (mouseGeneId == null) {
      throw ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND_BY_SYNONYM)
          .add(synonym)
          .build();
    }
    MouseGeneEntity mouseGeneEntity =
        mouseGeneRepository
            .findById(mouseGeneId)
            .orElseThrow(
                () ->
                    ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND)
                        .add(mouseGeneId)
                        .build());

    return mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntity);
  }

  @Override
  public MouseGeneRelationDetail getMouseGeneRelationDetailByQuery(Map<String, String> queryMap) {
    if (MapUtils.isEmpty(queryMap)) {
      return null;
    }
    String symbol = queryMap.get(FIELD_SYMBOL);
    if (StringUtils.hasText(symbol)) {
      return getMouseGeneRelationDetailBySymbol(symbol);
    }
    String mgiGeneAccId = queryMap.get(FIELD_MGI_GENE_ACC_ID);
    if (StringUtils.hasText(mgiGeneAccId)) {
      return getMouseGeneRelationDetailByMgiGeneAccId(mgiGeneAccId);
    }
    throw ServiceRuntimeExceptionBuilder.newBuilder(
            MouseGeneExceptionCode.NO_FILTER_RELATION_IS_FOUND)
        .build();
  }

  public MouseGeneRelationDetail getMouseGeneRelationDetailBySymbol(String symbol) {
    if (!StringUtils.hasText(symbol)) {
      return null;
    }
    MouseGeneEntity mouseGeneEntity =
        mouseGeneRepository
            .findMouseGeneBySymbol(symbol)
            .orElseThrow(
                () ->
                    ServiceRuntimeExceptionBuilder.newBuilder(
                            MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL)
                        .add(symbol)
                        .build());
    return mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntity);
  }

  public MouseGeneRelationDetail getMouseGeneRelationDetailByMgiGeneAccId(String mgiGeneAccId) {
    if (!StringUtils.hasText(mgiGeneAccId)) {
      return null;
    }
    MouseGeneEntity mouseGeneEntity =
        mouseGeneRepository
            .findMouseGeneByMgiGeneAccId(mgiGeneAccId)
            .orElseThrow(
                () ->
                    ServiceRuntimeExceptionBuilder.newBuilder(
                            MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID)
                        .add(mgiGeneAccId)
                        .build());
    return mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntity);
  }
}
