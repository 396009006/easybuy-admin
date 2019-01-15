package cn.situ.service;

import cn.situ.bean.PageBean;
import cn.situ.bean.Users;

import java.util.List;

public interface IUsersService {
    /**
     * ��ҳ
     * @param currPage
     * @param u
     * @return
     */
    PageBean<Users> getUserList(Integer currPage, Users u);

    /**
     * ����״̬
     * @param u
     * @return
     */
    boolean setUserState(Users u);

    /**
     * ��������
     * @param u
     * @return
     */
    boolean update(Users u);
}
