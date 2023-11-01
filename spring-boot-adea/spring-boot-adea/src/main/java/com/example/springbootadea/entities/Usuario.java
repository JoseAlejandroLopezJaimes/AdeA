package com.example.springbootadea.entities;

import java.io.Serializable;
import java.util.Date;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String login;
	@NotNull
	private String password;
	private String nombre;
	@NotNull
	private Double cliente;
	private String email;
	@NotNull
	@Column(name ="fecha_alta",columnDefinition = "DATE")
	private Date fechaAlta;
	@Column(name ="fecha_baja",columnDefinition = "DATE")
	private Date fechaBaja;
	@NotNull
	@Column(length = 1,columnDefinition="char(1)")
	private String  estatus;
	@NotNull
	private Double intentos;
	@Column(name ="fecha_revocado",columnDefinition = "DATE")
	private Date fechaRevocado;
	@Column(name ="fecha_vigencia",columnDefinition = "DATE")
	private Date fechaVigencia;
	@Column(name ="no_acceso")
	private Integer noAcceso;
	@Column(name ="apellido_paterno")
	private String apellidoPaterno;
	@Column(name ="apellido_materno")
	private String apellidoMaterno;
	private Integer area;
	@NotNull
	@Column(name ="fecha_modificacion",columnDefinition = "DATE")
	private Date fechaModificacion;
	
	public Usuario() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getCliente() {
		return cliente;
	}
	public void setCliente(Double cliente) {
		this.cliente = cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public Double getIntentos() {
		return intentos;
	}
	public void setIntentos(Double intentos) {
		this.intentos = intentos;
	}
	public Date getFechaRevocado() {
		return fechaRevocado;
	}
	public void setFechaRevocado(Date fechaRevocado) {
		this.fechaRevocado = fechaRevocado;
	}
	public Date getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}
	public Integer getNoAcceso() {
		return noAcceso;
	}
	public void setNoAcceso(Integer noAcceso) {
		this.noAcceso = noAcceso;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	

}
