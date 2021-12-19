package org.embl.ebi.demo.service;

import java.util.Map;
import org.embl.ebi.demo.model.internal.MouseGeneDetail;
import org.embl.ebi.demo.model.internal.MouseGeneRelationDetail;

public interface MouseGeneService {
  String FIELD_SYMBOL = "symbol";
  String FIELD_SYNONYM = "synonym";
  String FIELD_MGI_GENE_ACC_ID = "mgi_gene_acc_id";

  MouseGeneDetail getById(Long id);

  MouseGeneDetail getMouseGeneDetailByQuery(Map<String, String> queryMap);

  MouseGeneRelationDetail getMouseGeneRelationDetailByQuery(Map<String, String> queryMap);
}
