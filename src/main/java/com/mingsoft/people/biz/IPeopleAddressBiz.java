package com.mingsoft.people.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.people.constant.e.PeopleAddressEnum;
import com.mingsoft.people.entity.PeopleAddressEntity;
/**
 * 
 * 用户收货地址业务层
 * @author yangxy
 * @version 
 * 版本号：【100-000-000】
 * 创建日期：2015年8月23日 
 * 历史修订：
 */
public interface IPeopleAddressBiz extends IBaseBiz{
	/**
	 * 设置默认地址，
	 * 1、先把之前的默认地址重置
	 * 2、再把新的收货地址设置为默认
	 * @param peopleAddress 
	 */
	void setDefault(PeopleAddressEntity peopleAddress);
}
