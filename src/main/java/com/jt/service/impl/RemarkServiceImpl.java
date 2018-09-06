package com.jt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.dao.RemarkDao;
import com.jt.entity.Remark;
import com.jt.service.RemarkService;

/**
 * remarkService的实现类
 * @author gosin1994
 * @date 2018年8月16日上午11:38:37 
 * @email gx1008666@163.com
 */
@Service("RemarkService")
public class RemarkServiceImpl implements RemarkService{
	
	@Autowired
	private RemarkDao remarkDao;
	
	@Override
	public void save(Remark rk) {
		// TODO Auto-generated method stub
		remarkDao.save(rk);
	}

	@Override
	public List<Remark> selectByApplyId(Integer applyId) {
		return remarkDao.selectByApplyId(applyId);
	}

}
