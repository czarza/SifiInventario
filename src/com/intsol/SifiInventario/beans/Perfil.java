package com.intsol.SifiInventario.beans;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Contiene datos del perfil que son compartidos en todo el proyecto.
 * @author Alfredo Zarza
 *
 */
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Perfil {
	private String usuario;
	private Integer empresa;
	private String plan;
	private Integer ejercicio;
	private Integer periodo;
	private String nombreEmpresa;
	private String nombreUsuario;
	private String entroPerfil;
	private String serie;
	private String formaPago = "01";
	private String metodoPago = "PUE";
	private String usoCFDI = "G03";
	private String condicionesPago = "CONTADO";
	private Integer agregarPredial;
	private Integer editarPredial;
	private Integer cancelarRecibo;
	private String sucursal;

	public void clear() {
		this.empresa = null;
		this.nombreEmpresa = null;
		this.plan = null;
		this.ejercicio = null;
		this.periodo = null;
		this.usuario = null;
		this.entroPerfil = null;
		this.nombreUsuario = null;
		this.agregarPredial = 0;
		this.editarPredial = 0;
		this.cancelarRecibo = 0;
	}
	
	@Override
	public String toString() {
		return "Perfil [empresa=" + empresa + ", nombreEmpresa=" + nombreEmpresa + ", plan=" + plan + ", ejercicio="
				+ ejercicio + ", periodo=" + periodo + ", usuario=" + usuario + ", entro=" + entroPerfil + "]";
	}
	
	public boolean esDIF() {
		return (Util.nvl(this.getNombreEmpresa(),"").toUpperCase().contains("DIF"))?true:false;
	}
	
	public Integer getEmpresa() { return empresa; }
	public void setEmpresa(Integer empresa) { this.empresa = empresa; }
	public String getPlan() { return plan; }
	public void setPlan(String plan) { this.plan = plan; }
	public Integer getEjercicio() { return ejercicio; }
	public void setEjercicio(Integer ejercicio) { this.ejercicio = ejercicio; }
	public Integer getPeriodo() { return periodo; }
	public void setPeriodo(Integer periodo) { this.periodo = periodo; }
	public String getEntro() { return entroPerfil; }
	public void setEntro(String entro) { this.entroPerfil = entro; }
	public String getNombreEmpresa() { return nombreEmpresa; }
	public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }
	public String getUsuario() { return usuario; }
	public void setUsuario(String usuario) { this.usuario = usuario; }
	public String getNombreUsuario() { return nombreUsuario; } 
	public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; } 
	public String getEntroPerfil() { return entroPerfil; } 
	public void setEntroPerfil(String entroPerfil) { this.entroPerfil = entroPerfil; }
	public String getFechaActual() { return Util.DateToString(new Date()); }
	public String getSerie() { return serie; } 
	public void setSerie(String serie) { this.serie = serie; }
	public String getFormaPago() { return formaPago; } 
	public void setFormaPago(String formaPago) { this.formaPago = formaPago; } 
	public String getMetodoPago() { return metodoPago; } 
	public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; } 
	public String getCondicionesPago() { return condicionesPago; } 
	public void setCondicionesPago(String condicionesPago) { this.condicionesPago = condicionesPago; }
	public Integer getAgregarPredial() { return agregarPredial; } 
	public void setAgregarPredial(Integer agregarPredial) { this.agregarPredial = agregarPredial; } 
	public Integer getEditarPredial() { return editarPredial; } 
	public void setEditarPredial(Integer editarPredial) { this.editarPredial = editarPredial; } 
	public Integer getCancelarRecibo() { return cancelarRecibo; } 
	public void setCancelarRecibo(Integer cancelarRecibo) { this.cancelarRecibo = cancelarRecibo; }
	public String getSucursal() { return sucursal; } 
	public void setSucursal(String sucursal) { this.sucursal = sucursal; }
	public String getUsoCFDI() { return usoCFDI; } 
	public void setUsoCFDI(String usoCFDI) { this.usoCFDI = usoCFDI; } 
	
}
