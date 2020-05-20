package com.ycw.cebs.common.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.ycw.cebs.common.vo.TreeVO;

public class TreeUtil {

	private TreeUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static <T extends TreeVO> List<T> createTree(List<T> list) {
		List<T> resultList = new ArrayList<>();
		if (CollectionUtils.isEmpty(list)) {
			return resultList;
		}
		// 将集合中所有数据按照父Id进行分组，放入Map中，Map<parntId, List<Child>>
		Map<Long, List<T>> groupByParentIdMap = list.stream().collect(Collectors.groupingBy(T::getParentId, LinkedHashMap::new, Collectors.toList()));
		// 将集合中所有数据以数据Id为key，放入Map中，Map<id,T>
		Map<Long, T> dataMap = list.stream().collect(Collectors.toMap(T::getId, t -> t));
		// 遍历数据，将子节点放入对应父节点Children属性中
		groupByParentIdMap.keySet().forEach(parentId -> {
			List<T> childlist = groupByParentIdMap.get(parentId);
			if (dataMap.containsKey(parentId)) {
				T parentNode = dataMap.get(parentId);
				List<TreeVO> children = parentNode.getChildren();
				if (null == children) {
					children = new ArrayList<>();
				}
				children.addAll(childlist);
				parentNode.setChildren(children);
			} else {
				resultList.addAll(childlist);
			}
		});
		return resultList;
	}

}
