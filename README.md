# GroupGridList
自定义布局的显示处理
主要处理较复杂的ui，又不喜欢用expandListview, 所以就想了个办法， 把以往的expandListView中的group当做一条数据，把childview中的复杂ui拆分开，按行分组

分组接口已定义出来，分组你自己可以控制

    /**
     * 如果类型比较多,需要复写;
     */
    @Override
    public int getItemViewType(int position) {
        if (mRowList == null) {
            return -1;
        }
        RowItem item = mRowList.get(position);
        return item.getShowStyle();
    }
    
    @Override
    public int getViewTypeCount() {
        return ShowStyle.STYLE_MAX_VALUE;// 要大于getItemViewType中的最大值
    }

不同样式的view定义不同的ViewType，可以自己增加扩展
    public static interface ShowStyle { 
        //样式最大值，要大于所有的样式值 
        int STYLE_MAX_VALUE = 2;
    
        // 0:group
        int STYLE_GROUP = 0;
        // 1:2个个长图
        int STYLE_TWO_SQUARE_IMG = 1;
    
    }
