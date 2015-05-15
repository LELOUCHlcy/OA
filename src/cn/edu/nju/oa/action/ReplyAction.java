package cn.edu.nju.oa.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.edu.nju.oa.base.BaseAction;
import cn.edu.nju.oa.domain.Reply;
import cn.edu.nju.oa.domain.Topic;

@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> {
	private Long topicId;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public String addUI() {
		// 准备数据
		Topic topic = topicService.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}

	public String add() {
		// 封装
		// >> 表单字段，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setTopic(topicService.getById(topicId));
		// >> 当前信息
		model.setAuthor(getCurrentUser()); // 当前用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date()); // 当前时间

		// 保存
		replyService.save(model);
		return "toTopicShow";
	}
}
