package com.jt.dao;

import java.util.List;

import com.jt.entity.Remark;

public interface RemarkDao {

	void save(Remark rk);

	List<Remark> selectByApplyId(Integer applyId);

}
