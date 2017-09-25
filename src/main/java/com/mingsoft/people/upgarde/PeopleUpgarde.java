package com.mingsoft.people.upgarde;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mingsoft.base.entity.ResultJson;
import com.mingsoft.basic.biz.IModelBiz;
import com.mingsoft.basic.biz.IRoleModelBiz;
import com.mingsoft.basic.entity.ModelEntity;
import com.mingsoft.basic.entity.RoleModelEntity;
import com.mingsoft.mdiy.action.BaseAction;
import com.mingsoft.util.AESUtil;
import com.mingsoft.util.StringUtil;

import net.mingsoft.base.util.PropertiesUtil;
import net.mingsoft.base.util.SpringUtil;

public class PeopleUpgarde  extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7575749647205573895L;
	
	public ResultJson setup(){
		ResultJson result = new ResultJson();
		
		//检查当前系统是拥有代码
	   if(!this.checkModel("com.mingsoft.people.action.PeopleUserAction")) {
			result.setResult(false);
			result.setResultMsg("请先使用源码或Maven方式加载模块到系统！");
			return result;
		}

	   //获取当前模块版本号
	    String version = this.getVersion("com/mingsoft/people","07000000");
	    if(version == null) {
	    	result.setResult(false);
			result.setResultMsg("当前系统模块版本号异常！");
			return result;	
	    }
	    IModelBiz modelBiz = (IModelBiz) SpringUtil.getBean(IModelBiz.class);
	    ModelEntity modelFunction = modelBiz.getEntityByModelCode("07000000");
	    if(modelFunction != null){
	    	result.setResult(false);
			result.setResultMsg("当前系统模块版本最新");
			return result;	
	    }
	    //业务处理代码
	    ModelEntity modelParent = modelBiz.getEntityByModelCode("07000000");
	    ModelEntity oldModel = modelBiz.getEntityByModelCode("07020100");
	    if(modelParent == null && oldModel == null){
			result.setResult(false);
			result.setResultMsg("当前系统模块版本号异常！");
			return result;
		}
	    //更新菜单的model_url model_parent_ids
	    String updateSql = "update model set model_url = 'people/peopleUser/index.do' ,model_ismenu = 1, model_parent_ids = '"+modelParent.getModelId()+"' where model_id = "+oldModel.getModelId();
	    modelBiz.excuteSql(updateSql);
	    //处理菜单的功能,获取最新的菜单数据
	    ModelEntity modelEntity = modelBiz.getEntityByModelCode("07020100");
		int modelId = modelEntity.getModelId();
		String modelParentIds = modelId+"";
		if(modelParentIds != null){
			modelParentIds = modelId + "," + modelEntity.getModelParentIds();
		}
		//组织子功能的sql
		String functionSql = "INSERT INTO model (model_title,model_code,model_modelid,model_url,model_ismenu,model_parent_ids)VALUES('查看','07020101',"+modelId+",'people:view',0,'"+modelParentIds+"'),('新增','07020102',"+modelId+",'people:save',0,'"+modelParentIds+"'),('修改','07020104',"+modelId+",'people:update',0,'"+modelParentIds+"'),('删除','07020103',"+modelId+",'people:update',0,'"+modelParentIds+"')";
		modelBiz.excuteSql(functionSql);
		//权限
		List list = new ArrayList();
		IRoleModelBiz roleModelBiz = (IRoleModelBiz) SpringUtil.getBean(IRoleModelBiz.class);
		HttpServletRequest request = SpringUtil.getRequest();
		ModelEntity model = modelBiz.getEntityByModelCode("07020101");
		int people = this.getManagerBySession(request).getManagerRoleID();
		RoleModelEntity roleModePeople = new RoleModelEntity();
		roleModePeople.setModelId(model.getModelId());
		roleModePeople.setRoleId(people);
		list.add(roleModePeople);
		roleModelBiz.saveEntity(list);
		result.setResult(true);
		result.setResultMsg("安装成功");
		return result;
	}
	
	
	private boolean checkModel(String className) {
		try {
			Class cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private String getVersion(String pak,String modelCode) {
		try {
			String value = PropertiesUtil.get(pak+"/resources/resources_zh_CN.properties", "version");
			value = AESUtil.decrypt(value, StringUtil.Md5(modelCode).substring(16));
			if(value==null) {
				return null;
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}