package com.example.tpPersistencia;

import com.example.tpPersistencia.entidades.*;
import com.example.tpPersistencia.repositorios.*;
import com.example.tpPersistencia.util.Estado;
import com.example.tpPersistencia.util.FormaPago;
import com.example.tpPersistencia.util.Tipo;
import com.example.tpPersistencia.util.TipoEnvio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@SpringBootApplication
public class TpPersistenciaApplication {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	public static void main(String[] args) {
		SpringApplication.run(TpPersistenciaApplication.class, args);
		System.out.println("Estoy funcionando");
	}
	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, DomicilioRepository domicilioRepo) {
		return args -> {
			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			Rubro rubro1 = Rubro.builder()
					.denominacion("rubro1")
					.build();

			Producto producto1 = Producto.builder()
					.tipo(Tipo.INSUMO)
					.tiempoEstimadoCocina(2)
					.denominacion("Papas fritas")
					.precioVenta(900.00)
					.precioCompra(500.00)
					.stockActual(40)
					.stockMinimo(10)
					.unidadMedida("kg")
					.foto("/img/papasfritas.jpg")
					.receta("receta 1")
					.build();

			Producto producto2 = Producto.builder()
					.tipo(Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(4)
					.denominacion("Empanadas")
					.precioVenta(1000.00)
					.precioCompra(400.00)
					.stockActual(60)
					.stockMinimo(15)
					.unidadMedida("kg")
					.foto("/img/empanadas.jpg")
					.receta("receta 2")
					.build();

			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);

			rubroRepository.save(rubro1);

			productoRepository.save(producto1);
			productoRepository.save(producto2);

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(3800.00)
					.producto(producto1)
					.build();

			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(3000.00)
					.producto(producto2)
					.build();

			Pedido pedido1 = Pedido.builder()
					.fecha("12-09-2023")
					.estado(Estado.INICIADO)
					.horaEstimadaEntrega(LocalDateTime.of(2023, 9, 12, 15, 30))
					.tipoEnvio(TipoEnvio.retiro)
					.total(3400.00)
					.build();
			pedido1.agregarDetallePedido(detallePedido1);

			Pedido pedido2 = Pedido.builder()
					.fecha("12-09-2023")
					.estado(Estado.PREPARACION)
					.horaEstimadaEntrega(LocalDateTime.of(2023, 9, 12, 16, 30))
					.tipoEnvio(TipoEnvio.delivery)
					.total(4000.00)
					.build();
			pedido2.agregarDetallePedido(detallePedido2);

			Factura factura1 = Factura.builder()
					.fecha(formato.parse("12-09-2023"))
					.numero(2)
					.descuento(400.00)
					.formaPago(FormaPago.TRANSFERENCIA)
					.total(2900)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(formato.parse("12-09-2023"))
					.numero(3)
					.descuento(800.00)
					.formaPago(FormaPago.MERCADOPAGO)
					.total(4900)
					.build();

			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			Usuario usuario1 = Usuario.builder()
					.nombre("Juan")
					.password("qwerty123")
					.rol("cocinero")
					.build();
					usuario1.agregarPedidosUsuario(pedido1);
					usuario1.agregarPedidosUsuario(pedido2);

			Cliente cliente1 = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("261004589")
					.email("juanperez@gmail.com")
					.build();

			Cliente cliente2 = Cliente.builder()
					.nombre("Luis")
					.apellido("Prado")
					.telefono("261025549")
					.email("luisprado2@gmail.com")
					.build();

			cliente2.agregarPedidosCliente(pedido2);
			cliente2.agregarPedidosCliente(pedido1);

			Domicilio domicilio1 = Domicilio.builder()
					.calle("calle Salta")
					.numero("424")
					.localidad("San Martin")
					.cliente(cliente1)
					.build();
			domicilioRepository.save(domicilio1);
			domicilio1.setCliente(cliente1);

			domicilio1.agregarPedidosDomicilio(pedido1);
			domicilio1.agregarPedidosDomicilio(pedido2);

			usuarioRepository.save(usuario1);
			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);
			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);
			facturaRepository.save(factura1);
			facturaRepository.save(factura2);
			detallePedidoRepository.save(detallePedido1);
			detallePedidoRepository.save(detallePedido2);
			domicilioRepository.save(domicilio1);

			Producto productoRecuperado = productoRepository.findById(producto1.getId()).orElse(null);
			if (productoRecuperado != null){
				System.out.println("Producto 1");
				System.out.println("Tipo: "+ productoRecuperado.getTipo());
				System.out.println("Tiempo estimado cocina: " + productoRecuperado.getTiempoEstimadoCocina());
				System.out.println("Denominacion: "+ productoRecuperado.getDenominacion());
				System.out.println("Precio Venta: "+ productoRecuperado.getPrecioVenta());
				System.out.println("Precio Compra: "+ productoRecuperado.getPrecioCompra());
				System.out.println("Stock minimo: " + productoRecuperado.getStockMinimo());
				System.out.println("Stock actual: " + productoRecuperado.getStockActual());
				System.out.println("Unidad de medida: " + productoRecuperado.getUnidadMedida());
				System.out.println("Foto: " + productoRecuperado.getFoto());
				System.out.println("Receta: "+productoRecuperado.getReceta());
			}
			Producto productoRecuperado2 = productoRepository.findById(producto2.getId()).orElse(null);
			if (productoRecuperado2 != null){
				System.out.println("Producto 2");
				System.out.println("Tipo: "+ productoRecuperado2.getTipo());
				System.out.println("Tiempo estimado cocina: " + productoRecuperado2.getTiempoEstimadoCocina());
				System.out.println("Denominacion: "+ productoRecuperado2.getDenominacion());
				System.out.println("Precio Venta: "+ productoRecuperado2.getPrecioVenta());
				System.out.println("Precio Compra: "+ productoRecuperado2.getPrecioCompra());
				System.out.println("Stock minimo: " + productoRecuperado2.getStockMinimo());
				System.out.println("Stock actual: " + productoRecuperado2.getStockActual());
				System.out.println("Unidad de medida: " + productoRecuperado2.getUnidadMedida());
				System.out.println("Foto: " + productoRecuperado2.getFoto());
				System.out.println("Receta: "+productoRecuperado2.getReceta());
			}

			Usuario usuarioRecuperado = usuarioRepository.findById(usuario1.getId()).orElse(null);

			if (usuarioRecuperado != null) {
				System.out.println("Usuario 1");
				System.out.println("Nombre: " + usuarioRecuperado.getNombre());
				System.out.println("Password: " + usuarioRecuperado.getPassword());
				System.out.println("Rol: " + usuarioRecuperado.getRol());
				System.out.println("Pedidos: " + usuarioRecuperado.getPedidosUsuario());
			}

			Pedido pedidoRecuperado = pedidoRepository.findById(pedido1.getId()).orElse(null);

			if (pedidoRecuperado != null) {
				System.out.println("Pedido 1");
				System.out.println("Fecha: " + pedidoRecuperado.getFecha());
				System.out.println("Estado: " + pedidoRecuperado.getEstado());
				System.out.println("Hora aprox entrega: " + pedidoRecuperado.getHoraEstimadaEntrega());
				System.out.println("Tipo de envio: " + pedidoRecuperado.getTipoEnvio());
				System.out.println("Total: " + pedidoRecuperado.getTotal());
				System.out.println("Factura: "+ pedidoRecuperado.getFactura());
				System.out.println("Detalle Pedido: " + pedidoRecuperado.getPedidos());
			}

			Pedido pedidoRecuperado2 = pedidoRepository.findById(pedido2.getId()).orElse(null);

			if (pedidoRecuperado != null) {
				System.out.println("Pedido 2");
				System.out.println("Fecha: " + pedidoRecuperado2.getFecha());
				System.out.println("Estado: " + pedidoRecuperado2.getEstado());
				System.out.println("Hora aprox entrega: " + pedidoRecuperado2.getHoraEstimadaEntrega());
				System.out.println("Tipo de envio: " + pedidoRecuperado2.getTipoEnvio());
				System.out.println("Total: " + pedidoRecuperado2.getTotal());
				System.out.println("Factura: "+ pedidoRecuperado2.getFactura());
				System.out.println("Detalle Pedido: " + pedidoRecuperado2.getPedidos());
			}

			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("Cliente 1");
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				System.out.println("Pedidos" + clienteRecuperado.getPedidosCliente());
			}
			Cliente clienteRecuperado2 = clienteRepository.findById(cliente2.getId()).orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("Cliente 2");
				System.out.println("Nombre: " + clienteRecuperado2.getNombre());
				System.out.println("Apellido: " + clienteRecuperado2.getApellido());
				System.out.println("Telefono: " + clienteRecuperado2.getTelefono());
				System.out.println("Email: " + clienteRecuperado2.getEmail());
				System.out.println("Pedidos" + clienteRecuperado2.getPedidosCliente());

			}

			Factura facturaRecuperado = facturaRepository.findById(factura1.getId()).orElse(null);

			if (facturaRecuperado != null) {
				System.out.println("Factura 1");
				System.out.println("Fecha: " + facturaRecuperado.getFecha());
				System.out.println("Numero: " + facturaRecuperado.getNumero());
				System.out.println("Descuento: " + facturaRecuperado.getDescuento());
				System.out.println("Forma de pago: " + facturaRecuperado.getFormaPago());
				System.out.println("Total: " + facturaRecuperado.getTotal());
			}
			Factura facturaRecuperado2 = facturaRepository.findById(factura2.getId()).orElse(null);

			if (facturaRecuperado != null) {
				System.out.println("Factura 1");
				System.out.println("Fecha: " + facturaRecuperado2.getFecha());
				System.out.println("Numero: " + facturaRecuperado2.getNumero());
				System.out.println("Descuento: " + facturaRecuperado2.getDescuento());
				System.out.println("Forma de pago: " + facturaRecuperado2.getFormaPago());
				System.out.println("Total: " + facturaRecuperado2.getTotal());
			}
			DetallePedido detallePedidoRecuperado = detallePedidoRepository.findById(detallePedido1.getId()).orElse(null);

			if (detallePedidoRecuperado != null) {
				System.out.println("Detalle Pedido 1");
				System.out.println("Cantidad: " + detallePedidoRecuperado.getCantidad());
				System.out.println("Prpducto: " + detallePedidoRecuperado.getProducto());
				System.out.println("SubTotal: " + detallePedidoRecuperado.getSubtotal());
			}

			DetallePedido detallePedidoRecuperado2 = detallePedidoRepository.findById(detallePedido2.getId()).orElse(null);

			if (detallePedidoRecuperado2 != null) {
				System.out.println("Detalle Pedido 1");
				System.out.println("Cantidad: " + detallePedidoRecuperado2.getCantidad());
				System.out.println("Prpducto: " + detallePedidoRecuperado2.getProducto());
				System.out.println("SubTotal: " + detallePedidoRecuperado2.getSubtotal());
			}
			Domicilio domicilioRecuperado = domicilioRepository.findById(domicilio1.getId()).orElse(null);

			if (domicilioRecuperado != null) {
				System.out.println("Domicilio 1");
				System.out.println("Calle: " + domicilioRecuperado.getCalle());
				System.out.println("Numero: " + domicilioRecuperado.getNumero());
				System.out.println("Localidad: " + domicilioRecuperado.getLocalidad());
			}

		};
	}
}