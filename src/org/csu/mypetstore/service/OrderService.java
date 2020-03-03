package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.ItemDao;
import org.csu.mypetstore.persistence.OrderDAO;
import org.csu.mypetstore.persistence.LineItemDAO;
import org.csu.mypetstore.persistence.SequenceDAO;
import org.csu.mypetstore.persistence.impl.ItemDaoImpl;
import org.csu.mypetstore.persistence.impl.LineItemDAOlmpl;
import org.csu.mypetstore.persistence.impl.OrderDAOlmpl;
import org.csu.mypetstore.persistence.impl.SequenceDAOlmpl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class OrderService {

    private ItemDao itemDAO;
    private OrderDAO orderDAO;
    private SequenceDAO sequenceDAO;
    private LineItemDAO lineitemDAO;

    public OrderService()
    {
        itemDAO = new ItemDaoImpl();
        orderDAO = new OrderDAOlmpl();
        lineitemDAO = new LineItemDAOlmpl();
        sequenceDAO = new SequenceDAOlmpl();
    }


    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemDAO.updateInventoryQuantity(param);
        }

        orderDAO.insertOrder(order);
        orderDAO.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineitemDAO.insertLineItem(lineItem);
        }
    }


    public Order getOrder(int orderId) {
        Order order = orderDAO.getOrder(orderId);
        order.setLineItems(lineitemDAO.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDAO.getItem(lineItem.getItemId());
            item.setQuantity(itemDAO.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDAO.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) sequenceDAO.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        if(sequenceDAO.updateSequence(parameterObject)){
            return parameterObject.getNextId();
        }else {
            throw new RuntimeException("Can't updateSequence!");
        }

    }
}
