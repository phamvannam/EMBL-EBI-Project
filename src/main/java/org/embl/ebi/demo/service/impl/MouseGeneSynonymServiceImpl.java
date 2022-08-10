package org.embl.ebi.demo.service.impl;

import java.util.Collections;
import java.util.List;
import org.embl.ebi.demo.repository.MouseGeneSynonymRepository;
import org.embl.ebi.demo.service.MouseGeneSynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MouseGeneSynonymServiceImpl implements MouseGeneSynonymService {

  private MouseGeneSynonymRepository mouseGeneSynonymRepository;

  @Autowired
  public void setMouseGeneSynonymRepository(MouseGeneSynonymRepository mouseGeneSynonymRepository) {
    this.mouseGeneSynonymRepository = mouseGeneSynonymRepository;
  }

  @Override
  public List<String> getSynonymsByMouseGeneId(Long mouseGeneId) {
    if (mouseGeneId == null) {
      return Collections.emptyList();
    }
    return mouseGeneSynonymRepository.getSynonymsByMouseGeneId(mouseGeneId);
  }

  @Override
  public Long getSynonymsBySynonym(String synonym) {
    if (!StringUtils.hasText(synonym)) {
      return null;
    }
    return mouseGeneSynonymRepository.getMouseGeneIdBySynonym(synonym);
  }
}
