/**
 * comentario de teste
 */
package com.br.virtualstore.entity;

import java.math.BigDecimal;

/**
 * @author Alex
 *
 */
public class Produto {
	
	private int id;
	private String titulo;
	private String descricao;
	private BigDecimal valor;
	private String urlImg;
	
	/**
	 * @param id
	 * @param titulo
	 * @param descricao
	 * @param valor
	 * @param urlImg
	 */
	public Produto(int id, String titulo, String descricao, BigDecimal valor, String urlImg) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.valor = valor;
		this.urlImg = urlImg;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	/**
	 * @return the urlImg
	 */
	public String getUrlImg() {
		return urlImg;
	}
	/**
	 * @param urlImg the urlImg to set
	 */
	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	

}
