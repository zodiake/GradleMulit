package com.sj.admin.util;

import com.sj.model.type.ActivateEnum;

public class ActivateState {
	private ActivateEnum activateEnum;

	public ActivateState() {

	}

	public ActivateState(ActivateEnum activateEnum) {
		this.activateEnum = activateEnum;
	}

	public ActivateEnum getActivateEnum() {
		return activateEnum;
	}

	public void setActivateEnum(ActivateEnum activateEnum) {
		this.activateEnum = activateEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activateEnum == null) ? 0 : activateEnum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivateState other = (ActivateState) obj;
		if (activateEnum != other.activateEnum)
			return false;
		return true;
	}
}
