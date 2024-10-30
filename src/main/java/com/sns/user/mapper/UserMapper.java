package com.sns.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	// input:X    output:List<Map>
	public List<Map<String, Object>> selectUserList();
}
