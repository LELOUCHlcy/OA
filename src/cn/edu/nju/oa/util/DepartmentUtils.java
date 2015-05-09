package cn.edu.nju.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.edu.nju.oa.domain.Department;

public class DepartmentUtils {

	public static List<Department> getAllDepartments(List<Department> topList) {
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList, list, "-");
		return list;
	}

	private static void walkDepartmentTreeList(Collection<Department> topList,
			List<Department> list, String prefix) {
		// 顶点
		for (Department top : topList) {
			Department copy = new Department();
			copy.setId(top.getId());
			copy.setName(prefix + top.getName());
			copy.setDescription(top.getDescription());
			list.add(copy);
			walkDepartmentTreeList(top.getChildren(), list, "　" + prefix);
		}
		// 子树
	}

}
