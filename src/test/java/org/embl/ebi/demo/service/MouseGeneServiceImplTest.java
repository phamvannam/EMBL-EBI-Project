package org.embl.ebi.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.embl.ebi.demo.exception.ServiceRuntimeException;
import org.embl.ebi.demo.exception.code.MouseGeneExceptionCode;
import org.embl.ebi.demo.mapper.manual.MouseGeneManualMapper;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.repository.MouseGeneRepository;
import org.embl.ebi.demo.service.impl.MouseGeneServiceImpl;
import org.embl.ebi.demo.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MouseGeneServiceImplTest extends BaseTest {
  String KEYWORD = "keyword";
  @Mock private MouseGeneRepository mouseGeneRepository;

  @Mock private MouseGeneSynonymService mouseGeneSynonymService;

  @Mock private MouseGeneManualMapper mouseGeneManualMapper;

  @InjectMocks private MouseGeneServiceImpl mouseGeneService;

  @Mock MouseGeneEntity mouseGeneEntityMock;

  @Test
  void testGetById_id_isNull() {
    assertNull(mouseGeneService.getById(null));
  }

  @Test
  void testGetById_not_found() {
    when(mouseGeneRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
    assertThrows(ServiceRuntimeException.class, () -> mouseGeneService.getById(1L));
  }

  @Test
  void testGetById() {
    MouseGeneEntity mouseGeneEntity = new MouseGeneEntity();
    mouseGeneEntity.setId(1234L);
    mouseGeneEntity.setMgiGeneAccId("MGI:1920774");
    mouseGeneEntity.setName("RIKEN cDNA 1700088E04 gene");
    mouseGeneEntity.setSymbol("1700088E04Rik");

    MouseGeneDetail expected = new MouseGeneDetail();
    expected.setId(1234L);
    expected.setMgiGeneAccId("MGI:1920774");
    expected.setName("RIKEN cDNA 1700088E04 gene");
    expected.setSymbol("1700088E04Rik");

    when(mouseGeneRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mouseGeneEntity));

    MouseGeneDetail actual = mouseGeneService.getById(1L);
    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getMgiGeneAccId(), actual.getMgiGeneAccId());
    assertEquals(expected.getSymbol(), actual.getSymbol());
    assertEquals(expected.getName(), actual.getName());
  }

  /** testGetMouseGeneBySymbol */
  @Test
  void testGetMouseGeneBySymbol_symbol_isNulll() {
    assertNull(mouseGeneService.getMouseGeneBySymbol(null));
  }

  @Test
  void testGetMouseGeneBySymbol_symbol_not_found() {
    when(mouseGeneRepository.findMouseGeneBySymbol(Mockito.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneBySymbol(KEYWORD),
        MessageFormat.format(
            MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL.getMessageTemplate(), KEYWORD));
  }

  @Test
  void testGetMouseGeneBySymbol() {
    when(mouseGeneRepository.findMouseGeneBySymbol(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntityMock)).thenReturn(null);
    mouseGeneService.getMouseGeneBySymbol(KEYWORD);
    verify(mouseGeneManualMapper).buildMouseGeneDetail(mouseGeneEntityMock);
  }

  /** testGetMouseGeneByMgiGeneAccId */
  @Test
  void testGetMouseGeneByMgiGeneAccId_mgiGeneAccId_isNulll() {
    assertNull(mouseGeneService.getMouseGeneByMgiGeneAccId(null));
  }

  @Test
  void testGetMouseGeneByMgiGeneAccId_mgiGeneAccId_not_found() {
    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneByMgiGeneAccId(KEYWORD),
        MessageFormat.format(
            MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID.getMessageTemplate(), KEYWORD));
  }

  @Test
  void testGetMouseGeneByMgiGeneAccId() {
    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntityMock)).thenReturn(null);
    mouseGeneService.getMouseGeneByMgiGeneAccId(KEYWORD);
    verify(mouseGeneManualMapper).buildMouseGeneDetail(mouseGeneEntityMock);
  }

  /** testGetMouseGeneBySynonym */
  @Test
  void testGetMouseGeneBySynonym_synonym_isNulll() {
    assertNull(mouseGeneService.getMouseGeneBySynonym(null));
  }

  @Test
  void testGetMouseGeneBySynonym_synonym_not_found() {
    when(mouseGeneSynonymService.getSynonymsBySynonym(Mockito.anyString())).thenReturn(null);
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneBySynonym(KEYWORD),
        MessageFormat.format(
            MouseGeneExceptionCode.NOT_FOUND_BY_SYNONYM.getMessageTemplate(), KEYWORD));
  }

  @Test
  void testGetMouseGeneBySynonym_mouse_gene_not_found() {
    when(mouseGeneSynonymService.getSynonymsBySynonym(Mockito.anyString())).thenReturn(1L);
    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneBySynonym(KEYWORD),
        MessageFormat.format(MouseGeneExceptionCode.NOT_FOUND.getMessageTemplate(), 1L));
  }

  @Test
  void testGetMouseGeneBySynonym() {
    when(mouseGeneSynonymService.getSynonymsBySynonym(Mockito.anyString())).thenReturn(1L);
    when(mouseGeneRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntityMock)).thenReturn(null);
    mouseGeneService.getMouseGeneBySynonym(KEYWORD);
    verify(mouseGeneManualMapper).buildMouseGeneDetail(mouseGeneEntityMock);
  }

  /** testGetMouseGeneRelationDetailBySymbol */
  @Test
  void testGetMouseGeneRelationDetailBySymbol_symbol_isNull() {
    assertNull(mouseGeneService.getMouseGeneRelationDetailBySymbol(null));
  }

  @Test
  void testGetMouseGeneRelationDetailBySymbol_symbol_not_found() {
    when(mouseGeneRepository.findMouseGeneBySymbol(Mockito.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneRelationDetailBySymbol(KEYWORD),
        MessageFormat.format(
            MouseGeneExceptionCode.NOT_FOUND_BY_SYMBOL.getMessageTemplate(), KEYWORD));
  }

  @Test
  void testGetMouseGeneRelationDetailBySymbol() {
    when(mouseGeneRepository.findMouseGeneBySymbol(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntityMock))
        .thenReturn(null);
    mouseGeneService.getMouseGeneRelationDetailBySymbol(KEYWORD);
    verify(mouseGeneManualMapper).convertToMouseGeneRelationDetail(mouseGeneEntityMock);
  }

  /** testGetMouseGeneRelationDetailByMgiGeneAccId */
  @Test
  void testGetMouseGeneRelationDetailByMgiGeneAccId_mgiGeneAccId_isNulll() {
    assertNull(mouseGeneService.getMouseGeneRelationDetailByMgiGeneAccId(null));
  }

  @Test
  void testGetMouseGeneRelationDetailByMgiGeneAccId_mgiGeneAccId_not_found() {
    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.empty());
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneRelationDetailByMgiGeneAccId(KEYWORD),
        MessageFormat.format(
            MouseGeneExceptionCode.NOT_FOUND_BY_MGI_GENE_ACC_ID.getMessageTemplate(), KEYWORD));
  }

  @Test
  void testGetMouseGeneRelationDetailByMgiGeneAccId() {
    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntityMock))
        .thenReturn(null);
    mouseGeneService.getMouseGeneRelationDetailByMgiGeneAccId(KEYWORD);
    verify(mouseGeneManualMapper).convertToMouseGeneRelationDetail(mouseGeneEntityMock);
  }

  /** testGetMouseGeneRelationDetailByQuery */
  @Test
  void testGetMouseGeneRelationDetailByQuery_query_null() {
    assertNull(mouseGeneService.getMouseGeneRelationDetailByQuery(null));
  }

  @Test
  void testGetMouseGeneRelationDetailByQuery_query_notFound() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(KEYWORD, KEYWORD);
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneRelationDetailByQuery(queryParam),
        MessageFormat.format(
            MouseGeneExceptionCode.NO_FILTER_RELATION_IS_FOUND.getMessageTemplate(), ""));
  }

  @Test
  void testGetMouseGeneRelationDetailByQuery_query_symbol() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(MouseGeneService.FIELD_SYMBOL, KEYWORD);

    when(mouseGeneRepository.findMouseGeneBySymbol(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntityMock))
        .thenReturn(null);

    mouseGeneService.getMouseGeneRelationDetailByQuery(queryParam);
    verify(mouseGeneRepository).findMouseGeneBySymbol(Mockito.anyString());
  }

  @Test
  void testGetMouseGeneRelationDetailByQuery_query_mgiGeneAccId() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(MouseGeneService.FIELD_MGI_GENE_ACC_ID, KEYWORD);

    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntityMock))
        .thenReturn(null);

    mouseGeneService.getMouseGeneRelationDetailByQuery(queryParam);
    verify(mouseGeneRepository).findMouseGeneByMgiGeneAccId(Mockito.anyString());
  }

  /** testGetMouseGeneDetailByQuery */
  @Test
  void testGetMouseGeneDetailByQuery_query_null() {
    assertNull(mouseGeneService.getMouseGeneDetailByQuery(null));
  }

  @Test
  void testGetMouseGeneDetailByQuery_query_notFound() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(KEYWORD, KEYWORD);
    assertThrows(
        ServiceRuntimeException.class,
        () -> mouseGeneService.getMouseGeneDetailByQuery(queryParam),
        MessageFormat.format(MouseGeneExceptionCode.NO_FILTER_IS_FOUND.getMessageTemplate(), ""));
  }

  @Test
  void testGetMouseGeneDetailByQuery_query_symbol() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(MouseGeneService.FIELD_SYMBOL, KEYWORD);

    when(mouseGeneRepository.findMouseGeneBySymbol(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntityMock)).thenReturn(null);

    mouseGeneService.getMouseGeneDetailByQuery(queryParam);
    verify(mouseGeneRepository).findMouseGeneBySymbol(Mockito.anyString());
  }

  @Test
  void testGetMouseGeneDetailByQuery_query_mgiGeneAccId() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(MouseGeneService.FIELD_MGI_GENE_ACC_ID, KEYWORD);

    when(mouseGeneRepository.findMouseGeneByMgiGeneAccId(Mockito.anyString()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntityMock)).thenReturn(null);

    mouseGeneService.getMouseGeneDetailByQuery(queryParam);
    verify(mouseGeneRepository).findMouseGeneByMgiGeneAccId(Mockito.anyString());
  }

  @Test
  void testGetMouseGeneDetailByQuery_query_synonym() {
    Map<String, String> queryParam = new HashMap<>();
    queryParam.put(MouseGeneService.FIELD_SYNONYM, KEYWORD);

    when(mouseGeneSynonymService.getSynonymsBySynonym(Mockito.anyString())).thenReturn(1L);
    when(mouseGeneRepository.findById(Mockito.anyLong()))
        .thenReturn(Optional.of(mouseGeneEntityMock));
    when(mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntityMock)).thenReturn(null);

    mouseGeneService.getMouseGeneDetailByQuery(queryParam);
    verify(mouseGeneManualMapper).buildMouseGeneDetail(mouseGeneEntityMock);
  }
}
