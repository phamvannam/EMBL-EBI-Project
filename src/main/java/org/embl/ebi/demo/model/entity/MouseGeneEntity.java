package org.embl.ebi.demo.model.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "mouse_gene")
@Getter
@Setter
public class MouseGeneEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "symbol")
  private String symbol;

  @Column(name = "mgi_gene_acc_id")
  private String mgiGeneAccId;

  @OneToMany(
      mappedBy = "mouseGene",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL},
      orphanRemoval = true)
  @Fetch(FetchMode.SUBSELECT)
  private List<OrthologEntity> orthologEntities = Collections.emptyList();
}
