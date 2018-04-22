package com.chingtech.carlogo.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chingtech.carlogo.R;
import com.chingtech.carlogo.model.CarInfoSection;
import com.chingtech.library.base.holder.BaseViewHolderHelper;
import com.zhy.autolayout.utils.AutoUtils;
import java.util.List;

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
 * Package com.chingtech.carlogo.adapter
 * Description:
 * Created by 师春雷
 * Created at 18/4/22 下午3:24
 */
public class CarDetailsAdapter
        extends BaseSectionQuickAdapter<CarInfoSection, BaseViewHolderHelper> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CarDetailsAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolderHelper helper, CarInfoSection item) {
        AutoUtils.autoSize(helper.itemView);

        helper.setText(R.id.tv_type_header, item.header);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, CarInfoSection item) {
        AutoUtils.autoSize(helper.itemView);

        helper.setText(R.id.tv_key, item.t.getKey()).setText(R.id.tv_value, item.t.getValue());
    }
}
