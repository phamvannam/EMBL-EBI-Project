package org.embl.ebi.demo.model.internal;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MouseGeneDetail {
  private Long id;

  private String name;

  private String symbol;

  private String mgiGeneAccId;

  private List<String> synonyms;
}
