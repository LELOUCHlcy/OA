package cn.edu.nju.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.base.DaoSupport;
import cn.edu.nju.oa.base.DaoSupportImpl;
import cn.edu.nju.oa.domain.Role;
import cn.edu.nju.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements
		RoleService {

}
