package cn.edu.nju.oa.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.nju.oa.base.BaseAction;
import cn.edu.nju.oa.domain.Forum;
import cn.edu.nju.oa.domain.PageBean;
import cn.edu.nju.oa.domain.Reply;
import cn.edu.nju.oa.domain.Topic;
import cn.edu.nju.oa.domain.User;

@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {

	private Long forumId;
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}


	// 显示主帖+回帖
	public String show() {
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// List<Reply> replyList = replyService.getByTopic(topic);
		// ActionContext.getContext().put("replyList", replyList);
		PageBean pageBean = replyService.getPageBeanByTopic(pageNum, pageSize,
				topic);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

	// 添加主帖页面
	public String addUI() {
		// 准备数据
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}

	// 添加主题
	public String add() {
		// 分成三类数据封装
		// 表单传来的数
		// model.setTitle(title);
		// model.setContent(content);
		model.setForum(forumService.getById(forumId));
		// >> 当前直接获取的信息
		model.setAuthor(getCurrentUser()); // 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 当前请求中的IP
		model.setPostTime(new Date()); // 当前时间

		// 保存
		topicService.save(model);
		return "toShow";
	}
}
