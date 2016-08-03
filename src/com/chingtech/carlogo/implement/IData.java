package com.chingtech.carlogo.implement;

import java.util.List;

public interface IData<T> {

	void add(T elem);

	void addAll(List<T> elem);

	void set(T oldElem, T newElem);

	void set(int index, T elem);

	void remove(T elem);

	void remove(int index);

	void replaceAll(List<T> elem);

	boolean contains(T elem);

	void clear();

}
