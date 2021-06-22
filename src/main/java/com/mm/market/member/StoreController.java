package com.mm.market.member;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.memberLocation.MemberLocationService;
import com.mm.market.memberLocation.MemberLocationVO;
import com.mm.market.product.HeartVO;
import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;
import com.mm.market.review.ReviewService;
import com.mm.market.review.ReviewVO;
import com.mm.market.util.Pager;
import com.mm.market.util.ProductPager;

@Controller
@RequestMapping("/store/**")
public class StoreController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberLocationService memberLocationService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("{code}/products")
	public ModelAndView products(@PathVariable("code") Long code, ProductPager productPager, Authentication authentication, ModelAndView mv) throws Exception {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setCode(code);
		memberVO = memberService.getSelectByCode(memberVO);
		
		MemberFileVO memberFileVO = memberService.selectFile(memberVO);
		
		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);
		
		productPager.setUsername(memberVO.getUsername());
		List<ProductVO> productList = productService.getList(productPager, 16L, 5L);
		
		mv.addObject("member", memberVO);
		mv.addObject("file", memberFileVO);
		mv.addObject("locations", locationList);
		mv.addObject("products", productList);
		mv.addObject("pager", productPager);
		mv.setViewName("/store/products");
		return mv;
	}
	
	//code 넣으면 다른 지역이 안나와서 그냥 빼고 했습니당!
	@GetMapping("/hearts")
	public ModelAndView hearts(Authentication authentication, ModelAndView mv) throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO = (MemberVO)authentication.getPrincipal();
		MemberFileVO memberFileVO = memberService.selectFile(memberVO);
		
		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);
		
		HeartVO heartVO = new HeartVO();
		heartVO.setUsername(memberVO.getUsername());
		List<ProductVO> productList = productService.getHeartList(heartVO);
		
		mv.addObject("products", productList);
		mv.addObject("member", memberVO);
		mv.addObject("file", memberFileVO);
		mv.addObject("locations", locationList);
		mv.setViewName("/store/hearts");
		
		return mv;
	}

	@GetMapping("{code}/reviews")
	public ModelAndView getLists(@PathVariable("code") Long code, ModelAndView mv) throws Exception {
		MemberVO revieweeVO = new MemberVO();
		revieweeVO.setCode(code);
		revieweeVO = memberService.getSelectByCode(revieweeVO);
		
		ReviewVO reviewVO = new ReviewVO();
		reviewVO.setReviewee(revieweeVO.getUsername());
		reviewVO.setType(true);
		List<ReviewVO> buyerReviews = reviewService.getListByReviewee(reviewVO);
		reviewVO.setType(false);
		List<ReviewVO> sellerReviews = reviewService.getListByReviewee(reviewVO);
		
		MemberVO memberVO = new MemberVO();
		memberVO.setCode(code);
		memberVO = memberService.getSelectByCode(memberVO);
		
		MemberFileVO memberFileVO = memberService.selectFile(memberVO);
		
		MemberLocationVO memberLocationVO = new MemberLocationVO();
		memberLocationVO.setUsername(memberVO.getUsername());
		List<MemberLocationVO> locationList = memberLocationService.getList(memberLocationVO);

		
		mv.addObject("member", memberVO);
		mv.addObject("file", memberFileVO);
		mv.addObject("locations", locationList);
		
		mv.addObject("buyerReviews", buyerReviews);
		mv.addObject("sellerReviews", sellerReviews);
		mv.setViewName("store/reviews");

		return mv;
	}
	
	@GetMapping("{code}/socials")
	public String socials(@PathVariable("code") Long code) throws Exception {
		return "/store/socials";
	}
		
	@GetMapping("profileUpdate")
	public ModelAndView setUpdateFile(MemberFileVO memberFileVO, Authentication authentication) throws Exception{
		MemberVO memberVO =(MemberVO)authentication.getPrincipal();
		memberFileVO = memberService.selectFile(memberVO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("file",memberFileVO);
		mv.setViewName("member/profileUpdate");
		
		return mv;
	}
	
	@PostMapping("profileUpdate")
	public String setUpdateFile(MemberFileVO memberFileVO, MultipartFile avatar, Authentication authentication) throws Exception{
		MemberVO memberVO =(MemberVO)authentication.getPrincipal();
		int result = memberService.setUpdateFile(avatar, memberVO, authentication);
		return "redirect:/store/"+memberVO.getCode()+"/products";
	}
	
}
