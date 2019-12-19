package cn.mapper;

import org.apache.ibatis.annotations.Insert;

import cn.pojo.HeartInfo;

public interface HeartInfoMapper {
	@Insert("Insert into heart_info(deviceid,doorone,doortwo,camera,lockstate,zhendong,temp,liquid)values(#{deviceid},#{doorone},#{doortwo},#{camera},#{lockstate},#{zhendong},#{temp},#{liquid})")
	int addHeartInfo(HeartInfo heartInfo);
}
