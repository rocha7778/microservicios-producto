package com.microservice.producto.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.microservice.producto.model.Producto;


public interface ProductoRepository extends PagingAndSortingRepository<Producto, Long>{

}
