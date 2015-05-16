package cn.edu.nju.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.base.DaoSupportImpl;
import cn.edu.nju.oa.domain.Forum;
import cn.edu.nju.oa.domain.PageBean;
import cn.edu.nju.oa.domain.Reply;
import cn.edu.nju.oa.domain.Topic;
import cn.edu.nju.oa.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements
		ReplyService {

	@Override
	public void save(Reply reply) {
		// 1，保存
		getSession().save(reply);

		// 2，维护相关的信息
		Topic topic = reply.getTopic();
		Forum forum = topic.getForum();

		forum.setArticleCount(forum.getArticleCount() + 1); // 文章数量（主题数+回复数）
		topic.setReplyCount(topic.getReplyCount() + 1); // 回复数量
		topic.setLastReply(reply); // 最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime()); // 最后更新时间（主题的发表时间或最后回复的时间）

		getSession().update(topic);
		getSession().update(forum);
	}

	@Override
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) {
		List recordList = getSession()
				.createQuery("From Reply r WHERE r.topic=? ORDER BY r.postTime")
				.setParameter(0, topic).setMaxResults(pageSize)
				.setFirstResult((pageNum - 1) * pageSize).list();

		Long recordNum = (Long) getSession()
				.createQuery("Select COUNT(*) From Reply r where r.topic=? ")
				.setParameter(0, topic).uniqueResult();
		return new PageBean(pageNum, pageSize, recordList, recordNum.intValue());
	}

	@Override
	public List<Reply> getByTopic(Topic topic) {
		// TODO Auto-generated method stub
		return null;
	}
}
