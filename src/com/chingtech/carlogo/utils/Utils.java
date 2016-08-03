package com.chingtech.carlogo.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static String getDepthByType(int depth) {
		String depthValue = "";
		switch (depth) {
		case 1:
			depthValue = "品牌";
			break;
		case 2:
			depthValue = "子公司";
			break;
		case 3:
			depthValue = "车型";
			break;
		case 4:
			depthValue = "具体车型";
			break;
		default:
			break;
		}

		return depthValue;
	}

	/**
	 * 得到车体颜色
	 * 
	 * @param colors
	 * @return
	 */
	public static List<String> getColors(String colors) {
		List<String> color_list = new ArrayList<String>();

		String a[] = colors.split(",");

		for (int i = 1; i < a.length; i++) {
			if (i > 0) {
				if (a[i].length() < 7 && a[i].length() > 1) {
					if (a[i].length() == 2) {// 如#3 -> #333333
						String s = a[i].substring(1, 2);

						for (int j = 0; j < 5; j++) {
							a[i] += s;
						}
						color_list.add(a[i]);
					}

					if (a[i].length() == 3) {// 如#34 -> #343434
						String s = a[i].substring(1, 3);

						for (int j = 0; j < 2; j++) {
							a[i] += s;
						}

						color_list.add(a[i]);
					}

					if (a[i].length() == 4) {// 如#666 -> #666666
						String s = a[i].substring(1, 4);
						a[i] += s;
						color_list.add(a[i]);
					}
				} else {
					String str = a[i].substring(0, 7);
					color_list.add(str);
				}
			}
		}
		return color_list;
	}
}
