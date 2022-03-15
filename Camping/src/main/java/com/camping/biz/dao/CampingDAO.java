package com.camping.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampingVO;

@Repository
public class CampingDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ķ�� ���� �̸�  ��ȸ 
	public String getCampName(int camp_id) {
		return mybatis.selectOne("mappings.camping-mapping.getCampName",camp_id);
	}
	
	//���డ���� ���� ������ ���� ����
	public CampingVO getCamping(String camp_zone) {
		return mybatis.selectOne("mappings.camping-mapping.getCamping",camp_zone);
	}
	
	// ���డ���� ķ���� ����Ʈ ��ȸ
	public List<CampingVO> campingList(int camp_id) {
		return mybatis.selectList("mappings.camping-mapping.campingList", camp_id);
	}
}
