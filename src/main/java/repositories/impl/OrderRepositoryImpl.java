package repositories.impl;

import common.patterns.servicelocator.ServiceLocator;
import databases.Database;
import exceptions.NotFoundException;
import exceptions.ResourceAlreadyExistsException;
import models.entities.Order;
import repositories.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class OrderRepositoryImpl implements OrderRepository {
    private SortedSet<Order> orders = new TreeSet<>();
    private final Database database;

    public OrderRepositoryImpl() {
        database = ServiceLocator.getService(Database.class.getName());
        this.getAll();
    }

    @Override
    public List<Order> getAll() {
        List<Order> ordersFromDb = this.database.readData(Order.class);
        this.orders = new TreeSet<>(ordersFromDb);
        return orders.stream().toList();
    }

    @Override
    public Optional<Order> getById(int id) throws NotFoundException {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    @Override
    public Order create(Order order) throws ResourceAlreadyExistsException {
        order.setId(this.generateId());
        this.orders.add(order);

        this.save();

        return order;
    }

    @Override
    public Order update(Order order) throws NotFoundException {
        Order existedOrder = orders.stream().filter(o -> o.getId() == order.getId()).findFirst().orElse(null);

        if (existedOrder == null) {
            throw new NotFoundException("Order with id " + order.getId() + " does not exist");
        }

        orders.remove(existedOrder);
        orders.add(order);

        this.save();

        return order;
    }

    @Override
    public void deleteById(int id) throws NotFoundException {
        Order existedOrder = this.orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
        if (existedOrder == null) {
            throw new NotFoundException("Menu with id " + id + " does not exist");
        }

        this.orders.remove(existedOrder);

        this.save();
    }

    public SortedSet<Order> getOrders() {
        return orders;
    }

    public void setOrders(SortedSet<Order> orders) {
        this.orders = orders;
    }

    private void save() {
        this.database.saveAll(this.orders.stream().toList(), Order.class);
    }

    private int generateId() {
        return orders.isEmpty() ? 1 : orders.last().getId() + 1;
    }
}
