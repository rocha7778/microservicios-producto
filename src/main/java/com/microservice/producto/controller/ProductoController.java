package com.microservice.producto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.producto.model.Producto;
import com.microservice.producto.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
//	@Autowired
//	private QueueMessagingTemplate queueMessagingTemplate;
	
	@Autowired
	private  NotificationMessagingTemplate notificationMessagingTemplate;

	
	@Value("${cloud.aws.end-point.uri}")
	private String sqsEndpoint;

	@GetMapping("/productos")
	public List<Producto> getProductos() {
		return productoService.findAll();
	}
	
	@GetMapping("/productos/paginations")
	public Page<Producto> getAllPagination(Pageable pageable ) {
		return productoService.getAll(pageable);
	}

	@GetMapping("/productos/{id}")
	public Producto getProductoById(@PathVariable Long id) throws Exception {
		return productoService.findById(id);
	}
	
	
	@PostMapping("/productos")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto saveProducto(@RequestBody Producto producto) {
		 Producto p  = productoService.save(producto);
		//queueMessagingTemplate.convertAndSend(sqsEndpoint,p);
		notificationMessagingTemplate.convertAndSend("producto_events", p);
		return producto;
	}
	
	@PutMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Producto upDateProducto(@PathVariable Long id,@RequestBody Producto producto){
		Producto productoDB = productoService.findById(id);
		productoDB.setNombre(producto.getNombre());
		productoDB.setPrecio(producto.getPrecio());
		return productoService.save(productoDB);
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
