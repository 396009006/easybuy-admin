package cn.situ.action;

import cn.situ.bean.JsonModel;
import cn.situ.bean.Keywords;
import cn.situ.bean.PageBean;
import cn.situ.dao.IKeywordsDao;
import cn.situ.service.IKeyWordService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("json-default") //�̳а�
public class KeyWordAction extends ActionSupport implements ModelDriven<Keywords> {

    @Resource(name = "keyWordService")
    private IKeyWordService keyWordService;

    private Keywords keywords = new Keywords();

        private JsonModel jsonModel;

        public JsonModel getJsonModel() {
            return jsonModel;
        }

        public void setJsonModel(JsonModel jsonModel) {
            this.jsonModel = jsonModel;
        }

    public KeyWordAction(){
        jsonModel = new JsonModel();
    }

    /**
     * ��ȡȫ���ؼ�����Ϣ
     * @return
     */
    @Action(value = "/keyword/list",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String getKeyWordList(){
        //��ȡ�������
        HttpServletRequest request = ServletActionContext.getRequest();
        //��ȡ��������Ĳ���
        String page = request.getParameter("page");//��ȡ��ǰҳ��
        //�ж�Page��Ϊ��ʱ
        if (!(page == null || "".equals(page))) {
            //���ùؼ���service�е�findKeyWord����
            //��ѯȫ������ҳ
            PageBean<Keywords> keyWord = keyWordService.findKeyWord(Integer.parseInt(page));
            jsonModel.setCode(200);
            jsonModel.setMessage("success");
            jsonModel.setDate(keyWord);
            return SUCCESS;
        }
        jsonModel.setCode(400);
        jsonModel.setMessage("���������");
        return SUCCESS;
    }

    /**
     * �޸Ĺؼ���
     * @return
     */
    @Action(value = "/keyword/update",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String updateKeyWord(){
        if(getModel()!=null){
            //�����޸ķ�������Boolean
            boolean flag = keyWordService.update(getModel());
            if(flag){
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
     * ɾ��
     * @return
     */
    @Action(value = "/keyword/delete",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String deleteKeyWord(){
        if(getModel()!=null){
            boolean flag = keyWordService.delete(getModel());
            if(flag){
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
     * ����
     * @return
     */
    @Action(value = "/keyword/insert",results=@Result(type="json",name="success",params={"root","jsonModel","excludeNullProperties","false","noCache","true"}))
    public String insertKeyWord(){
        if(getModel()!=null){
            boolean flag = keyWordService.insert(getModel());
            if(flag){
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
    @Override
    public Keywords getModel() {
        return keywords;
    }
}
