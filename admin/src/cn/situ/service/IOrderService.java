package cn.situ.service;

import cn.situ.bean.OrderInfo;
import cn.situ.bean.OrderProduct;
import cn.situ.bean.PageBean;

import java.util.List;

public interface IOrderService {
    /**
     * ��ѯ���ն���
     * @return
     */
    PageBean<OrderInfo> findToday(Integer currPage, OrderInfo info,String beginTime,String endTime);

    /**
     * ���ݶ����Ų�ѯ���������Ʒ
     * @param info
     * @return
     */
    List<OrderProduct> findDetailedOrder(OrderInfo info);

    /**
     * �������Ÿ���
     * @param info
     * @return
     */
    boolean update(OrderInfo info);

    /**
     * ��ʷ����
     * @param currPage
     * @param info
     * @return
     */
    PageBean<OrderInfo> findHistory(Integer currPage, OrderInfo info,String beginTime,String endTime);


    List<OrderInfo> findRecently();
}
