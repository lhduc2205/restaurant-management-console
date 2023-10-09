package com.lhduc.view;

import com.lhduc.common.constant.MessageConstant;
import com.lhduc.common.enums.CrudOption;
import com.lhduc.common.filtered.ConditionCreator;
import com.lhduc.common.filtered.FilterCondition;
import com.lhduc.controller.MenuItemController;
import com.lhduc.controller.OrderController;
import com.lhduc.controller.OrderDetailController;
import com.lhduc.exception.NotFoundException;
import com.lhduc.exception.OperationForbiddenException;
import com.lhduc.exception.ResourceAlreadyExistsException;
import com.lhduc.model.dto.MenuItemDto;
import com.lhduc.model.dto.OrderDetailDto;
import com.lhduc.model.dto.OrderDto;
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
        int orderId = UserInputUtil.enterInteger(MessageConstant.ENTER_ORDER_ID);
        int menuItemId = UserInputUtil.enterInteger(MessageConstant.ENTER_MENU_ITEM_ID);
        int quantity = UserInputUtil.enterInteger(MessageConstant.ENTER_QUANTITY);

        try {
            orderController.getById(orderId);
            MenuItemDto menuItemDto = menuItemController.getById(menuItemId);
            orderDetailController.create(new OrderDetailDto(orderId, menuItemDto, quantity));

            System.out.println(MessageConstant.CREATED_SUCCESSFULLY);
        } catch (OperationForbiddenException | ResourceAlreadyExistsException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateOderDetail() {
        this.showOrderDetail();
        try {
            int orderId = UserInputUtil.enterInteger(MessageConstant.ENTER_ORDER_ID);

            int menuItemId = UserInputUtil.enterInteger(MessageConstant.ENTER_MENU_ITEM_ID);
            MenuItemDto existedMenuItem = menuItemController.getById(menuItemId);

            int quantity = UserInputUtil.enterInteger(MessageConstant.ENTER_QUANTITY);
            OrderDetailDto existedOrderDetail = orderDetailController.get(orderId, menuItemId);

            existedOrderDetail.setMenuItemId(menuItemId);
            existedOrderDetail.setMenuItem(existedMenuItem);
            existedOrderDetail.setQuantity(quantity);

            orderDetailController.update(existedOrderDetail);
            System.out.println(MessageConstant.UPDATED_SUCCESSFULLY);
        } catch (OperationForbiddenException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOderDetail() {
        this.showOrderDetail();
        int orderId = UserInputUtil.enterInteger(MessageConstant.ENTER_ORDER_ID);
        int menuItemId = UserInputUtil.enterInteger(MessageConstant.ENTER_MENU_ITEM_ID);

        try {
            orderDetailController.delete(orderId, menuItemId);
            System.out.println(MessageConstant.DELETED_SUCCESSFULLY);
        } catch (OperationForbiddenException | NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void filterOrderDetail() {
        this.showOrderDetail();
        final FilterCondition conditions = conditionCreator.createConditions();
        List<OrderDetailDto> orderDetail = orderDetailController.getAll(conditions);

        OrderDisplayUtil.displayOrderDetail(orderDetail);
    }
}
