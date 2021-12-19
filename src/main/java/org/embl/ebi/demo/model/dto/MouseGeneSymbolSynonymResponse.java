package org.embl.ebi.demo.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MouseGeneSymbolSynonymResponse extends MouseGeneResponse {
  private List<String> synonyms;
}
