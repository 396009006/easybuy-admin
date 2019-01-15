package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.Manager;
import cn.situ.bean.PageBean;
import cn.situ.bean.Users;
import cn.situ.dao.IUsersDao;
import cn.situ.service.IUsersService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //�̳а�
public class UsersAction extends ActionSupport implements ModelDriven<Users>{

    private JsonModel jsonModel;

    @Resource(name = "usersService")
    private IUsersService usersService;

    private Users users = new Users();

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public UsersAction(){
        jsonModel = new JsonModel();
    }

    /**
     * �û��б�
     *
     * @return
     */
    @Action(value = "/user/list",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getUserList(){
        //��ȡrequest����
        HttpServletRequest request = ServletActionContext.getRequest();
        //��ȡ�ͻ��˷��͹����Ĳ���
        String page = request.getParameter("page");//ҳ��
        String value = request.getParameter("value");
        String select = request.getParameter("select");

        //�ж�page��Ϊ��
        if(!(page==null||"".equals(page))){
            Users u = null;
            //�ж�value��Ϊ��
            if(!(value==null||"".equals(value))) {
                u = new Users();
                //�ж�ѡ�� ����/�ֻ�
                if("uName".equals(select)){
                    u.setuName(value);
                }else{
                    u.setuPhone(value);
                }
            }
            PageBean<Users> userList = usersService.getUserList(Integer.parseInt(page), u);

            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            jsonModel.setDate(userList);
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("���������");
        return SUCCESS;
    }

    /**
     * ���/�ָ� �û�
     * @return
     */
    @Action(value = "/user/state",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","false"}))
    public String setUserState(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        if(!(id==null||"".equals(id))){
            Users u = new Users();
            u.setuId(Integer.parseInt(id));
            boolean flag = usersService.setUserState(u);
            if(flag){//true
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("����ʧ��");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("���������");
        return SUCCESS;
    }

    /**
     * �޸��û�
     * @return
     */
    @Action(value = "/user/update",results = @Result(type = "json",name = "success",params = {"root","jsonModel","excludeNullProperties","false","noCache","false"}))
    public String updateUser(){
        boolean flag = usersService.update(getModel());
        if(flag){
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            return SUCCESS;
        }
        jsonModel.setCode(101);
        jsonModel.setMessage("����ʧ��");
        return SUCCESS;
    }

    @Override
    public Users getModel() {
        return users;
    }
}
