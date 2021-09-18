package com.rankofmatrix.blog.conf;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.InvalidRequestFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.FilterChainManager;

import javax.servlet.Filter;
import java.util.Map;

public class CustomShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    @Override
    protected FilterChainManager createFilterChainManager() {
        FilterChainManager manager = super.createFilterChainManager();
        // URL携带中文400，servletPath中文校验bug
        Map<String, Filter> filterMap = manager.getFilters();
        Filter invalidRequestFilter = filterMap.get(DefaultFilter.invalidRequest.name());
        if (invalidRequestFilter instanceof InvalidRequestFilter) {
            ((InvalidRequestFilter) invalidRequestFilter).setBlockNonAscii(false);
        }
        return manager;
    }
}