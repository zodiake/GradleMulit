package com.sj.model.type;

import java.util.Locale;

public enum ProductStatusEnum {
	DOWN {
		public String getName() {
			return "下架";
		}
	},
	UP {
		public String getName() {
			return "上架";
		}
	},
	EXAMINE {
		public String getName() {
			return "审核中";
		}
	},
	NOT {
		public String getName() {
			return "未通过";
		}
	};

	public abstract String getName();

	public static ProductStatusEnum formString(String scale) {
		try {
			return ProductStatusEnum.valueOf(scale.toUpperCase(Locale.US));
		} catch (Exception e) {
			return null;
		}
	}
}