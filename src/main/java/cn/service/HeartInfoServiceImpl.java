package cn.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mapper.HeartInfoMapper;
import cn.pojo.HeartInfo;

@Service("heartInfoService")
public class HeartInfoServiceImpl implements HeartInfoService {
	@Resource
	private HeartInfoMapper mapper;

	@Override
	public int addHeartInfo(HeartInfo heartInfo) {
		// TODO 自动生成的方法存根
		return mapper.addHeartInfo(heartInfo);
	}

}
