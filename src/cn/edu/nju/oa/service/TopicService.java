package cn.edu.nju.oa.service;

import java.util.List;

import cn.edu.nju.oa.base.DaoSupport;
import cn.edu.nju.oa.domain.Forum;
import cn.edu.nju.oa.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	List<Topic> getByForum(Forum forum);

}
