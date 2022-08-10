package org.embl.ebi.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import org.embl.ebi.demo.exception.ResourceNotFoundException;
import org.embl.ebi.demo.exception.ServiceRuntimeExceptionBuilder;
import org.embl.ebi.demo.exception.code.MouseGeneExceptionCode;
import org.embl.ebi.demo.model.dto.MouseGeneRelationResponse;
import org.embl.ebi.demo.model.dto.MouseGeneResponse;
import org.embl.ebi.demo.model.dto.MouseGeneSymbolSynonymResponse;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;
import org.embl.ebi.demo.service.MouseGeneService;
import org.embl.ebi.demo.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

public class MouseGeneControllerTest extends BaseTest {
  String KEYWORD = "keyword";

  @TestConfiguration
  static class MouseGeneControllerTestConfiguration {
    @Bean
    MouseGeneController botController() {
      return new MouseGeneController();
    }
  }

  @MockBean private MouseGeneService mouseGeneService;

  @Autowired private MouseGeneController mouseGeneController;

  @Test
  void testGetMouseGeneById() {
    when(mouseGeneService.getById(Mockito.anyLong())).thenReturn(new MouseGeneDetail());
    ResponseEntity<MouseGeneResponse> result = mouseGeneController.getMouseGeneById(1L);
    assertNotNull(result);
    assertNotNull(result.getBody());
  }

  @Test
  void testGetMouseGeneById_not_found() {
    when(mouseGeneService.getById(Mockito.anyLong()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND)
                .add(1L)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> {
          mouseGeneController.getMouseGeneById(1L);
        },
        MessageFormat.format(MouseGeneExceptionCode.NOT_FOUND.getMessageTemplate(), 1L));
  }

  @Test
  void testGetMouseGeneByQuery() {
    Map<String, String> query = new HashMap<>();
    query.put("symbol", "Fg91");
    when(mouseGeneService.getMouseGeneDetailByQuery(Mockito.anyMap()))
        .thenReturn(new MouseGeneDetail());
    ResponseEntity<MouseGeneSymbolSynonymResponse> result =
        mouseGeneController.getMouseGeneByQuery(query);
    assertNotNull(result);
    assertNotNull(result.getBody());
  }

  @Test
  void testGetMouseGeneByQuery_NOT_FOUND_BY_SYMBOL() {
    when(mouseGeneService.getMouseGeneDetailByQuery(Mockito.anyMap()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL)
                .add(KEYWORD)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> mouseGeneController.getMouseGeneByQuery(new HashMap<>()));
  }

  @Test
  void testGetMouseGeneByQuery_NOT_FOUND_BY_SYNONYM() {
    when(mouseGeneService.getMouseGeneDetailByQuery(Mockito.anyMap()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND_BY_SYNONYM)
                .add(KEYWORD)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> mouseGeneController.getMouseGeneByQuery(new HashMap<>()));
  }

  @Test
  void testGetMouseGeneByQuery_NOT_FOUND_BY_MGI_GENE_ACC_ID() {
    when(mouseGeneService.getMouseGeneDetailByQuery(Mockito.anyMap()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(
                    MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID)
                .add(KEYWORD)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> mouseGeneController.getMouseGeneByQuery(new HashMap<>()));
  }

  @Test
  void testGetMouseGeneByQuery_NO_FILTER_IS_FOUND() {
    when(mouseGeneService.getMouseGeneDetailByQuery(Mockito.anyMap()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NO_FILTER_IS_FOUND)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> mouseGeneController.getMouseGeneByQuery(new HashMap<>()));
  }

  @Test
  void testGetMouseGeneRelation() {
    Map<String, String> query = new HashMap<>();
    query.put("symbol", "Fg91");
    when(mouseGeneService.getMouseGeneRelationDetailByQuery(Mockito.anyMap()))
        .thenReturn(new MouseGeneRelationDetail());
    ResponseEntity<MouseGeneRelationResponse> result =
        mouseGeneController.getMouseGeneRelation(query);
    assertNotNull(result);
    assertNotNull(result.getBody());
  }

  @Test
  void testGetMouseGeneRelation_NOT_FOUND_BY_SYMBOL() {
    when(mouseGeneService.getMouseGeneRelationDetailByQuery(Mockito.anyMap()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL)
                .add(KEYWORD)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> mouseGeneController.getMouseGeneRelation(new HashMap<>()));
  }

  @Test
  void testGetMouseGeneRelation_NOT_FOUND_BY_MGI_GENE_ACC_ID() {
    when(mouseGeneService.getMouseGeneRelationDetailByQuery(Mockito.anyMap()))
        .thenThrow(
            ServiceRuntimeExceptionBuilder.newBuilder(
                    MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID)
                .add(KEYWORD)
                .build());
    assertThrows(
        ResourceNotFoundException.class,
        () -> mouseGeneController.getMouseGeneRelation(new HashMap<>()));
  }
}
