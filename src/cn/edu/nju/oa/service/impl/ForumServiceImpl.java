package cn.edu.nju.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.oa.base.DaoSupportImpl;
import cn.edu.nju.oa.domain.Forum;
import cn.edu.nju.oa.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl extends DaoSupportImpl<Forum> implements
		ForumService {

	@Override
	public List<Forum> getAll() {
		return getSession().createQuery("From Forum f ORDER BY f.position")
				.list();
	}

	@Override
	public void save(Forum entity) {
		getSession().save(entity);

		entity.setPosition(entity.getId().intValue());

		getSession().update(entity);
	}

	@Override
	public void moveUp(Long id) {

		Forum forum = getById(id);
		Forum other = (Forum) getSession()
				.createQuery(
						"From Forum f WHERE f.position<? ORDER BY f.position DESC")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
		if (other == null) {
			return;
		}

		// 交换位置
		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		getSession().update(forum);
		getSession().update(other);
	}

	@Override
	public void moveDown(Long id) {
		Forum forum = getById(id);
		Forum other = (Forum) getSession()
				.createQuery(
						"From Forum f WHERE f.position>? ORDER BY f.position ASC")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();
		if (other == null) {
			return;
		}

		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);

		getSession().update(forum);
		getSession().update(other);
	}

}
