package com.jt.service;

import java.util.List;

import com.jt.entity.Remark;

/**
 * 备注service接口
 * @author gosin1994
 * @date 2018年8月16日上午11:07:44 
 * @email gx1008666@163.com
 */
public interface RemarkService {

	void save(Remark rk);

	List<Remark> selectByApplyId(Integer applyId);


}
