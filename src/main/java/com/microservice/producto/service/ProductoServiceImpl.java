package com.microservice.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.producto.model.Producto;
import com.microservice.producto.repository.ProductoRepository;


@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private ProductoRepository productoDao;
	

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(new Producto());
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
		
	}

	@Override
	public Page<Producto> getAll(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

}
