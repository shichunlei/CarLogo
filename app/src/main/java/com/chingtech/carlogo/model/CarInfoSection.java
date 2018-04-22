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
 * Created at 18/4/22 下午3:23
 */
public class CarInfoSection extends SectionEntity<CarInfo> {

    public CarInfoSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public CarInfoSection(CarInfo info) {
        super(info);
    }
}
