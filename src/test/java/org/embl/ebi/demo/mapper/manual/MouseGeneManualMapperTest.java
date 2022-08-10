package org.embl.ebi.demo.mapper.manual;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import org.embl.ebi.demo.model.entity.MouseGeneEntity;
import org.embl.ebi.demo.model.entity.OrthologEntity;
import org.embl.ebi.demo.model.internal.HumanGeneRelationDetail;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;
import org.embl.ebi.demo.service.MouseGeneSynonymService;
import org.embl.ebi.demo.test.BaseTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MouseGeneManualMapperTest extends BaseTest {

  @InjectMocks MouseGeneManualMapper mouseGeneManualMapper;

  @Mock private MouseGeneSynonymService mouseGeneSynonymService;

  @Mock MouseGeneRelationDetail mouseGeneRelationDetail;

  @Mock OrthologEntity orthologEntity;

  @Test
  void testTransformToHumanGeneRelationDetail_null() {
    assertNull(mouseGeneManualMapper.transformToHumanGeneRelationDetail(null));
  }

  @Test
  void testTransformToHumanGeneRelationDetail() {
    OrthologEntity orthologEntity = new OrthologEntity();
    orthologEntity.setHumanGeneId("humanGenId");
    orthologEntity.setSupportCount(10L);

    HumanGeneRelationDetail result =
        mouseGeneManualMapper.transformToHumanGeneRelationDetail(orthologEntity);

    assertNotNull(result);
    assertNotNull(result.getHumanGene());
    assertEquals(orthologEntity.getHumanGeneId(), result.getHumanGene().getHumanGeneId());
    assertEquals(orthologEntity.getSupportCount(), result.getSupportCount());
  }

  @Test
  void testBuildHumanGeneId_detail_null() {
    mouseGeneManualMapper.buildHumanGeneId(null, Arrays.asList(orthologEntity));
    verify(mouseGeneRelationDetail, Mockito.times(0)).setHumanGeneRelationDetails(Mockito.any());
  }

  @Test
  void testBuildHumanGeneId_orthologEntities_null() {
    mouseGeneManualMapper.buildHumanGeneId(mouseGeneRelationDetail, Collections.emptyList());
    verify(mouseGeneRelationDetail, Mockito.times(0)).setHumanGeneRelationDetails(Mockito.any());
  }

  @Test
  void testBuildHumanGeneId() {
    mouseGeneManualMapper.buildHumanGeneId(mouseGeneRelationDetail, Arrays.asList(orthologEntity));
    verify(mouseGeneRelationDetail, Mockito.times(1)).setHumanGeneRelationDetails(Mockito.any());
  }

  @Test
  void testBuildMouseGeneDetail_null() {
    assertNull(mouseGeneManualMapper.buildMouseGeneDetail(null));
  }

  @Test
  void testBuildMouseGeneDetail() {
    MouseGeneEntity mouseGeneEntity = new MouseGeneEntity();
    mouseGeneEntity.setId(1L);
    mouseGeneEntity.setName("Mouse Gene name");
    mouseGeneEntity.setSymbol("SB123");
    mouseGeneEntity.setMgiGeneAccId("MgiGeneAccId1");
    when(mouseGeneSynonymService.getSynonymsByMouseGeneId(Mockito.anyLong()))
        .thenReturn(Collections.emptyList());
    MouseGeneDetail result = mouseGeneManualMapper.buildMouseGeneDetail(mouseGeneEntity);

    assertNotNull(result);
    assertEquals(mouseGeneEntity.getId(), result.getId());
    assertEquals(mouseGeneEntity.getMgiGeneAccId(), result.getMgiGeneAccId());
    assertEquals(mouseGeneEntity.getName(), result.getName());
    assertEquals(mouseGeneEntity.getSymbol(), result.getSymbol());
  }

  @Test
  void testConvertToMouseGeneRelationDetail_null() {
    assertNull(mouseGeneManualMapper.convertToMouseGeneRelationDetail(null));
  }

  @Test
  void testConvertToMouseGeneRelationDetail() {

    MouseGeneEntity mouseGeneEntity = new MouseGeneEntity();
    mouseGeneEntity.setId(1L);
    mouseGeneEntity.setName("Mouse Gene name");
    mouseGeneEntity.setSymbol("SB123");
    mouseGeneEntity.setMgiGeneAccId("MgiGeneAccId1");

    MouseGeneRelationDetail result =
        mouseGeneManualMapper.convertToMouseGeneRelationDetail(mouseGeneEntity);

    assertNotNull(result);
    assertEquals(mouseGeneEntity.getId(), result.getId());
    assertEquals(mouseGeneEntity.getMgiGeneAccId(), result.getMgiGeneAccId());
    assertEquals(mouseGeneEntity.getName(), result.getName());
    assertEquals(mouseGeneEntity.getSymbol(), result.getSymbol());
  }
}
