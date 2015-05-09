package cn.edu.nju.oa.base;

import java.util.List;

public interface DaoSupport<T> {

	/**
	 * 保存实体
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 查询一个实体
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 查询所有的实体
	 * @return
	 */
	List<T> getAll();

	/**
	 * 根据id来查询一群实体
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);
}
