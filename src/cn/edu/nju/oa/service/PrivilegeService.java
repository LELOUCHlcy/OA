package cn.edu.nju.oa.service;

import java.util.Collection;
import java.util.List;

import cn.edu.nju.oa.base.DaoSupport;
import cn.edu.nju.oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> getTopPrivilegeList();

	Collection<String> getAllPrivilegeUrls();

}
