package com.camping.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camping.biz.dto.CampOrderVO;

import utils.Criteria;

@Repository
public class CampOrderDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	// ���� ���� INSERT
	public void insertCampOrder(CampOrderVO vo) {
		mybatis.insert("mappings.campOrder-mapping.insertCampOrder", vo);
	}
	
	// ���ึ�� ó���� ���� üũ�γ�¥�� �������� ���೻�� ��ȸ
	public List<CampOrderVO> getCampOrderList(String indate){
		return mybatis.selectList("mappings.campOrder-mapping.getCampOrderList", indate);
	}
	
	// �����ȣ�� �������� ���೻�� �Ѱ� ��ȸ
	public CampOrderVO getMyCampOrder(int oseq){
		return mybatis.selectOne("mappings.campOrder-mapping.getMyCampOrder", oseq);
	}
	
	// ȸ�� id�� �������� �� ������ ���� ��ȸ
	public int countMyOrderList(String user_id) {
		return mybatis.selectOne("mappings.campOrder-mapping.countMyOrderList", user_id);
	}
	
	// ����¡ ó��(1~10���� ���) / ���� ���� ���
	public List<CampOrderVO> getMyListWithPaging(Criteria criteria, String user_id){

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("user_id", user_id);

		return mybatis.selectList("mappings.campOrder-mapping.MylistWithPaging", map);
	}
	
	
	
	/*
	 * ������ ���
	 */
	// ���� ���� ��ü ���� ��Ȳ ���� ��ȸ
	public int countOrderList(String camp_name) {
		return mybatis.selectOne("mappings.campOrder-mapping.countOrderList", camp_name);
	}
	
	// ���೻�� ����¡ó��
	public List<CampOrderVO> getListWithPaging(Criteria criteria, String camp_name) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("camp_name", camp_name);

		return mybatis.selectList("mappings.campOrder-mapping.listWithPaging", map);
	}
	
	// ��ü���� �� ������Ȳ ������ȸ
	public int countAllOrderList() {
		return mybatis.selectOne("mappings.campOrder-mapping.countAllOrderList");
	}
	
	// ��ü ���೻�� ����¡ó��
	public List<CampOrderVO> getAllListWithPaging(Criteria criteria) {

		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);

		return mybatis.selectList("mappings.campOrder-mapping.AlllistWithPaging", map);
	}
	
	// ���೻�� �Ѱ� ��ȸ
	public CampOrderVO getCampOrder(int oseq) {
		return mybatis.selectOne("mappings.campOrder-mapping.getCampOrder", oseq);
	}
	
	// ������� ����
	public void updateOrderStatus(int oseq) {
		mybatis.update("mappings.campOrder-mapping.updateOrderStatus", oseq);
	}
	
	// ���೻�� ����
	public void deleteOrderByOseq(int oseq) {
		mybatis.delete("mappings.campOrder-mapping.deleteOrderByOseq", oseq);
	}
}

