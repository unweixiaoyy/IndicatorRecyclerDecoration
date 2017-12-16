package com.example.jialong.indicatorrecyclerdecoration.ItemDecoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jialong.indicatorrecyclerdecoration.R;


/**
 * Created by chenjialong on 2017/12/11.
 * 用RecyclerView实现的滑动列表
 * 用RecyclerView.ItemDecoration实现的指示器
 */
public class IndicatorItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;

    private Paint indicatorPaint;

    private int indicatorWidth;
    private int indicatorHeight;

    private int currentPosition = 0;
    private float movedPercentage = 0f;

    private static final float leftPercent = 0.33f;
    private static final float rightPercent = 0.66f;


    private boolean isOutScroll = false;


    public IndicatorItemDecoration(Context context) {
        this.context = context;
        indicatorPaint = new Paint();
        Resources resources = context.getResources();
        indicatorPaint.setColor(resources.getColor(R.color.colorAccent));
        indicatorWidth = 54;
        indicatorHeight = 15;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //不是外部滑动状态
        if (!isOutScroll) {
            View currentView = layoutManager.findViewByPosition(currentPosition);
            if (currentView == null) {
                return;
            }
            float left = (currentView.getWidth() / 2 - indicatorWidth / 2) + currentView.getLeft();
            float right = left + indicatorWidth;
            float bottom = parent.getBottom();
            float top = bottom - indicatorHeight;
            c.drawRect(left, top, right, bottom, indicatorPaint);
            return;
        }
        //外部滑动状态
        View currentView = layoutManager.findViewByPosition(currentPosition);
        View nextView = layoutManager.findViewByPosition(currentPosition + 1);
        int currentViewWidth = currentView.getWidth();
        int nextViewWidth = nextView != null ? nextView.getWidth() :0;
        int minMoveDistance = (currentViewWidth + nextViewWidth) / 2 / 2;
        //开始计算长度渐变
        float rightMargin = 0f;
        float leftMargin = 0f;
        if (movedPercentage <= leftPercent) {//小于0。33
            leftMargin = 0f;
            rightMargin = minMoveDistance * (movedPercentage / leftPercent);
        } else if (movedPercentage >= rightPercent) {//大于0。66
            leftMargin = minMoveDistance + minMoveDistance * ((movedPercentage - rightPercent) / leftPercent);
            rightMargin = minMoveDistance * ((1 - movedPercentage) / leftPercent);
        } else {//0。33和0。66之间
            leftMargin = minMoveDistance * ((movedPercentage - leftPercent) / leftPercent);
            rightMargin = minMoveDistance;
        }
        float left = (currentViewWidth / 2 - indicatorWidth / 2) + currentView.getLeft() + leftMargin;
        float right = left + indicatorWidth + rightMargin;
        float bottom = parent.getBottom();
        float top = bottom - indicatorHeight;
        c.drawRect(left, top, right, bottom, indicatorPaint);
    }

    /**
     *
     * @param position
     */
    public void setSelected(int position) {
        setSelected(position, false);
    }


    /**
     *
     * @param position
     * @param isOutScroll
     */
    public void setSelected(int position, boolean isOutScroll) {
        currentPosition = position;
        this.isOutScroll = isOutScroll;
    }

    // 滑动到时候更新进度
    public void setOutScroll(boolean isOutScroll) {
        this.isOutScroll = isOutScroll;
    }

    // TODO: 2017/12/16
    // 滑动到时候更新进度
    public void setMovedPercentage(int position, float percentage) {
        currentPosition = position;
        movedPercentage = percentage;
    }

    //指示器宽度
    public void setIndicatorWidth(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
    }
    //指示器高度
    public void setIndicatorHeight(int indicatorHeight) {
        this.indicatorHeight = indicatorHeight;
    }

    //指示器颜色
    // TODO: 2017/12/16 支持渐变
    public void setIndicatorColor(@ColorRes int indicatorColor){
        indicatorPaint.setColor(context.getResources().getColor(indicatorColor));
    }

    // TODO: 2017/12/16 圆角之类到

}
