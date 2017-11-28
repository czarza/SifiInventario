package com.intsol.SifiInventario.beans;
		
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Months;
import org.nfunk.jep.JEP;

/**
 * Clase de utilerias varias.
 * @author Alfredo Zarza
 *
 */
public class Util {
	/**
	 * Regresa String b cuando String a es nulo
	 * @param a
	 * @param b
	 * @return
	 */
	public static String NVL(String a, String b) {
		return (isEmpty(a))?b:a;
	}  

	/**
	 * Regresa nullvalue cuando o es nulo.
	 * @param o
	 * @param nullvalue
	 * @return
	 */
	public static <T> T nvl(T o, T nullvalue){
		return (Util.isEmpty(o))?nullvalue:o;
	}
	
	/**
	 * Regresa Bigdecimal 0 cuando theBigDecimal es nulo.
	 * @param theBigDecimal
	 * @return
	 */
	public static BigDecimal ZeroWhenNull(BigDecimal theBigDecimal) {
		return (Util.isNull(theBigDecimal))?BigDecimal.ZERO:theBigDecimal;
	}
	
	public static BigDecimal add(BigDecimal oper1, BigDecimal oper2) {
		boolean operadoresNulos = Util.isNull(oper1) && Util.isNull(oper2); 
		if (operadoresNulos) return null;
		return ZeroWhenNull(oper1).add(ZeroWhenNull(oper2));
	}
	
	public static BigDecimal abs(BigDecimal elNumero) {
		boolean esNulo = Util.isNull(elNumero); 
		if (esNulo) return null;
		return elNumero.abs();
	}
	
	
	/**
	 * Regresa el porcentaje pct(en cientos) aplicado al value.
	 * @param theBigDecimal
	 * @return
	 */
	public static BigDecimal getPCT(BigDecimal value, BigDecimal pct, Integer decimales) {
		boolean hayValores = ! Util.isNull(value) && ! Util.isNull(pct);
		if (! hayValores ) return null;
		if (value.equals(BigDecimal.ZERO) || pct.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
		decimales = Util.nvl(decimales, 2);
		BigDecimal oneHundred = new BigDecimal(100);
		return value.multiply((pct.divide(oneHundred))).setScale(decimales, RoundingMode.HALF_UP);
	}
	
	
	
	/**
	 * BigDecimal es MAYOR a cero.
	 * @param theBigDecimal
	 * @return si theBigDecimal es mayor a cero regresa true, false en caso contrario.
	 */
	public static boolean isGTZero(BigDecimal theBigDecimal) {
		return (Util.ZeroWhenNull(theBigDecimal).compareTo(BigDecimal.ZERO) > 0)?true:false;
	}
	
	/**
	 * Compara el parametro bigdecimal vs zero y si es MAYO o IGUAL entonces regresa
	 * @param theBigDecimal
	 * @return
	 */
	public static boolean isEGTZero(BigDecimal theBigDecimal) {
		return (Util.ZeroWhenNull(theBigDecimal).compareTo(BigDecimal.ZERO) >= 0)?true:false;
	}
	
	/**
	 * Regresa 0 cuando el valor del Integer es nulo
	 * @param theInteger
	 * @return
	 */
	public static Integer ZeroWhenNull(Integer theInteger) {
		return (Util.isNull(theInteger))?0:theInteger;
	}
	
/*	*//**
	 * Valora sí el objeto es nulo 
	 * @param s
	 * @return
	 *//*
	public static boolean isEmpty(String s) {
		return (s == null || s.equals(""))? true: false;
	}
*/	
	
	/**
	 * Valora sí el objeto es nulo 
	 * No pasar un list, set o cualquier otro arreglo de objetos.
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		
		String s = o.toString();
		return (Util.isNull(s) || s.trim().equals("") || s.trim().length() == 0 || s.equals("null"))?true:false;
	}
	

	public static boolean isNull(Object o) {
		return o == null;
	}


	/**
	 * Convierte String a Entero, quita comas, espacios.
	 * @param s
	 * @return
	 */
	public static Integer StringToInt(String s) {
		if (! isNumber(s)) return null;
		Long numeroLong = Long.parseLong(s);
		if (numeroLong > Integer.MAX_VALUE) {
			System.out.println("ERROR: Esta tratando de convertir " + s + " a Entero, pero EXCEDE el limite de Eneros limite= " + Integer.MAX_VALUE);
			return null;
		}
		return Integer.parseInt(s.replace(",", ""));
	}

	/**
	 * Convierte String a BigDecimal, quita comas y espacios.
	 * @param s
	 * @return
	 */
	public static BigDecimal StringToBigDecimal(String s) {
		if (! isNumber(s)) return null;
		return new BigDecimal(s.replace(",", ""));
	}
	
	
	public static Long StringToLong(String s) {
		if (! isNumber(s)) return null;
		return Long.parseLong(s);
	}
	
	
	public static String replace(String s, String replace, String by) {
		if (Util.isEmpty(s)) return "";
		return s.replace(replace, by);
	}
	
	public static String ToString(Object o) {
		if (o == null) return "";
		return String.valueOf(o);
	}
	
	
	/**
	 * Convierte un string a mayusculas, si es nulo regresa nulo.
	 * @param s
	 * @return
	 */
	public static String toUpperCase(String s) {
		if (s == null) return null;
		return s.toUpperCase();
	}
	
	public static String toString(Object o) {
		return ToString(o);
	}
	
	//String without coma
   public static String DoubleToString2(double d, boolean decimals){
		
		DecimalFormat df;
		if (decimals)
			df = new DecimalFormat("###,###,###,##0.00");
		else
			df = new DecimalFormat("###,###,###,##0");
			
		String r = df.format(d);
		
		return (r.replace(",", ""));
	}
	
   
   /**
    * Convierte un BigDecimal a String sin separador de miles ","
    * @param bigDecimal		
    * @param includeDecimals
    * @return String del BigDecimal separado por comas 
    */
   public static String BigDecimalToStringNoCommas(BigDecimal bigDecimal, boolean includeDecimals) {
	    
	   if (Util.isNull(bigDecimal)) return "";
	    
	   	bigDecimal = bigDecimal.setScale((includeDecimals)?2:0, RoundingMode.HALF_UP);
	   	String thePattern = (includeDecimals)?"#################0.00":"##############0";
	   	return new DecimalFormat(thePattern).format(bigDecimal);
   }
   
   
   
   /**
    * Convierte un BigDecimal a String 
    * @param bigDecimal		
    * @param includeDecimals
    * @return String del BigDecimal separado por comas 
    */
   public static String BigDecimalToString(BigDecimal bigDecimal, boolean includeDecimals) {
	   
	   if (Util.isNull(bigDecimal)) return "";
	   
	   bigDecimal = bigDecimal.setScale((includeDecimals)?2:0, RoundingMode.HALF_UP);
	   String thePattern = (includeDecimals)?"###,###,###,##0.00":"###,###,###,##0";
	   return new DecimalFormat(thePattern).format(bigDecimal);
   }
   
   /**
    * BigDecimal to double.
    * @param bigDecimal
    * @return
    */
   public static Double BigDecimalToDouble(BigDecimal bigDecimal) {
	   if (Util.isNull(bigDecimal)) return null;
	   return bigDecimal.doubleValue();
   }
   
	
	public static String DoubleToString(double d, boolean decimals){
		
		DecimalFormat df;
		if (decimals)
			df = new DecimalFormat("###,###,###,##0.00");
		else
			df = new DecimalFormat("###,###,###,##0");
			
		String r = df.format(d);
		
		return r;
	}
	/**
	 * Convert DateToString including time in Recibo dd/MM/yyyy hh:mm:ss a
	 * @param d
	 * @return
	 */
	public static String DateToStringCarrito(Date d) {
		DateFormat formatter = new SimpleDateFormat(Parametros.FORMATO_CARRITO);
		return (formatter.format(d));
	}
	/**
	 * Convert DateToString incluidng time in Recibo
	 * @param d
	 * @return
	 */
	public static Date StringToDateCarrito(String s) throws ParseException{
//		Si no trae fecha regresa nulo.
		if (Util.isEmpty(s)) return null;
		
//		realiza conversion
		String rfecha ="";
		if (!Util.DateToString(Util.StringToDate(s)).equals(Util.DateToString(new Date()))){
			rfecha = (s+" 12:00:00 PM");}
		else{
			rfecha = s;
		 }
		DateFormat formatter = new SimpleDateFormat(Parametros.FORMATO_CARRITO);
		return((Date)formatter.parse(rfecha));  
	}
	/**
	 * Convert DateToString dd/MM/yyyy
	 * @param d
	 * @return
	 */
	public static String DateToString(Date d) {
		if (Util.isNull(d)) return null;
		DateFormat formatter = new SimpleDateFormat(Parametros.FORMATO_FECHA);
		return (formatter.format(d));
	}
	
	/**
	 * Regresa el primer dia del mes y año determinado en formato Date 01/01/2015 
	 * @param month
	 * @param year
	 * @return
	 * @throws ParseException
	 */
	public static Date StringtoDateFirstDay(Integer month, Integer year) throws ParseException {
		if (Util.isNull(month) || Util.isNull(year)) return null;
		String dateString = "01/" + month + "/" + year;
		return StringToDate(dateString);
	}
	
	/**
	 * Regresa el mes y año en fcha pero en el ultimo dia del mes 31/12/2015.
	 * @param month
	 * @param year
	 * @return
	 * @throws ParseException
	 */
	public static Date StringtoDateLastDay(Integer month, Integer year) throws ParseException {
		if (Util.isNull(month) || Util.isNull(year)) return null;
		String dateString = "01/" + month + "/" + year;
		Date date = StringToDate(dateString);
		Integer lastDay = getLastDayOfMonth(date);
		dateString = lastDay + "/" + month + "/" + year;
		return StringToDate(dateString);
	}
	
	
	/**
	 * Convierte date to string a formato MMyyyy
	 * @param d
	 * @return
	 */
	public static String DateToStringCorrido(Date d) {
		if (isNull(d)) return null;
		DateFormat formatter = new SimpleDateFormat("MMyyyy");
		return (formatter.format(d));
	}
	
	/**
	 * Convert Date to String incluidng time dd/MM/yyyy hh:mm a
	 * @param d
	 * @return
	 */
	public static String DateToString2(Date d) {
		if (isNull(d)) return null;
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		return (formatter.format(d));
	}
	
	/**
	 * Convierte fecha a ddMMyyyy
	 * @param d
	 * @return
	 */
	public static String DateToString3(Date d) {
		if (isNull(d)) return null;
		
		DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		return (formatter.format(d));
	}
	
	/**
	 * Convierte fecha a dd/MM/yyyy hh:mm:ss
	 * @param d
	 * @return
	 */
	public static String DateToString4(Date d) {
		if (isNull(d)) return null;
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return (formatter.format(d));
	}
	
	/**
	 * Convierte fecha a string sin el año dd/MM hh:mm a
	 * @param d
	 * @return
	 */
	public static String DateToStringShort(Date d) {
		if (isNull(d)) return null;
		
		DateFormat formatter = new SimpleDateFormat("dd/MM hh:mm a");
		return (formatter.format(d));
	}
	/**
	 * Convert String to date on the format  dd/mm/yyyy
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date StringToDate(String s) throws ParseException{
		if (Util.isNull(s)|| s.length() < 8) return null;
		DateFormat formatter = new SimpleDateFormat(Parametros.FORMATO_FECHA);
		return((Date)formatter.parse(s));  
	}
	
	/**
	 * String to double
	 * @param s
	 * @return
	 */
	public static double StringToDouble(String s){
		if (Util.isEmpty(s)) return 0.0;
		s = s.replace(",", "").replace("$", "").replace(" ", "").trim();
		if (Util.isEmpty(s)) return 0.0;
		return   Double.parseDouble(s);
	}
	
	/**
	 * Convierte un String en Array  ejemplo 10,20,1,81
	 * @param s
	 * @return
	 */
	public static ArrayList<String> StringtoList(String s){
		if (Util.isEmpty(s)) return null;
		String l = s.replace("'", "");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(l.split(",")));
		return list;
	}

	
	
	/**
	 * Get the YEAR of the specified date
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Date dd;
		dd = (d==null)?new Date(): d;
		
		return(new DateTime(dd).getYear());
	}
	
	/**
	 * Get the MONTH (1,2,3,etc.. ) of the specified date
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Date dd;
		dd = (d==null)?new Date(): d;
	
		return(new DateTime(dd).getMonthOfYear());
	}

	/**
	 * Get the DAY OF MONTH of the specified date
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Date dd;
		dd = (d==null)?new Date(): d;
		
		return(new DateTime(dd).getDayOfMonth());
	}
	
	/**
	 * Get the months difference between two dates    d2 > d1
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int monthsBetween(Date d1, Date d2) {
		Date dd;
		dd = (d2==null)?new Date(): d2;
		
		return (Months.monthsBetween(new DateTime(dd), new DateTime(d1)).getMonths());
	}
	
	
	/**
	 * Add months to the specified date
	 */
	public static Date addMonths(Date d, int m) {
		DateTime dt = new DateTime (d);
		dt.plusMonths(m);
		return dt.toDate();
	}
	
	/**
	 * Add Minutes
	 * @param d
	 * @param m
	 * @return
	 */
	public static Date addMinutes(Date d, int m) {
		DateTime dt = new DateTime (d);
		dt.plusMinutes(m);
		
		return dt.toDate();
	}
	

	/**
	 * Get the last day of the month
	 * @param d Date
	 * @return
	 */
	public static int getLastDayOfMonth(Date d) {
		DateTime dt = new DateTime (d);
		DateTime lastDayOfMonth = dt.dayOfMonth().withMaximumValue();
		
		return lastDayOfMonth.getDayOfMonth();
	}
	
	
	
	/**
	 * Obtiene el numero de dias laborables entre dos fechas.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal;
	    Calendar endCal;
	    startCal = Calendar.getInstance();
	    startCal.setTime(startDate);
	    endCal = Calendar.getInstance();
	    endCal.setTime(endDate);
	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

	    return workDays;
	}	
	
	/**
	 * Agrega a una fecha un numero determinado de dias laborables.
	 * @param startDate
	 * @param numBusinessDays
	 * @return
	 */
	public static Date addBusinessDate(Date startDate, int numBusinessDays) {
		
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(startDate);
		  for(int i = 0; i < numBusinessDays; ) {
		    cal.add(Calendar.DATE, 1);
		    boolean esSabado = cal.get(Calendar.DAY_OF_WEEK) == 7;
		    boolean esDomingo = cal.get(Calendar.DAY_OF_WEEK) == 1;
		    boolean esFinDeSemana = esSabado || esDomingo;
		    if( ! esFinDeSemana ) {
		    	i++;
		    }
		  }
		  
		  return cal.getTime();
	}
	
	/**
	 * Compara dos fecha y responde a la pregunta si initialDates es despues de finalDate.
	 * @param initialDate
	 * @param finalDate
	 * @return
	 */
	public static boolean isAfter(Date initialDate, Date finalDate) {
		DateTime iniDate = new DateTime(initialDate);
		DateTime endDate = new DateTime(finalDate);
		return iniDate.isAfter(endDate);
	}
	
	/**
	 * Compara dos fecha y responde a la pregunta si initialDates es antes de finalDate.
	 * @param initialDate
	 * @param finalDate
	 * @return
	 */
	public static boolean isBefore(Date initialDate, Date finalDate) {
		DateTime iniDate = new DateTime(initialDate);
		DateTime endDate = new DateTime(finalDate);
		return iniDate.isBefore(endDate);
	}
	
	/**
	 * Compara si las fechas son iguales 
	 * @param initialDate
	 * @param finalDate
	 * @return
	 */
	public static boolean isEqual(Date initialDate, Date finalDate) {
		DateTime iniDate = new DateTime(initialDate);
		DateTime endDate = new DateTime(finalDate);
		return iniDate.isEqual(endDate);
	}
	
	
	
	
	public static double getImpuesto(double cuotaFija, double limiteInferior, double bg) {
		return cuotaFija + bg - limiteInferior;
	}

	public static String getPeriodicidad(double impuesto, double sm){
		if (impuesto > 6 * sm && impuesto <= 9 * sm) 
			return "2";
		else if (impuesto > 9 * sm) 
			return "6";
		
		return "1";
	}
	
	/**
	 * Con base el ultimo ejercicio y ultimo mes pagado compara vs. ejercicio periodo del perfil 
	 * y regresa true cuando es fecha menor, falso en caso contrario
	 * @param ultEjercicio
	 * @param ultMes
	 * @param ejercicio
	 * @param periodicidad
	 * @param periodo
	 * @return
	 */
	public static boolean isPaid(Integer ultEjercicio, Integer ultMes, Integer ejercicio, String periodicidad, Integer periodo){
		boolean reply = false;
		int myPeriod = 1;
		
		if (isNull(ultEjercicio) || isNull(ultMes) || isNull(ejercicio) || isNull(periodicidad) || isNull(periodo)) 
			return false;
		
		if (ejercicio < ultEjercicio){
			reply =  true;
		} else if (periodicidad.equals("A")){
			if ( ejercicio == ultEjercicio)
				reply = true;
		} else if (periodicidad.equals("S")) {
			myPeriod = (ultMes > 6)?2:1;
			if ( ejercicio == ultEjercicio && periodo <= myPeriod)
				reply = true;
		} else if (periodicidad.equals("B")) {
			myPeriod = (int) Math.round(ultMes/2);
			if ( ejercicio == ultEjercicio && periodo <= myPeriod)
				reply = true;
		} else if (periodicidad.equals("M")) {
			myPeriod = ultMes;
			if ( ejercicio == ultEjercicio && periodo <= myPeriod)
				reply = true;
		}


		return reply;
	}

	/**
	 * Regresa el numero de periodos (pagos) que hay que hacer deacuerdo a la periodicidad
	 * @param periodicidad
	 * @return Integer numero de periodos
	 * 		A (anual) = 1
	 * 		M (mensual) = 12
	 * 		B (bimestral) = 6
	 * 		S (semestral) 2
	 */
	public static Integer getNumPeriodos(String periodicidad) {
		
		if (Util.isNull(periodicidad)) return -1;
		int periodos = -1;
		if (periodicidad.equals("A")) {
			periodos = 1;
		} else if (periodicidad.equals("M")) {
			periodos = 12;
		} else if (periodicidad.equals("B")) {
			periodos = 6;
		} else if (periodicidad.equals("S")) {
			periodos = 2;
		}
	
		return periodos;
	}
	
	
	/**
	 * De acuerdo a la periodicidad revisa si lo que se ha pagado es el ultimo periodo.
	 * @param periodicidad
	 * @param periodo
	 * @return
	 */
	public static boolean esUltimoPeriodo(String periodicidad, Integer periodo) {
		boolean hayDatos = ! Util.isNull(periodicidad) && ! Util.isNull(periodo);
		if ( ! hayDatos ) return false;
		
		if (periodicidad.equals("A") && periodo.equals(1)) {
			return true;
		} else if (periodicidad.equals("M") && periodo.equals(12)) {
			return true;
		} else if (periodicidad.equals("B") && periodo.equals(6)) {
			return true;
		} else if (periodicidad.equals("S") && periodo.equals(2)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * A partir del numero de mes, regresa el nombre del mes
	 * @param mes
	 * @return
	 */
	public static String nombreMes(Integer mes) {
		if (Util.isNull(mes) || mes > 11 || mes < 0) return "";
		
		if (mes.equals(0)) return 	"Enero";
		if (mes.equals(1)) return 	"Febrero";
		if (mes.equals(2)) return 	"Marzo";
		if (mes.equals(3)) return 	"Abril";
		if (mes.equals(4)) return 	"Mayo";
		if (mes.equals(5)) return 	"Junio";
		if (mes.equals(6)) return 	"Julio";
		if (mes.equals(7)) return 	"Agosto";
		if (mes.equals(8)) return 	"Septiembre";
		if (mes.equals(9)) return 	"Octubre";
		if (mes.equals(10)) return 	"Noviembre";
		if (mes.equals(11)) return 	"Diciembre";
		
		return "";
	}
	
	
	
	
	/**
	 * De acuerdo a la periodicidad y el periodo regresa el rango de meses en letra eje. ENE-JUN
	 * @param periodicidad
	 * @param periodo
	 * @return
	 */
	public static String getPeriodoMeses(String periodicidad, Integer periodo) {
		if (periodicidad.equals("A")){
			return "ENE-DIC";
		}
		if (periodicidad.equals("S")) {
			if (periodo.equals(1)) return "ENE-JUN";
			if (periodo.equals(2)) return "JUL-DIC";
		}
		if (periodicidad.equals("B")) {
			if (periodo.equals(1)) return "ENE-FEB";
			if (periodo.equals(2)) return "MAR-ABR";
			if (periodo.equals(3)) return "MAY-JUN";
			if (periodo.equals(4)) return "JUL-AGO";
			if (periodo.equals(5)) return "SEP-OCT";
			if (periodo.equals(6)) return "NOV-DIC";
		}
		if (periodicidad.equals("M")) {
			if (periodo.equals(1)) return "ENE";
			if (periodo.equals(2)) return "FEB";
			if (periodo.equals(3)) return "MAR";
			if (periodo.equals(4)) return "ABR";
			if (periodo.equals(5)) return "MAY";
			if (periodo.equals(6)) return "JUN";
			if (periodo.equals(7)) return "JUL";
			if (periodo.equals(8)) return "AGO";
			if (periodo.equals(9)) return "SEP";
			if (periodo.equals(10)) return "OCT";
			if (periodo.equals(11)) return "NOV";
			if (periodo.equals(12)) return "DIC";
		}
		
		return "";
	}
	
	
	
	/**
	 * llena a la derecha con espacios en blanco 
	 * @param s
	 * @param n longitud
	 * @return
	 */
	public static String rpad(String s, int length, char c){
		int needed = length - s.length();
		if (needed <= 0){
		  return s;
		}
		char padding[] = new char[needed];
		java.util.Arrays.fill(padding, c);
		StringBuffer sb = new StringBuffer(length);
		sb.append(s);
		sb.append(padding);
		return sb.toString();
	}	
	
	/**
	 * llenar con ceros a la izquierda para PRISMA
	 * @param s
	 * @param num
	 * @return
	 */
	public static String ZerosLeft(String s, int num) {
	s = Util.nvl(s, "");
	int zeroesNeeded = num - s.length();
	String myString = "";
	while(zeroesNeeded > 0)
	{
	   myString = "0" + myString;
	   zeroesNeeded--;
	}
	myString = myString + s ; 
	return myString;
	}
	
	
	
	public static String getValue(List<SelectBox> list, String key){
		for (SelectBox o : list){
			if (o.getKey().equals(key))
				return o.getValue();
		}
		
		return "";
	}
	
	
	/**
	 * Parsea una string para realizar la operacion matematica y regresa el resultado
	 * @param var
	 * @param salarioMinimo
	 * @return
	 */
	public static BigDecimal mathParser(String var, BigDecimal salarioMinimo) {
		JEP parser = new JEP();
		BigDecimal r = BigDecimal.ZERO;
		try {
			parser.parseExpression(var.replace("SM", salarioMinimo.toString()));
			r = new BigDecimal(parser.getValue());
		}catch (Exception e) { return BigDecimal.ZERO;}
		
		return r;
	}
	
	//Round to Decimals (c)
	public static double roundToDecimals(double d, int c) {
		int temp=(int)((d*Math.pow(10,c)));
		return (((double)temp)/Math.pow(10,c));
		}
	
	public static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
	
	public static double round(double d, int decimalPlace){
	    // see the Javadoc about why we use a String in the constructor
	    // http://java.sun.com/j2se/1.5.0/docs/api/java/math/BigDecimal.html#BigDecimal(double)
	    BigDecimal bd = new BigDecimal(Double.toString(d));
	    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	 }
	
	public static double intToDouble(int i){
		return Double.parseDouble(Integer.toString(i));
	}
	
	public static String IntegerToString(Integer i) {
		if (Util.isNull(i)) return null;
		return NumberFormat.getIntegerInstance().format(i);
	}

	
	public static String ProcesaQuery(String cadena){		
		if (cadena==null)return "";
		if(cadena.contains("$valores")){
			List<String> lista_funciones=new ArrayList<String>();
			int i1= cadena.indexOf("$valores") ;
			int i2= cadena.indexOf(")")+1;
			String cad_temp;			
			do{			
				i1= cadena.indexOf("$valores") ;
				i2= cadena.indexOf(")")+1;
				try{
					cad_temp=cadena.substring(i1,i2);
				}catch(Exception ex){
					break;
				}
				lista_funciones.add(cad_temp.replaceAll(" ",""));
				cadena=cadena.replace(cad_temp,  "<#-.-#>"    ); 
			}while(cad_temp.contains(" "));
			for(i1=0;i1<lista_funciones.size();i1++){
				cadena=cadena.replaceFirst("<#-.-#>", lista_funciones.get(i1).replace("$", "_"));
			}
			StringTokenizer st=new StringTokenizer(cadena);
			lista_funciones=new ArrayList<String>();
			while(st.hasMoreTokens()){
				lista_funciones.add(st.nextToken());
			}
			cadena="";
			for(i1=0;i1<lista_funciones.size();i1++){
				if(lista_funciones.get(i1).contains("_valores")){
					cad_temp=lista_funciones.get(i1);
					
					cad_temp=cad_temp.replace("_valores", "");
					cad_temp=cad_temp.replace("(", "");
					cad_temp=cad_temp.replace(")", "");
					cad_temp=cad_temp.replace("'", "");
					String[] arr;
					if(cad_temp.contains("-")){
						arr=cad_temp.split("-");
						if(arr.length==2){							
							cad_temp=" between " + valida_valor(arr[0]) + " and " + valida_valor(arr[1]); 
						}else{
							cad_temp=valida_valor(cad_temp);
						}
					}else if(cad_temp.contains("/")){
						arr=new String[2];
						arr[0]="";
						for(int t=0;t<cad_temp.length();t++){
							if(!Character.isDigit(cad_temp.charAt(t))){							
								arr[0]+= cad_temp.charAt(t);
							}else{								
								arr[1]=cad_temp.substring(t);
								break;
							}
						}
						cad_temp= " " + arr[0] + "  to_date('"  + arr[1]  + "','" + Parametros.FORMATO_FECHA + "')";
						
					}else{
						arr=cad_temp.split(",");
						cad_temp=" in(";
						for(i2=0;i2<arr.length;i2++){														
							cad_temp+= valida_valor(arr[i2]).trim() ;							
							if(i2<arr.length-1){
								if(valida_valor(arr[i2+1]).trim().length()>0){
									cad_temp+=",";
								}
							}
						}
						cad_temp+=") ";						
					}
					
					lista_funciones.remove(i1);
					lista_funciones.add(i1,cad_temp);
				}
				cadena= cadena +  lista_funciones.get(i1) + " ";
			}
			
		}
		return cadena;
	}
	private static String valida_valor(String val){
		if(!isNumber(val) && !isLetter(val))return "";
		if(isLetter(val)){
			val="'" + val + "'";
		}
		return val;
	}
	public static boolean isNumber(String str){
		if (isEmpty(str)) return false;
		if (isEmpty(str.trim())) return false;
		
		for (int i = 0; i < str.length(); i++) {			 	        
			if (!Character.isDigit(str.charAt(i)) &&  str.charAt(i)!='.' )
				return false;
		}
		return true;
	}
	public static boolean isLetter(String str){
		for (int i = 0; i < str.length(); i++) {			 	        
			if (!Character.isLetter(str.charAt(i)))
				return false;
		}
		return true;
	}
	
	public static Perfil getPerfil(HttpSession s){
		return (Perfil) s.getAttribute(Parametros.PERFIL);
	}
	public static Integer getEmpresa(HttpSession s){
		return getPerfil(s).getEmpresa();
	}
	public static String getPlan(HttpSession s) {
		return getPerfil(s).getPlan();
	}
	public static Integer getEjercicio(HttpSession s){
		return getPerfil(s).getEjercicio();
	}
	public static Integer getPeriodo(HttpSession s){
		return getPerfil(s).getPeriodo();
	}
	public static String getUsuario(HttpSession s){
		return (String) s.getAttribute(Parametros.USUARIO);
		
	}

	
	public static byte[] getCadenaOriginal(byte[] xml, byte[] xslt) throws TransformerException, ParserConfigurationException, FileNotFoundException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		/**
		 * XML source
		 */
		InputStream xmlFile = new ByteArrayInputStream(xml);
		Source xmlSource = new StreamSource(xmlFile);
		/**
		 * XSLT contract transformation 
		 */
		 ByteArrayInputStream input = new ByteArrayInputStream(xslt);
		 Source xsltSource = new StreamSource(input);
		 Result result = new  StreamResult (output);
		 /**
		  * Transform
		  */
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer trans = factory.newTransformer(xsltSource);
		trans.transform(xmlSource, result);

		return output.toByteArray();
	}
	

	/**
	 * Sella una cadena utilizando la llave.key y regresando en string base64.
	 * @param cadenaOriginal			  // String de la cadena original del SAT 
	 * @param privKeyBytes				  // llave.key leida en bytes[]
	 * @return
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static String getSello(byte[] cadenaOriginal, byte[] privKeyBytes) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, SignatureException  {
		/**
		 * Load RSA DER private Key
		 */
		KeyFactory kf = KeyFactory.getInstance("RSA"); 
		PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privKeyBytes);
        RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(privSpec);
		/**
		 * Digest the  "cadenaOriginal" and return it signed.
		 */
		Signature s = Signature.getInstance("SHA256withRSA");
		s.initSign(privateKey);
		s.update(cadenaOriginal);
//		String signedMessage = DatatypeConverter.printBase64Binary(s.sign());
		String signedMessage = new String(Base64.encodeBase64(s.sign()));
		return signedMessage;
	}

	
	/**
	 * Codifica en base64 la llave (llave.key).
	 * @param certKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static String codificaCSD(byte[] certKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kf = KeyFactory.getInstance("RSA"); 
		PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(certKey);
        RSAPrivateKey privateKey = (RSAPrivateKey) kf.generatePrivate(privSpec);

        return  DatatypeConverter.printBase64Binary(privateKey.getEncoded());
	}

	
	
	
	public static void email(Email mail, Logger logger) throws  MessagingException, IOException {
		
		final String username = mail.getUser();
		final String from = mail.getFrom();
	    final String password = mail.getPwd();
	    final String fileName = mail.getFilePath1();
	    final String attachName = mail.getAttach1();
	    final String fileName2 = mail.getFilePath2();
	    final String attachName2 = mail.getAttach2();
	    
		Properties props = new Properties();
	    props.put("mail.smtp.auth", true);
	    if (Util.nvl(mail.getSsl(),"N").equals("S")) props.put("mail.smtp.ssl.enable", true);
	    if (Util.nvl(mail.getTls(), "N").equals("S")) props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", mail.getSmtp());
	    props.put("mail.smtp.port", mail.getPort());
		props.put("mail.smtp.ssl.trust", mail.getSmtp());
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(os);
	    
	    Session s = Session.getInstance(props,
	            new javax.mail.Authenticator() {
            			protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(username, password); }
        });
	    if (Util.nvl(mail.getDebug(), "N").equals("S")) {
	        s.setDebug(true);
	    	s.setDebugOut(ps);
	    }
	    MimeMessage msg = new MimeMessage(s);
	    msg.setFrom(new InternetAddress(from));
	    msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
	    msg.setSubject(mail.getSubject());
//	    Set CC
	    if (! Util.isEmpty(mail.getCc())) {
	    	InternetAddress[] myCcList = InternetAddress.parse(mail.getCc());
	    	msg.addRecipients(Message.RecipientType.CC,myCcList);
	    }
	    
	    /**
	     * Define el body content
	     */
	    Multipart multipart = new MimeMultipart();
	    BodyPart body = new MimeBodyPart();
	    body.setContent(mail.getBody(), "text/html");
	    multipart.addBodyPart(body,0);
	    /**
	     * Define el attachment 1
	     */
	    if (! Util.isNull(fileName)) {
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
	        DataSource source = new FileDataSource( fileName);		// Nombre físico del archivo para se leído.
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(attachName);			// Nombre del archivo en el attachment
	        multipart.addBodyPart(messageBodyPart);				// Asigna attachment 1 al multipart
	    }
        /**
         * Define el attachment 2
         */
	    if ( ! Util.isNull(fileName2)) {
	        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
	        DataSource source2 = new FileDataSource( fileName2);		// Nombre físico del archivo para se leído.
	        messageBodyPart2.setDataHandler(new DataHandler(source2));
	        messageBodyPart2.setFileName(attachName2);			// Nombre del archivo en el attachment
	        multipart.addBodyPart(messageBodyPart2);					// Asigna attachment 2 al multipart
	    }
	    msg.setContent(multipart);
        Transport.send(msg);
//        If Log entonces manda la salida del debug al logger 
	    if (Util.nvl(mail.getDebug(), "N").equals("S")) {
	    	logger.info(os);
	    }
	    os.close();
	    ps.close();
	}
	
	
	/**
	 * Get string cell value, trim and uppercase executed.
	 * @param cell
	 * @return
	 */
	public static String getCellString (Cell cell) {
		if (Util.isEmpty(cell)) return null;
		String c;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			c = Util.toString((long) cell.getNumericCellValue());
			return Util.isEmpty(c)?c:c.trim().toUpperCase();
		case Cell.CELL_TYPE_STRING:
			c =  cell.getStringCellValue();
			return (Util.isEmpty(c) || c.equals("null"))?null:c.trim().toUpperCase();
		}
		return null;
	}
	

	public static Date getCellDate(Cell cell) throws ParseException {
		if (Util.isNull(cell)) return null;
		Date d = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			d = cell.getDateCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			d = Util.StringToDate(cell.getStringCellValue());
			break;
		}
		return d;
	}

	

	/**
	 * Date to String formato   YYYY-MM-DD HH:mm:ss
	 * @param d
	 * @return
	 */
	public static String DateToStringCFDI(Date d) {
		if (Util.isNull(d)) return null;
		
		LocalTime dt = new LocalTime();
		LocalTime ml = dt.minusMinutes(5);
		if (isNull(d)) return null;
		String formato = "yyyy-MM-dd";
		DateFormat formatter = new SimpleDateFormat(formato);
		String fecha = formatter.format(d);
		String fecha_hora = fecha + "T" +ml.toString("HH:mm:ss");
		
		return fecha_hora;
	}

	/** 
	 * Lee un archivo y lo pasa a un arreglo de byte[], es útil para cuando se lee
	 * el    llave.key o   certificado.cer o cualquier otro archivo binario.
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBinary(String filePath) throws IOException {
		File file = new File(filePath);
		DataInputStream di = new DataInputStream(new FileInputStream(filePath));
		byte[] bytes = new byte[(int)file.length()];
		di.readFully(bytes);
		di.close();
		
		return bytes;
	}
	
	/**
	 * Regresa true cuando el directorio/file existen, falso en caso contrario.
	 * @param fileOrDir
	 * @return
	 */
	public static boolean existsDirectory(String fileOrDir){
		File file = new File(fileOrDir);
		if (file.exists()) return true; 
		return false;
	}
	

	/**
	 * Busca el directorio, en caso que no exista lo crea
	 * @param dir
	 * @throws IOException
	 */
	public static void existsOrCreateDirectory(String dir) throws IOException {
		File file = new File(dir);
		if ( !file.exists()) file.mkdirs();
	}
	

	/**
	 * Elimina un archivo del disco
	 * @param fileName
	 * @throws IOException
	 */
	public static void deleteFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.delete();
	}
	
	
	
	/**
	 * De un HashMap<String, ArrayList<SelectBox>> lo pasa a formato HashMap<String(tablaID), String(JSON)>  para el jqgrid.
	 * @param comboBoxes
	 * @return
	 */
	public static HashMap<String, String> convertToJQ(HashMap<String, ArrayList<SelectBox>> comboBoxes){
		String str, comma;
		HashMap<String, String> jq = new HashMap<String,String>();
		for (Map.Entry<String, ArrayList<SelectBox>> e: comboBoxes.entrySet()){
			str = "";
			comma = "";
			for(SelectBox a: e.getValue()){
				str += comma + a.getKey() + ":" + a.getValue();
				comma = ";";
			}
			jq.put(e.getKey(), str);
		}
		
		return jq;
	}

	/**
	 * Agrega un archivo al ZIP file.
	 * @param fileName
	 * @param zos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void addToZipFile(SifiFile sifiFile, ZipOutputStream zos) throws FileNotFoundException, IOException {
		File file = new File(sifiFile.getPath() + "/" + sifiFile.getFileName());
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(sifiFile.getMonth() + "/" + sifiFile.getFileName());
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}	
	
	
	/**
	 * Obtiene la extension del archivo.
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		if (Util.isNull(fileName)) return null;
		
		String extension = null;
		int dotIndex=fileName.lastIndexOf('.');
		if(dotIndex>=0) { // to prevent exception if there is no dot
		  extension=fileName.substring(dotIndex+1, fileName.length()).toLowerCase();
		}
		
		return extension;
	}
	
	
	/**
	 * 	Valida CURP
	 * @param curp
	 * @return
	 */
	  public static boolean validarCURP(String curp)
	  {
		  if (Util.isEmpty(curp)) return false;
		  
		  	String regex = 
		    "[A-Z]{1}[AEIOUX]{1}[A-Z]{2}[0-9]{6}" +
		    "(H|M)" +
		    "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
		    "[B-DF-HJ-NP-TV-Z]{3}" +
		    "[0-9A-Z]{1}[0-9]{1}$";
		    
		    Pattern patron = Pattern.compile(regex);
		    if( ! patron.matcher(curp).matches())    return false;
		    return true;
	  }

	  /**
	   * Valida RFC
	   * @param rfc
	   * @return
	   */
	  public static boolean validarRFC(String rfc){
		  if (Util.isEmpty(rfc)) return false;
		  rfc=rfc.toUpperCase().trim();
//		  return rfc.matches("[A-Z]{4}[0-9]{6}[A-Z0-9]{3}");
		  return rfc.matches("[A-Z&Ñ]{3,4}[0-9]{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])[A-Z0-9]{2}[0-9A]");
	  }

	  
	  public static void sendPost(String url, String message) throws Exception {
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-type", "application/json");
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(message);
			wr.flush();
			wr.close();
//			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	}	  

	  /**
		 * Revisa para la cadena si es vacia entonces regresa la otra, pero siempre regresa cadena.. no regresa nulos.
		 * @param o
		 * @param nullvalue
		 * @return
		 */
		public static String nvlString(String o, String nullvalue){
			if (Util.isEmpty(o)) 
				return nvl(nullvalue,"");
			
			return nvl(o,"");
		}

		
		/**
		 * Regresa el número de quincena del año de acuerdo 
		 * a la fecha de parámetro
		 * @param d
		 * @return
		 */
		public static Integer getNumQuincena(Date d) {
			if (isNull(d)) return null;
			int q = (Util.getDay(d)>1)?2:1;
			return q + (Util.getMonth(d) - 1)*2;
		}
		
	  /**
	   * Función que elimina acentos y carácteres especiales.
	   * @param input
	   * @return
	   */
	  public static String quitarAcentos(String input) {
		  if (Util.isNull(input)) return "";
		  
		  String output = Util.nvlString(input, "");
		  output = output.replace("á","a");
		  output = output.replace("é","e");
		  output = output.replace("í","i");
		  output = output.replace("ó","o");
		  output = output.replace("ú","u");
//		  output = output.replace("ñ","n");
		  output = output.replace("Á","A");
		  output = output.replace("É","E");
		  output = output.replace("Í","I");
		  output = output.replace("Ó","O");
		  output = output.replace("Ú","U");
//		  output = output.replace("Ñ","N");
		  
		  return output;
	  	
/*		    // Descomposición canónica
		    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		    // Nos quedamos únicamente con los caracteres ASCII
		    Pattern pattern = Pattern.compile("\\P{ASCII}");
		    return pattern.matcher(normalized).replaceAll("");
*/		}  
	  
	  
	/*public static void main(String [] args){
		System.out.println("CADENA NUEVA =  " + Util.ProcesaQuery(" and r.id.re_recibo $valores('16,18')  ")         );
	}*/
}