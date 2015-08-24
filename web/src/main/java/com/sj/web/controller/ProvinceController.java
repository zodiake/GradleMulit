package com.sj.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.model.model.City;
import com.sj.model.model.Province;
import com.sj.repository.service.CityService;
import com.sj.repository.service.ProvinceService;
import com.sj.web.exception.ProvinceNotFoundException;

@Controller
public class ProvinceController {
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;

	@RequestMapping(value = "/provinces/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<PCity> findById(@PathVariable("id") Long id) {
		Province p = provinceService.findOne(id);
		if(p==null)
			new ProvinceNotFoundException();
		Set<City> cs = cityService.findByProvince(p);
		List<PCity> citys = new ArrayList<PCity>();
		cs.stream().map(r -> {
			PCity c = new PCity(r.getId(), r.getName());
			return c;
		}).forEach(i -> citys.add(i));
		return citys;
	}
	
	
	private class PCity{
		private Long id;
		private String name;
		
		public PCity(Long id,String name){
			this.id = id;
			this.name = name;
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
	}
}
