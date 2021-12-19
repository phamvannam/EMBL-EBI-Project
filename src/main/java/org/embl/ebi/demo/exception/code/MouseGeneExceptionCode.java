package org.embl.ebi.demo.exception.code;

public enum MouseGeneExceptionCode implements ExceptionCode {
  NOT_FOUND("No mouse gene is found for id {0,number,#}."),
  NOT_FOUND_BY_SYMBOL("No mouse gene is found for symbol {0}."),
  NOT_FOUND_BY_MGI_GENE_ACC_ID("No mouse gene is found for mgi_gene_acc_id {0}."),
  NOT_FOUND_BY_SYNONYM("No mouse gene is found for synonym {0}."),
  NO_FILTER_IS_FOUND(
      "No field is found in mouse gene. The list field name is [symbol, mgi_gene_acc_id, synonym]."),
  NO_FILTER_RELATION_IS_FOUND(
      "No field is found in mouse gene. The list field name is [symbol, mgi_gene_acc_id]."),
  ;

  private String messageTemplate;

  private MouseGeneExceptionCode(String m) {
    this.messageTemplate = m;
  }

  @Override
  public String getMessageTemplate() {
    return messageTemplate;
  }

  @Override
  public String getCode() {
    return this.name();
  }
}
