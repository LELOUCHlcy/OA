package cn.edu.nju.oa.domain;

import java.util.HashSet;
import java.util.Set;

public class Forum {

	private Long id;
	private String name;
	private String description;
	private int position;//标记Forum的位置

	private Set<Topic> topics = new HashSet<Topic>();//属于版块的主题
	private int topicCount;//主题数，避免数据库查询
	private int articleCount;//所有的主题与回复的数量，同上
	private Topic lastTopic;//用于显示最近发布的主题

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public Topic getLastTopic() {
		return lastTopic;
	}

	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
