package org.embl.ebi.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.embl.ebi.demo.model.internal.HumanGeneRelationDetail;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MouseGeneRelationResponse extends MouseGeneResponse {
  private List<HumanGeneRelationDetail> humanGeneRelationDetails;
}
