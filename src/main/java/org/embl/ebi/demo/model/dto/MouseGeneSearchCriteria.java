package org.embl.ebi.demo.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class MouseGeneSearchCriteria {
  @NotNull(message = "key_work must not be null")
  private String keyWork;

  @NotNull(message = "page must not be null")
  private Integer page;

  @NotNull(message = "size must not be null")
  private Integer size;
}
