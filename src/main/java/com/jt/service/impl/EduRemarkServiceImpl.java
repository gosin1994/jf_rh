package com.jt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.dao.EduRemarkDao;
import com.jt.dao.RemarkDao;
import com.jt.entity.Remark;
import com.jt.service.EduRemarkService;
import com.jt.service.RemarkService;

/**
 * remarkService的实现类
 * @author gosin1994
 * @date 2018年8月16日上午11:38:37 
 * @email gx1008666@163.com
 */
@Service("eduRemarkService")
public class EduRemarkServiceImpl implements EduRemarkService{
	
	@Autowired
	private EduRemarkDao eduRemarkDao;
	
	@Override
	public void save(Remark rk) {
		// TODO Auto-generated method stub
		eduRemarkDao.save(rk);
	}

	@Override
	public List<Remark> selectByApplyId(Integer applyId) {
		return eduRemarkDao.selectByApplyId(applyId);
	}

}
