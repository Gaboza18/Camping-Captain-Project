package com.camping.biz.campingImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camping.biz.camping.CampingService;
import com.camping.biz.dao.CampingDAO;
import com.camping.biz.dto.CampingVO;

@Service("campingService")
public class CampingServiceImpl implements CampingService {
	
	@Autowired
	private CampingDAO cDao;

	@Override
	public String getCampName(int camp_id) {
		return cDao.getCampName(camp_id);
	}
	
	@Override
	public CampingVO getCamping(String camp_zone) {
		return cDao.getCamping(camp_zone);
	}

	@Override
	public List<CampingVO> campingList(int camp_id) {
		return cDao.campingList(camp_id);
	}

}
