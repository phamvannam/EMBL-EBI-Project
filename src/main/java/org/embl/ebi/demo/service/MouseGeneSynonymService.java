package org.embl.ebi.demo.service;

import java.util.List;

public interface MouseGeneSynonymService {
  List<String> getSynonymsByMouseGeneId(Long mouseGeneId);

  Long getSynonymsBySynonym(String synonym);
}
