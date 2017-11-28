package com.intsol.SifiInventario.beans;


public class Email {
		private Integer empresa;
		private String  nombreEmpresa;
		private Long  folio;
		private String serie;
		private String uuid;
		
		private String user;
		private String pwd;
		private String smtp;
		private String port;
		private String ssl;
		private String tls;
		private String debug;
		private String from;
		private String to;
		private String subject;
		private String body;
		private String attach1;
		private String attach2;
		private String filePath1;
		private String filePath2;
		private String cc;
		
		
		public String getUuid() { return uuid; }
		public void setUuid(String uuid) { this.uuid = uuid; }
		public Long getFolio() { return folio; }
		public void setFolio(Long folio) { this.folio = folio; }
		public String getSerie() { return serie; }
		public void setSerie(String serie) { this.serie = serie; }
		public String getNombreEmpresa() { return nombreEmpresa; }
		public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }
		public Integer getEmpresa() { return empresa; }
		public void setEmpresa(Integer empresa) { this.empresa = empresa; }
		public String getUser() { return user; }
		public void setUser(String user) { this.user = user; }
		public String getPwd() { return pwd; }
		public void setPwd(String pwd) { this.pwd = pwd; }
		public String getSmtp() { return smtp; }
		public void setSmtp(String smtp) { this.smtp = smtp; }
		public String getPort() { return port; }
		public void setPort(String port) { this.port = port; }
		public String getFrom() { return from; }
		public void setFrom(String from) { this.from = from; }
		public String getSubject() { return subject; }
		public void setSubject(String subject) { this.subject = subject; }
		public String getBody() { return body; }
		public void setBody(String body) { this.body = body; }
		public String getTo() { return to; }
		public void setTo(String to) { this.to = to; }
		public String getAttach1() { return attach1; }
		public void setAttach1(String attach1) { this.attach1 = attach1; }
		public String getAttach2() { return attach2; }
		public void setAttach2(String attach2) { this.attach2 = attach2; }
		public String getFilePath1() { return filePath1; }
		public void setFilePath1(String filePath1) { this.filePath1 = filePath1; }
		public String getFilePath2() { return filePath2; }
		public void setFilePath2(String filePath2) { this.filePath2 = filePath2; }
		public String getSsl() { return ssl; }
		public void setSsl(String ssl) { this.ssl = ssl; }
		public String getDebug() { return debug; }
		public void setDebug(String debug) { this.debug = debug; }
		public String getTls() { return tls; }
		public void setTls(String tls) { this.tls = tls; }
		public String getCc() { return cc; }
		public void setCc(String cc) { this.cc = cc; }
		@Override
		public String toString() {
			return "Email [user=" + user + ", pwd=*****" + ", smtp=" + smtp
					+ ", port=" + port + ", ssl=" + ssl + ", tls=" + tls
					+ ", debug=" + debug + ", from=" + from + ", to=" + to
					+ ", subject=" + subject + ", body=" + body + ", attach1="
					+ attach1 + ", attach2=" + attach2 + ", filePath1="
					+ filePath1 + ", filePath2=" + filePath2 + ", cc=" + cc
					+ "]";
		}
}
