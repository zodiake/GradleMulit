package com.sj.admin.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sj.admin.annotation.PageRequestAnn;

@Component
public class PageRequestResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(PageRequestAnn.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		Integer page, size;
		String p = webRequest.getParameter("page");
		if (p == null)
			p = "1";
		String s = webRequest.getParameter("size");
		if (s == null)
			s = "15";
		String order = webRequest.getParameter("order");
		String dir = webRequest.getParameter("dir");
		if (order == null)
			order = "createdTime";
		try {
			page = Integer.valueOf(p);
			size = Integer.valueOf(s);
		} catch (Exception e) {
			return new org.springframework.data.domain.PageRequest(0, 15,
					Direction.fromStringOrNull(dir), order);
		}
		return new org.springframework.data.domain.PageRequest(page-1, size,
				Direction.fromStringOrNull(dir), order);
	}
}