package com.intsol.SifiInventario.beans;

import java.io.Serializable;

/**
 * Clase que se usa para generar un select-box en html.
 * @author Alfredo Zarza
 *
 */
public class SelectBox implements Serializable{
	private static final long serialVersionUID = 7085355936000969741L;
	String key;
	String value;
	String xtraValue;

	public SelectBox() {
		super();
	}
	public SelectBox(String key, String value, String xtra) {
		super();
		this.key = key;
		this.value = value;
		this.xtraValue = xtra;
	}
	public SelectBox(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	@Override
	public String toString() {
		return "SelectBox [key=" + key + ", value=" + value + ", xtraValue=" + xtraValue + "]";
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getXtraValue() {
		return xtraValue;
	}
	public void setXtraValue(String xtraValue) {
		this.xtraValue = xtraValue;
	}
	
}
