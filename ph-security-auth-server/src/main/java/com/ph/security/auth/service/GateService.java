package com.ph.security.auth.service;

import com.ph.security.common.biz.UserBiz;
import com.ph.security.common.entity.Group;
import com.ph.security.common.entity.User;
import com.ph.security.common.entity.ClientInfo;
import com.ph.security.agent.entity.auth.PermissionInfo;
import com.ph.security.common.biz.ElementBiz;
import com.ph.security.auth.biz.GateClientBiz;
import com.ph.security.auth.constant.CommonConstant;
import com.ph.security.common.entity.Element;
import com.ph.security.common.entity.GateClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GateService {
	@Autowired
	private GateClientBiz gateClientBiz;
	@Autowired
	private ElementBiz elmentBiz;

	@Autowired
	private UserBiz userBiz;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public ClientInfo getGateClientInfo(String clientId) {
		GateClient example = new GateClient();
		example.setCode(clientId);
		ClientInfo info = new ClientInfo();
		GateClient gateClient = gateClientBiz.selectByExample(example).get(0);
		BeanUtils.copyProperties(gateClient, info);
		info.setLocked(CommonConstant.BOOLEAN_NUMBER_TRUE.equals(gateClient.getLocked()));
//		info.setSecret(encoder.encode(info.getSecret()));
		return info;
	}

	public List<PermissionInfo> getGateServiceInfo() {
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		Element element = new Element();
		element.setMenuId("-1");
		List<Element> elements = elmentBiz.selectByExample(element);
		convert(infos, elements);
		return infos;
	}

	public List<PermissionInfo> getGateServiceInfo(String clientId) {
		GateClient example = new GateClient();
		example.setCode(clientId);
		GateClient gateClient = gateClientBiz.selectByExample(example).get(0);
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		convert(infos, gateClient.getElements());
		return infos;
	}

	public List<PermissionInfo> getUserPermissionInfo(String username) {
		User user = userBiz.findByUsername(username);
		List<Element> elements = new ArrayList<Element>();
		Set<Element> elementSet = new HashSet<Element>();
		for (Group g: user.getGroups()
			 ) {
			for (Element e:g.getElements()
				 ) {
				if(elementSet.add(e)){
					elements.add(e);
				}
			}
		}
		List<PermissionInfo> infos = new ArrayList<PermissionInfo>();
		convert(infos, elements);
		return infos;
	}



	private void convert(List<PermissionInfo> infos, List<Element> elements) {
		PermissionInfo info;
		for (Element element : elements) {
			info = new PermissionInfo();
			info.setCode(element.getCode());
			info.setType(element.getType());
			info.setUri(element.getUri());
			info.setMethod(element.getMethod());
			info.setName(element.getName());
			infos.add(info);
		}
	}
}
