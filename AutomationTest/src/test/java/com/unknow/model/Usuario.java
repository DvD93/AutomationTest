package com.unknow.model;

//DTO Usuario
public class Usuario {
	
	private int title;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String password;
	private int diaNac;
	private int mesNac;
	private int anioNac;
	private String direccion;
	private String cuidad;
	private int estado;
	private int codPostal;
	//optional parameters
	private String company;
	private int news;
	private int offers;
	private String fullAddress;
	private String info;
	private String telefono2;
	


	public Usuario(int title, String nombre, String apellido, String email, String telefono, String password, 
			int diaNac, int mesNac, int anioNac, String direccion, String ciudad, int estado, int codPostal) {
		super();
		this.title = title;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.diaNac = diaNac;
		this.mesNac = mesNac;
		this.anioNac = anioNac;
		this.direccion = direccion;
		this.cuidad = ciudad;
		this.estado = estado;
		this.codPostal = codPostal;
	}
	
	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
		
	}
	public Usuario(int title, String nombre, String apellido, String email, String telefono, String password, 
			int diaNac, int mesNac, int anioNac, String direccion, String ciudad, int estado, int codPostal,
			String company, int news, int offers, String fullAddress, String info, String telefono2) {
		this.title = title;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.diaNac = diaNac;
		this.mesNac = mesNac;
		this.anioNac = anioNac;
		this.direccion = direccion;
		this.cuidad = ciudad;
		this.estado = estado;
		this.codPostal = codPostal;
		this.news = news;
		this.offers = offers;
		this.fullAddress = fullAddress;
		this.info = info;
		this.telefono2 = telefono2;
		this.company = company;
	}
	
	public int getTitle(){
		return title;
	}
	
	public void setTitle(int title){
		this.title = title;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDiaNac() {
		return diaNac;
	}
	public void setDiaNac(int diaNac) {
		this.diaNac = diaNac;
	}
	public int getMesNac() {
		return mesNac;
	}
	public void setMesNac(int mesNac) {
		this.mesNac = mesNac;
	}
	public int getAnioNac() {
		return anioNac;
	}
	public void setAnioNac(int anioNac) {
		this.anioNac = anioNac;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCuidad() {
		return cuidad;
	}
	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	
	//optional
	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}

	public int getOffers() {
		return offers;
	}

	public void setOffers(int offers) {
		this.offers = offers;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}