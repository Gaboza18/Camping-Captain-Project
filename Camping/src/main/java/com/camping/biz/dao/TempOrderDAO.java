package com.camping.biz.dao;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.TempOrderVO;

@Repository
public class TempOrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// �ӽ� �������� insert
	public void insertTempOrder(TempOrderVO vo) {
		mybatis.insert("mappings.tempOrder-mapping.insertTempOrder", vo);
	}
	
	// �ӽ� �����ȣ�� �������� �Ѱ� ��ȸ
	public TempOrderVO getTempOrder(String temp_id) {
		return mybatis.selectOne("mappings.tempOrder-mapping.getTempOrder", temp_id);
	}
	
	// �ǰ��� ���� �� �ӽ����̺� ����
	public void deleteTempOrder(String temp_id) {
		mybatis.delete("mappings.tempOrder-mapping.deleteTempOrder", temp_id);
	}
}
