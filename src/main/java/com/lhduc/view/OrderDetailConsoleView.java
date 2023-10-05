package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.filtered.ConditionCreator;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.controller.MenuItemController;
import com.lhduc.controller.OrderController;
import com.lhduc.controller.OrderDetailController;
import com.lhduc.exception.ApplicationRuntimeException;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.ResourceAlreadyExistsException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.dto.OrderDto;
import com.lhduc.model.entity.OrderDetail;
import com.lhduc.util.MenuDisplayUtil;
import com.lhduc.util.OrderDisplayUtil;
import com.lhduc.util.UserInputUtil;

import java.util.List;

public class OrderDetailConsoleView extends ConsoleViewTemplate {
    private final MenuItemController menuItemController;
    private final OrderDetailController orderDetailController;
    private final OrderController orderController;
    private final ConditionCreator<OrderDetailDto> conditionCreator;

    public OrderDetailConsoleView() {
        this.menuItemController = new MenuItemController();
        this.orderDetailController = new OrderDetailController();
        this.orderController = new OrderController();
        this.conditionCreator = new ConditionCreator<>(OrderDetailDto.class);
    }

    @Override
    protected String getOptionTitle() {
        return "Order Detail";
    }

    @Override
    protected void doAction(CrudOption option) {
        option.displayTitle(getOptionTitle());
        switch (option) {
            case SHOW: {
                this.showOrderDetail();
                break;
            }
            case CREATE: {
                this.createOrderDetail();
                break;
            }
            case UPDATE: {
                this.updateOderDetail();
                break;
            }
            case DELETE: {
                this.deleteOderDetail();
                break;
            }
            case FILTER: {
                this.filterOrderDetail();
                break;
            }
        }
    }

    private void showOrderDetail() {
        OrderDisplayUtil.displayOrderDetail(orderDetailController.getAll());
    }

    private void createOrderDetail() {
        int orderId = UserInputUtil.enterInteger("Enter order id");
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
        int quantity = UserInputUtil.enterInteger("Enter quantity");

        try {
            orderController.getById(orderId);
            MenuItemDto menuItemDto = menuItemController.getById(menuItemId);
            orderDetailController.create(new OrderDetailDto(orderId, menuItemDto, quantity));

            System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
        } catch (ResourceAlreadyExistsException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOderDetail() {
        try {
            int orderId = UserInputUtil.enterInteger("Enter order id");
            OrderDto existedOrder = orderController.getById(orderId);

            if (existedOrder.getPaymentStatus().isNotEditable()) {
                System.out.println(MessageConstant.UNABLE_UPDATE_ORDER);
                return;
            }

            int menuItemId = UserInputUtil.enterInteger("Enter menu item id");
            MenuItemDto existedMenuItem = menuItemController.getById(menuItemId);

            int quantity = UserInputUtil.enterInteger("Enter quantity");
            OrderDetailDto existedOrderDetail = orderDetailController.get(orderId, menuItemId);

            existedOrderDetail.setMenuItemId(menuItemId);
            existedOrderDetail.setMenuItem(existedMenuItem);
            existedOrderDetail.setQuantity(quantity);

            orderDetailController.update(existedOrderDetail);
            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOderDetail() {
        int orderId = UserInputUtil.enterInteger("Enter order id");
        int menuItemId = UserInputUtil.enterInteger("Enter menu item id");

        try {
            orderDetailController.delete(orderId, menuItemId);
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterOrderDetail() {
        final FilterCondition conditions = conditionCreator.createConditions();
        List<OrderDetailDto> orderDetail = orderDetailController.getAll(conditions);

        OrderDisplayUtil.displayOrderDetail(orderDetail);
    }
}
