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
 * Created at 18/4/14 上午11:08
 */
public class BrandAdapter extends BaseQuickAdapter<CarBean, BaseViewHolderHelper> {

    public BrandAdapter(@Nullable List<CarBean> data) {
        super(R.layout.item_brand_list, data);
    }

    @Override
    protected void convert(BaseViewHolderHelper helper, CarBean item) {
        AutoUtils.autoSize(helper.itemView);

        helper.setImageUrl(R.id.image_logo, item.getLogo(), R.drawable.icon_loading)
              .setText(R.id.tv_name, item.getName());
    }
}
