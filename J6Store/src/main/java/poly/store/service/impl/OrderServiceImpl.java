package poly.store.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import poly.store.dao.OrderDao;
import poly.store.dao.OrderDetailDao;
import poly.store.entity.Order;
import poly.store.service.OrderService;
import poly.store.entity.OrderDetail;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao odao;
	
	@Autowired
	OrderDetailDao ddao;

	@Override
	public Order create(JsonNode orderData) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		
		odao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		
		ddao.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return odao.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		// TODO Auto-generated method stub
		return odao.findByUsername(username);
	}
	
	
}
