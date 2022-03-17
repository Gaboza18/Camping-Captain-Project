package com.camping.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.camping.biz.adminnotice.AdminNoticeService;
import com.camping.biz.dto.AdminNoticeVO;
import com.camping.biz.dto.AdminVO;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("admin_notice")
public class AdminNoticeController {
	
	@Autowired
	private AdminNoticeService adminNoticeService;
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �긽�꽭蹂닿린, 議고쉶�닔 利앷�(珥앷�由ъ옄)
	 */
	@RequestMapping(value = "/admin_notice_detail", method = RequestMethod.GET)
	public String adminNoticeDetail(HttpSession session, AdminNoticeVO vo, Model model, int aseq) {

		adminNoticeService.updateViewCount(vo.getAseq()); // 議고쉶�닔 利앷�

		AdminNoticeVO AdminNoticeDetail = adminNoticeService.detailNotice(aseq);
		model.addAttribute("adminnoticeVO", AdminNoticeDetail);

		return "admin/adminnotice/admin_notice_detail";
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆(�럹�씠吏� 泥섎━) 議고쉶(珥앷�由ъ옄)
	 */
	@RequestMapping(value = "/admin_notice_list", method = RequestMethod.GET)
	public String adminnoticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		// 怨듭��궗�빆 紐⑸줉 議고쉶 - 怨듭��궗�빆 10媛쒕쭔 議고쉶
		List<AdminNoticeVO> AdminNoticeList = adminNoticeService.getListWithPaging(criteria, title);

		// �솕硫댁뿉 �몴�떆�븷 �럹�씠吏� 踰꾪듉 �젙蹂� �꽕�젙
		PageMaker pageMaker = new PageMaker();
		int totalCount = adminNoticeService.countNoticeList(title);

		pageMaker.setCriteria(criteria); // �쁽�옱 �럹�씠吏��� �럹�씠吏��떦 �빆紐� �닔 �젙蹂� �꽕�젙
		pageMaker.setTotalCount(totalCount); // �쟾泥� 怨듭��궗�빆 紐⑸줉 媛��닔 �꽕�젙 諛� �럹�씠吏� �젙蹂� 珥덇린�솕

		model.addAttribute("adminnoticeList", AdminNoticeList); // ${noticeList} �냽�꽦媛믪뿉 �떞怨� �솕硫댁뿉 �샇異쒗븳�떎
		model.addAttribute("adminnoticeListSize", AdminNoticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "admin/adminnotice/admin_notice_list"; // 怨듭��궗�빆 由ъ뒪�듃 �솕硫댁쑝濡� �쟾�넚
	}
	
	/*
	 *  愿�由ъ옄 怨듭��궗�빆 議고쉶(吏��젏愿�由ъ옄)
	 */
	@RequestMapping(value = "/manager_admin_notice_list", method = RequestMethod.GET)
	public String managerAdminNoticeList(@RequestParam(value = "key", defaultValue = "") String title, Criteria criteria,
			HttpSession session, Model model) {

		// 怨듭��궗�빆 紐⑸줉 議고쉶 - 怨듭��궗�빆 10媛쒕쭔 議고쉶
		List<AdminNoticeVO> AdminNoticeList = adminNoticeService.getListWithPaging(criteria, title);

		// �솕硫댁뿉 �몴�떆�븷 �럹�씠吏� 踰꾪듉 �젙蹂� �꽕�젙
		PageMaker pageMaker = new PageMaker();
		int totalCount = adminNoticeService.countNoticeList(title);

		pageMaker.setCriteria(criteria); // �쁽�옱 �럹�씠吏��� �럹�씠吏��떦 �빆紐� �닔 �젙蹂� �꽕�젙
		pageMaker.setTotalCount(totalCount); // �쟾泥� 怨듭��궗�빆 紐⑸줉 媛��닔 �꽕�젙 諛� �럹�씠吏� �젙蹂� 珥덇린�솕

		model.addAttribute("adminnoticeList", AdminNoticeList); // ${noticeList} �냽�꽦媛믪뿉 �떞怨� �솕硫댁뿉 �샇異쒗븳�떎
		model.addAttribute("adminnoticeListSize", AdminNoticeList.size());
		model.addAttribute("pageMaker", pageMaker);

		return "admin/adminnotice/manager_admin_notice_list"; // 怨듭��궗�빆 由ъ뒪�듃 �솕硫댁쑝濡� �쟾�넚
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �긽�꽭蹂닿린(吏��젏 愿�由ъ옄)
	 */
	@RequestMapping(value = "/manager_admin_notice_detail", method = RequestMethod.GET)
	public String managerAdminNoticeDetail(HttpSession session, AdminNoticeVO vo, Model model, int aseq) {
		
		adminNoticeService.updateViewCount(vo.getAseq()); // 議고쉶�닔 利앷�

		AdminNoticeVO AdminNoticeDetail = adminNoticeService.detailNotice(aseq);
		model.addAttribute("adminnoticeVO", AdminNoticeDetail);

		return "admin/adminnotice/manager_admin_notice_detail";
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �벑濡� �럹�씠吏� �씠�룞(珥앷�由ъ옄)
	 */
	@RequestMapping(value="/admin_notice_write_form", method=RequestMethod.GET)
	public String adminNoitceWriteForm(HttpSession session, AdminNoticeVO vo) {
		return "admin/adminnotice/admin_notice_write_form";
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �벑濡�(珥앷�由ъ옄)
	 */
	@RequestMapping(value="/admin_notice_write", method=RequestMethod.GET)
	public String adminNoticeWrite(HttpSession session, AdminNoticeVO vo) {
		
		adminNoticeService.insertAdminNotice(vo);
		return "redirect:admin_notice_list";
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �닔�젙 �럹�씠吏� �씠�룞(珥앷�由ъ옄)
	 */
	@RequestMapping(value = "/admin_notice_update_form", method = RequestMethod.GET)
	public String adminQuestionUpdateForm(@RequestParam("aseq") int aseq, Model model) {
		
		AdminNoticeVO  adminNoitceDetail = adminNoticeService.detailNotice(aseq);
		model.addAttribute("adminnoticeVO", adminNoitceDetail);
		
		return "admin/adminnotice/admin_notice_update_form";
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �닔�젙(珥앷�由ъ옄)
	 */
	@RequestMapping(value="/admin_notice_update", method=RequestMethod.GET)
	public String adminNoticeUpdate(HttpSession session, AdminNoticeVO vo) {
		
		adminNoticeService.updateAdminNotice(vo);
		return "redirect:admin_notice_list";
	}
	
	/*
	 * 愿�由ъ옄 怨듭��궗�빆 �궘�젣(珥앷�由ъ옄)
	 */
	@RequestMapping(value = "/admin_notice_delete", method = RequestMethod.GET)
	public String adminQuestionDelete(HttpSession session, AdminNoticeVO vo, int aseq) {
		
		adminNoticeService.deleteAdminNotice(aseq);
		return "redirect:admin_notice_list";
	}
	
}