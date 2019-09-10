package com.youyd.authorization.service;

import com.youyd.api.user.ResourceServiceRpc;
import com.youyd.authorization.exception.RemoteRpcException;
import com.youyd.pojo.user.Resource;
import com.youyd.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceService {

    @Autowired
    private ResourceServiceRpc resourceServiceRpc;

    public Set<Resource> findResourceByCondition() {
	    Resource resource = new Resource();
	    JsonData<List<Resource>> resourceByCondition = resourceServiceRpc.findResourceByCondition(resource);
	    if (!JsonData.isSuccess(resourceByCondition)){
		    throw new RemoteRpcException(resourceByCondition);
	    }
	    List<Resource> resources = resourceByCondition.getData();
	    return new HashSet<>(resources);
    }

    public Set<Resource> queryByRoleIds(String[] roleIds) {
	    HashSet<Resource> resources = new HashSet<>();
	    resources.add(new Resource());
	    JsonData<List<Resource>> resourceOfRole = resourceServiceRpc.findResourceByRoleIds(roleIds);
	    if (!JsonData.isSuccess(resourceOfRole)){
		    throw new RemoteRpcException(resourceOfRole);
	    }
	    List<Resource> data = resourceOfRole.getData();
	    Set<Resource> resourcesSet = new HashSet<>(data);
	    Optional<Set<Resource>> resourcesSetOpt = Optional.of(resourcesSet);
	    return resourcesSetOpt.orElseGet(Collections::emptySet);
    }

}
