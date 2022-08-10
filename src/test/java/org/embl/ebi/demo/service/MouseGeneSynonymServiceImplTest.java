package org.embl.ebi.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.embl.ebi.demo.repository.MouseGeneSynonymRepository;
import org.embl.ebi.demo.service.impl.MouseGeneSynonymServiceImpl;
import org.embl.ebi.demo.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MouseGeneSynonymServiceImplTest extends BaseTest {
  @InjectMocks private MouseGeneSynonymServiceImpl mouseGeneSynonymService;

  @Mock private MouseGeneSynonymRepository mouseGeneSynonymRepository;

  /** testGetSynonymsByMouseGeneId */
  @Test
  void testGetSynonymsByMouseGeneId_null() {
    assertEquals(mouseGeneSynonymService.getSynonymsByMouseGeneId(null), Collections.emptyList());
  }

  @Test
  void testGetSynonymsByMouseGeneId() {
    List<String> expectResults = new ArrayList<>();
    expectResults.add("MJSG1s2");
    when(mouseGeneSynonymRepository.getSynonymsByMouseGeneId(Mockito.anyLong()))
        .thenReturn(expectResults);
    List<String> actualResults = mouseGeneSynonymService.getSynonymsByMouseGeneId(1L);
    assertEquals(1, actualResults.size());
  }

  /** testGetSynonymsBySynonym */
  @Test
  void testGetSynonymsBySynonym_null() {
    assertNull(mouseGeneSynonymService.getSynonymsBySynonym(null));
  }

  @Test
  void testGetSynonymsBySynonym() {
    when(mouseGeneSynonymRepository.getMouseGeneIdBySynonym(Mockito.anyString())).thenReturn(1L);
    Long actualResult = mouseGeneSynonymService.getSynonymsBySynonym("MJSG1s2");
    assertEquals(1L, actualResult);
  }
}
