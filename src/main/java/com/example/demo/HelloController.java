package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {
	
	// Webページの表示
	
	@GetMapping("/top")
	public String top() {
		return "top";
	}
	
	
	// 問題１
	@GetMapping("/thankyou")
	public String thankyou() {
		return "thankyou";
	}
	
	
	
	
	// 値の受渡し
	
	@GetMapping("/delivery")
	public String delivery(Model model) {
		model.addAttribute("msg", "Hello!");
		return "delivery";
	}
	
	
	// 問題２
	@GetMapping("/today")
	public String index(Model model) {
//		LocalDate ld = LocalDate.now();
		LocalDateTime ld = LocalDateTime.now();
		model.addAttribute("date", ld);
		return "today";
	}
	
	
	
	
	// パスの取得
	
	@GetMapping("/path/{value}")
	public String pathValue(Model model, @PathVariable String value) {
		// パスが"top"の場合
		if ("top".equals(value)) {
			return "top";
			
		// パスが"top"以外の場合
		} else {
			// 画面に表示する値をコントローラークラスでセット
			model.addAttribute("msg", "other");
			return "delivery";
		}
	}
	
	
	// 問題３
	@GetMapping("/q3/{value}")
	public String q3(@PathVariable String value, Model model) {
		String msg = "";
		if ("thankyou".equals(value)) {
			msg = "ありがとう";
		} else if ("today".equals(value)) {
			LocalDate ld = LocalDate.now();
			msg = ld.toString();
		} else {
			msg = "その他";
		}
		model.addAttribute("msg", msg);    
		return "delivery";
	}
	
	
	
	
	// フォワードとリダイレクト
	
	@GetMapping("/before")
	public String before() {
		return "before";
	}
	
	@GetMapping("/after")
	public String after() {
		return "after";
	}
	
	// フォワード
	@GetMapping("/frwrd")
	public String forward(Model model) {
		model.addAttribute("msg", "forward成功");
		return "forward:/after";
	}
	
	// リダイレクト
	@GetMapping("/rdrct")
	public String redirect(Model model) {
		model.addAttribute("msg", "redirect成功");
		return "redirect:/after";
	}
	
	
	// （参考）
	@GetMapping("/frwrd2")
	public String frwrd() {
		return "forward:https://www.yahoo.co.jp/";
	}
	
	@GetMapping("/rdrct2")
	public String rdrct() {
		return "redirect:https://www.yahoo.co.jp/";
	}
	
	
	// 問題４
	@GetMapping("/q4/{value}")
	public String q4(@PathVariable String value, Model model) {
		String target = "";    // 遷移先
		if ("yahoo".equals(value)) {
			// Yahooへリダイレクト
			target = "redirect:https://www.yahoo.co.jp/";
		} else {
			model.addAttribute("msg", "その他");
			// 「/q3/other」へフォワード
			target = "forward:/q3/other";
		}
		return target;
	}
	
	

}
