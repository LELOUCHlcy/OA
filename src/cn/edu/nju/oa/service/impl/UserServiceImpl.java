package cn.edu.nju.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.base.DaoSupportImpl;
import cn.edu.nju.oa.domain.User;
import cn.edu.nju.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements
		UserService {

	@Override
	public User findByNameAndPassword(String loginName, String password) {
		String md5Digest = DigestUtils.md5Hex(password);
		return (User) getSession().createQuery(
						"FROM User u WHERE u.loginName=? AND u.password=?")
				.setParameter(0, loginName).setParameter(1, md5Digest)
				.uniqueResult();
	}

}
