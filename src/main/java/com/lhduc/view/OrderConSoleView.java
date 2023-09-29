package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.enums.PaymentStatus;
import com.lhduc.common.pattern.servicelocator.ServiceLocator;
import com.lhduc.controller.MenuItemController;
import com.lhduc.controller.OrderController;
import com.lhduc.controller.OrderDetailController;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.ResourceAlreadyExistsException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.dto.OrderDto;
import com.lhduc.util.OrderDisplayUtil;
import com.lhduc.util.UserInputUtil;

public class OrderConSoleView extends ConsoleViewTemplate {
    private final OrderController orderController;
    private final OrderDetailController orderDetailController;
    private final MenuItemController menuItemController;

    public OrderConSoleView() {
        this.orderController = ServiceLocator.getService(OrderController.class);
        this.orderDetailController = ServiceLocator.getService(OrderDetailController.class);
        this.menuItemController = ServiceLocator.getService(MenuItemController.class);
    }

    @Override
    protected String getOptionTitle() {
        return "Order";
    }

    @Override
    protected void doAction(CrudOption option) {
        option.displayTitle(getOptionTitle());
        switch (option) {
            case SHOW: {
                this.showOrder();
                break;
            }
            case CREATE: {
                this.createOrder();
                break;
            }
            case UPDATE: {
                this.updateOrder();
                break;
            }
            case DELETE: {
                this.deleteOrder();
                break;
            }
        }
    }

    private void showOrder() {
        OrderDisplayUtil.displayOrder(this.orderController.getAll());
    }

    private void createOrder() {
        try {
            OrderDto createdOrder = orderController.create(new OrderDto());

            int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
            int quantity = UserInputUtil.enterInteger("Enter quantity");
            MenuItemDto menuItem = menuItemController.getById(menuItemId);

            OrderDetailDto orderDetail = orderDetailController.create(new OrderDetailDto(createdOrder.getId(), menuItem, quantity));
            orderDetail.setMenuItem(menuItem);

            createdOrder.addOrderDetail(orderDetail);
            OrderDisplayUtil.displayOrder(createdOrder);

            System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
        } catch (ResourceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOrder() {
        int orderId = UserInputUtil.enterInteger("Enter order id");

        try {
            OrderDto orderDto = orderController.getById(orderId);
            OrderDisplayUtil.displayPaymentStatus();
            int paymentStatusOption = UserInputUtil.enterInteger("Choose payment status", PaymentStatus.values().length);
            PaymentStatus paymentStatus = PaymentStatus.values()[paymentStatusOption - 1];

            orderDto.setPaymentStatus(paymentStatus);
            orderController.update(orderDto);

            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);

        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOrder() {
        int orderId = UserInputUtil.enterInteger("Enter order id");

        try {
            orderController.deleteById(orderId);
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
