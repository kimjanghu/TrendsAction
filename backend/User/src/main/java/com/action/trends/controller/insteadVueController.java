package com.action.trends.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class insteadVueController {

	@RequestMapping(value = "/")
	public String index() {

		return "index";
	}
}
