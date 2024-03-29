package com.qqxhb.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.qqxhb.freemarker.model.Student;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {

	// 基于模板生成静态化文件
	@Test
	public void testGenerateHtml() throws IOException, TemplateException {
		// 创建配置类
		Configuration configuration = new Configuration(Configuration.getVersion());
		String classpath = this.getClass().getResource("/").getPath();
		// 设置模板路径
		configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
		// 设置字符集
		configuration.setDefaultEncoding("utf-8");
		// 加载模板
		Template template = configuration.getTemplate("student.ftl");
		// 数据模型
		Map<?, ?> map = getMap();
		// 静态化
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		// 静态化内容
		InputStream inputStream = IOUtils.toInputStream(content);
		// 输出文件
		FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/student.html"));
		IOUtils.copy(inputStream, fileOutputStream);
	}

	// 基于模板字符串生成静态化文件
	@Test
	public void testGenerateHtmlByString() throws IOException, TemplateException {
		// 创建配置类
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 获取模板内容
		// 模板内容，这里测试时使用简单的字符串作为模板
		String templateString = "<html>\r\n" + "<head>\r\n" + "  <title>Welcome!</title>\r\n" + "</head>\r\n"
				+ "<body>\r\n" + "  <h1>Welcome ${user} to FreeMarker!</h1>\r\n" + "</body>\r\n" + "</html>";

		// 加载模板
		// 模板加载器
		StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
		stringTemplateLoader.putTemplate("template", templateString);
		configuration.setTemplateLoader(stringTemplateLoader);
		Template template = configuration.getTemplate("template", "utf-8");

		// 数据模型
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", "小宝");
		// 静态化
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		// 静态化内容
		InputStream inputStream = IOUtils.toInputStream(content);
		// 输出文件
		FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/simple.html"));
		IOUtils.copy(inputStream, fileOutputStream);
	}

	// 数据模型
	private Map<String, Object> getMap() {
		Map<String, Object> map = new HashMap<>();
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
		return map;
	}
}
