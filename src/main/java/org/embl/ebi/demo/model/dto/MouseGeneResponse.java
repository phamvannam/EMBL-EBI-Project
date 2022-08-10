package org.embl.ebi.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MouseGeneResponse {
  private Long id;

  private String name;

  private String symbol;

  private String mgiGeneAccId;
}
