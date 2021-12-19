package org.embl.ebi.demo.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ortholog")
@Getter
@Setter
public class OrthologEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "human_gene_id")
  private String humanGeneId;

  @Column(name = "support_count")
  private Long supportCount;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "mouse_gene_id")
  private MouseGeneEntity mouseGene;
}
