package cn.edu.nju.oa.service;

import cn.edu.nju.oa.base.DaoSupport;
import cn.edu.nju.oa.domain.Forum;

public interface ForumService extends DaoSupport<Forum> {

	void moveUp(Long id);

	void moveDown(Long id);


}
