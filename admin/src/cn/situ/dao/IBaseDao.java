package cn.situ.dao;

import cn.situ.bean.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
    /**
     * ��ѯȫ��
     */
    List<T> findAll();

    /**
     * ������ѯ
     * @param criteria
     * @return
     */
    T find(DetachedCriteria criteria);


    /**
     * ��ȡ������
     * @param criteria
     * @return
     */
     Integer getCount(DetachedCriteria criteria);

    /**
     * ��ҳ
     * @param criteria
     * @param pageBean
     * @param begin
     * @param end
     * @return
     */
     PageBean<T> getList(DetachedCriteria criteria,PageBean<T> pageBean,Integer begin,Integer end);

    /**
     * ����������ѯ
     * @param id
     * @return
     */
     T find(Serializable id);

    /**
     * ����
     * @param t
     * @return
     */
     boolean update(T t);

    /**
     * ����
     * @param t
     * @return
     */
     boolean save(T t);

    /**
     * ��ѯȫ��������
     * @param detachedCriteria
     * @return
     */
     List<T> findList(DetachedCriteria detachedCriteria);

    /**
     * ��ѯ����
     * @param obj
     * @param id
     * @return
     */
     T get(String obj,Serializable id);

     boolean delete(T t);
}
