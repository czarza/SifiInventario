package com.intsol.SifiInventario.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class IvAdeudo {
	@EmbeddedId AdeudoId id = new AdeudoId();
	@Column private String ad_status;
	@Column private String ad_usuario;
	@Column private String ad_monto;
	@Column private String ad_motivo;
	@Column private Date   ad_fecha_emision;
	@Column private Date   ad_fecha_vencimiento;
	@Column private String ad_notas;

}
