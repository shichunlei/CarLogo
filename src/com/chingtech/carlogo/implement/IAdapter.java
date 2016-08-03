package com.chingtech.carlogo.implement;

import com.chingtech.carlogo.base.BaseAdapterHelper;

public interface IAdapter<T> {

	void onUpdate(BaseAdapterHelper helper, T item, int position);

	int getLayoutResId(T item);
}
