package com.mingsoft.people.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.people.constant.e.PeopleEnum;
import com.mingsoft.people.entity.PeopleAddressEntity;

/**
 * 
 * 用户收货地址持久化层
 * @author yangxy
 * @version 
 * 版本号：【100-000-000】
 * 创建日期：2015年8月23日 
 * 历史修订：
 */
public interface IPeopleAddressDao extends IBaseDao{
	
	/**
	 * 设置默认地址，
	 * 1、先把之前的默认地址重置
	 * 2、再把新的收货地址设置为默认
	 * @param peopleAddress 
	 */
	void setDefault(PeopleAddressEntity peopleAddress);
}
