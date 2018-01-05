package com.excelRead;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/webController")
public class WebController {

	/**
	 * 读取excel文件中的用户信息，保存在数据库中
	 * 
	 * @param excelFile
	 */
	@RequestMapping(params="excel")
	public void readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<String[]> userList = POIUtilWeb.readExcel(excelFile);
			for (int i = 0; i < userList.size(); i++) {
				for(String[] str : userList){
					for(String s : str){
						System.out.print(s);
					}
				}
				System.out.println();
			}
		} catch (IOException e) {
			System.out.println("读取excel文件失败");
		}
	}
	
	@RequestMapping(params="aaa")
	public void test(HttpServletRequest req, HttpServletResponse resp) {
			POIUtil.test();
			System.out.println("读取excel文件失败");
	}

}
