package org.embl.ebi.demo.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HumanGeneRelationDetail {
  private HumanGeneDetail humanGene;

  private Long supportCount;
}
