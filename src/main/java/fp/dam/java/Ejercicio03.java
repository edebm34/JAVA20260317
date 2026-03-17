package fp.dam.java;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.dam.java.model.Order;
import fp.dam.java.model.OrderDetail;
import fp.dam.java.model.Product;
import fp.dam.java.model.ProductLine;

public class Ejercicio03 {

	static final List<Product> products;
	static final List<ProductLine> productLines;
	static final List<Order> orders;
	static final List<OrderDetail> orderDetails;

	static {
		try (Stream<String> lines = Files
				.lines(Path.of(Ejercicio03.class.getResource("/classicmodels/products.csv").toURI()))) {
			products = lines.collect(Collectors.mapping(Product::new, Collectors.toCollection(LinkedList::new)));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
		try (Stream<String> lines = Files
				.lines(Path.of(Ejercicio03.class.getResource("/classicmodels/productlines.csv").toURI()))) {
			productLines = lines
					.collect(Collectors.mapping(ProductLine::new, Collectors.toCollection(LinkedList::new)));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
		try (Stream<String> lines = Files
				.lines(Path.of(Ejercicio03.class.getResource("/classicmodels/orders.csv").toURI()))) {
			orders = lines.collect(Collectors.mapping(Order::new, Collectors.toCollection(LinkedList::new)));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
		try (Stream<String> lines = Files
				.lines(Path.of(Ejercicio03.class.getResource("/classicmodels/orderdetails.csv").toURI()))) {
			orderDetails = lines
					.collect(Collectors.mapping(OrderDetail::new, Collectors.toCollection(LinkedList::new)));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	static Map<String, Double> mediaPVPR(List<Product> products) {
		return products.stream().collect(
				Collectors.groupingBy(Product::getProductLine, Collectors.averagingDouble(p -> p.getBuyPrice())));
	}

	static Map<Integer, Double> mediaImportes(List<OrderDetail> orderDetails) {
		return orderDetails.stream()
				// .mapToDouble(o -> o.getPriceEach() * o.getQuantityOrdered())
				.collect(Collectors.groupingBy(OrderDetail::getOrderNumber,
						Collectors.averagingDouble(o -> o.getPriceEach() * o.getQuantityOrdered())));
	}

//	static String mayorProveedor(List<Product> products) {
//		return products
//				.stream()
//				.max(Product::getVendor);
//	}

	static Map<String, Double> totalVentas(List<Product> products) {
		return products.stream()
				.collect(Collectors.groupingBy(Product::getVendor, Collectors.summingDouble(Product::getBuyPrice)));
	}

//	static LinkedList<OrderDetail> ejercicio3d(List<Product> products) {
//		return products
//				.stream()
//				.filter(p -> p.getProductLine().equalsIgnoreCase("trains") || p.getProductLine().equalsIgnoreCase("ships"))
//				.map(p -> p.getCode())
//				.collect(
//						Collectors.toCollection(LinkedList::new));
//	}
	public static void main(String[] args) {
		mediaPVPR(products).entrySet().forEach(IO::println);
		totalVentas(products).entrySet().forEach(IO::println);
		mediaImportes(orderDetails).entrySet().forEach(IO::println);
	}

}
