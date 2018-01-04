package excelRead;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {

	/**
	 * 读取excel文件中的用户信息，保存在数据库中
	 * 
	 * @param excelFile
	 */
	@RequestMapping("/readExcel")
	public void readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest req, HttpServletResponse resp) {
		try {
			List<String[]> userList = POIUtilWeb.readExcel(excelFile);
			for (int i = 0; i < userList.size(); i++) {
				String[] users = userList.get(i);
				Integer.parseInt(users[2]);
			}
		} catch (IOException e) {
			System.out.println("读取excel文件失败");
		}
	}
	
	@RequestMapping("/aaa")
	public void test(HttpServletRequest req, HttpServletResponse resp) {
			System.out.println("读取excel文件失败");
	}

}
