package org.embl.ebi.demo.controller;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.embl.ebi.demo.exception.ResourceNotFoundException;
import org.embl.ebi.demo.exception.ServiceRuntimeException;
import org.embl.ebi.demo.exception.code.ExceptionCode;
import org.embl.ebi.demo.exception.code.MouseGeneExceptionCode;
import org.embl.ebi.demo.mapper.MouseGeneMapper;
import org.embl.ebi.demo.mapper.MouseGeneRelationMapper;
import org.embl.ebi.demo.model.dto.MouseGeneRelationResponse;
import org.embl.ebi.demo.model.dto.MouseGeneResponse;
import org.embl.ebi.demo.model.dto.MouseGeneSymbolSynonymResponse;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;
import org.embl.ebi.demo.service.MouseGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mouse-gene")
@Slf4j
public class MouseGeneController {
  private MouseGeneService mouseGeneService;

  @Autowired
  public void setMouseGeneService(MouseGeneService mouseGeneService) {
    this.mouseGeneService = mouseGeneService;
  }

  /**
   * Get Mouse gene by id
   *
   * @param mouseGeneId
   * @return ResponseEntity<MouseGeneResponse>
   */
  @GetMapping("/{id}")
  public ResponseEntity<MouseGeneResponse> getMouseGeneById(
      @PathVariable(value = "id") Long mouseGeneId) {
    log.info("[MouseGeneController] Get mouse gene by id = {}", mouseGeneId);
    try {
      MouseGeneDetail detail = mouseGeneService.getById(mouseGeneId);
      return ResponseEntity.ok().body(MouseGeneMapper.INSTANCE.toMouseGeneResponse(detail));
    } catch (ServiceRuntimeException e) {
      if (e.getCode() == MouseGeneExceptionCode.NOT_FOUND) {
        throw new ResourceNotFoundException(e.getMessage(), e.getCode(), e);
      }
      throw e;
    }
  }

  /**
   * The request to get mouse gene. That query by symbol or mgi_gene_acc_id or synonym
   *
   * @param queryMap accept the request query like api/v1/mouse-gene?symbol=Fgf8
   * @return return ResponseEntity<MouseGeneSymbolSynonymResponse>
   */
  @GetMapping
  public ResponseEntity<MouseGeneSymbolSynonymResponse> getMouseGeneByQuery(
      @RequestParam Map<String, String> queryMap) {
    log.info("[MouseGeneController] Get mouse gene by query {}", queryMap.toString());
    try {
      MouseGeneDetail detail = mouseGeneService.getMouseGeneDetailByQuery(queryMap);
      return ResponseEntity.ok()
          .body(MouseGeneMapper.INSTANCE.toMouseGeneSymbolSynonymResponse(detail));
    } catch (ServiceRuntimeException e) {
      ExceptionCode code = e.getCode();
      if (code == MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL
          || code == MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID
          || code == MouseGeneExceptionCode.NOT_FOUND_BY_SYNONYM
          || code == MouseGeneExceptionCode.NO_FILTER_IS_FOUND) {
        throw new ResourceNotFoundException(e.getMessage(), code, e);
      }
      throw e;
    }
  }
  /**
   * Get Mouse Gene and the relationships between mouse and human genes query param
   *
   * @param queryMap accept the request query like api/v1/mouse-gene/relations?symbol=Fgf8
   * @return return ResponseEntity<MouseGeneRelationResponse>
   */
  @GetMapping("/relations")
  public ResponseEntity<MouseGeneRelationResponse> getMouseGeneRelation(
      @RequestParam Map<String, String> queryMap) {
    log.info("[MouseGeneController] Get mouse gene relations by query {}", queryMap.toString());
    try {
      MouseGeneRelationDetail detail = mouseGeneService.getMouseGeneRelationDetailByQuery(queryMap);
      return ResponseEntity.ok()
          .body(MouseGeneRelationMapper.INSTANCE.toMouseGeneRelationResponse(detail));
    } catch (ServiceRuntimeException e) {
      ExceptionCode code = e.getCode();
      if (code == MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL
          || code == MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID
          || code == MouseGeneExceptionCode.NO_FILTER_RELATION_IS_FOUND) {
        throw new ResourceNotFoundException(e.getMessage(), code, e);
      }
      throw e;
    }
  }
}
