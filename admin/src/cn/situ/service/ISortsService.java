package cn.situ.service;

import cn.situ.bean.LevelSort;
import cn.situ.bean.PageBean;
import cn.situ.bean.Sorts;

import java.util.List;

public interface ISortsService {
    /**
     * ��ѯȫ��
     * @return
     */
    List<Sorts> findAll();

    /**
     * ��ҳ��ѯ
     * @param currPage
     * @return
     */
    PageBean<Sorts> getSortsList(Integer currPage);

    /**
     * ����һ������
     * @param sorts
     * @return
     */
    boolean update(Sorts sorts);

    /**
     * ɾ��һ������
     * @param sorts
     * @return
     */
    boolean delete(Sorts sorts);

    /**
     * ���¶�������
     * @param levelSort
     * @return
     */
    boolean update(LevelSort levelSort);

    /**
     * ɾ����������
     * @param levelSort
     * @return
     */
    boolean delete(LevelSort levelSort);

    /**
     * �������ౣ��
     * @param levelSort
     * @return
     */
    boolean insert(LevelSort levelSort);

    /**
     * һ�����ౣ��
     * @param sorts
     * @return
     */
    boolean insert(Sorts sorts);
}
