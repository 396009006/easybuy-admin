package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.Manager;
import cn.situ.service.IManagerService;
import cn.situ.tools.JsonTools;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //�̳а�
public class ManagerAction extends ActionSupport {

    private JsonModel jsonModel;

    @Resource(name = "managerService")
    private IManagerService managerService;

    public JsonModel getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JsonModel jsonModel) {
        this.jsonModel = jsonModel;
    }

    public ManagerAction(){
        jsonModel = new JsonModel();
    }

    @Action(value="/login",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","true","noCache","true"}))
//root��ָ���ض��� excludeNullProperties ָȥ��json�����еĿ�ֵ ��noCache ָ�޻��棬��ֵ����ʽ����
    public String login() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String requestPostData = JsonTools.getRequestPostData(request);
        Manager manager;
        try {
            manager = new Gson().fromJson(requestPostData, Manager.class);
        }catch (Exception e){
            manager = null;
        }
        if(manager!=null) {
            Manager login = managerService.login(manager);
            if(login!=null) {
                jsonModel.setCode(200);
                jsonModel.setMessage("success");
                jsonModel.setDate(login);
                return SUCCESS;
            }
            jsonModel.setCode(101);
            jsonModel.setMessage("��ʾ���û������ڣ������������");
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("��ʾ�����������");
        return SUCCESS;
    }


}
