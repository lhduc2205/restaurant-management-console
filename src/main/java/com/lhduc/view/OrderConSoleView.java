package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.enums.PaymentStatus;
import com.lhduc.controller.MenuItemController;
import com.lhduc.controller.OrderController;
import com.lhduc.controller.OrderDetailController;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.ResourceAlreadyExistsException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.dto.OrderDto;
import com.lhduc.util.MenuDisplayUtil;
import com.lhduc.util.OrderDisplayUtil;
import com.lhduc.util.UserInputUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderConSoleView extends ConsoleViewTemplate {
    private final OrderController orderController;
    private final OrderDetailController orderDetailController;
    private final MenuItemController menuItemController;

    public OrderConSoleView() {
        this.orderController = new OrderController();
        this.orderDetailController = new OrderDetailController();
        this.menuItemController = new MenuItemController();
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
        List<OrderDetailDto> orderDetails = createOrderDetails(getMenuItemQuantityMap());
        this.placeOrder(orderDetails);
        System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
    }

    private void placeOrder(List<OrderDetailDto> orderDetails) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDetail(orderDetails);

        try {
            orderController.create(orderDto);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Map<MenuItemDto, Integer> getMenuItemQuantityMap() {
        Map<MenuItemDto, Integer> menuItemQuantityMap = new HashMap<>();

        MenuDisplayUtil.displayMenuItem(menuItemController.getAll());

        while (true) {
            try {
                int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
                int quantity = UserInputUtil.enterInteger("Enter quantity");
                MenuItemDto menuItemDto = menuItemController.getById(menuItemId);

                menuItemQuantityMap.put(menuItemDto, menuItemQuantityMap.getOrDefault(menuItemDto, 0) + quantity);

                if (!UserInputUtil.getUserChoiceForYesNoOption("Do you want to add more item? (y/n): ")) {
                    break;
                }
            } catch (ResourceAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
        }

        return menuItemQuantityMap;
    }

    private List<OrderDetailDto> createOrderDetails(Map<MenuItemDto, Integer> menuItemQuantityMap) {
        List<OrderDetailDto> orderDetails = new ArrayList<>();

        for (Map.Entry<MenuItemDto, Integer> entry : menuItemQuantityMap.entrySet()) {
            orderDetails.add(new OrderDetailDto(0, entry.getKey(), entry.getValue()));
        }

        return orderDetails;
    }

    private void updateOrder() {
        int orderId = UserInputUtil.enterInteger("Enter order id");

        try {
            OrderDto orderDto = orderController.getById(orderId);

            if (orderDto.getPaymentStatus().isNotEditable()) {
                System.out.println(MessageConstant.UNABLE_UPDATE_ORDER);
                return;
            }

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
