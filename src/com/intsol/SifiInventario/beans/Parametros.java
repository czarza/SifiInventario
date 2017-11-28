package com.intsol.SifiInventario.beans;

/**
 * clase que contiene los valores fijos de datos de servicios y parametros del sistema. 
 * @author Alfredo Zarza
 *
 */
public final class Parametros {
	public static final String  CATALOGOS = "catalogos";
	public static final String  USUARIO = "usuario";
	public static final String  NOMBRE_USUARIO = "nombre_usuario";
	public static final String  USUARIO_ADMINISTRADOR = "usuario_administrador";
	
	public static final String  PERFIL = "perfil";
	public static final String  EMPRESA = "empresa";
	public static final String  PLAN="plan";
	public static final String  EJERCICIO = "ejercicio";
	public static final String  EMPRESAS = "empresas";
	public static final String  PLANES="planes";
	public static final String  EJERCICIOS = "ejercicios";
	public static final String  ENTROPERFIL = "entroPerfil";
	
	public static final String  EMPRESAOBJ = "empresaObj";
	public static final String  PERIODO = "periodo";
	public static final String  MUNICIPIOS = "municipios";
	public static final String  ESTADO_DEFAULT = "estado_default";
	public static final int  	YOFFSET = 7;
	public static final int  	YAFTER = 8;
	public static final String  PCTRECARGOS = "pctRecargos";
	public static final String  PCTACTUALIZACIONES = "pctActualizaciones";
	public static final String  PREDIAL = "109";
	public static final String  AGUA = "130";
	public static final String  DRENAJE = "130-B";
	public static final String  NOTA_CREDITO = "NC";
	public static final String  HOSPEDAJE = "126";
	public static final String  TRASLADO_DOMINIO = "115";
	public static final String  ARRESTO_FEDERAL = "AF001";
	public static final String  CARRITO = "carrito";
	public static final String  FORMATO_CARRITO = "dd/MM/yyyy hh:mm:ss a";
	public static final String  FORMATO_FECHA = "dd/MM/yyyy";
	public static final String  SERIE = "A";
	public static final String  SERIE_NOTA_CREDITO = "N";
	public static final String  TIPO_RELACION_NOTA_CREDITO = "01";
	public static final String  TIPO_RELACION_NOTA_CREDITO_DESC = "Nota de crédito de los documentos relacionados";
	public static final String  USO_CFDI = "G03";
	public static final String  USO_CFDI_NOTA_CREDITO = "G02";
	
	public static final Integer  CATALOGO_SAT_PRODSERV = 1;
	public static final Integer  CATALOGO_SAT_CLAVE_UNIDAD = 2;
	public static final Integer  CATALOGO_SAT_FORMAS_PAGO = 3;
	public static final Integer  CATALOGO_SAT_IMPUESTOS = 4;
	public static final Integer  CATALOGO_SAT_METODO_PAGO = 5;
	public static final Integer  CATALOGO_SAT_MONEDA = 6;  
	public static final Integer  CATALOGO_SAT_PAISES = 7;
	public static final Integer  CATALOGO_SAT_REGIMEN_FISCAL = 8;
	public static final Integer  CATALOGO_SAT_TASAS = 9;
	public static final Integer  CATALOGO_SAT_TIPOS_COMPROBANTE = 10;
	public static final Integer  CATALOGO_SAT_USO_CFDI = 11;
		
	
	public static final String ERROR 	= "msg_error";
	public static final String SUCCESS 	= "msg_success";
	public static final String PROCESS 	= "msg_process";
	public static final String ERROR_DESC 		= "";
	public static final String SUCCESS_DESC 	= "Registro actualizado exitosamente";
	public static final String PROCESS_DESC 	= "procesando...";
	
	public static final String  FILES_CONTEXT = "/sifi/sifi_receptoria";
	public static final String  TEMPLATES_DIR = "plantillas_reportes";
	public static final String  DOCS_DIR = "expediente";
	public static final String  LOGOS_DIR = "logos";
	
	
	public static final String RFC_REGEXP = "[A-Z]{4}[0-9]{6}[[A-Z0-9]{3}]?";
	public static final String RFC_GENERICO = "XAXX010101000";
	public static final String CURP_REGEXP = "[A-Z]{4}[0-9]{6}[A-Z]{6}[0-9]{2}";
	public static final String CATASTRO_REGEXP = "[0-9]{10,13}";
	public static final String CONTROL_REGEXP = "[0-9]{1,9}";
	public static final String NOMBRE_REGEXP = "\\w\\s*";	
	
	public static final String CXCPAGADA="P";
	public static final String CXCREVISION="R";
	public static final String CXCACTIVA="A";
	public static final String CXCCONVENIO="C";
	public static final String PAGOACTIVO = "A";
	public static final String PAGOCANCELADO = "C";
	public static final String PREDIALCANCELADO = "C";
	public static final String TIPOPAGOEFECTIVO= "E";
	public static final String POLIZAPRODUCCION= "P";
	public static final String POLIZAACTIVA= "A";
	public static final String RECIBOACTIVO = "A";
	public static final String RECIBOCANCELADO = "C";
	public static final String CORTECAJASTATUS = "C";
	public static final String SI="S";
	
	public static final String COSTO_FIJO="F";
	
	public static final Integer MAXTHREADS=10;
	
	public static final Integer META_PAIS = 18;
	public static final Integer META_ESTADO = 17;
	public static final Integer META_MUNICIPIO = 16;
	public static final Integer META_GIROS = 47;
	public static final Integer META_METODOSPAGO = 52;
	public static final Integer META_RUTAS = 55;
	public static final Integer META_PROFESIONES = 68;
	
	public static final Integer DECIMALES_CALCULO = 6;
	
	public static final String RFC_PAC="CAD100607RY8";

}