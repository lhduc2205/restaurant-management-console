package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.enums.PaymentStatus;
import com.lhduc.common.filtered.ConditionCreator;
import com.lhduc.common.filtered.FilterCondition;
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
    private final MenuItemController menuItemController;
    private final ConditionCreator<OrderDto> conditionCreator;

    public OrderConSoleView() {
        this.orderController = new OrderController();
        this.menuItemController = new MenuItemController();
        this.conditionCreator = new ConditionCreator<>(OrderDto.class);
    }

    @Override
    protected String getOptionTitle() {
        return "Order";
    }

    @Override
    protected void doAction(CrudOption option) {
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
            case FILTER: {
                this.filterOrder();
                break;
            }
        }
    }

    private void showOrder() {
        OrderDisplayUtil.displayOrder(this.orderController.getAll());
    }

    private void createOrder() {
        final List<MenuItemDto> menuItems = menuItemController.getAll();
        MenuDisplayUtil.displayMenuItem(menuItems);

        if (!menuItems.isEmpty()) {
            List<OrderDetailDto> orderDetails = createOrderDetails(getMenuItemQuantityMap());
            this.placeOrder(orderDetails);
        }
    }

    private void placeOrder(List<OrderDetailDto> orderDetails) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderDetail(orderDetails);

        try {
            orderController.create(orderDto);
            System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private Map<MenuItemDto, Integer> getMenuItemQuantityMap() {
        Map<MenuItemDto, Integer> menuItemQuantityMap = new HashMap<>();

        while (true) {
            try {
                int menuItemId = UserInputUtil.enterInteger(MessageConstant.ENTER_MENU_ITEM_ID);
                int quantity = UserInputUtil.enterInteger(MessageConstant.ENTER_QUANTITY);
                MenuItemDto menuItemDto = menuItemController.getById(menuItemId);

                menuItemQuantityMap.put(menuItemDto, menuItemQuantityMap.getOrDefault(menuItemDto, 0) + quantity);

                if (!UserInputUtil.getUserChoiceForYesNoOption(MessageConstant.WANT_ADD_MORE_ITEM)) {
                    break;
                }
            } catch (NotFoundException e) {
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
        this.showOrder();
        int orderId = UserInputUtil.enterInteger(MessageConstant.ENTER_ORDER_ID);

        try {
            OrderDto orderDto = orderController.getById(orderId);

            if (orderDto.getPaymentStatus().isNotEditable()) {
                System.out.println(MessageConstant.UNABLE_UPDATE_ORDER);
                return;
            }

            OrderDisplayUtil.displayPaymentStatus();
            int paymentStatusOption = UserInputUtil.enterInteger(MessageConstant.CHOOSE_PAYMENT_STATUS, PaymentStatus.values().length);
            PaymentStatus paymentStatus = PaymentStatus.values()[paymentStatusOption - 1];

            orderDto.setPaymentStatus(paymentStatus);
            orderController.update(orderDto);

            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);

        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOrder() {
        this.showOrder();
        int orderId = UserInputUtil.enterInteger(MessageConstant.ENTER_ORDER_ID);

        try {
            orderController.deleteById(orderId);
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterOrder() {
        this.showOrder();
        FilterCondition condition = conditionCreator.createConditions();
        List<OrderDto> orders = orderController.getAll(condition);
        OrderDisplayUtil.displayOrder(orders);
    }
}
