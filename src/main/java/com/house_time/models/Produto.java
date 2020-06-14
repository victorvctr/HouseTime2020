package com.house_time.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProd;
	@NotBlank (message = "{fabicante.not.blank}")
	private String fabricante;
	@NotBlank (message = "{sistema.not.blank}")
	private String sistema;
	@NotBlank (message = "{punseira.not.blank}")
	private String pulseira;
	private String dimensao;
    private String cor;
	private String peso;
	private String modelo;
	private String valor;
	
	@Min(value = 1, message = "{estoque.not.null}")
	private int estoque;
	private String url_imagem;
	


	public long getIdProd() {
		return idProd;
	}

	public void setIdProd(long idProd) {
		this.idProd = idProd;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getPulseira() {
		return pulseira;
	}

	public void setPulseira(String pulseira) {
		this.pulseira = pulseira;
	}

	public String getDimensao() {
		return dimensao;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getValor() {
		return valor;
	}
	
	public String getValorReal() {
		return "R$ " + valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}



	public String getUrl_imagem() {
		return url_imagem;
	}

	public void setUrl_imagem(String url_imagem) {
		this.url_imagem = url_imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProd ^ (idProd >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (idProd != other.idProd)
			return false;
		return true;
	}



	}