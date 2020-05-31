package com.microservice.producto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.microservice.producto.model.Producto;


public interface IProductoService {
	public List<Producto>findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);
	public Page<Producto> getAll(Pageable pageable);
}
