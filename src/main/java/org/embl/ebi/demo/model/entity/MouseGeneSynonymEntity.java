package org.embl.ebi.demo.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mouse_gene_synonym")
@Getter
@Setter
public class MouseGeneSynonymEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "mgi_gene_acc_id")
  private String mgiGeneAccId;

  @Column(name = "synonym")
  private String synonym;
}
