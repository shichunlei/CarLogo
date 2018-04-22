package com.chingtech.carlogo.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * CarLogo
 * Package com.chingtech.carlogo.model
 * Description:
 * Created by 师春雷
 * Created at 18/4/15 下午12:22
 */
public class CarTypeSection extends SectionEntity<CarBean> {

    public CarTypeSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public CarTypeSection(CarBean carBean) {
        super(carBean);
    }
}
