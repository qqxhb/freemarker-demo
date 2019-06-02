package com.qqxhb.freemarker.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qqxhb.freemarker.model.Student;

@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {
	@GetMapping("simple")
	public String simpleDemo(Map<String, Object> map) {
		map.put("user", "小宝");
		// 返回模板文件名称
		return "simple";
	}

	@GetMapping("student")
	public String student(Map<String, Object> map) {
		// 向数据模型放数据
		map.put("name", "程序员");
		Student stu1 = new Student();
		stu1.setName("小明");
		stu1.setAge(18);
		stu1.setNumber(2019180251);
		stu1.setBirthday(new Date());
		Student stu2 = new Student();
		stu2.setName("小红");
		stu2.setNumber(2019180252);
		stu2.setAge(19);
		stu2.setBirthday(new Date());
		List<Student> friends = new ArrayList<>();
		friends.add(stu1);
		stu2.setFriends(friends);
		stu2.setBestFriend(stu1);
		List<Student> stus = new ArrayList<>();
		stus.add(stu1);
		stus.add(stu2);
		// 向数据模型放数据
		map.put("stus", stus);
		// 准备map数据
		HashMap<String, Student> stuMap = new HashMap<>();
		stuMap.put("stu1", stu1);
		stuMap.put("stu2", stu2);
		// 向数据模型放数据
		map.put("stu1", stu1);
		// 向数据模型放数据
		map.put("stuMap", stuMap);
		// 返回模板文件名称
		return "student";
	}
}
