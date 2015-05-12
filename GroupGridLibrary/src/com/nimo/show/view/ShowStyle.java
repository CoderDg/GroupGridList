package com.nimo.show.view;

import android.R.integer;

public class ShowStyle {
        //样式最大值，要大于所有的样式值
        public static int STYLE_MAX_VALUE=2;
        
        private int styleValue;
        
        // 0:group
        public final static int STYLE_GROUP = 0;
        
        public ShowStyle(int value) {
            this.styleValue = value;
        }
        
        public int getStyleValue() {
            return styleValue;
        }
        public void setStyleValue(int styleValue) {
            this.styleValue = styleValue;
        }
}
