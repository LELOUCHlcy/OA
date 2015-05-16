package cn.edu.nju.oa.service;

import java.util.List;

import cn.edu.nju.oa.base.DaoSupport;
import cn.edu.nju.oa.domain.PageBean;
import cn.edu.nju.oa.domain.Reply;
import cn.edu.nju.oa.domain.Topic;

public interface ReplyService extends DaoSupport<Reply> {

	List<Reply> getByTopic(Topic topic);

	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);

}
