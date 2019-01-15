package cn.situ.service;

import cn.situ.bean.PageBean;
import cn.situ.bean.Products;

public interface IProductsService {
    /**
     * ��ҳ
     * @param currPage ��ǰҳ
     * @param products ��ҳ������
     * @return
     */
    PageBean<Products> getProductsList(Integer currPage, Products products);

    /**
     * �ϼ�/�¼� ��Ʒ
     * @param products
     * @return
     */
    boolean setProductsState(Products products);

    /**
     * ������Ʒ
     * @param products
     * @return
     */
    boolean updateProducts(Products products);

    boolean addProducts(Products products);
}
