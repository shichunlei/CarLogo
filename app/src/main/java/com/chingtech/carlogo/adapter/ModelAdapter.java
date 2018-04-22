package com.chingtech.carlogo.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chingtech.carlogo.R;
import com.chingtech.carlogo.model.CarBean;
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
 * Created at 18/4/15 下午12:08
 */
public class ModelAdapter extends BaseQuickAdapter<CarBean, BaseViewHolderHelper> {

    public ModelAdapter(@Nullable List<CarBean> data) {
        super(R.layout.item_model, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, CarBean item) {
        AutoUtils.autoSize(helper.itemView);

        helper.setImageUrl(R.id.iv_logo, item.getLogo(), R.drawable.icon_loading)
              .setText(R.id.tv_name, item.getName())
              .setText(R.id.tv_price, item.getPrice())
              .setText(R.id.tv_sale, item.getProductionstate() + "/" + item.getSalestate())
              .setText(R.id.tv_yeartype, item.getYeartype())
              .setText(R.id.tv_sizetype, item.getSizetype());
    }
}
