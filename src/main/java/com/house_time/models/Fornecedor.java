package com.house_time.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Fornecedor {
	
	@Id
	
	private long idFor;
	private String nomeEmpresa;
	private String cnpj ;
	public long getIdFor() {
		return idFor;
	}
	public void setIdFor(long idFor) {
		this.idFor = idFor;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
