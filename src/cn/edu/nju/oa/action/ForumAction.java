package cn.edu.nju.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.nju.oa.base.BaseAction;
import cn.edu.nju.oa.domain.Forum;
import cn.edu.nju.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

	// 板块列表
	public String list() {
		List<Forum> forumList = forumService.getAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}

	// 显示版块（主题列表）
	public String show() {
		//准备数据
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		
		List<Topic> topicList = topicService.getByForum(forum);
		ActionContext.getContext().put("topicList", topicList);
		return "show";
	}
}
